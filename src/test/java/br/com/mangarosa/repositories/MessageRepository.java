package br.com.mangarosa.repositories;

import br.com.mangarosa.messages.Message; // Importa a classe Message

/**
 * Interface que define o contrato para um repositório de mensagens.
 */
public interface MessageRepository {
    /**
     * Salva uma mensagem associada a uma fila.
     *
     * @param queue   O nome da fila onde a mensagem será armazenada.
     * @param message A mensagem a ser salva.
     */
    void saveMessage(String queue, Message message);

    /**
     * Busca uma mensagem pelo seu ID.
     *
     * @param id O ID da mensagem a ser buscada.
     * @return A mensagem associada ao ID fornecido ou null se não encontrada.
     */
    Message getMessage(String id);
}
