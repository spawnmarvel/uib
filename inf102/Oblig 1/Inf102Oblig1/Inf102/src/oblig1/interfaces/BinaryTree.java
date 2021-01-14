package oblig1.interfaces;

import java.util.*;

import oblig1.exceptions.BoundaryViolationException;
import oblig1.exceptions.EmptyTreeException;
import oblig1.exceptions.InvalidPositionException;

/**
 * An interface for a binary tree, where each node can have zero, one,
 * or two children.
 * All nodes in the tree should have a unique id.
 * @author Michael Goodrich
 */
public interface BinaryTree<N extends BinaryNode<N>>
{
	/** Returns the number of nodes in the tree. */
	public int size();

	/** Returns whether the tree is empty. */
	public boolean isEmpty();

	/** Returns an iterator of the nodes of the tree. */
	public Iterator<? extends N> iterator();

	/** Returns the root of the tree. */
	public N root() throws EmptyTreeException;

	/** Returns the parent of a given node.
	 *  Throw InvalidPositionException if the node does not belong to this tree
	 *  Throw BoundaryViolationException if the node does not have any parent
	 */
	public N parent(BinaryNode<?> child)
		throws InvalidPositionException, BoundaryViolationException;

	/** Returns the left child of a node.
	 *  Throw InvalidPositionException if the node does not belong to this tree
	 *  Throw BoundaryViolationException if the node does not have any left child
	 */
	public N left(BinaryNode<?> parent)
		throws InvalidPositionException, BoundaryViolationException;

	/** Returns the right child of a node.
	 *  Throw InvalidPositionException if the node does not belong to this tree
	 *  Throw BoundaryViolationException if the node does not have any right child
	 */
	public N right(BinaryNode<?> parent)
		throws InvalidPositionException, BoundaryViolationException;

	/** Returns whether a node has a left child.
	 *  Throw InvalidPositionException if the node does not belong to this tree
	 */
	public boolean hasLeft(BinaryNode<?> parent) throws InvalidPositionException;

	/** Returns whether a node has a right child.
	 *  Throw InvalidPositionException if the node does not belong to this tree
	 */
	public boolean hasRight(BinaryNode<?> parent) throws InvalidPositionException;

	/** Returns whether a given node is internal.
	 *  Throw InvalidPositionException if the node does not belong to this tree
	 */
	public boolean isInternal(BinaryNode<?> node) throws InvalidPositionException;

	/** Returns whether a given node is external.
	 *  Throw InvalidPositionException if the node does not belong to this tree
	 */
	public boolean isExternal(BinaryNode<?> node) throws InvalidPositionException;

	/** Returns whether a given node is the root of the tree.
	 *  Throw InvalidPositionException if the node does not belong to this tree
	 */
	public boolean isRoot(BinaryNode<?> node) throws InvalidPositionException;

	/**
	 * Returns true if the given node is contained in the tree, false otherwise
	 * Note: really have to be same node, not just same content of the node
	 */
	public boolean containsNode(BinaryNode<?> node);
}
