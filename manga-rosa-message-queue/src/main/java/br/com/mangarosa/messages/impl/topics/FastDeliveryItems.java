package br.com.mangarosa.messages.impl.topics;

import java.util.ArrayList;
import java.util.List;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

public class FastDeliveryItems implements Topic {
    private final List<Consumer> consumers;
    private MessageRepository messageRepository;

    public FastDeliveryItems(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.consumers = new ArrayList<>();
    }

    /*Retorna o nome do tópico; */
    @Override
    public String name() {
        return "queue/fast-delivery-items";
    }

    /*Adiciona a nova menssagem enviada pelo producer no repositório; */
    @Override
    public void addMessage(Message message) {
        messageRepository.append(this.name(), message);
    }

    /**Adiciona um consumer na lista de consumers do tópico; */
    @Override
    public void subscribe(Consumer consumer) {
        this.consumers.add(consumer);
    }

    /*Remove um consumer específico da lista de consumers do tópico; */
    @Override
    public void unsubscribe(Consumer consumer) {
        this.consumers.remove(consumer);
    }

    /*Retorna a lista de consumers de tópico; */
    @Override
    public List<Consumer> consumers() {
        return this.consumers;
    }

    /*retorna o repositório que o tópico está usando; */
    @Override
    public MessageRepository getRepository() {
        return this.messageRepository;
    }

}
