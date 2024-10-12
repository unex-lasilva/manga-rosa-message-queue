package br.com.mangarosa.messages.interfaces;

import br.com.mangarosa.messages.MessageBroker;
import br.com.mangarosa.messages.MsgBuild;
import java.util.Objects;

/**
 * Implementação da interface Producer.
 */
public class ProducerImpl implements Producer {
    private final String name; // Nome do produtor
    private final MessageBroker messageBroker; // Broker de mensagens associado
    private final String topicName; // Nome do tópico associado

    /**
     * Construtor que inicializa um produtor com um nome, um broker de mensagens e um tópico.
     * @param name o nome do produtor
     * @param messageBroker o broker de mensagens associado
     * @param topicName o nome do tópico associado
     */
    public ProducerImpl(String name, MessageBroker messageBroker, String topicName) {
        this.name = Objects.requireNonNull(name, "Topic name can't be null");
        this.messageBroker = Objects.requireNonNull(messageBroker, "The producer must have a message broker");
        this.topicName = Objects.requireNonNull(topicName, "Topic name can't be null");
    }

    /**
     * Adiciona um novo tópico ao broker de mensagens.
     * @param topic o tópico a ser adicionado
     */
    @Override
    public void addTopic(Topic topic) {
        this.messageBroker.createTopic(topic);
    }

    /**
     * Remove um tópico do broker de mensagens.
     * @param topic o tópico a ser removido
     */
    @Override
    public void removeTopic(Topic topic) {
        this.messageBroker.removeTopic(topic.name());
    }

    /**
     * Envia uma mensagem para o tópico associado.
     * @param message a mensagem a ser enviada
     */
    @Override
    public void sendMessage(String message) {
        this.messageBroker.getTopicByName(this.topicName).addMessage(MsgBuild.build(this, message));
    }

    /**
     * Retorna o nome do produtor.
     * @return o nome do produtor
     */
    @Override
    public String name() {
        return this.name;
    }
}
