package oblig_2;

import java.io.IOException;
import java.net.*;

class RIPServer {
	static int ack = -1;
	static int seq = 1;
	static int predecessorAck;
	static boolean conn = false;
	private static DatagramSocket serverSocket;
	private static String out;
	private static InetAddress destAddress;
	private static InetAddress relayAddress;
	private static int destPort;


	public static void main(String args[]) throws Exception
	{
		System.out.println("RealyServer says hi");
		serverSocket = new DatagramSocket(9876);




		while(true)
		{
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			String in = new String(receivePacket.getData());
			String[]input = in.split(";");
			int pos = 2;
			if(input.length > 5){
				pos = 4;
			}
			predecessorAck = Integer.parseInt(input[pos]);//will only return correctly received acks. if the ack is not predecessorAck+1, then the last  correct ack is returned.
			if(predecessorAck == ack + 1){
				System.out.println("Server correctly recieved SEQ #: " + input[pos] + " " + input[input.length-1]);
				ack = predecessorAck;
			}
			destPort = Integer.parseInt(input[1]);
			relayAddress = receivePacket.getAddress();
			destAddress = InetAddress.getByName("localhost");


			String s = input[input.length-1];
			out = s;
			if(out.contains("SYN") && !conn){//handshake. First packet.
				conn = true;
				out = "SYNACK";
				predecessorAck = Integer.parseInt(input[pos]);//The first ack.
				System.out.println("Server got " + s + ". First sequence is set to " + predecessorAck);
				ack = predecessorAck;
			}
			else if(out.contains("FIN")){//connection tear-down.
				out = "FINACK";
				System.out.println(out);
				DatagramPacket sendPacket = RelayPakke.storSendPakke(out, destAddress, destPort,relayAddress,11111,seq,ack);
				serverSocket.send(sendPacket);
				serverSocket.close();
				System.exit(0);
			}
			send();

		}
	}

    /**
     * send from server
     * @throws IOException
     */
	public static void send() throws IOException {
		DatagramPacket sendPacket = RelayPakke.storSendPakke(out, destAddress, destPort,relayAddress,11111,seq,ack);
		serverSocket.send(sendPacket);
		seq++;

	}

}
