package com.jsprite.core.utils;

import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;

public class DateFormatUtils {

	public static final String PATTERN_YEAR_MILLISECOND = "yyyy-MM-dd HH:mm:ss.S";
	
	public static final String PATTERN_YEAR_SECOND = "yyyy-MM-dd HH:mm:ss";
	
	public static final String PATTERN_YEAR_DAY = "yyyy-MM-dd";
	
	public static final String PATTERN_TIME = "HH:mm:ss";
	
	public static String now2FormatedString(String pattern){
		return getFormatedString(new Date(), pattern);
	}
	
	public static String now2FormatedString(){
		return now2FormatedString(PATTERN_YEAR_SECOND);
	}
	
	public static String getFormatedString(long date, String pattern){
		return getFormatedString(new Date(date), pattern);
	}
	
	public static String getFormatedString(Date date, String pattern){
		SimpleDateFormat formater = getBlankFormater();
		formater.applyPattern(pattern);
		return formater.format(date);
	}
	
	public static SimpleDateFormat getPatternFormater(String pattern){
		return new SimpleDateFormat(pattern);
	}
	
	private static SimpleDateFormat getBlankFormater(){
		return new SimpleDateFormat();
	}
}
