package br.com.mangarosa;

import br.com.mangarosa.messages.impl.Consumers.*;
import br.com.mangarosa.messages.impl.Producers.*;
import br.com.mangarosa.messages.impl.Topicos.*;


public class Main {

    public static void main(String[] args) {
        // Criação de tópicos
        FastDeliveryItemsTopic fastDeliveryItemsTopic = new FastDeliveryItemsTopic();
        LongDistanceItemsTopic longDistanceItemsTopic = new LongDistanceItemsTopic();

        // Criação de produtores
        FastDeliveryProducer fastDeliveryProducer = new FastDeliveryProducer();
        FoodDeliveryProducer foodDeliveryProducer = new FoodDeliveryProducer();
        PhysicPersonDeliveryProducer physicPersonDeliveryProducer = new PhysicPersonDeliveryProducer();
        PyMarketPlaceProducer pyMarketPlaceProducer = new PyMarketPlaceProducer();

        // Adição de tópicos aos produtores
        fastDeliveryProducer.addTopic(longDistanceItemsTopic);
        foodDeliveryProducer.addTopic(fastDeliveryItemsTopic);
        physicPersonDeliveryProducer.addTopic(fastDeliveryItemsTopic);
        pyMarketPlaceProducer.addTopic(longDistanceItemsTopic);

        // Criação de consumidores
        FastDeliveryConsumer fastDeliveryConsumer = new FastDeliveryConsumer();
        LongDistanceConsumer longDistanceConsumer = new LongDistanceConsumer();

        // Adição de consumidores aos tópicos
        fastDeliveryItemsTopic.subscribe(fastDeliveryConsumer);
        longDistanceItemsTopic.subscribe(longDistanceConsumer);

        // Envio de mensagens
        fastDeliveryProducer.sendMessage("Mensagem de entrega rápida");
        foodDeliveryProducer.sendMessage("Mensagem de entrega de comida");
        physicPersonDeliveryProducer.sendMessage("Mensagem de entrega de pessoa física");
        pyMarketPlaceProducer.sendMessage("Mensagem de entrega de marketplace");

        // Consumo de mensagens
        System.out.println("Mensagens não consumidas do tópico de entrega rápida:");
        fastDeliveryItemsTopic.getRepository().getAllNotConsumedMessagesByTopic(fastDeliveryItemsTopic.name())
                .forEach(message -> System.out.println(message.getMessage()));

        System.out.println("Mensagens não consumidas do tópico de entrega de longa distância:");
        longDistanceItemsTopic.getRepository().getAllNotConsumedMessagesByTopic(longDistanceItemsTopic.name())
                .forEach(message -> System.out.println(message.getMessage()));

        // Consumo de mensagens
        fastDeliveryItemsTopic.getRepository().getAllNotConsumedMessagesByTopic(fastDeliveryItemsTopic.name())
                .forEach(message -> fastDeliveryConsumer.consume(message));

        longDistanceItemsTopic.getRepository().getAllNotConsumedMessagesByTopic(longDistanceItemsTopic.name())
                .forEach(message -> longDistanceConsumer.consume(message));

        // Verificação de mensagens consumidas
        System.out.println("Mensagens consumidas do tópico de entrega rápida:");
        fastDeliveryItemsTopic.getRepository().getAllConsumedMessagesByTopic(fastDeliveryItemsTopic.name())
                .forEach(message -> System.out.println(message.getMessage()));

        System.out.println("Mensagens consumidas do tópico de entrega de longa distância:");
        longDistanceItemsTopic.getRepository().getAllConsumedMessagesByTopic(longDistanceItemsTopic.name())
                .forEach(message -> System.out.println(message.getMessage()));
    }
}

