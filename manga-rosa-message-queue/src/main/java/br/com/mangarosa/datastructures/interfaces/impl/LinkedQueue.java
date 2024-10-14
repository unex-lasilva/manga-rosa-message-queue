package br.com.mangarosa.datastructures.interfaces.impl;

import br.com.mangarosa.datastructures.interfaces.Queue;
import java.util.Iterator;

/**
 * A classe LinkedQueue implementa uma fila encadeada (FIFO),
 * permitindo operações de inserção, remoção e acesso a elementos.
 *
 * @param <T> o tipo de elemento mantido nesta fila
 */
public class LinkedQueue<T extends Comparable<T>> implements Queue<T> {
    private QueueNode<T> start;
    private int size;

    /**
     * Adiciona um elemento ao final da fila.
     *
     * @param value o valor a ser adicionado à fila
     */
    @Override
    public void enqueue(T value) {
        QueueNode<T> node = new QueueNode<>(value);
        if (start == null) {
            start = node;
        } else {
            QueueNode<T> aux = start;
            while (aux.pointer != null) {
                aux = aux.pointer;
            }
            aux.pointer = node;
        }
        this.size++;
    }

    /**
     * Remove e retorna o elemento no início da fila.
     *
     * @return o elemento no início da fila, ou null se a fila estiver vazia
     */
    @Override
    public T dequeue() {
        if (start == null) {
            return null;
        } else {
            QueueNode<T> aux = start;
            start = start.pointer;
            this.size--;
            return (T) aux;
        }
    }

    /**
     * Retorna o elemento no início da fila sem removê-lo.
     *
     * @return o elemento no início da fila, ou null se a fila estiver vazia
     */
    @Override
    public T peek() {
        return start != null ? (T) start : null;
    }

    /**
     * Verifica se a fila está vazia.
     *
     * @return true se a fila estiver vazia, false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return start == null;
    }

    /**
     * Retorna o número de elementos na fila.
     *
     * @return o número de elementos na fila
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Retorna um array contendo todos os elementos da fila.
     *
     * @return um array contendo todos os elementos da fila
     */
    @Override
    public T[] toArray() {
        T[] array = (T[]) new Comparable[size];
        int index = 0;
        for (QueueNode<T> current = start; current != null; current = current.pointer) {
            array[index] = (T) current;
            index++;
        }
        return array;
    }

    /**
     * Remove todos os elementos da fila.
     */
    @Override
    public void clear() {
        this.start = null;
        this.size = 0;
    }

    /**
     * Retorna um iterador sobre os elementos nesta fila em ordem adequada.
     *
     * @return um iterador sobre os elementos nesta fila
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private QueueNode<T> current = start;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IllegalStateException("Não há mais elementos.");
                }
                T value = (T) current.getValue();
                current = current.getPointer();
                return value;
            }
        };
    }
}
