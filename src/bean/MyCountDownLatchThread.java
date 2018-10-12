package bean;

import java.util.concurrent.CountDownLatch;

/** 
 * @ClassName: MyCountDownLatchThread 
 * @Description: 自定义CountDownLatch线程类
 * @author HongDa
 * @date 2018年9月6日 上午10:25:30 
 */
public class MyCountDownLatchThread extends Thread{
	
	/** 
	 * @Fields treadName : 线程名称 
	 */ 
	private int treadName;
	
	/** 
	 * @Fields flag : 是否是等待线程,默认true
	 */ 
	private boolean flag;
	
	/** 
	 * @Fields countDownLatch : 倒计时锁对象的引用
	 */ 
	private CountDownLatch countDownLatch;
	
	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param name
	* @param flag
	* @param countDownLatch 
	*/
	public MyCountDownLatchThread(int name,boolean flag,CountDownLatch countDownLatch){
		
		this.treadName = name;
		this.flag = flag;
		this.countDownLatch = countDownLatch;
	}
	
	@Override
	public void run() {
		if(!flag){
			//工作线程调用CountDownLatch的countDown()方法
			try {
				System.out.println("[Thread-" + treadName + "]: Start");
				Thread.sleep(treadName * 1000);
				System.out.println("[Thread-" + treadName + "]: The Num Before CountDown Is:" + countDownLatch.getCount());
				countDownLatch.countDown();
				System.out.println("[Thread-" + treadName + "]: The Num After CountDown Is:" + countDownLatch.getCount());
				System.out.println("[Thread-" + treadName + "] End");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}else{
			//等待线程调用CountDownLatch的await()方法
			try {
				System.out.println("[Thread-Wait-" + treadName + "]: Start ");
				System.out.println("[Thread-Wait-" + treadName + "]: The Num Before Await Is:" + countDownLatch.getCount());
				System.out.println("[Thread-Wait-" + treadName + "]: Waiting ...");
				countDownLatch.await();
				System.out.println("[Thread-Wait-" + treadName + "]: Continue ...");
				System.out.println("[Thread-Wait-" + treadName + "]: The Num After Await Is:" + countDownLatch.getCount());
				System.out.println("[Thread-Wait-" + treadName + "]: End");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getTreadName() {
		return treadName;
	}

	public void setTreadName(int treadName) {
		this.treadName = treadName;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}

	public void setCountDownLatch(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}
}
