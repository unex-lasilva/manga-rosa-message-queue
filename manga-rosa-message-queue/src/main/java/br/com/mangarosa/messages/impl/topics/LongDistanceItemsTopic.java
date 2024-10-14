package br.com.mangarosa.messages.impl.topics;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe LongDistanceItemsTopic representa um tópico específico para itens de longa distância,
 */
public class LongDistanceItemsTopic implements Topic {

    // Repositório de mensagens para armazenar mensagens do tópico
    private final MessageRepository messageRepository;

    // Lista de consumidores inscritos no tópico
    private final List<Consumer> consumers;

    /**
     * Construtor da classe LongDistanceItemsTopic.
     * Inicializa o tópico com um repositório de mensagens e uma lista vazia de consumidores.
     *
     * @param messageRepository o repositório de mensagens onde as mensagens serão armazenadas
     */
    public LongDistanceItemsTopic(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.consumers = new ArrayList<>();
    }

    /**
     * Retorna o nome do tópico.
     *
     * @return o nome do tópico
     */
    @Override
    public String name() {
        return "queue/long-distance-items";
    }

    /**
     * Adiciona uma nova mensagem ao repositório de mensagens associado a este tópico.
     *
     * @param message a mensagem a ser adicionada ao repositório
     */
    @Override
    public void addMessage(Message message) {
        this.messageRepository.append(name(), message);
    }

    /**
     * Inscreve um consumidor para receber mensagens associadas a este tópico.
     *
     * @param consumer o consumidor a ser inscrito no tópico
     */
    @Override
    public void subscribe(Consumer consumer) {
        this.consumers.add(consumer);
    }

    /**
     * Remove um consumidor da lista de inscritos deste tópico.
     *
     * @param consumer o consumidor a ser removido do tópico
     */
    @Override
    public void unsubscribe(Consumer consumer) {
        this.consumers.remove(consumer);
    }

    /**
     * Retorna a lista de consumidores inscritos neste tópico.
     *
     * @return a lista de consumidores do tópico
     */
    @Override
    public List<Consumer> consumers() {
        return this.consumers;
    }

    /**
     * Retorna o repositório de mensagens associado a este tópico.
     *
     * @return o repositório de mensagens
     */
    @Override
    public MessageRepository getRepository() {
        return this.messageRepository;
    }
}
