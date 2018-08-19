package ThreadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolDemo {
	public static void main(String[] args) {
		ExecutorService executorService=Executors.newCachedThreadPool();
		for(int i=0;i<20;i++)
		{
			final int no=i;
			Runnable task=new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("into "+no);
					try {
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
		executorService.shutdown();
		System.out.println("main thread hava terminate");
	}
}
