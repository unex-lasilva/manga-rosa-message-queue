package br.com.mangarosa.datastructures.interfaces.impl;

import br.com.mangarosa.datastructures.interfaces.Queue;
import br.com.mangarosa.datastructures.interfaces.QueueNode;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Implementação de uma fila encadeada
 *
 * @param <T> o tipo de elementos que esta fila irá conter, que deve ser comparável.
 */
public class LinkedQueue<T extends Comparable<T>> implements Queue<T> {
    private QueueNode<T> first;
    private QueueNode<T> last;
    private int size;

    /**
     * Construtor que inicializa uma fila vazia.
     */
    public LinkedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Adiciona um elemento ao final da fila.
     *
     * @param value O valor a ser adicionado.
     */
    @Override
    public void enqueue(T value) {
        QueueNode<T> oldLast = last;
        last = new QueueNode<>(value);
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.setNext(last);
        }
        size++;
    }

    /**
     * Remove e retorna o elemento do início da fila.
     *
     * @return O valor do elemento removido.
     * @throws NoSuchElementException Se a fila estiver vazia.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty queue");
        }
        T value = first.getValue();
        first = first.getNext();
        size--;
        if (isEmpty()) {
            last = null;
        }
        return value;
    }

    /**
     * Retorna o elemento do início da fila sem removê-lo.
     *
     * @return O valor do primeiro elemento.
     * @throws NoSuchElementException Se a fila estiver vazia.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty queue");
        }
        return first.getValue();
    }

    /**
     * Verifica se a fila está vazia.
     *
     * @return true se a fila estiver vazia, false caso contrário.
     */
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Retorna o número de elementos na fila.
     *
     * @return O tamanho da fila.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Converte a fila em um array.
     *
     * @return Um array contendo todos os elementos da fila.
     */
    @Override
    public T[] toArray() {
        T[] array = (T[]) new Comparable[size];
        int index = 0;
        for (QueueNode<T> current = first; current != null; current = current.getNext()) {
            array[index++] = current.getValue();
        }
        return array;
    }

    /**
     * Limpa a fila, removendo todos os elementos.
     */
    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Retorna um iterador para percorrer os elementos da fila.
     *
     * @return Um iterador sobre a fila.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private QueueNode<T> current = first;

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
