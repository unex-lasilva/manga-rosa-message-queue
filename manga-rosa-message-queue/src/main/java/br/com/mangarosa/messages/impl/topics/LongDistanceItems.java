package br.com.mangarosa.messages.impl.topics;

import java.util.ArrayList;
import java.util.List;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

public class LongDistanceItems implements Topic {
    private final List<Consumer> consumers;
    private MessageRepository messageRepository;

    public LongDistanceItems(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.consumers = new ArrayList<>();
    }

    @Override
    public String name() {
        return "queue/long-distance-items";
    }

    @Override
    public void addMessage(Message message) {
        messageRepository.append(name(), message);
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
