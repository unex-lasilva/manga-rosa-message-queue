package br.com.mangarosa.messages.producers;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Producer;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PhysicPersonDeliveryProducer implements Producer {
    private final String name = "FoodDeliveryProducer";
    private final List<Topic> topics = new ArrayList<>();

    @Override
    public void addTopic(Topic topic) {
        if (!topics.contains(topic)) {
            topics.add(topic);
        }
    }

    @Override
    public void removeTopic(Topic topic) {
        topics.remove(topic);
    }

    @Override
    public void sendMessage(String messageContent) {
        Message message = new Message(this, messageContent);
        for (Topic topic : topics) {
            topic.addMessage(message);
            System.out.println("Message sent to topic: " + topic.name());
        }
    }

    @Override
    public String name() {
        return name;
    }
}