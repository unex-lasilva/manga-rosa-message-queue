package br.com.mangarosa.messages.impl.Topicos;

import br.com.mangarosa.messages.impl.RepositoryImpl;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.Message;
import java.util.List;
import java.util.ArrayList;

public class LongDistanceItemsTopic implements Topic {
    private final List<Consumer> consumers = new ArrayList<>();
    private final MessageRepository repository = new RepositoryImpl();

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
     * Adiciona uma mensagem ao tópico.
     *
     * @param message a mensagem a ser adicionada
     */
    @Override
    public void addMessage(Message message) {
        repository.append(name(), message);
    }

    /**
     * Adiciona um consumidor das mensagens dos produtores
     * @param consumer consumidor
     */
    @Override
    public void subscribe(Consumer consumer) {
        consumers.add(consumer);
    }

    /**
     * Remove um consumidor das mensagens dos produtores
     * @param consumer consumidor
     */
    @Override
    public void unsubscribe(Consumer consumer) {
        consumers.remove(consumer);
    }

    /**
     * Retorna a lista de consumidores
     * @return lista de consumidores
     */
    @Override
    public List<Consumer> consumers() {
        return consumers;
    }

    /**
     * Retorna o repository que conecta com o banco chave-valor
     * @return repository
     */
    @Override
    public MessageRepository getRepository() {
        return repository;
    }
}
