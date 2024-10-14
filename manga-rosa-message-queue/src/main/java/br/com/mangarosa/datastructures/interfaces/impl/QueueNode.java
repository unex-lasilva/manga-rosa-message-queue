package br.com.mangarosa.datastructures.interfaces.impl;

public class QueueNode<T> {
    private T value;            // Valor armazenado no nó
    private QueueNode<T> next;  // Referência para o próximo nó

    public QueueNode(T value) {
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return value;
    }

    public QueueNode<T> getNext() {
        return next;
    }

    public void setNext(QueueNode<T> next) {
        this.next = next;
    }
}
