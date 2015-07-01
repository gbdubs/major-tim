package graphs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import matrices.Matrix;
import matrices.MatrixDimensionMismatchException;

public class Graph {

	private int[][] adjacencyMatrix;
	private List<Node> nodes;
	
	public Graph(int[][] matrix) throws MatrixDimensionMismatchException{
		if (matrix.length != matrix[0].length){
			throw new MatrixDimensionMismatchException("CREATING A SQUARE ADJACENCY MATRIX", new Matrix(matrix));
		}
		
		nodes = new ArrayList<Node>();
		for (int i = 0; i < matrix.length; i++){
			nodes.add(new Node());
		}
		
		for (int i = 0; i < matrix.length; i++){
			for (int j = i; j < matrix.length; j++){
				if (matrix[i][j] > 0){
					nodes.get(i).makeNeighbors(nodes.get(j));
				}
			}
		}
	}
	
	public Graph(Collection<Node> n){
		this.nodes = new ArrayList<Node>(n);
		this.adjacencyMatrix = new int[nodes.size()][nodes.size()];
		for (int i = 0; i < nodes.size(); i++){
			Node nodeI = nodes.get(i);
			for (int j = 0; j < nodes.size(); j++){
				Node nodeJ = nodes.get(j);
				if (nodeI.hasNeighbor(nodeJ)){
					adjacencyMatrix[i][j] = 1;
				}
			}
		}
	}
	
	public Node getNode(int i){
		return nodes.get(i);
	}

	public int[][] getAdjacencyList() {
		return adjacencyMatrix;
	}
}
