package br.com.mangarosa.messages.interfaces.impl.producers;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Producer;
import br.com.mangarosa.messages.interfaces.Topic;

/**
 * A classe PhysicPersonDeliveryProducer implementa a interface Producer,
 * sendo responsável por produzir e enviar mensagens para os tópicos associados.
 */
public class PhysicPersonDeliveryProducer implements Producer {
    private final String name = "PhysicPersonDeliveryProducer";
    private final LinkedQueue<Topic> topics = new LinkedQueue<>();

    /**
     * Adiciona um tópico à lista de tópicos associados ao produtor.
     *
     * @param topic O tópico a ser adicionado.
     */
    @Override
    public void addTopic(Topic topic) {
        topics.enqueue(topic);
    }

    /**
     * Remove um tópico da lista de tópicos associados ao produtor.
     *
     * @param topic O tópico a ser removido.
     */
    @Override
    public void removeTopic(Topic topic) {
        LinkedQueue<Topic> tempQueue = new LinkedQueue<>();

        // Transfere os tópicos para uma fila temporária, exceto o tópico a ser removido
        while (!topics.isEmpty()) {
            Topic currentTopic = topics.dequeue();
            if (!currentTopic.equals(topic)) {
                tempQueue.enqueue(currentTopic);
            }
        }

        // Reinsere os tópicos restantes na fila original
        while (!tempQueue.isEmpty()) {
            topics.enqueue(tempQueue.dequeue());
        }
    }

    /**
     * Envia uma mensagem para todos os tópicos associados ao produtor.
     *
     * @param messageContent O conteúdo da mensagem a ser enviada.
     */
    @Override
    public void sendMessage(String messageContent) {
        Message message = new Message(this, messageContent);

        // Envia a mensagem para cada tópico na fila de tópicos
        for (Topic topic : topics) {
            topic.addMessage(message);
        }
    }

    /**
     * Retorna o nome do produtor.
     *
     * @return O nome do produtor.
     */
    @Override
    public String name() {
        return name;
    }
}
