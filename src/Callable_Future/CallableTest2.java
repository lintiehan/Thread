package Callable_Future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableTest2 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor=Executors.newSingleThreadExecutor();
		CallableTask call=new CallableTask();
		FutureTask<String> task=new FutureTask<String>(call);
		executor.submit(task);
		 
		Thread.sleep(5000);
		System.out.println("主线程等待5s");
		String str=task.get();
		 System.out.println("Future已拿到数据，str=" + str + ";当前时间为：" + System.currentTimeMillis());
		 executor.shutdown();}
}
class CallableTask implements Callable<String>
{

	@Override
	public String call() throws Exception {
		System.out.println("进入Call方法，开始休眠，休眠时间为：" + System.currentTimeMillis());
        Thread.sleep(10000);
        return "今天停电";
	}
	
}