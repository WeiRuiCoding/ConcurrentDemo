package demo;

/** 
 * @ClassName: SynchronizedInstanceMethodDemo 
 * @Description: 测试synchronized同步实例方法
 * @author HongDa
 * @date 2018年9月7日 下午3:40:07 
 */
public class SynchronizedInstanceMethodDemo {
	public static void main(String[] args){
		
		SynInstanceMethod synInstanceMethod1 = new SynInstanceMethod();
		SynInstanceMethod synInstanceMethod2 = new SynInstanceMethod();
		SynInstanceMethod synInstanceMethod3 = new SynInstanceMethod();
		
		/**
		 * thread1和thread2用的是同一个实例对象 synInstanceMethod1
		 * thread3和thread4用的是同一个实例对象 synInstanceMethod2
		 * thread5使用实例对象 synInstanceMethod3,并重复获得锁
		 */
		SynInstanceMethodThread thread1 = new SynInstanceMethodThread(1, "Thread-1", synInstanceMethod1);
		SynInstanceMethodThread thread2 = new SynInstanceMethodThread(2, "Thread-2", synInstanceMethod1);
		SynInstanceMethodThread thread3 = new SynInstanceMethodThread(3, "Thread-3", synInstanceMethod2);
		SynInstanceMethodThread thread4 = new SynInstanceMethodThread(4, "Thread-4", synInstanceMethod2);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		
		new SynInstanceMethodThread(5, "Thread-5", synInstanceMethod3){
			@Override
			public void run(){
				System.out.println("[" + this.getThreadName() + "] Start");
				this.getSynInstanceMethod().setValue(this,5);
				System.out.println("[" + this.getThreadName() + "] End");
			}
		}.start();
	}
}

/** 
 * @ClassName: SynchronizedInstanceMethod 
 * @Description: 实例方法被synchronized修改的类 
 * @author HongDa
 * @date 2018年9月7日 下午3:40:56 
 */
class SynInstanceMethod{
	
	private int value = 0;

	public int getValue() {
		return value;
	}

	public synchronized void setValue(int value,SynInstanceMethodThread thread) {
		
		System.out.println("[" + thread.getThreadName() + "] Get Lock");
		System.out.println("[" + thread.getThreadName() + "] Get Lock Value=" + this.value);
		
		try {
			Thread.sleep(1000 * value);
			this.value = value;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("[" + thread.getThreadName() + "] Free Lock");
		System.out.println("[" + thread.getThreadName() + "] Free Lock Value=" + this.value);
	}
	
	public synchronized void setValue(SynInstanceMethodThread thread,int value) {
		
		System.out.println("[" + thread.getThreadName() + "] Get Lock");
		System.out.println("[" + thread.getThreadName() + "] Get Lock Value=" + this.value);
		
		try {
			Thread.sleep(1000 * value);
			this.value = value;
			this.setValue(value * 2, thread);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("[" + thread.getThreadName() + "] Free Lock");
		System.out.println("[" + thread.getThreadName() + "] Free Lock Value=" + this.value);
	}
}

/** 
 * @ClassName: SynInstanceMethodThread 
 * @Description: 自定义测试同步实例方法的线程类
 * @author HongDa
 * @date 2018年9月7日 下午4:01:00 
 */
class SynInstanceMethodThread extends Thread{
	
	private int num;
	private String threadName;
	private SynInstanceMethod synInstanceMethod;
	
	public SynInstanceMethodThread(int num,String name,SynInstanceMethod synInstanceMethod) {
		this.num = num;
		this.threadName = name;
		this.synInstanceMethod = synInstanceMethod;
	}
	
	@Override
	public void run() {
		System.out.println("[" + threadName + "] Start");
		synInstanceMethod.setValue(num,this);
		System.out.println("[" + threadName + "] End");
	}
	
	public String getThreadName(){
		return threadName;
	}
	
	public SynInstanceMethod getSynInstanceMethod(){
		return synInstanceMethod;
	}
}
