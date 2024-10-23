package br.com.mangarosa.datastructures.abstractClasses.extd;

import br.com.mangarosa.datastructures.abstractClasses.QueueNode;
import br.com.mangarosa.messages.Message;


/**
 * Classe que representa um nó na fila de mensagens e implementa a interface Comparable para permitir a comparação entre nós.
 */
public class QueueNodeMessage extends QueueNode<Message> implements Comparable<QueueNodeMessage> {

    /**
     * Construtor que inicializa o nó com um valor de mensagem.
     * @param value a mensagem a ser armazenada no nó
     */
    public QueueNodeMessage(Message value) {
        this(value, null);
    }

    /**
     * Construtor que inicializa o nó com um valor de mensagem e uma referência para o próximo nó.
     * @param value a mensagem a ser armazenada no nó
     * @param next o próximo nó na fila
     */
    public QueueNodeMessage(Message value, QueueNode<Message> next) {
        super(value, next);
    }

    /**
     * Compara este nó de mensagem com outro nó baseado na data de criação da mensagem.
     * @param node o nó de mensagem a ser comparado
     * @return -1 se este nó foi criado antes do nó comparado, 0 se foram criados na mesma data e 1 se foi criado depois
     */
    @Override
    public int compareTo(QueueNodeMessage node) {
        return this.value.getCreatedAt().compareTo(node.getValue().getCreatedAt());
    }
}
