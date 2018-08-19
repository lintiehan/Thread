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
		System.out.println("主线程在执行任务");
		System.out.println("task运行结果"+result.get());
	}
}
class CallableThread implements Callable<Integer>
{

	@Override
	public Integer call() throws Exception {
		 System.out.println("子线程在进行计算");
	        Thread.sleep(3000);
	        int sum = 0;
	        for(int i=0;i<100;i++)
	            sum += i;
	        return sum;
	}
	
}