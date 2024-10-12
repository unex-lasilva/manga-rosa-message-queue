package br.com.mangarosa.datastructures.interfaces;
/**
 * Classe que representa um nó de uma fila encadeada.
 *
 * @param <T> o tipo de valor que este nó irá armazenar.
 */
public class QueueNode<T> {
    // O valor armazenado no nó
    T value;
    // O próximo nó na fila
    QueueNode<T> next;

    /**
     * Construtor que inicializa um nó com um valor especificado.
     *
     * @param value O valor a ser armazenado neste nó.
     */
    public QueueNode(T value) {
        this.value = value;
        this.next = null;
    }

    /**
     * Retorna o valor armazenado neste nó.
     *
     * @return O valor do nó.
     */
    public T getValue() {
        return value;
    }


    /**
     * Define um novo valor para este nó.
     *
     * @param value O novo valor a ser armazenado.
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Define o próximo nó na fila.
     *
     * @param next O próximo nó a ser definido.
     */
    public void setNext(QueueNode<T> next) {
        this.next = next;
    }


    /**
     * Retorna o próximo nó na fila.
     *
     * @return O próximo nó.
     */
    public QueueNode<T> getNext() {
        return next;
    }
}
