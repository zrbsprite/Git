package com.jsprite.core.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class HttpUtilsTest {

	@Test
	public void testGetRequest() {
		String out = HttpUtils.getRequest("http://www.ab95569.com", null);
		assertNotNull(out);
	}

	@Test
	public void testPostRequest() {
		String out = HttpUtils.postRequest("http://www.ab95569.com", null);
		assertNotNull(out);
	}

	@Test
	public void testPostRequestUpper() {
		String out = HttpUtils.postRequestUpper("http://www.ab95569.com", null);
		assertNotNull(out);
	}

}
