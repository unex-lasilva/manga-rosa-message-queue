package br.com.mangarosa.messages.impl.producers;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.Producer;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um produtor de mensagens para tópicos de entrega rápida.
 * Este produtor envia mensagens para consumidores registrados em tópicos associados.
 */
public class FastDeliveryProducer implements Producer {

    private List<Topic> topics;

    /**
     * Construtor da classe FastDeliveryProducer.
     * Inicializa uma lista de tópicos vazia.
     */
    public FastDeliveryProducer(){
        this.topics = new ArrayList<>();
    }

    /**
     * Adiciona um tópico à lista de tópicos do produtor.
     *
     * @param topic o tópico a ser adicionado
     */
    @Override
    public void addTopic(Topic topic) {
        this.topics.add(topic);
    }

    /**
     * Remove um tópico da lista de tópicos do produtor.
     *
     * @param topic o tópico a ser removido
     */
    @Override
    public void removeTopic(Topic topic) {
        this.topics.remove(topic);
    }

    /**
     * Envia uma mensagem para todos os consumidores registrados nos tópicos deste produtor.
     * A mensagem é validada antes de ser enviada para garantir que não seja nula ou vazia.
     *
     * @param message a mensagem a ser enviada
     * @throws IllegalArgumentException se a mensagem for nula ou vazia
     */
    @Override
    public void sendMessage(String message) {
        // Valida se a mensagem é válida (não nula ou vazia)
        if (message == null || message.isEmpty()){
            throw new IllegalArgumentException("A mensagem não pode ser nula ou vazia");
        }

        // Cria uma nova mensagem e a envia para todos os consumidores de cada tópico associado
        for (Topic topic : topics) {
            Message newMessage = new Message(this, message);

            // Obtém a lista de consumidores para o tópico e envia a mensagem
            List<Consumer> consumers = topic.consumers();
            for(Consumer consumer : consumers){
                consumer.consume(newMessage);
            }
        }
    }

    /**
     * Retorna o nome do produtor.
     *
     * @return o nome do produtor"
     */
    @Override
    public String name() {
        return "FastDeliveryProducer";
    }
}
