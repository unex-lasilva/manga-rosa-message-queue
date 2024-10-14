package br.com.mangarosa.datastructures.interfaces.impl;

import br.com.mangarosa.datastructures.interfaces.Queue;

import java.util.Iterator;

public class LinkedQueue implements Queue {

    /***
     * Declarando o nó inicial (cabeça) da fila e o tamanho atual dela.
     */
    public QueueNode inicio;
    public int size;

    /***
     * Faz um append, adicionando um valor no final da fila/ última posição.
     * @param value - um valor de qualquer tipo T que vai ser inserido na fila
     */
    @Override
    public void enqueue(Comparable value) {
        QueueNode node = new QueueNode<>(value);
        if(inicio == null){
            inicio = node;
        } else {
            QueueNode aux = inicio;
            while (aux.next != null){
                aux = aux.next;
            }
            aux.next = node;
        }
        size++;}

    /***
     * Remove um elemento e retorna o valor no início da fila. Se o valor for nulo, ele vai retornar nulo.
     * @return
     */

    @Override
    public Comparable dequeue() {

        if (inicio == null){
            return null;
        }else {
            QueueNode aux = inicio;
            inicio = inicio.next;
            size--;
            return (Comparable) aux.value;
        }
    }

    /***
     * Retorna o valor do primeiro elemento da fila sem o remover, caso esteja vazia, vai retornar nulo
     * @return
     */
    @Override
    public Comparable peek() {
        if (inicio == null){
            return null;
        } else{
            return (Comparable) inicio.value;
        }
    }

    /***
     * Verificando se a fila está vazia, retornando true se estiver e false se não estiver
     * @return
     */
    @Override
    public boolean isEmpty() {
        if(inicio == null) {
            return true;
        } else {
            return false;
        }
    }

    /***
     * Tamanho da fila
     * @return
     */
    @Override
    public int size() {
        return size;
    }

    /***
     * Retorna os elementos da fila como um array
     * @return
     */
    @Override
    public Comparable[] toArray() {
        return new Comparable[0];
    }

    /***
     * Limpa a fila toda
     */
    @Override
    public void clear() {
        inicio = null;
        size = 0;
    }

    /***
     * Não implementei
     * @return
     */
    @Override
    public Iterator iterator() {
        return null;
    }
}
