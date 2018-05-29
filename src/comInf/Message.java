package comInf;

import entities_states.Chef_State;
import entities_states.Student_State;
import entities_states.Waiter_State;
import java.io.*;

/**
 * Defines the message class that constitutes all the messages sent/received
 * form the client to the server
 *
 * @author Diogo Jorge
 */
public class Message implements Serializable {

    /**
     * Serial key
     */
    private static final long serialVersionUID = 1001L;

    /* Tipos das mensagens */
    public static final int SERVERACKNOWLEDGE = 1;
    public static final int WATCHTHENEWS = 2;
    public static final int STARTPREPARATION = 3;
    public static final int PROCEEDTOPRESENTATION = 4;
    public static final int ALERTTHEWAITER = 5;
    public static final int ALLPORTIONSBEENDELIVERED = 6;
    public static final int HAVENEXTPORTIONREADY = 7;
    public static final int HAVETHEORDERBEENCOMPLETED = 8;
    public static final int CONTINUEPREPARATION = 9;
    public static final int CLEANUP = 10;
    public static final int WAITERINTHEBARUP = 11;
    public static final int WAITINGFORSTUDENTSTOFINISHDOWN = 12;

    public static final int SALUTETHECLIENT = 13;
    public static final int GETTHEPAD = 14;
    public static final int DELIVEREPORTION = 15;
    public static final int HAVEALLCLIENTSBEENSERVED = 16;
    public static final int RESETDELIVEREDCOUNTER = 17;
    public static final int PRESENTTHEBILL = 18;
    public static final int SAYGOODBYE = 19;
    public static final int LOOKARROUND = 20;
    public static final int PREPARETHEBILL = 21;
    public static final int RETURNTOBAR = 22;
    public static final int HANDNOTETOTHECHEF = 23;
    public static final int COLLECTPORTION = 24;
    public static final int CHEFWAITINGFORDELIVERYUP = 25;
    public static final int CALLTHEWAITERTOSERVE = 26;

    public static final int ENTER = 27;
    public static final int READTHEMENU = 28;
    public static final int HASEVERYBODYCHOSEN = 29;
    public static final int PREPARETHEORDER = 30;
    public static final int DESCRIBETHEORDER = 31;
    public static final int JOINTHETALK = 32;
    public static final int INFORMCOMPANION = 33;
    public static final int STARTEATING = 34;
    public static final int ENDEATING = 35;
    public static final int EXIT = 39;
    public static final int HASEVERYBODYFINISHED = 40;
    public static final int SHOULDHAVEARRIVEDEARLIER = 41;
    public static final int HONORTHEBILL = 42;
    public static final int CALLTHEWAITER = 43;
    public static final int ALERTWAITEREXIT = 44;
    public static final int WAITERGOHOME = 45;
    public static final int PAYTHEWAITER = 46;
    public static final int ALERTWAITERENTERING = 47;
    public static final int SIGNALTHEWAITER = 48;
    public static final int SHUTKITCHEN = 49;
    public static final int SHUTBAR = 50;
    public static final int SHUTTABLE = 51;
    public static final int SHUTGENERALREPO = 51;

    public static final int UPDATEWAITERSTATE = 52;
    public static final int UPDATECHEFSTATE = 53;
    public static final int UPDATECOURSE = 54;
    public static final int UPDATESTUDENTSTATE = 55;

    private int msgType = -1;
    private boolean bool;
    private int Count = -1;
    private char situation;

    private Waiter_State waiter_state;
    private Chef_State chef_State;
    private Student_State student_State;

    /**
     * Message instantiation (type1)
     *
     * @param type Message type
     */
    public Message(int type) {
        msgType = type;
    }

    /**
     * Message instantiation (type2)
     *
     * @param type Message type
     * @param bool Boolean value
     */
    public Message(int type, Boolean bool) {
        msgType = type;
        this.bool = bool;
    }

    /**
     * Message instantiation (type3)
     *
     * @param type Message type
     * @param situation Char value
     */
    public Message(int type, char situation) {
        msgType = type;
        this.situation = situation;
    }

    /**
     * Message instantiation (type4)
     *
     * @param type Message type
     * @param Count Integer value
     */
    public Message(int type, int Count) {
        msgType = type;
        this.Count = Count;
    }

    /**
     * Message instantiation (type5)
     *
     * @param type Message type
     * @param waiter_newstate Waiter State
     */
    public Message(int type, Waiter_State waiter_newstate) {
        msgType = type;
        waiter_state = waiter_newstate;
    }

    /**
     * Message instantiation (type6)
     *
     * @param type Message type
     * @param chef_newstate Chef State
     */
    public Message(int type, Chef_State chef_newstate) {
        msgType = type;
        chef_State = chef_newstate;
    }

    /**
     * Message instantiation (type7)
     *
     * @param type Message type
     * @param StudentNewState Student State
     * @param studentID Student identifier
     */
    public Message(int type, Student_State StudentNewState, int studentID) {
        msgType = type;
        student_State = StudentNewState;
        Count = studentID;
    }

    /**
     * Message type getter
     * @return Message type
     */
    public int getType() {
        return (msgType);
    }

    /**
     * Boolean getter
     * @return Boolean
     */
    public boolean getBool() {
        return (bool);
    }

    /**
     * Count getter
     *
     * @return Count
     */
    public int getStudentCount() {
        return (Count);
    }

    /**
     * Waiter situation getter
     *
     * @return Waiter situation
     */
    public char getSituation() {
        return (situation);
    }

    /**
     * Waiter state getter
     *
     * @return Waiter state
     */
    public Waiter_State getWaiter() {
        return (waiter_state);
    }

    /**
     * Chef state getter
     *
     * @return Chef state
     */
    public Chef_State getChef() {
        return (chef_State);
    }

    /**
     * Student state getter
     *
     * @return Student state
     */
    public Student_State getStudent() {
        return (student_State);
    }
}
