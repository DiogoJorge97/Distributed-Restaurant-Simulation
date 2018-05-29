package student;

import stubs.BarStub;
import stubs.TableStub;


/**
 * Defines the student that constitutes the service provided
 *
 * @author Diogo Jorge
 */
public class Student extends Thread {

    //Student stats
    private final TableStub tableStub;
    private final BarStub barStub;
    private final int CoursesNumber;
    private final int StudentsNumber;
    private final int studentID;

    /**
     * Student's constructor
     *
     * @param tableStub table stub
     * @param barStub bar stub
     * @param CoursesNumber number of courses
     * @param StudentsNumber number of students in the restaurant
     * @param id student's identifier
     */
    public Student(TableStub tableStub, BarStub barStub, int CoursesNumber, int StudentsNumber, int id) {
        this.tableStub = tableStub;
        this.barStub = barStub;
        this.CoursesNumber = CoursesNumber;
        this.StudentsNumber = StudentsNumber;
        this.studentID = id;
    }

    /**
     * Student's run life
     */
    @Override
    public void run() {
        WalkABit();
        int arrivalOrder;
        arrivalOrder = tableStub.enter(studentID);
        tableStub.readTheMenu(studentID);
        if (arrivalOrder == 1) {
            while (!tableStub.hasEverybodyChosen()) {
                tableStub.prepareTheOrder(studentID);
            }
            barStub.callTheWaiter();
            tableStub.describeTheOrder();
            tableStub.joinTheTalk(studentID);
        } else {
            tableStub.informCompanion(studentID);
        }

        for (int i = 0; i < CoursesNumber; i++) {
            tableStub.startEating(studentID);
            tableStub.endEating(studentID);
            if (tableStub.hasEverybodyFinished(i)) {
                if (i != (CoursesNumber - 1)) {
                    barStub.SignalTheWaiter();
                }
            }
        }
        if (arrivalOrder == (StudentsNumber)) {
            tableStub.shouldHaveArrivedEarlier(studentID);
            tableStub.honorTheBill();
        }
        tableStub.exit(studentID);

    }

    /**
     *  Student walks to the restaurant
     */
    private void WalkABit() {
        try {
            Thread.sleep((long) ((Math.random() * 1500) + 1000));
        } catch (InterruptedException ex) {
        }

    }
}
