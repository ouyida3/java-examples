package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Java5�������� Callable�����ڷ���ֵ���������޷�����ֵ�� Runnable��
 * 
 * @author danni
 * @since 2018.9.23
 * @see ��Thinking in Java Fourth Edition��
 */
public class Java5Callable {
	public static void main(String[] args)
			throws InterruptedException {
		/**
		 * ����Ҽ���ʹ����õ�Cached�̳߳ء�
		 */
		ExecutorService e = Executors.newCachedThreadPool();

		for (int i = 0; i < 3; i++) {
			/**
			 * ��α����Callable��������Runnable�����execute�������ٿ��á�
			 * <p>
			 * ȡ����֮����submit������ Ȼ�����������ﻹ������ģʽ��
			 */
			// ��Ҫɵɵ��д����ôһ�䣬�����з��صģ�
			// e.submit(new TaskWithResult());
			// ����Ҫ��ͼ��ôд��������󣬷��ص���Future
			// String result = e.submit(new TaskWithResult());

			// Future�����������������˼��Ҳ��1.5�����
			Future<String> result = e
					.submit(new TaskWithResult());
			// Ҫ��ȡFuture������get���ɣ������׳��쳣
			try {
				// idDone�ж��Ƿ�ִ���꣬Futureִ���꣬�Ϳ��Ե���get��ȡ��
				// ����ܻ���֣�����ûִ���꣬��ôfutureҲ������ˣ�
				// ������Ϊ����ȫ���Բ�����idDone�жϣ�ֱ��get����ʱ��get��������
				// ֱ��������ɣ���ȡ�����
				boolean done = result.isDone();
				System.out.println("done?" + done);

				// get���ص���Future�ķ��Ͳ������ͣ��������String��
				String s = result.get();
				System.out.println(s);
			} catch (ExecutionException e1) {
				e1.printStackTrace();
			} finally {
				/**
				 * ���ﲻ��shutdown�� Exception in thread "main"
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
		 * ͬ����ExecutorService������ע��ִ�е���Runnable����Callable��shutdown�Ǳ���ġ�
		 * ������Ҫ˼�����ǣ���Ҫ����fnally����
		 */
		e.shutdown();

	}

}

/**
 * ��Ҫע�⣬Callable�Ƿ��ͣ������е����Ͳ�������������ָ������������String��
 */
class TaskWithResult implements Callable<String> {
	static int id = 0;

	/**
	 * ���õ��ķ��ص����ͣ�eclipse����ݷ����Զ����ɡ�
	 */
	@Override
	public String call() throws Exception {
		return "hello " + id++;
	}

}
