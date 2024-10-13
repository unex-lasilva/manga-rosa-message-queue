package br.com.mangarosa.messages.impl;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.ArrayList;

public class MessageRepositoryImpl implements MessageRepository {

    private final Map<String, LinkedQueue<Message>> topicQueues = new HashMap<>();

    @Override
    public void append(String topic, Message message) {
        LinkedQueue<Message> queue = topicQueues.getOrDefault(topic, new LinkedQueue<>());
        queue.enqueue(message);
        topicQueues.put(topic, queue);
    }

    @Override
    public void consumeMessage(String topic, UUID messageId) {
        LinkedQueue<Message> queue = topicQueues.get(topic);
        if (queue == null) {
            throw new IllegalArgumentException("Tópico não encontrado.");
        }
        Message foundMessage = null;
        for (int i = 0; i < queue.size(); i++) {
            Message message = queue.dequeue();
            if (message.getId().equals(messageId.toString())) {
                foundMessage = message;
                break;
            }
            queue.enqueue(message);
        }

        if (foundMessage == null) {
            throw new IllegalArgumentException("Mensagem não encontrada no tópico.");
        }

        foundMessage.setConsumed(true);
    }

    @Override
    public List<Message> getAllNotConsumedMessagesByTopic(String topic) {
        LinkedQueue<Message> queue = topicQueues.get(topic);
        if (queue == null) {
            throw new IllegalArgumentException("Tópico não encontrado.");
        }

        List<Message> notConsumedMessages = new ArrayList<>();
        for (int i = 0; i < queue.size(); i++) {
            Message message = queue.dequeue();
            if (!message.isConsumed()) {
                notConsumedMessages.add(message);
            }
            queue.enqueue(message);
        }

        return notConsumedMessages;
    }

    @Override
    public List<Message> getAllConsumedMessagesByTopic(String topic) {
        LinkedQueue<Message> queue = topicQueues.get(topic);
        if (queue == null) {
            throw new IllegalArgumentException("Tópico não encontrado.");
        }

        List<Message> consumedMessages = new ArrayList<>();
        for (int i = 0; i < queue.size(); i++) {
            Message message = queue.dequeue();
            if (message.isConsumed()) {
                consumedMessages.add(message);
            }
            queue.enqueue(message);
        }

        return consumedMessages;
    }
}
