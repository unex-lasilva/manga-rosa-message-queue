package br.com.mangarosa.messages.impl.consumers;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.impl.Repository;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;

public class FastDeliveryItemsConsumer implements Consumer {

      private Topic topic;
      private MessageRepository repository;

    public FastDeliveryItemsConsumer(Topic topic, MessageRepository repository){
        this.topic = topic;
        this.repository = repository;
    }

    @Override
    public boolean consume(Message message) {
        message.setConsumed(true);
        message.addConsumption(this);
        return message.isConsumed();
    }

    @Override
    public String name() {
        return "FastDeliveryItemsConsumer";
    }

}
