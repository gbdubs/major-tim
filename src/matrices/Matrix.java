package matrices;

public class Matrix {

	private int rows;
	private int cols;
	private int[][] data;
	
	public Matrix(int m, int n){
		data = new int[m][n];
		this.rows = m;
		this.cols = n;
	}
	
	public static Matrix multiply(Matrix a, Matrix b) throws MatrixDimensionMismatchException{
		if (a.cols != b.rows){
			throw new MatrixDimensionMismatchException("MULTIPLICATION", a, b);
		}
		Matrix c = new Matrix(a.rows, b.cols);
		for(int i = 0; i < a.rows; i++){
			for(int j = 0; j < b.cols; j++){
				int entry = 0;
				for(int k = 0; k < a.cols; k++){
					entry += a.data[i][k] * b.data[k][j];
				}
				c.data[i][j] = entry;
			}
		}
		return c;
	}
	
	public String dimensionString(){
		return "(" + rows + " x " + cols + ")";
	}
	
}
