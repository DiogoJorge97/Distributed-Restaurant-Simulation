package generalRepository;

import entities_states.Chef_State;
import static entities_states.Chef_State.WFO;
import entities_states.Student_State;
import static entities_states.Student_State.GTR;
import entities_states.Waiter_State;
import static entities_states.Waiter_State.ATS;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import semaphore.Semaphore;

/**
 * Create generalRepo instantiation
 *
 * @author Diogo Jorge
 */
public final class GeneralRepo {

    private int courseCounter;
    private int courseCounterMinusOne;
    private String filename;
    private PrintWriter writer;
    private Chef_State chefstate;
    private Waiter_State waiterstate;
    Student_State[] studentstates = {GTR, GTR, GTR, GTR, GTR, GTR, GTR};
    private String line;
    private String lineMinusOne;
    private final Semaphore access;
    private final Semaphore flag;

    BufferedWriter bw = null;
    FileWriter fw = null;
    String generalRepoHostName;
    int generalRepoPortNumber;

    /**
     * GeneralRepo constructor
     *
     * @param filename filename
     * @param generalRepoHostName generalRepo host name
     * @param generalRepoPortNumber generalRepo port number
     * @throws IOException
     */
    public GeneralRepo(String filename, String generalRepoHostName, int generalRepoPortNumber) throws IOException {
        //inicial states of entities
        waiterstate = ATS;
        chefstate = WFO;
        this.access = new Semaphore();
        this.courseCounter = 0;
        this.courseCounterMinusOne = 0;
        this.filename = filename;
        access.up();
        this.generalRepoHostName = generalRepoHostName;
        this.generalRepoPortNumber = generalRepoPortNumber;
        initFile(filename);
        this.flag = new Semaphore();
    }

    /**
     * Initiates fileWriter
     *
     * @param filename
     * @throws IOException if logger file can't be initiated
     */
    public void initFile(String filename) throws IOException {
        fw = new FileWriter(filename);
        bw = new BufferedWriter(fw);
        lineMinusOne = "  Course   CHEF         WAITER       ST1        ST2        ST3        ST4        ST5        ST6        ST7\n";
    }

    /**
     * Adds line with new states to the logger file
     *
     * @throws IOException if line can not be appended to logger file
     */
    public void appendLine() throws IOException {
        access.down();
        line = "     " + courseCounter + "      " + chefstate + "          " + waiterstate + "         " + studentstates[0] + "        " + studentstates[1] + "        " + studentstates[2] + "        " + studentstates[3] + "        " + studentstates[4] + "        " + studentstates[5] + "        " + studentstates[6] + "\n";
        if (!line.equals(lineMinusOne)) {
            if (courseCounter == courseCounterMinusOne){
                bw.append(lineMinusOne);
            }
        }
        lineMinusOne = line;
        access.up();
    }

    /**
     * Updates chef sate and prints in logger
     *
     * @param newChefState Chef state
     * @throws IOException if the chef state can't be written to the logger file
     */
    public void updateChefState(Chef_State newChefState) throws IOException {
        chefstate = newChefState;
        appendLine();

    }

    /**
     * Updates waiter sate and prints in logger
     *
     * @param newstate Waite State
     * @throws IOException if the waiter state can't be written to the logger
     * file
     */
    public void updateWaiterState(Waiter_State newstate) throws IOException {
        waiterstate = newstate;
        appendLine();

    }

    /**
     * Updates student x sate and prints in logger
     *
     * @param newstate Student State
     * @param studentID student identifier
     * @throws IOException if the student state can't be written to the logger
     * file
     */
    public void updateStudentState(Student_State newstate, int studentID) throws IOException {
        studentstates[studentID] = newstate;
        appendLine();
    }

    /**
     * Updates course and prints in logger
     *
     * @param newcourseCounter Course Counter
     * @throws IOException if the course state can't be written to the logger
     * file
     */
    public void updateCourse(int newcourseCounter) throws IOException {
        access.down();
        bw.append("-------------------------------------------------- Course " + newcourseCounter + " ----------------------------------------------\n");
        courseCounter = newcourseCounter;
        access.up();
        appendLine();
        courseCounterMinusOne = courseCounter;

    }

    /**
     * closes BufferWriter and FileWriter
     *
     * @throws IOException if logger file can't be closed
     */
    public void closeWriter() throws IOException {
        bw.append(lineMinusOne);
        bw.close();
        fw.close();
    }
}
