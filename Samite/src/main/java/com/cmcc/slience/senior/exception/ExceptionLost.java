package com.cmcc.slience.senior.exception;

public class ExceptionLost {

	public static void main(String[] args) {
		try {
			int i = 0;
			double d = i/12;
			System.out.println(d);
		} catch (Exception e) {
			SlienceException ex = new SlienceException();
			//·ÀÖ¹Òì³£¶ªÊ§
			ex.initCause(e);
		}
		
	}
}
