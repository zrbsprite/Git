package com.cmcc.slience.senior.base;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Java��BigDecimal��8������ģʽ�������� java.math.BigDecimal
 * ���ɱ�ġ����⾫�ȵ��з���ʮ��������BigDecimal �����⾫�ȵ������Ǳ��ֵ��32λ���������(scale)��ɡ�
 * ���Ϊ�������,������С������λ�������Ϊ����,�򽫸����ķǱ��ֵ����10�ĸ�scale���ݡ�
 * ���,BigDecimal��ʾ����ֵ��(unscaledValue �� 10-scale)��
 * ��֮��صĻ���������:
 * java.math.MathContext:
 * �ö����Ƿ�װ���������õĲ��ɱ����,�����������������ĳЩ����,�����ݵľ���,���뷽ʽ�ȡ�
 * java.math.RoundingMode:
 * ����һ��ö������,�����˺ܶೣ�õ��������뷽ʽ��
 * ��������������ǺܱȽϸ��ӵ�,ԭ����������ģʽ,�����������̫��̫��,
 * ������ѧרҵ������˿�������API���������,��Щ������ʵ����ʹ�õ�ʱ���ڷ��Ķ����ü���
 * �����С��ʻ����Ʒѵ�����,BigDecimal�ṩ�˾�ȷ����ֵ���㡣����8�����뷽ʽֵ�����ա�
 * 1��ROUND_UP
 * ����Զ���������ģʽ��
 * �ڶ������㲿��֮ǰʼ����������(ʼ�նԷ�����������ǰ������ּ�1)��
 * ע��,������ģʽʼ�ղ�����ټ���ֵ�Ĵ�С��
 * 2��ROUND_DOWN
 * �ӽ��������ģʽ��
 * �ڶ���ĳ����֮ǰʼ�ղ���������(�Ӳ�����������ǰ������ּ�1,���ض�)��
 * ע��,������ģʽʼ�ղ������Ӽ���ֵ�Ĵ�С��
 * 3��ROUND_CEILING
 * �ӽ�������������ģʽ��
 * ��� BigDecimal Ϊ��,��������Ϊ�� ROUND_UP ��ͬ;
 * ���Ϊ��,��������Ϊ�� ROUND_DOWN ��ͬ��
 * ע��,������ģʽʼ�ղ�����ټ���ֵ��
 * 4��ROUND_FLOOR
 * �ӽ�������������ģʽ��
 * ��� BigDecimal Ϊ��,��������Ϊ�� ROUND_DOWN ��ͬ;
 * ���Ϊ��,��������Ϊ�� ROUND_UP ��ͬ��
 * ע��,������ģʽʼ�ղ������Ӽ���ֵ��
 * 5��ROUND_HALF_UP
 * ����ӽ��ġ���������,����������������ֵľ������,��Ϊ�������������ģʽ��
 * ����������� >= 0.5,��������Ϊ�� ROUND_UP ��ͬ;����������Ϊ�� ROUND_DOWN ��ͬ��
 * ע��,�������Ǵ��������Сѧʱ��ѧ��������ģʽ(��������)��
 * 6��ROUND_HALF_DOWN
 * ����ӽ��ġ���������,����������������ֵľ������,��Ϊ�����������ģʽ��
 * ����������� > 0.5,��������Ϊ�� ROUND_UP ��ͬ;����������Ϊ�� ROUND_DOWN ��ͬ(��������)��
 * 7��ROUND_HALF_EVEN
 * ����ӽ��ġ���������,����������������ֵľ������,�������ڵ�ż�����롣
 * �������������ߵ�����Ϊ����,��������Ϊ�� ROUND_HALF_UP ��ͬ;
 * ���Ϊż��,��������Ϊ�� ROUND_HALF_DOWN ��ͬ��
 * ע��,���ظ�����һϵ�м���ʱ,������ģʽ���Խ��ۼӴ��������С��
 * ������ģʽҲ��Ϊ�����м����뷨��,��Ҫ������ʹ�á���������,������������
 * ���ǰһλΪ����,����λ,������ȥ��
 * ��������Ϊ����С����1λ,��ô�������뷽ʽ�µĽ����
 * 1.15>1.2 1.25>1.2
 * 8��ROUND_UNNECESSARY
 * ��������Ĳ������о�ȷ�Ľ��,��˲���Ҫ���롣
 * ����Ի�þ�ȷ����Ĳ���ָ��������ģʽ,���׳�ArithmeticException��
 */
public class BigdecimalOpration {

	public static void main(String[] args) {
		double numA = 522452.1548;
		BigDecimal bigNumA = new BigDecimal(numA);
		System.out.println(bigNumA.toString());
		System.out.println(bigNumA.setScale(1,RoundingMode.HALF_UP).toString());
		System.out.println(bigNumA.setScale(1,RoundingMode.HALF_DOWN).toString());
		System.out.println(bigNumA.setScale(2,RoundingMode.HALF_UP).toString());
		System.out.println(bigNumA.setScale(2,RoundingMode.HALF_DOWN).toString());
		System.out.println(bigNumA.setScale(3,RoundingMode.HALF_UP).toString());
		System.out.println(bigNumA.setScale(3,RoundingMode.HALF_DOWN).toString());
		
		BigDecimal bigNumB = new BigDecimal(1.5);
		System.out.println(bigNumB.setScale(0,RoundingMode.HALF_UP).toString());
		System.out.println(bigNumB.setScale(0,RoundingMode.HALF_DOWN).toString());
		
		System.out.println(bigNumA.setScale(1, BigDecimal.ROUND_HALF_UP).toString());
		System.out.println(bigNumA.setScale(1,BigDecimal.ROUND_HALF_DOWN).toString());
		
	}
}
