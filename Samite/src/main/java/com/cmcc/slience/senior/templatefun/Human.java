package com.cmcc.slience.senior.templatefun;

public class Human extends Biology {

	@Override
	protected void birth() {
		System.out.println("Human birth...");
	}

	@Override
	protected void grow() {
		System.out.println("Human grow up...");
	}

	@Override
	protected void older() {
		System.out.println("Human change older...");
	}

	@Override
	protected void die() {
		System.out.println("Human is going to die...");
	}

}
