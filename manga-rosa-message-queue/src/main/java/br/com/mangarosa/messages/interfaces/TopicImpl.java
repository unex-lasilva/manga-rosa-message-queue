package br.com.mangarosa.messages.interfaces;

import br.com.mangarosa.messages.Message;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Implementação da interface Topic.
 */
public class TopicImpl implements Topic {
    private final String name; // Nome do tópico
    private final List<Consumer> consumers; // Lista de consumidores inscritos
    private final MsgRepositoryImpl messageRepository; // Repositório de mensagens

    /**
     * Construtor que inicializa um tópico com um nome e um repositório de mensagens.
     *
     * @param name              o nome do tópico
     * @param messageRepository o repositório de mensagens associado
     */
    public TopicImpl(String name, MsgRepositoryImpl messageRepository) {
        this.name = Objects.requireNonNull(name, "Topic name can't be null");
        this.messageRepository = Objects.requireNonNull(messageRepository, "The topic must have a repository");
        this.consumers = new ArrayList<>();
    }

    /**
     * Retorna o nome do tópico.
     *
     * @return o nome do tópico
     */
    @Override
    public String name() {
        return this.name;
    }

    /**
     * Adiciona uma mensagem ao tópico e notifica os consumidores.
     *
     * @param message a mensagem a ser adicionada
     * @throws IllegalArgumentException se a mensagem for nula
     */
    @Override
    public void addMessage(Message message) {
        if (message.getMessage() == null) {
            throw new IllegalArgumentException("Message can't be null");
        }
        this.messageRepository.append(this.name, message);
        this.notifyConsumers(message);
    }

    /**
     * Inscreve um consumidor no tópico.
     *
     * @param consumer o consumidor a ser inscrito
     * @throws IllegalArgumentException se o consumidor já estiver inscrito
     */
    @Override
    public void subscribe(Consumer consumer) {
        if (consumers.contains(consumer)) {
            throw new IllegalArgumentException("The Consumer:" + consumer.name() + " is already in the topic");
        }
        consumers.add(consumer);
    }

    /**
     * Remove a inscrição de um consumidor do tópico.
     *
     * @param consumer o consumidor a ser removido
     * @throws IllegalArgumentException se o consumidor não estiver inscrito
     */
    @Override
    public void unsubscribe(Consumer consumer) {
        if (consumers.contains(consumer)) {
            consumers.remove(consumer);
        } else {
            throw new IllegalArgumentException("The Consumer:" + consumer.name() + " isn't in the topic");
        }
    }

    /**
     * Retorna a lista de consumidores inscritos no tópico.
     *
     * @return a lista de consumidores inscritos
     */
    @Override
    public List<Consumer> consumers() {
        return this.consumers;
    }

    /**
     * Retorna o repositório de mensagens associado ao tópico.
     *
     * @return o repositório de mensagens
     */
    @Override
    public MessageRepository getRepository() {
        return this.messageRepository;
    }
}

