package example;

import java.util.ArrayList;
import java.util.List;

public class Example1 {

	public static void main(String[] args) {

		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		List<Integer> evens = getEvens(numbers);
		System.out.println(evens);
		
		List<Integer> odds=getOdds(numbers);
		System.out.println(odds);

	}

	private static List<Integer> getOdds(List<Integer> in) {
		List<Integer> out = new ArrayList<Integer>();
		for (Integer number : in) {
			if (number % 2 != 0)
				out.add(number);
		}
		return out;
	}

	private static List<Integer> getEvens(List<Integer> in) {
		List<Integer> out = new ArrayList<Integer>();
		for (Integer number : in) {
			if (number % 2 == 0)
				out.add(number);
		}
		return out;
	}

}


/*
 *  imperative style 
 *  ----------------
 *  
 *   limitation(s)
 *   
 *   	=> intention ( what we need ) + implementation ( how we achieved ) code mixed together
 *      => difficult to apply concurrent/parallel execution
 * 
 * 
 * 
 */


