package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   TestNamer.class,
   TestGraphCreation.class,
   TestMatrixMultiplication.class,
})
public class TestSuite {
	
}