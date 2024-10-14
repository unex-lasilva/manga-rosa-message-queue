package br.com.mangarosa.messages.interfaces.topicImpl;

import br.com.mangarosa.messages.interfaces.MsgRepositoryImpl;
import br.com.mangarosa.messages.interfaces.TopicImpl;

/**
 * Tópico específico para "queue/long-distance-items".
 */
public class LongDistanceItems extends TopicImpl {

    /**
     * Construtor que inicializa o tópico com o repositório de mensagens.
     *
     * @param messageRepository o repositório de mensagens associado
     */
    public LongDistanceItems(MsgRepositoryImpl messageRepository) {
        super("queue/long-distance-items", messageRepository);
    }
}
