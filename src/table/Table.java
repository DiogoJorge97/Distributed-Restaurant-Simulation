package table;

import entities_states.Student_State;
import semaphore.Semaphore;
import entities_states.Waiter_State;
import java.io.IOException;
import stubs.BarStub;
import stubs.KitchenStub;
import comInf.Info;
import stubs.GeneralRepoStub;

/**
 * Defines the table that constitutes the service provided
 * @author Diogo Jorge
 */
public class Table {

    private final Semaphore access;
    private final Semaphore studentsWaitingForSalute;
    private final Semaphore waiterPresentTheMenu;
    private final Semaphore studentChatting;
    private final Semaphore studentOrderConfirmation;
    private final Semaphore studentWaitingForBill;
    private final Semaphore waiterWaitingForPayment;
    private final Semaphore waitingForFriendsToBeServed;
    private final Semaphore waitingForFriends;
    private final Semaphore getReadyToPay;
    private final Semaphore preparingTheOrder;
    private final Semaphore waitingforgoodbye;
    private int exitCount;

    private final Semaphore getPadReady;
    private int studentCount, readyCounter, deliveredCounter, MaxStudents, MCourses;
    private GeneralRepoStub generalRepoStub;
    private int coursesNumber;
    private int studentNumber;
    private BarStub barStub;
    private KitchenStub kitchenStub;

    Table(int studentNumber, int coursesNumber, BarStub barStub, KitchenStub kitchenStub, GeneralRepoStub generalRepoStub) {
        this.coursesNumber = coursesNumber;
        this.studentNumber = studentNumber;
        this.barStub = barStub;
        this.kitchenStub = kitchenStub;
        this.deliveredCounter = 0;
        this.waitingForFriends = new Semaphore();
        this.studentCount = 0;
        this.readyCounter = 0;
        this.exitCount = 0;
        this.waitingForFriendsToBeServed = new Semaphore();
        this.access = new Semaphore();
        this.getReadyToPay = new Semaphore();
        access.up();
        this.waiterPresentTheMenu = new Semaphore();
        this.studentChatting = new Semaphore();
        this.studentOrderConfirmation = new Semaphore();
        this.studentWaitingForBill = new Semaphore();
        this.waiterWaitingForPayment = new Semaphore();
        this.getPadReady = new Semaphore();
        this.preparingTheOrder = new Semaphore();
        this.studentsWaitingForSalute = new Semaphore();
        this.waitingforgoodbye = new Semaphore();
        this.MaxStudents = Info.studentNumber;
        MCourses = Info.coursesNumber;
        this.generalRepoStub = generalRepoStub;
    }

    /**
     * Student enters the restaurant
     *
     * @param StudentId
     * @return Count of students o entered the restaurant 
     * @throws IOException if the student state can't be written to the logger file
     */
    public int enter(int StudentId) throws IOException {
        access.down();
//        System.out.println("Table       Student     " + studentCount + " Student enters");
        barStub.alertWaiterEntering();
        generalRepoStub.updateStudentState(Student_State.TST, StudentId);
        access.up();
        studentsWaitingForSalute.down();
        return ++studentCount;
    }

    /**
     * Waiter salutes the client that has just entered
     * @throws IOException if the waiter state can't be written to the logger file 
     */
    public void saluteTheClient() throws IOException {
        access.down();
//        System.out.println("Table       Waiter      Salute The Client");
        generalRepoStub.updateWaiterState(Waiter_State.PTM);
        studentsWaitingForSalute.up();
        access.up();
        waiterPresentTheMenu.down();
    }

    /**
     * Student reads the menu
     *
     * @param StudentId Student Identifier
     * @throws IOException if the student state can't be written to the logger file
     */
    public void readTheMenu(int StudentId) throws IOException {
        access.down();
        generalRepoStub.updateStudentState(Student_State.STC, StudentId);
//        System.out.println("Table       Student     starts reading");
        waiterPresentTheMenu.up();
        access.up();

    }

    /**
     * First student to arrive checks if every student has chosen his order
     *
     * @return true if everybody has finished, false if not
     */
    public boolean hasEverybodyChosen() {
        return (readyCounter == MaxStudents - 1);
    }

    /**
     * First student to arrive waits for other students to read the menu
     *
     * @param StudentId Student identifier
     * @throws IOException if the student state can't be written to the logger file
     */
    public void prepareTheOrder(int StudentId) throws IOException {
        preparingTheOrder.down();
        access.down();
        generalRepoStub.updateStudentState(Student_State.OTO, StudentId);
//        System.out.println("Table       Student     " + "Prepare the order");
        access.up();
    }

    /**
     * Student x informs first student to arrive that he has chosen the menu
     *
     * @param StudentId Student identifier
     * @throws IOException if the student state can't be written to the logger file
     */
    public void informCompanion(int StudentId) throws IOException {
        access.down();
        readyCounter++;
        preparingTheOrder.up();
//        System.out.println("Table       Student     " + readyCounter + " Students ready readyCounter" + readyCounter);
        generalRepoStub.updateStudentState(Student_State.CWC, StudentId);
        access.up();
    }

    /**
     * Waiter gets the pad to take order
     *
     * @throws IOException if the waiter state can't be written to the logger file
     */
    public void getThePad() throws IOException {
        access.down();
//        System.out.println("Table       Waiter      Get The Pad");
        generalRepoStub.updateWaiterState(Waiter_State.TTO);
        access.up();
        getPadReady.up();
        studentOrderConfirmation.down();

    }

    /**
     * First student to arrive describes the order to the waiter
     */
    public void describeTheOrder() {
        getPadReady.down();
//        System.out.println("Table       Student     Student Describes The Order");
        studentOrderConfirmation.up();

    }

    /**
     * First student to arrive joins the talk
     *
     * @param StudentId Student identifier
     * @throws IOException if the student state can't be written to the logger file
     */
    public void joinTheTalk(int StudentId) throws IOException {
        access.down();
        access.up();
//        System.out.println("Table       Student     Student joins the talk");
        resetReadyCounter();
        generalRepoStub.updateStudentState(Student_State.CWC, StudentId);

    }

    /**
     * Waiter delivers one portion to table
     */
    public void deliverPortion() {
        access.down();
//        System.out.println("Table       Waiter      Deliver Portion " + deliveredCounter);
        studentChatting.up();
        deliveredCounter++;
        kitchenStub.chefWaitingForDeliveryUp();
        access.up();
    }

    /**
     * Student x starts eating
     *
     * @param StudentId Student identifier
     * @throws IOException if the student state can't be written to the logger file
     * @throws java.lang.InterruptedException if the thread is interrupted during the sleep time
     */
    public void startEating(int StudentId) throws IOException, InterruptedException {
        waitingForFriendsToBeServed.down();
        studentChatting.down();
        access.down();
//        System.out.println("Table       Student     Starts eating " + StudentId);
        generalRepoStub.updateStudentState(Student_State.ETM, StudentId);
        access.up();
        Thread.sleep(500);
    }

    /**
     * Student x ends eating
     *
     * @param StudentId Student identifier
     * @throws IOException if the student state can't be written to the logger file
     */
    public void endEating(int StudentId) throws IOException {
        access.down();
        generalRepoStub.updateStudentState(Student_State.CWC, StudentId);     
        readyCounter++;
//        System.out.println("Table       Student     End eating " + StudentId + " Ready Counter " + readyCounter);
        access.up();
    }

    /**
     * Students x check if everybody finished eating
     *
     * @param currentCourse
     * @return true if everybody has finished eating, false if not
     */
    public boolean hasEverybodyFinished(int currentCourse) {
        access.down();
        if ((readyCounter == MaxStudents)) {
//            System.out.println("Table       Student     Everybody Finished Current Course " + currentCourse);
            resetReadyCounter();
            if (currentCourse == (MCourses - 1)) {
                getReadyToPay.up();
            }
            access.up();
            return true;
        }
        access.up();
        return false;
    }

    /**
     * Last Student to arrive informs water he is ready to pay
     *
     * @param StudentId Student identifier
     * @throws IOException if the student state can't be written to the logger file
     */
    public void shouldHaveArrivedEarlier(int StudentId) throws IOException {
        getReadyToPay.down();
        access.down();
        generalRepoStub.updateCourse(0);
//        System.out.println("Table       Student     Should Have Arrived Earlier " + StudentId);
        barStub.PayTheWaiter();
        generalRepoStub.updateStudentState(Student_State.PTB, StudentId);
        access.up();
        studentWaitingForBill.down();

    }

    /**
     * Last student to arrive pays the bill
     */
    public void honorTheBill() {
        access.down();
//        System.out.println("Table       Student     Honor The Bill");
        access.up();
        waiterWaitingForPayment.up();
        for (int i = 0; i < MaxStudents; i++) {
            waitingForFriends.up();
        }
    }

    /**
     * Student x exits the restaurant
     *
     * @param StudentId Student identifier
     * @throws IOException if the student state can't be written to the logger file
     */
    public void exit(int StudentId) throws IOException {
        waitingForFriends.down();
        access.down();
//        System.out.println("Table       Student     Exit " + StudentId);
        barStub.alertTheWaiterExit();
        waitingforgoodbye.down();
        generalRepoStub.updateStudentState(Student_State.SGH, StudentId);
        exitCount++;
        if (exitCount == MaxStudents) {
            barStub.waiterGoHome();
        }
        access.up();
    }

    /**
     * Waiter checks if all clients have been served
     *
     * @return true if all clients have been served, false if not
     */
    public boolean haveAllClientsBeenServed() {
        access.down();
        if (deliveredCounter == MaxStudents) {
            for (int k = 0; k < MaxStudents; k++) {
                waitingForFriendsToBeServed.up();
            }
            access.up();
            return true;
        }
        access.up();
        return false;
    }

    /**
     * Waiter presents the bill
     *
     * @throws IOException if the student waiter can't be written to the logger file
     */
    public void presentTheBill() throws IOException {
        access.down();
//        System.out.println("Table       Waiter      Present The Bill");
        generalRepoStub.updateWaiterState(Waiter_State.RTP);
        access.up();
        studentWaitingForBill.up();
        waiterWaitingForPayment.down();

    }

    /**
     * Waiter says goodbye to student
     */
    public void sayGoodbye() {
//        System.out.println("Table       Waiter      Say Goodbye");
        waitingforgoodbye.up();
    }

    /**
     * Resets the number of students that are ready
     */
    public void resetReadyCounter() {
        readyCounter = 0;
    }

    /**
     * Resets the number of dishes that have been served
     */
    public void resetDeliveredCounter() {
        deliveredCounter = 0;
    }

}
