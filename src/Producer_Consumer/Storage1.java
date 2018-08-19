package Producer_Consumer;

import java.util.LinkedList;
/**
 * wait() / nofity()方法是基类Object的两个方法：

wait()方法：当缓冲区已满/空时，生产者/消费者线程停止自己的执行，放弃锁，使自己处于等等状态，让其他线程执行。
notify()方法：当生产者/消费者向缓冲区放入/取出一个产品时，向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态。
 * @author John
 *
 */
public class Storage1 {
	private final int MAX_SIZE = 100;
	private LinkedList<Object> list = new LinkedList<Object>();

	public void produce(String producer) {
		synchronized (list) {
			while (list.size() == MAX_SIZE) {
				System.out.println("仓库已满，"+producer+"暂时不能进行生产任务");
				try {
					list.wait();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			list.add(new Object());
			System.out.println(producer + "生产了一个产品，现储量为：" + list.size());
			list.notifyAll();
		}
	}

	public void consume(String consumer) {
		synchronized (list) {
			while (list.size() == 0) {
				System.out.println("仓库为空，"+consumer+"暂时不能进行消费任务");
				try {
					list.wait();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			list.remove();
			System.out.println(consumer + "消费了一个产品，现储量为：：" + list.size());
			list.notifyAll();
		}
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
		Storage1 storage = new Storage1();
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
