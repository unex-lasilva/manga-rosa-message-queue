package br.com.mangarosa.messages.producers;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Producer;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.HashMap;

public class PyMarketPlaceProducer implements Producer {
    private final HashMap<String,Topic> topics;
    private static final String DEFAULT_TOPIC_NAME = "queue/long-distance-items";

    public PyMarketPlaceProducer() {
        topics = new HashMap<>();
    }

    @Override
    public void addTopic(Topic topic) {
        this.topics.put(topic.name(), topic);
    }

    @Override
    public void removeTopic(Topic topic) {
        this.topics.remove(topic.name());
    }

    @Override
    public void sendMessage(String message) {
        Message msg = new Message(this, message);
        if (topics.containsKey(name())) {
            topics.get(name()).addMessage(msg);
        }
    }

    @Override
    public String name() {
        return DEFAULT_TOPIC_NAME;
    }
}
