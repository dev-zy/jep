package org.nfunk.jep.function;

import java.util.Stack;

import org.nfunk.jep.ParseException;

/**
 * This class serves mainly as an example of a function that accepts any number
 * of parameters. Note that the numberOfParameters is initialized to -1.
 */
@SuppressWarnings("all")
public class Avg extends PostfixMathCommand {
	private Add fun = new Add();

	/**
	 * Constructor.
	 */
	public Avg() {
		// Use a variable number of arguments
		numberOfParameters = -1;
	}

	/**
	 * Calculates the result of summing up all parameters, which are assumed to
	 * be of the Double type.
	 */
	public void run(Stack stack) throws ParseException {
		checkStack(stack);// check the stack
		if (curNumberOfParameters < 1)
			throw new ParseException("No arguments for Sum");
		// initialize the result to the first argument
		Object sum = stack.pop();
		Object param;
		int i = 1;
		// repeat summation for each one of the current parameters
		while (i < curNumberOfParameters) {
			// get the parameter from the stack
			param = stack.pop();
			// add it to the sum (order is important for String arguments)
			sum = fun.add(param, sum);
			i++;
		}
		// push the result on the inStack
		stack.push(new Divide().div(sum, i));
	}
}
