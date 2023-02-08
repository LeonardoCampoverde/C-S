/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elmiao
 */
public class ProgServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        Server s = new Server();

        s.accept();
        String msgIn = null;
        do {
            msgIn = s.getMsg();
            String msgOut = null;
            System.out.println(msgIn);
            if (msgIn != null) {
                if (msgIn.equals("PING")) {
                    msgOut = "\u001b[32mPONG\u001b[0m";

                } else if (msgIn.equals("AIUTO")) {

                    msgOut=s.getHelp();

                } else if (msgIn.equals("USCIRE")) {
                    s.sendMsg("USCIRE");
                    break;

                } else {
                    msgOut = "\u001b[31mSE NON SAI I COMANDI DIGITA 'AIUTO'";
                }
                s.sendMsg(msgOut);
                System.out.println("MESSAGGIO INVIATO: " + msgOut);
            } else {
                System.out.println("PROBLEMA CON LA LETTURA DATI");
            }

            System.out.println("\u001b[0m");
        } while (true);

        s.close();

    }
}
