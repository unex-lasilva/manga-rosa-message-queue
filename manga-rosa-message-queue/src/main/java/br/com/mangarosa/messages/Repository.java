package br.com.mangarosa.messages;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.UUID;

import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;


public class Repository implements MessageRepository {
    
    public Map<String, Topic> topics;

    public Repository(){
        this.topics = new HashMap<>();
    }    
    
    @Override
    public void append(String topic, Message message) {
        if(!topics.keySet().contains(topic)){
            SimpleTopic newTopic = new SimpleTopic(topic);
            newTopic.addMessage(message);
        }


        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'append'");
    }

    @Override
    public void consumeMessage(String topic, UUID messageId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consumeMessage'");
    }

    @Override
    public List<Message> getAllNotConsumedMessagesByTopic(String topic) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllNotConsumedMessagesByTopic'");
    }

    @Override
    public List<Message> getAllConsumedMessagesByTopic(String topic) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllConsumedMessagesByTopic'");
    }

}
