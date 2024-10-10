package br.com.mangarosa.messages.impl;

import java.util.UUID;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

public class MessageConsumer implements Consumer {

    private Topic topic;
    private MessageRepository repository;
    private final String name;

    public MessageConsumer(String name, Topic topic, MessageRepository repository) {
        this.topic = topic;
        this.repository = repository;
        this.name = name;
    }

    /**Método que acessa o repositório para consumir a mensagem que foi passada pelo argumento, 
     * armazena o consumidor na mensagem e retorna se a mensagem foi consumida corretamente;
    */
    @Override
    public boolean consume(Message message) {

        this.repository.consumeMessage(this.topic.name(), UUID.fromString(message.getId()));
        message.addConsumption(this);
        return message.isConsumed();
    }

    // Método que retorna o nome do consumer;
    @Override
    public String name() {
        return this.name;
    }

}
