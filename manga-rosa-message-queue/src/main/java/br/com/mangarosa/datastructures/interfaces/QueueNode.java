package br.com.mangarosa.datastructures.interfaces.impl;

public class QueueNode<T> {
    public T value;
    public QueueNode next;


    public QueueNode(T value) {
        this.value = value;
        this.next = null;
    }

}
