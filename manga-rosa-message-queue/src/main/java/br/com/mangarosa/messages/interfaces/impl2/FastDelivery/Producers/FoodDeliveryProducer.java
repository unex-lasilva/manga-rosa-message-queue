package br.com.mangarosa.messages.interfaces.impl2.FastDelivery.Producers;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Producer;
import br.com.mangarosa.messages.interfaces.Topic;

import java.util.ArrayList;
import java.util.List;

public class FoodDeliveryProducer implements Producer {
    /** Lista de tópicos que este produtor gerencia e o nome do producer */
    private List<Topic> topicos;
    private final String nome;

    /***
     * Inicializa o nome e os topicos
     */
    public FoodDeliveryProducer() {
        this.nome = "FoodDeliveryProducer";
        this.topicos = new ArrayList<>();
    }

    /***
     * Adiciona um topico à lista
     * @param topic topico
     */
    @Override
    public void addTopic(Topic topic) {
        topicos.add(topic);
    }

    /***
     * Remove um topico
     * @param topic topico
     */
    @Override
    public void removeTopic(Topic topic) {
        topicos.remove(topic);
    }

    /***
     * Envia uma mensagem para todos os tópicos gerenciados por este produtor.
     * @param message mensagem para ser processada
     */
    @Override
    public void sendMessage(String message) {
        for (Topic topico : topicos) {
            topico.addMessage(new Message(this, message));
        }
    }

    /***
     * Retorna o nome do producer
     * @return
     */
    @Override
    public String name() {
        return(nome);
    }
}
