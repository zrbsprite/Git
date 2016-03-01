package com.penzias.quartz;

import org.springframework.util.StringUtils;

public class CronVO {

	private String year;

	private String month;

	private String day;

	private String hour;

	private String minute = "0";

	private String second = "0";

	public String getYear(){

		return year;
	}

	public void setYear(String year){

		this.year = year;
	}

	public String getMonth(){

		return month;
	}

	public void setMonth(String month){

		this.month = month;
	}

	public String getDay(){

		return day;
	}

	public void setDay(String day){

		this.day = day;
	}

	public String getHour(){

		return hour;
	}

	public void setHour(String hour){

		this.hour = hour;
	}

	public String getMinute(){

		return minute;
	}

	public void setMinute(String minute){
		if(StringUtils.isEmpty(minute)){
			minute = "0";
		}
		this.minute = minute;
	}

	public String getSecond(){

		return second;
	}

	public void setSecond(String second){
		if(StringUtils.isEmpty(second)){
			second = "0";
		}
		this.second = second;
	}
}
