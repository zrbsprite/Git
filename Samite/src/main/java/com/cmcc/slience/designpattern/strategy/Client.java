/**
 * File Name:Client.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年3月18日上午11:19:57
 */
package com.cmcc.slience.designpattern.strategy;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月18日上午11:19:57 <br>
 * E-mail:  <br> 
 */
public class Client {

	@Test
	public void unCompress() throws Exception {
		ZIPCompress zipCategary = new ZIPCompress();
		Context context = new Context(zipCategary);
		File zip = new File("C:\\Users\\qq\\Desktop\\Context.zip");
		File file = new File("C:\\Users\\qq\\Desktop");
		context.doUnCompress(zip, file);
	}
	
	@Test
	public void compress() throws Exception {
		ZIPCompress zipCategary = new ZIPCompress();
		Context context = new Context(zipCategary);
		File zip = new File("C:\\Users\\qq\\Desktop\\ContextA.zip");
		File file = new File("C:\\Users\\qq\\Desktop\\Context.java");
		context.doCompress(file, zip);
	}
	
	@Test
	public void gzipUnCompress() throws Exception {
		GZIPCompress gzipCategary = new GZIPCompress();
		Context context = new Context(gzipCategary);
		File zip = new File("C:\\Users\\qq\\Desktop\\Context.tar.gz");
		File file = new File("C:\\Users\\qq\\Desktop");
		context.doUnCompress(zip, file);
	}
}
