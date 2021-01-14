package com.task2;

import com.task2.exceptions.*;
/**
 * a class which represents an edge object for use in a graph
 * @author jkl070 4/11/11
 *
 */

public class Edge{
	private Node from, to;
	private int Id;
	private int weight;
	private Node [] endVertics;
   /**
    * constructor of an edge
    * @param a, the node origin   
    * @param b, the node destination
    * @param id, the id of the edge
    * @param weight, the weight of the edge
    */
	public Edge(Node a, Node b, int id, int weight)	{
		from = a;
		to = b;
		Id = id;
		this.weight = weight;
		endVertics = new Node[2];
		endVertics[0] = to;//end vertices position 0 is set to the origin node 
		endVertics[1] = from;//end vertices position 1 is set to the destination node 
	}
    /**
     * runs in O(1) time
     * gets the id of the edge
     * @return, the id of the edge
     */
	public int getId() {
		return Id;
	}
    /**
     * runs in O(1) time
     * returns opposite node
     * @param v, the node to get the opposite from
     * @return the opposite node
     * @throws InvalidPositionException, if the vertices do not exist
     */
	public Node opposite(Node v) throws InvalidPositionException {
		if(v == from) {
			return to;
		}
		else if(v == to){
		return from;	
		}
		else {
			throw new InvalidPositionException("No such vertex exists...");
		}
	}
    /**
     * runs in O(1) time
     * return the end vertices of a edge in an array 
     * @return, return endVertics array
     */
	public Node[] endVertices() {
		return endVertics;
	}
    /**
     * runs in O(1) time
     * gets the element of an edge, in this case an integer variable
     * @return, return the integer weight
     */
	public Integer getElement() {
		return weight;
	}
    /**
     * runs in O(n) time
     * a to string representation of the edge object
     */
	public String toString() {
		String s = " ";
		s+= "Edge " + "id = "+ getId()+","+ " weight = " + getElement()+".";
		s+= " from: " + from.getElement()+" id: "+ from.getId();
		s+= " to: " + to.getElement() + " id: " + to.getId()+"\n";
		return s;
	}

}









