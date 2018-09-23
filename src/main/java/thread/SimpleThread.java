package thread;

/**
 * �򵥵��߳��ࡣ
 * 
 * ��java5֮ǰ������������ӵġ�
 * 
 * @author danni
 * @since 2018.9.23(Sunday)
 * @date 2018.9.23 ��д���������Ҫ˵����������д��javadoc����ʶ���ˡ�
 * @Date 2018.9.23 ���������д���͸���Ц�ˣ���ָ��java.util.Date�ࡣ
 * @see ��Thinking in Java Fourth Edition����������˼��û���ӣ�
 */
public class SimpleThread {
	public static void main(String[] args) {
		/**
		 * - No enclosing instance of type SimpleThread is accessible. Must qualify the
		 * allocation with an enclosing instance of type SimpleThread (e.g. x.new A()
		 * where x is an instance of SimpleThread).
		 */
		// LiftOff l = new LiftOff();

		// ���̣߳������ڱ��߳�������
		new SimpleThread().new LiftOff().run();

		// ���̣߳������߳�����
		Thread thread = new Thread(new SimpleThread().new LiftOff());
		thread.start();
		// ��Ҫstart���Σ����׳��߳�״̬�쳣��java.lang.IllegalThreadStateException
		//thread.start();

		// ���߳�
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

				// ���̵߳�������һ�ֽ��飺���������Ҫ���񣬴�ʱ�����л�����������ִ�еĴ��ʱ��
				Thread.yield();
			}
		}

	}
}
