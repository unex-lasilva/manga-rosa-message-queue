package br.com.mangarosa.messages.impl.fast_delivery_items.consumers;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;

public class FastDeliveryConsumer implements Consumer {

    private final String name;
    private final int timeConsume;

    public FastDeliveryConsumer() {
        this.name = "FastDeliveryConsumer";
        this.timeConsume = 4;
    }

    @Override
    public boolean consume(Message message) {
        System.out.println(this.name + ": Consumindo a mensagem..."+message.getMessage());

        for (int i = timeConsume; i > 0; i--) {
            System.out.println("Tempo restante para consumir: " + i + " segundos");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    @Override
    public String name() {
        return this.name;
    }
}
