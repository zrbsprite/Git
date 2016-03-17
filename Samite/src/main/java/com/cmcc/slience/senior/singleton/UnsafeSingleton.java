package com.cmcc.slience.senior.singleton;

public class UnsafeSingleton {

	private UnsafeSingleton(){
		
	}
	
	private static UnsafeSingleton Singleton = new UnsafeSingleton();
	
	public static UnsafeSingleton getInstance(){
		return Singleton;
	}
	
}
