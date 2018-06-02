package table;

import comInf.Info;
import comInf.Message;
import comInf.MessageException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class responsible for decoding the messages received from the client side of
 * the problem and instantiate the proper methods on the Table (server side)
 *
 */
public class TableInterface {

    /**
     * Table (represents the service)
     *
     * @serialField kitchen
     */
    private Table table;

    /**
     * Create an instance of the kitchen interface.
     *
     * @param table Table
     */
    public TableInterface(Table table) {
        this.table = table;
    }

    /**
     * Message processing based on the execution of corresponding tasks. Answer
     * message creation
     *
     * @param inMessage message containing the client request
     *
     * @return Reply message
     *
     * @throws MessageException if the request message is considered invalid
     */
    public Message processAndReply(Message inMessage) throws MessageException, IOException, InterruptedException {
        Message outMessage = null;                           // mensagem de resposta

        /* validação da mensagem recebida */
        switch (inMessage.getType()) {
            case Message.SALUTETHECLIENT:
                break;
            case Message.GETTHEPAD:
                break;
            case Message.DELIVEREPORTION:
                break;
            case Message.HAVEALLCLIENTSBEENSERVED:
                break;
            case Message.RESETDELIVEREDCOUNTER:
                break;
            case Message.PRESENTTHEBILL:
                break;
            case Message.SAYGOODBYE:
                break;
            case Message.ENTER:
                if (inMessage.getStudentCount() < 0 || inMessage.getStudentCount() > Info.studentNumber) {
                    throw new MessageException("Id do cliente inválido!", inMessage);
                }
                break;
            case Message.READTHEMENU:
                if (inMessage.getStudentCount() < 0 || inMessage.getStudentCount() > Info.studentNumber) {
                    throw new MessageException("Id do cliente inválido!", inMessage);
                }
            case Message.HASEVERYBODYCHOSEN:
                break;
            case Message.PREPARETHEORDER:
                if (inMessage.getStudentCount() < 0 || inMessage.getStudentCount() > Info.studentNumber) {
                    throw new MessageException("Id do cliente inválido!", inMessage);
                }
            case Message.DESCRIBETHEORDER:
                break;
            case Message.JOINTHETALK:
                if (inMessage.getStudentCount() < 0 || inMessage.getStudentCount() > Info.studentNumber) {
                    throw new MessageException("Id do cliente inválido!", inMessage);
                }
                break;
            case Message.INFORMCOMPANION:
                if (inMessage.getStudentCount() < 0 || inMessage.getStudentCount() > Info.studentNumber) {
                    throw new MessageException("Id do cliente inválido!", inMessage);
                }
                break;
            case Message.STARTEATING:
                if (inMessage.getStudentCount() < 0 || inMessage.getStudentCount() > Info.studentNumber) {
                    throw new MessageException("Id do cliente inválido!", inMessage);
                }
                break;
            case Message.ENDEATING:
                if (inMessage.getStudentCount() < 0 || inMessage.getStudentCount() > Info.studentNumber) {
                    throw new MessageException("Id do cliente inválido!", inMessage);
                }
                break;
            case Message.HASEVERYBODYFINISHED:
                if (inMessage.getStudentCount() < 0 || inMessage.getStudentCount() > Info.studentNumber) {
                    throw new MessageException("Id do cliente inválido!", inMessage);
                }
                break;
            case Message.SHOULDHAVEARRIVEDEARLIER:
                if (inMessage.getStudentCount() < 0 || inMessage.getStudentCount() > Info.studentNumber) {
                    throw new MessageException("Id do cliente inválido!", inMessage);
                }
                break;
            case Message.HONORTHEBILL:
                break;
            case Message.EXIT:
                if (inMessage.getStudentCount() < 0 || inMessage.getStudentCount() > Info.studentNumber) {
                    throw new MessageException("Id do cliente inválido!", inMessage);
                }
                break;
            case Message.SHUTTABLE:                                                        // shutdown do servidor
                break;
            default:
                throw new MessageException("Tipo inválido!", inMessage);
        }

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
            case Message.SHUTTABLE:                                                        // shutdown do servidor
                TableMain.waitConnection = false;
                (((ClientProxy) (Thread.currentThread())).getScon()).setTimeout(10);
                outMessage = new Message(Message.SERVERACKNOWLEDGE);            // gerar confirmação
                break;

        }

        return (outMessage);
    }
}
