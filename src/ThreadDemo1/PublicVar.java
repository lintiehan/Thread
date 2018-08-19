package ThreadDemo1;

public class PublicVar {
	public String username="A";
	public String pwd="aaaa";

	//同步实例方法
	public synchronized void setValue(String username,String pws)
	{
		try {
			this.username=username;
			Thread.sleep(5000);
			this.pwd=pwd;

			System.out.println("setValue threadName="+Thread.currentThread().getName());
			System.out.println(" username="+username +" pwd="+pwd);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getValue()
	{
		System.out.println("getValue threadName="+Thread.currentThread().getName());
		System.out.println(" username="+username +" pwd="+pwd);
	}
}
