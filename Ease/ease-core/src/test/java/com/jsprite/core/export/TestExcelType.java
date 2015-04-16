package com.jsprite.core.export;

import static org.junit.Assert.assertEquals;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestExcelType {

	@Test
	public void test() {
		Workbook fbook = new HSSFWorkbook();
		Workbook sbook = new XSSFWorkbook();
		
		assertEquals("class org.apache.poi.hssf.usermodel.HSSFWorkbook", fbook.getClass().toString());
		assertEquals("class org.apache.poi.xssf.usermodel.XSSFWorkbook", sbook.getClass().toString());
		assertEquals(true, fbook instanceof HSSFWorkbook);
		assertEquals(true, fbook instanceof Workbook);
		assertEquals(false, fbook instanceof XSSFWorkbook);
		
	}

}
