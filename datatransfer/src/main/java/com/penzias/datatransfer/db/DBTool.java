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
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * 描述：<br>
 * 作者：ZRB <br>
 * 修改日期：2016年1月25日上午11:40:39 <br>
 * E-mail: <br>
 */
public class DBTool {

	private static BoneCPDataSource ds;

	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	static {
		ResourceBundle rb = ResourceBundle.getBundle("configuration.jdbc");
		String driverName = rb.getString("database.driverClassName");
		String dbUrl = rb.getString("database.url");
		String username = rb.getString("database.username");
		String password = rb.getString("database.password");
		ds = new BoneCPDataSource();
		ds.setDriverClass(driverName);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setJdbcUrl(dbUrl);
	}

	public static Connection getConnection() {
		Connection conn = threadLocal.get();
		if (null == conn) {
			try {
				conn = ds.getConnection();
				threadLocal.set(conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return conn;
	}

	public static DataSource getDataSource(){
		return ds;
	}
}
