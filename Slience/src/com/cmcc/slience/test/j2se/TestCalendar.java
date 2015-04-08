package com.cmcc.slience.test.j2se;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestCalendar {

	private static Calendar calendar = new GregorianCalendar();
	
	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * ��ȡ���Ƶ�ʱ���ʽ��ĸ�ʽ������
	 * @param formatString
	 * @return
	 */
	public static SimpleDateFormat setFormater(String formatString){
		if(null==formatString||"".equals(formatString)){
			formatString = DEFAULT_FORMAT;
		}
		return new SimpleDateFormat(formatString);
	}
	
	public static String formatDateByFormatString(String formatString,Date date){
		return setFormater(formatString).format(date);
	}
	
	/**
	 * ��������
	 */
	public static void resetCalendar(){
		calendar = new GregorianCalendar();
	}
	
	/**
	 * ��ȡ�ö����·ݵ�ʵ������
	 * @param year:��ǰ�꣬eg.2014
	 * @param month:�·�
	 * @return
	 */
	public static int daysOfMonth(int year,int month){
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.YEAR, year-1970+1);
		calendar.set(Calendar.MONTH, month-1);
		//��ȡָ���µĿ��ܵ��������ֵ
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * ��ȡ�������������ܵĵ�һ�죬����һ
	 * @param now
	 * @return
	 */
	public static Date dateOfMondayInWeek(Date now){
		calendar.setTime(now);;
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return calendar.getTime();
	}
	
	/**
	 * ��ȡ�������������ܵĵ����죬������
	 * @param now
	 * @return
	 */
	public static Date dateOfSundayInWeek(Date now){
		calendar.setTime(now);
		//�����ܵĵ�һ��
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 23);//�ȼ��� calendar.set(Calendar.HOUR, 10);calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek()+6);
		return calendar.getTime();
	}
	
	/**
	 * Ψһ��ָ��ĳ���е�ĳһ��
	 * @param week
	 * @param day
	 * @return
	 */
	public static Date goIntoSomeDay(int month,int week,int day){
		resetCalendar();
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, week);
		calendar.set(Calendar.DAY_OF_WEEK, day);
		return calendar.getTime();
	}
	
	/**
	 * ĳ����һ���еĵڼ���
	 * @param date
	 * @return
	 */
	public static int dayOfYear(Date date){
		resetCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_YEAR);
	}
	
	public static void main(String[] args) {
		int days = daysOfMonth(2014,2);
		System.out.println("������"+days+"��");
		
		Date now = new Date();
		System.out.println(setFormater(DEFAULT_FORMAT).format(dateOfMondayInWeek(now)));
		System.out.println(setFormater(DEFAULT_FORMAT).format(dateOfSundayInWeek(now)));
		
		System.out.println(dayOfYear(now));
	}
}