package br.com.mangarosa.datastructures.interfaces.impl;

import br.com.mangarosa.datastructures.interfaces.Queue;

import java.util.Iterator;

public class LinkedQueue<T extends Comparable<T>> implements Queue<T> {

    public QueueNode<T> first;
    public QueueNode<T> last;
    public int size;

    public LinkedQueue(){
        this.first = null;
        this.last = this.first;
        this.size = 0;
    }

    @Override
    public void enqueue(T value) {
    QueueNode<T> queueNode = new QueueNode<>(value);
        if (this.first == null){
            this.first = this.last = queueNode;
        }
        else {
            this.last.next = queueNode;
            this.last = queueNode;
        }
        this.size++;
    }

    @Override
    public T dequeue() {
        QueueNode<T> removedQueueNode = this.first;
        if(isEmpty()){
            throw new IllegalStateException("Queue is empty");

        } else {
            if (this.first == this.last){
                this.first = null;
                this.last = null;
            } else {
                this.first = this.first.next;
            }

            this.size--;
            return removedQueueNode.value;
        }
    }

    @Override
    public T peek() {
        if(isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        return this.first.value;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T[] toArray() {
        T[] array = (T[]) new Object[this.size];

        QueueNode<T> aux = this.first;
        for(int i = 0; i <= (this.size - 1); i++){
            array[i] = aux.value;
            aux = aux.next;
        }
        return array;
    }

    @Override
    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
