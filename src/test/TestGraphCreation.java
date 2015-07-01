package test;

import static org.junit.Assert.*;
import graphs.Graph;
import graphs.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class TestGraphCreation {

	@Test
	public void testNodesToGraph(){
		Node a = new Node();
		Node b = new Node();
		Node c = new Node();
		Node d = new Node();
		a.makeNeighbors(b);
		b.makeNeighbors(c);
		c.makeNeighbors(d);
		d.makeNeighbors(b);
		
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(a);
		nodes.add(b);
		nodes.add(c);
		nodes.add(d);
		
		Graph g = new Graph(nodes);
		
		int[][] expectedAdjacencyList = {{0, 1, 0, 0},
										 {1, 0, 1, 1},
										 {0, 1, 0, 1},
										 {0, 1, 1, 0}};
		
		boolean deepEquals = Arrays.deepEquals(g.getAdjacencyList(), expectedAdjacencyList);
		
		assertTrue(deepEquals);
	}
	
}
