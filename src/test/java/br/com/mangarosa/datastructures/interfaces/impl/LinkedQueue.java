package br.com.mangarosa.datastructures.interfaces.impl;

import br.com.mangarosa.datastructures.interfaces.Queue;
import java.util.LinkedList;

/**
 * Implementação de uma fila encadeada utilizando LinkedList.
 * @param <T> O tipo de dados armazenado na fila.
 */
public class LinkedQueue<T> implements Queue<T> {
    private final LinkedList<T> list = new LinkedList<>();  // Utiliza LinkedList para armazenar os elementos da fila

    /**
     * Adiciona um valor ao final da fila.
     * @param item O valor do tipo T a ser inserido na fila.
     */
    @Override
    public void enqueue(T item) {
        list.addLast(item);  // Adiciona o item ao final da fila
    }

    /**
     * Remove e retorna o elemento no início da fila.
     * @return O primeiro elemento da fila ou null se a fila estiver vazia.
     */
    @Override
    public T dequeue() {
        return list.pollFirst();  // Remove e retorna o primeiro item da fila, ou null se estiver vazia
    }

    /**
     * Verifica se a fila está vazia.
     * @return true se a fila estiver vazia, false caso contrário.
     */
    @Override
    public boolean isEmpty() {
        return list.isEmpty();  // Retorna true se a fila estiver vazia
    }
}
