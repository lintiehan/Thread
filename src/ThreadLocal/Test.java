package ThreadLocal;

public class Test {
	ThreadLocal<Long> longLocal = new ThreadLocal<>();
	ThreadLocal<String> stringLocal = new ThreadLocal<>();
	
	public void set()
	{
		longLocal.set(Thread.currentThread().getId());
		stringLocal.set(Thread.currentThread().getName());
	}
	public long getLong()
	{
		return longLocal.get();
	}
	public String getString()
	{
		return stringLocal.get();
	}
	public static void main(String[] args) throws InterruptedException {
		Test test=new Test();
		test.set();
		System.out.println(test.getLong());
		System.out.println(test.getString());
				
		Thread thread1=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				test.set();
                System.out.println(test.getLong());
                System.out.println(test.getString());
			}
		});
		thread1.start();
		thread1.join();
		
		
		   System.out.println(test.getLong());
           System.out.println(test.getString());
	 
	}
}