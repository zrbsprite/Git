package com.cmcc.slience.senior.thread.pc1;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ��������JDK5.0֮��Java�ṩ�˸��ӽ�׳���̴߳�����ƣ�����ͬ�����������̳߳صȣ����ǿ���ʵ�ָ�ϸ���ȵ��߳̿��ơ�await()��signal()
 * ��������������ͬ�������ַ��������ǵĹ��ܻ����Ϻ�wait() /
 * nofity()��ͬ����ȫ����ȡ�����ǣ��������Ǻ����������������Lockֱ�ӹҹ������и��������ԡ�
 * ͨ����Lock�����ϵ���newCondition()������������������һ����������а󶨣��������Ʋ���������ʾ�����Դ�İ�ȫ��<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2016��3��8������5:48:16 <br>
 * E-mail: <br>
 *
 */
public class Storage {
	// �ֿ����洢��
	private final int MAX_SIZE = 100;

	// �ֿ�洢������
	private LinkedList<Object> list = new LinkedList<Object>();

	// ��
	private final Lock lock = new ReentrantLock();

	// �ֿ�������������
	private final Condition full = lock.newCondition();

	// �ֿ�յ���������
	private final Condition empty = lock.newCondition();

	// ����num����Ʒ
	public void produce(int num) {
		// �����
		lock.lock();

		// ����ֿ�ʣ����������
		while (list.size() + num > MAX_SIZE) {
			System.out.println("��Ҫ�����Ĳ�Ʒ������:" + num + "/t���������:" + list.size()
					+ "/t��ʱ����ִ����������!");
			try {
				// �������������㣬��������
				full.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// ����������������£�����num����Ʒ
		for (int i = 1; i <= num; ++i) {
			list.add(new Object());
		}

		System.out.println("���Ѿ�������Ʒ����:" + num + "/t���ֲִ���Ϊ��:" + list.size());

		// �������������߳�
		full.signalAll();
		empty.signalAll();

		// �ͷ���
		lock.unlock();
	}

	// ����num����Ʒ
	public void consume(int num) {
		// �����
		lock.lock();

		// ����ֿ�洢������
		while (list.size() < num) {
			System.out.println("��Ҫ���ѵĲ�Ʒ������:" + num + "/t���������:" + list.size()
					+ "/t��ʱ����ִ����������!");
			try {
				// �������������㣬��������
				empty.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// ����������������£�����num����Ʒ
		for (int i = 1; i <= num; ++i) {
			list.remove();
		}

		System.out.println("���Ѿ����Ѳ�Ʒ����:" + num + "/t���ֲִ���Ϊ��:" + list.size());

		// �������������߳�
		full.signalAll();
		empty.signalAll();

		// �ͷ���
		lock.unlock();
	}

	// set/get����
	public int getMAX_SIZE() {
		return MAX_SIZE;
	}

	public LinkedList<Object> getList() {
		return list;
	}

	public void setList(LinkedList<Object> list) {
		this.list = list;
	}
}
