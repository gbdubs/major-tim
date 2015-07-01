package test;

import static org.junit.Assert.*;
import matrices.Matrix;
import matrices.MatrixDimensionMismatchException;

import org.junit.Test;

public class TestMatrixMultiplication {

	@Test
	public void testBasicSquareMatrixMultiplication(){
		int[][] dataA = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		Matrix a = new Matrix(dataA);
		int[][] dataB = {{0, 1, 0}, {1, 0, 1}, {1, 1, 0}};
		Matrix b = new Matrix(dataB);
		
		int[][] expectedAB = {{5, 4, 2}, {11, 10, 5}, {17, 16, 8}};
		int[][] expectedBA = {{4, 5, 6}, {8, 10, 12}, {5, 7, 9}};
		
		try {
			assertEquals(new Matrix(expectedAB), Matrix.multiply(a, b));
			assertEquals(new Matrix(expectedBA), Matrix.multiply(b, a));
		} catch (MatrixDimensionMismatchException e) {
			assertTrue(false);
		}
	}
}
