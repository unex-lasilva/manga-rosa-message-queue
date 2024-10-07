package br.com.mangarosa.messages.impl.consumers;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;

public class FastDeliveryItemsConsumer implements Consumer {

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
