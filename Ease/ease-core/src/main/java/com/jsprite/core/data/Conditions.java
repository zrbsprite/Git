package com.jsprite.core.data;

public enum Conditions {
	
	ORDER, SEARCH;
	
	public enum Order{
		desc, asc
	}
	
	public enum Search{
		eq, lt, gt, like, between
	}
}