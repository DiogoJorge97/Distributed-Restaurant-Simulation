package generalRepository;

import entities_states.Chef_State;
import static entities_states.Chef_State.WFO;
import entities_states.Student_State;
import static entities_states.Student_State.GTR;
import entities_states.Waiter_State;
import static entities_states.Waiter_State.ATS;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import semaphore.Semaphore;

/**
 *
 * @author Ricardo Antão
 * @author Diogo Jorge
 */
public final class GeneralRepo {

    private int courseCounter;
    private String filename;
    private PrintWriter writer;
    private Chef_State chefstate;
    private Waiter_State waiterstate;
    Student_State[] studentstates = {GTR, GTR, GTR, GTR, GTR, GTR, GTR};
    private String line;
    private String lineMinusOne;
    private final Semaphore access;

    BufferedWriter bw = null;
    FileWriter fw = null;
    String generalRepoHostName;
    int generalRepoPortNumber;

    public GeneralRepo(String filename, String generalRepoHostName, int generalRepoPortNumber) throws IOException {
        //inicial states of entities
        waiterstate = ATS;
        chefstate = WFO;
        this.access = new Semaphore();
        this.courseCounter = 0;
        this.filename = filename;
        access.up();
        this.generalRepoHostName = generalRepoHostName;
        this.generalRepoPortNumber = generalRepoPortNumber;
        initFile(filename);
    }

    /**
     * Initiates fileWriter
     *
     * @param filename
     * @throws IOException
     */
    public void initFile(String filename) throws IOException {
        fw = new FileWriter(filename);
        bw = new BufferedWriter(fw);
        lineMinusOne = "  Course   CHEF         WAITER       ST1        ST2        ST3        ST4        ST5        ST6        ST7\n";
        bw.append(lineMinusOne);
    }

    /**
     * Adds line with new states to the logger file
     *
     * @throws IOException
     */
    public void appendLine() throws IOException {
        access.down();
        line = "     " + courseCounter + "      " + chefstate + "          " + waiterstate + "         " + studentstates[0] + "        " + studentstates[1] + "        " + studentstates[2] + "        " + studentstates[3] + "        " + studentstates[4] + "        " + studentstates[5] + "        " + studentstates[6] + "\n";
        if (!line.equals(lineMinusOne)) {
            bw.append(line);
        }
        lineMinusOne = line;
        access.up();
    }

    /**
     * Updates chef sate and prints in logger
     *
     * @param newChefState
     * @throws IOException
     */
    public void updateChefState(Chef_State newChefState) throws IOException {
        chefstate = newChefState;
        System.out.println(chefstate);
        appendLine();

    }

    /**
     * Updates waiter sate and prints in logger
     *
     * @param newstate
     * @throws IOException
     */
    public void updateWaiterState(Waiter_State newstate) throws IOException {
        waiterstate = newstate;
        appendLine();

    }

    /**
     * Updates student x sate and prints in logger
     *
     * @param newstate
     * @param studentID
     * @throws IOException
     */
    public void updateStudentState(Student_State newstate, int studentID) throws IOException {
        studentstates[studentID] = newstate;
        appendLine();
    }

    /**
     * Updates course and prints in logger
     *
     * @throws IOException
     */
    public void updateCourse(int newcourseCounter) throws IOException {
        courseCounter = newcourseCounter;
        appendLine();
    }

    /**
     * closes BufferWriter and FileWriter
     *
     * @throws IOException
     */
    public void closeWriter() throws IOException {
        bw.close();
        fw.close();
    }
}
