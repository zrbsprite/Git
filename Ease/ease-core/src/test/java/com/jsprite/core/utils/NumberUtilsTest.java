package com.jsprite.core.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class NumberUtilsTest {

	@Test
	public void testDefaultNumberFormat() {
		double dNum = 123.568;
		String result = NumberUtils.defaultNumberFormat(dNum);
		assertEquals("123.56", result);
		double dNum1 = 123.563;
		String result1 = NumberUtils.defaultNumberFormat(dNum1);
		assertEquals("123.56", result1);
	}

	@Test
	public void testCharSequenceNumberFormat(){
		String sNum = "12.589";
		assertEquals("12.58", NumberUtils.charSequenceNumberFormat(sNum));
	}
	
	@Test
	public void testIsValidNumber(){
		String oNum = "235.129";
		assertEquals(true, NumberUtils.isValidNumber(oNum));
	}
}
