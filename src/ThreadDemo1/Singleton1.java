package ThreadDemo1;

/*
 * 饿汉式单例
 *类加载的方式是按需加载，且只加载一次。因此，在上述单例类被加载时，就会实例化一个对象并交给自己的引用，供系统使用。
 *换句话说，在线程访问单例对象之前就已经创建好了。再加上，由于一个类在整个生命周期中只会被加载一次，因此该单例类只会创建一个实例，
 *也就是说，线程每次都只能也必定只可以拿到这个唯一的对象。因此就说，饿汉式单例天生就是线程安全的
 */
public class Singleton1 {
	// 指向自己实例的私有静态引用，主动创建
	private static Singleton1 singleton1 = new Singleton1();

	// 私有构造方法
	private Singleton1() {
	}

	// 以自己实例为返回值的静态的公有方法，静态工厂方法
	public static Singleton1 getSingleton1() {
		return singleton1;
	}
}

// 传统懒汉式单例
class Singleton2 {

	// 指向自己实例的私有静态引用
	private static Singleton2 singleton2;

	// 私有的构造方法
	private Singleton2() {
	}

	// 以自己实例为返回值的静态的公有方法，静态工厂方法
	public static Singleton2 getSingleton2() {
		// 被动创建，在真正需要使用时才去创建
		if (singleton2 == null) {
			singleton2 = new Singleton2();
		}
		return singleton2;
	}
}

/*
 * 同步延迟加载 — synchronized方法 线程安全的懒汉式单例
 */
class Singleton3 {

	private static Singleton3 singleton3;

	private Singleton3() {
	}

	// 使用 synchronized 修饰，临界资源的同步互斥访问
	public static synchronized Singleton3 getSingleton3() {
		if (singleton3 == null) {
			singleton3 = new Singleton3();
		}
		return singleton3;
	}
}

/*
 * 同步延迟加载 — synchronized块 线程安全的懒汉式单例
 */
class Singleton4 {

	private static Singleton4 singleton4;

	private Singleton4() {
	}

	public static Singleton4 getSingleton4() {
		synchronized (Singleton2.class) { // 使用 synchronized 块，临界资源的同步互斥访问
			if (singleton4 == null) {
				singleton4 = new Singleton4();
			}
		}
		return singleton4;
	}
}

/*
 * 同步延迟加载 — 使用内部类实现延迟加载 线程安全的懒汉式单例 我们可以使用内部类实现线程安全的懒汉式单例，这种方式也是一种效率比较高的做法，
 * 它与饿汉式单例的区别就是 ：这种方式不但是线程安全的，还是延迟加载的，真正做到了用时才初始化。
 * 
 * 当客户端调用getSingleton5()方法时，会触发Holder类的初始化。
 * 由于singleton5是Hold的类成员变量，因此在JVM调用Holder类的类构造器对其进行初始化时，
 * 虚拟机会保证一个类的类构造器在多线程环境中被正确的加锁、同步，如果多个线程同时去初始化一个类，
 * 那么只会有一个线程去执行这个类的类构造器，其他线程都需要阻塞等待，直到活动线程执行方法完毕。
 * 在这种情形下，其他线程虽然会被阻塞，但如果执行类构造器方法的那条线程退出后，其他线程在唤醒之后不会再次进入/执行类构造器， 因为
 * 在同一个类加载器下，一个类型只会被初始化一次，因此就保证了单例。
 */
class Singleton5 {

	// 私有内部类，按需加载，用时加载，也就是延迟加载
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
 * 双重检测同步延迟加载去创建单例
 * 为了在保证单例的前提下提高运行效率，我们需要对 singleton6 进行第二次检查，
 * 目的是避开过多的同步（因为这里的同步只需在第一次创建实例时才同步，一旦创建成功，以后获取实例时就不需要同步获取锁了）。
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
 * 借助于 ThreadLocal，我们可以实现双重检查模式的变体。我们将临界资源instance线程私有化(局部化)，
 * 具体到本例就是将双重检测的第一层检测条件 if (instance == null) 转换为线程局部范围内的操作
 */
class Singleton7{
	   // ThreadLocal 线程局部变量，将单例instance线程私有化
	private static ThreadLocal<Singleton7> threadlocal=new ThreadLocal<Singleton7>();
	private static Singleton7 instance;
	private Singleton7() {
		
	}
	public static Singleton7 getInstance()
	{
		// 第一次检查：若线程第一次访问，则进入if语句块；否则，若线程已经访问过，则直接返回ThreadLocal中的值
		if(threadlocal.get()==null)
		{
			synchronized (Singleton7.class) {
				if(instance==null) // 第二次检查：该单例是否被创建
				{
					instance=new Singleton7();
				}
			}
			threadlocal.set(instance);
		}
		return threadlocal.get(); // 将单例放入ThreadLocal中
	}
	

}
