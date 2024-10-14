package br.com.mangarosa.messages.impl.consumers;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;

/**
 * Implementação de um consumidor para itens de entrega a longa distância.
 * Este consumidor processa mensagens relacionadas a entregas de itens que envolvem percursos mais longos.
 */
public class LongDistanceItemsConsumer implements Consumer {

    /**
     * Consome uma mensagem, verificando se ela não foi consumida e se ainda não expirou.
     * Caso a mensagem seja válida, ela será marcada como consumida e o consumo será registrado.
     *
     * @param message a mensagem a ser consumida
     * @return true se a mensagem foi consumida com sucesso, false caso contrário
     */
    @Override
    public boolean consume(Message message) {
        if (message != null && !message.isConsumed() && !message.isExperied()){
            System.out.println(name() + " consumiu a mensagem: " + message.getMessage());
            message.setConsumed(true);
            message.addConsumption(this);
            return true;
        }
        return false;
    }

    /**
     * Retorna o nome deste consumidor.
     *
     * @return o nome do consumidor
     */
    @Override
    public String name() {
        return "LongDistanceItemsConsumer";
    }
}
