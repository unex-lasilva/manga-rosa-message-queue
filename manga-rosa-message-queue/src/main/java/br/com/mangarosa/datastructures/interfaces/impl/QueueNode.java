package br.com.mangarosa.datastructures.interfaces.impl;

import br.com.mangarosa.messages.Message;

public class QueueNode<T> {

    private final T info;

    private QueueNode<T> next;

    public QueueNode(T info) {
        this.info = info;
    }


    public T getInfo() {
        return info;
    }

    public QueueNode<T> getNext() {
        return next;
    }

    public void setNext(QueueNode<T> next) {
        this.next = next;
    }
}
