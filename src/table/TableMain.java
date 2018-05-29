package table;

import comInf.Info;
import java.net.SocketTimeoutException;
import stubs.BarStub;
import stubs.GeneralRepoStub;
import stubs.KitchenStub;

/**
 * Class responsible for instantiating all the objects used by the Table (server
 * side)
 *
 * @author Diogo Jorge
 */
public class TableMain {

    /**
     * Server port number
     *
     * @serialField portNumb
     */
    private static final int portNumb = Info.tablePortNumber;
    
    /**
     * Boolean value that represents the connection state
     */
    public static boolean waitConnection;                              // sinalização de actividade

    /**
     *  Table's main program .
     */
    public static void main(String[] args) {

        int coursesNumber = Info.coursesNumber;
        int studentNumber = Info.studentNumber;

        String barHostName = Info.barHostName;
        int barPortNumber = Info.barPortNumber;
        String kitchenHostName = Info.kitchenHostName;
        int kitchenPortNumber = Info.kitchenPortNumber;
        String generalRepoHostName = Info.generalRepoHostName;
        int generalRepoPortNumber = Info.generalRepoPortNumber;

        Table table;
        TableInterface tableInterface;
        BarStub barStub;
        KitchenStub kitchenStub;
        ServerCom scon, sconi;                               // canais de comunicação
        ClientProxy cliProxy;                                // thread agente prestador do serviço

        System.out.println("Server Side - Table");

        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                     // criação do canal de escuta e sua associação
        scon.start();                                       // com o endereço público
        kitchenStub = new KitchenStub(kitchenHostName, kitchenPortNumber);
        barStub = new BarStub(barHostName, barPortNumber);
        GeneralRepoStub generalRepoStub = new GeneralRepoStub(generalRepoHostName, generalRepoPortNumber);
        table = new Table(studentNumber, coursesNumber, barStub, kitchenStub, generalRepoStub);
        tableInterface = new TableInterface(table);
        System.out.println("O serviço foi estabelecido!");
        System.out.println("O servidor esta em escuta.");

        /* processamento de pedidos */
        waitConnection = true;
        while (waitConnection) {
            try {
                sconi = scon.accept();                          // entrada em processo de escuta
                cliProxy = new ClientProxy(sconi, tableInterface);  // lançamento do agente prestador do serviço
                cliProxy.start();
            } catch (SocketTimeoutException e) {
            }
        }
        scon.end();                                         // terminação de operações
        System.out.println("O servidor foi desactivado.");
    }
}
