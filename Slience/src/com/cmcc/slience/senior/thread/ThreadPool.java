package com.cmcc.slience.senior.thread;

import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述：使用jdk自带的线程池<br>
 * 作者：ZRB <br>
 * 修改日期：2015年2月11日下午3:01:40 <br> <br/>
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
		//不需要带配置文件后缀
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
	 * 方法名称: getInstance<br>
	 * 描述：获取线程池实例   <br>
	 *     单例模式<br>
	 * 作者: ZRB<br>
	 * 修改日期：2015年2月11日下午2:20:47<br>
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
	 * 方法名称: getInstanceByConfig<br>
	 * 描述：根据配置文件中配置的线程池类别创建线程池   <br>
	 * 作者: ZRB<br>
	 * 修改日期：2015年2月11日下午2:23:38<br>
	 * @return<br>
	 */
	public ExecutorService getInstanceByConfig() {
		int poolType = getPoolTypeConfig();
		return getInstance(poolType);
	}
	
	/**
	 * 方法名称: getPoolTypeConfig<br>
	 * 描述：要创建的线程池的类型   <br>
	 * 作者: ZRB<br>
	 * 修改日期：2015年2月11日下午2:45:54<br>
	 * @return<br>
	 */
	private int getPoolTypeConfig() {
		String value = bundle.getString("THREAD_POOL_TYPE");
		return Integer.parseInt(value);
	}

	/**
	 * 获取线程池大小配置
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
		//常挂线程的方式
		while(true){
			//do something
		}
	}
	
}
