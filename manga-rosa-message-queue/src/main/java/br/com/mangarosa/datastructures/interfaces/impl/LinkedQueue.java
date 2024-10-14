package br.com.mangarosa.datastructures.interfaces.impl;

import br.com.mangarosa.datastructures.interfaces.Queue;

import java.util.Iterator;

/**
 * Classe auxiliar que representa um nó da fila.
 * Cada nó armazena um valor e a referência para o próximo nó na fila.
 *
 * @param <T> o tipo de valor que o nó armazena
 */
class QueueNode<T> {
    T value;
    QueueNode<T> next;

    /**
     * Construtor do nó da fila.
     *
     * @param value o valor a ser armazenado no nó
     */
    public QueueNode(T value){
        this.value = value;
        this.next = null;
    }
}

/**
 * Implementação de uma fila encadeada utilizando nós.
 * Esta fila implementa a interface Queue e armazena elementos que são comparáveis.
 *
 * @param <T> o tipo de dados que a fila armazena, que deve ser comparável
 */
public class LinkedQueue<T extends Comparable<T>> implements Queue<T> {

    private QueueNode<T> head;
    private int size;

    /**
     * Construtor que inicializa uma fila vazia.
     */
    public LinkedQueue(){
        this.head = null;
        this.size = 0;
    }

    /**
     * Adiciona um valor ao final da fila.
     *
     * @param value é o valor a ser adicionado à fila
     */
    @Override
    public void enqueue(T value) {
        QueueNode<T> node = new QueueNode<>(value);
        if (this.head == null){
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

    /**
     * Remove e retorna o valor do primeiro elemento da fila.
     *
     * @return o valor do primeiro elemento da fila
     * @throws IndexOutOfBoundsException se a fila estiver vazia
     */
    @Override
    public T dequeue(){
        T value;
        if (this.head == null){
            throw new IndexOutOfBoundsException("A fila está vazia");
        } else {
            value = this.head.value;
            this.head = this.head.next;
            this.size--;
        }
        return value;
    }

    /**
     * Retorna o valor do primeiro elemento da fila sem removê-lo.
     *
     * @return o valor do primeiro elemento da fila
     * @throws IndexOutOfBoundsException se a fila estiver vazia
     */
    @Override
    public T peek() {
        return this.head.value;
    }

    /**
     * Verifica se a fila está vazia.
     *
     * @return true se a fila estiver vazia, false caso contrário
     */
    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Retorna o tamanho atual da fila.
     *
     * @return o número de elementos na fila
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Retorna todos os elementos da fila em um array.
     *
     * @return um array contendo todos os elementos da fila
     */
    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] array = (T[]) new Comparable[this.size];
        QueueNode<T> current = this.head;
        int i = 0;

        while (current != null) {
            array[i++] = current.value;
            current = current.next;
        }

        return array;
    }

    /**
     * Limpa a fila, removendo todos os seus elementos.
     */
    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Retorna um iterator para percorrer os elementos da fila.
     *
     * @return um Iterator para a fila
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private QueueNode<T> current = head;

            /**
             * Verifica se há mais elementos na fila.
             *
             * @return true se houver mais elementos, false caso contrário
             */
            @Override
            public boolean hasNext() {
                return current != null;
            }

            /**
             * Retorna o próximo elemento da fila.
             *
             * @return o próximo elemento
             * @throws IllegalStateException se não houver mais elementos
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new IllegalStateException("Não há mais elementos da fila");
                }
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }
}
