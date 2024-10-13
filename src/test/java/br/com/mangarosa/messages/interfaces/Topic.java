package br.com.mangarosa.messages.interfaces;

import br.com.mangarosa.consumers.Consumer; // Importa a classe Consumer
import br.com.mangarosa.messages.Message; // Importa a classe Message
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um tópico para a publicação e consumo de mensagens.
 */
public class Topic {
    private final String name; // Nome do tópico
    private final List<Consumer> consumers; // Lista de consumidores inscritos no tópico

    /**
     * Construtor da classe Topic.
     *
     * @param name O nome do tópico.
     */
    public Topic(String name) {
        this.name = name;
        this.consumers = new ArrayList<>(); // Inicializa a lista de consumidores
    }

    /**
     * Adiciona um consumidor ao tópico.
     *
     * @param consumer O consumidor a ser adicionado.
     */
    public void addConsumer(Consumer consumer) {
        if (consumer != null) {
            consumers.add(consumer); // Adiciona o consumidor se não for nulo
        } else {
            System.out.println("Não é possível adicionar um consumidor nulo."); // Mensagem de erro
        }
    }

    /**
     * Notifica todos os consumidores registrados sobre uma nova mensagem.
     *
     * @param message A mensagem a ser consumida.
     */
    public void notifyConsumers(Message message) {
        if (message != null) {
            for (Consumer consumer : consumers) {
                consumer.consume(message); // Chama o método consume de cada consumidor
            }
        } else {
            System.out.println("Não é possível notificar consumidores com uma mensagem nula."); // Mensagem de erro
        }
    }

    // Outros métodos para manipular tópicos e consumidores podem ser implementados aqui...
}
