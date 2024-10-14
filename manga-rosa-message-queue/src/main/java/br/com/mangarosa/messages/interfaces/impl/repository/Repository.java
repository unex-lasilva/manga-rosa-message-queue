package br.com.mangarosa.messages.interfaces.impl.repository;

import br.com.mangarosa.datastructures.interfaces.Queue;
import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.MessageRepository;

import java.util.*;

/**
 * A classe Repository implementa a interface MessageRepository,
 * sendo responsável por gerenciar a persistência de mensagens para diferentes tópicos.
 */
public class Repository implements MessageRepository {
    // Mapeia nomes de tópicos para filas de mensagens
    private final Map<String, Queue<Message>> topics = new HashMap<>();
    // Mapeia nomes de tópicos para listas de mensagens consumidas
    private final Map<String, List<Message>> consumedMessages = new HashMap<>();

    /**
     * Adiciona uma nova mensagem no final da fila do tópico.
     * Se o tópico não existir, ele deve ser criado e a mensagem adicionada.
     *
     * @param topic   O nome do tópico que deve ser único.
     * @param message A mensagem a ser adicionada.
     */
    @Override
    public void append(String topic, Message message) {
        if (!topics.containsKey(topic)) {
            topics.put(topic, new LinkedQueue<>());
        }
        topics.get(topic).enqueue(message);
    }

    /**
     * Grava uma mensagem como consumida.
     * Se o tópico não existir ou a mensagem com messageId também não existir no tópico, uma exceção deve ser lançada.
     *
     * @param topic     O nome do tópico que deve existir.
     * @param messageId O código da mensagem.
     */
    @Override
    public void consumeMessage(String topic, UUID messageId) {
        Queue<Message> messages = topics.get(topic);
        if (messages == null) {
            throw new NoSuchElementException("O tópico: " + topic + " não foi encontrado ou não existe.");
        }

        Message messageConsume = null;
        Queue<Message> queue = new LinkedQueue<>();

        // Procura e remove a mensagem específica da fila
        while (!messages.isEmpty()) {
            Message message = messages.dequeue();
            if (message.getId().equals(messageId.toString())) {
                messageConsume = message;
            } else {
                queue.enqueue(message);
            }
        }

        // Reinsere as mensagens restantes na fila original
        while (!queue.isEmpty()) {
            messages.enqueue(queue.dequeue());
        }

        if (messageConsume == null) {
            throw new NoSuchElementException("A mensagem: " + messageId + " não foi encontrada.");
        }

        // Adiciona a mensagem consumida na lista de mensagens consumidas
        if (!consumedMessages.containsKey(topic)) {
            consumedMessages.put(topic, new ArrayList<>());
        }
        consumedMessages.get(topic).add(messageConsume);
    }

    /**
     * Retorna todas as mensagens ainda não consumidas e não expiradas que estão num tópico.
     * Se um tópico não existir, uma exceção deve ser lançada.
     *
     * @param topic O nome do tópico.
     * @return Uma lista com todas as mensagens não consumidas.
     */
    @Override
    public List<Message> getAllNotConsumedMessagesByTopic(String topic) {
        Queue<Message> messages = topics.get(topic);
        if (messages == null) {
            throw new NoSuchElementException("Tópico não encontrado: " + topic);
        }

        List<Message> notConsumedMessages = new ArrayList<>();
        for (Message message : messages) {
            if (!message.isExperied()) {
                notConsumedMessages.add(message);
            }
        }
        return notConsumedMessages;
    }

    /**
     * Retorna todas as mensagens consumidas em um tópico e que ainda não foram expiradas.
     * Se um tópico não existir, uma exceção deve ser lançada.
     *
     * @param topic O nome do tópico.
     * @return Uma lista com todas as mensagens consumidas.
     */
    @Override
    public List<Message> getAllConsumedMessagesByTopic(String topic) {
        List<Message> messages = consumedMessages.get(topic);
        if (messages == null) {
            throw new NoSuchElementException("Tópico não encontrado: " + topic);
        }

        List<Message> validConsumedMessages = new ArrayList<>();
        for (Message message : messages) {
            if (!message.isExperied() && message.isConsumed()) {
                validConsumedMessages.add(message);
            }
        }
        return validConsumedMessages;
    }
}
