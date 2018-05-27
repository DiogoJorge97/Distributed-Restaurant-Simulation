package table;

import comInf.Info;
import kitchen.*;
import genclass.GenericIO;
import java.net.SocketTimeoutException;
import stubs.BarStub;
import stubs.GeneralRepoStub;
import stubs.KitchenStub;

/**
 * Este tipo de dados simula uma solução do lado do servidor do Problema dos
 * Barbeiros Sonolentos que implementa o modelo cliente-servidor de tipo 2
 * (replicação do servidor) com lançamento estático dos threads barbeiro. A
 * comunicação baseia-se em passagem de mensagens sobre sockets usando o
 * protocolo TCP.
 */
public class TableMain {

    /**
     * Número do port de escuta do serviço a ser prestado (4000, por defeito)
     *
     * @serialField portNumb
     */
    private static final int portNumb = 22674;
    public static boolean waitConnection;                              // sinalização de actividade

    /**
     * Programa principal.
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
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

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
        GenericIO.writelnString("O servidor foi desactivado.");
    }
}
