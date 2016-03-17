package com.cmcc.slience.senior.thread.bqpc;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * ������BlockingQueue��JDK5.0���������ݣ�����һ���Ѿ����ڲ�ʵ����ͬ���Ķ��У�ʵ�ַ�ʽ���õ������ǵ�2��await() /
 * signal()�����������������ɶ���ʱָ��������С��������������������put()��take()������
 * put()����������������������������̣߳������ﵽ���ʱ���Զ������� take()����������������������������̣߳�����Ϊ0ʱ���Զ�������<br>
 * ��ʱʹ��BlockingQueue���ܻ����put()��System.out.println()�����ƥ��������������������֮��û��ͬ����ɵġ�
 * ����������������������put()����ʱ��put()�ڲ�������await()�������������̵߳�ִ�У�Ȼ���������߳�ִ�У�����take()������take()
 * �ڲ�������signal()������֪ͨ�������߳̿���ִ�У���ʹ�������ߵ�println()��û���е�����������ߵ�println()�ȱ�ִ�У��������������ƥ��������
 * ����BlockingQueue��ҿ��Է���ʹ�ã���ɲ����������⣬ֻ�������ͱ�Ķ���֮���ͬ�������⡣<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2016��3��8������5:44:01 <br>
 * E-mail: <br>
 *
 */
public class Storage {

	// �ֿ����洢��
	private final int MAX_SIZE = 100;

	// �ֿ�洢������
	private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<Object>(
			100);

	// ����num����Ʒ
	public void produce(int num) {
		// ����ֿ�ʣ������Ϊ0
		if (list.size() == MAX_SIZE) {
			System.out.println("���������:" + MAX_SIZE + "/t��ʱ����ִ����������!");
		}

		// ����������������£�����num����Ʒ
		for (int i = 1; i <= num; ++i) {
			try {
				// �����Ʒ���Զ�����
				list.put(new Object());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("���ֲִ���Ϊ��:" + list.size());
		}
	}

	// ����num����Ʒ
	public void consume(int num) {
		// ����ֿ�洢������
		if (list.size() == 0) {
			System.out.println("���������:0/t��ʱ����ִ����������!");
		}

		// ����������������£�����num����Ʒ
		for (int i = 1; i <= num; ++i) {
			try {
				// ���Ѳ�Ʒ���Զ�����
				list.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("���ֲִ���Ϊ��:" + list.size());
	}

	// set/get����
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
