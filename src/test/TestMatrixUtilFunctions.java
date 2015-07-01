package test;

import java.util.Arrays;

import matrices.Matrix;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestMatrixUtilFunctions {

	@Test
	public void testToString(){
		int[][] data = {{1, 2},{3,4},{5,6}};
		Matrix m = new Matrix(data);
		assertEquals(Arrays.deepToString(data), m.toString());
	}
	
	@Test
	public void testDifferentObjectEquality(){
		int[][] data = {{1, 2},{3,4},{5,6}};
		Matrix m = new Matrix(data);
		assertTrue(m.equals(data));
	}
	
	@Test
	public void testFalseObjectEquality(){
		int[][] data = {{1, 2},{3,4},{5,6}};
		int[] other = {1, 2, 3, 4 , 5, 6};
		Matrix m = new Matrix(data);
		assertFalse(m.equals(other));
	}
}
