package generalRepository;

import java.net.SocketTimeoutException;
import comInf.Info;
import java.io.IOException;

/**
 * Class responsible for instantiating all the objects used by the General
 * Repository (server side)
 *
 * @author Diogo Jorge
 */
public class GeneralRepoMain {

    /**
     * Server port number
     *
     * @serialField portNumb
     */
    private static final int portNumb = Info.generalRepoPortNumber;

    /**
     * Boolean value that represents the connection state
     */
    public static boolean waitConnection;                              // sinalização de actividade

    /**
     * General Repository's main program .
     * @param args
     * @throws java.io.IOException if file is not found
     */
    public static void main(String[] args) throws IOException {

        int coursesNumber = Info.coursesNumber;
        int studentNumber = Info.studentNumber;
        String generalRepoHostName = Info.generalRepoHostName;
        int generalRepoPortNumber = Info.generalRepoPortNumber;

        GeneralRepo generalRepo;
        GeneralRepoInterface generalRepoInterface;
        ServerCom scon, sconi;                               // canais de comunicação
        ClientProxy cliProxy;

        // thread agente prestador do serviço
        System.out.println("Server Side - GeneralRepo");

        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                     // criação do canal de escuta e sua associação
        scon.start();                                       // com o endereço público
        generalRepo = new GeneralRepo(Info.filename, generalRepoHostName, generalRepoPortNumber);
        generalRepoInterface = new GeneralRepoInterface(generalRepo);
        System.out.println("O serviço foi estabelecido!");
        System.out.println("O servidor esta em escuta.");

        generalRepo.initFile(Info.filename);

        /* processamento de pedidos */
        waitConnection = true;
        while (waitConnection) {
            try {
                sconi = scon.accept();                          // entrada em processo de escuta
                cliProxy = new ClientProxy(sconi, generalRepoInterface);  // lançamento do agente prestador do serviço
                cliProxy.start();
            } catch (SocketTimeoutException e) {
            }
        }
        scon.end();                                         // terminação de operações
        System.out.println("O servidor foi desactivado.");
    }
}
