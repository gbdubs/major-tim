package graphs;

import java.util.HashSet;
import java.util.Set;

import utilities.Namer;

public class Node {

	private static int nodeId = 1;
	
	private String name;
	private Set<Node> neighbors;
	
	public Node(){
		this.name = Namer.intToString(nodeId++);
		this.neighbors = new HashSet<Node>();
	}
	
	public void makeNeighbors(Node other){
		if (!neighbors.contains(other)){
			other.neighbors.add(this);
			this.neighbors.add(other);
		}
	}
	
	public String toString(){
		return name;
	}

	public boolean hasNeighbor(Node nodeOther) {
		return neighbors.contains(nodeOther);
	}

	public int degree() {
		return neighbors.size();
	}
	
}
