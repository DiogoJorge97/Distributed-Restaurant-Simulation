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
import java.io.IOException;
import waiter.ClientCom;

/**
 * Create generalRepo stub instantiation
 * @author Diogo Jorge
 */
public class GeneralRepoStub {

    String serverHostName;
    int serverPortNumber;

    /**
     * GeneralRepo stub constructor
     * @param serverHostName server host name
     * @param serverPortNumber server port number
     */
    public GeneralRepoStub(String serverHostName, int serverPortNumber) {
        this.serverHostName = serverHostName;
        this.serverPortNumber = serverPortNumber;
    }

    /**
     * Updates chef sate and prints in logger
     *
     * @param ChefNewState Chef new state

     */
    public void updateChefState(Chef_State ChefNewState)  {
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
            System.out.println("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Updates waiter sate and prints in logger
     *
     * @param waiter_newstate Waiter new state
     */
    public void updateWaiterState(Waiter_State waiter_newstate){
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
            System.out.println("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Updates student x sate and prints in logger
     *
     * @param StudentNewState Student new state
     * @param studentID student identifier
     */
    public void updateStudentState(Student_State StudentNewState, int studentID)  {
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
            System.out.println("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Updates course and prints in logger
     *
     * @param newcourseCounter Course state
     */
    public void updateCourse(int newcourseCounter)  {
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
            System.out.println("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

    /**
     * Informs server to shutdown
     */
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
            System.out.println("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
    }

}
