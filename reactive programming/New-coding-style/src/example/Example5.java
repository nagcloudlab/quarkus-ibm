package example;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class Shop {

	private static Random random = new Random();

	public static Future<Double> getPrice(String product) {
		// ..
		CompletableFuture<Double> futurePrice = new CompletableFuture<Double>();
		new Thread(() -> {
			try {
				Thread.sleep(3000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			double price = random.nextDouble() + product.charAt(0) + product.charAt(1);
			futurePrice.complete(price);
		});
		return futurePrice;
	}
}

public class Example5 {

	public static void main(String[] args) {

		// best price Finder

		Future<Double> future = Shop.getPrice("iphone10"); // sync
		// ..

		try {
			double price = future.get();
			System.out.println(price);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}

}
