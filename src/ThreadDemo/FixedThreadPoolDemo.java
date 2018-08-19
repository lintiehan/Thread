package ThreadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {
	public static void main(String[] args) {
		ExecutorService executorService=Executors.newFixedThreadPool(5);
		for(int i=0;i<20;i++)
		{
			final int no=i;
			Runnable task=new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						System.out.println("into "+no);
						Thread.sleep(1000L);
						System.out.println("end "+no);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			executorService.execute(task);
		}
		System.out.println("main thread hava terminate");
		executorService.shutdown();
	}
}
