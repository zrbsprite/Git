package com.cmcc.slience.senior.thread;

/**
 * 描述：同步代码块儿<br>
 * 	   以前不知道可以在方法中使用代码块儿<br>
 *   synchronized不能用在构造代码块儿中<br>
 * 作者：ZRB <br>
 * 修改日期：2015年2月11日下午4:29:39 <br> <br/>
 */
public class SynchronizationBlock {
	
	private int i;
	
	public void up(){
		//关键是this代表的是什么，肯定是当前对象啊
		//为什么要用this而不是   i 呢？，i是可以被改编的，而this不能
		synchronized(this){
			i++;
			System.out.println("id--" + Thread.currentThread().getId()+ ":" + i);
		}
		i++;
	}
	
	public static void main(String[] args) {
		SynchronizationBlock syn = new SynchronizationBlock();
		
		SynchronizationBlock syn2 = new SynchronizationBlock();
		
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
		
		private SynchronizationBlock syn;
		
		MineThread(SynchronizationBlock syn){
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
		
		private SynchronizationBlock syn;
		
		SecondThread(SynchronizationBlock syn){
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
