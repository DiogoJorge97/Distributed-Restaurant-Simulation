package generalRepository;

import entities_states.Chef_State;
import entities_states.Student_State;
import entities_states.Waiter_State;
import comInf.Message;
import comInf.MessageException;
import java.io.IOException;

/**
 * Class responsible for decoding the messages received from the client side of
 * the problem and instantiate the proper methods on the GeneralRepo (server
 * side)
 *
 */
public class GeneralRepoInterface {

    /**
     * GeneralRepo (represents the service)
     *
     * @serialField generalRepo
     */
    private GeneralRepo generalRepo;

    /**
     * Create an instance of the kitchen interface.
     *
     * @param generalRepo GeneralRepo
     */
    public GeneralRepoInterface(GeneralRepo generalRepo) {
        this.generalRepo = generalRepo;
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
            case Message.UPDATEWAITERSTATE:
                if (!(inMessage.getWaiter() instanceof Waiter_State)) {
                    throw new MessageException("Estado invalido!", inMessage);
                }
                break;
            case Message.UPDATECHEFSTATE:
                if (!(inMessage.getChef() instanceof Chef_State)) {
                    throw new MessageException("Estado invalido!", inMessage);
                }
                break;
            case Message.UPDATESTUDENTSTATE:
                if (!(inMessage.getStudent() instanceof Student_State) || inMessage.getStudentCount() == -1) {
                    throw new MessageException("Estado ou Numero invalido!", inMessage);
                }
                break;
            case Message.UPDATECOURSE:
                if (inMessage.getStudentCount() == -1) {
                    throw new MessageException("Numero invalido!", inMessage);
                }
                break;
            case Message.SHUTGENERALREPO:
                break;
            default:
                throw new MessageException("Tipo inválido!", inMessage);
        }


        /* seu processamento */
        switch (inMessage.getType()) {
            case Message.UPDATEWAITERSTATE:
                generalRepo.updateWaiterState(inMessage.getWaiter());
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.UPDATECHEFSTATE:
                generalRepo.updateChefState(inMessage.getChef());
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;
            case Message.UPDATESTUDENTSTATE:
                generalRepo.updateStudentState(inMessage.getStudent(), inMessage.getStudentCount());
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;

            case Message.UPDATECOURSE:
                generalRepo.updateCourse(inMessage.getStudentCount());
                outMessage = new Message(Message.SERVERACKNOWLEDGE);
                break;

            case Message.SHUTGENERALREPO:                                                        // shutdown do servidor
                generalRepo.closeWriter();
                GeneralRepoMain.waitConnection = false;
                (((ClientProxy) (Thread.currentThread())).getScon()).setTimeout(10);
                outMessage = new Message(Message.SERVERACKNOWLEDGE);            // gerar confirmação
                break;
        }

        return (outMessage);
    }
}
