package br.com.mangarosa;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.Producer;
import br.com.mangarosa.messages.interfaces.Topic;
import br.com.mangarosa.messages.interfaces.impl2.FastDelivery.Consumers.ConsumerParaFast;
import br.com.mangarosa.messages.interfaces.impl2.FastDelivery.Producers.FoodDeliveryProducer;
import br.com.mangarosa.messages.interfaces.impl2.FastDelivery.Producers.PhysicPersonDeliveryProducer;
import br.com.mangarosa.messages.interfaces.impl2.FastDelivery.QueueFastDeliveryItems;
import br.com.mangarosa.messages.interfaces.impl2.LongDistance.Consumers.ConsumerParaLong;
import br.com.mangarosa.messages.interfaces.impl2.LongDistance.Producers.FastDeliveryProducer;
import br.com.mangarosa.messages.interfaces.impl2.LongDistance.Producers.PyMarketPlaceProducer;
import br.com.mangarosa.messages.interfaces.impl2.LongDistance.QueueLongDistanceItems;

public class Main {
    public static void main(String[] args) {

        //essa main é só pra testar se estava tudo funcionando direito

        LinkedQueue queue = new LinkedQueue();
        queue.enqueue(6);
        queue.enqueue(7);
        queue.enqueue(13);

        System.out.println("Este é o primeiro elemento: " + queue.peek());
        System.out.println("Tamanho atual da fila: " + queue.size());
        System.out.println("Elemento removido" + queue.dequeue());
        System.out.println("Este é o primeiro elemento após a remoção do anterior: " + queue.peek());
        System.out.println("Tamanho atual da fila: " + queue.size());

        System.out.println("A fila está vazia? " + queue.isEmpty());

        queue.clear();
        System.out.println("Fila após a limpa: " + queue.size());
        System.out.println("A fila está vazia após esvaziar? " + queue.isEmpty());

        Topic fastDeliveryTopic = new QueueFastDeliveryItems();
        Topic longDistanceTopic = new QueueLongDistanceItems();


        Producer fastDeliveryProducer = new FastDeliveryProducer();
        Producer foodDeliveryProducer = new FoodDeliveryProducer();
        Producer physicPersonDeliveryProducer = new PhysicPersonDeliveryProducer();
        Producer pyMarketPlaceProducer = new PyMarketPlaceProducer();


        fastDeliveryProducer.addTopic(fastDeliveryTopic);
        foodDeliveryProducer.addTopic(fastDeliveryTopic);
        physicPersonDeliveryProducer.addTopic(longDistanceTopic);
        pyMarketPlaceProducer.addTopic(longDistanceTopic);

        Consumer consumerFast = new ConsumerParaFast("Jungkook");
        Consumer consumerLongDistance = new ConsumerParaLong("Minwook");


        fastDeliveryTopic.subscribe(consumerFast);
        longDistanceTopic.subscribe(consumerLongDistance);


        fastDeliveryProducer.sendMessage("Mensagem urgente para entrega rápida");
        foodDeliveryProducer.sendMessage("Mensagem de entrega de comida");
        physicPersonDeliveryProducer.sendMessage("Mensagem de entrega de longa distância");
        pyMarketPlaceProducer.sendMessage("Mensagem de marketplace para entrega de longa distância");

        System.out.println("\nConsumindo mensagens do tópico Fast Delivery:");
        for (Consumer consumidor : fastDeliveryTopic.consumers()) {
            for (Message message : fastDeliveryTopic.getRepository().getAllNotConsumedMessagesByTopic(fastDeliveryTopic.name())) {
                consumidor.consume(message);
            }
        }

        System.out.println("\nConsumindo mensagens do tópico Long Distance:");
        for (Consumer consumidor : longDistanceTopic.consumers()) {
            for (Message message : longDistanceTopic.getRepository().getAllNotConsumedMessagesByTopic(longDistanceTopic.name())) {
                consumidor.consume(message);
            }
        }
    }
}