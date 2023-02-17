/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elmiao
 * @version 03/02/23
 */
public class Server extends Thread {

    ServerSocket server;
    Socket client;
    DataInputStream sIn;
    DataOutputStream sOut;
    String help = "\u001b[36mCOMANDI UTILIZZABILI:\n\u001b[36m-PING: RISPONDE CON LA PAROLA '\u001b[31;1mPONG\u001b[36m'\n\u001b[36m-USCIRE: ESCE DAL TERMINALE PIU FIGO DEL \u001b[35mMONDO\u001b[36m";

    Server() {
        try {
            server = new ServerSocket(2000);
 
       } catch (IOException ex) {
            System.err.println("ERRORE.");
        }

    }

    public void run() {

        communication();

    }

    public void communication() {

        String msgIn = null;
        do {
            msgIn = getMsg();
            String msgOut = null;
            System.out.println(msgIn);
            if (msgIn != null) {
                if (msgIn.equals("PING")) {
                    msgOut = "\u001b[32mPONG\u001b[0m";

                } else if (msgIn.equals("AIUTO")) {

                    msgOut = help;

                } else if (msgIn.equals("USCIRE")) {
                    sendMsg("USCIRE");
                    break;

                } else {
                    msgOut = "\u001b[31mSE NON SAI I COMANDI DIGITA 'AIUTO'";
                }
                sendMsg(msgOut);
                System.out.println("MESSAGGIO INVIATO: " + msgOut);
            } else {
                System.out.println("PROBLEMA CON LA LETTURA DATI");
            }

            System.out.println("\u001b[0m");
        } while (true);

    }

    boolean connect() {

        try {
            server.setSoTimeout(10000);
            client = server.accept();
            System.out.println("SERVER IN ASCOLTO");
            

        } catch (SocketTimeoutException e) {
            System.err.println("STO ASPETTANDO DA 10 SECONDI E NESSUNO SI CONNETTE...");
            return false;
        } catch (IOException ex) {
            Logger.getLogger(Server.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    void sendMsg(String msg) {

        try {
            sOut = new DataOutputStream(client.getOutputStream());
            //sOut.writeUTF(msg);
            System.out.println("STO MANDANDO IL MESSAGGIO: " + msg);
            byte msgByte[] = null;
            sOut.writeInt(msg.length());
            sOut.write(msg.getBytes());
            sOut.flush();

        } catch (IOException ex) {
            Logger.getLogger(Server.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    String getHelp() {
        return this.help;
    }

    String getMsg() {

        String msg = null;

        try {
            System.out.println("LEGGENDO MESSAGGIO...");
            sIn = new DataInputStream(client.getInputStream());

            //msg=sIn.readUTF();    
            int length = sIn.readInt();
            byte msgByte[] = new byte[length];
            sIn.readFully(msgByte, 0, length);
            msg = new String(msgByte);
            System.out.println("MESSAGGIO LETTO: " + msg);

        } catch (IOException ex) {
            Logger.getLogger(Server.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return msg;

    }

    void close() {

        try {
            server.close();

        } catch (IOException ex) {
            Logger.getLogger(Server.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
