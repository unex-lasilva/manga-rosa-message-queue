package br.com.mangarosa.messages.interfaces.impl.topics;

import br.com.mangarosa.datastructures.interfaces.Queue;
import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * A classe LongDistanceItems implementa a interface Topic,
 * representando um tópico de itens de longa distância.
 */
public class LongDistanceItems implements Topic {
    private final String name = "queue/long-distance-items";
    private final List<Consumer> consumers = new ArrayList<>();
    private final Queue<Message> messages = new LinkedQueue<>();
    private final MessageRepository repository;

    /**
     * Construtor que inicializa o repositório de mensagens.
     *
     * @param repository O repositório de mensagens usado para armazenar e acessar mensagens.
     */
    public LongDistanceItems(MessageRepository repository) {
        this.repository = repository;
    }

    /**
     * Retorna o nome do tópico.
     *
     * @return O nome do tópico.
     */
    @Override
    public String name() {
        return name;
    }

    /**
     * Adiciona uma mensagem ao tópico e notifica os consumidores.
     *
     * @param message A mensagem a ser adicionada.
     */
    @Override
    public void addMessage(Message message) {
        messages.enqueue(message);
        repository.append(this.name, message);
        notifyConsumers(message);
    }

    /**
     * Adiciona um consumidor ao tópico.
     *
     * @param consumer O consumidor a ser adicionado.
     */
    @Override
    public void subscribe(Consumer consumer) {
        consumers.add(consumer);
    }

    /**
     * Remove um consumidor do tópico.
     *
     * @param consumer O consumidor a ser removido.
     */
    @Override
    public void unsubscribe(Consumer consumer) {
        consumers.remove(consumer);
    }

    /**
     * Retorna a lista de consumidores do tópico.
     *
     * @return A lista de consumidores.
     */
    @Override
    public List<Consumer> consumers() {
        return new ArrayList<>(consumers);
    }

    /**
     * Retorna o repositório de mensagens associado ao tópico.
     *
     * @return O repositório de mensagens.
     */
    @Override
    public MessageRepository getRepository() {
        return repository;
    }

    /**
     * Notifica todos os consumidores sobre uma nova mensagem.
     *
     * @param message A mensagem que deve ser notificada.
     */
    @Override
    public void notifyConsumers(Message message) {
        for (Consumer consumer : consumers) {
            CompletableFuture<Boolean> completableFuture = CompletableFuture
                    .supplyAsync(() -> consumer.consume(message));
            completableFuture.thenAccept(result ->
                    System.out.printf("Mensagem consumida: %s%n", result)
            );
        }
    }

    /**
     * Compara este tópico com outro tópico com base no nome.
     *
     * @param o O outro tópico a ser comparado.
     * @return Um valor negativo, zero ou positivo conforme o nome deste tópico
     *         seja lexicograficamente menor, igual ou maior que o nome do outro tópico.
     */
    @Override
    public int compareTo(Topic o) {
        return this.name().compareTo(o.name());
    }
}
