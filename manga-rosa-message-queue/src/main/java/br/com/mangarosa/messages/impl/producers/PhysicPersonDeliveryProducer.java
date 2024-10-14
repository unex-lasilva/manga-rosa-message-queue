package br.com.mangarosa.messages.impl.producers;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.Producer;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe PhysicPersonDeliveryProducer representa um produtor de mensagens
 * que envia mensagens relacionadas à entrega para pessoas físicas.
 * Este produtor é responsável por enviar mensagens para todos os consumidores
 * registrados nos tópicos associados.
 */
public class PhysicPersonDeliveryProducer implements Producer {

    private List<Topic> topics;

    /**
     * Construtor da classe PhysicPersonDeliveryProducer.
     * Inicializa uma lista de tópicos vazia.
     */
    public PhysicPersonDeliveryProducer(){
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
        // Verifica se a mensagem é nula ou vazia
        if (message == null || message.isEmpty()){
            throw new IllegalArgumentException("A mensagem não pode ser nula ou vazia.");
        }

        // Envia a mensagem para todos os consumidores de cada tópico associado
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
        return "PhysicPersonDeliveryProducer";
    }
}
