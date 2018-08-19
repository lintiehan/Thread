package ThreadDemo1;

public class Test {
	public static void main(String[] args) {
		PublicVar p=new PublicVar();
		p.getValue();
		ThreadA a=new ThreadA(p);
		a.start();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p.getValue();
	}

}
class ThreadA extends Thread
{
	private PublicVar p;
	public ThreadA(PublicVar p) {
		super();
		this.p=p;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		p.setValue("B", "bbbb");
	}
}