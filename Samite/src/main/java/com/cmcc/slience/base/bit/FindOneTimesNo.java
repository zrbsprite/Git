package com.cmcc.slience.base.bit;

import org.junit.Assert;
import org.junit.Test;

/**
 * 描述：一组数中只有一个数字只出现了一次，其余都出现了两次，找出出现一次的数字。利用了两个相同的数异或为0的特性<br>
 * 作者：ZRB <br>
 * 修改日期：2016年12月3日下午2:43:05 <br>
 */
public class FindOneTimesNo {

	public int find(int[] array){
		int result = 0;
		for(int one:array){
			result = result^one;
		}
		return result;
	}
	
	/**
	 * 方法名称: findTwo<br>
	 * 描述：找出一组数中只出现一次的两个数字，这里使用计数排序实现<br/>
	 * 作者: ZRB<br/>
	 * 修改日期：2016年12月3日下午2:49:01<br/>
	 * @param array
	 * @return
	 */
	public int[] findTwo(int[] array){
		/*
		 * 与找出一个不同的在于，不能通过一次遍历找出这个数字。想一下发现这一组数中两个出线一次的数字的位中必不同，一个为0一个为，这样就可以将数分成两组执行find就可以了.
		 * 把所有数的异或结果中的二进制表示中的第一个1的位置记下N，并以此数组分成两个
		 * xor是：11111111^00000000=11111111,00000101^00000010=00000111
		 * &:00000001&00000000=00000000,00000010&00000010=00000010
		 * |:00000001|00000000=00000001,00000010|00000010=00000010,00000000|00000000=00000000
		 */
		int res=0;
		int flag = 1;//00000001
		for(int one : array){
			res^=one;
		}
		//8->00001000
		//10-> 00001010
		//xor(异或)-> 00000010 = 2
		while((res&flag)==0)
			//左移一位
			//00000010
			flag<<=1;
		int[] result = new int[]{0,0};
		for(int one : array){
			if((one&flag)==0){
				result[0]=result[0]^one;
			}else{
				result[1]=result[1]^one;
			}
		}
		return result;
	}
	
	@Test
	public void test(){
		int[] array = new int[]{5,6,5,6,8,4,4,9,7,7,9};
		FindOneTimesNo bean = new FindOneTimesNo();
		Assert.assertEquals(8, bean.find(array));
		
		int[] array2 = new int[]{5,6,5,6,8,4,10,4,9,7,7,9};
		int[] result = findTwo(array2);
		System.out.println(result[0]+"--" + result[1]);
	}
}
