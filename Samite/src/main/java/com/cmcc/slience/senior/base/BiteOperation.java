package com.cmcc.slience.senior.base;

public class BiteOperation {

	public static void main(String[] args) {
		//hello();
		
	}
	
	private static void first(){
		//һ��32λ������  3 ��������λ�ļ�����̺ͽ��
		//0000 0000 0000 0000 0000 0000 0000 0011
		//00 0000 0000 0000 0000 0000 0000 0011
		//00 0000 0000 0000 0000 0000 0000 0011 00
		//0000 0000 0000 0000 0000 0000 0000 1100
		//ת10
		/*
		 * 1*2��0�η�+1*2��1�η�+0*2��3�η�....... = 3
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
			 * ��Ӽ���ʮ��������
			 */
			
			int temp = 1234;
			temp = 4*10^0+3*10^1+2*10^2+1*10^3;
			temp = 4+30+200+1000;
			/*
			 * �����ơ��˽��ơ�ʮ���ơ�ʮ�����ƶ���һ���ĵ��� 
			 */
		}
		
	}
	
	/**
	 * ��򵥵Ĳ���<br>
	 * �з���λ��
	 */
	private static void hello(){
		int i = 3;
		//��ͷ�������λ�Ʒ������Ƽ�λ���ǳ���2�ļ��η�
		int iResult = i<<2;
		System.out.println(iResult);
		//���Ƽ�λ�൱�ڳ���2�ļ��η�
		int j = 8;
		int iRightResult = j>>2;
		System.out.println(iRightResult);
	}
}
