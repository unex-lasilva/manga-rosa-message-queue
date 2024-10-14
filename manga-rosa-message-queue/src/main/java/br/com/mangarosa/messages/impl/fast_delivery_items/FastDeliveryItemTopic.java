package br.com.mangarosa.messages.impl.fast_delivery_items;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.impl.repository.MesageRepository;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastDeliveryItemTopic implements Topic {
    
    private final String name;
    private final MessageRepository messageRepository;
    private final List<Consumer> listConsumers;
    private final LinkedQueue<Message> queue;

    public FastDeliveryItemTopic(MesageRepository repository) {
        this.name = "queue/fast-delivery-items";
        this.messageRepository = repository;
        this.listConsumers = new ArrayList<>();
        this.queue = new LinkedQueue<Message>();
    }


    @Override
    public String name() {
        return this.name;
    }


    @Override
    public void addMessage(Message message) {
        if (message.isExpired()){
            System.out.println("Mensagem expirada: "+message.getMessage());
            return;
        }

        this.messageRepository.append(this.name, message);
        this.queue.enqueue(message);
        notifyConsumers(message,
                this.queue,
                this.messageRepository,
                this.name);
    }

    @Override
    public void subscribe(Consumer consumer) {
        this.listConsumers.add(consumer);
    }

    @Override
    public void unsubscribe(Consumer consumer) {
        this.listConsumers.remove(consumer);
    }

    @Override
    public List<Consumer> consumers() {
        return this.listConsumers;
    }

    @Override
    public MessageRepository getRepository() {
        return this.messageRepository;
    }
}
