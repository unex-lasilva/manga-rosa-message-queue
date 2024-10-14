package br.com.mangarosa.messages.interfaces.producersImpl;

import br.com.mangarosa.messages.MessageBroker;
import br.com.mangarosa.messages.interfaces.ProducerImpl;

/**
 * Produtor para o tópico "queue/long-distance-items".
 */
public class PyMarketPlaceProducer extends ProducerImpl {

    /**
     * Construtor que inicializa o produtor para "queue/long-distance-items".
     *
     * @param messageBroker o broker de mensagens associado
     */
    public PyMarketPlaceProducer(MessageBroker messageBroker) {
        super("PyMarketPlaceProducer", messageBroker, "queue/long-distance-items");
    }
}
