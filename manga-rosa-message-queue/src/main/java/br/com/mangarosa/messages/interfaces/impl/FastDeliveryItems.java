package br.com.mangarosa.messages.interfaces.impl;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.List;

public class FastDeliveryItems implements Topic {

    @Override
    public String name() {
        return "";
    }

    @Override
    public void addMessage(Message message) {

    }

    @Override
    public void subscribe(Consumer consumer) {

    }

    @Override
    public void unsubscribe(Consumer consumer) {

    }

    @Override
    public List<Consumer> consumers() {
        return List.of();
    }

    @Override
    public MessageRepository getRepository() {
        return null;
    }

    @Override
    public void notifyConsumers(Message message) {
        Topic.super.notifyConsumers(message);
    }
}
