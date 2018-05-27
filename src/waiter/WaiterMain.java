package waiter;

import stubs.BarStub;
import stubs.TableStub;
import stubs.KitchenStub;
import stubs.BarStub;
import stubs.TableStub;
import genclass.GenericIO;

/**
 * Este tipo de dados simula uma solução do lado do cliente do Problema dos
 * Barbeiros Sonolentos que implementa o modelo cliente-servidor de tipo 2
 * (replicação do servidor) com lançamento estático dos threads barbeiro. A
 * comunicação baseia-se em passagem de mensagens sobre sockets usando o
 * protocolo TCP.
 */
public class WaiterMain {

    /**
     * Programa principal.
     */
    public static void main(String[] args) throws InterruptedException {
        int coursesNumber = 3;
        int studentNumber = 7;
        String kitchenHostName = "localhost";
        int kitchenPortNumber = 22676;
        String barHostName = "localhost";
        int barPortNumber = 22675;
        String tableHostName = "localhost";
        int tablePortNumber = 22674;

        KitchenStub kitchenStub = new KitchenStub(kitchenHostName, kitchenPortNumber);
        BarStub barStub = new BarStub(barHostName, barPortNumber);
        TableStub tableStub = new TableStub(tableHostName, tablePortNumber);
        Waiter waiter = new Waiter(kitchenStub, barStub, tableStub);

        waiter.start();
        waiter.join();
    }
}
