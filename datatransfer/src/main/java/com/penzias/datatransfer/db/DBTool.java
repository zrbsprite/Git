/**
 * File Name:DBTool.java
 * @Description: 
 * Copyright 2015 Ease Company Ltd.
 * @author:ZRB
 * @version:v1.0
 * Createdate:2016年1月25日上午11:40:39
 */
package com.penzias.datatransfer.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

/**描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2016年1月25日上午11:40:39 <br>
 * E-mail:  <br> 
 */
public class DBTool {

	public static Connection getConnection() {
		ResourceBundle rb = ResourceBundle.getBundle("configuration.jdbc");
		String driverName = rb.getString("database.driverClassName");
		String dbUrl = rb.getString("database.url");
		String username = rb.getString("database.username");
		String password = rb.getString("database.password");
		Connection conn = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(dbUrl, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static int count(String sql){
		Connection connection = getConnection();
		QueryRunner runner = new QueryRunner();
		try {
			Map<String, Object> map = runner.query(connection, sql, new MapHandler());
			map.get("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
