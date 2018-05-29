package student;

import comInf.Info;
import stubs.BarStub;
import stubs.TableStub;

/**
 * Class responsible for instantiating all the objects used by the Student
 *
 * @author Diogo Jorge
 */
public class StudentMain {

    /**
     * Student's main program .
     * @param args
     * @throws java.lang.InterruptedException if thread is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        int coursesNumber = Info.coursesNumber;
        int studentNumber = Info.studentNumber;
        String barHostName = Info.barHostName;
        int barPortNumber = Info.barPortNumber;
        String tableHostName = Info.tableHostName;
        int tablePortNumber = Info.tablePortNumber;

        System.out.println("Client Side - Students");

        BarStub barStub = new BarStub(barHostName, barPortNumber);
        TableStub tableStub = new TableStub(tableHostName, tablePortNumber);

        Student[] student = new Student[studentNumber];
        for (int id = 0; id < studentNumber; id++) {
            student[id] = new Student(tableStub, barStub, coursesNumber, studentNumber, id);
        }

        for (int i = 0; i < studentNumber; i++) {
            student[i].start();
        }
        for (int i = 0; i < studentNumber; i++) {
            student[i].join();
        }
    }
}
