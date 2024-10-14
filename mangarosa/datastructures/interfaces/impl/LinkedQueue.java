package br.com.mangarosa.datastructures.interfaces.impl;

import br.com.mangarosa.datastructures.interfaces.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T extends Comparable<T>> implements Queue<T> {

    private QueueNode<T> head; // nó que representa a frente da fila
    private QueueNode<T> tail; // nó que representa o fundo da fila
    private int size; // tamanho da fila

    /**
     * Construtor para criar uma fila vazia.
     */
    public LinkedQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Insere um valor no final da fila
     * @param value - um valor de qualquer tipo T que vai ser inserido na fila
     */
    @Override
    public void enqueue(T value) {
        QueueNode<T> newNode = new QueueNode<>(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    /**
     * Remove e retorna o elemento no início da fila.
     * @return O valor removido da fila.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Fila vazia");
        }
        T value = head.getValue();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return value;
    }

    /***
     * Retorna o primeiro elemento no início da fila, mas não
     * o remove da fila.
     * @return o primeiro elemento da fila
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Fila vazia");
        }
        return head.getValue();
    }

    /**
     * Retorna se o a fila está vazia ou não
     * @return se a fila está vazia ou não
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Retorna o tamanho da fila.
     * @return quantidade de elementos da fila
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Retorna todos os elementos da fila em um array
     * @return todos os elementos da fila
     */
    @Override
    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        QueueNode<T> current = head;
        int i = 0;
        while (current != null) {
            array[i] = current.getValue();
            current = current.getNext();
            i++;
        }
        return array;
    }

    /***
     * Limpa a fila.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Retorna um iterador para percorrer os elementos da fila.
     * @return Um iterador para a fila.
     */
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
                T value = current.getValue();
                current = current.getNext();
                return value;
            }
        };
    }
}