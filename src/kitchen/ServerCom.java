package kitchen;

import java.io.*;
import java.net.*;

/**
 * Implements the communication channel (server side) Allows for a communication
 * based on TCP protocol
 *
 */
public class ServerCom {

    /**
     * Listening Socket
     *
     * @serialField listeningSocket
     */
    private ServerSocket listeningSocket = null;

    /**
     * Communication socket
     *
     * @serialField commSocket
     */
    private Socket commSocket = null;

    /**
     * Kitchen server port number (server side)
     *
     * @serialField serverPortNumb
     */
    private int serverPortNumb;

    /**
     * Communication channel entering stream
     *
     * @serialField in
     */
    private ObjectInputStream in = null;

    /**
     * Communication channel exiting stream
     *
     * @serialField out
     */
    private ObjectOutputStream out = null;

    /**
     * Instantiation of a communication channel
     *
     * @param portNumb Server side port number (Kitchen)
     */
    public ServerCom(int portNumb) {
        serverPortNumb = portNumb;
    }

    /**
     * Instantiation of a communication channel
     *
     * @param portNumb Server side port number (Kitchen)
     * @param lSocket Listening socket
     */
    public ServerCom(int portNumb, ServerSocket lSocket) {
        serverPortNumb = portNumb;
        listeningSocket = lSocket;
    }

    /**
     * Service instantiation
     */
    public void start() {
        try {
            listeningSocket = new ServerSocket(serverPortNumb);
            setTimeout(10000);
        } catch (BindException e) // erro fatal --- port já em uso
        {
            System.out.println(Thread.currentThread().getName()
                    + " - não foi possível a associação do socket de escuta ao port: "
                    + serverPortNumb + "!");
            System.exit(1);
        } catch (IOException e) // erro fatal --- outras causas
        {
            System.out.println(Thread.currentThread().getName()
                    + " - ocorreu um erro indeterminado na associação do socket de escuta ao port: "
                    + serverPortNumb + "!");
            System.exit(1);
        }
    }

    /**
     * Service shutdown.
     */
    public void end() {
        try {
            listeningSocket.close();
        } catch (IOException e) {
            System.out.println(Thread.currentThread().getName()
                    + " - não foi possível fechar o socket de escuta!");
            System.exit(1);
        }
    }

    /**
     * Listening process. Creation of a communication channel for pending
     * requests
     *
     *
     * @return Communication channel
     */
    public ServerCom accept() throws SocketTimeoutException {
        ServerCom scon;                                      // canal de comunicação

        scon = new ServerCom(serverPortNumb, listeningSocket);
        try {
            scon.commSocket = listeningSocket.accept();
        } catch (SocketTimeoutException e) {
            throw new SocketTimeoutException("Timeout!");
        } catch (SocketException e) {
            System.out.println(Thread.currentThread().getName()
                    + " - foi fechado o socket de escuta durante o processo de escuta!");
            System.exit(1);
        } catch (IOException e) {
            System.out.println(Thread.currentThread().getName()
                    + " - não foi possível abrir um canal de comunicação para um pedido pendente!");
            System.exit(1);
        }

        try {
            scon.in = new ObjectInputStream(scon.commSocket.getInputStream());
        } catch (IOException e) {
            System.out.println(Thread.currentThread().getName()
                    + " - não foi possível abrir o canal de entrada do socket!");
            System.exit(1);
        }

        try {
            scon.out = new ObjectOutputStream(scon.commSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println(Thread.currentThread().getName()
                    + " - não foi possível abrir o canal de saída do socket!");
            System.exit(1);
        }

        return scon;
    }

    /**
     * Communication channel closure.
     */
    public void close() {
        try {
            in.close();
        } catch (IOException e) {
            System.out.println(Thread.currentThread().getName()
                    + " - não foi possível fechar o canal de entrada do socket!");
            System.exit(1);
        }

        try {
            out.close();
        } catch (IOException e) {
            System.out.println(Thread.currentThread().getName()
                    + " - não foi possível fechar o canal de saída do socket!");
            System.exit(1);
        }

        try {
            commSocket.close();
        } catch (IOException e) {
            System.out.println(Thread.currentThread().getName()
                    + " - não foi possível fechar o socket de comunicação!");
            System.exit(1);
        }
    }

    /**
     * Listening time out
     *
     * @param time
     */
    public void setTimeout(int time) {
        try {
            listeningSocket.setSoTimeout(time);
        } catch (SocketException e) {
            System.out.println(Thread.currentThread().getName()
                    + " - ocorreu um erro na fixação de um timeout de escuta!");
            System.exit(1);
        }
    }

    /**
     * Communication channel object reader
     *
     * @return red object
     */
    public Object readObject() {
        Object fromClient = null;                            // objecto

        try {
            fromClient = in.readObject();
        } catch (InvalidClassException e) {
            System.out.println(Thread.currentThread().getName()
                    + " - o objecto lido não é passível de desserialização!");
            System.exit(1);
        } catch (IOException e) {
            System.out.println(Thread.currentThread().getName()
                    + " - erro na leitura de um objecto do canal de entrada do socket de comunicação!");
            System.exit(1);
        } catch (ClassNotFoundException e) {
            System.out.println(Thread.currentThread().getName()
                    + " - o objecto lido corresponde a um tipo de dados desconhecido!");
            System.exit(1);
        }

        return fromClient;
    }

    /**
     * Communication channel object writer
     *
     * @param toClient object to be written
     */
    public void writeObject(Object toClient) {
        try {
            out.writeObject(toClient);
        } catch (InvalidClassException e) {
            System.out.println(Thread.currentThread().getName()
                    + " - o objecto a ser escrito não é passível de serialização!");
            System.exit(1);
        } catch (NotSerializableException e) {
            System.out.println(Thread.currentThread().getName()
                    + " - o objecto a ser escrito pertence a um tipo de dados não serializável!");
            System.exit(1);
        } catch (IOException e) {
            System.out.println(Thread.currentThread().getName()
                    + " - erro na escrita de um objecto do canal de saída do socket de comunicação!");
            System.exit(1);
        }
    }
}
