package br.com.mangarosa.datastructures.abstractClasses;

import br.com.mangarosa.messages.Message;

/**
 * Classe abstrata que representa um nó em uma fila.
 * @param <E> o tipo de dados armazenado no nó
 */
public abstract class QueueNode<E> {

    /**
     * Referência para o próximo nó na fila.
     */
    public QueueNode<E> next;

    /**
     * Valor armazenado no nó.
     */
    public E value;

    /**
     * Construtor para inicializar o valor do nó.
     *
     * @param value o valor a ser armazenado
     * @param next
     */
    public QueueNode(E value, QueueNode<Message> next) {
        this.value = value;
        this.next = null;
    }

    /**
     * Retorna o próximo nó na fila.
     * @return o próximo nó
     */
    public QueueNode<E> getNext() {
        return next;
    }

    /**
     * Define o próximo nó na fila.
     * @param next o próximo nó a ser definido
     */
    public void setNext(QueueNode<E> next) {
        this.next = next;
    }

    /**
     * Retorna o valor armazenado no nó.
     * @return o valor armazenado
     */
    public E getValue() {
        return value;
    }

    /**
     * Define o valor a ser armazenado no nó.
     * @param value o valor a ser armazenado
     */
    public void setValue(E value) {
        this.value = value;
    }
}
