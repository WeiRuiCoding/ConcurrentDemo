package demo;

/** 
 * @ClassName: TestConcurrentDemo 
 * @Description: 线程并发测试类 
 * @author HongDa
 * @date 2018年10月12日 上午11:36:34 
 */
public class TestConcurrentDemo {
	public static void main(String[] args){
		
		Object lock1 = new Object();
		Object lock2 = new Object();
		
		Thread thread1 = new Thread(){
			@Override
			public void run() {
				super.run();
				synchronized (lock1) {
					
					System.out.println("[" + Thread.currentThread().getName() + "] thread1 Get Lock1");
					
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					synchronized (lock2) {
						System.out.println("[" + Thread.currentThread().getName() + "] Thread1 Get Lock2");
					}
				}
			}
		};
		
		Thread thread2 = new Thread(){
			@Override
			public void run() {
				super.run();
				synchronized (lock2) {
					System.out.println("[" + Thread.currentThread().getName() + "] Thread2 Get Lock2");
					try {
						sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					synchronized (lock1) {
						System.out.println("[" + Thread.currentThread().getName() + "] Thread2 Get Lock1");
					}
				}
			}
		};
		
		thread1.start();
		thread2.start();
	}
}
