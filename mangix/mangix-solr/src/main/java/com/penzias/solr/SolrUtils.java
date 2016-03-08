/**
 * File Name:SolrUtils.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年3月2日上午10:23:51
 */
package com.penzias.solr;

import org.apache.solr.client.solrj.impl.HttpSolrClient;


/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2016年3月2日上午10:23:51 <br>
 * E-mail:  <br> 
 */
public class SolrUtils {

	public static final String SOLR_URL="http://admin:zhangribo@solr.penzias.com:8081";
	
	private final HttpSolrClient server = new HttpSolrClient(SOLR_URL);
	
	public SolrUtils(){
		server.setAllowCompression(true);
	}
}
