package Producer_Consumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * await()和signal()的功能基本上和wait() / nofity()相同，完全可以取代它们，
 * 但是它们和新引入的锁定机制Lock直接挂钩，具有更大的灵活性。
 * 通过在Lock对象上调用newCondition()方法，将条件变量和一个锁对象进行绑定，进而控制并发程序访问竞争资源的安全。
 * 
 * @author John
 *
 */
public class Storage2 {

	private final int MAX_SIZE = 100;
	private LinkedList<Object> list = new LinkedList<Object>();

	private final Lock lock = new ReentrantLock();
	// 仓库满的条件变量
	private final Condition full = lock.newCondition();
	// 仓库空的条件变量
	private final Condition empty = lock.newCondition();

	public void produce(String producer) {
		lock.lock();
		while (list.size() == MAX_SIZE) {
			System.out.println(producer + " 暂时不能进行生产任务");
			try {
				// 条件不满足，生产阻塞
				full.await();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		list.add(new Object());
		System.out.println(producer + "生产了一个产品，现储量为：" + list.size());
		empty.signalAll();
		lock.unlock();
	}

	public void consume(String consumer) {
		lock.lock();
		while (list.size() == 0) {
			System.out.println(consumer + " 暂时不能进行消費任务");
			try {
				// 条件不满足，消費阻塞
				empty.await();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		list.remove();
		System.out.println(consumer + "消費了一个产品，现储量为：" + list.size());
		full.signalAll();
		lock.unlock();
	}

	public LinkedList<Object> getList() {
		return list;
	}

	public void setList(LinkedList<Object> list) {
		this.list = list;
	}

	public int getMAX_SIZE() {
		return MAX_SIZE;
	}

	public static void main(String[] args) {
		Storage2 storage = new Storage2();
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
