package graphs;

import java.util.HashSet;
import java.util.Set;

import utilities.Namer;

public class Node {

	private static int nodeId = 0;
	
	private String name;
	private Set<Node> neighbors;
	
	public Node(){
		this.name = Namer.intToString(nodeId++);
		this.neighbors = new HashSet<Node>();
	}
	
	public void makeNeighbors(Node other){
		other.neighbors.add(this);
		this.neighbors.add(other);
	}
	
	public String toString(){
		return name;
	}
	
}
