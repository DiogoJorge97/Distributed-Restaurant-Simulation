package generalRepository;

import comInf.Message;
import comInf.MessageException;
import java.io.IOException;

/**
 * Este tipo de dados define o interface à barbearia numa solução do Problema
 * dos Barbeiros Sonolentos que implementa o modelo cliente-servidor de tipo 2
 * (replicação do servidor) com lançamento estático dos threads barbeiro.
 */
public class GeneralRepoInterface {

    /**
     * Barbearia (representa o serviço a ser prestado)
     *
     * @serialField bShop
     */
    private GeneralRepo generalRepo;

    public GeneralRepoInterface(GeneralRepo generalRepo) {
        this.generalRepo = generalRepo;
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
