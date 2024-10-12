package br.com.mangarosa.messages.impl.topic;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um tópico específico chamado "LongDistanceItemsTopic",
 * que trata de mensagens relacionadas a itens de longa distância.
 * Esta classe implementa a interface Topic e permite o gerenciamento de mensagens e consumidores.
 */

public class LongDistanceItemsTopic implements Topic {
    // Nome do tópico relacionado a itens de longa distância
    private final String topicName = "queue/long-distance-items";

    // Lista de consumidores que estão inscritos para receber notificações de novas mensagens
    private final List<Consumer> consumers = new ArrayList<>();

    // Repositório de mensagens que armazena todas as mensagens do tópico
    private final MessageRepository repository;

    // Fila de mensagens internas usando a implementação LinkedQueue
    private final LinkedQueue<Message> messageQueue;


    /**
     * Construtor que inicializa o tópico com o repositório de mensagens fornecido.
     *
     * @param repository Repositório de mensagens para o tópico.
     */
    public LongDistanceItemsTopic(MessageRepository repository) {
        this.repository = repository;
        this.messageQueue = new LinkedQueue<>();
    }

    /**
     * Retorna o nome do tópico.
     *
     * @return String com o nome do tópico.
     */

    @Override
    public String name() {
        return topicName;
    }

    /**
     * Adiciona uma nova mensagem ao tópico, tanto no repositório quanto na fila interna,
     * e notifica todos os consumidores inscritos.
     *
     * @param message A mensagem a ser adicionada ao tópico.
     */
    @Override
    public void addMessage(Message message) {
        repository.append(topicName, message);
        messageQueue.enqueue(message);
        notifyConsumers(message);
    }


    /**
     * Inscreve um novo consumidor no tópico. Se o consumidor já estiver inscrito, não faz nada.
     *
     * @param consumer O consumidor a ser inscrito.
     */
    @Override
    public void subscribe(Consumer consumer) {
        if (!consumers.contains(consumer)) {
            consumers.add(consumer);
        }
    }

    /**
     * Remove a inscrição de um consumidor no tópico.
     *
     * @param consumer O consumidor a ser removido da lista de inscritos.
     */
    @Override
    public void unsubscribe(Consumer consumer) {
        consumers.remove(consumer);
    }

    /**
     * Retorna a lista de todos os consumidores inscritos no tópico.
     *
     * @return Lista de consumidores inscritos.
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
