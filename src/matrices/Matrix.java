package matrices;

import java.util.Arrays;

public class Matrix {

	private int rows;
	private int cols;
	private int[][] data;
	
	public Matrix(int[][] d){
		this.data = d;
		this.rows = d.length;
		this.cols = d[0].length;
	}
	
	public Matrix(int m, int n){
		data = new int[m][n];
		this.rows = m;
		this.cols = n;
	}
	
	public static Matrix multiply(Matrix a, Matrix b) throws MatrixDimensionMismatchException{
		return new Matrix(multiply(a.data, b.data));
	}
	
	public String dimensionString(){
		return "(" + rows + " x " + cols + ")";
	}
	
	public String toString(){
		return Arrays.deepToString(data);
	}
	
	public boolean equals(Object other){
		if (other instanceof Matrix){
			Matrix otherMatrix = (Matrix) other;
			return Arrays.deepEquals(data, otherMatrix.data);
		}
		return false;
	}

	public static int[][] multiply(int[][] a, int[][] b) throws MatrixDimensionMismatchException {
		if (a[0].length != b.length){
			throw new MatrixDimensionMismatchException("MULTIPLICATION", new Matrix(a), new Matrix(b));
		}
		int[][] c = new int[a.length][b[0].length];
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < b[0].length; j++){
				int entry = 0;
				for(int k = 0; k < b.length; k++){
					entry += a[i][k] * b[k][j];
				}
				c[i][j] = entry;
			}
		}
		return c;
	}
	
}
