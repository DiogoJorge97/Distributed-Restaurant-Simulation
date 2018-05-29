package kitchen;

import java.net.SocketTimeoutException;
import stubs.BarStub;
import comInf.Info;
import stubs.GeneralRepoStub;

/**
 * Class responsible for instantiating all the objects used by the Kitchen
 * (server side)
 *
 * @author Diogo Jorge
 */
public class KitchenMain {

    /**
     * Server port number
     *
     * @serialField portNumb
     */
    private static final int portNumb = Info.kitchenPortNumber;
    
    /**
     * Boolean value that represents the connection state
     */
    public static boolean waitConnection;                              // sinalização de actividade

    /**
     * Kitchen's main program .
     */
    public static void main(String[] args) {

        int coursesNumber = Info.coursesNumber;
        int studentNumber = Info.studentNumber;
        String barHostName = Info.barHostName;
        int barPortNumber = Info.barPortNumber;
        String generalRepoHostName = Info.generalRepoHostName;
        int generalRepoPortNumber = Info.generalRepoPortNumber;

        Kitchen kitchen;
        KitchenInterface kitchenInterface;
        ServerCom scon, sconi;                               // canais de comunicação
        ClientProxy cliProxy;
        GeneralRepoStub generalRepoStub = new GeneralRepoStub(generalRepoHostName, generalRepoPortNumber);

        // thread agente prestador do serviço
        System.out.println("Server Side - Kitchen");

        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                     // criação do canal de escuta e sua associação
        scon.start();                                       // com o endereço público
        BarStub barStub = new BarStub(barHostName, barPortNumber);
        kitchen = new Kitchen(coursesNumber, studentNumber, barStub, generalRepoStub);
        kitchenInterface = new KitchenInterface(kitchen);
        System.out.println("O serviço foi estabelecido!");
        System.out.println("O servidor esta em escuta.");

        /* processamento de pedidos */
        waitConnection = true;
        while (waitConnection) {
            try {
                sconi = scon.accept();                          // entrada em processo de escuta
                cliProxy = new ClientProxy(sconi, kitchenInterface);  // lançamento do agente prestador do serviço
                cliProxy.start();
            } catch (SocketTimeoutException e) {
            }
        }
        scon.end();                                         // terminação de operações
        System.out.println("O servidor foi desactivado.");
        generalRepoStub.shutdown();
    }
}
