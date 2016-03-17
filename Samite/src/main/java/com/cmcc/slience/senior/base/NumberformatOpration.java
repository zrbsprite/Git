package com.cmcc.slience.senior.base;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.junit.Assert;
import org.junit.Test;

public class NumberformatOpration {

	private NumberFormat nf;
	
	@Test
	public void test(){
		String result = formatMoney("###,###.00", 1234567.251);
		Assert.assertEquals("1,234,567.25", result);
	}
	
	private String formatMoney(String pattern, double value){
		nf = new DecimalFormat(pattern);
		String result = nf.format(value);
		return result;
	}
	
	@Test
	public void formatMoneyCN(){
		nf = NumberFormat.getCurrencyInstance();
		String result = nf.format(123.65);
		Assert.assertEquals("гд123.65", result);
	}
	
	@Test
	public void formatPercent(){
		nf = NumberFormat.getPercentInstance();
		String result = nf.format(0.654);
		System.out.println(result);
		Assert.assertEquals("65.4%", result);
	}
	
	@Test
	public void dFormatPercent(){
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("###.00%");
		String result = df.format(0.654);
		System.out.println(result);
		Assert.assertEquals("65.40%", result);
	}
	
}
