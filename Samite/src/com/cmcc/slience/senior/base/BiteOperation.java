package com.cmcc.slience.senior.base;

public class BiteOperation {

	public static void main(String[] args) {
		//hello();
		
	}
	
	private static void first(){
		//一个32位的数字  3 的右移两位的计算过程和结果
		//0000 0000 0000 0000 0000 0000 0000 0011
		//00 0000 0000 0000 0000 0000 0000 0011
		//00 0000 0000 0000 0000 0000 0000 0011 00
		//0000 0000 0000 0000 0000 0000 0000 1100
		//转10
		/*
		 * 1*2的0次方+1*2的1次方+0*2的3次方....... = 3
		 * 0*2-0+0*2-1+1*2-2+1*2-3+0*2-4... = 4+8=12
		 */
		int a = 0x1f2e5d;
		String aStr = (a+"").replace("0x", "");
		int result = 0;
		for(int i=0;i<aStr.length();i++){
			/**
			 * 0123456789abcdef
			 * a=10
			 * b=11
			 * c=12
			 * d=13
			 * e=14
			 * f=15
			 * 
			 * d*16^0
			 * 5*16^1
			 * e*16^2
			 * 2*16^3
			 * f*16^4
			 * 1*16^5
			 * 相加即是十进制数字
			 */
			
			int temp = 1234;
			temp = 4*10^0+3*10^1+2*10^2+1*10^3;
			temp = 4+30+200+1000;
			/*
			 * 二进制、八进制、十进制、十六进制都是一样的道理。 
			 */
		}
		
	}
	
	/**
	 * 最简单的测试<br>
	 * 有符号位移
	 */
	private static void hello(){
		int i = 3;
		//箭头朝向就是位移方向，左移几位就是乘以2的几次方
		int iResult = i<<2;
		System.out.println(iResult);
		//右移几位相当于除以2的几次方
		int j = 8;
		int iRightResult = j>>2;
		System.out.println(iRightResult);
	}
}
