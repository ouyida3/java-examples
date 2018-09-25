package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Java5���߳��ࡣ
 * 
 * ��java5֮������ѡ����̷߳������Ͳ���{@code SimpleThread}������д�ˡ�
 * �����import����ῴ��concurrent������ʣ��ǡ�ͬʱ�����ġ�����˼��Ҳ�������ǳ��õĲ�����
 * 
 * @author danni
 * @since 2018.9.23
 * @see ��Thinking in Java Fourth Edition��
 */
public class Java5Thread {
	public static void main(String[] args) {
		/**
		 * һ��ʼ����ᷢ������дҲ���ԡ���ΪExecutorService�Ǽ̳�Executor�ġ�
		 * �����������֣������Ӳ��޷�����shutdown������鷳�ˣ�������Զ���˳��ˡ�
		 */
		// Executor e = Executors.newCachedThreadPool();

		ExecutorService e = Executors.newCachedThreadPool();

		/**
		 * ���Ժ����׵ĸ���executor��fix���޶����߳�����
		 * ��Ҫע����ǣ�cacheӦ������ѡ����ʹ��cache���������⣬��Ӧ�ÿ��Ǹ���Ϊfix��
		 */
		//e = Executors.newFixedThreadPool(2);

		/**
		 * singleȷ��ֻ��һ���̣߳������Ļ�����Ŷӣ�ȷ����һ������ִ�����˲��ֵ���һ������
		 * ������Ҫȷ��Ψһ����ִ��ʱ������ʹ�á�����ͬһ���ļ�д�롣
		 */
		//e = Executors.newSingleThreadExecutor();
		
		for (int i = 0; i < 2; i++) {
			// ����ģʽ
			e.execute(new LiftOff());
		}

		/**
		 * shutdown���������ڣ��ڵ���shutdown֮ǰ���ύ��������
		 * �������Ҳ����main�߳�Ҫ���ύ��������󾡿��˳���
		 */
		e.shutdown();
	}

}

/**
 * �������ա�
 * 
 * Ϊ�˲���x.new A()�����ӵ��ã�����ʹ���ڲ��ࡣ
 */
class LiftOff implements Runnable {
	// static��ͬһ���ڴ�Ŷ��������߳�������������������
	private static int taskCount = 0;// һ���ж��ٻ��

	// ԭ���ң���һ��ʼд���������ˣ�
	// int final id = taskCount++;// Syntax error on token "final", delete this
	// token

	// ���û���final�����id�ɱ䣬�������ᱨ��������
	private final int id = taskCount++;// ����ı��id

	@Override
	public void run() {
		int i = 3;
		while (i-- > 0) {
			System.out.println(
					"task" + id + " ready :" + (i + 1));

			// ���̵߳�������һ�ֽ��飺���������Ҫ���񣬴�ʱ�����л�����������ִ�еĴ��ʱ��
			Thread.yield();
		}
		System.out.println("Lift Off!");
	}

}
