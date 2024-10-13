package br.com.mangarosa.messages.factory;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Producer;

import java.util.UUID;

public class MessageFactory {

    public static Message build (Producer producer, String messageText) {

        Message message = new Message(producer, messageText);

        // Define um ID unico para a mensagem
        message.setId(UUID.randomUUID().toString());

        // Define o estado da mensagem como não salse
        message.setConsumed(false);
        return  message;
    }
}
