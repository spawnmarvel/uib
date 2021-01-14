package oblig1.interfaces;

import oblig1.interfaces.Node;

/**
 * A node that can store an element.
 * @author Martin Vatshelle
 * @param <E> the type of element to store
 */
public interface ElementNode<E> extends Node {

	/**
	 * @return the element stored at this node
	 */
	public E getElement();

	/**
	 * @param e the new element to be stored
	 * @return true if the element changed, false otherwise
	 */
	public boolean setElement(E e);
}
