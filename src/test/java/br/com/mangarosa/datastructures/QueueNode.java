package br.com.mangarosa.datastructures;

/**
 * Classe que representa um nó em uma fila encadeada.
 *
 * @param <T> O tipo do item armazenado no nó.
 */
public class QueueNode<T> {
    private T item;                // O item armazenado neste nó
    private QueueNode<T> next;     // Referência para o próximo nó na fila

    /**
     * Construtor para criar um novo nó com o item fornecido.
     *
     * @param item O item a ser armazenado no nó.
     */
    public QueueNode(T item) {
        this.item = item;           // Armazena o item
        this.next = null;           // Inicializa a referência do próximo nó como null
    }

    /**
     * Obtém o item armazenado no nó.
     *
     * @return O item armazenado.
     */
    public T getItem() {
        return item;                // Retorna o item armazenado
    }

    /**
     * Obtém a referência ao próximo nó.
     *
     * @return O próximo nó na fila, ou null se não houver.
     */
    public QueueNode<T> getNext() {
        return next;                // Retorna a referência para o próximo nó
    }

    /**
     * Define a referência ao próximo nó.
     *
     * @param next O próximo nó a ser definido.
     */
    public void setNext(QueueNode<T> next) {
        this.next = next;           // Define a referência para o próximo nó
    }
}
