package br.com.mangarosa.messages.consumers;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;

public class FastDeliveryConsumer implements Consumer {
    private final String name;

    public FastDeliveryConsumer(String name) {
        this.name = name;
    }

    @Override
    public boolean consume(Message message) {
        if (message.isExpired()) {
            System.out.println("Mensagem expirada e descartada: " + message.getMessage());
            return false;
        }

        System.out.println("Consumindo mensagem: " + message.getMessage());
        message.setConsumed(true);
        return true;
    }

    @Override
    public String name() {
        return name;
    }
}

