package Producer_Consumer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * await()��signal()�Ĺ��ܻ����Ϻ�wait() / nofity()��ͬ����ȫ����ȡ�����ǣ�
 * �������Ǻ����������������Lockֱ�ӹҹ������и��������ԡ�
 * ͨ����Lock�����ϵ���newCondition()������������������һ����������а󶨣��������Ʋ���������ʾ�����Դ�İ�ȫ��
 * 
 * @author John
 *
 */
public class Storage2 {

	private final int MAX_SIZE = 100;
	private LinkedList<Object> list = new LinkedList<Object>();

	private final Lock lock = new ReentrantLock();
	// �ֿ�������������
	private final Condition full = lock.newCondition();
	// �ֿ�յ���������
	private final Condition empty = lock.newCondition();

	public void produce(String producer) {
		lock.lock();
		while (list.size() == MAX_SIZE) {
			System.out.println(producer + " ��ʱ���ܽ�����������");
			try {
				// ���������㣬��������
				full.await();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		list.add(new Object());
		System.out.println(producer + "������һ����Ʒ���ִ���Ϊ��" + list.size());
		empty.signalAll();
		lock.unlock();
	}

	public void consume(String consumer) {
		lock.lock();
		while (list.size() == 0) {
			System.out.println(consumer + " ��ʱ���ܽ������M����");
			try {
				// ���������㣬���M����
				empty.await();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		list.remove();
		System.out.println(consumer + "���M��һ����Ʒ���ִ���Ϊ��" + list.size());
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
					storage.produce(String.format("������%d:", index));
				}
			}).start();
		}

		for (int i = 1; i < 4; i++) {
			int index = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					storage.consume(String.format("��������%d:", index));
				}
			}).start();
		}
	}
}
