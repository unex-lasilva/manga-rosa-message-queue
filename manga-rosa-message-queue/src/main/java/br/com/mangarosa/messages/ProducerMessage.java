package br.com.mangarosa.messages;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import br.com.mangarosa.messages.interfaces.Producer;
import br.com.mangarosa.messages.interfaces.Topic;

public class ProducerMessage implements Producer {
    private Message message;
    private MessageBroker broker;
    private Repository repository;
    private Topic topic;

    public ProducerMessage(MessageBroker broker, Repository repository) {
        this.broker = broker;
        this.repository = repository;
    }

    @Override
    public void addTopic(Topic topic) {
        if (!repository.topics.keySet().contains(topic.name())) {
            this.broker.createTopic(topic);
            repository.topics.put(topic.name(), topic);
        } else {
            this.topic = topic;
        }
    }

    @Override
    public void removeTopic(Topic topic) {
        broker.removeTopic(topic.name());
    }

    @Override
    public void sendMessage(String message) {
        this.message = new Message(this, message);
        repository.append(this.topic.name(), this.message);

    }

    @Override
    public String name() {
        return null;
    }

}
