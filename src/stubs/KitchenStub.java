/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stubs;

import chef.ClientCom;
import comInf.Message;

/**
 * Defines the kitchen stub that constitutes the service provided
 *
 * @author Diogo Jorge
 */
public class KitchenStub {

    String serverHostName;
    int serverPortNumber;

    /**
     * KithcenStub constructor
     *
     * @param serverHostName server host name
     * @param serverPortNumber server port number
     */
    public KitchenStub(String serverHostName, int serverPortNumber) {
        this.serverHostName = serverHostName;
        this.serverPortNumber = serverPortNumber;
    }

    /**
     * Chef watches the news waiting for orders
     */
    public void WatchTheNews() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.WATCHTHENEWS);        // pede a realização do serviço
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
     * Chef starts preparing the first course
     */
    public void StartPreparation() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.STARTPREPARATION);        // pede a realização do serviço
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
     * Chef proceeds to dishing the portions
     */
    public void ProceedToPresentation() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.PROCEEDTOPRESENTATION);        // pede a realização do serviço
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
     * Chef alerts the waiter to deliver the dishes
     *
     * @param deliveredCount Counter of the dishes delivered by the chef to the
     * waiter
     */
    public void AlertTheWaiter(int deliveredCount) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.ALERTTHEWAITER, deliveredCount);        // pede a realização do serviço
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
     * Chef checks if all portions have been delivered
     *
     * @param StudentNumber Number of students in the restaurant
     * @return true if all portions have been delivered
     */
    public boolean AllPortionsBeenDelivered(int StudentNumber) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.ALLPORTIONSBEENDELIVERED);        // pede a realização do serviço
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        if ((inMessage.getType() != Message.SERVERACKNOWLEDGE)) {
            System.out.println("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
        return inMessage.getBool();
    }

    /**
     * Chef starts preparing next portion
     */
    public void haveNextPortionReady() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.HAVENEXTPORTIONREADY);        // pede a realização do serviço
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
     * Chef checks if order is completed
     *
     * @return true if the order has been completed
     */
    public boolean HaveTheOrderBeenCompleted() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.HAVETHEORDERBEENCOMPLETED);        // pede a realização do serviço
        con.writeObject(outMessage);
        inMessage = (Message) con.readObject();
        if ((inMessage.getType() != Message.SERVERACKNOWLEDGE)) {
            System.out.println("Thread " + Thread.currentThread().getName() + ": Tipo inválido!");
            System.out.println(inMessage.toString());
            System.exit(1);
        }
        con.close();
        return inMessage.getBool();
    }

    /**
     * Chef prepares next course and increments courseCounter
     *
     * @param MaxRound Number of courses
     */
    public void ContinuePreparation(int MaxRound) {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.CONTINUEPREPARATION);        // pede a realização do serviço
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
     * Chef cleans up and gets ready to leave
     *
     */
    public void cleanup() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.CLEANUP);        // pede a realização do serviço
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
     * Waiter hands order to the chef
     *
     */
    public void handTheNoteToTheChef() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.HANDNOTETOTHECHEF);        // pede a realização do serviço
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
     * Waiter collects cooked portion
     *
     */
    public void collectPortion() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.COLLECTPORTION);        // pede a realização do serviço
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
     * Waiter informs chef that he has delivered the dish
     */
    public void chefWaitingForDeliveryUp() {
        ClientCom con = new ClientCom(serverHostName, serverPortNumber);
        Message inMessage, outMessage;

        while (!con.open()) // aguarda ligação
        {
            try {
                Thread.currentThread().sleep((long) (10));
            } catch (InterruptedException e) {
            }
        }
        outMessage = new Message(Message.CHEFWAITINGFORDELIVERYUP);        // pede a realização do serviço
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
        outMessage = new Message(Message.SHUTKITCHEN);
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
