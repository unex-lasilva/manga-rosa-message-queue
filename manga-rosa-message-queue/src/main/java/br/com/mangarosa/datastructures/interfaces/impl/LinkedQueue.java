package br.com.mangarosa.datastructures.interfaces.impl;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

import br.com.mangarosa.datastructures.interfaces.Queue;

public class LinkedQueue<T extends Comparable<T>> implements Queue<T> {
    private QueueNode<T> head;
    private int size;
    private T[] array;
    private Class<T> type;

    public LinkedQueue(Class<T> clazz) {
        this.head = null;
        this.size = 0;
        this.type = clazz;

    }

    @Override
    public void enqueue(T value) {
        QueueNode<T> node = new QueueNode<>(value);
        if (this.isEmpty()) {
            this.head = node;
        } else {

            QueueNode<T> aux = this.head;

            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = node;
        }
        this.size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila está vazia!");
        } else {
            QueueNode<T> aux = this.head;
            this.head = this.head.next;
            this.size--;
            return aux.value;
        }
    }

    @Override
    public void clear() {
        this.head = null;

    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException("A lista está vazia!");
        }
        return this.head.value;
    }

    @Override
    public int size() {
        return this.size;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray() {
        if (this.isEmpty()) {
            throw new IllegalStateException("A lista está vazia!");
        } else {

            this.array = (T[]) Array.newInstance(this.type, this.size());

            QueueNode<T> aux = this.head;
            int index = 0;

            while (aux != null) {
                this.array[index] = aux.value;
                aux = aux.next;
                index++;
            }
            return this.array;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            QueueNode<T> current = head;
            
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T value = current.value;
                    current = current.next;
                    return value;
                } else {
                    throw new NoSuchElementException("Elemento inesistente!");
                }
            }
        };
    }
}
