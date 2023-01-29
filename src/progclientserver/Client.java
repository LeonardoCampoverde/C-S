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
 */
public class Client {
    private String host="127.0.0.1";
    private int port=2000;
    Socket s;
    DataOutputStream sOut;
    DataInputStream sIn;
    
    Client(){
        
        try {
            s= new Socket(host,port);
            
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
    }
    
    
    Client(String host,int port){
        
        try {
            s= new Socket(host,port);
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
    
    void sendMsg(){
        try {
            sOut = new DataOutputStream(s.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print("SCRIVERE MESSAGGIO: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msg = "";
        try {
            msg = br.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println("MANDANDO MESSAGGIO: "+msg);
            
            sOut.write(msg.getBytes());
            //sOut.writeUTF(msg);
            sOut.flush();
            
            
            System.out.println("MESSAGGIO INVIATO");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    
    
    String readMsg(){
        
        try {
            sIn = new DataInputStream(s.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("LETTURA MESSAGGIO...");
        String msg=null;
        try {
            InputStream dInStream = s.getInputStream();
            sIn = new DataInputStream(dInStream);
            
            //msg= sIn.readUTF();
            msg= new BufferedReader(new InputStreamReader(sIn)).readLine();
            sIn.close();
        } catch (IOException ex) {
            
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        System.out.println("MESSAGGIO RICEVUTO: "+msg);
        
        return msg;
        
        
    }
    
    void close(){
        try {
            s.close();
            System.out.println("CONNESSIONE CHIUSA");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
        
        
    
    
    
    
    
}
