package kitchen;

import genclass.GenericIO;
import java.net.SocketTimeoutException;
import stubs.BarStub;
import comInf.Info;

/**
 * Este tipo de dados simula uma solução do lado do servidor do Problema dos
 * Barbeiros Sonolentos que implementa o modelo cliente-servidor de tipo 2
 * (replicação do servidor) com lançamento estático dos threads barbeiro. A
 * comunicação baseia-se em passagem de mensagens sobre sockets usando o
 * protocolo TCP.
 */
public class KitchenMain {
    private static final int portNumb = Info.kitchenPortNumber;
    public static boolean waitConnection;                              // sinalização de actividade

    /**
     * Programa principal.
     */
    public static void main(String[] args) {

        int coursesNumber = Info.coursesNumber;
        int studentNumber = Info.studentNumber;
        String barHostName = Info.barHostName;
        int barPortNumber = Info.barPortNumber;

        Kitchen kitchen;
        KitchenInterface kitchenInterface;
        ServerCom scon, sconi;                               // canais de comunicação
        ClientProxy cliProxy;

        // thread agente prestador do serviço

        /* estabelecimento do servico */
        scon = new ServerCom(portNumb);                     // criação do canal de escuta e sua associação
        scon.start();                                       // com o endereço público
        BarStub barStub = new BarStub(barHostName, barPortNumber);
        kitchen = new Kitchen(coursesNumber, studentNumber, barStub);
        kitchenInterface = new KitchenInterface(kitchen);
        GenericIO.writelnString("O serviço foi estabelecido!");
        GenericIO.writelnString("O servidor esta em escuta.");

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
        GenericIO.writelnString("O servidor foi desactivado.");
    }
}
