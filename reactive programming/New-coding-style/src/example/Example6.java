package example;

import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

class UserList implements Iterable<String> {

	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public String next() {
				// 10s
				return null;
			}
		};
	}
}

//--------------------------------------
//data source 
//--------------------------------------

class UserRepository {

	public String findById(int id) {
		// ...10s
		return "Nag";
	}

	public UserList findAll() {
		UserList userList = new UserList();
		return userList;
	}

	public CompletableFuture<String> findByIdAsync(int id) {
		ExecutorService executorService = Executors.newFixedThreadPool(8);
		return CompletableFuture.supplyAsync(() -> {
			return "Nag"; // push / non-blocking
		}, executorService);
	}

	public Observable<String> findAllAsync() {
		return Observable.create(emitter -> {
			int i = 0;
			while (i < 5) {
				TimeUnit.SECONDS.sleep(2);
				System.out.println(Thread.currentThread().getName() + " emitting User");
				emitter.onNext("user-" + i);
//				emitter.onError(new IllegalStateException("oops"));
				i++;
			}
			emitter.onComplete();
		});
	}

}

//--------------------------------------
// web 
//--------------------------------------

class UserController {

	private UserRepository userRepository = new UserRepository();

	public void getUser(int id) {
		// String user = userRepository.findById(id); // pull / block-class / sync-api

		userRepository.findByIdAsync(id).thenAccept(user -> {
			System.out.println(user);
		});

	}

	public void getUsers() {

		// UserList userList = userRepository.findAll();
		// Iterator<String> iterator = userList.iterator();
		// while (iterator.hasNext()) {
		// String user = (String) iterator.next(); // pull / block-class / sync-api
		// System.out.println(user);
		// }

		userRepository.findAllAsync().subscribeOn(Schedulers.io()).blockingSubscribe(user -> {
			System.out.println(Thread.currentThread().getName() + " logging User");
			System.out.println(user);
		}, error -> {
			System.out.println(error);
		}, () -> {
			System.out.println("done");
		});

	}

}

public class Example6 {

	public static void main(String[] args) {

		UserController userController = new UserController();
//		userController.getUser(111);
		userController.getUsers();

	}

}
