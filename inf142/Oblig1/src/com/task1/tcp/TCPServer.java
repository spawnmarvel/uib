package com.task1.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * The server class
 * @author jkl070 and dro068
 *
 */

public  class TCPServer {

	private static Socket clientSocket;


   /**
    * Returns the ip from the client
    * @return
    */
	public static String getIp() {
		String ipAdd =  clientSocket.getInetAddress().getHostName();
		return ipAdd;
	}
	/**
	 * Starts the server
	 * @param args
	 * @throws IOException
	 */
    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6001);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 6001.");
            System.exit(1);
        }

        clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Connection fail");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                clientSocket.getInputStream()));
        String inputLine, outputLine;
        MyProtocol protocol = new MyProtocol();

        outputLine = protocol.processInput(null);
        out.println(outputLine);

        while ((inputLine = in.readLine()) != null) {
             outputLine = protocol.processInput(inputLine);
             out.println(outputLine);
             if (outputLine.equals("Bye."))

                break;
        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}