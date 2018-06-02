package kitchen;

import comInf.Message;
import comInf.MessageException;
import java.io.IOException;

/**
 * Class responsible for decoding the messages received from the client side of the problem and instantiate the proper methods on the Kitchen (server side)
 * 
 */
public class KitchenInterface {

    /**
     * Kitchen (represents the service)
     *
     * @serialField kitchen 
     */
    private Kitchen kitchen;

    /**
     * Create an instance of the kitchen interface.
     *
     * @param kitchen kitchen
     */
    public KitchenInterface(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    /**
     * Message processing based on the execution of corresponding tasks.
     * Answer message creation
     *
     * @param inMessage message containing the client request
     *
     * @return Reply message
     *
     * @throws MessageException if the request message is considered invalid
     */
    public Message processAndReply(Message inMessage) throws MessageException, IOException {
        Message outMessage = null;                           // mensagem de resposta

        /* validação da mensagem recebida */
        switch (inMessage.getType()) {
            case Message.WATCHTHENEWS:
                break;
            case Message.STARTPREPARATION:
                break;
            case Message.PROCEEDTOPRESENTATION:
                break;
            case Message.ALERTTHEWAITER:
                if (inMessage.getStudentCount() == -1) {
                    throw new MessageException("Numero invalido!", inMessage);
                }
                break;
            case Message.ALLPORTIONSBEENDELIVERED:
                break;
            case Message.HAVENEXTPORTIONREADY:
                break;
            case Message.HAVETHEORDERBEENCOMPLETED:
                break;
            case Message.CONTINUEPREPARATION:
                break;
            case Message.CLEANUP:
                break;
            case Message.HANDNOTETOTHECHEF:
                break;
            case Message.COLLECTPORTION:
                break;
            case Message.CHEFWAITINGFORDELIVERYUP:
                break;
            case Message.SHUTKITCHEN:
                break;

            default:
                throw new MessageException("Tipo inválido!", inMessage);
        }


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
                break;
            case Message.SHUTKITCHEN:                                                        // shutdown do servidor
                KitchenMain.waitConnection = false;
                (((ClientProxy) (Thread.currentThread())).getScon()).setTimeout(10);
                outMessage = new Message(Message.SERVERACKNOWLEDGE);            // gerar confirmação
                break;
        }

        return (outMessage);
    }
}
