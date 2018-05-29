package kitchen;

import semaphore.Semaphore;
import entities_states.Chef_State;
import entities_states.Waiter_State;
import java.io.IOException;
import stubs.BarStub;
import stubs.GeneralRepoStub;

/**
 * Defines the kitchen that constitutes the service provided
 * @author Diogo Jorge
 */
public class Kitchen {

    private boolean HasTheOrderBeenCompleted = false;
    private final Semaphore access;
    private final Semaphore chefWatchingTheNews;
    private final Semaphore chefWaitingForDelivery;
    private final Semaphore waiterWaitingForPortion;

    int coursesNumber = 3;
    int studentNumber = 7;
    BarStub barStub;
    private final GeneralRepoStub generalRepoStub;
    int PortionCounter;
    private int CourseCounter = 0;

    public Kitchen(int courseNumber, int studentNumber, BarStub barStub, GeneralRepoStub generalRepoStub) {
        this.coursesNumber = courseNumber;
        this.studentNumber = studentNumber;
        this.chefWatchingTheNews = new Semaphore();
        this.waiterWaitingForPortion = new Semaphore();
        this.barStub = barStub;
        this.PortionCounter = 0;
        this.chefWaitingForDelivery = new Semaphore();
        this.CourseCounter = 1;
        this.access = new Semaphore();
        access.up();
        this.generalRepoStub = generalRepoStub;
    }

    /**
     * Chef watches the news waiting for orders
     *
     * @throws IOException if the chef state can't be written to the logger file
     */
    public void WatchTheNews() throws IOException {
//        System.out.println("Kitchen     Chef        Watch the news");
        generalRepoStub.updateChefState(Chef_State.WFO);
        chefWatchingTheNews.down();
    }

    /**
     * Waiter hands order to the chef
     *
     * @throws IOException if the waiter state can't be written to the logger file
     */
    public void handTheNoteToTheChef() throws IOException {
        access.down();
//        System.out.println("Kitchen     Waiter      Hand the note to the chef");
        generalRepoStub.updateWaiterState(Waiter_State.PTO);
        chefWatchingTheNews.up();
        access.up();
    }

    /**
     * Chef starts preparing the first course
     *
     * @throws IOException if the chef state can't be written to the logger file
     */
    public void StartPreparation() throws IOException {
        access.down();
//        System.out.println("Kitchen     Chef        Start Course Preparation");
        generalRepoStub.updateChefState(Chef_State.PTC);
        generalRepoStub.updateCourse(1);
        //increase course counter in repo by 1
        access.up();
    }

    /**
     * Chef proceeds to dishing the portions
     *
     * @throws IOException if the chef state can't be written to the logger file
     */
    public void ProceedToPresentation() throws IOException {
        access.down();
//        System.out.println("Kitchen     Chef        Proceed to presentation");
        generalRepoStub.updateChefState(Chef_State.DIP);
        access.up();

    }

    /**
     * Chef alerts the waiter to deliver the dishes
     *
     * @param deliveredCount Counter of the dishes delivered by the chef to the waiter 
     * @throws IOException if the chef state can't be written to the logger file
     */
    public void AlertTheWaiter(int deliveredCount) throws IOException {
        access.down();
//        System.out.println("Kitchen     Chef        Alert the waiter");
        barStub.CallTheWaitertoServe();
        if (deliveredCount == 0) {
            barStub.waiterInTheBarUp();
//            System.out.println("Kitchen     Chef        Alert the waiter Up");
        }
        generalRepoStub.updateChefState(Chef_State.DLP);
        access.up();
        waiterWaitingForPortion.up();
    }

    /**
     * Waiter collects cooked portion
     *
     * @throws IOException if the waiter state can't be written to the logger file
     */
    public void collectPortion() throws IOException {
        waiterWaitingForPortion.down();
        access.down();
//        System.out.println("Kitchen     Waiter      Collect Portion " + PortionCounter);
        generalRepoStub.updateWaiterState(Waiter_State.WFP);
        PortionCounter++;
        access.up();
    }

    /**
     * Waiter informs chef that he has delivered the dish
     */
    public void chefWaitingForDeliveryUp() {
        chefWaitingForDelivery.up();
    }

    /**
     * Chef checks if all portions have been delivered
     *
     * @param StudentSize Number of students in the restaurant 
     * @return true if all portions have been delivered 
     */
    public boolean AllPortionsBeenDelivered(int StudentSize) {
        if (PortionCounter == StudentSize) {
//            System.out.println("Kitchen     Chef        All Portions Delivered");
            return true;
        }
        return false;
    }

    /**
     * Chef starts preparing next portion
     *
     * @throws IOException if the chef state can't be written to the logger file
     */
    public void HaveNextPortionReady() throws IOException {
        chefWaitingForDelivery.down();
        access.down();
        generalRepoStub.updateChefState(Chef_State.DIP);
//        System.out.println("Kitchen     Chef        Have Next Portion Ready");
        access.up();
    }

    /**
     * Chef prepares next course and increments courseCounter
     *
     * @param MaxRound Number of courses
     * @throws IOException if the chef state can't be written to the logger file
     */
    public void ContinuePreparation(int MaxRound) throws IOException {
        barStub.waitingForStudentsToFinishDown();
        access.down();
//        System.out.println("Kitchen     Chef        Continue Preparation");
        generalRepoStub.updateChefState(Chef_State.PTC);
        PortionCounter = 0;
        CourseCounter++;
        if (CourseCounter == MaxRound) {
            HasTheOrderBeenCompleted = true;
        }
        generalRepoStub.updateCourse(CourseCounter);
        access.up();
    }

    /**
     * Chef cleans up and gets ready to leave
     *
     * @throws IOException if the chef state can't be written to the logger file
     */
    public void cleanup() throws IOException {
        access.down();
//        System.out.println("Kitchen     Chef        Cleanup");
        generalRepoStub.updateChefState(Chef_State.CTS);
        access.up();

    }

    /**
     * Chef checks if order is completed
     *
     * @return true if the order has been completed 
     */
    public boolean HaveTheOrderBeenCompleted() {
        return HasTheOrderBeenCompleted;
    }
}
