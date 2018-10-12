package demo;

import java.util.concurrent.CountDownLatch;

import bean.MyCountDownLatchThread;

/**
 * @ClassName: CountDownLatchTest 
 * @Description: 测试倒计时锁的用法 
 * @author HongDa
 * @date 2018年9月5日 下午3:59:45
 */
public class CountDownLatchDemo extends Thread{
	
	public static void main(String[] args){
		
		CountDownLatch countDownLatch = new CountDownLatch(4);
		
		Thread thread1 = new MyCountDownLatchThread(1, false, countDownLatch);
		Thread thread2 = new MyCountDownLatchThread(2, false, countDownLatch);
		Thread thread3 = new MyCountDownLatchThread(3, false, countDownLatch);
		Thread thread4 = new MyCountDownLatchThread(4, false, countDownLatch);
		Thread threadAwait = new MyCountDownLatchThread(5, true, countDownLatch);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		
		/**
		 * 线程threadAwait等待上面四个线程执行完再继续执行
		 */
		threadAwait.start();
	}
}