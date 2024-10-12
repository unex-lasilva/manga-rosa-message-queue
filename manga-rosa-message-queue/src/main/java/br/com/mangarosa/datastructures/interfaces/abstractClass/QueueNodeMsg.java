package br.com.mangarosa.datastructures.interfaces.abstractClass;

import br.com.mangarosa.messages.Message;
import java.time.LocalDateTime;

/**
 * Classe que representa um nó de mensagem na fila.
 */
public class QueueNodeMsg extends QueueNode<Message> implements Comparable<QueueNodeMsg> {

    /**
     * Construtor que inicializa o nó da fila com uma mensagem.
     * @param value a mensagem a ser armazenada no nó
     */
    public QueueNodeMsg(Message value) {
        this.value = value;
    }

    /**
     * Construtor que inicializa o nó da fila com uma mensagem e o próximo nó.
     * @param value a mensagem a ser armazenada no nó
     * @param next o próximo nó na fila
     */
    public QueueNodeMsg(Message value, QueueNode<Message> next) {
        this.value = value;
        this.next = next;
    }

    /**
     * Compara este nó com outro nó com base na data de criação da mensagem.
     * @param node o nó a ser comparado
     * @return -1 se este nó foi criado antes do nó fornecido, 0 se foram criados no mesmo momento, 1 se foi criado depois
     */
    @Override
    public int compareTo(QueueNodeMsg node) {
        LocalDateTime actualNode = this.value.getCreatedAt();
        LocalDateTime nodeCreatedAt = node.getValue().getCreatedAt();
        if (actualNode.isBefore(nodeCreatedAt)) return -1;
        else if (actualNode.isEqual(nodeCreatedAt)) return 0;
        return 1;
    }
}
