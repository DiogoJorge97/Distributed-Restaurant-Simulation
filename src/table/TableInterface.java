package table;

import kitchen.*;
import comInf.Message;
import comInf.MessageException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Este tipo de dados define o interface à barbearia numa solução do Problema
 * dos Barbeiros Sonolentos que implementa o modelo cliente-servidor de tipo 2
 * (replicação do servidor) com lançamento estático dos threads barbeiro.
 */
public class TableInterface {

    /**
     * Barbearia (representa o serviço a ser prestado)
     *
     * @serialField bShop
     */
    private Table table;

    /**
     * Instanciação do interface à barbearia.
     *
     * @param table barbearia
     */
    public TableInterface(Table table) {
        this.table = table;
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
    public Message processAndReply(Message inMessage) throws MessageException, IOException, InterruptedException {
        Message outMessage = null;                           // mensagem de resposta

        /* validação da mensagem recebida */
//        switch (inMessage.getType()) {
//            case Message.SALUTETHECLIENT:
//                break;
//            case Message.GETTHEPAD:
//                break;
//            case Message.DELIVEREPORTION:
//                break;
//            case Message.HAVEALLCLIENTSBEENSERVED:
//                break;
//            case Message.RESETDELIVEREDCOUNTER:
//                break;
//            case Message.PRESENTTHEBILL:
//                break;
//            case Message.SAYGOODBYE:
//                break;
//            default:
//                throw new MessageException("Tipo inválido!", inMessage);
//        }

        /* seu processamento */
        switch (inMessage.getType()) {
            case Message.SALUTETHECLIENT:
                table.saluteTheClient();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.GETTHEPAD:
                table.getThePad();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.DELIVEREPORTION:
                table.deliverPortion();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.HAVEALLCLIENTSBEENSERVED:
                boolean allserved = table.haveAllClientsBeenServed();
                outMessage = new Message(Message.SERVERACKNOWLEDGE, allserved);
                break;
            case Message.RESETDELIVEREDCOUNTER:
                table.resetDeliveredCounter();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.PRESENTTHEBILL:
                table.presentTheBill();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.SAYGOODBYE:
                table.sayGoodbye();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.ENTER:
                int studentCount = table.enter(inMessage.getStudentCount());
                
                outMessage = new Message(Message.SERVERACKNOWLEDGE, studentCount);
                break;
            case Message.READTHEMENU:
                table.readTheMenu(inMessage.getStudentCount());
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.HASEVERYBODYCHOSEN:
                boolean everybody = table.hasEverybodyChosen();
                outMessage = new Message(Message.SERVERACKNOWLEDGE, everybody);
                break;
            case Message.PREPARETHEORDER:
                table.prepareTheOrder(inMessage.getStudentCount());
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.DESCRIBETHEORDER:
                table.describeTheOrder();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.JOINTHETALK:
                table.joinTheTalk(inMessage.getStudentCount());
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.INFORMCOMPANION:
                table.informCompanion(inMessage.getStudentCount());
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.STARTEATING: {
                try {
                    table.startEating(inMessage.getStudentCount());
                } catch (InterruptedException ex) {
                    Logger.getLogger(TableInterface.class.getName()).log(Level.SEVERE, null, ex);
                }

                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            }

            case Message.ENDEATING:
                table.endEating(inMessage.getStudentCount());
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;

            case Message.HASEVERYBODYFINISHED:
                boolean finished = table.hasEverybodyFinished(inMessage.getStudentCount());
                outMessage = new Message(Message.SERVERACKNOWLEDGE, finished);
                break;

            case Message.SHOULDHAVEARRIVEDEARLIER:
                table.shouldHaveArrivedEarlier(inMessage.getStudentCount());
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;

            case Message.HONORTHEBILL:
                table.honorTheBill();
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;

            case Message.EXIT:
                table.exit(inMessage.getStudentCount());
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;

        }

        return (outMessage);
    }
}
