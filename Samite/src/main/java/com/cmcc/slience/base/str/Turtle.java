package com.cmcc.slience.base.str;

import java.io.PrintStream;
import java.util.Formatter;

public class Turtle {

	private String name;
	
	private Formatter formatter;
	
	public Turtle(String name, Formatter formatter){
		this.name = name;
		this.formatter = formatter;
	}
	
	public void move(int x, int y){
		formatter.format("%s The Turtle is at (%d, %d)\n", name, x, y);
	}
	
	public static void main(String[] args) {
		PrintStream ps = System.out;//ps 是System.out的一个别名（可以这样理解）
		Turtle tommy = new Turtle("Tommy", new Formatter(System.out));
		Turtle terry = new Turtle("terry", new Formatter(ps));
		tommy.move(0, 0);
		terry.move(4, 8);
		tommy.move(3, 4);
		terry.move(3, 3);
	}
}
