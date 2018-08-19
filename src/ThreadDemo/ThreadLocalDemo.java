package ThreadDemo;

import java.util.Random;

public class ThreadLocalDemo implements Runnable {
	private final static ThreadLocal studentLocal = new ThreadLocal();

	public static void main(String[] args) {
		ThreadLocalDemo th = new ThreadLocalDemo();
		Thread t1 = new Thread(th, "a");
		Thread t2 = new Thread(th, "b");
		t1.start();
		t2.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		accessStudent();
	}

	private void accessStudent() {
		// ��ȡ��ǰ�̵߳�����
		String currentThreadName = Thread.currentThread().getName();
		System.out.println(currentThreadName + " is running");
		// ����һ�����������ӡ
		Random random = new Random();
		int age = random.nextInt(100);
		System.out.println("thread " + currentThreadName + " set age to:" + age);

		// ��ȡһ��Student���󣬲��������������뵽����������
		Student student = getStudent();
		student.setAge(age);
		System.out.println("thread " + currentThreadName + " first read age is:" + student.getAge());
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("thread " + currentThreadName + " second read age is:" + student.getAge());

	}

	private Student getStudent() {
		// ��ȡ�����̱߳�����ǿ��ת��ΪStudent����
		Student s = (Student) studentLocal.get();
		// �߳��״�ִ�д˷�����ʱ��studentLocal.get()�϶�Ϊnull
		if (s == null) {
			// ����һ��Student���󣬲����浽�����̱߳���studentLocal��
			s = new Student();
			studentLocal.set(s); 
		}
		return s;
	}
}


/**
 * ThreadLocal ʹ�ó�����Ҫ������߳��������򲢷�������һ�µ�����
 * ThreadLocal Ϊÿһ���߳��в������ʵ������ṩһ��������ͨ�����ʸ���������ҵ�񣬺ķ����ڴ棬�����������߳���������������������
 * Ҳ�������̲߳������Ƶĸ��Ӷ�
 * 
 * 
 * synchronized���������Ļ��ƣ�ʹ������������ĳһʱ��ֻ�ܱ�һ���̷߳���
 * ThreadLocalΪÿһ���̶߳��ṩ�˱����ĸ�����ʹ��ÿ���߳���ĳһʱ����ʵ��Ĳ�����ͬһ������
 * �����͸����˶���̶߳����ݵ����ݹ�����synchronized���ڶ���̼߳�ͨ��ʱ�ܹ�������ݹ���
 */
