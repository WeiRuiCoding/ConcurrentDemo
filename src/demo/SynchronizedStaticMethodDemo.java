package demo;

/**
 * @ClassName: SynchronizedStaticMethod 
 * @Description: 测试Synchronized同步静态方法  
 * @author HongDaWang
 * @date 2018年9月5日 下午3:10:11
 */
public class SynchronizedStaticMethodDemo {
	
	public static void main(String[] args){
		
		Thread thread1 = new Thread(){
			@Override
			public void run() {
				System.out.println("Thread1 [" + this.getName() + "] execute");
				SynStaticMethod.setValue(3, this);
			}
		};
		
		Thread thread2 = new Thread(){
			@Override
			public void run() {
				System.out.println("Thread2 [" + this.getName() + "] execute");
				SynStaticMethod.setValue(2, this);
			}
		};
		
		Thread thread3 = new Thread(){
			@Override
			public void run() {
				System.out.println("Thread3 [" + this.getName() + "] execute");
				SynStaticMethod.setValue(1, this);
			}
		};
		
		thread1.start();
		thread2.start();
		thread3.start();
	}
}

/** 
 * @ClassName: SynStaticMethod 
 * @Description: 静态方法被synchronized修饰的类 
 * @author HongDa
 * @date 2018年9月6日 上午10:56:45 
 */
class SynStaticMethod {
	
	/** 
	 * @Fields value : 共享资源 
	 */ 
	private static int value = 0;
	
	public synchronized static int setValue(int value,Thread thread){
		
		try {
			
			System.out.println("[" + thread.getName() + "] Get Lock");
			Thread.sleep(1000 * value);
			SynStaticMethod.value = value;
			System.out.println("[" + thread.getName() + "] Free Lock");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return value;
	}
	
	public static int getValue(){
		return value;
	}
}