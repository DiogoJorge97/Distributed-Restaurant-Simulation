package comInf;

import java.io.*;

/**
 * Este tipo de dados define as mensagens que são trocadas entre os clientes e o
 * servidor numa solução do Problema dos Barbeiros Sonolentos que implementa o
 * modelo cliente-servidor de tipo 2 (replicação do servidor) com lançamento
 * estático dos threads barbeiro. A comunicação propriamente dita baseia-se na
 * troca de objectos de tipo Message num canal TCP.
 */
public class Message implements Serializable {

    /**
     * Chave de serialização
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


    /* Campos das mensagens */
    /**
     * Tipo da mensagem
     */
    private int msgType = -1;

    private boolean bool;

    private int studentCount = -1;
    
    private char situation;


    public Message(int type) {
        msgType = type;
    }

    public Message(int type, Boolean bool) {
        msgType = type;
        this.bool = bool;
    }

    public Message(int type, char situation) {
        msgType = type;
        this.situation = situation;
    }

    public Message(int type, int studentCount) {
        msgType = type;
        this.studentCount = studentCount;
    }


    
    public int getType() {
        return (msgType);
    }
    
    public boolean getBool() {
        return (bool);
    }
    
    public int getStudentCount() {
        return (studentCount);
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
    
    
    public char getSituation() {
        return (situation);
    }

    /**
     * Impressão dos campos internos. Usada para fins de debugging.
     *
     * @return string contendo, em linhas separadas, a concatenação da
     * identificação de cada campo e valor respectivo
     */
    @Override
    public String toString() {
        return ("Tipo = " + msgType
                + "\nId StudentCount = " + studentCount
                + "\nId Situação = " + situation);
    }
}
