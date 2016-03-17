package com.cmcc.slience.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

/**
 * ������һ�� Stream ֻ����ʹ��һ�Ρ�ʹ��һ����ָstream����ֹTerminal����<br>
 * ���ߣ�ZRB <br>
 * �޸����ڣ�2016��2��26������5:25:42 <br>
 *
 */
public class HelloWorld {
	
	/**
	 * ��������: limit<br>
	 * ������limit ���� Stream ��ǰ�� n ��Ԫ��
	 * ����: ZRB
	 * �޸����ڣ�2016��2��26������4:31:48
	 */
	@Test
	public void limit(){
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			list.add(i);
		}
		List<Integer> son = list.stream().limit(3).collect(Collectors.toList());
		Assert.assertEquals(3, son.size());
	}
	
	/**
	 * ��������: skip<br>
	 * ������skip �����ӵ�ǰ n ��Ԫ��
	 * ����: ZRB
	 * �޸����ڣ�2016��2��26������4:36:56
	 */
	@Test
	public void skip(){
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			list.add(i);
		}
		List<Integer> son = list.stream().skip(3).collect(Collectors.toList());
		Assert.assertEquals(7, son.size());
	}
	
	/**
	 * ��������: sorted<br>
	 * ������limit �� skip �� sorted ������д�����Ӱ��
	 * ����: ZRB
	 * �޸����ڣ�2016��2��26������4:41:05
	 */
	@Test
	public void sorted(){
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			list.add(i);
		}
		List<Integer> son = list.stream().sorted().skip(3).collect(Collectors.toList());
		Assert.assertEquals(7, son.size());
	}
	
	@Test
	public void reduce(){
		// reduce��һ���������հ��ַ�����Ϊ��ʼֵ���ڶ���������String::concat��Ϊ BinaryOperator
		//�����������Ҫ�����ǰ� Stream Ԫ���������
		// �ַ������ӣ�concat = "ABCD"
		String concat = Stream.of("A", "B", "C", "D")
				.reduce("", String::concat);
		System.out.println(concat);

		// ����Сֵ��minValue = -3.0
		double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
		System.out.println(minValue);
		
		// ��ͣ�sumValue = 10, ����ʼֵ
		int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
		System.out.println(sumValue);
		// ��ͣ�sumValue = 10, ����ʼֵ
		sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
		System.out.println(sumValue);
		// ���ˣ��ַ������ӣ�concat = "ace"
		concat = Stream.of("a", "B", "c", "D", "e", "F")
				.filter(x -> x.compareTo("Z") > 0).reduce("", String::concat);
		System.out.println(concat);
	}
	
	
	/**
	 * ��������: iterate<br>
	 * ������iterate �� reduce �������񣬽���һ������ֵ����һ�� UnaryOperator������ f����Ȼ������ֵ��Ϊ Stream �ĵ�һ��Ԫ�أ�f(seed) Ϊ�ڶ�����f(f(seed)) ���������Դ����ơ�
	 * ����: ZRB
	 * �޸����ڣ�2016��2��26������4:56:59
	 *
	 */
	@Test
	public void iterate(){
		//���ɵȲ�����
		Stream.iterate(0, n->n+3).limit(10).forEach(System.out::println);
	}
	
	/**
	 * ��������: supplier<br>
	 * ������ͨ��ʵ�� Supplier �ӿڣ�������Լ��������������ɡ���������ͨ������������������� Stream��������Ҫǰ��Ԫ�ؼ�ά����ĳ��״̬��Ϣ�� Stream
	 * 	         �����������޵ģ��ڹܵ��У��������� limit ֮��Ĳ������� Stream ��С
	 * ����: ZRB
	 * �޸����ڣ�2016��2��26������5:05:39
	 */
	@Test
	public void supplier(){
		Random seed = new Random();
		//ע������
		Supplier<Integer> random = seed::nextInt;
		Stream.generate(random).limit(10).forEach(System.out::println);
		
		//Another way
		IntStream.generate(() -> (int) (System.nanoTime() % 100)).limit(10).forEach(System.out::println);
	}
	
	/**
	 * ��������: allMatch<br>
	 * ������Stream ��ȫ��Ԫ�ط��ϴ���� predicate������ true����һ����ִ��skip����
	 * ����: ZRB
	 * �޸����ڣ�2016��2��26������5:11:13
	 */
	@Test
	public void allMatch(){
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			list.add(i);
		}
		boolean flag = list.stream().allMatch(val -> val<=5);
		Assert.assertTrue(!flag);
	}
	
	/**
	 * ��������: anyMatch<br>
	 * ������anyMatch ֻҪ��һ������ͷ���true
	 * ����: ZRB
	 * �޸����ڣ�2016��2��26������5:14:34
	 *
	 */
	@Test
	public void anyMatch(){
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			list.add(i);
		}
		boolean flag = list.stream().anyMatch(val -> val<=5);
		Assert.assertTrue(flag);
	}
	
	/**
	 * ��������: noneMatch<br>
	 * ������noneMatch���еĶ������㷵��true
	 * ����: ZRB
	 * �޸����ڣ�2016��2��26������5:15:58
	 *
	 */
	@Test
	public void noneMatch(){
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<10;i++){
			list.add(i);
		}
		boolean flag = list.stream().noneMatch(val -> val>50);
		Assert.assertTrue(flag);
	}
	
	/**
	 * ��������: peek<br>
	 * ��������ÿ��Ԫ��ִ�в���������һ���µ� Stream
	 * 	   forEach �����޸��Լ������ı��ر���ֵ��Ҳ������ break/return ֮��Ĺؼ�����ǰ����ѭ����
	 * ����: ZRB
	 * �޸����ڣ�2016��2��26������5:18:36
	 *
	 */
	@Test
	public void peek(){
		Stream.of("one", "two", "three", "four")
		 .filter(e -> e.length() > 3)
		 .peek(e -> System.out.println("Filtered value: " + e))
		 .map(String::toUpperCase)
		 .peek(e -> System.out.println("Mapped value: " + e))
		 .collect(Collectors.toList());
	}
	
}
