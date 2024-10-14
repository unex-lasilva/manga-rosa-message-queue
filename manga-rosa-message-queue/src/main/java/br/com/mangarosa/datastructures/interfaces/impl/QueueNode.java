package br.com.mangarosa.datastructures.interfaces.impl;

public class QueueNode<T> {
    T value;
    QueueNode<T> nextNode;

    public QueueNode(T value) {
        this.value = value;
        this.nextNode = null;
    }
}