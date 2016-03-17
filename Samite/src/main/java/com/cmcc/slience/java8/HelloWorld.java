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
 * 描述：一个 Stream 只可以使用一次。使用一次是指stream的终止Terminal操作<br>
 * 作者：ZRB <br>
 * 修改日期：2016年2月26日下午5:25:42 <br>
 *
 */
public class HelloWorld {
	
	/**
	 * 方法名称: limit<br>
	 * 描述：limit 返回 Stream 的前面 n 个元素
	 * 作者: ZRB
	 * 修改日期：2016年2月26日下午4:31:48
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
	 * 方法名称: skip<br>
	 * 描述：skip 则是扔掉前 n 个元素
	 * 作者: ZRB
	 * 修改日期：2016年2月26日下午4:36:56
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
	 * 方法名称: sorted<br>
	 * 描述：limit 和 skip 对 sorted 后的运行次数无影响
	 * 作者: ZRB
	 * 修改日期：2016年2月26日下午4:41:05
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
		// reduce第一个参数（空白字符）即为起始值，第二个参数（String::concat）为 BinaryOperator
		//这个方法的主要作用是把 Stream 元素组合起来
		// 字符串连接，concat = "ABCD"
		String concat = Stream.of("A", "B", "C", "D")
				.reduce("", String::concat);
		System.out.println(concat);

		// 求最小值，minValue = -3.0
		double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
		System.out.println(minValue);
		
		// 求和，sumValue = 10, 有起始值
		int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
		System.out.println(sumValue);
		// 求和，sumValue = 10, 无起始值
		sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
		System.out.println(sumValue);
		// 过滤，字符串连接，concat = "ace"
		concat = Stream.of("a", "B", "c", "D", "e", "F")
				.filter(x -> x.compareTo("Z") > 0).reduce("", String::concat);
		System.out.println(concat);
	}
	
	
	/**
	 * 方法名称: iterate<br>
	 * 描述：iterate 跟 reduce 操作很像，接受一个种子值，和一个 UnaryOperator（例如 f）。然后种子值成为 Stream 的第一个元素，f(seed) 为第二个，f(f(seed)) 第三个，以此类推。
	 * 作者: ZRB
	 * 修改日期：2016年2月26日下午4:56:59
	 *
	 */
	@Test
	public void iterate(){
		//生成等差数列
		Stream.iterate(0, n->n+3).limit(10).forEach(System.out::println);
	}
	
	/**
	 * 方法名称: supplier<br>
	 * 描述：通过实现 Supplier 接口，你可以自己来控制流的生成。这种情形通常用于随机数、常量的 Stream，或者需要前后元素间维持着某种状态信息的 Stream
	 * 	         由于它是无限的，在管道中，必须利用 limit 之类的操作限制 Stream 大小
	 * 作者: ZRB
	 * 修改日期：2016年2月26日下午5:05:39
	 */
	@Test
	public void supplier(){
		Random seed = new Random();
		//注意这里
		Supplier<Integer> random = seed::nextInt;
		Stream.generate(random).limit(10).forEach(System.out::println);
		
		//Another way
		IntStream.generate(() -> (int) (System.nanoTime() % 100)).limit(10).forEach(System.out::println);
	}
	
	/**
	 * 方法名称: allMatch<br>
	 * 描述：Stream 中全部元素符合传入的 predicate，返回 true，有一个就执行skip操作
	 * 作者: ZRB
	 * 修改日期：2016年2月26日下午5:11:13
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
	 * 方法名称: anyMatch<br>
	 * 描述：anyMatch 只要有一个满足就返回true
	 * 作者: ZRB
	 * 修改日期：2016年2月26日下午5:14:34
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
	 * 方法名称: noneMatch<br>
	 * 描述：noneMatch所有的都不满足返回true
	 * 作者: ZRB
	 * 修改日期：2016年2月26日下午5:15:58
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
	 * 方法名称: peek<br>
	 * 描述：对每个元素执行操作并返回一个新的 Stream
	 * 	   forEach 不能修改自己包含的本地变量值，也不能用 break/return 之类的关键字提前结束循环。
	 * 作者: ZRB
	 * 修改日期：2016年2月26日下午5:18:36
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
