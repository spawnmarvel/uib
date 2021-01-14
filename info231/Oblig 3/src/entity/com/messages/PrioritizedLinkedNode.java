package entity.com.messages;

/**
 * The class creates a node that holds messages.
 * @author Curriculum book "Modern software development using Java"
 * Implimented by dro068.
 *
 * @param <Mess>
 */

public class PrioritizedLinkedNode<Mess> {
	private int priority;
	private Mess data;
	private PrioritizedLinkedNode<Mess> next;
	/**
	 * Constructor.
	 * @param newData
	 * @param p
	 */
	public PrioritizedLinkedNode(Mess newData, int p){
		data = newData;
		priority = p;
		next = null;
	}
	/**
	 * returns the data stored in a node.
	 * @return data (Text)
	 */
	public Mess getData(){
		return data;
	}
	/**
	 * get the next node (if such exists.)
	 * @return next Node.
	 */
	public PrioritizedLinkedNode<Mess> getNext(){
		return next;
	}
	/**
	 * sets data in the node.
	 * @param newData
	 */
	public void setData(Mess newData){
		data = newData;
	}
	/**
	 * set new node as next.
	 * @param newNext
	 */
	public void setNext(PrioritizedLinkedNode<Mess> newNext){
		next = newNext;
	}
	/**
	 * sets the priority (the nodes will later be organized according to their priority
	 * @param newPriority
	 */
	public void setPriority(int p){
		priority = p;
	}
	/**
	 * returns the priority of that message.
	 * @return int - The priority.
	 */
	public int getPriority(){
		return priority;
	}

}
