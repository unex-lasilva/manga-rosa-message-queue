package br.com.mangarosa.messages.impl.Consumers;

import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.Message;

/**
 * Implementação do consumidor para itens de entrega rápida.
 * Consome mensagens do tópico FastDeliveryItems.
 */
public class FastDeliveryConsumer implements Consumer {
    /**
     * Consome a mensagem e a marca como consumida.
     *
     * @param message a mensagem a ser consumida
     * @return true se a mensagem foi consumida com sucesso
     */
    @Override
    public boolean consume(Message message) {
        System.out.println("FastDeliveryConsumer consumindo: " + message.getMessage());
        message.setConsumed(true);
        return true;
    }

    /**
     * Retorna o nome do consumer
     * @return nome do consumidor
     */
    @Override
    public String name() {
        return "FastDeliveryConsumer";
    }
}
