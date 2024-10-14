package br.com.mangarosa.messages.interfaces.consumersImpl;

import br.com.mangarosa.messages.interfaces.ConsumerImpl;
import br.com.mangarosa.messages.interfaces.MsgRepositoryImpl;

/**
 * Consumidor para o tópico "queue/fast-delivery-items".
 */
public class FastDeliveryItemsConsumer extends ConsumerImpl {

    /**
     * Construtor que inicializa o consumidor para "queue/fast-delivery-items".
     *
     * @param messageRepository o repositório de mensagens associado
     */
    public FastDeliveryItemsConsumer(MsgRepositoryImpl messageRepository) {
        super("FastDeliveryItemsConsumer", "queue/fast-delivery-items", messageRepository);
    }
}
