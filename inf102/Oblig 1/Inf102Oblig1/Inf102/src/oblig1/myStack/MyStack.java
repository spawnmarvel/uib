package oblig1.myStack;
import java.util.ArrayList;
import oblig1.exceptions.EmptyStackException;
import oblig1.interfaces.Stack;
/**
 * This class is a generic stack constructed with an array list from java.util
 * It can hold any object. It implements the stack interface from assignment 1 interfaces,
 * the methods witch are overridden are not commented. 
 * @author jkl070
 * Date 14/09/2011
 *
 * @param <E>
 */

public class MyStack<E> implements Stack<E> {
	private ArrayList<E> list;//The generic list
	private String empty;//a string error messages


	/**
	 * The constructor of myStack
	 * initializes the generic array
	 * initializes an error string for EmptyStackException
	 */
	public MyStack() {
		list = new ArrayList<E>();
		empty = "MyStack stack is empty(myOwnException)";
	}

	@Override
	//runs in O(1)
	public boolean isEmpty() {
		if(list.size() <= 0){ 
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	//The pop method runs in constant time O(1)
	public E pop() throws EmptyStackException {
		if(isEmpty()) {
			throw new EmptyStackException(empty);
		}
		E temp = list.get(list.size()-1);
		list.remove(list.size()-1);
		return temp;
	}
	
	@Override
	//The pop method runs in constant time O(1)
	public void push(E element) {
		list.add(element);

	}

	@Override
	//runs in O(1)
	public int size() {
		return list.size();
	}
	
	@Override
	//The pop method runs in constant time O(1)
	public E top() throws EmptyStackException {
		if(isEmpty()) {
			throw new EmptyStackException(empty);
		}
		return list.get(list.size()-1);
	}
	/**
	 * A string representation of the MyStack object,
	 * the toString is overridden
	 */
	//runs in O(N)
	public String toString() {
		String s = "[MyStack contains: ";
		for(E element : list) {
			s+= ": "+element ;
		}
		s+="]"+ "[StackInfo: size: " +list.size()+ " isEmpty: " + isEmpty();
		return s+= "]"; 
	}

	/**
	 * @param args
	 * A test method for MyStack
	 */
	public static void main(String[] args) {
				MyStack<String> m = new MyStack<String>();
				System.out.println("Test stack");
				m.push("john");
				m.push("nina");
				m.push("steven");
				m.push("liz");
				m.push("carl");
				m.push("petrina");
				System.out.println(m.toString());
				
	}
}






