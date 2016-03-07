package com.penzias.datatransfer;

import org.framework.util.DESEncryptUtil;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	private static final String ENCRYPT_KEY = "U2FsdGVkX19Fcum6/spC9RBqB41+jVWmLEFU4CyZoWn077DYvFKrUHKFodO4Q54hX9zSxevF";

	@Test
	public void testEncryptOfFillBlank() {
		String backStr = "PkthbZ8mwKE=";
		String foreStr = DESEncryptUtil.decrypt(backStr, ENCRYPT_KEY);
		System.out.println(foreStr);
		// backStr = "f0JYp+Md8NmiaPiXP5/ckfuMWrwsEHd3";
		backStr = "7Bl8bezbF0k=";
		foreStr = DESEncryptUtil.decrypt(backStr, ENCRYPT_KEY);
		System.out.println(foreStr);
		backStr = "f0JYp+Md8NmiaPiXP5/ckfuMWrwsEHd3";
		foreStr = DESEncryptUtil.decrypt(backStr, ENCRYPT_KEY);
		System.out.println(foreStr);
		backStr = "HWD6bIWvybKv4aoKEOEQwMsWW/1Jp+AP";
		foreStr = DESEncryptUtil.decrypt(backStr, ENCRYPT_KEY);
		System.out.println(foreStr);
		String srcStr = "111@@222@@333@@444";
		String tarStr = DESEncryptUtil.encrypt(srcStr, ENCRYPT_KEY);
		System.out.println(tarStr);//HWD6bIWvybKv4aoKEOEQwICoieK0C5uN
		String str1 = "VjqN5C6zkssxPPaiKu3453giPxe7t39XG7iDTx+BJ2Q=";
		String outStr1 = DESEncryptUtil.decrypt(str1, ENCRYPT_KEY);
		System.out.println(outStr1);
	}
}
