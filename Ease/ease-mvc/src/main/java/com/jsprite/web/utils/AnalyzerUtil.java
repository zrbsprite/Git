/**
 * File Name:AnalyzerUtil.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年5月7日下午4:22:46
 */
package com.jsprite.web.utils;

import org.apache.lucene.analysis.Analyzer;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2015年5月7日下午4:22:46 <br>
 * E-mail: <br>
 */
public class AnalyzerUtil {

	private static Analyzer analyzer;

	public static Analyzer getIkAnalyzer() {
		if (analyzer == null) {
			// 当为true时，分词器迚行最大词长切分 ；当为false时，分词器迚行最细粒度切
			analyzer = new IKAnalyzer(true);
		}
		return analyzer;
	}
}
