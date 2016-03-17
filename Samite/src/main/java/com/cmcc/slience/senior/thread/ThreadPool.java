package com.cmcc.slience.senior.thread;

import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ������ʹ��jdk�Դ����̳߳�<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2015��2��11������3:01:40 <br> <br/>
 */
public class ThreadPool {

	public static final int TYPE_SINGLE = 1;

	public static final int TYPE_FIXED = 2;

	public static final int TYPE_CACHED = 3;

	public static final int TYPE_SCHEDULED = 4;

	private static ThreadPool threadPool = null;
	
	private ResourceBundle bundle = null;
	
	private ThreadPool(){
		init();
	}
	
	private void init(){
		//����Ҫ�������ļ���׺
		bundle = ResourceBundle.getBundle("configuration.ThreadPool");
	}
	
	public static ThreadPool getThreadPool(){
		if(threadPool==null){
			threadPool = new ThreadPool();
		}
		return threadPool;
	}
	
	private ExecutorService pool = null;

	/**
	 * ��������: getInstance<br>
	 * ��������ȡ�̳߳�ʵ��   <br>
	 *     ����ģʽ<br>
	 * ����: ZRB<br>
	 * �޸����ڣ�2015��2��11������2:20:47<br>
	 * @param poolType
	 * @return<br>
	 */
	public ExecutorService getInstance(int poolType) {
		if (pool == null) {
			switch (poolType) {
			case TYPE_SINGLE:
				pool = Executors.newSingleThreadExecutor();
				break;
			case TYPE_FIXED:
				pool = Executors.newFixedThreadPool(getPoolSizeConfig());
				break;
			case TYPE_CACHED :
				pool = Executors.newCachedThreadPool();
				break;
			case TYPE_SCHEDULED :
				pool = Executors.newScheduledThreadPool(getPoolSizeConfig());
				break;
			default:
				pool = Executors.newFixedThreadPool(getPoolSizeConfig());
				break;
			} 
		}
		return pool;
	}
	
	/**
	 * ��������: getInstanceByConfig<br>
	 * ���������������ļ������õ��̳߳���𴴽��̳߳�   <br>
	 * ����: ZRB<br>
	 * �޸����ڣ�2015��2��11������2:23:38<br>
	 * @return<br>
	 */
	public ExecutorService getInstanceByConfig() {
		int poolType = getPoolTypeConfig();
		return getInstance(poolType);
	}
	
	/**
	 * ��������: getPoolTypeConfig<br>
	 * ������Ҫ�������̳߳ص�����   <br>
	 * ����: ZRB<br>
	 * �޸����ڣ�2015��2��11������2:45:54<br>
	 * @return<br>
	 */
	private int getPoolTypeConfig() {
		String value = bundle.getString("THREAD_POOL_TYPE");
		return Integer.parseInt(value);
	}

	/**
	 * ��ȡ�̳߳ش�С����
	 * @return
	 */
	private int getPoolSizeConfig(){
		String value = bundle.getString("THREAD_POOL_SIZE");
		return Integer.parseInt(value);
	}
	
	public static void main(String[] args) {
		ThreadPool pool = ThreadPool.getThreadPool();
		ExecutorService service = pool.getInstanceByConfig();
		service.execute(new Thread(){
			@Override
			public void run() {
				System.out.println("Thread id is:" + Thread.currentThread().getId());
			}
		});
		//�����̵߳ķ�ʽ
		while(true){
			//do something
		}
	}
	
}
