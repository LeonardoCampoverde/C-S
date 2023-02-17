/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progclientserver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elmiao
 * @version 03/02/23
 */
public class Client {

    private String host = "127.0.0.1";
    private int port = 2000;
    Socket s;
    DataOutputStream sOut;
    DataInputStream sIn;

    Client() {

        try {
            s = new Socket(host, port);

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    Client(String host, int port) {

        try {
            s = new Socket(host, port);
            sOut = new DataOutputStream(s.getOutputStream());
            sIn = new DataInputStream(s.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
    void sendMsg(String msg){
        
        try {
            sOut.writeUTF(msg);
            sOut.flush();

            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     */

    void sendMsg() {
        try {
            sOut = new DataOutputStream(s.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print("\u001b[32m>");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        byte msg[] = null;
        try {
            msg = br.readLine().getBytes();

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println("MANDANDO MESSAGGIO: " + new String(msg));
            sOut.writeInt(msg.length);
            sOut.write(msg);
            //sOut.writeUTF(msg);
            sOut.flush();

            System.out.println("MESSAGGIO INVIATO");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    String getMsg() {
        String msg = null;
        try {
            sIn = new DataInputStream(s.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("LETTURA MESSAGGIO...");

        try {
            InputStream dInStream = s.getInputStream();
            sIn = new DataInputStream(dInStream);

            //msg= sIn.readUTF();
            int length = sIn.readInt();
            byte msgByte[] = new byte[length];
            sIn.readFully(msgByte, 0, length);

            msg = new String(msgByte);

        } catch (IOException ex) {

            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);

        }

        return msg;

    }

    public void communication() {

        do {
            sendMsg();
            String mes;
            mes = getMsg();
            if (mes.equals("USCIRE")) {
                break;
            }
            System.out.println(mes);
        } while (true);

    }

    void close() {
        try {
            s.close();
            System.out.println("CONNESSIONE CHIUSA");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
