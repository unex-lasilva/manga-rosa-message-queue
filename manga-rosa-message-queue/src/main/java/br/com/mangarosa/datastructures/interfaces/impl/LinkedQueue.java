package br.com.mangarosa.datastructures.interfaces.impl;

import br.com.mangarosa.datastructures.interfaces.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementação de uma fila encadeada (LinkedQueue) que usa a classe QueueNode
 * @param <T> um tipo de dados que implementa Comparable
 */
public class LinkedQueue<T extends Comparable<T>> implements Queue<T> {
    private QueueNode<T> front; // Início da fila (cabeça)
    private QueueNode<T> rear;  // Final da fila (cauda)
    private int size;           // Tamanho da fila

    // Construtor da fila encadeada
    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    @Override
    public void enqueue(T value) {
        QueueNode<T> newNode = new QueueNode<>(value);
        if (isEmpty()) {
            front = rear = newNode; // Se estiver vazia, o novo nó será tanto o front quanto o rear
        } else {
            rear.setNext(newNode); // Adiciona o novo nó no final
            rear = newNode;        // Atualiza o rear
        }
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("A fila está vazia.");
        }
        T value = front.getValue();
        front = front.getNext(); // Remove o nó da cabeça
        size--;

        // Se a fila estiver vazia após a remoção, rear também deve ser null
        if (isEmpty()) {
            rear = null;
        }
        return value;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("A fila está vazia.");
        }
        return front.getValue(); // Retorna o valor no início sem removê-lo
    }

    @Override
    public boolean isEmpty() {
        return size == 0; // Verifica se o tamanho é 0
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T[] toArray() {
        if (isEmpty()) {
            return null;
        }
        T[] array = (T[]) new Comparable[size];
        int index = 0;
        for (T item : this) {
            array[index++] = item;
        }
        return array;
    }

    @Override
    public void clear() {
        front = rear = null;
        size = 0; // Zera a fila
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedQueueIterator();
    }

    // Iterator para percorrer a fila
    private class LinkedQueueIterator implements Iterator<T> {
        private QueueNode<T> current = front;

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
    }
}
