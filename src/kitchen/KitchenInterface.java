package kitchen;

import comInf.Message;
import comInf.MessageException;
import java.io.IOException;

/**
 * Este tipo de dados define o interface à barbearia numa solução do Problema
 * dos Barbeiros Sonolentos que implementa o modelo cliente-servidor de tipo 2
 * (replicação do servidor) com lançamento estático dos threads barbeiro.
 */
public class KitchenInterface {

    /**
     * Barbearia (representa o serviço a ser prestado)
     *
     * @serialField bShop
     */
    private Kitchen kitchen;

    /**
     * Instanciação do interface à barbearia.
     *
     * @param kitchen barbearia
     */
    public KitchenInterface(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    /**
     * Processamento das mensagens através da execução da tarefa correspondente.
     * Geração de uma mensagem de resposta.
     *
     * @param inMessage mensagem com o pedido
     *
     * @return mensagem de resposta
     *
     * @throws MessageException se a mensagem com o pedido for considerada
     * inválida
     */
    public Message processAndReply(Message inMessage) throws MessageException, IOException {
        Message outMessage = null;                           // mensagem de resposta

        /* validação da mensagem recebida */
//        switch (inMessage.getType()) {
//            case Message.WATCHTHENEWS:
//                break;
//            case Message.STARTPREPARATION:
//                break;
//            case Message.PROCEEDTOPRESENTATION:
//                break;
//            case Message.ALERTTHEWAITER:
//                break;
//            case Message.ALLPORTIONSBEENDELIVERED:
//                break;
//            case Message.HAVENEXTPORTIONREADY:
//                break;
//            case Message.HAVETHEORDERBEENCOMPLETED:
//                break;
//            case Message.CONTINUEPREPARATION:
//                break;
//            case Message.CLEANUP:
//                break;
//            case Message.HANDNOTETOTHECHEF:
//                break;
//            case Message.COLLECTPORTION:
//                break;
//            default:
//                throw new MessageException("Tipo inválido!", inMessage);
//        }

        /* seu processamento */
        switch (inMessage.getType()) {
            case Message.WATCHTHENEWS:
                kitchen.WatchTheNews();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.STARTPREPARATION:
                kitchen.StartPreparation();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.PROCEEDTOPRESENTATION:
                kitchen.ProceedToPresentation();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.ALERTTHEWAITER:
                kitchen.AlertTheWaiter(inMessage.getStudentCount());
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.ALLPORTIONSBEENDELIVERED:
                boolean allportionsdelivered = kitchen.AllPortionsBeenDelivered(kitchen.studentNumber);
                outMessage = new Message(Message.SERVERACKNOWLEDGE, allportionsdelivered);
                break;
            case Message.HAVENEXTPORTIONREADY:
                kitchen.HaveNextPortionReady();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.HAVETHEORDERBEENCOMPLETED:
                boolean completed = kitchen.HaveTheOrderBeenCompleted();
                outMessage = new Message(Message.SERVERACKNOWLEDGE, completed);
                break;
            case Message.CONTINUEPREPARATION:
                kitchen.ContinuePreparation(kitchen.coursesNumber);
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.CLEANUP:
                kitchen.cleanup();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.HANDNOTETOTHECHEF:
                kitchen.handTheNoteToTheChef();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.COLLECTPORTION:
                kitchen.collectPortion();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.CHEFWAITINGFORDELIVERYUP:
                kitchen.chefWaitingForDeliveryUp();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
        }

        return (outMessage);
    }
}
