package com.task1.tcp;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * The client class
 * @author jkl070 and dro068
 *
 */
public class TCPClient {
	/**
	 * Starts the client
	 * @param args
	 * @throws IOException
	 */
    public static void main(String[] args) throws IOException {

        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            socket = new Socket("127.0.0.1", 6001);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("unknown host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost");
            System.exit(1);
        }

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;


        while ((fromServer = in.readLine()) != null) {
            System.out.println("Server: " + fromServer);
            if (fromServer.equals("Bye.")){
            	 System.out.println("Server: Bye." );
                break;

            }
            fromUser = keyboard.readLine();
        if (fromUser != null) {
                System.out.println("Client: " + fromUser);
                out.println(fromUser);
        }
        }
        System.out.println("Server: Bye. Closing....");
        out.close();
        in.close();
        keyboard.close();
        socket.close();
    }
}