package chef;

import stubs.KitchenStub;

/**
 *
 * @author Ricardo Antão
 * @author Diogo Jorge
 */
public class Chef extends Thread {

    private final KitchenStub kitchenStub;
    private final int MaxRound;
    private final int StudentNumber;

    public Chef(KitchenStub kitchenStub, int MaxRound, int StudentNumber) {
        this.kitchenStub = kitchenStub;
        this.MaxRound = MaxRound;
        this.StudentNumber = StudentNumber;
    }

    @Override
    public void run() {

        kitchenStub.WatchTheNews();
        kitchenStub.StartPreparation();
        for (int rounds = 0; rounds < MaxRound; rounds++) {
            kitchenStub.ProceedToPresentation();
            for (int st = 0; st < StudentNumber; st++) {
                kitchenStub.AlertTheWaiter(st);
                if (!kitchenStub.AllPortionsBeenDelivered(StudentNumber)) {
                    kitchenStub.haveNextPortionReady();
                }
            }
            if (!kitchenStub.HaveTheOrderBeenCompleted()) {
                kitchenStub.ContinuePreparation(MaxRound);
            }
        }
        kitchenStub.cleanup();
    }
}

