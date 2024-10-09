package br.com.mangarosa.messages.impl.producers;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.MessageBroker;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Producer;
import br.com.mangarosa.messages.interfaces.Topic;

public class PyMarketPlaceProducer implements Producer {
    MessageBroker broker;
    MessageRepository repository;
    Message message;
    Topic topic;

    public PyMarketPlaceProducer(MessageBroker broker, MessageRepository repository) {
        this.broker = broker;
        this.repository = repository;
    }

    @Override
    public void addTopic(Topic topic) {
        this.broker.createTopic(topic);
        this.topic = topic;
    }

    @Override
    public void removeTopic(Topic topic) {
        this.broker.removeTopic(topic.name());
    }

    @Override
    public void sendMessage(String message) {
        Message newMessage = new Message(this, message);
        this.repository.append(this.topic.name(), this.message);
        this.broker.getTopicByName(topic.name()).notifyConsumers(newMessage);
    }

    @Override
    public String name() {
        return "PyMarketPlaceProducer";
    }
}
