package ThreadDemo1;

/*
 * ����ʽ����
 *����صķ�ʽ�ǰ�����أ���ֻ����һ�Ρ���ˣ������������౻����ʱ���ͻ�ʵ����һ�����󲢽����Լ������ã���ϵͳʹ�á�
 *���仰˵�����̷߳��ʵ�������֮ǰ���Ѿ��������ˡ��ټ��ϣ�����һ��������������������ֻ�ᱻ����һ�Σ���˸õ�����ֻ�ᴴ��һ��ʵ����
 *Ҳ����˵���߳�ÿ�ζ�ֻ��Ҳ�ض�ֻ�����õ����Ψһ�Ķ�����˾�˵������ʽ�������������̰߳�ȫ��
 */
public class Singleton1 {
	// ָ���Լ�ʵ����˽�о�̬���ã���������
	private static Singleton1 singleton1 = new Singleton1();

	// ˽�й��췽��
	private Singleton1() {
	}

	// ���Լ�ʵ��Ϊ����ֵ�ľ�̬�Ĺ��з�������̬��������
	public static Singleton1 getSingleton1() {
		return singleton1;
	}
}

// ��ͳ����ʽ����
class Singleton2 {

	// ָ���Լ�ʵ����˽�о�̬����
	private static Singleton2 singleton2;

	// ˽�еĹ��췽��
	private Singleton2() {
	}

	// ���Լ�ʵ��Ϊ����ֵ�ľ�̬�Ĺ��з�������̬��������
	public static Singleton2 getSingleton2() {
		// ������������������Ҫʹ��ʱ��ȥ����
		if (singleton2 == null) {
			singleton2 = new Singleton2();
		}
		return singleton2;
	}
}

/*
 * ͬ���ӳټ��� �� synchronized���� �̰߳�ȫ������ʽ����
 */
class Singleton3 {

	private static Singleton3 singleton3;

	private Singleton3() {
	}

	// ʹ�� synchronized ���Σ��ٽ���Դ��ͬ���������
	public static synchronized Singleton3 getSingleton3() {
		if (singleton3 == null) {
			singleton3 = new Singleton3();
		}
		return singleton3;
	}
}

/*
 * ͬ���ӳټ��� �� synchronized�� �̰߳�ȫ������ʽ����
 */
class Singleton4 {

	private static Singleton4 singleton4;

	private Singleton4() {
	}

	public static Singleton4 getSingleton4() {
		synchronized (Singleton2.class) { // ʹ�� synchronized �飬�ٽ���Դ��ͬ���������
			if (singleton4 == null) {
				singleton4 = new Singleton4();
			}
		}
		return singleton4;
	}
}

/*
 * ͬ���ӳټ��� �� ʹ���ڲ���ʵ���ӳټ��� �̰߳�ȫ������ʽ���� ���ǿ���ʹ���ڲ���ʵ���̰߳�ȫ������ʽ���������ַ�ʽҲ��һ��Ч�ʱȽϸߵ�������
 * �������ʽ������������� �����ַ�ʽ�������̰߳�ȫ�ģ������ӳټ��صģ�������������ʱ�ų�ʼ����
 * 
 * ���ͻ��˵���getSingleton5()����ʱ���ᴥ��Holder��ĳ�ʼ����
 * ����singleton5��Hold�����Ա�����������JVM����Holder����๹����������г�ʼ��ʱ��
 * ������ᱣ֤һ������๹�����ڶ��̻߳����б���ȷ�ļ�����ͬ�����������߳�ͬʱȥ��ʼ��һ���࣬
 * ��ôֻ����һ���߳�ȥִ���������๹�����������̶߳���Ҫ�����ȴ���ֱ����߳�ִ�з�����ϡ�
 * �����������£������߳���Ȼ�ᱻ�����������ִ���๹���������������߳��˳��������߳��ڻ���֮�󲻻��ٴν���/ִ���๹������ ��Ϊ
 * ��ͬһ����������£�һ������ֻ�ᱻ��ʼ��һ�Σ���˾ͱ�֤�˵�����
 */
class Singleton5 {

	// ˽���ڲ��࣬������أ���ʱ���أ�Ҳ�����ӳټ���
	private static class Holder {
		private static Singleton5 singleton5 = new Singleton5();
	}

	private Singleton5() {

	}

	public static Singleton5 getSingleton5() {
		return Holder.singleton5;
	}
}

/*
 * ˫�ؼ��ͬ���ӳټ���ȥ��������
 * Ϊ���ڱ�֤������ǰ�����������Ч�ʣ�������Ҫ�� singleton6 ���еڶ��μ�飬
 * Ŀ���Ǳܿ������ͬ������Ϊ�����ͬ��ֻ���ڵ�һ�δ���ʵ��ʱ��ͬ����һ�������ɹ����Ժ��ȡʵ��ʱ�Ͳ���Ҫͬ����ȡ���ˣ���
 */
class Singleton6 {
	private static volatile Singleton6 singleton6;

	private Singleton6() {
	}

	public static Singleton6 getSingleton6() {
		if (singleton6 == null) {
			synchronized (Singleton6.class) {
				if (singleton6 == null) {
					singleton6 = new Singleton6();
				}
			}
		}
		return singleton6;
	}
}
/*
 * ������ ThreadLocal�����ǿ���ʵ��˫�ؼ��ģʽ�ı��塣���ǽ��ٽ���Դinstance�߳�˽�л�(�ֲ���)��
 * ���嵽�������ǽ�˫�ؼ��ĵ�һ�������� if (instance == null) ת��Ϊ�ֲ߳̾���Χ�ڵĲ���
 */
class Singleton7{
	   // ThreadLocal �ֲ߳̾�������������instance�߳�˽�л�
	private static ThreadLocal<Singleton7> threadlocal=new ThreadLocal<Singleton7>();
	private static Singleton7 instance;
	private Singleton7() {
		
	}
	public static Singleton7 getInstance()
	{
		// ��һ�μ�飺���̵߳�һ�η��ʣ������if���飻�������߳��Ѿ����ʹ�����ֱ�ӷ���ThreadLocal�е�ֵ
		if(threadlocal.get()==null)
		{
			synchronized (Singleton7.class) {
				if(instance==null) // �ڶ��μ�飺�õ����Ƿ񱻴���
				{
					instance=new Singleton7();
				}
			}
			threadlocal.set(instance);
		}
		return threadlocal.get(); // ����������ThreadLocal��
	}
	

}
