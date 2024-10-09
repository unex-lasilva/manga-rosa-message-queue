package br.com.mangarosa.messages.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import br.com.mangarosa.datastructures.interfaces.Queue;
import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.datastructures.interfaces.impl.QueueNode;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.MessageRepository;

public class Repository implements MessageRepository {

    public Map<String, Queue<Message>> Queues = new HashMap<>();

    @Override
    public void append(String topic, Message message) {
        if (this.Queues.containsKey(topic)) {
            this.Queues.get(topic).enqueue(message);
        } else {
            this.Queues.put(topic, new LinkedQueue<>(Message.class));
            this.Queues.get(topic).enqueue(message);
        }
    }

    @Override
    public void consumeMessage(String topic, UUID messageId) {
        if (!this.Queues.containsKey(topic))
            throw new NoSuchElementException("the topic" + topic + "does not exist or the message with messageId"
                    + messageId + "does not exist in the topic either");

        Queue<Message> linkedQueue = this.Queues.get(topic);
        while (linkedQueue.iterator().hasNext()) {
            Message message = linkedQueue.iterator().next();
            if (Objects.equals(message.getId(), messageId.toString())) {
                message.setConsumed(true);
            }
        }

    }

    @Override
    public List<Message> getAllNotConsumedMessagesByTopic(String topic) {
        if (!this.Queues.containsKey(topic))
            throw new NoSuchElementException("Key " + topic + " not found in the map.");

        List<Message> listMessages = Arrays.asList(this.Queues.get(topic).toArray());
        List<Message> listMessagesNotConsumed = new ArrayList<>();
        for (Message message : listMessages) {
            if (!message.isConsumed() && !message.isExperied()) {
                listMessagesNotConsumed.add(message);
            }
        }

        return listMessagesNotConsumed;
    }

    @Override
    public List<Message> getAllConsumedMessagesByTopic(String topic) {
        if (!this.Queues.containsKey(topic))
            throw new NoSuchElementException("Key " + topic + " not found in the map.");

        List<Message> listMessages = Arrays.asList(this.Queues.get(topic).toArray());
        List<Message> listMessagesConsumed = new ArrayList<>();
        for (Message message : listMessages) {
            if (message.isConsumed() && !message.isExperied()) {
                listMessagesConsumed.add(message);
            }
        }

        return listMessagesConsumed;
    }

}
