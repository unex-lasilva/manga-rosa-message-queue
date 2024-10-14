package br.com.mangarosa.datastructures.interfaces.impl;

import br.com.mangarosa.datastructures.interfaces.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T extends Comparable<T>> implements Queue<T> {

    private QueueNode<T> head;
    private QueueNode<T> tail;
    private int size;

    public LinkedQueue(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void enqueue(T value) {

        QueueNode<T> newNode = new QueueNode<>(value);
        if (this.tail != null) {
            this.tail.nextNode = newNode;

        } else {
            this.head = newNode;

        }
        this.tail = newNode;
        this.size++;
    }

    @Override
    public T dequeue() {

        if (this.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        T value = this.head.value;
        this.head = this.head.nextNode;

        if (this.head == null) {
            this.tail = null;
        }

        this.size--;
        return value;
    }

    @Override
    public T peek() {
        return this.head.value;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T[] toArray() {
        T[] array = (T[]) new Comparable[this.size];
        QueueNode<T> nodeAux = this.head;
        for (int i = 0; i < this.size; i++) {
            array[i] = nodeAux.value;
            nodeAux = nodeAux.nextNode;
        }
        return array;
    }


    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private QueueNode<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = current.value;
                current = current.nextNode;
                return value;
            }
        };
    }

}
