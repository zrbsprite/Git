package com.jsprite.core.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jsprite.core.exception.EaseException;

public class ExcelUtils {

	public static final int EXCEL_07 = 7;
	
	public static final int EXCEL_03 = 3;
	
	/**
	 * 未知版本
	 */
	public static final int EXCEL_UNKNOWN = -1;
	
	public static int judgeExcelVersion(Workbook book){
		if(book instanceof HSSFWorkbook){
			return EXCEL_03;
		}else if(book instanceof XSSFWorkbook){
			return EXCEL_07;
		}
		return EXCEL_UNKNOWN;
	}
	
	//1103.42
	public static void export(Workbook book, String parent, String fileName) throws EaseException{
		int version = judgeExcelVersion(book);
		parent = parent.replaceAll("\\\\|\\/", "\\"+File.separator);
		if(!parent.endsWith(File.separator)){
			parent += File.separator;
		}
		OutputStream os = null;
		try {
			if(EXCEL_07 == version){
				os = new FileOutputStream(parent + fileName + ".xlsx");
			}else if(EXCEL_03 == version){
				os = new FileOutputStream(parent + fileName + ".xls");
			}else{
				LogUtils.warn("导出Excel出错：未知excel版本！", ExcelUtils.class);
				throw new EaseException("未知excel版本");
			}
			book.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			LogUtils.error("输出excel文件出错："+e.getMessage());
		}finally{
			os = null;
		}
	}
	
	/**
	 *  方法名称: export<br>
	 *  描述：输出excel到OutputStream
	 *  作者: ZRB 
	 *  修改日期：2015年4月16日下午1:49:28
	 *  @param book
	 *  @param os
	 */
	public static void export(Workbook book, OutputStream os){
		try {
			book.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			LogUtils.error("导出excel出错："+e.getMessage());
		}finally{
			os = null;
		}
	}
}
