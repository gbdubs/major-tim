package test;

import static org.junit.Assert.*;
import graphs.Graph;
import graphs.Node;
import isomorphsim.IsomorphismSolver;

import java.util.Arrays;
import java.util.Map;

import org.junit.Test;

public class TestIsomorphismSolver {

	@Test
	public void testIsomorphsimWithIndependentEquivClasses(){
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
		
		Graph g1 = new Graph(Arrays.asList(a, b, c, d, e));
		
		Node f = new Node();
		Node g = new Node();
		Node h = new Node();
		Node i = new Node();
		Node j = new Node();
		f.makeNeighbors(j);
		f.makeNeighbors(h);
		g.makeNeighbors(i);
		g.makeNeighbors(j);
		g.makeNeighbors(h);
		i.makeNeighbors(j);
		i.makeNeighbors(h);
		
		Graph g2 = new Graph(Arrays.asList(f, g, h, i, j));
		
		Map<Node, Node> isomorphism = IsomorphismSolver.constructIsomorphism(g1, g2);
		assertNotNull(isomorphism);
		
		assertEquals(f, isomorphism.get(a));
		boolean way1 = j == isomorphism.get(b) && h == isomorphism.get(d);
		boolean way2 = h == isomorphism.get(b) && j == isomorphism.get(d);
		assertTrue(way1 != way2);
		
		way1 = i == isomorphism.get(c) && g == isomorphism.get(e);
		way2 = i == isomorphism.get(e) && g == isomorphism.get(c);
		assertTrue(way1 != way2);
	}

	@Test
	public void testIsomorphismWithDependentEquivClasses(){
		
		Node a = new Node();
		Node b = new Node();
		Node c = new Node();
		Node d = new Node();
		a.makeNeighbors(b);
		b.makeNeighbors(c);
		c.makeNeighbors(d);
		d.makeNeighbors(a);
		
		Node e = new Node();
		Node f = new Node();
		Node g = new Node();
		Node h = new Node();
		e.makeNeighbors(f);
		f.makeNeighbors(g);
		g.makeNeighbors(h);
		h.makeNeighbors(e);
		
		Graph g1 = new Graph(Arrays.asList(a, b, c, d));
		Graph g2 = new Graph(Arrays.asList(f, h, g, e));
		
		Map<Node, Node> iso = IsomorphismSolver.constructIsomorphism(g2, g1);
		
		assertNotNull(iso);
		
		assertTrue(IsomorphismSolver.verifyIsomorphism(iso));
	}
}
