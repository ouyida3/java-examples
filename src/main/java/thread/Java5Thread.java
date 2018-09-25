package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Java5的线程类。
 * 
 * 在java5之后，优先选择的线程方案，就不是{@code SimpleThread}那样子写了。
 * 这里的import，你会看见concurrent这个单词，是“同时发生的”的意思，也就是我们常用的并发。
 * 
 * @author danni
 * @since 2018.9.23
 * @see 《Thinking in Java Fourth Edition》
 */
public class Java5Thread {
	public static void main(String[] args) {
		/**
		 * 一开始，你会发现这样写也可以。因为ExecutorService是继承Executor的。
		 * 但是慢慢发现，这样子并无法调用shutdown。这可麻烦了，程序永远不退出了。
		 */
		// Executor e = Executors.newCachedThreadPool();

		ExecutorService e = Executors.newCachedThreadPool();

		/**
		 * 可以很轻易的更换executor，fix是限定了线程数。
		 * 需要注意的是，cache应该是首选，当使用cache出现了问题，才应该考虑更换为fix。
		 */
		//e = Executors.newFixedThreadPool(2);

		/**
		 * single确保只有一个线程，其他的会进行排队，确保上一个任务执行完了才轮到下一个任务。
		 * 当你需要确保唯一任务执行时，可以使用。比如同一个文件写入。
		 */
		//e = Executors.newSingleThreadExecutor();
		
		for (int i = 0; i < 2; i++) {
			// 命令模式
			e.execute(new LiftOff());
		}

		/**
		 * shutdown的作用在于，在调用shutdown之前会提交所有任务，
		 * 这个程序，也就是main线程要在提交所有任务后尽快退出。
		 */
		e.shutdown();
	}

}

/**
 * 发射升空。
 * 
 * 为了不用x.new A()这样子调用，不再使用内部类。
 */
class LiftOff implements Runnable {
	// static，同一份内存哦！在这个线程里，不，是在整个进程里。
	private static int taskCount = 0;// 一共有多少火箭

	// 原谅我，我一开始写成这样子了：
	// int final id = taskCount++;// Syntax error on token "final", delete this
	// token

	// 不用怀疑final，如果id可变，编译器会报错告诉你的
	private final int id = taskCount++;// 火箭的编号id

	@Override
	public void run() {
		int i = 3;
		while (i-- > 0) {
			System.out.println(
					"task" + id + " ready :" + (i + 1));

			// 对线程调度器的一种建议：我已完成重要任务，此时正是切换给其他任务执行的大好时机
			Thread.yield();
		}
		System.out.println("Lift Off!");
	}

}
