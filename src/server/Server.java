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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elmiao
 * @version 03/02/23
 */
public class Server {
    ServerSocket server;
    Socket client;
    DataInputStream sIn;
    DataOutputStream sOut;
    String help="\u001b[36mCOMANDI UTILIZZABILI:\n\u001b[36m-PING: RISPONDE CON LA PAROLA '\u001b[31;1mPONG\u001b[36m'\n\u001b[36m-USCIRE: ESCE DAL TERMINALE PIU FIGO DEL \u001b[35mMONDO\u001b[36m";
    
    Server(){
        try {
            server = new ServerSocket(2000);
            
            
            
            System.out.println("SERVER IN ASCOLTO");
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    void accept(){
        
        try {

            client = server.accept();
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    void sendMsg(String msg){

        try {
            sOut = new DataOutputStream(client.getOutputStream());
            //sOut.writeUTF(msg);
            System.out.println("STO MANDANDO IL MESSAGGIO: "+msg);
            byte msgByte[]=null;
            sOut.writeInt(msg.length());
            sOut.write(msg.getBytes());
            sOut.flush();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    String getHelp(){
        return this.help;
    }
    
    String getMsg(){
        
        String msg=null;
        
        try {
            System.out.println("LEGGENDO MESSAGGIO...");
            sIn = new DataInputStream(client.getInputStream());
            
            //msg=sIn.readUTF();    
            int length= sIn.readInt();
            byte msgByte[]=new byte[length];
            sIn.readFully(msgByte, 0, length);
            msg= new String(msgByte);
            System.out.println("MESSAGGIO LETTO: "+msg);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return msg;
        
    }
    
    void close(){
        
        try {
            server.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
}
