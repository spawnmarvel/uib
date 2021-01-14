package oblig_2;


import java.net.*;



public class SimpleRelay {

	public static void main(String args[]) throws Exception
    {
		System.out.println("SimpleRelay says hi");

      DatagramSocket serverSocket = new DatagramSocket(11111);

//      byte[] receiveData = new byte[1024];
//      byte[] sendData  = new byte[1024];

      while(true)
        {
           DatagramPacket receivePacket =
             new DatagramPacket(new byte[1024], 1024);
           serverSocket.receive(receivePacket);
           String sentence = new String(receivePacket.getData());
           System.out.println(sentence);
           InetAddress sourceAddress = receivePacket.getAddress(); //IPaddress of source!
           int sourceport = receivePacket.getPort();  // port of source!


           System.out.println("RELAY: Received: " + sentence + " from " +
        		   sourceAddress + " at port " + sourceport);

           //sendData = sentence.getBytes();
           // Now get IP address of dest and port of dest!

           int semicol = sentence.indexOf(";");
           String substring  = sentence.substring(0,semicol);
           if (substring.indexOf("/") >= 0)
        	   substring = "localhost";

           InetAddress destAddress = InetAddress.getByName(substring); //IPaddress of source!
           sentence = sentence.substring(semicol+1);
           System.out.println("IN SIMPLE: " + sentence);
           semicol = sentence.indexOf(";");
           substring  = sentence.substring(0,semicol);

           int destport = Integer.parseInt(substring);
           sentence = sentence.substring(semicol+1);
           System.out.println("IN SIMPLE2: " + sentence);
           DatagramPacket sendPacket =
               // new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
          	  RelayPakke.storSendPakke(sentence, sourceAddress, sourceport,destAddress,destport,0,0);


           //DatagramPacket sendPacket =
           //   new DatagramPacket(sendData, sendData.length, IPAddress,
           //                     port);

           serverSocket.send(sendPacket);

           System.out.println("RELAY: Sent: " + sentence + " to " +
        		   destAddress + " at port " + destport);
         }
     }
 }
