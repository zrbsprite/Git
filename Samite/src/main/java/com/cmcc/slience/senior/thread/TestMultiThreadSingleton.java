package com.cmcc.slience.senior.thread;

/**
 * ��������Ҫ�ǲ����ڵ���ģʽ�·����ǲ��ǻ�����߳�ͬ������<br/>
 * �����ǲ���<br/>
 * ���ߣ�ZRB <br/
 * �޸����ڣ�2015��2��28������2:53:51<br/>
 */
public class TestMultiThreadSingleton {

	public static void main(String[] args) {
		 final Singleton bean = Singleton.getInstence();
		 new Thread(){
			@Override
			public void run() {
				 int result = bean.add(10, 12);
				 System.out.println("a is :"+result);
			}
		 }.start();
		 new Thread(){
			@Override
			public void run() {
				 int result = bean.add(11, 15);
				 System.out.println("b is :"+result);
			}
		 }.start();
		 new Thread(){
				@Override
				public void run() {
					 int result = bean.add(10, 9);
					 System.out.println("c is :"+result);
				}
			 }.start();
	}
}
