package br.com.mangarosa;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.impl.fast_delivery_items.FastDeliveryItemTopic;
import br.com.mangarosa.messages.impl.fast_delivery_items.consumers.FastDeliveryConsumer;
import br.com.mangarosa.messages.impl.fast_delivery_items.producers.PhysicPersonDeliveryProducer;
import br.com.mangarosa.messages.impl.fast_delivery_items.producers.FoodDeliveryProducer;
import br.com.mangarosa.messages.impl.long_distance_items.LongDistanceItemsTopic;
import br.com.mangarosa.messages.impl.long_distance_items.consumers.LongDistanceConsumer;
import br.com.mangarosa.messages.impl.long_distance_items.producers.FastDeliveryProducer;
import br.com.mangarosa.messages.impl.long_distance_items.producers.PyMarketPlaceProducer;
import br.com.mangarosa.messages.impl.repository.MesageRepository;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

//      TESTE FastDeliveryItemTopic
        MesageRepository repository = new MesageRepository();
        FastDeliveryItemTopic topic = new FastDeliveryItemTopic(repository);
        FoodDeliveryProducer producer1 = new FoodDeliveryProducer();
        PhysicPersonDeliveryProducer producer2 = new PhysicPersonDeliveryProducer();
        FastDeliveryConsumer consumer = new FastDeliveryConsumer();

        producer1.addTopic(topic);
        producer2.addTopic(topic);
        topic.subscribe(consumer);


        producer1.sendMessage("ian");
        producer1.sendMessage("Lucas");
        producer2.sendMessage("Bruna");
        producer2.sendMessage("Duda");

        List<Message> rep = (repository.getAllConsumedMessagesByTopic("queue/fast-delivery-items"));

        for (Message msg: rep){
            System.out.print(msg.getMessage()+ " ");
        }

//      TESTE FastDeliveryItemTopic
        LongDistanceItemsTopic topic_ = new LongDistanceItemsTopic(repository);
        FastDeliveryProducer producer1_ = new FastDeliveryProducer();
        PyMarketPlaceProducer producer2_ = new PyMarketPlaceProducer();
        LongDistanceConsumer consumer_ = new LongDistanceConsumer();

        producer1_.addTopic(topic_);
        producer2_.addTopic(topic_);
        topic_.subscribe(consumer_);


        producer1_.sendMessage("uva");
        producer1_.sendMessage("banana");
        producer2_.sendMessage("pera");
        producer2_.sendMessage("jaca");

        List<Message> rep_ = (repository.getAllConsumedMessagesByTopic("queue/long-distance-consumer"));

        for (Message msg: rep_){
            System.out.print(msg.getMessage()+ " ");
        }
    }
}