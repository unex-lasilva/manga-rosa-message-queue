package br.com.mangarosa.datastructures.interfaces.impl;

import br.com.mangarosa.datastructures.interfaces.Queue;
import br.com.mangarosa.datastructures.interfaces.abstractClass.QueueNodeMsg;

import java.util.Iterator;
import java.util.NoSuchElementException;

/** Classe que implementa uma fila encadeada (Linked Queue).
// @param <QueueNodeMsg> o tipo de elemento da fila.
*/

public class LinkedQueue implements Queue<QueueNodeMsg> {
    private QueueNodeMsg front; // Primeira mensagem da fila
    private QueueNodeMsg rear; // Última mensagem da fila
    private Integer size; // Tamanho da fila

    /**
     * Construtor que inicializa uma fila vazia.
     */
    public LinkedQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Adiciona uma nova mensagem ao final da fila.
     * @param newNode a nova mensagem a ser adicionada.
     */
    @Override
    public void enqueue(QueueNodeMsg newNode) {
        if (this.size.equals(0)) {
            this.front = newNode;
            this.rear = newNode;
        } else {
            QueueNodeMsg pointer = this.rear;
            this.rear = newNode;
            pointer.setNext(newNode);
        }
        this.size++;
    }

    /**
     * Remove e retorna a primeira mensagem da fila.
     * @return a mensagem removida.
     * @throws IllegalStateException se a fila estiver vazia.
     */
    @Override
    public QueueNodeMsg dequeue() {
        if (this.front == null) {
            throw new IllegalStateException("Queue is empty");
        }
        QueueNodeMsg node = this.front;
        this.front = (QueueNodeMsg) this.front.getNext();
        if (this.front == null) {
            this.rear = null;
        }
        this.size--;
        return node;
    }

    /**
     * Retorna, sem remover, a primeira mensagem da fila.
     * @return a primeira mensagem da fila.
     * @throws IllegalStateException se a fila estiver vazia.
     */
    @Override
    public QueueNodeMsg peek() {
        if (this.front == null) {
            throw new IllegalStateException("Queue is empty");
        }
        return this.front;
    }

    /**
     * Verifica se a fila está vazia.
     * @return true se a fila estiver vazia, false caso contrário.
     */
    @Override
    public boolean isEmpty() {
        return this.front == null;
    }

    /**
     * Retorna o tamanho da fila.
     * @return o número de mensagens na fila.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Converte a fila em um array de mensagens.
     * @return um array contendo todas as mensagens da fila.
     */
    @Override
    public QueueNodeMsg[] toArray() {
        QueueNodeMsg[] array = new QueueNodeMsg[this.size()];
        QueueNodeMsg current = this.front;
        int index = 0;
        while (current != null) {
            array[index] = current;
            current = (QueueNodeMsg) current.getNext();
            index++;
        }
        return array;
    }

    /**
     * Remove todas as mensagens da fila.
     */
    @Override
    public void clear() {
        QueueNodeMsg current = this.front;
        while (current != null) {
            QueueNodeMsg next = (QueueNodeMsg) current.getNext();
            current.setNext(null);
            current = next;
        }
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Retorna um iterador sobre as mensagens da fila.
     * @return um iterador para percorrer as mensagens.
     */
    @Override
    public Iterator<QueueNodeMsg> iterator() {
        return new Iterator<QueueNodeMsg>() {
            private QueueNodeMsg current = front;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public QueueNodeMsg next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                QueueNodeMsg temp = current;
                current = (QueueNodeMsg) current.getNext();
                return temp;
            }
        };
    }
}
