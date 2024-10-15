package br.com.mangarosa.messages;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe que representa uma fila de mensagens para processamento.
 */
public class MessageQueue {
    private final Queue<Message> queue;

    /**
     * Construtor para inicializar uma fila de mensagens.
     */
    public MessageQueue() {
        this.queue = new LinkedList<>();
    }

    /**
     * Adiciona uma mensagem à fila.
     * @param message a mensagem a ser adicionada
     */
    public void enqueue(Message message) {
        queue.offer(message);
    }

    /**
     * Remove e retorna a próxima mensagem da fila.
     * @return a mensagem removida da fila
     */
    public Message dequeue() {
        return queue.poll();
    }

    /**
     * Retorna a próxima mensagem na fila sem removê-la.
     * @return a próxima mensagem na fila
     */
    public Message peek() {
        return queue.peek();
    }

    /**
     * Verifica se a fila está vazia.
     * @return true se a fila estiver vazia, false caso contrário
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Retorna o tamanho da fila.
     * @return o número de mensagens na fila
     */
    public int size() {
        return queue.size();
    }

    /**
     * Limpa a fila de mensagens.
     */
    public void clear() {
        queue.clear();
    }

    /**
     * Remove as mensagens expiradas da fila.
     */
    public void removeExpiredMessages() {
        queue.removeIf(Message::isExpired);
    }
}
