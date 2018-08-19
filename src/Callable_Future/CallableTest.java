package Callable_Future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor=Executors.newCachedThreadPool();
		CallableThread task=new CallableThread();
		Future<Integer> result=executor.submit(task);
		
		executor.shutdown();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("���߳���ִ������");
		System.out.println("task���н��"+result.get());
	}
}
class CallableThread implements Callable<Integer>
{

	@Override
	public Integer call() throws Exception {
		 System.out.println("���߳��ڽ��м���");
	        Thread.sleep(3000);
	        int sum = 0;
	        for(int i=0;i<100;i++)
	            sum += i;
	        return sum;
	}
	
}