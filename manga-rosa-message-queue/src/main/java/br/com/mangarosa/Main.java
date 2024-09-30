package br.com.mangarosa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.datastructures.interfaces.impl.QueueNode;
import br.com.mangarosa.messages.MessageBroker;
import br.com.mangarosa.messages.impl.FastDeliveryItems;
import br.com.mangarosa.messages.impl.FastDeliveryItemsConsumer;
import br.com.mangarosa.messages.impl.FoodDeliveryProducer;
import br.com.mangarosa.messages.impl.Repository;
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

        fastDeliveryItems.subscribe(fastDeliveryItemsConsumer);

        broker.subscribe(fastDeliveryItems.name(), fastDeliveryItemsConsumer);

        foodDeliveryProducer.sendMessage("Menssagem 1");
        
    }
}