package br.com.mangarosa.messages.impl.consumer;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;

/**
 * Classe que implementa um consumidor de mensagens para itens de longa distância.
 * Esta classe define como as mensagens de itens de longa distância são consumidas
 * e registra a ação de consumo.
 */
public class LongDistanceItemsConsumer implements Consumer {
    /**
     * Consome uma mensagem de longa distância, marcando-a como consumida
     * e registrando o consumo.
     *
     * @param message A mensagem que será consumida.
     * @return true se a mensagem foi consumida com sucesso, false caso contrário.
     */
    @Override
    public boolean consume(Message message) {
        System.out.println("Consuming long distance message: " + message.getMessage());
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
        return "LongDistanceItemsConsumer";
    }
}
