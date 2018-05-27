package generalRepository;

import kitchen.*;
import genclass.GenericIO;
import java.net.SocketTimeoutException;
import stubs.BarStub;
import comInf.Info;
import java.io.IOException;

/**
 * Este tipo de dados simula uma solução do lado do servidor do Problema dos
 * Barbeiros Sonolentos que implementa o modelo cliente-servidor de tipo 2
 * (replicação do servidor) com lançamento estático dos threads barbeiro. A
 * comunicação baseia-se em passagem de mensagens sobre sockets usando o
 * protocolo TCP.
 */
public class GeneralRepoMain {
    private static final int portNumb = Info.generalRepoPortNumber;
    public static boolean waitConnection;                              // sinalização de actividade

    /**
     * Programa principal.
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
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

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
        GenericIO.writelnString("O servidor foi desactivado.");
    }
}
