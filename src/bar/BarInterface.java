package bar;

import comInf.Message;
import comInf.MessageException;
import java.io.IOException;

/**
 * Este tipo de dados define o interface à barbearia numa solução do Problema
 * dos Barbeiros Sonolentos que implementa o modelo cliente-servidor de tipo 2
 * (replicação do servidor) com lançamento estático dos threads barbeiro.
 */
public class BarInterface {

    /**
     * Barbearia (representa o serviço a ser prestado)
     *
     * @serialField bShop
     */
    private Bar bar;

    /**
     * Instanciação do interface à barbearia.
     *
     * @param bar barbearia
     */
    public BarInterface(Bar bar) {
        this.bar = bar;
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
                outMessage = new Message(Message.ALERTWAITERENTERING);
                break;
            case Message.SIGNALTHEWAITER:
                bar.SignalTheWaiter();
                outMessage = new Message(Message.ALERTWAITERENTERING);
                break;
        }

        return (outMessage);
    }
}
