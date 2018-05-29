package bar;

import comInf.Info;
import java.net.SocketTimeoutException;
import stubs.GeneralRepoStub;

/**
 * Class responsible for instantiating all the objects used by the Bar (server side)
 * @author Diogo Jorge
 */
public class BarMain {

    /**
     * Server port number
     *
     * @serialField portNumb
     */
    private static final int portNumb = Info.barPortNumber;

    /**
     * Boolean value that represents the connection state
     */
    public static boolean waitConnection;                              // sinalização de actividade

    /**
     * Bar's main program .
     */
    public static void main(String[] args) {

        int coursesNumber = Info.coursesNumber;
        int studentNumber = Info.studentNumber;
        String generalRepoHostName = Info.generalRepoHostName;
        int generalRepoPortNumber = Info.generalRepoPortNumber;

        Bar bar;
        BarInterface barInterface;
        ServerCom scon, sconi;                               // canais de comunicação
        ClientProxy cliProxy;                                // thread agente prestador do serviço
        GeneralRepoStub generalRepoStub = new GeneralRepoStub(generalRepoHostName, generalRepoPortNumber);

        System.out.println("Server Side - Bar");

        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                     // criação do canal de escuta e sua associação
        scon.start();                                       // com o endereço público
        bar = new Bar(studentNumber, generalRepoStub);
        barInterface = new BarInterface(bar);
        System.out.println("O serviço foi estabelecido!");
        System.out.println("O servidor esta em escuta.");

        /* processamento de pedidos */
        waitConnection = true;
        while (waitConnection) {
            try {
                sconi = scon.accept();                          // entrada em processo de escuta
                cliProxy = new ClientProxy(sconi, barInterface);  // lançamento do agente prestador do serviço
                cliProxy.start();
            } catch (SocketTimeoutException e) {
            }
        }
        scon.end();                                         // terminação de operações
        System.out.println("O servidor foi desactivado.");
    }
}
