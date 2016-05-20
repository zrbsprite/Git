package com.cmcc.slience.base.str;

import java.util.Formatter;

/**
 * 描述：<br>
 * 作者：ruibo <br>
 * 修改日期：2016年4月17日上午9:06:01 <br>
 * 说明：formatter接受的规则：%[argument_index$][flags][width][.precision]conversion
 * 							 %-10.10s[%d %f %b x%[16进制] c%(unicode) %h(十六进制hash)] \n
 */
public class Receipt {

	private double total = 0;
	
	private Formatter f = new Formatter(System.out);
	
	public void printTitle(int len){
		f.format("%-15s %5s %10s\n", "Item", "Qty", "Price");
		f.format("%-15s %5s %10s\n", "----", "---", "-----");
	}
	
	public void print(String name, int qty, double price, int len){
		f.format("%-15.15s %5d %10.2f\n", "name, qty, price");
		total += price;
	}
	
	public static void main(String[] args) {
		Formatter fm = new Formatter(System.out);
		fm.format("%-2.2s\n", "10");
		fm.format("%-2.2s\n", 10);
		fm.format("%-4.2s\n", "10.03");
		fm.format("%-4.2s\n", "abcds");
		fm.format("%-4.2s\n", 10);
		fm.format("%-5.2s\n", 10.03);
		//exception,[.precision]不接受整型
		//fm.format("%-4.2f\n", 10);
		fm.format("%-5.2f\n", 10.03);
		fm.close();
	}
}
