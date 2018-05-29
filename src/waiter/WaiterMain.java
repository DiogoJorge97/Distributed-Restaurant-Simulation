package waiter;

import stubs.KitchenStub;
import stubs.BarStub;
import stubs.TableStub;
import comInf.Info;

/**
 * Class responsible for instantiating all the objects used by the Student
 *
 * @author Diogo Jorge
 */
public class WaiterMain {

    /**
     * Waiter's main program .
     * @param args
     * @throws java.lang.InterruptedException if thread is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        String kitchenHostName = Info.kitchenHostName;
        int kitchenPortNumber = Info.kitchenPortNumber;
        String barHostName = Info.barHostName;
        int barPortNumber = Info.barPortNumber;
        String tableHostName = Info.tableHostName;
        int tablePortNumber = Info.tablePortNumber;

        System.out.println("Client Side - Waiter");

        KitchenStub kitchenStub = new KitchenStub(kitchenHostName, kitchenPortNumber);
        BarStub barStub = new BarStub(barHostName, barPortNumber);
        TableStub tableStub = new TableStub(tableHostName, tablePortNumber);
        Waiter waiter = new Waiter(kitchenStub, barStub, tableStub);

        waiter.start();
        waiter.join();
        barStub.shutdown();
        tableStub.shutdown();
        
    }
}
