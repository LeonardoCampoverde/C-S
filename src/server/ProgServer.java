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
    public static void main(String[] args) {

        
        while (true) {
            Server s = new Server();
            if (!s.connect()) {
                return;
            }

            s.communication();

            s.close();

        }

    }
}
