package com.jsprite.core.wraper;

import org.junit.Assert;
import org.junit.Test;

import com.jsprite.core.utils.LogUtils;

public class HTMLFilterTest {

	@Test
	public void testDoFilter() {
		String msg = "<html><div>这是html文本块儿</div></html>";
		Filter dector = new FilterDecorator();
		Filter filter = new HTMLFilter(new IllegalWordFilter(dector));
		String result = filter.doFilter(msg);
		LogUtils.info(result);
		Assert.assertNotSame("这是html文本块儿", result);
	}

}
