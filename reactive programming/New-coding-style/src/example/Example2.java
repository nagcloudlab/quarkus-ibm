package example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Example2 {

	public static void main(String[] args) {

		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		List<Integer> evens = getEvens(numbers);
		System.out.println(evens);

		List<Integer> odds = getOdds(numbers);
		System.out.println(odds);

	}

	private static List<Integer> getOdds(List<Integer> in) {
//		return Lib.filter(in, number -> number % 2 != 0);
		return in
				 .stream()
				 .filter(number -> number % 2 != 0)
				 .collect(Collectors.toList());
	}

	private static List<Integer> getEvens(List<Integer> in) {
//		return Lib.filter(in, number -> number % 2 == 0);
		return in
				 .stream()
				 .filter(number -> number % 2 == 0)
				 .collect(Collectors.toList());
	}

}



class Lib {
	public static List<Integer> filter(List<Integer> in, Predicate<Integer> predicate) {
		List<Integer> out = new ArrayList<Integer>();
		for (Integer number : in) {
			if (predicate.test(number))
				out.add(number);
		}
		return out;
	}
}




/*
 * 
 *    declarative style with function i.e Functional Programming
 *    
 *    
 *    advantages
 *    -----------
 *    
 *    	==> intention & implementation code separated
 *      ==> difficult to apply concurrency/parallel execution
 *      
 *      ==> compact & concise code
 *      ==> higher-order-function ( HOF ) enables build complex algorithms with simple functions
 *       
 *    
 *    
 * 
 */
