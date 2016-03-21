/**
 * File Name:ICompress.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年3月18日上午10:33:31
 */
package com.cmcc.slience.designpattern.strategy;

import java.io.File;

/**描述：抽象策略<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月18日上午10:33:31 <br>
 * E-mail:  <br> 
 */
public interface ICompress {

	void doCompress(File file, File target);
	
	void doUnCompress(File file, File target);
}
