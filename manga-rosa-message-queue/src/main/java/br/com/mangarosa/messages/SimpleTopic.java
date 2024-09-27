package br.com.mangarosa.messages;

import java.util.List;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

public class SimpleTopic implements Topic {
    private LinkedQueue<Message> Queue;
    private String name;

    public SimpleTopic(String name) {
        this.Queue = new LinkedQueue<>(Message.class);
        this.name = name;

    }

    @Override
    public void addMessage(Message message) {
        this.Queue.enqueue(message);

    }

    @Override
    public List<Consumer> consumers() {

        return null;
    }

    @Override
    public MessageRepository getRepository() {

        return null;
    }

    @Override
    public String name() {

        return null;
    }

    @Override
    public void subscribe(Consumer consumer) {

    }

    @Override
    public void unsubscribe(Consumer consumer) {

    }

}
