package br.com.mangarosa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.datastructures.interfaces.impl.QueueNode;
import br.com.mangarosa.messages.MessageBroker;
import br.com.mangarosa.messages.ProducerMessage;
import br.com.mangarosa.messages.Repository;
import br.com.mangarosa.messages.SimpleTopic;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();
        MessageBroker broker = new MessageBroker(repository);
        ProducerMessage producer = new ProducerMessage(broker, repository);
        broker.createTopic(new SimpleTopic("teste"));
        producer.addTopic(broker.getTopicByName("teste"));
        producer.sendMessage("teste");


        
    }
}