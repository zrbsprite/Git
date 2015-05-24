package com.jsprite.web.commons;

import java.util.List;
import java.util.Map;

/**
 * 描述：查询条件封装<br>
 * 作者：ruibo <br>
 * 修改日期：2015年5月24日下午6:40:11 <br>
 * @see com.jsprite.core.data.Conditions
 */
public class QueryParameters {

	//查询条件
	private Map<String, String> searchConditions;
	
	//排序规则
	private Map<String, String> orderConditions;
	
	//查询字段
	private List<String> queryStrings;
	
	public QueryParameters(Map<String, String> searchConditions, Map<String, String> orderConditions){
		this.searchConditions = searchConditions;
		this.orderConditions = orderConditions;
	}

	public Map<String, String> getSearchConditions() {
		return searchConditions;
	}

	public void setSearchConditions(Map<String, String> searchConditions) {
		this.searchConditions = searchConditions;
	}

	public Map<String, String> getOrderConditions() {
		return orderConditions;
	}

	public void setOrderConditions(Map<String, String> orderConditions) {
		this.orderConditions = orderConditions;
	}

	public List<String> getQueryStrings() {
		return queryStrings;
	}

	public void setQueryStrings(List<String> queryStrings) {
		this.queryStrings = queryStrings;
	}
}

