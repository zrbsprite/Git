package com.cmcc.slience.senior.thread;

/**
 * 描述：synchronized同步的方法，线程执行完这个方法就释放锁，而不是整个线程执行完<br>
 * 	并且非静态方法只对同一个对象的多线程起作用，同一个类的不同对象是不起作用的
 * 作者：ZRB <br>
 * 修改日期：2015年2月11日下午4:13:44 <br> <br/>
 */
public class Synchronization {

	private int i = 0;
	
	public synchronized void up(){
		this.i++;
		System.out.println("id--" + Thread.currentThread().getId()+ ":" + i);
		//证明是线程执行完这个方法就释放锁，而不是整个线程执行完
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Synchronization syn = new Synchronization();
		
		Synchronization syn2 = new Synchronization();
		
		SecondThread second = syn.new SecondThread(syn);
		MineThread mine = syn.new MineThread(syn);
		MineThread mine2 = syn2.new MineThread(syn2);
		
		Thread thread2 = new Thread(second);
		Thread thread1 = new Thread(mine);
		Thread thread3 = new Thread(mine2);
		
		thread2.start();
		thread1.start();
		thread3.start();
	}
	
	private class MineThread implements Runnable{
		
		private Synchronization syn;
		
		MineThread(Synchronization syn){
			this.syn = syn;
		}
		
		@Override
		public void run() {
			System.out.println("mine start at:" + System.currentTimeMillis());
			syn.up();
			System.out.println("mine end at: " + System.currentTimeMillis());
		}
	}
	
	private class SecondThread implements Runnable{
		
		private Synchronization syn;
		
		SecondThread(Synchronization syn){
			this.syn = syn;
		}
		
		@Override
		public void run() {
			System.out.println("second start at:" + System.currentTimeMillis());
			syn.up();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("second end at:"+ System.currentTimeMillis());
		}
	}
}
