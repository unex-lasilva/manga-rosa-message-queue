package br.com.mangarosa.datastructures.interfaces.abstractClass;

/**
 * Classe abstrata que representa um nó em uma estrutura de dados encadeada.
 * @param <E> o tipo de elemento armazenado no nó
 */
public abstract class QueueNode<E> {
    public QueueNode<E> next; // Referência para o próximo nó na fila
    public E value; // Valor armazenado no nó

    /**
     * Retorna o próximo nó na fila.
     * @return o próximo nó na fila
     */
    public QueueNode<E> getNext() {
        return next;
    }

    /**
     * Define o próximo nó na fila.
     * @param next o próximo nó na fila
     */
    public void setNext(QueueNode<E> next) {
        this.next = next;
    }

    /**
     * Retorna o valor armazenado no nó.
     * @return o valor armazenado no nó
     */
    public E getValue() {
        return value;
    }

    /**
     * Define o valor armazenado no nó.
     * @param value o valor a ser armazenado no nó
     */
    public void setValue(E value) {
        this.value = value;
    }
}

