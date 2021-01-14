package oblig1.interfaces;

/**
 * A BinaryTree that can store a element at each node.
 * @author Martin Vatshelle
 * @param <E> Type of elements to store at each node
 * @param <N> Type of nodes in the tree
 */
public interface ElementBinaryTree<E,N extends ElementBinaryNode<E,N>> extends BinaryTree<N>
{
	/**
	 * @param element the element to search for.
	 * @return true if the tree contains a Node with the given element, false otherwise
	 */
	public boolean containsElement(E element);

}
