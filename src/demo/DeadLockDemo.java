package demo;

/** 
 * @ClassName: DeadLockDemo 
 * @Description: 一个简单的死锁实现
 * 线程间互相请求对方已持有的锁,形成死锁.
 * @author HongDa
 * @date 2018年9月7日 下午3:06:11 
 */
public class DeadLockDemo {
	public static void main(String[] args){
		
		Object lock1 = new Object();
		Object lock2 = new Object();
		
		/**
		 * 线程1获取到lock1锁后请求lock2锁
		 */
		Thread thread1 = new Thread(){
			@Override
			public void run() {
				synchronized (lock1) {
					System.out.println(Thread.currentThread().getName() + "获得到lock1锁");
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					synchronized (lock2) {
						System.out.println(Thread.currentThread().getName() + "获取到lock2锁");
					}
				}
			}
		};
		
		/**
		 * 线程2获取到Lock2锁请求Lock1锁
		 */
		Thread thread2 = new Thread(){
			@Override
			public void run() {
				synchronized (lock2) {
					System.out.println(Thread.currentThread().getName() + "获取到lock2锁");
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					synchronized (lock1) {
						System.out.println(Thread.currentThread().getName() + "获取到lock1锁");
					}
				}
			}
		};
		
		thread1.start();
		thread2.start();
	}
}
