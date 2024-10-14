package br.com.mangarosa.messages.interfaces.impl2.LongDistance;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.Consumer;
import br.com.mangarosa.messages.interfaces.MessageRepository;
import br.com.mangarosa.messages.interfaces.Topic;
import br.com.mangarosa.messages.interfaces.impl2.MessageRepositoryc;

import java.util.ArrayList;
import java.util.List;

public class QueueLongDistanceItems implements Topic {

    /***
     * Declarando a lista de consumidores e o repositório de mensagens
     */
    private final List<Consumer> listaConsumidores = new ArrayList<>();
    private final MessageRepository repository = new MessageRepositoryc();

    /***
     * Retorna o nome do tópico
     * @return
     */
    @Override
    public String name() {
        return "queue/long-distance-items";
    }

    /***
     * Adiciona uma mensagem ao repositório e notfica todos os consumidores
     * @param message mensagem a ser processada
     */
    @Override
    public void addMessage(Message message) {
        repository.append(name(), message);
        notifyConsumers(message);
    }

    /***
     * Adiciona um consumidor a lista de 'inscritos'
     * @param consumer consumidor
     */
    @Override
    public void subscribe(Consumer consumer) {
        listaConsumidores.add(consumer);
    }

    /***
     * Remove um consumidor da lista
     * @param consumer consumidor
     */
    @Override
    public void unsubscribe(Consumer consumer) {
        listaConsumidores.remove(consumer);
    }

    /***
     * Retorna a lista de consumidores inscritos no tópico
     * @return
     */
    @Override
    public List<Consumer> consumers() {
        return new ArrayList<>(listaConsumidores);
    }

    /***
     *Retorna o repositório de mensagens associado ao tópico
     * @return
     */
    @Override
    public MessageRepository getRepository() {
        return repository;
    }

    /***
     *Notifica todos os consumidores inscritos sobre uma nova mensagem chamando o todo "consume" de cada consumidor, passando a mensagem
     * @param message mensagem que deve ser notificada
     */
    @Override
    public void notifyConsumers(Message message) {
        for (Consumer consumer : listaConsumidores){
            consumer.consume(message);
        }
    }
}
