package org.suNing.utli;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BaseDao {
	//驱动路径
	private static final String driverUrl = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	//  jdbc 连接语句
	private final String url = "jdbc:sqlserver://localhost:1433;databaseName=SuNingUpCityy;trustServerCertificate=true;";
	// 数据库登陆名
	private final String userName ="sa";
	//登陆密码
	private final String userPwd = "1551097460..xjj";
	
	
	static{
		try {
			Class.forName(driverUrl);  // 获取驱动
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取连接
	 * @return
	 */
	public Connection getConnection(){
		
		try {
			Connection conn = DriverManager.getConnection(url, userName, userPwd);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
				
	}
	
	/**
	 * 执行增删改方法
	 * @param sql   
	 * @param params
	 * @return
	 */
	public int executeUpdate(String sql,Object...params){
		Connection conn = getConnection();
		PreparedStatement sta = null;
		int result = 0;
		try {
			sta = conn.prepareStatement(sql);
			if(params != null && params.length>0){
				for (int i = 0; i < params.length; i++) {
					sta.setObject(i+1, params[i]);
				}
			}
			result = sta.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeConnection(null, sta, conn);
		}
		return result;
	}
	
	/**
	 * 执行查询操作
	 * @param sql
	 * @param params
	 * @return
	 */
	public ResultSet executeQuery(String sql,Object...params){
		Connection conn = getConnection();
		PreparedStatement sta = null;
		ResultSet res = null;
		try {
			sta = conn.prepareStatement(sql);
			if(params != null && params.length>0){
				for (int i = 0; i < params.length; i++) {
					sta.setObject(i+1, params[i]);
				}
			}
			res = sta.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public void closeConnection(ResultSet res,Statement sta,Connection conn){
		try {
			if(res != null)
				res.close();
			if(sta != null)
				sta.close();
			if(conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
