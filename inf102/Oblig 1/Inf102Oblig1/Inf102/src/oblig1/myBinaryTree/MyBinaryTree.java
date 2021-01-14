package oblig1.myBinaryTree;

import java.util.ArrayList;


import java.util.Iterator;
import oblig1.exceptions.BoundaryViolationException;
import oblig1.exceptions.EmptyTreeException;
import oblig1.exceptions.InvalidPositionException;
import oblig1.interfaces.BinaryNode;
import oblig1.interfaces.ElementBinaryTree;
/**
 * A class which is a binary tree implementing the interfaces from the assignment 1
 * and have some additional methods too. The methods that are overridden are not commented. 
 * @author jkl070
 * Date 22/09/2011
 *
 * @param <E>
 */

public class MyBinaryTree implements ElementBinaryTree<Character, MyNode> {

	private MyNode root;//the root
	private ArrayList<MyNode> nodeList;//a list for adding nodes but not for the tree structure
	private int size;//the size of the tree
	private String notBelong;//InvalidPositionException

	/**
	 * The constructor of a binary tree.
	 * The root is null.
	 * Size is null.
	 * The arraylist is initialized:
	 * The tree is empty by default:
	 */
	public MyBinaryTree() {
		root = null;
		size = 0;
		nodeList = new ArrayList<MyNode>();
		notBelong = "The node does not belong to the tree";
	}
	@Override
	//runs in 0(N) time
	public boolean containsElement(Character element) {
		for(MyNode n : nodeList) {
			if(n.getElement().equals(element)) {
				return true;
			}
		}
		return false;
	}


	@Override
	//runs in 0(1) time
	public MyNode parent(BinaryNode<?> child) throws InvalidPositionException,
	BoundaryViolationException {
		String noParent = "The node does not have a parent yet/MyBinaryTree";
		MyNode nodeHasParent = (MyNode) child;
		if(!containsNode(nodeHasParent)) {
			throw new InvalidPositionException(notBelong);
		}
		else if(!nodeHasParent.isParent() && !isRoot(nodeHasParent)) {
			//will never execute here because insertLeft & insertRight takes a parent as a second argument
			throw new BoundaryViolationException(noParent);
		}
		else if(nodeHasParent.isParent()) {
			return nodeHasParent.parent();
		}
		else {
			return root;
		}
	}

	@Override
	//runs in 0(1) time
	public MyNode left(BinaryNode<?> parent) throws InvalidPositionException,
	BoundaryViolationException {
		String noLeft= "This node does not have left child/MyBinaryTree";
		MyNode parentLeft = (MyNode) parent;
		if(!containsNode(parentLeft)) {
			throw new InvalidPositionException(notBelong);
		}
		else if(!parentLeft.isLeft()) {
			throw new BoundaryViolationException(noLeft);
		}
		else {
			return parentLeft.left();
		}
	}

	@Override
	//runs in 0(1) time
	public MyNode right(BinaryNode<?> parent) throws InvalidPositionException,
	BoundaryViolationException {
		String noRight ="This node does not have a right child/MyBinaryTree";
		MyNode parentRight = (MyNode) parent;
		if(!containsNode(parentRight)) {
			throw new InvalidPositionException(notBelong);
		}
		else if(!parentRight.isRight()) {
			throw new BoundaryViolationException(noRight);
		}
		else {
			return parentRight.right();
		}
	}


	@Override
	//runs in time O(1)
	public boolean hasLeft(BinaryNode<?> parent) throws InvalidPositionException {
		MyNode parHasLeft = (MyNode) parent;
		if(!containsNode(parHasLeft)){
			throw new InvalidPositionException(notBelong);
		}

		return parHasLeft.isLeft();
	}


	@Override
	//runs in 0(1) time
	public boolean hasRight(BinaryNode<?> parent) throws InvalidPositionException {
		MyNode parHasRight = (MyNode) parent;
		if(!containsNode(parHasRight)){
			throw new InvalidPositionException(notBelong);
		}

		return parHasRight.isLeft();
	}

	@Override
	//runs in 0(1) time
	public boolean isInternal(BinaryNode<?> node) 
	throws InvalidPositionException {
		MyNode isIntern = (MyNode) node;
		if(!containsNode(isIntern)) {
			throw new InvalidPositionException(notBelong);
		}
		return hasLeft(isIntern) || hasRight(isIntern);
	}


	@Override
	//runs in 0(1) time
	public boolean isExternal(BinaryNode<?> node) {
		MyNode isExtern = (MyNode) node;
		if(!containsNode(isExtern)) {
			throw new InvalidPositionException(notBelong);
		}
		return !hasLeft(isExtern) && !hasRight(isExtern);
	}




	/**
	 * This method adds a root to the binary tree,
	 * and throws an exception if the tree already has a root.
	 * @param theRoot
	 * @return
	 * @throws InvalidPositionException
	 */
	//runs in 0(1) time
	public MyNode addRoot(MyNode theRoot) throws InvalidPositionException {
		String s = "The tree has already a root";
		if(!isEmpty()) {
			throw new InvalidPositionException(s);
		}
		else {
			root = theRoot;
			root.setParent(theRoot);
			size++;
			nodeList.add(theRoot);
			return root;
		}
	}
	@Override
	//runs in 0(1) time
	public boolean isRoot(BinaryNode<?> node) throws InvalidPositionException {
		String isRoot = "Not root";
		if(node == root) {
			return true;
		}
		else {
			throw new InvalidPositionException(isRoot);
		}
	}
	/**
	 *This method attaches a left node to a given parent
	 * @param nodeLeft
	 * @param parent
	 */
	//runs in 0(1) time
	public void insertLeft(MyNode nodeLeft, MyNode parent) {
		if(root != null){
			nodeLeft.setParent(parent);
			parent.setLeft(nodeLeft);
			nodeList.add(nodeLeft);
			size++;
		}
	}
	/**
	 * This method attaches a right node to a given parent, 
	 * @param nodeRight
	 * @param parent
	 */
	//runs in 0(1) time
	public void insertRight(MyNode nodeRight, MyNode parent) {
		if(root != null){
			nodeRight.setParent(parent);
			parent.setRight(nodeRight);
			nodeList.add(nodeRight);
			size++;
		}
	}

	@Override
	//runs in 0(1) time
	public MyNode root() throws EmptyTreeException {
		String empty = "The tree is empty";
		if(root != null) {
			return root;
		}
		else {

			throw new EmptyTreeException(empty);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	//runs in 0(1) time
	public Iterator iterator() {
		return nodeList.iterator();
	}
	@Override
	//runs in 0(1) time
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
	}

	@Override
	//runs in 0(1) time
	public int size() {
		return size;
	}

	/**
	 *This method replaces the element
	 *of a node with a new element.
	 * @param node
	 * @param element
	 */
	//runs in 0(1) time
	public void replaceElement(MyNode node, Character element) {
		node.setElement(element);
	}
	@Override
	//runs in 0(N) time
	public boolean containsNode(BinaryNode<?> node) {
		MyNode no = (MyNode) node;
		return nodeList.contains(no);
	}
	/**
	 * Prints out the elements in level numbering.
	 * A toString representation
	 */
	//runs in 0(N) time
	public String toString() {
		String s = " ";
		for(MyNode element : nodeList) {
			s+= element + ", ";
		}
		return "Elements in level numbering: " +s ;
	}

	public static void main (String [] args) {
		
		MyNode root = new MyNode('a');
		MyNode child1 = new MyNode('b');
		MyNode child2 = new MyNode('c');
		MyNode child3 = new MyNode('d');
		MyNode child4 = new MyNode('e');
		MyNode child5 = new MyNode('f');
		MyNode child6 = new MyNode('g');

		MyBinaryTree t = new MyBinaryTree();
		System.out.println("Empty tre :" +t.isEmpty());
		System.out.println("Size of empty tree: " + t.size());
		System.out.println("------------------");
		System.out.println("Connecting some nodes to the tree.... building tree:");
		t.addRoot(root);
		t.insertLeft(child1, root);
		t.insertRight(child2, root);
		t.insertLeft(child3,child1);
		t.insertRight(child4, child1);
		t.insertLeft(child5, child2);
		t.insertRight(child6, child2);
		t.replaceElement(root, 'A');
		System.out.println("The size of the tree: " + t.size());
		System.out.println("The tre representation : " + t.toString());
		
	}



}
