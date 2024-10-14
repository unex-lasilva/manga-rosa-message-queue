package br.com.mangarosa.datastructures.interfaces.impl;

/**
 * Classe que representa um nó em uma fila encadeada.
 * @param <T> O tipo de dado armazenado no nó.
 */
public class QueueNode<T> {
    private T value;
    private QueueNode<T> next;

    /**
     * Construtor para criar um nó com um valor específico.
     * @param value O valor que será armazenado no nó.
     */
    public QueueNode(T value) {
        this.value = value;
        this.next = null;
    }

    /**
     * Retorna o valor armazenado no nó.
     * @return O valor armazenado.
     */
    public T getValue() {
        return value;
    }

    /**
     * Define o valor armazenado no nó.
     * @param value O novo valor a ser armazenado.
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Retorna o próximo nó na fila.
     * @return O próximo nó.
     */
    public QueueNode<T> getNext() {
        return next;
    }

    /**
     * Define o próximo nó na fila.
     * @param next O próximo nó a ser apontado.
     */
    public void setNext(QueueNode<T> next) {
        this.next = next;
    }
}