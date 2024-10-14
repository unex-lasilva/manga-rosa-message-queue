package br.com.mangarosa;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.MessageBroker;
import br.com.mangarosa.messages.interfaces.*;
import br.com.mangarosa.messages.interfaces.MsgRepositoryImpl;
import br.com.mangarosa.messages.interfaces.consumersImpl.FastDeliveryItemsConsumer;
import br.com.mangarosa.messages.interfaces.consumersImpl.LongDistanceItemsConsumer;
import br.com.mangarosa.messages.interfaces.producersImpl.FastDeliveryProducer;
import br.com.mangarosa.messages.interfaces.producersImpl.FoodDeliveryProducer;
import br.com.mangarosa.messages.interfaces.producersImpl.PhysicPersonDeliveryProducer;
import br.com.mangarosa.messages.interfaces.producersImpl.PyMarketPlaceProducer;
import br.com.mangarosa.messages.interfaces.topicImpl.FastDeliveryItems;
import br.com.mangarosa.messages.interfaces.topicImpl.LongDistanceItems;

import java.util.List;

public class Main {
    public static MessageRepository repository;
    public static MessageBroker messageBroker;
    public static Topic fastDeliveryItems;
    public static Topic longDistanceItems;
    public static Producer foodDeliveryProducer;
    public static Producer physicPersonDeliveryProducer;
    public static Producer pyMarketPlaceProducer;
    public static Producer fastDeliveryProducer;
    public static Consumer fastDeliveryItemsConsumer;
    public static Consumer longDistanceItemsConsumer;

    public static void main(String[] args) throws InterruptedException {
        repository = new MsgRepositoryImpl();
        messageBroker = new MessageBroker(repository);

        // Criar e registrar tópicos no MessageBroker
        fastDeliveryItems = new FastDeliveryItems((MsgRepositoryImpl) repository);
        longDistanceItems = new LongDistanceItems((MsgRepositoryImpl) repository);

        // Adicionar tópicos ao MessageBroker
        messageBroker.createTopic(fastDeliveryItems);
        messageBroker.createTopic(longDistanceItems);

        // Criar produtores
        foodDeliveryProducer = new FoodDeliveryProducer(messageBroker);
        physicPersonDeliveryProducer = new PhysicPersonDeliveryProducer(messageBroker);
        pyMarketPlaceProducer = new PyMarketPlaceProducer(messageBroker);
        fastDeliveryProducer = new FastDeliveryProducer(messageBroker);

        // Criar consumidores
        fastDeliveryItemsConsumer = new FastDeliveryItemsConsumer((MsgRepositoryImpl) repository);
        longDistanceItemsConsumer = new LongDistanceItemsConsumer((MsgRepositoryImpl) repository);

        // Subscrever consumidores aos tópicos
        messageBroker.subscribe(fastDeliveryItems.name(), fastDeliveryItemsConsumer);
        messageBroker.subscribe(longDistanceItems.name(), longDistanceItemsConsumer);

        // Produzir mensagens de exemplo
        produceMessages();

        // Verificar mensagens armazenadas
        verifyStoredMessages();
    }

    // Método para produzir mensagens de exemplo
    private static void produceMessages() {
        Message msg1 = new Message(foodDeliveryProducer, "Order #1001: Deliver food to customer A");
        Message msg2 = new Message(physicPersonDeliveryProducer, "Order #1002: Deliver documents to client B");
        Message msg3 = new Message(pyMarketPlaceProducer, "Order #1003: Deliver electronics to store C");
        Message msg4 = new Message(fastDeliveryProducer, "Order #1004: Deliver package to warehouse D");

        // Enviar mensagens para os produtores
        foodDeliveryProducer.sendMessage(msg1.toString());
        physicPersonDeliveryProducer.sendMessage(msg2.toString());
        pyMarketPlaceProducer.sendMessage(msg3.toString());
        fastDeliveryProducer.sendMessage(msg4.toString());

        // Enviar mensagens para o repositório
        repository.append(fastDeliveryItems.name(), msg1);
        repository.append(fastDeliveryItems.name(), msg2);
        repository.append(longDistanceItems.name(), msg3);
        repository.append(longDistanceItems.name(), msg4);

        System.out.println("Mensagens de exemplo produzidas e armazenadas.");
    }

    // Método para verificar as mensagens armazenadas
    private static void verifyStoredMessages() {
        List<Message> fastDeliveryMessages = repository.getAllNotConsumedMessagesByTopic(fastDeliveryItems.name());
        List<Message> longDistanceMessages = repository.getAllNotConsumedMessagesByTopic(longDistanceItems.name());

        System.out.println("Mensagens armazenadas no tópico 'fast-delivery-items':");
        for (Message message : fastDeliveryMessages) {
            System.out.println(formatMessage(message));
        }

        System.out.println("Mensagens armazenadas no tópico 'long-distance-items':");
        for (Message message : longDistanceMessages) {
            System.out.println(formatMessage(message));
        }
    }

    // Método para formatar a mensagem de forma legível
    private static String formatMessage(Message message) {
        return String.format("ID: %s | Producer: %s | Created At: %s | Message: %s | Consumed: %s",
                message.getId(),
                message.getProducer().name(),
                message.getCreatedAt(),
                message.getMessage(),
                message.isConsumed()
        );
    }
}
