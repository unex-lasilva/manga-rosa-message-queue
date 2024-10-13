package br.com.mangarosa.consumers;

import br.com.mangarosa.repositories.MessageRepository; // Para a interface MessageRepository
import br.com.mangarosa.messages.Message; // Para a classe Message
import br.com.mangarosa.consumers.Consumer; // Para a interface Consumer

public class FoodDeliveryConsumer implements Consumer {
    private final MessageRepository repository; // Declare como final, pois não será alterado após a inicialização

    // Construtor que recebe um MessageRepository
    public FoodDeliveryConsumer(MessageRepository repository) {
        this.repository = repository;
    }

    // Método que consome uma mensagem (um parâmetro)
    @Override
    public void consume(Message message) { // Apenas um parâmetro
        if (message != null) { // Verifica se a mensagem não é nula
            System.out.println("Consumed message: " + message.getContent());
        } else {
            System.out.println("Received a null message."); // Mensagem informativa se a mensagem for nula
        }
    }

    // Sobrecarga do método consume para aceitar um inteiro
    public void consume(Message message, int someInt) {
        if (message != null) { // Verifica se a mensagem não é nula
            System.out.println("Consumed message: " + message.getContent());
            System.out.println("Some integer value: " + someInt); // Exibe o valor do inteiro
        } else {
            System.out.println("Received a null message."); // Mensagem informativa se a mensagem for nula
        }
    }
}
