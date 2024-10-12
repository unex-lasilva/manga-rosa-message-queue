package br.com.mangarosa.messages.topics;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;

public class FastDeliveryItems implements Topic {
    private static final String NAME = "queue/fast-delivery-items";
    private LinkedQueue<Message> queue;
    private List<Consumer> consumers;
    private final MessageRepository repository;

    public FastDeliveryItems(MessageRepository repository) {
        this.repository = repository;
        this.queue = new LinkedQueue<Message>();
        this.consumers = new ArrayList<Consumer>();
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public void addMessage(Message message) {
        queue.enqueue(message);
        repository.append(NAME, message);
    }

    @Override
    public void subscribe(Consumer consumer) {
        consumers.add(consumer);
    }

    @Override
    public void unsubscribe(Consumer consumer) {
        consumers.remove(consumer);
    }

    @Override
    public List<Consumer> consumers() {
        return consumers;
    }

    @Override
    public MessageRepository getRepository() {
        return repository;
    }

    @Override
    public void notifyConsumers(Message message) {
        Topic.super.notifyConsumers(message);
    }
}
