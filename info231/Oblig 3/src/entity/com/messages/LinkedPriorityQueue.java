package entity.com.messages;

/**
 * This class creates a queue based on message's priority level.
 * @author Curriculum book "Modern software development using Java".
 * Implemented by dro068.
 *
 * @param <T>
 */

public class LinkedPriorityQueue<T>  {
	public static final int MIN_PRIORITY = 0;

	private PrioritizedLinkedNode<T> front;
	private PrioritizedLinkedNode<T> back;
	private int size;

	/**
	 * Constructor.
	 */
	public LinkedPriorityQueue(){
		front = null;
		back = null;
		size = 0;
	}

	/**
	 * puts a new node in the queue according to priority level.
	 * @param data
	 * @param priority
	 */
	public void enqueue (T data, int priority){
		PrioritizedLinkedNode<T> cur = front;
		PrioritizedLinkedNode<T> prev = null;
		PrioritizedLinkedNode<T> newNode = new PrioritizedLinkedNode<T>(data, priority);

		if (back == null) {
			front = newNode;
			back = newNode;
		}
		else {
			while (cur != null && cur.getPriority() >= priority){
				prev = cur;
				cur = cur.getNext();
			}

			if (cur == null){
				back.setNext(newNode);
				back = newNode;
			}
			else if (prev == null){
				newNode.setNext(front);
				front = newNode;
			}
			else {
				prev.setNext(newNode);
				newNode.setNext(cur);
			}
		}
		size = size + 1;
	}
	/**
	 * removes the front (first) node in the queue.
	 */

	public void dequeue() {
		front = front.getNext();
		if (front == null){
			back = null;
		}
		size = size - 1;
	}
	/**
	 * Returns the font(first) node.
	 * @return node - the first node. 
	 */
	public T front() {
		return front.getData();
	}
	/**
	 * Returns the last node in the queue.
	 * @return Node - the last node.
	 */
	public T back() {
		return back.getData();
	}
	/**
	 * Returns true if the queue is empty.
	 * False otherwise.
	 * @return boolean .
	 */
	public boolean empty() {
		return size == 0;
	}
	/**
	 * Returns false since the queue is never full.
	 * @return boolean.
	 */
	public boolean full() {
		return false;
	}
	/**
	 * Returns the size (number of elements) of the queue.
	 * @return int - the size.
	 */
	public int getSize(){
		return size;
	}

}
