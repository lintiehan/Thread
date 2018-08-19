package Producer_Consumer;

import java.util.LinkedList;
/**
 * wait() / nofity()�����ǻ���Object������������

wait()������������������/��ʱ��������/�������߳�ֹͣ�Լ���ִ�У���������ʹ�Լ����ڵȵ�״̬���������߳�ִ�С�
notify()��������������/�������򻺳�������/ȡ��һ����Ʒʱ���������ȴ����̷߳�����ִ�е�֪ͨ��ͬʱ��������ʹ�Լ����ڵȴ�״̬��
 * @author John
 *
 */
public class Storage1 {
	private final int MAX_SIZE = 100;
	private LinkedList<Object> list = new LinkedList<Object>();

	public void produce(String producer) {
		synchronized (list) {
			while (list.size() == MAX_SIZE) {
				System.out.println("�ֿ�������"+producer+"��ʱ���ܽ�����������");
				try {
					list.wait();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			list.add(new Object());
			System.out.println(producer + "������һ����Ʒ���ִ���Ϊ��" + list.size());
			list.notifyAll();
		}
	}

	public void consume(String consumer) {
		synchronized (list) {
			while (list.size() == 0) {
				System.out.println("�ֿ�Ϊ�գ�"+consumer+"��ʱ���ܽ�����������");
				try {
					list.wait();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			list.remove();
			System.out.println(consumer + "������һ����Ʒ���ִ���Ϊ����" + list.size());
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
