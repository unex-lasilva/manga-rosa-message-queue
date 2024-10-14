package br.com.mangarosa.messages.interfaces.producersImpl;

import br.com.mangarosa.messages.MessageBroker;
import br.com.mangarosa.messages.interfaces.ProducerImpl;

/**
 * Produtor para o tópico "queue/fast-delivery-items".
 */
public class PhysicPersonDeliveryProducer extends ProducerImpl {

    /**
     * Construtor que inicializa o produtor para "queue/fast-delivery-items".
     *
     * @param messageBroker o broker de mensagens associado
     */
    public PhysicPersonDeliveryProducer(MessageBroker messageBroker) {
        super("PhysicPersonDeliveryProducer", messageBroker, "queue/fast-delivery-items");
    }
}
