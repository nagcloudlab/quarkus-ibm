package example;

import java.util.Scanner;

/*
 * 
 *  tasks
 *  	- computation
 *      - io
 *      
 *      
 *      
 *      how many threads  ?
 *      
 *                           # of cpus e.g 8
 *      # of threads   <=   -----------------
 *                           blocking-factor
 *                   
 *                                                          8
 *      computational intensive work   =>  # of threads <= --- 
 *                                                          1
 *                                                          
 *                                                          8
 *      io intensive work              =>   # of threads <= --- 
 *                                                          0.5                                                   
 *                                                          
 */

public class Example3 {

	public static void main(String[] args) {

		String name = Thread.currentThread().getName();
		System.out.println(name + " started...");

		// step-1
		io();

		// step-2
		computation();

	}

	private static void io() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " started io");
		Scanner sc = new Scanner(System.in);
		System.out.println("say ur name!");
		String myName = sc.nextLine();
		System.out.println("hello " + myName);
		System.out.println(name + " finished io");
		sc.close();

	}

	private static void computation() {

		String name = Thread.currentThread().getName();
		System.out.println(name + " started computation");
		while (true) {
		}

	}

}
