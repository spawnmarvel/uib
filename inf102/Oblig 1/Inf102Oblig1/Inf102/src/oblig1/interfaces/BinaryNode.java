package oblig1.interfaces;

import oblig1.exceptions.*; 

/** 
 * An interface for a binary node, having zero, one, or two children.
 * All nodes should have a unique id.
 * @author Martin Vatshelle
 * @param <N> the type you want the methods to return, implementations would typically set N to the name of the implementing class.
 */
public interface BinaryNode<N extends BinaryNode<?>> extends Node {

	/**
	 * @return the parent of this node.
	 * @throws BoundaryViolationException if this node does not have any parent.
	 */
	public N parent() throws BoundaryViolationException;

	/**
	 * @return the left child of this node.
	 * @throws BoundaryViolationException if this node does not have a left child.
	 */
	public N left() throws BoundaryViolationException;

	/**
	 * @return the right child of this node.
	 * @throws BoundaryViolationException if this node does not have a right child.
	 */
	public N right() throws BoundaryViolationException;
}
