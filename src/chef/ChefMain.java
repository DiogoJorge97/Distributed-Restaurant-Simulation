package chef;

import stubs.KitchenStub;

/**
 * Class responsible for instantiating all the objects used by the Chef
 *
 * @author Diogo Jorge
 */
public class ChefMain {

    /**
     * Chef's main program .
     * @param args
     * @throws java.lang.InterruptedException if thread is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        int coursesNumber = 3;
        int studentNumber = 7;
        String serverHostName = comInf.Info.kitchenHostName;
        int serverPortNumber = comInf.Info.kitchenPortNumber;

        System.out.println("Client Side - Chef");

        KitchenStub kitchenStub = new KitchenStub(serverHostName, serverPortNumber);
        Chef chef = new Chef(kitchenStub, coursesNumber, studentNumber);

        chef.start();
        chef.join();
        kitchenStub.shutdown();
    }
}
