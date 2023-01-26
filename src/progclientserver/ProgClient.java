/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progclientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elmiao
 */
public class ProgClient {
    public static void main(String[] args) {    

        
            //System.out.print("Scrivi il messaggio: ");
            //Scanner sc = new Scanner(System.in);
            //String msg = sc.nextLine();
            Client c=new Client();
            c.sendMsg();
            String mes=c.readMsg();
            System.out.println(mes);
            c.close();
            
    }
    
}
