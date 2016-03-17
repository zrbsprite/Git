package com.cmcc.slience.senior.thread.bqpc;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 描述：BlockingQueue是JDK5.0的新增内容，它是一个已经在内部实现了同步的队列，实现方式采用的是我们第2种await() /
 * signal()方法。它可以在生成对象时指定容量大小。它用于阻塞操作的是put()和take()方法。
 * put()方法：类似于我们上面的生产者线程，容量达到最大时，自动阻塞。 take()方法：类似于我们上面的消费者线程，容量为0时，自动阻塞。<br>
 * 有时使用BlockingQueue可能会出现put()和System.out.println()输出不匹配的情况，这是由于它们之间没有同步造成的。
 * 当缓冲区已满，生产者在put()操作时，put()内部调用了await()方法，放弃了线程的执行，然后消费者线程执行，调用take()方法，take()
 * 内部调用了signal()方法，通知生产者线程可以执行，致使在消费者的println()还没运行的情况下生产者的println()先被执行，所以有了输出不匹配的情况。
 * 对于BlockingQueue大家可以放心使用，这可不是它的问题，只是在它和别的对象之间的同步有问题。<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月8日下午5:44:01 <br>
 * E-mail: <br>
 *
 */
public class Storage {

	// 仓库最大存储量
	private final int MAX_SIZE = 100;

	// 仓库存储的载体
	private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<Object>(
			100);

	// 生产num个产品
	public void produce(int num) {
		// 如果仓库剩余容量为0
		if (list.size() == MAX_SIZE) {
			System.out.println("【库存量】:" + MAX_SIZE + "/t暂时不能执行生产任务!");
		}

		// 生产条件满足情况下，生产num个产品
		for (int i = 1; i <= num; ++i) {
			try {
				// 放入产品，自动阻塞
				list.put(new Object());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("【现仓储量为】:" + list.size());
		}
	}

	// 消费num个产品
	public void consume(int num) {
		// 如果仓库存储量不足
		if (list.size() == 0) {
			System.out.println("【库存量】:0/t暂时不能执行生产任务!");
		}

		// 消费条件满足情况下，消费num个产品
		for (int i = 1; i <= num; ++i) {
			try {
				// 消费产品，自动阻塞
				list.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("【现仓储量为】:" + list.size());
	}

	// set/get方法
	public LinkedBlockingQueue<Object> getList() {
		return list;
	}

	public void setList(LinkedBlockingQueue<Object> list) {
		this.list = list;
	}

	public int getMAX_SIZE() {
		return MAX_SIZE;
	}
}
