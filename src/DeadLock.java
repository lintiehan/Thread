
public class DeadLock implements Runnable {
	public boolean flag;
	 
	public DeadLock(boolean f) {
		flag = f;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			if(flag)
			{
				synchronized (Lock.locka) {
					System.out.println("if locka");
					synchronized (Lock.lockb) {
						System.out.println("if lockb");
					}
				}
			}else
			{
				synchronized (Lock.lockb) {
					System.out.println("else lockb");
					synchronized (Lock.locka) {
						System.out.println("else locka");
					}
				}
			}
		}
		
	}
	 public static void main(String args[])  
	    {  
	        Thread t1=new Thread(new DeadLock(true));  
	        Thread t2=new Thread(new DeadLock(false));  
	        t1.start();  
	        t2.start();  
	    }  
}
class Lock  
{  
    static Object locka=new Object();  
    static Object lockb=new Object();  
}  