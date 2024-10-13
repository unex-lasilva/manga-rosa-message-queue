package br.com.mangarosa.messages.topics;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.impl.MessageRepositoryImpl;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;

public class LongDistanceItensTopic implements Topic {
    private final String name = "queue/long-distance-items";
    private LinkedQueue<Message> messageQueue = new LinkedQueue<>();
    private List<Consumer> consumers = new ArrayList<>();
    private MessageRepository repository = new MessageRepositoryImpl();

    public void LongDistanceItensTopic(MessageRepository repository) {
        this.messageQueue = new LinkedQueue<>();
        this.consumers = new ArrayList<>();
        this.repository = repository;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public void addMessage(Message message) {
        messageQueue.enqueue(message);
        repository.append(name, message);
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
    public void processMessages() {
        while (!messageQueue.isEmpty()) {
            Message message = messageQueue.dequeue();
            if (message.isExpired()) {
                System.out.println("Mensagem expirada e descartada: " + message.getMessage());
                continue;
            }
            notifyConsumers(message);
        }
    }

    public void notifyConsumers(Message message) {
        for (Consumer consumer : consumers) {
            consumer.consume(message);
        }
    }
}
