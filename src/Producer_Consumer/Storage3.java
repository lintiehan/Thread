package Producer_Consumer;

import java.util.concurrent.LinkedBlockingQueue; 

/**
 * ����һ���Ѿ����ڲ�ʵ����ͬ���Ķ��У�ʵ�ַ�ʽ���õ������ǵ�2��await() / signal()����
 * �����������ɶ���ʱָ��������С��������������������put()��take()������
 * 
 * put()����������������������������̣߳������ﵽ���ʱ���Զ�������
 * 
 * take()����������������������������̣߳�����Ϊ0ʱ���Զ�������*
 * 
 * @author John
 *
 */
public class Storage3 {

	private final int MAX_SIZE = 100;
	private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<Object>(100);

	public void produce(String producer) {
		if (list.size() == MAX_SIZE) {
			System.out.println(producer + "��ʱ���ܽ�����������");
		}
		try {
			list.put(new Object());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(producer + "������һ����Ʒ���ִ���Ϊ��" + list.size());

	}

	public void consume(String consumer) {

		if (list.size() == 0) {
			System.out.println(consumer + " ��ʱ���ܽ������M����");
		}
		try {
			list.take();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(consumer + "���M��һ����Ʒ���ִ���Ϊ��" + list.size());
	}


	public static void main(String[] args) {
		Storage3 storage = new Storage3();
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
