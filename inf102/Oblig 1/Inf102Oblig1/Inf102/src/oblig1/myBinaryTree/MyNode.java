package oblig1.myBinaryTree;
import oblig1.exceptions.BoundaryViolationException;

import oblig1.interfaces.BinaryNode;
import oblig1.interfaces.ElementBinaryNode;
/**
 * A class which is a binary node
 * @author Espen
 *
 * @param <E>
 */


import oblig1.exceptions.BoundaryViolationException;

import oblig1.interfaces.BinaryNode;
import oblig1.interfaces.ElementNode;
import oblig1.exceptions.BoundaryViolationException;
import oblig1.interfaces.BinaryNode;
import oblig1.interfaces.ElementBinaryNode;
import oblig1.exceptions.BoundaryViolationException;

import oblig1.interfaces.BinaryNode;
import oblig1.interfaces.ElementNode;
import oblig1.exceptions.BoundaryViolationException;
import oblig1.interfaces.BinaryNode;
import oblig1.interfaces.ElementBinaryNode;
/**
 * A class which is a binary node for use in a binary tree,
 * due to the first assignment in inf102
 * @author jkl070
 * Date 17/09/2011
 *
 * @param <E>
 */

public class MyNode implements ElementBinaryNode<Character, MyNode> {
	private MyNode parent;
	private MyNode left;
	private MyNode right;
	private Character element;
	private int id;

	/**
	 * The constructor of the binary node, my node class
	 * @param theElement
	 * @param parent
	 * @param leftChi
	 * @param rightChi
	 * @param Id
	 */
	public MyNode( Character theElement,MyNode parent, MyNode leftChi, MyNode rightChi, int Id) {
		setElement(theElement);
		setParent(parent);
		setLeft(leftChi);
		setRight(rightChi);
		setId(Id);
	}

	/**
	 * The constructor of one node, with an id
	 * @param theElement
	 */
	public MyNode(Character theElement,int Id) {
		setElement(theElement);
		setId(Id);

	}

	/**
	 * The constructor of one node, with an element
	 * @param theElement
	 */
	public MyNode(Character theElement) {
		setElement(theElement);
	}
	/**
	 * Sets the parent of a child
	 * @param parent
	 */
	//runs in O(1) time
	public void setParent(MyNode parent) {
		this.parent = parent;

	}
	/**
	 * Sets the left child of a node
	 * @param left
	 */
	//runs in O(1) time
	public void setLeft(MyNode left) {
		this.left =  left;
	}
	/**
	 * Sets the right child of a node
	 * @param right
	 */
	//runs in O(1) time
	public void setRight(MyNode right) {
		this.right =  right;
	}
	@Override
	//runs in O(1) time
	public MyNode left() throws BoundaryViolationException {
		String hasNoLeft = "The node has no left child/MyNode";

		if(left != null) {
			return left;
		}
		else {
			throw new BoundaryViolationException(hasNoLeft);
		}
	}
	/**
	 * Returns true if the node has left, false otherwise
	 * @return boolean
	 */
	//runs in O(1) time
	public boolean isLeft() {
		return left !=null;
	}

	/**
	 * Returns true if the node has left, false otherwise
	 * @return boolean
	 */
	//runs in O(1) time
	public boolean isRight() {
		return right !=null;
	}
	/**
	 * Returns true if the node is a parent, false otherwise
	 * @return boolean
	 */
	//runs in O(1) time
	public boolean isParent() {
		return parent != null;
	}

	@Override
	//runs in O(1) time
	public MyNode parent() throws BoundaryViolationException {
		String hasNoParent = "The node has no parent/MyNode";
		if(parent != null) {
			return parent;
		}
		else {
			throw new BoundaryViolationException(hasNoParent);
		}
	}
	@Override
	//runs in O(1) time
	public MyNode right() throws BoundaryViolationException {
		String hasNoRight = "The node has no right child/MyNode";
		if(right != null) {
			return right;
		}
		else {
			throw  new BoundaryViolationException(hasNoRight);
		}
	}

	@Override
	//runs in O(1) time
	public int getId() {
		return id;
	}
	@Override
	//runs in O(1) time
	public boolean setId(int id) {
		if(id >= 0) {
			this.id = id;
			return true;
		}
		else {
			id = 0;
			return false;
		}
	}
	@Override
	//runs in O(1) time
	public Character getElement() {
		return element;
	}
	@Override
	//runs in O(1) time
	public boolean setElement(Character e) {
		if(e != null) {
			element =   e;
			return true;
		}
		return false;
	}
	/**
	 * Prints out a string representation of the nodes
	 * element.(in this case a character)
	 */
	//runs in O(1) time
	public String toString() {
		return element.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MyNode node1 = new MyNode('a',1);
		MyNode node2 = new MyNode('b');
		MyNode node3 = new MyNode('c');
		MyNode node4 = new MyNode('d');
		MyNode node5 = new MyNode('e', 5);
		node1.setLeft(node2);
		node1.setRight(node3);
		node3.setParent(node1);
		System.out.println("node1 has leftnode: " +node1.left()+", " + " node1 has rightnode: "  + node1.right());
		System.out.println("node3 has parentnode: "+ node3.parent());
		node3.setLeft(node4);
		node3.setRight(node5);
		System.out.println("node3 has leftnode: " + node3.left() +", " + " node3 hasrightnode: " + node3.right());
		System.out.println("node1 has id: "+node1.getId()+ ", "+ "node1 has element: " + node1.getElement());
		System.out.println("node5 has id: "+node5.getId()+ ", "+ "node5 has element: " + node5.getElement());




		System.out.println("*****************************");
		System.out.println("        node1:a,1        ");
		System.out.println("  node2            node3 ");
		System.out.println("          node4          node5:e,5");




	}

}

