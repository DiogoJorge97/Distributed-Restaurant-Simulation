package student;

import comInf.Info;
import stubs.BarStub;
import stubs.TableStub;
import stubs.KitchenStub;
import stubs.BarStub;
import stubs.TableStub;
import genclass.GenericIO;

/**
 * Este tipo de dados simula uma solução do lado do cliente do Problema dos
 * Barbeiros Sonolentos que implementa o modelo cliente-servidor de tipo 2
 * (replicação do servidor) com lançamento estático dos threads barbeiro. A
 * comunicação baseia-se em passagem de mensagens sobre sockets usando o
 * protocolo TCP.
 */
public class StudentMain {

    /**
     * Programa principal.
     */
    public static void main(String[] args) throws InterruptedException {
        int coursesNumber = Info.coursesNumber;
        int studentNumber = Info.studentNumber;
        String barHostName = Info.barHostName;
        int barPortNumber = Info.barPortNumber;
        String tableHostName = Info.tableHostName;
        int tablePortNumber = Info.tablePortNumber;
        
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
