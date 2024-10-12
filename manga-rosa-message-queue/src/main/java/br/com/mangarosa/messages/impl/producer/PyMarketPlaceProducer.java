package br.com.mangarosa.messages.impl.producer;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Producer;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que implementa um produtor de mensagens para o PyMarketPlace.
 * Esta classe permite adicionar tópicos e enviar mensagens formatadas
 * para todos os tópicos associados ao produtor do PyMarketPlace.
 */
public class PyMarketPlaceProducer implements Producer {
    // Lista de tópicos aos quais o produtor está associado
    private final List<Topic> topics = new ArrayList<>();

    /**
     * Adiciona um novo tópico à lista de tópicos associados a este produtor.
     *
     * @param topic O tópico que será adicionado.
     */
    @Override
    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    /**
     * Remove um tópico da lista de tópicos associados a este produtor.
     *
     * @param topic O tópico que será removido.
     */
    @Override
    public void removeTopic(Topic topic) {
        topics.remove(topic);
    }

    /**
     * Envia uma mensagem para todos os tópicos associados a este produtor,
     * prefixando o conteúdo da mensagem com "PyMarketPlace: ".
     *
     * @param message O conteúdo da mensagem que será enviada.
     */
    @Override
    public void sendMessage(String message) {
        for (Topic topic : topics) {
            topic.addMessage(new Message(this,"PyMarketPlace: " + message));
        }
    }

    /**
     * Retorna o nome do produtor.
     *
     * @return O nome do produtor como uma string.
     */
    @Override
    public String name() {
        return "PyMarketPlaceProducer";
    }
}

