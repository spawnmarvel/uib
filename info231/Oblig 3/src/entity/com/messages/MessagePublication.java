package entity.com.messages;

/**
 * This class gets an array of all messages for a given date
 * removes out dated messages and publish the relevant messages according to their priority
 * high priority  first. 
 * @author dro068 (David).
 * 
 *
 */

public class MessagePublication {
	private MessageArrayD mA;
	private LinkedPriorityQueue<PrioritizedLinkedNode<Mess>> lpq;
	private int frontMessID;
	/**
	 * Constructor.
	 * @param inArray
	 */
	public MessagePublication(MessageArrayD inArray){
		mA = inArray;
		removeOutDatedMessage();
		makePrioritizedQueue();	
		setFrontMessID();
	}
	/**
	 * Removes out dated messages - an out dated massage will receive "0" priority. 
	 *A message with priority 0 will never be published.
	 */
	public void removeOutDatedMessage(){
		for (int i = mA.getLast();i>=0; i-- ){
			if ((mA.getList()[i].getImportance()) == 0){
				mA.remove(i);
			}
		}
	}
	/**
	 * places the messages in the queue according to theirs priority.
	 */
	public void makePrioritizedQueue(){
		lpq = new LinkedPriorityQueue<PrioritizedLinkedNode<Mess>>();
		for (int i = 0; i<= mA.getLast(); i++){
			PrioritizedLinkedNode<Mess> pln = new PrioritizedLinkedNode<Mess>(mA.getList()[i], mA.getList()[i].getImportance());
			lpq.enqueue(pln, pln.getPriority());
		}
	}
	/**
	 * returns the messages to be appended on the screen according to theirs priority.
	 * @return String - the message text.
	 */
	public String publicAllMessages(){

		String s = "";

		if (!lpq.empty()){
			s +=((Mess) ((PrioritizedLinkedNode<Mess>)lpq.front()).getData()).toString();
			setFrontMessID();
			lpq.dequeue();
		}
		else{
			s = "Ingen flere meldinger for denne dagen";
		}
		return s;
	}
	/**
	 * 
	 */
	public void setFrontMessID(){
		if(!lpq.empty()){
		frontMessID = lpq.front().getData().getMessageId();
		}
	}
	/**
	 * 
	 */
	public int getFrontMessID(){
		return frontMessID;
	}
}

