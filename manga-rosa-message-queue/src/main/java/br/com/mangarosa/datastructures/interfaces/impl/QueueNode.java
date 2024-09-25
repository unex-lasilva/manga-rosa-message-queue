package br.com.mangarosa.datastructures.interfaces.impl;

public class QueueNode<T> {
    public T value;
    QueueNode<T> next;

    public QueueNode(T value) {
        this.value = value;
    }

}
