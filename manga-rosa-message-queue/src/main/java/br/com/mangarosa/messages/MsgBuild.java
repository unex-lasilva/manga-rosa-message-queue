package br.com.mangarosa.messages;

import java.util.UUID;
import br.com.mangarosa.messages.interfaces.Producer;

/**
 * Classe utilitária para construir mensagens.
 */
public class MsgBuild {

    /**
     * Constrói uma nova mensagem.
     * @param producer o produtor da mensagem
     * @param messageText o conteúdo da mensagem
     * @return a mensagem construída
     */
    public static Message build(Producer producer, String messageText) {
        // Cria uma nova mensagem com o produtor e o conteúdo da mensagem
        Message message = new Message(producer, messageText);

        // Define um ID único para a mensagem
        message.setId(UUID.randomUUID().toString());

        // Define o status da mensagem como não consumida
        message.setConsumed(false);

        // Retorna a mensagem construída
        return message;
    }
}
