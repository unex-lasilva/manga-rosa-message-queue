package br.com.mangarosa.messages.interfaces;

import br.com.mangarosa.messages.Message;
import java.util.*;

/**
 * Implementação da interface MessageRepository.
 */
public class MsgRepositoryImpl implements MessageRepository {
    private final Map<String, List<Message>> topics; // Mapa de tópicos, cada um contendo uma lista de mensagens

    /**
     * Construtor que inicializa o repositório de mensagens.
     */
    public MsgRepositoryImpl() {
        this.topics = new HashMap<>();
    }

    /**
     * Adiciona uma mensagem a um tópico específico.
     * @param topic o tópico onde a mensagem será adicionada
     * @param message a mensagem a ser adicionada
     */
    @Override
    public void append(String topic, Message message) {
        if (this.topics.get(topic) == null) {
            this.topics.put(topic, new ArrayList<>());
        }
        topics.get(topic).add(message);
    }

    /**
     * Marca uma mensagem como consumida com base no ID da mensagem.
     * @param topic o tópico que contém a mensagem
     * @param messageId o ID da mensagem a ser consumida
     */
    @Override
    public void consumeMessage(String topic, UUID messageId) {
        List<Message> messages = topics.get(topic);
        if (messages == null) throw new IllegalArgumentException(topic + " not found");
        for (Message message : messages) {
            String uuid = message.getId();
            if (Objects.equals(uuid, messageId.toString())) {
                message.setConsumed(true);
            }
        }
    }

    /**
     * Retorna todas as mensagens não consumidas de um tópico específico.
     * @param topic o tópico para recuperar as mensagens
     * @return a lista de mensagens não consumidas
     */
    @Override
    public List<Message> getAllNotConsumedMessagesByTopic(String topic) {
        List<Message> messages = topics.get(topic);
        if (messages == null) throw new IllegalArgumentException(topic + " not found");
        for (Message message : messages) {
            if (message.isConsumed()) {
                messages.remove(message);
            }
        }
        return messages;
    }

    /**
     * Retorna todas as mensagens consumidas de um tópico específico.
     * @param topic o tópico para recuperar as mensagens
     * @return a lista de mensagens consumidas
     */
    @Override
    public List<Message> getAllConsumedMessagesByTopic(String topic) {
        List<Message> messages = topics.get(topic);
        if (messages == null) throw new IllegalArgumentException(topic + " not found");
        return messages.stream().filter(Message::isConsumed).toList();
    }
}
