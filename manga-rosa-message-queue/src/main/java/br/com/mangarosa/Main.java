package br.com.mangarosa;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.impl.MessageRepositoryImpl;
import br.com.mangarosa.messages.impl.consumer.FastDeliveryConsumer;
import br.com.mangarosa.messages.impl.consumer.LongDistanceItemsConsumer;
import br.com.mangarosa.messages.impl.producer.FastDeliveryProducer;
import br.com.mangarosa.messages.impl.producer.FoodDeliveryProducer;
import br.com.mangarosa.messages.impl.producer.PhysicPersonDeliveryProducer;
import br.com.mangarosa.messages.impl.producer.PyMarketPlaceProducer;
import br.com.mangarosa.messages.impl.topic.FastDeliveryItemsTopic;
import br.com.mangarosa.messages.impl.topic.LongDistanceItemsTopic;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.LinkedList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        createFastDelivery();
        createLongDistanceDelivery();

    }

    private static void createFastDelivery() {
        Topic fastDeliveryTopic = new FastDeliveryItemsTopic(new MessageRepositoryImpl());


        FoodDeliveryProducer foodDeliveryProducer = new FoodDeliveryProducer();
        PhysicPersonDeliveryProducer physicPersonDeliveryProducer = new PhysicPersonDeliveryProducer();

        foodDeliveryProducer.addTopic(fastDeliveryTopic);
        physicPersonDeliveryProducer.addTopic(fastDeliveryTopic);


        fastDeliveryTopic.addMessage(new Message(foodDeliveryProducer, "Produtor: foodDeliveryProducer"));
        fastDeliveryTopic.addMessage(new Message(physicPersonDeliveryProducer, "Produtor: physicPersonDeliveryProducer"));



        Consumer consumer = new FastDeliveryConsumer();
        fastDeliveryTopic.subscribe(consumer);

        for (Message msn: fastDeliveryTopic.getRepository().getAllNotConsumedMessagesByTopic(fastDeliveryTopic.name())) {
            fastDeliveryTopic.notifyConsumers(msn);
        }

        try { //fazer sistema esperar o tempo para testar se foi expirado - mudar seg -> min
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var mensagensConsumidas = fastDeliveryTopic.getRepository().getAllConsumedMessagesByTopic(fastDeliveryTopic.name());
        System.out.println("Mensagens consumidas: " + mensagensConsumidas);
    }

    private static void createLongDistanceDelivery(){

        Topic longDistanceTopic = new LongDistanceItemsTopic(new MessageRepositoryImpl());

        // Fila de mensagens
        // LinkedQueue<Message> messageQueue = new LinkedQueue<>();


        PyMarketPlaceProducer pyMarketPlaceProducer = new PyMarketPlaceProducer();
        FastDeliveryProducer fastDeliveryProducer = new FastDeliveryProducer();


        pyMarketPlaceProducer.addTopic(longDistanceTopic);
        fastDeliveryProducer.addTopic(longDistanceTopic);

        longDistanceTopic.addMessage(new Message(fastDeliveryProducer, "Produtor: pyMarketPlaceProducer"));
        longDistanceTopic.addMessage(new Message(pyMarketPlaceProducer, "Produtor: fastDeliveryProducer"));


        Consumer longDistanceConsumer = new LongDistanceItemsConsumer();
        longDistanceTopic.subscribe(longDistanceConsumer);

        for(Message msn : longDistanceTopic.getRepository().getAllNotConsumedMessagesByTopic(longDistanceTopic.name())){
            longDistanceTopic.notifyConsumers(msn);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        var mensagensConsumidas = longDistanceTopic.getRepository().getAllConsumedMessagesByTopic(longDistanceTopic.name());
        System.out.println("Mensagens consumidas (long distance): " + mensagensConsumidas);

    }
}