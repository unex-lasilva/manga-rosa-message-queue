package br.com.mangarosa.messages.impl;

import java.util.List;
import java.util.UUID;
import java.util.Map;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

public class Repository implements MessageRepository {

    public Map<String, Topic> topics;
    

    @Override
    public void append(String topic, Message message) {
        if (this.topics.containsKey(topic)) {
            this.topics.get(topic).addMessage(message);
        };
    }

    @Override
    public void consumeMessage(String topic, UUID messageId) {
        Message message;
        if()
        
    }

    @Override
    public List<Message> getAllNotConsumedMessagesByTopic(String topic) {
        
        return null;
    }

    @Override
    public List<Message> getAllConsumedMessagesByTopic(String topic) {
        return null;

    }

}
