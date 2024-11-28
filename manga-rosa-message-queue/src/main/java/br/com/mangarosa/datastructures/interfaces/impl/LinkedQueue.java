package br.com.mangarosa.datastructures.interfaces.impl;

import br.com.mangarosa.datastructures.interfaces.Queue;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedQueue<T extends Comparable<T>> implements Queue<T> {

    private QueueNode<T> first;
    private QueueNode<T> last;
    private int size;

    public LinkedQueue() {
        this.size = 0;
    }



    @Override
    public void enqueue(T value) {
        QueueNode<T> node = new QueueNode<>(value);
        if (isEmpty()) {
            first = node;
            last = first;
        } else {
            last.setNext(node);
        }
        size++;
    }

    @Override
    public T dequeue() {
        if (first == null) {
            return null;
        }
        QueueNode<T> firstElement = first;
        first = first.getNext();
        size--;

        if (isEmpty()) {
            last = first;
        }

        return firstElement.getInfo();
    }

    @Override
    public T peek() {
        return !isEmpty() ? first.getInfo() : null;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T[] toArray() {
        T[] array = (T[]) new Comparable[size];
        QueueNode<T> current = first;
        for (int i = 0; i < size; i++) {
            array[i] = current.getInfo();
            current = current.getNext();
        }
        return array;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;

    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
