package br.com.mangarosa.messages.interfaces.producersImpl;

import br.com.mangarosa.messages.MessageBroker;
import br.com.mangarosa.messages.interfaces.ProducerImpl;

/**
 * Produtor para o tópico "queue/fast-delivery-items".
 */
public class FoodDeliveryProducer extends ProducerImpl {

    /**
     * Construtor que inicializa o produtor para "queue/fast-delivery-items".
     *
     * @param messageBroker o broker de mensagens associado
     */
    public FoodDeliveryProducer(MessageBroker messageBroker) {
        super("FoodDeliveryProducer", messageBroker, "queue/fast-delivery-items");
    }
}
