package br.com.mangarosa.producers;

import br.com.mangarosa.repositories.MessageRepository;
import br.com.mangarosa.messages.Message;

public class FoodDeliveryProducer {
    private MessageRepository repository;

    public FoodDeliveryProducer(MessageRepository repository) {
        this.repository = repository;
    }

    public void produce(String queue, String messageContent, int messageNumber) {
        Message message = new Message(messageContent);
        System.out.println("Produzido: " + messageNumber + " - " + message.getContent());
        repository.saveMessage(queue, message);
    }
}
