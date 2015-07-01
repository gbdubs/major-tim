package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import graphs.Graph;
import graphs.Node;

import java.util.HashMap;
import java.util.Map;

import matrices.PathsToSelfGenerator;

import org.junit.Test;

public class TestPathsToSelfGenerator {

	@Test
	public void testBasicPathsToSelf(){
		Node a = new Node();
		Node b = new Node();
		Node c = new Node();
		Node d = new Node();
		Node e = new Node();
		a.makeNeighbors(b);
		a.makeNeighbors(d);
		e.makeNeighbors(c);
		e.makeNeighbors(b);
		e.makeNeighbors(d);
		c.makeNeighbors(b);
		c.makeNeighbors(d);
		
		Map<Node, int[]> expected = new HashMap<Node, int[]>();
		int[] expA = {0, 2, 0, 12, 8, 80, 104, 568, 1032, 4232};
		int[] expB = {0, 3, 2, 20, 26, 142, 258, 1058, 2322, 8154};
		int[] expC = {0, 3, 4, 19, 40, 143, 348, 1123, 2928, 8967};
		int[] expD = {0, 3, 2, 20, 26, 142, 258, 1058, 2322, 8154};
		int[] expE = {0, 3, 4, 19, 40, 143, 348, 1123, 2928, 8967};
		expected.put(a, expA);
		expected.put(b, expB);
		expected.put(c, expC);
		expected.put(d, expD);
		expected.put(e, expE);
		
		Map<Node, int[]> actual = PathsToSelfGenerator.generatePathsData(new Graph(expected.keySet()));
		
		assertPathsToSelfMappingsEqual(expected, actual);
		
	}
	
	
	
	public static void assertPathsToSelfMappingsEqual(Map<Node, int[]> expected, Map<Node, int[]> actual){
		assertTrue(expected.keySet().containsAll(actual.keySet()));
		
		assertTrue(actual.keySet().containsAll(expected.keySet()));
		
		for (Node key : expected.keySet()){
			int[] expectedNumbers = expected.get(key);
			int[] actualNumbers = actual.get(key);
			assertNotNull(actualNumbers);
			
			assertEquals(expectedNumbers.length, actualNumbers.length);
			
			for (int i = 0; i < expectedNumbers.length; i++){
				assertEquals(expectedNumbers[i], actualNumbers[i]);
			}
		}
	}
}
