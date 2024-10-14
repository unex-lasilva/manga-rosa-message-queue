package br.com.mangarosa.messages.interfaces.impl;

import java.util.*;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.MessageRepository;

/**
 * Implementação do repositório de mensagens.
 * O repositório é baseado em um mapa que armazena as mensagens por tópico.
 */
public class Repository implements MessageRepository {

    // Mapa para armazenar as mensagens por tópico. Cada tópico tem uma lista de mensagens.
    private final Map<String, List<Message>> topicMessages;
    private final Map<String, List<Message>> consumedMessages;

    // Construtor da classe Repository
    public Repository() {
        this.topicMessages = new HashMap<>();
        this.consumedMessages = new HashMap<>();
    }

    @Override
    public void append(String topic, Message message) {
        // Se o tópico não existir, crie um novo
        topicMessages.computeIfAbsent(topic, k -> new ArrayList<>());

        // Adiciona a mensagem ao final da fila do tópico
        topicMessages.get(topic).add(message);
    }

    @Override
    public void consumeMessage(String topic, UUID messageId) {
        // Verifica se o tópico existe
        if (!topicMessages.containsKey(topic)) {
            throw new IllegalArgumentException("Tópico não encontrado: " + topic);
        }

        // Busca a mensagem pelo messageId
        List<Message> messages = topicMessages.get(topic);
        Optional<Message> messageToConsume = messages.stream()
                .filter(message -> message.getId().equals(messageId))
                .findFirst();

        // Se a mensagem não for encontrada, lança uma exceção
        if (!messageToConsume.isPresent()) {
            throw new IllegalArgumentException("Mensagem com ID " + messageId + " não encontrada no tópico " + topic);
        }

        // Remove a mensagem da lista de não consumidas e a adiciona na lista de consumidas
        Message consumedMessage = messageToConsume.get();
        messages.remove(consumedMessage);

        // Armazena a mensagem como consumida
        consumedMessages.computeIfAbsent(topic, k -> new ArrayList<>());
        consumedMessages.get(topic).add(consumedMessage);
    }

    @Override
    public List<Message> getAllNotConsumedMessagesByTopic(String topic) {
        // Verifica se o tópico existe
        if (!topicMessages.containsKey(topic)) {
            throw new IllegalArgumentException("Tópico não encontrado: " + topic);
        }

        // Retorna todas as mensagens não consumidas
        return new ArrayList<>(topicMessages.get(topic));
    }

    @Override
    public List<Message> getAllConsumedMessagesByTopic(String topic) {
        // Verifica se o tópico existe
        if (!consumedMessages.containsKey(topic)) {
            throw new IllegalArgumentException("Tópico não encontrado: " + topic);
        }

        // Retorna todas as mensagens consumidas
        return new ArrayList<>(consumedMessages.get(topic));
    }
}
