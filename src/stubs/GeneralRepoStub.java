/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stubs;

import comInf.Message;
import entities_states.Chef_State;
import entities_states.Student_State;
import entities_states.Waiter_State;
import genclass.GenericIO;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import waiter.ClientCom;

/**
 *
 * @author diogojorge
 */
public class GeneralRepoStub {

    String serverHostName;
    int serverPortNumber;

    public GeneralRepoStub(String serverHostName, int serverPortNumber) {
        this.serverHostName = serverHostName;
        this.serverPortNumber = serverPortNumber;
    }

    /**
     * Updates chef sate and prints in logger
     *
     * @param newstate
     * @throws IOException
     */
    public void updateChefState(Chef_State ChefNewState) throws IOException {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.UPDATECHEFSTATE, ChefNewState);        // pede a realização do serviço
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        if ((inMessage.getType() != Message.SERVERACKNOWLEDGE)) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Updates waiter sate and prints in logger
     *
     * @param newstate
     * @throws IOException
     */
    public void updateWaiterState(Waiter_State waiter_newstate) throws IOException {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.UPDATEWAITERSTATE, waiter_newstate);        // pede a realização do serviço
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        if ((inMessage.getType() != Message.SERVERACKNOWLEDGE)) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Updates student x sate and prints in logger
     *
     * @param newstate
     * @param studentID
     * @throws IOException
     */
    public void updateStudentState(Student_State StudentNewState, int studentID) throws IOException {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.UPDATESTUDENTSTATE, StudentNewState, studentID);        // pede a realização do serviço
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        if ((inMessage.getType() != Message.SERVERACKNOWLEDGE)) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Updates course and prints in logger
     *
     * @throws IOException
     */
    public void updateCourse(int newcourseCounter) throws IOException {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.UPDATECOURSE, newcourseCounter);        // pede a realização do serviço
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        if ((inMessage.getType() != Message.SERVERACKNOWLEDGE)) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    public void shutdown() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.SHUTGENERALREPO);
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        if (inMessage.getType() != Message.SERVERACKNOWLEDGE) {
            GenericIO.writelnString("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            GenericIO.writelnString(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

}
