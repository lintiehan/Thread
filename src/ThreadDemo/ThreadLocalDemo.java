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
		// 获取当前线程的名字
		String currentThreadName = Thread.currentThread().getName();
		System.out.println(currentThreadName + " is running");
		// 产生一个随机数并打印
		Random random = new Random();
		int age = random.nextInt(100);
		System.out.println("thread " + currentThreadName + " set age to:" + age);

		// 获取一个Student对象，并将随机数年龄插入到对象属性中
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
		// 获取本地线程变量并强制转换为Student类型
		Student s = (Student) studentLocal.get();
		// 线程首次执行此方法的时候，studentLocal.get()肯定为null
		if (s == null) {
			// 创建一个Student对象，并保存到本地线程变量studentLocal中
			s = new Student();
			studentLocal.set(s); 
		}
		return s;
	}
}


/**
 * ThreadLocal 使用场合主要解决多线程中数据因并发产生不一致的问题
 * ThreadLocal 为每一个线程中并发访问的数据提供一个副本，通过访问副本来运行业务，耗费了内存，但大大减少了线程永不所带来的性能消耗
 * 也减少了线程并发控制的复杂度
 * 
 * 
 * synchronized是利用锁的机制，使变量或代码块在某一时刻只能被一个线程访问
 * ThreadLocal为每一个线程都提供了变量的副本，使得每个线程在某一时间访问到的并不是同一个对象
 * 这样就隔离了多个线程对数据的数据共享，而synchronized用于多个线程间通信时能够获得数据共享
 */
