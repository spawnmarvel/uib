package com.task2;
import java.util.ArrayList;
/**
 * a class which is a graph object , and uses the two classes node and edge
 * an adjacent list
 * @author jkl070 5/11/11
 *
 */

public class Graph {
	//the node list 
	private ArrayList<Node> nodeList;
	//the edge list
	private ArrayList<Edge> edgeList;
	//an edge instance variable
	private Edge edge;

   /**
    * constructor for the graph
    */
	public Graph() 	{
		nodeList = new ArrayList<Node>();
		edgeList = new ArrayList<Edge>();

	}
    /**
     * runs in O(1) time
     * add s a node to the graph
     * @param id, the id of the node
     * @param name, the name of the node
     */
	public void addNode(int id, String name){
		Node n = new Node(id, name);
		nodeList.add(n);
	}
    /**
     * runs in O(1) time
     * add a node to the node list of the graph and store it in the 
     * index position, for later use of fast retrieval
     * @param v
     */
	public void addNode(Node v){
	      nodeList.add(v.getId(),v);		
	}
    /**
     * fast retrieval O(1) ,due to array list get method, 
     * since the node is stored at the index position according to the id
     * check to see if a node has a given id
     * @param id
     * @return
     */
	public boolean hasNodeId(int id){
		try {
			return nodeList.get(id)!= null;
		}
		//catch the exception if the graph does not have a node with the id
		//return false
		catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
	/**
	 * runs in O(1) time
	 * gets the node from the array list and get it from the index position
	 * @param id, the id input which a node has
	 * @return, return the nodes is
	 */
	public Node getNode(int id){
		return nodeList.get(id);
	}
    /**
     * runs in O(1) time
     * add an edge by using only the id of two nodes
     * the id of the edge is sat to the size of the edge list, 
     * and the weight of the edge is 0 by default
     * @param ida
     * @param idb
     */
	public void addEdge(int ida, int idb){
		Node a = getNode(ida);//get the a node by id
		Node b = getNode(idb);//get the b node by id
		edge = new Edge(a,b,edgeList.size()+1 ,0);//make a new edge
		edgeList.add(edge);//graph adds the edge
		a.addEdge(edge);//a node adds the edge
		b.addEdge(edge);//b node adds the edge
	}
    /**
     * runs in O(1) time
     * get the number of vertices in the graph
     * @return, the number of vertices , node list size
     */
	public int numVertices(){
		return nodeList.size();
	}
    /**
     * method to see if a graph contains a specific node,
     * runs in O(1) due to the array list get method, since
     * the node is stored at the index position according to the id of the node
     * @param v, the node to check if it is added or not
     * @return, true if it is in list/graph, false otherwise
     */
	public boolean containsNode(Node v) {
		try {
			return nodeList.get(v.getId()) != null;
		}
		//catch the exception if the node is not in the graph
		//and return false
		catch (IndexOutOfBoundsException e) {
			return false; 
		}
	}
    /**
     * runs in O(1) time
     * get the number of edges in the graph
     * @return, the number of edges, edge list size
     */
	public int numEdges() {
		return edgeList.size();
	}
    /**
     * runs in O(1) time
     * gets the vertices in the graph 
     * @return, the vertices in the list
     */
	public Iterable<Node> vertices() {
		return nodeList;
	}
	public static void main(String [] args) {
		Graph g = new Graph();
		Node a = new Node(0,"nøstet");
		Node b = new Node(1,"bergen");
		Node c = new Node (2,"sandviken");
		//add the nodes to the graph
		g.addNode(a);
		g.addNode(b);
		g.addNode(c);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		//node to string
		a.toString();
		b.toString();
		c.toString();
		//print some graph methods
		System.out.println("graph contains node a: "+g.containsNode(a));
		System.out.println("the number of edges connected: "+g.numEdges());
		System.out.println("the numbers of nodes: "+g.numVertices());
		System.out.println("has the graph a node with id 1: "+g.hasNodeId(1));
		System.out.println("is node a: "+ a.getElement()+" neighbor with b: "+b.getElement()+" "+a.isNeighbor(b));
		System.out.println(g.getNode(1));
		System.out.println(g.getNode(2));
	}

}








