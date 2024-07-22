package com.offcn.Dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.offcn.Entity.Users;

public class UserDao {
	//创建数据库连接对象
	QueryRunner qrRunner = new QueryRunner(new ComboPooledDataSource());
	
	//判断用户登录
	public Users checkLogin(String username, String password) {
		String sql = "SELECT * FROM users WHERE username=? AND password=?";
		try {
			return qrRunner.query(sql,new BeanHandler<Users>(Users.class),username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//查询用户名信息
	public Users checkUserName(String username) {
		String sql = "SELECT * FROM users WHERE username=?";
		try {
			return qrRunner.query(sql,new BeanHandler<Users>(Users.class),username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//注册用户
	public int insertUser(Users users) {
		String sql = "INSERT INTO users(name,email,status,username,password,modifytime)VALUES(?,?,?,?,?,?)";
		try {
			return qrRunner.update(sql,users.getName(),users.getEmail(),users.getStatus(),users.getUsername(),users.getPassword(),users.getModifytime());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	//修改用户
	public int updateUser(Users users) {
		String sql = "UPDATE users SET name=?,email=?,status=?,password=?,modifytime=? WHERE username=?";
		try {
			return qrRunner.update(sql,users.getName(),users.getEmail(),users.getStatus(),users.getPassword(),users.getModifytime(),users.getUsername());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	//查询邮箱是否已被注册
		public Users checkEmail(String email) {
			String sql = "SELECT * FROM users WHERE email=?";
			try {
				return qrRunner.query(sql, new BeanHandler<Users>(Users.class),email);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

}
