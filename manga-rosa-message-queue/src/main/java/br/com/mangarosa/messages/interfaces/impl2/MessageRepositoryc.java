package br.com.mangarosa.messages.interfaces.impl2;

import br.com.mangarosa.messages.Message;
import br.com.mangarosa.messages.interfaces.MessageRepository;

import java.util.*;

public class MessageRepositoryc implements MessageRepository {

    /***
     * Aemazenando as mensagens associadas a cada tópico e os id's das mensagens
     */
    private final Map<String, List<Message>> armazenarMensagens = new HashMap<>();
    private final Map<String, Set<UUID>> mensagensConsumidas = new HashMap<>();


    /***
     * Adiciona uma mensagem ao tópico especificado. Se o tópico ainda não existir, ele será criado com uma nova lista de mensagens
     * @param topic nome do tópico que deve ser único
     * @param message mensagem - json
     */
    @Override
    public void append(String topic, Message message) {

        if (!armazenarMensagens.containsKey(topic)){
            armazenarMensagens.put(topic, new ArrayList<>());
        }
        armazenarMensagens.get(topic).add(message);
    }

    /***
     *Marca uma mensagem como consumida, associando seu ID ao tópico especificado.
     * @param topic nome do tópico que deve existir
     * @param messageId código da mensagem
     */
    @Override
    public void consumeMessage(String topic, UUID messageId) {

        List<Message> messageList = armazenarMensagens.get(topic);
        if (messageList != null){
            for(Message msg : messageList){
                if (msg.getId().equals(messageId)){
                    if (!mensagensConsumidas.containsKey(topic)){
                        mensagensConsumidas.put(topic, new HashSet<>());
                    }
                    mensagensConsumidas.get(topic).add(messageId);
                    break;
                }
            }
        }

    }

    /***
     * Retorna uma lista de todas as mensagens que ainda não foram consumidas associadas a um tópico específico.
     * @param topic nome do tópico
     * @return
     */
    @Override
    public List<Message> getAllNotConsumedMessagesByTopic(String topic) {

        List<Message> todasMensagens = armazenarMensagens.getOrDefault(topic, Collections.emptyList());
        Set<UUID> idsConsumidos = mensagensConsumidas.getOrDefault(topic, Collections.emptySet());

        List<Message> naoConsumidas = new ArrayList<>();
        for (Message msg : todasMensagens){
            if (idsConsumidos.contains(msg.getId())){
                naoConsumidas.add(msg);
            }
        }
        return naoConsumidas;
    }

    /***
     * Retorna uma lista de todas as mensagens que já foram consumidas associadas a um tópico específico.
     * @param topic nome do tópico
     * @return
     */
    @Override
    public List<Message> getAllConsumedMessagesByTopic(String topic) {

        List<Message> todasMensagens = armazenarMensagens.getOrDefault(topic, Collections.emptyList());
        Set<UUID> idsConsumidos = mensagensConsumidas.getOrDefault(topic, Collections.emptySet());

        List<Message> consumidas = new ArrayList<>();
        for (Message msg : todasMensagens) {
            if (idsConsumidos.contains(msg.getId())) {
                consumidas.add(msg);
            }
        }
        return consumidas;

    }
}
