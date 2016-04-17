package com.cmcc.slience.senior.exception;

public class ExceptionAndReturn {

	public static int testOrder(){
		int i = 10;
		try {
			i++;
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			return i;
		} finally{
			i++;
		}
	}
	
	public static void main(String[] args) {
		System.err.print(testOrder());
	}
}
