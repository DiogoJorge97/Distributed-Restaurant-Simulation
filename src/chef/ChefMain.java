package chef;

import stubs.KitchenStub;
import genclass.GenericIO;

/**
 * Este tipo de dados simula uma solução do lado do cliente do Problema dos
 * Barbeiros Sonolentos que implementa o modelo cliente-servidor de tipo 2
 * (replicação do servidor) com lançamento estático dos threads barbeiro. A
 * comunicação baseia-se em passagem de mensagens sobre sockets usando o
 * protocolo TCP.
 */
public class ChefMain {

    public static void main(String[] args) throws InterruptedException {
        int coursesNumber = 3;
        int studentNumber = 7;
        String serverHostName = "localhost";
        int serverPortNumber = 22676;

        System.out.println("Client Side - Chef");

        KitchenStub kitchenStub = new KitchenStub(serverHostName, serverPortNumber);
        Chef chef = new Chef(kitchenStub, coursesNumber, studentNumber);

        chef.start();
        chef.join();
        kitchenStub.shutdown();
    }
}
