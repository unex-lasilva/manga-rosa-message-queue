package br.com.mangarosa.datastructures.interfaces.impl;

import br.com.mangarosa.datastructures.abstractClasses.extd.QueueNodeMessage;
import br.com.mangarosa.datastructures.interfaces.Queue;
import br.com.mangarosa.messages.Message;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementação de uma fila encadeada usando nós de mensagem.
 */
public class LinkedQueue implements Queue<QueueNodeMessage> {
    private QueueNodeMessage firstNode;
    private QueueNodeMessage lastNode;
    private int size;

    /**
     * Construtor para inicializar uma fila vazia.
     */
    public LinkedQueue() {
        this.firstNode = null;
        this.lastNode = null;
        this.size = 0;
    }

    @Override
    public void enqueue(QueueNodeMessage newNode) {
        if (this.size == 0) {
            this.firstNode = newNode;
            this.lastNode = newNode;
        } else {
            this.lastNode.setNext(newNode);
            this.lastNode = newNode;
        }
        this.size++;
    }

    @Override
    public QueueNodeMessage dequeue() {
        if (this.firstNode == null) {
            throw new IllegalStateException("A fila está vazia.");
        }
        QueueNodeMessage node = this.firstNode;
        this.firstNode = (QueueNodeMessage) this.firstNode.getNext();
        if (this.firstNode == null) {
            this.lastNode = null;
        }
        this.size--;
        return node;
    }

    public Message dequeueValue() {
        return this.dequeue().getValue();
    }

    @Override
    public QueueNodeMessage peek() {
        if (this.firstNode == null) {
            throw new IllegalStateException("A fila está vazia.");
        }
        return this.firstNode;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public QueueNodeMessage[] toArray() {
        QueueNodeMessage[] arr = new QueueNodeMessage[this.size()];
        QueueNodeMessage current = this.firstNode;
        int index = 0;
        while (current != null) {
            arr[index] = current;
            current = (QueueNodeMessage) current.getNext();
            index++;
        }
        return arr;
    }

    public Message[] toArrayValues() {
        Message[] arr = new Message[this.size];
        QueueNodeMessage current = this.firstNode;
        int index = 0;
        while (current != null) {
            arr[index] = current.getValue();
            current = (QueueNodeMessage) current.getNext();
            index++;
        }
        return arr;
    }

    @Override
    public void clear() {
        QueueNodeMessage current = this.firstNode;
        while (current != null) {
            QueueNodeMessage next = (QueueNodeMessage) current.getNext();
            current.setNext(null);
            current.setValue(null);
            current = next;
        }
        this.firstNode = null;
        this.lastNode = null;
        this.size = 0;
    }

    @Override
    public Iterator<QueueNodeMessage> iterator() {
        return new Iterator<QueueNodeMessage>() {
            private QueueNodeMessage current = firstNode;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public QueueNodeMessage next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                QueueNodeMessage temp = current;
                current = (QueueNodeMessage) current.getNext();
                return temp;
            }
        };
    }
}
