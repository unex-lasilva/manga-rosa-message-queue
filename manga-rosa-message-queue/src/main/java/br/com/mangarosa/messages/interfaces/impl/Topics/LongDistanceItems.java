package br.com.mangarosa.messages.interfaces.impl.Topics;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementação do tópico "Long Distance Items".
 * Este tópico é responsável por armazenar e notificar as mensagens relacionadas a entregas de longa distância.
 */
public class LongDistanceItems implements Topic {

    private final String name;
    private final List<Consumer> consumers;
    private final MessageRepository repository;

    public LongDistanceItems(MessageRepository repository) {
        this.name = "queue/long-distance-items";
        this.consumers = new ArrayList<>();
        this.repository = repository;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public void addMessage(Message message) {
        // Adiciona a mensagem no repositório
        repository.append(this.name, message);
        // Notifica todos os consumidores
        notifyConsumers(message);
    }

    @Override
    public void subscribe(Consumer consumer) {
        consumers.add(consumer);
    }

    @Override
    public void unsubscribe(Consumer consumer) {
        consumers.remove(consumer);
    }

    @Override
    public List<Consumer> consumers() {
        return new ArrayList<>(consumers);
    }

    @Override
    public MessageRepository getRepository() {
        return this.repository;
    }
}
