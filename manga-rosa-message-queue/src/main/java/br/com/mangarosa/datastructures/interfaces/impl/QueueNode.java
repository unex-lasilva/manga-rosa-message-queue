package br.com.mangarosa.datastructures.interfaces.impl;

/**
 * A classe QueueNode representa um nó na estrutura de uma fila encadeada,
 * armazenando um valor e um ponteiro para o próximo nó.
 *
 * @param <T> o tipo de elemento mantido neste nó
 */
public class QueueNode<T> {
    protected Object value;
    protected QueueNode<T> pointer;

    /**
     * Construtor que inicializa o nó com um valor.
     *
     * @param value o valor a ser armazenado no nó
     */
    public QueueNode(Object value) {
        this.value = value;
        this.pointer = null;
    }

    /**
     * Retorna o valor armazenado no nó.
     *
     * @return o valor armazenado no nó
     */
    public Object getValue() {
        return value;
    }

    /**
     * Retorna o ponteiro para o próximo nó.
     *
     * @return o ponteiro para o próximo nó
     */
    public QueueNode<T> getPointer() {
        return pointer;
    }
}
