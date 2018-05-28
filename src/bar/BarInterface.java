package bar;

import comInf.Message;
import comInf.MessageException;
import java.io.IOException;

/**
 * Class responsible for decoding the messages received from the client side of the problem and instantiate the proper methods on the Bar (server side)
 * 
 */

//TODO ADD authors in all files
//TODO delete all genericIO dependecies 
//TODO Add inMessage validations to all interfaces
public class BarInterface {

    /**
     * Bar (represents the service)
     *
     * @serialField bar 
     */
    private final Bar bar;

    /**
     * Create an instance of the bar interface.
     *
     * @param bar bar
     */
    public BarInterface(Bar bar) {
        this.bar = bar;
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
//        switch (inMessage.getType()) {
//            case Message.LOOKARROUND:
//                break;
//            case Message.PREPARETHEBILL:
//                break;
//            case Message.RETURNTOBAR:
//                break;
//            case Message.WAITERINTHEBARUP:
//                break;
//            case Message.WAITINGFORSTUDENTSTOFINISHDOWN:
//                break;
//            default:
//                throw new MessageException("Tipo inválido!", inMessage);
//        }

        /* seu processamento */
        switch (inMessage.getType()) {
            case Message.LOOKARROUND:
                char situation = bar.lookAround();
                outMessage = new Message(Message.SERVERACKNOWLEDGE, situation);
                break;
            case Message.PREPARETHEBILL:
                bar.prepareTheBill();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.RETURNTOBAR:
                bar.returnToTheBar();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.WAITERINTHEBARUP:
                bar.waiterInTheBarUp();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.WAITINGFORSTUDENTSTOFINISHDOWN:
                bar.waitingForStudentsToFinishDown();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.CALLTHEWAITERTOSERVE:
                bar.CallTheWaitertoServe();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.CALLTHEWAITER:
                bar.callTheWaiter();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.ALERTWAITEREXIT:
                bar.alertTheWaiterExit();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.WAITERGOHOME:
                bar.waiterGoHome();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.PAYTHEWAITER:
                bar.PayTheWaiter();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.ALERTWAITERENTERING:
                bar.alertWaiterEntering();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.SIGNALTHEWAITER:
                bar.SignalTheWaiter();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.SHUTBAR:                                                        // shutdown do servidor
                BarMain.waitConnection = false;
                (((ClientProxy) (Thread.currentThread())).getScon()).setTimeout(10);
                outMessage = new Message(Message.SERVERACKNOWLEDGE);            // gerar confirmação
                break;
        }

        return (outMessage);
    }
}
