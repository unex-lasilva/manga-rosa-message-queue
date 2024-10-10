package br.com.mangarosa.datastructures.interfaces.impl;

// Nó que irá armazenar a mensagem e o ponteiro para a mensagem seguinte da fila;
public class QueueNode<T> {
    public T value;
    QueueNode<T> next;

    public QueueNode(T value) {
        this.value = value;
    }
}
