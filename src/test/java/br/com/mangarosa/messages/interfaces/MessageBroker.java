package br.com.mangarosa.messages.interfaces;

import br.com.mangarosa.messages.Message;

public interface MessageBroker {
    void publishMessage(String queue, Message message);
    Message consumeMessage(String queue);
}
