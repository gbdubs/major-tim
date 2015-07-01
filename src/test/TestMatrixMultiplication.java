package test;

import static org.junit.Assert.*;

import java.util.Arrays;

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
			fail("The matrix multiplicaiton should not have failed due to a size mismatch.");
		}
	}
	
	@Test
	public void testUnevenMatrixMultiplication(){
		int[][] dataA = {{1, 2, 3}, {4, 5, 6}};
		Matrix a = new Matrix(dataA);
		int[][] dataB = {{1, 0}, {1, 1}, {0, 1}};
		Matrix b = new Matrix(dataB);
		
		int[][] expectedAB = {{3, 5},{9, 11}};
		int[][] expectedBA = {{1, 2, 3},{5, 7, 9},{4, 5, 6}};
		
		try {
			assertEquals(new Matrix(expectedBA), Matrix.multiply(b, a));
			assertEquals(new Matrix(expectedAB), Matrix.multiply(a, b));
		} catch (MatrixDimensionMismatchException e) {
			fail("The matrix multiplicaiton should not have failed due to a size mismatch.");
		}
	}
	

	@Test
	public void testUnevenIncorrectMatrixMultiplication(){
		int[][] dataA = {{1, 2, 3}, {4, 5, 6}};
		Matrix a = new Matrix(dataA);

		boolean thrown = false;
		try {
			Matrix.multiply(a, a);
		    fail("The matrix multiplication was successful. It should have thrown a dimension mismatch exception");;
		} catch (MatrixDimensionMismatchException expectedException) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	@Test
	public void testOnlyOneWayMatrixMultiplication(){
		int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}};

		int[][] b = {{1},{-1},{1},{-1}};
		
		int[][] expected = {{-2},{-2}};
		
		try {
			boolean equal = Arrays.deepEquals(expected, Matrix.multiply(a, b));
			assertTrue(equal);
		} catch (MatrixDimensionMismatchException e) {
			fail("The matrix multiplication should work for mismatched (but tennable) matrices.");
		}

		boolean thrown = false;
		try {
			Matrix.multiply(b, a);
		    fail("The matrix multiplication was successful. It should have thrown a dimension mismatch exception");;
		} catch (MatrixDimensionMismatchException expectedException) {
			thrown = true;
		}
		assertTrue(thrown);
	}
}
