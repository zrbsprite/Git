package com.cmcc.slience.senior.thread.junior;

/**
 * ���������̳߳���<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2016��3��2������4:38:34 <br>
 * E-mail: <br>
 */
public class MultiThread extends Thread{

	private int MAX_VALUE = 0;
	
	/*
	 * �������߳�ͬ�����⣬ÿ���߳����Ÿ��Ե�max_value�������ھ���������
	 * ��һ�����빫����Դ
	 */
	
	@Override
	public void run() {
		while(true){
			if(MAX_VALUE >= 50){
				break;
			}
			MAX_VALUE ++;
			System.out.println(currentThread().getName()+":" + MAX_VALUE);
		}
	}
	
	public static void main(String[] args) {
		MultiThread one = new MultiThread();
		MultiThread two = new MultiThread();
		MultiThread three = new MultiThread();
		one.start();
		two.start();
		three.start();
	}
}
