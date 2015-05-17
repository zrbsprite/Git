package com.jsprite.core;

/**
 * 描述：key vlaue 排序-倒序实现<br>
 * 作者：ruibo <br>
 * 修改日期：2015年5月17日下午6:38:08 <br>
 * E-mail:  <br>
 */
public class SortKeyValueEntity implements Comparable<SortKeyValueEntity>{

	
	private int order;
	
	private Object vlaue;
	
	public int compareTo(SortKeyValueEntity o) {
		Integer one = new Integer(this.order);
		Integer two = new Integer(o.getOrder());
		return one.compareTo(two) * -1;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Object getVlaue() {
		return vlaue;
	}

	public void setVlaue(Object vlaue) {
		this.vlaue = vlaue;
	}
}
