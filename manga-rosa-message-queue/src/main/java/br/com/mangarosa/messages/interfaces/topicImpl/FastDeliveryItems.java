package br.com.mangarosa.messages.interfaces.topicImpl;

import br.com.mangarosa.messages.interfaces.MsgRepositoryImpl;
import br.com.mangarosa.messages.interfaces.TopicImpl;

/**
 * Tópico específico para "queue/fast-delivery-items".
 */
public class FastDeliveryItems extends TopicImpl {

    /**
     * Construtor que inicializa o tópico com o repositório de mensagens.
     *
     * @param messageRepository o repositório de mensagens associado
     */
    public FastDeliveryItems(MsgRepositoryImpl messageRepository) {
        super("queue/fast-delivery-items", messageRepository);
    }
}
