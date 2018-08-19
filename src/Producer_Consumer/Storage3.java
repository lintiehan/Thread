package Producer_Consumer;

import java.util.concurrent.LinkedBlockingQueue; 

/**
 * 它是一个已经在内部实现了同步的队列，实现方式采用的是我们第2种await() / signal()方法
 * 它可以在生成对象时指定容量大小。它用于阻塞操作的是put()和take()方法：
 * 
 * put()方法：类似于我们上面的生产者线程，容量达到最大时，自动阻塞。
 * 
 * take()方法：类似于我们上面的消费者线程，容量为0时，自动阻塞。*
 * 
 * @author John
 *
 */
public class Storage3 {

	private final int MAX_SIZE = 100;
	private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<Object>(100);

	public void produce(String producer) {
		if (list.size() == MAX_SIZE) {
			System.out.println(producer + "暂时不能进行生产任务");
		}
		try {
			list.put(new Object());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(producer + "生产了一个产品，现储量为：" + list.size());

	}

	public void consume(String consumer) {

		if (list.size() == 0) {
			System.out.println(consumer + " 暂时不能进行消費任务");
		}
		try {
			list.take();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(consumer + "消費了一个产品，现储量为：" + list.size());
	}


	public static void main(String[] args) {
		Storage3 storage = new Storage3();
		for (int i = 1; i < 6; i++) {
			int index = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					storage.produce(String.format("生产者%d:", index));
				}
			}).start();
		}

		for (int i = 1; i < 4; i++) {
			int index = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					storage.consume(String.format("消费者者%d:", index));
				}
			}).start();
		}
	}
}
