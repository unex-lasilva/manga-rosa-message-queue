package br.com.mangarosa.messages;

import java.util.UUID; // Importa a classe para gerar IDs únicos

public class Message {
    private final String id;      // ID da mensagem, que é imutável
    private final String content; // Conteúdo da mensagem, também imutável

    /**
     * Construtor da classe Message com ID e conteúdo.
     *
     * @param id      O ID da mensagem.
     * @param content O conteúdo da mensagem.
     */
    public Message(String id, String content) {
        this.id = id; // Inicializa o ID da mensagem
        this.content = content; // Inicializa o conteúdo da mensagem
    }

    /**
     * Construtor da classe Message com apenas o conteúdo.
     * Um ID único é gerado automaticamente.
     *
     * @param messageContent O conteúdo da mensagem.
     */
    public Message(String messageContent) {
        this.id = UUID.randomUUID().toString(); // Gera um ID único
        this.content = messageContent;           // Inicializa o conteúdo da mensagem
    }

    /**
     * Obtém o ID da mensagem.
     *
     * @return O ID da mensagem.
     */
    public String getId() {
        return id; // Retorna o ID da mensagem
    }

    /**
     * Obtém o conteúdo da mensagem.
     *
     * @return O conteúdo da mensagem.
     */
    public String getContent() {
        return content; // Retorna o conteúdo da mensagem
    }
}
