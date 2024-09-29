package br.com.mangarosa.messages.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.datastructures.interfaces.Queue;
import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

public class FastDeliveryItems implements Topic {
    public Queue<Message> queue;
    private final List<Consumer> consumers;
    private MessageRepository messageRepository;

    public FastDeliveryItems(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.consumers = new ArrayList<>();
        this.queue = new LinkedQueue<>(Message.class);
    }

    @Override
    public String name() {
        return "queue/fast-delivery-items";
    }

    @Override
    public void addMessage(Message message) {
        messageRepository.append(name(), message);
        this.queue.enqueue(message);
    }

    @Override
    public void subscribe(Consumer consumer) {
        this.consumers.add(consumer);
    }

    @Override
    public void unsubscribe(Consumer consumer) {
        this.consumers.remove(consumer);
    }

    @Override
    public List<Consumer> consumers() {
        return this.consumers;
    }

    @Override
    public MessageRepository getRepository() {
        return this.messageRepository;
    }

}
