package oblig1.interfaces;

/**
 * A node which is a BinaryNode and an ElementNode
 * @author Martin Vatshelle
 *
 * @param <E> the type of element to store
 * @param <N> the type you want the methods to return, implementations would typically set N to the name of the implementing class.
 */
public interface ElementBinaryNode<E,N extends ElementBinaryNode<E,?>> extends ElementNode<E>, BinaryNode<N> {

}
