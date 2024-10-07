package br.com.mangarosa;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import br.com.mangarosa.messages.MessageBroker;
import br.com.mangarosa.messages.impl.Repository;
import br.com.mangarosa.messages.impl.consumers.FastDeliveryItemsConsumer;
import br.com.mangarosa.messages.impl.producers.FoodDeliveryProducer;
import br.com.mangarosa.messages.impl.topics.FastDeliveryItems;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MessageRepository repository = new Repository();
        MessageBroker broker = new MessageBroker(repository);

        Producer foodDeliveryProducer = new FoodDeliveryProducer(broker, repository);

        Topic fastDeliveryItems = new FastDeliveryItems(repository);

        foodDeliveryProducer.addTopic(fastDeliveryItems);

        Consumer fastDeliveryItemsConsumer = new FastDeliveryItemsConsumer();

        fastDeliveryItems.subscribe(new FastDeliveryItemsConsumer());
        broker.subscribe(fastDeliveryItems.name(), fastDeliveryItemsConsumer);

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