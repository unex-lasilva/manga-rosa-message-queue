package br.com.mangarosa;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.mangarosa.messages.MessageBroker;
import br.com.mangarosa.messages.impl.Repository;
import br.com.mangarosa.messages.impl.consumers.FastDeliveryItemsConsumer;
import br.com.mangarosa.messages.impl.consumers.LongDistanceItemsConsumer;
import br.com.mangarosa.messages.impl.producers.*;
import br.com.mangarosa.messages.impl.topics.*;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MessageRepository repository = new Repository();
        MessageBroker broker = new MessageBroker(repository);

        Producer foodDeliveryProducer = new FoodDeliveryProducer(broker, repository);
        Producer fastDeliveryProducer = new FastDeliveryProducer(broker, repository);
        Producer pyMarketPlaceProducer = new PyMarketPlaceProducer(broker, repository);
        Producer physicPersonDeliveryProducer = new PhysicPersonDeliveryProducer(broker, repository);

        Topic fastDeliveryItems = new FastDeliveryItems(repository);
        Topic longDistanceItems = new LongDistanceItems(repository);

        foodDeliveryProducer.addTopic(fastDeliveryItems);
        physicPersonDeliveryProducer.addTopic(fastDeliveryItems);

        pyMarketPlaceProducer.addTopic(longDistanceItems);
        fastDeliveryProducer.addTopic(longDistanceItems);

        Consumer fastDeliveryItemsConsumer = new FastDeliveryItemsConsumer(fastDeliveryItems, repository);
        Consumer longDistanceItemsConsumer = new LongDistanceItemsConsumer(longDistanceItems, repository);

        fastDeliveryItems.subscribe(fastDeliveryItemsConsumer);
        broker.subscribe(fastDeliveryItems.name(), fastDeliveryItemsConsumer);
        
        fastDeliveryItems.subscribe(longDistanceItemsConsumer);
        broker.subscribe(fastDeliveryItems.name(), longDistanceItemsConsumer);

        foodDeliveryProducer.sendMessage("Menssagem 1");

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable tarefa = new Runnable() {
            public void run() {
                System.out.println(
                        repository.getAllNotConsumedMessagesByTopic(fastDeliveryItems.name()).get(0).isExperied());
            }
        };

        scheduler.scheduleAtFixedRate(tarefa, 0, 30, TimeUnit.SECONDS);

    }
}