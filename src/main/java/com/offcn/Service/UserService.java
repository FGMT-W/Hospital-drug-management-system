package com.offcn.Service;

import javax.servlet.http.HttpSession;


import com.offcn.Dao.UserDao;
import com.offcn.Entity.Users;

public class UserService {
	private UserDao userDao = new UserDao();
	
	//判断用户登录
	public boolean checkLogin(String username, String password, HttpSession session) {
		Users users = userDao.checkLogin(username,password);
		//当前登录用户信息保存到session中
		session.setAttribute("users", users);
		if(users != null) {
			return true;
		}
		return false;
	}

	//查询用户名信息
	public boolean checkUserName(String username) {
		return userDao.checkUserName(username) != null?true:false;
	}

	//注册用户
	public boolean insertUser(Users user) {
		return userDao.insertUser(user)>0?true:false;
	}
	
	//修改用户信息
	public boolean updateUser(Users users) {
		return userDao.updateUser(users)>0?true:false;
	}

	//查询邮箱是否已被注册
	public boolean checkEmail(String email) {
		return userDao.checkEmail(email) != null?true:false;
	}

}
