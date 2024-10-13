package br.com.mangarosa.messages.interfaces;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.repositories.MessageRepository;
import br.com.mangarosa.datastructures.interfaces.Queue;
import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;

import java.util.HashMap;
import java.util.Map;

public class SimpleMessageBroker implements MessageBroker {
    private final MessageRepository repository; // Repositório para armazenar mensagens
    private final Map<String, Queue<Message>> queues; // Mapa de filas por nome

    public SimpleMessageBroker(MessageRepository repository) {
        this.repository = repository;
        this.queues = new HashMap<>(); // Inicializa o mapa de filas
    }

    @Override
    public void publishMessage(String queue, Message message) {
        Queue<Message> messageQueue = queues.computeIfAbsent(queue, k -> new LinkedQueue<>());
        messageQueue.enqueue(message); // Enfileira a mensagem
        repository.saveMessage(queue, message); // Salva a mensagem no repositório
        System.out.println("Mensagem publicada na fila '" + queue + "': " + message.getContent());
    }

    @Override
    public Message consumeMessage(String queue) {
        Queue<Message> messageQueue = queues.get(queue);
        if (messageQueue != null && !messageQueue.isEmpty()) {
            Message message = messageQueue.dequeue(); // Desenfileira a mensagem
            return message; // Retorna a mensagem consumida
        }
        return null; // Retorna null se a fila estiver vazia ou não existir
    }
}
