package br.com.mangarosa.messages.consumers;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;

import java.util.Objects;
import java.util.UUID;

public class ConsumerBrokerImpl implements Consumer {

    private final String name;
    private final String topicName;
    private final MessageRepository messageRepository;

    public ConsumerBrokerImpl(String name, String topicName, MessageRepository messageRepository) {
        this.name = Objects.requireNonNull(name, "Consumer name can`t be null");
        this.topicName = Objects.requireNonNull(topicName, "The topic name can`t be null");
        this.messageRepository = Objects.requireNonNull(messageRepository, "The consumer must have a repository");
    }

    @Override
    public boolean consume(Message message) {
        // Se a mensagem for nula lança uma exceção
        if (message == null) throw new IllegalArgumentException("Message can`t be null");

        System.out.println("Consumer " + name + " is processing: " + message.getId());
        message.addConsumption(this);

        //Verifica se a mensagem expirou, se expirou lança uma exceção
        try {
            if (message.isExperied()) {
                throw new IllegalArgumentException("Message: " + message.toString() +
                        "\nExpired in: " + message.getCreatedAt().plusMinutes(5));
            }
            // Marcar a mensagem como consumida
            this.messageRepository.consumeMessage(this.topicName, UUID.fromString(message.getId()));
            return true;

        } catch (Exception e) {
            System.err.println("Error on processes message: " + e.getMessage());
            return false;
        }

    }

    @Override
    public String name() {
        return name;
    }

}
