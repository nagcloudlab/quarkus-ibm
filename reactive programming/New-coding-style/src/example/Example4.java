package example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// JDK 1.5 Future 

public class Example4 {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(8);

		Callable<String> callable = () -> {
			TimeUnit.SECONDS.sleep(5);
			// read message from network or file-system
			return "hello ibm";
		};

		Future<String> future = executorService.submit(callable);

		// .....
		System.out.println(future.isDone());

		try {

			String greetMessage = (String) future.get(); // blocks current thread
			System.out.println(greetMessage);

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

}
