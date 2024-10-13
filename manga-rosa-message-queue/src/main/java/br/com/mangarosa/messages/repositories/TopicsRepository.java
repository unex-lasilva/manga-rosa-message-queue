package br.com.mangarosa.messages.repositories;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.MessageRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class TopicsRepository implements MessageRepository{
    private final HashMap<String, HashMap<String, Message>> robsonDB;

    public TopicsRepository() {
        this.robsonDB = new HashMap<>();
    }

    @Override
    public void append(String topic, Message message) {
        if (!robsonDB.containsKey(topic)) {
            HashMap<String, Message> messages = new HashMap<>();
            messages.put(message.getId(), message);
            robsonDB.put(topic, messages);
        } else {
            robsonDB.get(topic).put(message.getId(), message);
        }
    }

    @Override
    public void consumeMessage(String topic, UUID messageId) {
        Message message = getMessage(topic, messageId.toString());
        message.setConsumed(true);
        robsonDB.get(topic).put(messageId.toString(), message);
    }

    @Override
    public List<Message> getAllNotConsumedMessagesByTopic(String topic) {
        if (!robsonDB.containsKey(topic)) {
            throw new IllegalArgumentException("No such topic " + topic);
        }
        List<Message> messages = new ArrayList<>();
        for (Message message : robsonDB.get(topic).values()) {
            if (!message.isConsumed() && !message.isExpired()) {
                messages.add(message);
            }
        }
        return messages;
    }

    @Override
    public List<Message> getAllConsumedMessagesByTopic(String topic) {
        if (!robsonDB.containsKey(topic)) {
            throw new IllegalArgumentException("No such topic " + topic);
        }
        List<Message> messages = new ArrayList<>();
        for (Message message : robsonDB.get(topic).values()) {
            if (message.isConsumed() && !message.isExpired()) {
                messages.add(message);
            }
        }
        return messages;
    }

    private Message getMessage(String topic, String messageId) {
        if (!robsonDB.containsKey(topic)) {
            throw new IllegalArgumentException("No such topic " + topic);
        }
        if (!robsonDB.get(topic).containsKey(messageId)) {
            throw new IllegalArgumentException("No such message " + messageId);
        }
        return robsonDB.get(topic).get(messageId);
    }
}
