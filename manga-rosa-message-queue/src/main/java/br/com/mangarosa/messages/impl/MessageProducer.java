package br.com.mangarosa.messages.impl;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.MessageBroker;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Producer;
import br.com.mangarosa.messages.interfaces.Topic;

public class MessageProducer implements Producer {
    private MessageBroker broker;
    private MessageRepository repository;
    private Topic topic;
    private final String name;

    public MessageProducer(String name, MessageBroker broker, MessageRepository repository, Topic topic) {
        this.broker = broker;
        this.repository = repository;
        this.topic = topic;
        this.name = name;
    }

    /*Método que adicona um novo tópico na lista de tópicos do MessageBroker; */
    @Override
    public void addTopic(Topic topic) {
        this.broker.createTopic(topic);
    }

    /*Método que remove um tópico da lista de tópicos; */
    @Override
    public void removeTopic(Topic topic) {
        this.broker.removeTopic(topic.name());
    }

    /*Método que cria uma nova mensagem, envia para o repositório 
    e avisa aos consumers que uma nova mensagem foi adicionada na fila; */
    @Override
    public void sendMessage(String message) {
        Message newMessage = new Message(this, message);
        this.broker.getTopicByName(this.topic.name()).addMessage(newMessage);
        this.broker.notifyConsumers();
    }

    /*Método que retorna o nome do Producer; */
    @Override
    public String name() {
        return this.name;
    }
}
