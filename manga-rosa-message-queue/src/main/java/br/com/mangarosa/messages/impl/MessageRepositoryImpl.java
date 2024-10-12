package br.com.mangarosa.messages.impl;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import java.util.*;

/**
 * Implementação da interface MessageRepository. Gerencia tópicos e suas respectivas mensagens,
 * permitindo a adição, consumo e consulta de mensagens com base em seus estados (consumida ou não consumida).
 */
public class MessageRepositoryImpl implements MessageRepository {

    /**
     * Mapa que associa cada tópico a uma fila de mensagens.
     * A chave do mapa é o nome do tópico, e o valor é uma fila de mensagens associadas a esse tópico.
     */
    private final Map<String, Queue<Message>> topicMap = new HashMap<>();

    /**
     * Adiciona uma nova mensagem a um tópico específico.
     * Se o tópico não existir, ele será criado.
     *
     * @param topic o nome do tópico
     * @param message a mensagem a ser adicionada ao tópico
     */
    @Override
    public void append(String topic, Message message) {
        topicMap.computeIfAbsent(topic, k -> new LinkedList<>()).add(message);
    }

    /**
     * Marca uma mensagem como consumida em um tópico específico, com base no seu ID.
     *
     * @param topic o nome do tópico
     * @param messageId o ID da mensagem a ser marcada como consumida
     * @throws IllegalArgumentException se o tópico não existir ou a mensagem não for encontrada
     */
    @Override
    public void consumeMessage(String topic, UUID messageId) {
        Queue<Message> messages = topicMap.get(topic);
        if (messages == null) {
            throw new IllegalArgumentException("Topic does not exist");
        }

        boolean found = false;
        for (Message message : messages) {
            if (message.getId().equals(messageId.toString())) {
                message.setConsumed(true);  // Marca a mensagem como consumida
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Message not found in topic.");
        }
    }

    /**
     * Retorna todas as mensagens não consumidas de um tópico específico.
     *
     * @param topic o nome do tópico
     * @return uma lista de mensagens não consumidas e que não expiraram
     * @throws IllegalArgumentException se o tópico não existir
     */
    @Override
    public List<Message> getAllNotConsumedMessagesByTopic(String topic) {
        Queue<Message> messages = topicMap.get(topic);
        if (messages == null) {
            throw new IllegalArgumentException("Topic does not exist.");
        }

        List<Message> notConsumed = new ArrayList<>();
        for (Message message : messages) {
            if (!message.isConsumed() && !message.isExpired()) {  // Mensagens não consumidas e não expiradas
                notConsumed.add(message);
            }
        }

        return notConsumed;
    }

    /**
     * Retorna todas as mensagens consumidas de um tópico específico.
     *
     * @param topic o nome do tópico
     * @return uma lista de mensagens consumidas que ainda não expiraram
     * @throws IllegalArgumentException se o tópico não existir
     */
    @Override
    public List<Message> getAllConsumedMessagesByTopic(String topic) {
        Queue<Message> messages = topicMap.get(topic);
        if (messages == null) {
            throw new IllegalArgumentException("Topic does not exist");
        }

        List<Message> consumed = new ArrayList<>();
        for (Message message : messages) {
            if (message.isConsumed() && !message.isExpired()) {  // Mensagens consumidas e não expiradas
                consumed.add(message);
            }
        }

        return consumed;
    }
}
