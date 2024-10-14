package br.com.mangarosa.messages.impl;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import java.util.*;

public class RepositoryImpl implements MessageRepository {

    private final Map<String, LinkedQueue<Message>> topicMessages = new HashMap<>();
    private final Set<String> consumedMessages = new HashSet<>();

    /**
     * Adiciona uma nova mensagem ao repositório sob um tópico específico.
     *
     * @param topic   o tópico onde a mensagem será armazenada
     * @param message a mensagem a ser adicionada
     */
    @Override
    public void append(String topic, Message message) {
        // Verifica se o tópico já existe, se não, cria uma nova lista de mensagens
        topicMessages.computeIfAbsent(topic, k -> new LinkedQueue<>()).enqueue(message);
        message.setId(UUID.randomUUID().toString()); // Gera um ID para a mensagem
    }

    /**
     * Consome uma mensagem específica de um tópico pelo seu ID.
     *
     * @param topic     o tópico de onde a mensagem será consumida
     * @param messageId o ID da mensagem a ser consumida
     */
    @Override
    public void consumeMessage(String topic, UUID messageId) {
        // Verifica se o tópico existe
        LinkedQueue<Message> queue = topicMessages.get(topic);
        if (queue == null) {
            throw new NoSuchElementException("Tópico não existe.");
        }

        // Procura a mensagem pelo ID e marca como consumida
        Iterator<Message> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Message message = iterator.next();
            if (message.getId().equals(messageId.toString())) {
                // Verifica se a mensagem está expirada antes de consumi-la
                if (message.isExpired()) {
                    throw new IllegalStateException("A mensagem já expirou e não pode ser consumida.");
                }
                message.setConsumed(true);
                consumedMessages.add(messageId.toString());
                return;
            }
        }
        throw new NoSuchElementException("Mensagem não encontrada no tópico.");
    }

    /**
     * Retorna todas as mensagens não consumidas de um tópico específico.
     *
     * @param topic o tópico de onde as mensagens serão obtidas
     * @return uma lista de mensagens não consumidas
     */
    @Override
    public List<Message> getAllNotConsumedMessagesByTopic(String topic) {
        // Verifica se o tópico existe
        LinkedQueue<Message> queue = topicMessages.get(topic);
        if (queue == null) {
            throw new NoSuchElementException("Tópico não existe.");
        }

        List<Message> notConsumedMessages = new ArrayList<>();
        for (Message message : queue) {
            // Adiciona apenas mensagens não consumidas e não expiradas à lista
            if (!message.isConsumed() && !message.isExpired()) {
                notConsumedMessages.add(message);
            }
        }
        return notConsumedMessages;
    }

    /**
     * Retorna todas as mensagens consumidas de um tópico específico.
     *
     * @param topic o tópico de onde as mensagens serão obtidas
     * @return uma lista de mensagens consumidas
     */
    @Override
    public List<Message> getAllConsumedMessagesByTopic(String topic) {
        // Verifica se o tópico existe
        LinkedQueue<Message> queue = topicMessages.get(topic);
        if (queue == null) {
            throw new NoSuchElementException("Tópico não existe.");
        }

        List<Message> consumedMessagesList = new ArrayList<>();
        for (Message message : queue) {
            // Adiciona apenas mensagens consumidas e não expiradas à lista
            if (message.isConsumed() && !message.isExpired()) {
                consumedMessagesList.add(message);
            }
        }
        return consumedMessagesList;
    }
}