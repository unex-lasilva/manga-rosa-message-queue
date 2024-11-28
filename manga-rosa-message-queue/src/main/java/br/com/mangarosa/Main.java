package br.com.mangarosa;

import br.com.mangarosa.messages.MessageBroker;
import br.com.mangarosa.messages.consumers.FastDeliveryItemsConsumer;
import br.com.mangarosa.messages.consumers.LongDistanceItemsConsumer;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Producer;
import br.com.mangarosa.messages.interfaces.Topic;
import br.com.mangarosa.messages.producers.FastDeliveryProducer;
import br.com.mangarosa.messages.producers.FoodDeliveryProducer;
import br.com.mangarosa.messages.producers.PhysicPersonDeliveryProducer;
import br.com.mangarosa.messages.producers.PyMarketPlaceProducer;
import br.com.mangarosa.messages.repositories.TopicsRepository;
import br.com.mangarosa.messages.topics.FastDeliveryItemsTopic;
import br.com.mangarosa.messages.topics.LongDistanceItemsTopic;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MessageRepository repository = new TopicsRepository();

        //creating topics
        Topic fastDeliveryTopic = new FastDeliveryItemsTopic(repository);
        Topic longDistanceTopic = new LongDistanceItemsTopic(repository);

        //creating producers and adding topics (long-distance)
        Producer fastDeliveryProducer = new FastDeliveryProducer();
        fastDeliveryProducer.addTopic(longDistanceTopic);

        Producer pyMarketProducer = new PyMarketPlaceProducer();
        pyMarketProducer.addTopic(longDistanceTopic);

        //creating producers and adding topics (fast-delivery)
        Producer foodDeliveryProducer = new FoodDeliveryProducer();
        foodDeliveryProducer.addTopic(fastDeliveryTopic);

        Producer physicPersonProducer = new PhysicPersonDeliveryProducer();
        physicPersonProducer.addTopic(fastDeliveryTopic);

        //creating consumers
        Consumer fastDeliveryConsumer = new FastDeliveryItemsConsumer();
        Consumer longDistanceConsumer = new LongDistanceItemsConsumer();

        //creating message broker and message consumption
        MessageBroker broker = new MessageBroker(repository);

        //adding topics to broker
        broker.createTopic(fastDeliveryTopic);
        broker.createTopic(longDistanceTopic);

        //adding consumers to topics in the broker
        broker.subscribe(fastDeliveryTopic.name(), fastDeliveryConsumer);
        broker.subscribe(longDistanceTopic.name(), longDistanceConsumer);

        //call broker to notify the users
        broker.notifyConsumers();

        //sending messages in producers
        fastDeliveryProducer.sendMessage("Long distance (fast producer)");
        pyMarketProducer.sendMessage("Long distance (py producer)");
        foodDeliveryProducer.sendMessage("Fast Delivery (food producer)");
        physicPersonProducer.sendMessage("Fast Delivery (physic producer)");
    }
}