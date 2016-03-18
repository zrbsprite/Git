/**
 * File Name:Context.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年3月18日上午10:38:08
 */
package com.cmcc.slience.designpattern.strategy;

import java.io.File;

/**描述：策略上下文<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月18日上午10:38:08 <br>
 * E-mail:  <br> 
 */
public class Context {

	private ICompress compress;
	
	public Context(ICompress compress){
		this.compress = compress;
	}
	
	public void doCompress(File file, File zip){
		this.compress.doCompress(file, zip);
	}
	
	public void doUnCompress(File zip, File file){
		this.compress.doUnCompress(zip, file);
	}
}
