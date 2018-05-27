package waiter;

import stubs.KitchenStub;
import stubs.BarStub;
import stubs.TableStub;


/**
 *
 * @author Ricardo Antão
 * @author Diogo Jorge
 */
public class Waiter extends Thread {

    private final KitchenStub kitchenStub;
    private final BarStub barStub;
    private final TableStub tableStub;

    public Waiter(KitchenStub kitchenStub, BarStub barStub, TableStub tableStub) {
        this.kitchenStub = kitchenStub;
        this.barStub = barStub;
        this.tableStub = tableStub;
    }

    @Override
    public void run() {
        char alt;
        while ((alt = barStub.lookAround()) != 'e') {
            switch (alt) {
                case 'n':
                    tableStub.saluteTheClient();
                    break;
                case 'o':
                    tableStub.getThePad();
                    kitchenStub.handTheNoteToTheChef();
                    break;
                case 'c':
                    do {
                        kitchenStub.collectPortion();
                        tableStub.deliverPortion();
                    } while (!tableStub.haveAllClientsBeenServed());
                    tableStub.resetDeliveredCounter();
                    break;
                case 'p':
                    barStub.prepareTheBill();
                    tableStub.presentTheBill();
                    break;
                case 'g':
                    tableStub.sayGoodbye();
                    break;
            }
            if (alt != 'g') {
                barStub.returnToTheBar();
            }
        }

    }
}
