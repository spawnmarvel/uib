package oblig_2;
import java.net.*;

public class RelayPakke {

	static DatagramPacket storSendPakke(
			String sendData,
			InetAddress destAddress,
			int destPort,
			InetAddress relayAddress,
			int relayPort,
			int seq,
			int ack){
		 byte[] storData;
		 String s = "" + destAddress + ";" + destPort + ";" +
		 seq + ";" + ack + ";" +
		 sendData;
		storData = s.getBytes();
		DatagramPacket sp;
		sp = new DatagramPacket(storData, storData.length, relayAddress, relayPort);
		return sp;
	}


	static DatagramPacket storSendPakke(
			String sendData,
			InetAddress destAddress,
			int destPort,
			InetAddress relayAddress,
			int relayPort){
		 byte[] storData;
		 String s = "" + destAddress + ";" + destPort + ";" + sendData;
		storData = s.getBytes();
		DatagramPacket sp;
		sp = new DatagramPacket(storData, storData.length, relayAddress, relayPort);
		return sp;
	}

	public static DatagramPacket reformat(DatagramPacket receivePacket ){
		String sentence = new String(receivePacket.getData());
        InetAddress sourceAddress = receivePacket.getAddress(); //IPaddress of source!
        int sourceport = receivePacket.getPort();  // port of source!
        //System.out.println("RELAY: Received: " + sentence + " from " +
     		  // sourceAddress + " at port " + sourceport);

        //sendData = sentence.getBytes();
        // Now get IP address of dest and port of dest!

        int semicol = sentence.indexOf(";");
        String substring  = sentence.substring(0,semicol);
	int slashindex = substring.indexOf("/");
        if (substring.indexOf("/") >= 0)
     	   substring = substring.substring(slashindex+1);

        InetAddress destAddress = null;
        try {
        destAddress =
        	InetAddress.getByName(substring); //IPaddress of source!
        }
        catch (  UnknownHostException e )
        	{System.out.println("Feil: <" + substring + ">");}

        sentence = sentence.substring(semicol+1);
        semicol = sentence.indexOf(";");
        substring  = sentence.substring(0,semicol);

        int destport = Integer.parseInt(substring);
        sentence = sentence.substring(semicol+1);
        DatagramPacket sendPacket =

       	  RelayPakke.storSendPakke(sentence, sourceAddress, sourceport,destAddress,destport);
        //System.out.println("RELAY: reformatting: " + sentence + " to " +
     	//	   destAddress + " at port " + destport);
        return sendPacket;
	}

}
