package matrices;

@SuppressWarnings("serial")
public class MatrixDimensionMismatchException extends Exception {

	public MatrixDimensionMismatchException(String operation, Matrix a, Matrix b){
		super(operation + " attempted between a " + a.dimensionString() + " matrix and a " + b.dimensionString() + "matrix.");
	}
	
	public MatrixDimensionMismatchException(String operation, Matrix a){
		super(operation + " attempted with a " + a.dimensionString() + " matrix.");
	}
}
