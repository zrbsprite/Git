package com.cmcc.slience.test.j2se;

import com.cmcc.slience.test.entity.Paper;
import com.cmcc.slience.test.entity.Something;

public class TestExtends {

	public static void main(String[] args) {
		Something som = new Something(){};
		som.soar();
		som = new Paper();
		som.soar();
	}
}
