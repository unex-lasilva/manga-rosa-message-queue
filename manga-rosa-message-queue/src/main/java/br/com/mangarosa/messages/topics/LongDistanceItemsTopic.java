package br.com.mangarosa.messages.topics;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LongDistanceItemsTopic implements Topic {
    private static final String NAME = "queue/long-distance-items";
    private final LinkedQueue<Message> queue;
    private final List<Consumer> consumers;
    private final MessageRepository repository;

    public LongDistanceItemsTopic(MessageRepository repository) {
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
        return null;
    }

    @Override
    public void notifyConsumers(Message message) {
        Topic.super.notifyConsumers(message);
        queue.dequeue();
        repository.consumeMessage(NAME, UUID.fromString(message.getId()));
    }

}
