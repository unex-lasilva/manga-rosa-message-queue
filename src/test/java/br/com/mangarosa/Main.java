package br.com.mangarosa;

import br.com.mangarosa.consumers.FoodDeliveryConsumer;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.producers.FoodDeliveryProducer;
import br.com.mangarosa.repositories.InMemoryMessageRepository;
import br.com.mangarosa.messages.interfaces.SimpleMessageBroker;

public class Main {
    public static void main(String[] args) {
        System.out.println("Manga Rosa");

        // Criação do repositório de mensagens
        InMemoryMessageRepository messageRepository = new InMemoryMessageRepository();

        // Criação do MessageBroker
        SimpleMessageBroker messageBroker = new SimpleMessageBroker(messageRepository);

        // Criação do produtor e consumidor
        FoodDeliveryProducer producer = new FoodDeliveryProducer(messageRepository);
        FoodDeliveryConsumer consumer = new FoodDeliveryConsumer(messageRepository);

        // Produzir algumas mensagens
        produceMessages(producer, messageBroker, 5); // Produz 5 mensagens

        // Consumir as mensagens produzidas
        consumeMessages(consumer, messageBroker, 5); // Tenta consumir 5 mensagens
    }

    private static void produceMessages(FoodDeliveryProducer producer, SimpleMessageBroker messageBroker, int count) {
        for (int i = 1; i <= count; i++) {
            String messageContent = "Entrega de Resultados laboratoriais"; // Mensagem que será enviada
            producer.produce("foodDeliveryQueue", messageContent, i); // Produz a mensagem
            messageBroker.publishMessage("foodDeliveryQueue", new Message(messageContent)); // Publica a mensagem
        }
    }

    private static void consumeMessages(FoodDeliveryConsumer consumer, SimpleMessageBroker messageBroker, int maxConsumeCount) {
        for (int i = 1; i <= maxConsumeCount; i++) {
            Message message = messageBroker.consumeMessage("foodDeliveryQueue"); // Consome a mensagem da fila
            if (message != null) {
                consumer.consume(message); // Chama o método com um único parâmetro
            } else {
                System.out.println("Todos os itens foram entregues/consumidos e a fila ficou vazia.");
                break;
            }
        }
    }
}
