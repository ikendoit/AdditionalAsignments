import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import javax.management.RuntimeErrorException;

/**
 * Calc.java - Calculator of "postfix notation" - Assignment 10
 * Example : 3 5 7 10 + - * = -36
 * @Author	Trung Kien Nguyen
 * @ID 	  	100284963
 * @CoAuthor	Professor Jeremy Hilliker
 * @Company		Langara
 * @version 	1.0
 * @date		July, 17th - 2017
 * @Class		CPSC 1181 
 */
public class Calc implements Callable<Integer> {

	private final Scanner sc;
	private final Deque<Integer> stack;

	/** 
	 * Constructor that takes parameter = string
	 * @param anIn String parameter
	 */
	public Calc(String anIn) {
		stack = new ArrayDeque<Integer>();
		sc = new Scanner(anIn); 
	}

	/**
	 * Constructor that takes parameter = Scanner
	 * @param aScanner Scanner of parameter
	 */
	public Calc(Scanner aScanner) {
		this.sc = aScanner;
		stack = new ArrayDeque<Integer>(); 
	}

	/**
	 * Call method, works the calculations
	 * throws the exceptions 
	 * @return int the result of the input calculation
	 */
	public Integer call() {
		// checkScanner IOException
		boolean testSc = sc.hasNext();
		if (sc.ioException() != null){
			throw new RuntimeException("Scanner Exception",sc.ioException());
		}

		// check exists contents		
		if (!sc.hasNext()){
			throw new IllegalStateException("Not enough operands");
		}

		try { 
			while (sc.hasNext()){ 
				//Calculate inputs, can throw NoSuchElementException
				if (sc.hasNextInt()){ 
					stack.push(sc.nextInt());
				} else {
					stack.push(operate(sc.next(), stack.pop(), stack.pop()));
				}
			}

		} catch (NoSuchElementException e ){
			// process stack's NoSuchElementException
			throw new IllegalStateException("Not enough operands",e); 
		} finally { 
			sc.close();
		}

		if (stack.size() > 1){
			// check if more than 2 elements left in stack
			throw new IllegalStateException(stack.size()+ "");
		}

		return stack.getFirst();

	}

	/**
	 * operate the simple calculations
	 * throws exceptions when needed 
	 * (div by 0 , unknown operation)
	 * @param operator String of operator 
	 * @param i2	   first operand
	 * @param i1	   second operand
	 * @return int 	   result of calculation
	 */
	private static int operate(String operator, int i2, int i1) {
		switch (operator) {
			case "+": return i1+i2 ; 
			case "-": return i1-i2 ; 
			case "*": return i1*i2 ;
			case "/": 
				if (i2 ==0 ){
					// div by 0 Exception
					throw new ArithmeticException("Div by 0");
				}
				return i1/i2 ; 
			case "%": return i1%i2 ;
			case "^": return (int) Math.pow(i1,i2); 
			// unknown operation Exception
			default : throw new IllegalArgumentException(operator);
		}
	}
}
