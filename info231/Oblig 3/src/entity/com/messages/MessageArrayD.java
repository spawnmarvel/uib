package entity.com.messages;

/**
 * This class creates an array to hold the messages.
 * @author Curriculum book "Modern software development using Java".
 * Implemented by dro068.
 *
 */

public class MessageArrayD{
	private static final int OFF_LIST = -1;
	private static int ARRAY_SIZE = 50;
	private Mess[] messageList;
	private int last;
	private int cur;
	
	/**
	 *Constructor.
	 */
	public MessageArrayD(){
		messageList = new Mess[ARRAY_SIZE];
		last = OFF_LIST;
		cur = OFF_LIST;
	}
	/**
	 * gives the message its counter position..
	 * @param bm
	 */
	public void addToList(Mess bm){
		cur = cur + 1;
		addToList(bm, cur);
	}
	/**
	 *adds to list according to the given position. 
	 * @param bm
	 * @param pos
	 */
	public void addToList(Mess bm, int pos){
		if (last == OFF_LIST){
			messageList[0] = bm;
			last = 0;
		}
		else{
			if (last == ARRAY_SIZE - 1){
				doubleArraySize();
			}
			for (int i = last; i>= pos; i--){
				messageList[i + 1] = messageList[i];
			}
			messageList[pos] = bm;
			last = last + 1;
			cur = pos;
		}
	}
	/**
	 * gets the position of the message to be removed.
	 * sends the message with its current position to the removing method.
	 */
	public void remove(){
		remove(cur);
	}
	/**
	 *Removes a message by the given position.
	 * @param pos
	 */
	public void remove (int pos){
		if (pos != OFF_LIST){
			for (int i = pos; i < last; i++){
				messageList[i] = messageList[i+1];
			}
			if (last == pos){
				cur = OFF_LIST;
			}
			last = last - 1;
		}
	}
	/**
	 * Doubles the size of the array when needed.
	 */
	public void doubleArraySize(){
		ARRAY_SIZE = ARRAY_SIZE * 2;
		Mess[] temp = new Mess[ARRAY_SIZE];
		for (int i = 0; i <= last; i++){
			temp[i] = messageList[i];
		}
		messageList = temp;
	}
	/**
	 *returns the list of messages.
	 * @return message list.
	 */
	public Mess[]getList(){
		return messageList;
	}
	/**
	 *returns the position of the last element (the actual number of elements in the list)
	 * @return int - the last element's position.
	 */
	public int getLast(){
		return last;
	}
	/**
	 * returns a string description of each message in the list.
	 */
	public String toString(){
		String s = "";
		for(int i = 0; i <= last; i++){
			s += messageList[i].toString();
			s += "\n";
		}
		return s;
	}
}

