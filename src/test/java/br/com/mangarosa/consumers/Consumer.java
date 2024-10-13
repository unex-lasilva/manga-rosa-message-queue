package br.com.mangarosa.consumers;

import br.com.mangarosa.messages.Message;

public interface Consumer {
    void consume(Message message); // Somente um parâmetro
}
