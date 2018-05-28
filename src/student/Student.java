package student;

import entities_states.Student_State;
import stubs.BarStub;
import stubs.TableStub;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo Antão
 * @author Diogo Jorge
 */
public class Student extends Thread {

    //Student stats
    private final TableStub tableStub;
    private final BarStub barStub;
    private final int CoursesNumber;
    private final int StudentsNumber;
    private final int studentID;

    public Student(TableStub tableStub, BarStub barStub, int CoursesNumber, int StudentsNumber, int id) {
        this.tableStub = tableStub;
        this.barStub = barStub;
        this.CoursesNumber = CoursesNumber;
        this.StudentsNumber = StudentsNumber;
        this.studentID = id;
    }

    @Override
    public void run() {
        try {
            WalkABit();
        } catch (IOException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        int arrivalOrder;
        System.out.println("WHY");
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

    private void WalkABit() throws IOException {
        try {
            Thread.sleep((long) ((Math.random() * 1500) + 1000));
        } catch (InterruptedException ex) {
        }

    }
}
