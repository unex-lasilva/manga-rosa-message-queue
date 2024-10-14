package br.com.mangarosa.messages.interfaces.impl.consumers;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;

import java.util.UUID;

/**
 * A classe LongDistanceConsumer implementa a interface Consumer,
 * sendo responsável por consumir mensagens enviadas para o tópico "queue/long-distance-items".
 */
public class LongDistanceConsumer implements Consumer {
    private final String name = "LongDistanceConsumer";
    private final LinkedQueue<Message> messageQueue = new LinkedQueue<>();
    private final MessageRepository repository;

    /**
     * Construtor que inicializa o repositório de mensagens.
     *
     * @param repository O repositório de mensagens usado para armazenar e acessar mensagens.
     */
    public LongDistanceConsumer(MessageRepository repository) {
        this.repository = repository;
    }

    /**
     * Consome uma mensagem, processando-a e marcando-a como consumida.
     *
     * @param message A mensagem a ser consumida.
     * @return true se a mensagem foi consumida adequadamente, false caso contrário.
     */
    @Override
    public boolean consume(Message message) {
        if (message == null || message.isExperied()) {
            System.out.println("Mensagem inválida ou expirada.");
            return false;
        }

        // Adiciona a mensagem à fila
        messageQueue.enqueue(message);
        System.out.println("Mensagem adicionada na fila: " + message.getMessage());

        // Processa a mensagem na fila
        while (!messageQueue.isEmpty()) {
            Message nextMessage = messageQueue.dequeue();
            try {
                // Cria uma nova thread para processar a mensagem
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // Pausa de 1 segundo
                            Thread.sleep(1000);

                            // Marcar a mensagem como consumida
                            System.out.println("Mensagem processada pelo " + name + ": " + nextMessage.getMessage());
                            nextMessage.setConsumed(true);
                            nextMessage.addConsumption(LongDistanceConsumer.this);

                            // Marcar a mensagem como consumida no repositório
                            repository.consumeMessage("queue/fast-delivery-items", UUID.fromString(nextMessage.getId()));
                            System.out.println("Mensagem processada com sucesso.");
                        } catch (Exception e) {
                            System.err.println("Erro ao processar a mensagem: " + e.getMessage());
                        }
                    }
                });
                thread.start();
                thread.join();
            } catch (Exception e) {
                System.err.println("Erro ao processar a mensagem: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * Retorna o nome do consumidor.
     *
     * @return O nome do consumidor.
     */
    @Override
    public String name() {
        return name;
    }
}