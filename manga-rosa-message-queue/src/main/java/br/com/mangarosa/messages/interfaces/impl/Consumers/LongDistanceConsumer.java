package br.com.mangarosa.messages.interfaces.impl.Consumers;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;

public class LongDistanceConsumer implements Consumer {

    private final String consumerName;

    public LongDistanceConsumer(String consumerName) {
        this.consumerName = consumerName;
    }

    @Override
    public boolean consume(Message message) {
        if (message.isExpired()) {
            System.out.println("Mensagem expirada e não pode ser consumida.");
            return false;
        }

        // Simulação do processamento da mensagem
        System.out.printf("LongDistanceConsumer [%s] consumiu a mensagem: %s%n", consumerName, message.getMessage());

        // Adiciona o consumo da mensagem
        message.addConsumption(this);
        message.setConsumed(true); // Marca a mensagem como consumida
        return true;
    }

    @Override
    public String name() {
        return consumerName;
    }
}
