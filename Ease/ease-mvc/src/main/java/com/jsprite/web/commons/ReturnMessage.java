/**
 * File Name:ReturnMessage.java
 * @Description: 
 * Copyright 2012 China Life Insurance Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2015年5月4日上午9:37:19
 */
package com.jsprite.web.commons;

/**描述：返回请求状态信息<br>
 * 作者：ZRB <br>
 * 修改日期：2015年5月4日上午9:37:19 <br>
 * E-mail:  <br> 
 */
public class ReturnMessage {

	public static final int REQUEST_STATUS_SUCCESS = 200;
	
	public static final int REQUEST_STATUS_FAIL = 400;
	
	public ReturnMessage(){
		
	}
	
	public ReturnMessage(int status, String message){
		this.status = status;
		this.message = message;
	}
	
	private int status = 0;
	
	private String message;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
