package oblig_2;
import java.io.*;

import java.net.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class implements the RIPClient protocol.
 * The process runs two parallel threads. A thread that seeks to send packets and another that receive packets form the server.
 * @author dro068, jekl
 *
 */

class RIPClient extends Thread {
	//Different states
	private static final int LISTENING = 0;
	private static final int RECOVERING = 1;
	private static final int TEAR_DOWN = 2;
	private static final int CONN_SET_UP = 3;
	private static final int GETINPUT = 4;
	private static final int CLOSING = 5;
	private static int state;
	//Variables
	private static final int MAX_WINDOW_SIZE = 10;
	private static final int MAX_ATTEMPS_TO_RECOVER = 10;
	private static int attemptsToRecover = 0;
	private static int baseAtError = -3;
	private static DatagramSocket clientSocket;
	private static int relayport;
	private static int destport;
	static int sentNotAcked = -1;

	static Random r = new Random();
	static int seq = r.nextInt(100);//the first sequence number is randomly chosen
	static int check = seq;//used to check whether
	static final int FIRST_SEQ = seq;
	static int ack = 0;
	static int base = -1;
	static String[] buffer;
	static boolean conn = false;//Indicate a successful connection.
	static boolean synack = false;
	static boolean morePacksToSend;
	static boolean recoveredFromError = true;
	static int delay = 2000;
	static boolean timeOut = false;
	static boolean CorruptAck = false;//In this case wrong ack number.
	static final int MAX_CONSECUTIVE_WRONG_ACKS = 3;//If too many wrong acks arrive consecutively then re-sending stores and waits for a correct ack. 
	static int consecutiveWrongAcks = 0;
	static boolean tooManyWrongs = false;
	static boolean store;//Prevent overloading of the channel.



	public RIPClient() throws Exception {
		state = CONN_SET_UP;
		clientSocket = new DatagramSocket();
		relayport = 11111;
		destport = 9876;
		startProgram();
	}
	/**
	 *Start executing of FSM
	 * @throws Exception
	 */
	private void startProgram() throws Exception {
		boolean alive = true;
		while(alive){
			if(state == CONN_SET_UP){
				for (int i = 0; i < 6; i++) {
					try {
						if(handShake()){//First, initial session with server.
							conn = true;
							System.out.println("Huston, we have connection!");
							state = GETINPUT;//Once session accepted, get input.
							break;
						}
						else{//Retry.
							sentNotAcked--;
							System.out.println("Couldnt establish connection. Retring: " + (i+1));
						}
					}
					catch (Exception e) {
						delay += 1000;
					}
				}
				if(!conn){//After six attempts to connect, the process gives up.
					recoveredFromError = false;
					state = TEAR_DOWN;
				}
			}
			else if(state == GETINPUT){
				getInput();//read data from file and populate buffer.
				morePacksToSend = true;
				start();// Starts a separate thread always seeking to send packets.
				state = LISTENING;
			}
			else if(state == LISTENING){
				if(base >= buffer.length-1){// All pack's sent. FIN procedure
					run();//Initiates FIN procedure
					if(receive()){
						state = TEAR_DOWN;//will loop until "FINACK" is received - the system will then exit in the receiving method.
					}
					else{
						state = LISTENING;
					}
				}
				else if(base < buffer.length-1){//Are there more packets to send?
					if(receive() == true){ //if current ack is correct (correct sequence number) and not timeout
						delay = 2000;
						System.out.println("Correct ack. Time out is now " + delay + " msec.");
						attemptsToRecover = 0;
					}
					else {//something went wrong; timeout or lost
						if(CorruptAck){//Wrong ack number,  packet or ack lost
							consecutiveWrongAcks ++;
							if(consecutiveWrongAcks >= MAX_CONSECUTIVE_WRONG_ACKS){
								tooManyWrongs = true;
							}
							System.out.println("Wrong Ack, Dammit!");
							CorruptAck = false;
							state = LISTENING;
						}
						else {//time out 
							state = RECOVERING;
						}
					}
				}
			}
			else if(state == RECOVERING){//A time out occurred
				System.out.println("Recovering");
				if(base >= buffer.length-1){//Last packet was sent
					recoveredFromError = true;
					run();//Initiate "FIN" procedure
					delay += 2000;
					state = TEAR_DOWN;
				}
				else if(base == baseAtError){//if base wasn't incremented then we are trying to recover form an all ready existing error.
					attemptsToRecover++;//increment the attempts to recover.
					if(attemptsToRecover > 0){//increase timeOut
						delay += 2000;
						System.out.println("Incrementing time out to: " + delay + " msec.");
					}
					if(attemptsToRecover == MAX_ATTEMPS_TO_RECOVER){//max 5 attempts to recover.
						recoveredFromError = false;
						state = TEAR_DOWN;
					}
					else{
						state = LISTENING;//we have more attempts to recover left.
					}
				}
				else{
					baseAtError = base;//this is the first attempt to recover from the current (last) time out event
					state = LISTENING;// we still have more attempts before the channel pronounce dead (after 5 miss-fortunate attempts).
				}
			}
			else if(state == TEAR_DOWN){
				if(!recoveredFromError){// we arrived here after 5 non-successful recovery attempts.
					System.err.println("All attempts to recover failed." + "\n" +
							"Possible reasons: Connection failure or server failure or too long delays in current chanel." + "\n" + "" + "Exiting");
					close();
				}
				else{
					System.out.println("All packets sent.");
					if(!receive()){//a call to the receiving method to look for "FINACK" from the server.
						state = RECOVERING;
						if(delay > 6000){
							System.err.println("Time out while waiting for 'FINACK'.Exiting.");
							close();
						}
					}
					else {
						state = LISTENING;// will start FIN procedure
					}
				}
			}
		}
	}
	/**
	 *Hand Shake
	 * @return
	 * @throws Exception
	 */
	static boolean handShake() throws Exception{

		String handShake = "SYN";//Initiate call to server with "SYN" message. 
		InetAddress relayAddress = InetAddress.getByName("localhost");
		InetAddress destAddress = InetAddress.getByName("localhost");

		DatagramPacket sendPacket = RelayPakke.storSendPakke(handShake, destAddress, destport,relayAddress,relayport,seq,ack);
		clientSocket.send(sendPacket);
		seq++;
		sentNotAcked++;
		System.out.println("CLIENT: Sent: " + handShake);
		if(receive()){
			if(synack) {
				return true;
			}
		}
		return false;
	}
	/**
	 *Reads the text file an populates the sending buffer with data.
	 * @throws Exception
	 */
	static void getInput() throws Exception{
		System.out.println("Getting input");
		File f = new File("The road not taken");
		Scanner in = new Scanner(f);
		ArrayList<String> words = new ArrayList<String>();
		while(in.hasNextLine()) {
			String input = in.nextLine();
			String[]inputArray = input.split(" ");
			for (int i = 0; i < inputArray.length; i++) {
				words.add(inputArray[i]);
			}
		}
		buffer = new String[words.size()];
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = words.get(i);
		}
		return;
	}
	/**
	 *Starts the sending thread.
	 */
	public synchronized void run(){
		while(morePacksToSend){
			int tempBase = base;//current base 
			if(base >= buffer.length-1){//all packets were successfully sent. now send "FIN".
				morePacksToSend = false;
				System.out.println("All packets sent. Initializing connection close.");
				state = CLOSING;
			}
			InetAddress relayAddress = null;
			try {
				relayAddress = InetAddress.getByName("localhost");
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			InetAddress destAddress = null;
			try {
				destAddress = InetAddress.getByName("localhost");
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			if(state == CLOSING){//"FIN" procedure
				System.out.println("SENDING FIN");
				String sentence = "FIN";
				DatagramPacket sendPacket = RelayPakke.storSendPakke(sentence, destAddress, destport,relayAddress,relayport,seq,ack);
				try {
					clientSocket.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(timeOut){//Correct ack was not received in time. Re-send non-acked packets. (max packets allowed in the pipeline is MAX_WINDOW_SIZE) 
				ack -= sentNotAcked;//Rewind ack number to the last correct 
				if(seq > FIRST_SEQ +1){
					seq = FIRST_SEQ + base+1;//GO-BACK-N
				}
				sentNotAcked = 0;//GO-BACK-N. Re-send non-acked packets: '0' = base = last correct received ack. 
				timeOut = false;
			}
			if(base + sentNotAcked > -1){//start after handshake: base = 0, sentNotAcked = 0.
				if((base + sentNotAcked) < (base + MAX_WINDOW_SIZE) && base + sentNotAcked < buffer.length){//as long as there are more allowed pack's in the pipeline
					String sentence = buffer[base + sentNotAcked];//get data from buffer
					DatagramPacket sendPacket = RelayPakke.storSendPakke(sentence, destAddress, destport,relayAddress,relayport,seq,ack);
					try {
						clientSocket.send(sendPacket);
					} catch (IOException e) {
						e.printStackTrace();
					}
					ack++;//Increment ack to the next
					if(seq <= FIRST_SEQ + buffer.length){
						seq++;//First sequence is incremented after handshake, the rest will be incremented here
					}
					sentNotAcked++;//this packet is not acked yet
					if(delay > 6000 && tooManyWrongs){//a form for congestion control. Let sending buffer (used by the Relay channel) clean up his act before sending more pack's in the channel.
						store = true;
					}
					if(sentNotAcked == (MAX_WINDOW_SIZE-1)){
						while(tempBase == base){//no need to re send, over and over again, the last allowed packet.
							try {
								currentThread();
								Thread.sleep(100);
							}
							catch (InterruptedException e) {
								e.printStackTrace();
							}

							if(timeOut && !store){
								break;
							}
							if(base > tempBase){
								break;
							}
						}
					}
				}
			}
		}
	}
	/**
	 * Receive packet from server.
	 * Check for ack sequence number, if correct then increment base, else if time out enter recovery state.
	 * when last packet arrive, containing "FINACK" from server, then call close().
	 * @return
	 * @throws IOException
	 */
	public static synchronized boolean receive() throws IOException {
		byte[] receiveData = new byte[1024];

		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.setSoTimeout(delay);
		try {
			clientSocket.receive(receivePacket);
		}
		catch (SocketTimeoutException s) {
			timeOut = true;
			System.err.println("A timeout ocurred.....did not get packet from server");
			return false;//will cause state transition to recovery state.
		}
		String in = new String(receivePacket.getData());
		String[] fs = in.split(";");//will store the respond from the server


		if(fs[fs.length-1].contains("FINACK")){//last packet from server
			System.out.println("GOT FINACK");
			close();

		}
		if(fs[fs.length-1].contains("SYNACK")){//The server puts its data in cell[array.length -1].The first ack from the server will include "SYNACK" - connection accepted.
			System.out.println("GOT SYNACK");
			synack = true;//Handshake process successful
		}
		if(check <= Integer.parseInt(fs[fs.length-2])){//did the server echoed back the correct ack-sequence? correct or cumulative.
			if(sentNotAcked > 0){//an ack is correctly received the the number of un-acked pack's is reduced.
				sentNotAcked--;
			}
			base = (Integer.parseInt(fs[fs.length-2]) - FIRST_SEQ);//ack is correct. Increment base.
			check = Integer.parseInt(fs[fs.length-2])+1;//ack is correct. Increment check.
			tooManyWrongs = false;
			consecutiveWrongAcks = 0;
			store = false;
			return true;//continue the circle of listening to server responds.
		}
		CorruptAck = true;
		System.out.println("Expected Ack: " + check +  "  returned Ack: " + fs[fs.length-2] + " .");
		return false;//Ack was'nt correct - will cause state transition to recovery state.
	}
	/**
	 * close socket and exiting system.
	 */
	public static void close(){
		clientSocket.close();
		System.exit(0);
	}
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception

	{
		new RIPClient();

	}
}
