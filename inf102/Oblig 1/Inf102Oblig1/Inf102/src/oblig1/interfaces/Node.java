package oblig1.interfaces;


/**
 * A general node used in any kind of graphs.
 * Each node is supposed to belong to only one graph.
 * The nodes of a graph should have id's from 0 to n-1, hence all id's are unique
 * The id is used to easily access data specific to a node.
 * @author Martin Vatshelle
 */
public interface Node
{
	/**
	 * @return the id of this node
	 */
	public int getId();

	/**
	 * The id should be a positive integer
	 * @param id the new id of this node
	 * @return false if id is invalid, true if the id changed, false otherwise
	 */
	public boolean setId(int id);
}
 