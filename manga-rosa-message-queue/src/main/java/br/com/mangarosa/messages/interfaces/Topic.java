package br.com.mangarosa.messages.interfaces;

import br.com.mangarosa.datastructures.interfaces.impl.LinkedQueue;
import br.com.mangarosa.messages.Message;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/***
 * Tópico é uma estrutura de fila onde as mensagens ficam para serem consumidas
 * pelos consumidores.
 */
public interface Topic extends Serializable {
    /***
     * Retorna o nome do tópico
     * @return topic name
     */
    String name();

    /**
     * Adiciona uma mensagem no final da fila do tópico
     * @param message mensagem a ser processada
     */
    void addMessage(Message message);

    /**
     * Adiciona um consumidor das mensagens dos produtores
     * @param consumer consumidor
     */
    void subscribe(Consumer consumer);

    /**
     * Remove um consumidor das mensagens dos produtores
     * @param consumer consumidor
     */
    void unsubscribe(Consumer consumer);

    /**
     * Retorna a lista de consumidores
     * @return lista de consumidores
     */
    List<Consumer> consumers();

    /**
     * Retorna o repository que conecta com o banco chave-valor
     * @return repository
     */
    MessageRepository getRepository();

    /***
     * notifica a todos os consumidores que tem uma nova mensagem para ser consumida.
     * @param message mensagem que deve ser notificada
     */
    default void notifyConsumers(Message message, LinkedQueue<Message> messageQueue, MessageRepository repository, String topic){
        consumers().forEach( consumer -> {
            CompletableFuture<Boolean> completableFuture = CompletableFuture
                    .supplyAsync(() -> consumer.consume(message));
            completableFuture.join();
            completableFuture.thenAccept(result -> {
                if (result) {
                    message.setConsumed(true);
                    repository.consumeMessage(topic, UUID.fromString(message.getId()));
                    System.out.println("Mensagem consumida com sucesso!");
                    messageQueue.dequeue();
                } else {
                    System.out.println("Falha ao consumir a mensagem.");
                }
            }).exceptionally(ex -> {
                System.out.println("Erro ao consumir a mensagem: " + ex.getMessage());
                return null;
            });
        });

    }
}
