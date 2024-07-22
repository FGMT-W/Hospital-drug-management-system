package com.offcn.Servlet;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;

import com.offcn.Entity.Users;
import com.offcn.Service.UserService;

@WebServlet("/User")
@MultipartConfig
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	private String Username_For_Update;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置HttpServletResponse使用UTF-8
		request.setCharacterEncoding("utf-8");
		// 通知浏览器使用UTF-8解码
		response.setHeader("Content-Type", "text/html;charset=utf-8");
		//测试方法名是否被调用		
		String method = request.getParameter("method");
		
		switch(method) {
		case "checkLogin":
			checkLogin(request,response); 
			break;
		case "checkeVercode":
			checkeVercode(request,response); 
			break;
		case "checkUserName":
			checkUserName(request,response);
			break;
		case "insertUser":
			insertUser(request,response);
			break;
		case "logout":
			logout(request,response);
			break;
		case "updateUser":
			updateUser(request,response);
			break;
		case "checkEmail":
			checkEmail(request,response);
			break;
		default:
			break;
		}
	}

	// 注册页面邮箱验证
	public void checkEmail(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String email = request.getParameter("email");
		boolean flag = userService.checkEmail(email);
		response.getWriter().print(flag);
	}

	//修改用户信息
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date modifytime = new Date();
		//封装实体类
		Users users = new Users(name,email,Username_For_Update,password,modifytime);//DateTool.stringToDate()将String对象转化为Date类型
		//调用service层的添加方法插入数据库
		boolean flag = userService.updateUser(users);
		if(flag) {
			response.sendRedirect("login.jsp");
		}else {
			response.sendRedirect("Update_user.jsp");
		}
	}

	//检查用户名信息
	private void checkUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		boolean flag = userService.checkUserName(username);
		response.getWriter().print(flag);
	}

	//注册用户
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date modifytime = new Date();
		
		//封装实体类
		Users user = new Users(name,email,username,password,modifytime);//DateTool.stringToDate()将String对象转化为Date类型
		//调用service层的添加方法插入数据库
		boolean flag = userService.insertUser(user);
		if(flag) {
			response.sendRedirect("login.jsp");
		}else {
			response.sendRedirect("regist.jsp");
		}
	}

	//判断用户登录
	private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取前端传入的用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//获取登录时的用户名用于修改该用户
		Username_For_Update = request.getParameter("username");
		HttpSession session = request.getSession();
		boolean flag = userService.checkLogin(username,password,session);
		if(flag) {
			response.sendRedirect("index.jsp");
		}else {
			request.setAttribute("msg", "账号或用户名错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}
	
	//检查验证码是否正确
	private void checkeVercode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//先从前端获取用户输入的验证码
		String vercode = request.getParameter("vercode");
		//获取生成的验证码
		HttpSession session = request.getSession();
		String verCode = (String)session.getAttribute("verCode");
			
		boolean flag;
		if(vercode.equalsIgnoreCase(verCode)) {
			flag = true;
		}else {
			flag = false;
		}
			response.getWriter().print(flag);
	}
	
	// 退出登录
	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("login.jsp");
	}

}
