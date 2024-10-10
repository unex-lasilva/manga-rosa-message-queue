package br.com.mangarosa;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.MessageBroker;
import br.com.mangarosa.messages.impl.MessageConsumer;
import br.com.mangarosa.messages.impl.MessageProducer;
import br.com.mangarosa.messages.impl.Repository;
import br.com.mangarosa.messages.impl.topics.*;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MessageRepository repository = new Repository();
        MessageBroker broker = new MessageBroker(repository);

        Topic fastDeliveryItems = new FastDeliveryItems(repository);
        Topic longDistanceItems = new LongDistanceItems(repository);

        Producer foodDeliveryProducer = new MessageProducer("FoodDeliveryProducer", broker, repository,
                fastDeliveryItems);
        Producer fastDeliveryProducer = new MessageProducer("FastDeliveryProducer", broker, repository,
                fastDeliveryItems);
        Producer pyMarketPlaceProducer = new MessageProducer("PyMarketPlaceProducer", broker, repository,
                longDistanceItems);
        Producer physicPersonDeliveryProducer = new MessageProducer("PhysicPersonDeliveryProducer", broker, repository,
                longDistanceItems);

        foodDeliveryProducer.addTopic(fastDeliveryItems);
        pyMarketPlaceProducer.addTopic(longDistanceItems);

        Consumer fastDeliveryItemsConsumer = new MessageConsumer("FastDeliveryItemsConsumer", fastDeliveryItems,
                repository);
        Consumer longDistanceItemsConsumer = new MessageConsumer("LongDistanceItemsConsumer", longDistanceItems,
                repository);

        broker.subscribe(fastDeliveryItems.name(), fastDeliveryItemsConsumer);
        broker.subscribe(longDistanceItems.name(), longDistanceItemsConsumer);

        // foodDeliveryProducer.sendMessage("Menssagem 1");
        fastDeliveryProducer.sendMessage("Menssagem 1");
        fastDeliveryProducer.sendMessage("Menssagem 2");
        fastDeliveryProducer.sendMessage("Menssagem 3");
        // pyMarketPlaceProducer.sendMessage("Menssagem 3");
        // physicPersonDeliveryProducer.sendMessage("Menssagem 4");

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable tarefa = new Runnable() {
            public void run() {
                int i = 0;
                List<Message> list = repository.getAllNotConsumedMessagesByTopic(fastDeliveryItems.name());
                System.out.println("Message not consumed: \n");
                while (i < list.size()) {
                    System.out.println(
                            "Message: " + list.get(i).getMessage() + " IsExperied: " + list.get(i).isExperied());
                    i++;
                }

            };

        };
        scheduler.scheduleAtFixedRate(tarefa, 0, 15, TimeUnit.SECONDS);
    }
}