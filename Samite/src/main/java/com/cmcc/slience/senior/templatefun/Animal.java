package com.cmcc.slience.senior.templatefun;

public class Animal extends Biology {

	@Override
	protected void birth() {
		System.out.println("Animal birth...");
	}

	@Override
	protected void grow() {
		System.out.println("Animal grow up...");
	}

	@Override
	protected void older() {
		System.out.println("Animal change older...");
	}

	@Override
	protected void die() {
		System.out.println("Animal is going to die...");
	}

}
