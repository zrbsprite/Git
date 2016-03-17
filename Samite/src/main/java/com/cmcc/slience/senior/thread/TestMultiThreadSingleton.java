package com.cmcc.slience.senior.thread;

/**
 * 描述：主要是测试在单例模式下方法是不是会存在线程同步问题<br/>
 * 结论是不会<br/>
 * 作者：ZRB <br/
 * 修改日期：2015年2月28日下午2:53:51<br/>
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
