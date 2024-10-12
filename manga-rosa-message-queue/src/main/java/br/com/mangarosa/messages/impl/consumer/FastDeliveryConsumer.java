package br.com.mangarosa.messages.impl.consumer;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;

/**
 * Classe que implementa um consumidor de mensagens para entrega rápida.
 * Esta classe define como as mensagens de entrega rápida são consumidas
 * e registra a ação de consumo.
 */
public class FastDeliveryConsumer implements Consumer {
    /**
     * Consome uma mensagem de entrega rápida, marcando-a como consumida
     * e registrando o consumo.
     *
     * @param message A mensagem que será consumida.
     * @return true se a mensagem foi consumida com sucesso, false caso contrário.
     */
    @Override
    public boolean consume(Message message) {
        System.out.println("Consuming fast delivery message: " + message.getMessage());
        message.setConsumed(true);
        message.addConsumption(this);
        return true;
    }

    /**
     * Retorna o nome do consumidor.
     *
     * @return O nome do consumidor como uma string.
     */
    @Override
    public String name() {
        return "FastDeliveryConsumer";
    }
}
