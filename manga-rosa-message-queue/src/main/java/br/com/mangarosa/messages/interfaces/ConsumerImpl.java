package br.com.mangarosa.messages.interfaces;

import br.com.mangarosa.messages.Message;
import java.util.Objects;
import java.util.UUID;

/**
 * Implementação da interface Consumer.
 */
public class ConsumerImpl implements Consumer {
    private final String name; // Nome do consumidor
    private final String topicName; // Nome do tópico associado
    private final MsgRepositoryImpl messageRepository; // Repositório de mensagens associado

    /**
     * Construtor que inicializa um consumidor com um nome, um tópico e um repositório de mensagens.
     * @param name o nome do consumidor
     * @param topicName o nome do tópico
     * @param messageRepository o repositório de mensagens associado
     */
    public ConsumerImpl(String name, String topicName, MsgRepositoryImpl messageRepository) {
        this.name = Objects.requireNonNull(name, "Consumer name can't be null");
        this.topicName = Objects.requireNonNull(topicName, "The topic name can't be null");
        this.messageRepository = Objects.requireNonNull(messageRepository, "The consumer must have a repository");
    }

    /**
     * Consome uma mensagem.
     * @param message a mensagem a ser consumida
     * @return true se a mensagem foi consumida com sucesso, false caso contrário
     * @throws IllegalArgumentException se a mensagem for nula ou expirada
     */
    @Override
    public boolean consume(Message message) {
        if (message == null) throw new IllegalArgumentException("Message can't be null");

        System.out.println("Consumer " + name + " is processing: " + message.getId());
        message.addConsumption(this);

        try {
            if (message.isExperied()) {
                throw new IllegalArgumentException("Message: " + message.toString() +
                        "\nExpired at: " + message.getCreatedAt().plusMinutes(5));
            }
            this.messageRepository.consumeMessage(this.topicName, UUID.fromString(message.getId()));
            this.consumeMessageOnConsole(message);
            return true;
        }
        catch (Exception e) {
            System.err.println("Error on processing message: " + e.getMessage());
            return false;
        }
    }

    /**
     * Retorna o nome do consumidor.
     * @return o nome do consumidor
     */
    @Override
    public String name() {
        return name;
    }

    /**
     * Exibe informações da mensagem no console.
     * @param message a mensagem a ser exibida
     */
    private void consumeMessageOnConsole(Message message) {
        System.out.println("======== CONSUMING MESSAGE BY " + name + " ==========");
        System.out.println("ID: " + message.getId());
        System.out.println("Message: " + message.getMessage());
        System.out.println("Producer: " + message.getProducer().name());
        System.out.println("CreatedAt: " + message.getCreatedAt());
        System.out.println("Consumed: " + message.isConsumed());
        System.out.println("Expired: " + message.isExperied());
        System.out.println("===============================================================");
    }
}
