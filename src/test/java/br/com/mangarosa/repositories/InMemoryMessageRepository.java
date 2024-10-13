package br.com.mangarosa.repositories;

import br.com.mangarosa.messages.Message;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementação de um repositório de mensagens em memória.
 */
public class InMemoryMessageRepository implements MessageRepository {
    // Armazena as mensagens em um mapa, onde a chave é o ID da mensagem
    private Map<String, Message> messageStore = new HashMap<>();

    /**
     * Salva uma mensagem associada a uma fila.
     *
     * @param queue   O nome da fila onde a mensagem será armazenada.
     * @param message A mensagem a ser salva.
     */
    @Override
    public void saveMessage(String queue, Message message) {
        messageStore.put(message.getId(), message); // Armazena a mensagem com o ID como chave
    }

    /**
     * Busca uma mensagem pelo seu ID.
     *
     * @param id O ID da mensagem a ser buscada.
     * @return A mensagem associada ao ID fornecido ou null se não encontrada.
     */
    @Override
    public Message getMessage(String id) {
        return messageStore.get(id); // Busca a mensagem pelo ID
    }
}
