package br.com.mangarosa.messages.impl.fast_delivery_items.producers;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Producer;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.UUID;

public class PhysicPersonDeliveryProducer implements Producer {

    private final String name;
    private final ArrayList<Topic> listTopics;
    private int roundRobinCount;

    public PhysicPersonDeliveryProducer() {
        this.name = "FoodDeliveryProducer";
        this.listTopics = new ArrayList<>();
        this.roundRobinCount = 0;
    }

    @Override
    public void addTopic(Topic topic) {
        this.listTopics.add(topic);
    }

    @Override
    public void removeTopic(Topic topic) {
        this.listTopics.remove(topic);
    }

    /**
     * Utiliza o roundRobinCount para alternar entre os topicos listados
     */
    @Override
    public void sendMessage(String message) {
        if(this.roundRobinCount == this.listTopics.size()) this.roundRobinCount = 0;
        Message msg = new Message(this, message);
        msg.setId(UUID.randomUUID().toString());
        this.listTopics.get(roundRobinCount).addMessage(msg);
    }

    @Override
    public String name() {
        return this.name;
    }
}
