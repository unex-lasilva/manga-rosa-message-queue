package br.com.mangarosa.messages.interfaces.consumersImpl;

import br.com.mangarosa.messages.interfaces.ConsumerImpl;
import br.com.mangarosa.messages.interfaces.MsgRepositoryImpl;

/**
 * Consumidor para o tópico "queue/long-distance-items".
 */
public class LongDistanceItemsConsumer extends ConsumerImpl {

    /**
     * Construtor que inicializa o consumidor para "queue/long-distance-items".
     *
     * @param messageRepository o repositório de mensagens associado
     */
    public LongDistanceItemsConsumer(MsgRepositoryImpl messageRepository) {
        super("LongDistanceItemsConsumer", "queue/long-distance-items", messageRepository);
    }
}
