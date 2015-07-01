package matrices;

import graphs.Graph;
import graphs.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathsToSelfGenerator {
	
	public static Map<Node, int[]> generatePathsData(Graph g){
		
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
}
