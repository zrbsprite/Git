package com.jsprite.core.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateTimeUtils {

	public static long getCurrentTimeMillis(){
		return System.currentTimeMillis();
	}
	
	public static Date getDateNow(){
		return new Date();
	}
	
	public static String showAuthorBirthday(){
		Calendar calendar = initCalendar(1988, 10, 25, 0, 0, 0, 0);
		return DateFormatUtils.getFormatedString(calendar.getTime(), DateFormatUtils.PATTERN_YEAR_DAY);
	}
	
	public static Calendar initCalendar(){
		return new GregorianCalendar();
	}
	
	public static Calendar initCalendar(int year, int month, int day, int hour, int minute, int second, int millisecond){
		Calendar calendar = initCalendar();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, millisecond);
		return calendar;
	}
	
//	public static 
	
}
