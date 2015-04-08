package com.cmcc.slience.senior.thread;

/**
 * ��������̬�����ͬ�������еĶ�����Ч,��Ϊ��̬�ķ�������ֱ�ӷ��ʷǾ�̬�ĳ�Ա����<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2015��2��11������4:48:16 <br> <br/>
 */
public class SynchronizedStatic {

	private static int i;
	
	public synchronized static void up(){
		i++;
		System.out.println("id--" + Thread.currentThread().getId()+ ":" + i);
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			
		}
	}
	
	public static void main(String[] args) {
		new Thread(){
			@Override
			public void run() {
				SynchronizedStatic.up();
			}
		}.start();
		new Thread(){
			@Override
			public void run() {
				SynchronizedStatic.up();
				//��ʱup�Ѿ�ִ����ɲ���Ӱ��up�ĵȴ�ʱ��
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					
				}
			}
		}.start();
		new Thread(){
			@Override
			public void run() {
				SynchronizedStatic.up();
			}
		}.start();
	}
}
