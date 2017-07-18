import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * CalcTester.java - Calculator of "postfix notation" - Assignment 10
 * Example : 3 5 7 10 + - * = -36
 * @Author Trung Kien Nguyen
 * @ID 	   100284963
 * @CoAuthor Professor Jeremy Hilliker
 * @Company	 Langara
 * @version  1.0
 * @date	 July, 17th - 2017
 * @CLass	 CPSC 1181 
 */

public class CalcTester  {
	public static void main(String[] args){
		new TestRunner(CalcTester.class).run();
	}

	/**
	 * Test special boundaries with Strings 
	 */
	public void testSpecials(){ 
		//test MaxInt boundary
		String testMaxInt = "2 31 ^ 1 +"; 
		assert Integer.MIN_VALUE == new Calc(testMaxInt).call();
		//test MinInt Boundary
		String testMinInt = "2 31 ^ -1 * 2 -"; 
		assert Integer.MAX_VALUE == new Calc(testMinInt).call();
		//test multiplied by 0 
		String test0 = "100 30 ^ 0 *"; 
		assert 0 == new Calc(test0).call();
		//test single operand 
		String testSingle = "7"; 
		assert 7 == new Calc(testSingle).call();
		//test multiple spaces
		String testSpaces = "    10     11  +  21 -   9 + 3 /        ";
		assert 3 == new Calc(testSpaces).call(); 
	}

	/** 
	 * Test normal inputs with Strings
	 */
	public void testNormalStrings() { 
		String test1 = "10 11 12 + - 3 - 10 * ";
		assert -160 == new Calc(test1).call();

		String test2 = "5 8 9 6 - * - 19 / 2 ^ 50 * 48 % ";
		assert 2 == new Calc(test2).call();

		String test3 = "7000 11 21 38 48 50 70 * + - + - / 5 ^";
		assert 32 == new Calc(test3).call();

		String test4 = "7000 70 % 10 + ";
		assert 10 == new Calc(test4).call();
	}

	/**
	 * Test Exceptions with Strings
	 */
	public void testExceptions() {
		//test div by 0
		try {
			String testDiv0 = "0 0 / "; 
			new Calc(testDiv0).call();
			assert false;
		} catch (ArithmeticException e){
			assert "Div by 0".equals(e.getMessage());
		}
		
		//test div by 0 
		try { 
			String testDiv0_1 = "10 40 ^ 23 5 ^ 100 - 0 /";
			new Calc(testDiv0_1).call();
			assert false;
		} catch (ArithmeticException e) {
			assert "Div by 0".equals(e.getMessage());
		}

		//test no conent
		try { 
			String testNone = ""; 
			new Calc(testNone).call();
			assert false;
		} catch (IllegalStateException e){
			assert "Not enough operands".equals(e.getMessage());
		}
		
		//test unknown operation + multiple spaces
		try { 
			String testOperator = "4 5 ^ 10 * 6             ("; 
			new Calc(testOperator).call();
			assert false;
		} catch (IllegalArgumentException e ){ 
			assert "(".equals(e.getMessage());
		}
		
		//test not enough operands
		try { 
			String testLackOperands =" 4 5 * -";
			new Calc(testLackOperands).call();
			assert false;
		} catch (IllegalStateException e){
			assert "Not enough operands".equals(e.getMessage());
			assert e.getCause() instanceof NoSuchElementException; 
		}

		//test not enough operations
		try { 
			String testLackOperations = "2 3 4 -"; 
			new Calc(testLackOperations).call();
			assert false;
		} catch (IllegalStateException e){ 
			assert "2".equals(e.getMessage());
		}
	}

	/**
	 * Test using Scanner for normal and Exceptions
	 */
	public void testScanner(){
		//Test Scanner wrong inputs
		try { 
			Scanner scTest = new Scanner(" jjsb djdj jfj");
			new Calc(scTest).call();
			assert false;
		} catch (IllegalStateException e ){
			assert "Not enough operands".equals(e.getMessage());
		}

		//TestScanner normal inputs 
		Scanner scNormal = new Scanner("1 1 5 3 2 + - * * 7 +");
		assert 7 == new Calc(scNormal).call();
		
		Scanner scNormalSingle = new Scanner("5"); 
		assert 5 == new Calc(scNormalSingle).call(); 

		//testScanner Exception
		// AutoCloseable scEx = new Scanner("test string") { 
		// 	public void close() throws IOException { 
		// 		throw new IOException("Cause's IOException");
		// 	}
		// };
		
		// try {
		// 	new Calc(scEx).call();
		// 	assert false;
		// } catch (RuntimeException e){
		// 	assert "Scanner Exception".equals(e.getMessage());
		// 	assert e.getCause() instanceof IOException;
		// 	assert "Cause's IOException".equals(e.getCause().getMessage());
		// }
	
	}

	/** 
	 * Test the tests in test.in and test.expected files
	 */
	public void testGivenTests() throws IOException {
		try (Scanner scin = new Scanner(new BufferedReader(new FileReader("test.in"))); 
			Scanner scout = new Scanner(new BufferedReader(new FileReader("test.expected")))){
				while (scin.hasNextLine()){
					String line = scin.nextLine();
					int result = new Calc(line).call(); 
					assert result == scout.nextInt();
				}
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
