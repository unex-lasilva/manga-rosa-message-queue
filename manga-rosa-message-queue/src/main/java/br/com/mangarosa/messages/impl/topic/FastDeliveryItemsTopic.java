package br.com.mangarosa.messages.impl.topic;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe que representa um tópico específico chamado "FastDeliveryItemsTopic",
 * utilizado para gerenciar mensagens relacionadas a itens de entrega rápida.
 * Implementa a interface Topic para fornecer funcionalidade de adicionar mensagens e gerenciar consumidores.
 */

public class FastDeliveryItemsTopic implements Topic {
    // Nome do tópico relacionado a itens de entrega rápida
    private final String topicName = "queue/fast-delivery-items";

    // Lista de consumidores inscritos para receber mensagens deste tópico
    private final List<Consumer> consumers = new ArrayList<>();

    // Repositório de mensagens, utilizado para armazenar e recuperar mensagens do tópico
    private final MessageRepository repository;


    /**
     * Construtor que inicializa o tópico com o repositório de mensagens fornecido.
     *
     * @param repository Repositório de mensagens para armazenar as mensagens do tópico.
     */
    public FastDeliveryItemsTopic(MessageRepository repository) {
        this.repository = repository;
    }

    /**
     * Retorna o nome do tópico.
     *
     * @return O nome do tópico como uma string.
     */
    @Override
    public String name() {
        return topicName;
    }

    /**
     * Adiciona uma nova mensagem ao tópico, tanto no repositório quanto notificando os consumidores inscritos.
     *
     * @param message A mensagem que será adicionada ao tópico.
     */
    @Override
    public void addMessage(Message message) {

        repository.append(topicName, message);
        notifyConsumers(message);
    }


    /**
     * Inscreve um novo consumidor ao tópico. Se o consumidor já estiver inscrito, ele não será adicionado novamente.
     *
     * @param consumer O consumidor que será inscrito no tópico.
     */
    @Override
    public void subscribe(Consumer consumer) {
        if (!consumers.contains(consumer)) {
            consumers.add(consumer);
        }
    }

    /**
     * Remove a inscrição de um consumidor do tópico.
     *
     * @param consumer O consumidor que será removido da lista de inscritos.
     */
    @Override
    public void unsubscribe(Consumer consumer) {
        consumers.remove(consumer);
    }

    /**
     * Retorna a lista de todos os consumidores inscritos no tópico.
     *
     * @return A lista de consumidores inscritos.
     */
    @Override
    public List<Consumer> consumers() {
        return consumers;
    }

    /**
     * Retorna o repositório de mensagens associado a este tópico.
     *
     * @return O repositório de mensagens.
     */
    @Override
    public MessageRepository getRepository() {
        return repository;
    }

}
