package br.com.mangarosa.messages.consumers;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;

public class LongDistanceItemsConsumer implements Consumer{

    private static final String NAME = "LongDistanceItemsConsumer";

    @Override
    public boolean consume(Message message) {
        message.addConsumption(this);
        return true;
    }

    @Override
    public String name() {
        return NAME;
    }
}
