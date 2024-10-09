package br.com.mangarosa.messages.impl.consumers;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

public class LongDistanceItemsConsumer implements Consumer {

    private Topic topic;
    private MessageRepository repository;

    public LongDistanceItemsConsumer(Topic topic, MessageRepository repository){
        this.topic = topic;
        this.repository = repository;
    }

    public boolean consume(Message message) {
        message.setConsumed(true);
        message.addConsumption(this);
        return message.isConsumed();
    }

    @Override
    public String name() {
        return "LongDistanceItemsConsumer";
    }
}
