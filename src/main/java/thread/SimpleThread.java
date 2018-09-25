package thread;

/**
 * 简单的线程类。
 * 
 * 在java5之前，大概是这样子的。
 * 
 * @author danni
 * @since 2018.9.23(Sunday)
 * @date 2018.9.23 我写下这个，是要说明，这样子写，javadoc根本识别不了。
 * @Date 2018.9.23 如果这样子写，就更可笑了，会指向java.util.Date类。
 * @see 《Thinking in Java Fourth Edition》（不好意思，没链接）
 */
public class SimpleThread {
	public static void main(String[] args) {
		/**
		 * - No enclosing instance of type SimpleThread is accessible. Must qualify the
		 * allocation with an enclosing instance of type SimpleThread (e.g. x.new A()
		 * where x is an instance of SimpleThread).
		 */
		// LiftOff l = new LiftOff();

		// 单线程，并且在本线程中运行
		new SimpleThread().new LiftOff().run();

		// 单线程，新启线程运行
		Thread thread = new Thread(new SimpleThread().new LiftOff());
		thread.start();
		// 不要start两次，会抛出线程状态异常：java.lang.IllegalThreadStateException
		//thread.start();

		// 多线程
		for (int i = 0; i < 3; i++) {
			Thread threads = new Thread(new SimpleThread().new LiftOff());
			threads.start();
		}
	}

	class LiftOff implements Runnable {

		@Override
		public void run() {
			int i = 3;
			while (i-- > 0) {
				System.out.println("hello:" + i);

				// 对线程调度器的一种建议：我已完成重要任务，此时正是切换给其他任务执行的大好时机
				Thread.yield();
			}
		}

	}
}
