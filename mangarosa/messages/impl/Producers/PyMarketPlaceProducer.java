package br.com.mangarosa.messages.impl.Producers;

import br.com.mangarosa.messages.interfaces.Topic;
import br.com.mangarosa.messages.interfaces.Producer;
import br.com.mangarosa.messages.Message;
import java.util.List;
import java.util.ArrayList;

public class PyMarketPlaceProducer implements Producer {
    private final List<Topic> topics = new ArrayList<>();

    /**
     * Adiciona o tópico na lista de tópicos
     * @param topic topico
     */
    @Override
    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    /**
     * Remove o tópico da lista de tópicos
     * @param topic topico
     */
    @Override
    public void removeTopic(Topic topic) {
        topics.remove(topic);
    }

    /**
     * Envia a messagem para todos os consumidores do tópico
     * @param message mensagem para ser processada
     */
    @Override
    public void sendMessage(String message) {
        Message msg = new Message(this, message);
        for (Topic topic : topics) {
            if (topic.name().equals("queue/long-distance-items")) {
                topic.addMessage(msg);
            }
        }
    }

    /**
     * Retorna o nome do tópico que deve ser único em um broker
     * @return nome do tópico
     */
    @Override
    public String name() {
        return "PyMarketPlaceProducer";
    }
}


