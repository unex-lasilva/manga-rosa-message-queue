package br.com.mangarosa.messages.impl.consumers;

import java.util.UUID;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

public class LongDistanceItemsConsumer implements Consumer {

    private Topic topic;
    private MessageRepository repository;

    public LongDistanceItemsConsumer(Topic topic, MessageRepository repository) {
        this.topic = topic;
        this.repository = repository;
    }

    public boolean consume(Message message) {

        this.repository.consumeMessage(this.topic.name(), UUID.fromString(message.getId()));
        message.addConsumption(this);
        return message.isConsumed();
    }

    @Override
    public String name() {
        return "LongDistanceItemsConsumer";
    }
}
