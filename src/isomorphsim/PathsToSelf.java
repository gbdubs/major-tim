package isomorphsim;

import graphs.Graph;
import graphs.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import matrices.Matrix;
import matrices.MatrixDimensionMismatchException;

public class PathsToSelf {
	
	public static Map<Node, int[]> generatePathData(Graph g){
		
		HashMap<Node, int[]> result = new HashMap<Node, int[]>();
		List<Node> nodes = g.getNodes();
		for (Node n : nodes){
			result.put(n, new int[2 * nodes.size()]);
		}
		
		int[][] adjacencyMatrix = g.getAdjacencyMatrix();
		int[][] currentMatrix = adjacencyMatrix.clone();
		
		for(int iteration = 0; iteration < 2 * nodes.size(); iteration++){
			for (int node = 0; node < nodes.size(); node++){
				result.get(nodes.get(node))[iteration] = currentMatrix[node][node];
			}
			try {
				currentMatrix = Matrix.multiply(currentMatrix, adjacencyMatrix);
			} catch (MatrixDimensionMismatchException e) {
				// Should never be triggered, as our matrices are the same size.
				return null;
			}
		}
		
		return result;
	}
	
	public static List<Set<Node>> establishEquivalencySets(Graph g){
		Map<String, Set<Node>> equivalencySetTracker = new HashMap<String, Set<Node>>();
		Map<Node, int[]> pathData = generatePathData(g);
		for(Node n : pathData.keySet()){
			String vector = Arrays.toString(pathData.get(n));
			if (!equivalencySetTracker.containsKey(vector)){
				equivalencySetTracker.put(vector, new HashSet<Node>());
			}
			equivalencySetTracker.get(vector).add(n);
		}
		List<String> allVectors = new ArrayList<String>(equivalencySetTracker.keySet());
		Collections.sort(allVectors);
		List<Set<Node>> results = new ArrayList<Set<Node>>();
		for (String vector : allVectors){
			results.add(equivalencySetTracker.get(vector));
		}
		return results;
	}
}
