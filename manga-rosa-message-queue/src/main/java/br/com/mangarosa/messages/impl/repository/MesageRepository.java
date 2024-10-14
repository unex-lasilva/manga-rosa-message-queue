package br.com.mangarosa.messages.impl.repository;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.MessageRepository;

import java.util.*;
import java.util.UUID;

public class MesageRepository implements MessageRepository {

    private final Map<String, List<Message>> topicMessages;

    public MesageRepository() {
        this.topicMessages = new HashMap<>();
    }

    @Override
    public void append(String topic, Message message) {

        topicMessages.putIfAbsent(topic, new ArrayList<Message>());
        topicMessages.get(topic).add(message);
    }

    @Override
    public void consumeMessage(String topic, UUID messageId) {

        if (!topicMessages.containsKey(topic)) {
            throw new IllegalArgumentException("Tópico não encontrado: " + topic);
        }

        List<Message> messages = topicMessages.get(topic);
        for (Message msg : messages){
            if (msg.getId().equals(messageId.toString())){
                msg.setConsumed(true);
                return;
            }
        }
        throw new IllegalArgumentException("Mensagem não encontrada: " + messageId);
    }

    @Override
    public List<Message> getAllNotConsumedMessagesByTopic(String topic) {

        if (!topicMessages.containsKey(topic)) {
            throw new IllegalArgumentException("Tópico não encontrado: " + topic);
        }


        List<Message> messages = topicMessages.get(topic);
        List<Message> notConsumedMessages = new ArrayList<>();
        for (Message message : messages) {
            if (!message.isConsumed()){
                notConsumedMessages.add(message);
            }
        }

        return notConsumedMessages;
    }

    @Override
    public List<Message> getAllConsumedMessagesByTopic(String topic) {
        if (!topicMessages.containsKey(topic)) {
            throw new IllegalArgumentException("Tópico não encontrado: " + topic);
        }

        List<Message> messages = topicMessages.get(topic);
        List<Message> consumedMessagesList = new ArrayList<>();
        for (Message message : messages) {
            if (message.isConsumed()) {
                consumedMessagesList.add(message);
            }
        }

        return consumedMessagesList;
    }
}
