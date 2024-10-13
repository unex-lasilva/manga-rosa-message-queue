package br.com.mangarosa.datastructures.interfaces;

/**
 * Interface que define uma fila do tipo T.
 * @param <T> o tipo de dados armazenado na fila.
 */
public interface Queue<T> {

    /**
     * Adiciona um valor ao final da fila.
     * @param item O valor do tipo T a ser inserido na fila.
     */
    void enqueue(T item);

    /**
     * Remove e retorna o elemento no início da fila.
     * @return O primeiro elemento da fila ou null se a fila estiver vazia.
     */
    T dequeue();

    /**
     * Verifica se a fila está vazia.
     * @return true se a fila estiver vazia, false caso contrário.
     */
    boolean isEmpty();
}
