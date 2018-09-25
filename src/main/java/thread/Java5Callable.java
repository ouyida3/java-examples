package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Java5还新增了 Callable，用于返回值，区别于无法返回值的 Runnable。
 * 
 * @author danni
 * @since 2018.9.23
 * @see 《Thinking in Java Fourth Edition》
 */
public class Java5Callable {
	public static void main(String[] args)
			throws InterruptedException {
		/**
		 * 这里，我继续使用最常用的Cached线程池。
		 */
		ExecutorService e = Executors.newCachedThreadPool();

		for (int i = 0; i < 3; i++) {
			/**
			 * 入参变成了Callable，而不是Runnable，因此execute方法不再可用。
			 * <p>
			 * 取而代之的是submit方法。 然而，请问这里还是命令模式吗？
			 */
			// 不要傻傻的写下这么一句，它是有返回的！
			// e.submit(new TaskWithResult());
			// 更不要试图这么写，编译错误，返回的是Future
			// String result = e.submit(new TaskWithResult());

			// Future泛型这个命名很有意思，也是1.5引入的
			Future<String> result = e
					.submit(new TaskWithResult());
			// 要获取Future，调用get即可，但会抛出异常
			try {
				// idDone判断是否执行完，Future执行完，就可以调用get获取。
				// 你可能会奇怪，明明没执行完，怎么future也打出来了？
				// 那是因为你完全可以不调用idDone判断，直接get，此时，get会阻塞，
				// 直到任务完成，获取结果。
				boolean done = result.isDone();
				System.out.println("done?" + done);

				// get返回的是Future的泛型参数类型，这里就是String了
				String s = result.get();
				System.out.println(s);
			} catch (ExecutionException e1) {
				e1.printStackTrace();
			} finally {
				/**
				 * 这里不能shutdown。 Exception in thread "main"
				 * java.util.concurrent.RejectedExecutionException: Task
				 * java.util.concurrent.FutureTask@1f96302 rejected from
				 * java.util.concurrent.ThreadPoolExecutor@14eac69[Terminated, pool size = 0,
				 * active threads = 0, queued tasks = 0, completed tasks = 1]
				 * 
				 */
				// e.shutdown();
			}
		}
		/**
		 * 同样，ExecutorService并不关注你执行的是Runnable还是Callable，shutdown是必须的。
		 * 还有需要思考的是，需要放在fnally中吗？
		 */
		e.shutdown();

	}

}

/**
 * 需要注意，Callable是泛型，它具有的类型参数可以由你来指定。我这里是String。
 */
class TaskWithResult implements Callable<String> {
	static int id = 0;

	/**
	 * 不用担心返回的类型，eclipse会根据泛型自动生成。
	 */
	@Override
	public String call() throws Exception {
		return "hello " + id++;
	}

}
