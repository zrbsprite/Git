package com.cmcc.slience.senior.singleton;

public class SafeSingleton {

	private SafeSingleton(){
		
	}
	
	private static SafeSingleton singleton = null;
	
	/**
	 * ��������: getInstance<br>
	 * ��������������̰߳�ȫ�����   <br>
	 * ����: ZRB<br>
	 * �޸����ڣ�2015��4��8������10:45:09<br>
	 * @return<br>
	 */
	public static SafeSingleton getInstance(){
		if(null==singleton){
			singleton = new SafeSingleton();
		}
		return singleton;
	}
	
	/**
	 * ��������: getSafeInstance<br>
	 * ������������������İ�ȫ����   <br>
	 * ����: ZRB<br>
	 * �޸����ڣ�2015��4��8������10:46:10<br>
	 * @return<br>
	 */
	public static synchronized SafeSingleton getSafeInstance(){
		if(null==singleton){
			singleton = new SafeSingleton();
		}
		return singleton;
	}
	
	/**
	 * ��������: getSafeInstance<br>
	 * ������������������İ�ȫ����   <br>
	 * ����: ZRB<br>
	 * �޸����ڣ�2015��4��8������10:46:10<br>
	 * @return<br>
	 */
	public static SafeSingleton getSafeInstance2(){
		synchronized(singleton){
			if(null==singleton){
				singleton = new SafeSingleton();
			}
		}
		return singleton;
	}
}
