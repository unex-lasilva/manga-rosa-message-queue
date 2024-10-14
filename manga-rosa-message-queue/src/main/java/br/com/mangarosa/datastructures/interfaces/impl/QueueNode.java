package br.com.mangarosa.datastructures.interfaces.impl;

public class QueueNode<T extends Comparable<T> >{

    /***
     * Armazenando o valor do nó e a referência para o próximo
     */
    public Object value;
    public QueueNode next;

    /***
     * Inicializa o nó com o valor
     * @param value valor que vai ser armazenado no nó
     */
    public QueueNode(Object value){
        this.value = value;
    }
}
