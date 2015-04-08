package com.cmcc.slience.senior.thread;

/**
 * ������ͬ��������<br>
 * 	   ��ǰ��֪�������ڷ�����ʹ�ô�����<br>
 *   synchronized�������ڹ����������<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2015��2��11������4:29:39 <br> <br/>
 */
public class SynchronizationBlock {
	
	private int i;
	
	public void up(){
		//�ؼ���this�������ʲô���϶��ǵ�ǰ����
		//ΪʲôҪ��this������   i �أ���i�ǿ��Ա��ı�ģ���this����
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
