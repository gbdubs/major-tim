package test;

import static org.junit.Assert.*;
import graphs.Graph;
import graphs.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import matrices.MatrixDimensionMismatchException;

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
		
		int[][] expectedAdjacencyMatrix = {{0, 1, 0, 0},
										   {1, 0, 1, 1},
										   {0, 1, 0, 1},
										   {0, 1, 1, 0}};
		
		boolean deepEquals = Arrays.deepEquals(g.getAdjacencyMatrix(), expectedAdjacencyMatrix);
		
		assertTrue(deepEquals);
	}
	
	@Test
	public void testMatrixToGraph(){
		
		int[][] matrix = {{0, 1, 0, 0},
				          {1, 0, 1, 1},
				          {0, 1, 0, 1},
				          {0, 1, 1, 0}};
		Graph g;
		try {
			g = new Graph(matrix);
		} catch (MatrixDimensionMismatchException e) {
			fail("We created a graph with a square matrix, so it should not fail.");
			return;
		}
		
		int[] expectedDegree = {1, 3, 2, 2};
		
		for(int i = 0; i < 4; i++){
			assertEquals(g.getNode(i).degree(), expectedDegree[i]);
		}
	}
	
	@Test
	public void testMismatchedDimensionGraphCreation(){
		
		int[][] matrix = {{0, 1, 0, 0},
				          {0, 1, 0, 1},
				          {0, 1, 1, 0}};
		
		boolean failure = false;
		try {
			new Graph(matrix);
			fail("The Creation of the new Graph should have been a failure, as it was a 3x4 matrix given.");
		} catch (MatrixDimensionMismatchException e) {
			failure = true;
		}
		
		assertTrue(failure);
	}
}
