package com.task2;

import java.util.ArrayList;
import com.task2.Edge;
/**
 * a class which represents a node object used in a graph
 * @author jkl070 4/11/11
 *
 */
public class Node{
	//the id field
	private int Id;
	//the element field
	private String elem;
	//the edge list of the node
	private ArrayList<Edge> edgeList;
	//the nodes adjacent list
	private ArrayList<Node> adj;
	//a parent node
	private Node parent;
	
	/**
	 * constructor of a node
	 * @param id, the id of the node
	 * @param element, the element of the node
	 */
	public Node(int id, String element)	{
		Id = id;
		elem = element;
		edgeList = new ArrayList<Edge>();
		adj = new ArrayList<Node>();
	}
	/**
	 * runs in O(1) time
	 * add an edge to the between to nodes to the nodes different list
	 * and adds the nodes to each others adjacent list
	 * @param e, the edge to be added
	 */
	public void addEdge(Edge e){
		edgeList.add(e);//add the edge to the nodes list of edges
		Node n = e.opposite(this);//the opposite node from the one we holding
		adj.add(n);//add the opposite node to the current nodes adjacent list
		n.adj.add(this);//add this currant node to the opposite nodes adjacent list
		n.edgeList.add(e);//add the edge to the opposite nodes edge list
		
	}
    /**
     * runs in O(1) time
     * gets the parent of a node
     * @return, the parent of a node
     */
	public Node getParent(){
		return parent;
	}
    /**
     * runs in O(1) time
     * set the parent for a given node
     * @param p, the parent to set
     */
	public void setParent(Node p){
		parent = p;

	}
	/**
	 * runs in O(1) time
	 * returns the edge list with the incident edges that belongs to a node
	 * @return, return the edge list for a node 
	 */
	public Iterable<Edge> incidentEdges() {
		return edgeList;
	}
    /**
     * runs in O(1) time
     * returns the adjacent list that belongs to a node
     * @return, return the adjacent list for a node
     */
	public Iterable<Node> neighbors() {
		return adj;
	}
    /**
     * runs in O(n) time, due to contains
     * @param v, the node to check if is is a neighbor
     * @return, true if neighbor false otherwise
     */
	public boolean isNeighbor(Node v){
		return adj.contains(v); 
		}
	/**
	 * runs in O(1) time
	 * return the id of an node
	 * @return, return the id of the node
	 */
	public int getId() {
		return Id;
	}
	/**
	 * runs in O(1) time
	 * return the element of a node, a string
	 * @return, return the element of a node
	 */
	public String getElement() {
		return elem;
	}
	/**
	 * runs in O(n) time
	 * a to string representation of an node object
	 */
	public String toString() {
		String s = "";
		System.out.println("NodeId: "+this.getId()+"."+ " Name: " + this.getElement());
		for(Edge e : edgeList) {
			s+= e.toString();
		}
		return s;

	}
	public static void main (String []args) {
		//make two nodes and add the edge
		Node a = new Node(1, "bergen");
		Node b = new Node(2, "bryggen");
		Edge one = new Edge(a,b,51,30);
		a.addEdge(one);
        //make two nodes and add the edge
		Node c = new Node(3,"Voss");
		Node d = new Node(4,"Sandviken");
		Edge four = new Edge(c,d,53, 30);
		c.addEdge(four);

		Edge three = new Edge(c,b, 52, 30);
		c.addEdge(three);

		Node e = new Node(5,"nøstet");
		Edge two = new Edge(b,e,54,30);
		b.addEdge(two);
		//print some node info
		System.out.println(a.toString());
		System.out.println(b.toString());
		System.out.println(c.toString());
		System.out.println(d.toString());
		System.out.println(e.toString());


	}
}
