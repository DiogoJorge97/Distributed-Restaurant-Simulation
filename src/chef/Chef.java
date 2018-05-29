package chef;

import stubs.KitchenStub;

/**
 * Defines the chef that constitutes the service provided
 * @author Diogo Jorge
 */
public class Chef extends Thread {

    private final KitchenStub kitchenStub;
    private final int MaxRound;
    private final int StudentNumber;

    /**
     * Chef's constructor
     * @param kitchenStub kitchen stub
     * @param MaxRound number of courses
     * @param StudentNumber  number of students in the restaurant
     */
    public Chef(KitchenStub kitchenStub, int MaxRound, int StudentNumber) {
        this.kitchenStub = kitchenStub;
        this.MaxRound = MaxRound;
        this.StudentNumber = StudentNumber;
    }

    /**
     * Chef's run life
     */
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

