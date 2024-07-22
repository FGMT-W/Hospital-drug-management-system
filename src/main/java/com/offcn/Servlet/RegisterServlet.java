package com.offcn.Servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.offcn.Entity.Doctor;
import com.offcn.Entity.Register;
import com.offcn.Service.DoctorService;
import com.offcn.Service.RegisterService;
import com.offcn.utils.PageTool;

@WebServlet("/Register")
@MultipartConfig
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegisterService registerService = new RegisterService();
	private DoctorService doctorService = new DoctorService();

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
		case "findRegisterByPage":
			findRegisterByPage(request,response); 
			break;
		case "insertRegister":
			insertRegister(request,response); 
			break;
		case "findRegisterByRid":
			findRegisterByRid(request,response);
			break;
		case "updateRegister":
			updateRegister(request,response);
			break;
		case "toUpdate":
			toUpdate(request,response);
			break;
		case "deleteRegister":
			deleteRegister(request,response);
			break;
		default:
			break;
		}
	}
	
	// 分页查询挂号病人
	public void findRegisterByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rid = request.getParameter("rid");
		String name = request.getParameter("name");
		//用doctor对象传递name
		Doctor doctor = new Doctor(name);
		String department = request.getParameter("department");
		
		Register register = new Register(rid, doctor);
		if (department != null) {
			register.setDepartment(Integer.valueOf(department));
		}
		int totalCount = registerService.getTotalCount(register);
		String currentPage = request.getParameter("currentPage");
		PageTool pageTool = new PageTool(currentPage,totalCount);
		List<Register> registerList = registerService.findRegistersByPage(register, pageTool);
		
		request.setAttribute("register", register);
		request.setAttribute("pageTool", pageTool);
		request.setAttribute("registerList", registerList);
		request.getRequestDispatcher("/register/index.jsp").forward(request, response);
	}

	// 添加挂号病人
	public void insertRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 从前端jsp页面获取数据
		String rid = request.getParameter("rid");
		String name = request.getParameter("name");
		String idCard = request.getParameter("idCard");
		String siNumber = request.getParameter("siNumber");
		String registerMoney = request.getParameter("registerMoney");
		String phone = request.getParameter("phone");
		String isPay = request.getParameter("isPay");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String consultation = request.getParameter("consultation");
		String department = request.getParameter("department");
		String did = request.getParameter("did");
		String remark = request.getParameter("remark");
		
		// 实例化实体类
		Register register = new Register(rid, name, idCard, siNumber, Double.valueOf(registerMoney), phone,
				Integer.valueOf(isPay), Integer.valueOf(sex), Integer.valueOf(age), Integer.valueOf(consultation),
				Integer.valueOf(department), Integer.valueOf(did), 1, new Date(), remark);
		boolean flag = registerService.insertRegister(register);
		if (flag) {
			request.getRequestDispatcher("Register?method=findRegisterByPage").forward(request, response);
		}
	}

	// 根据id查询挂号病人
	public void findRegisterByRid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rid = request.getParameter("rid");
		Register register = registerService.findRegisterByRid(rid);
		request.setAttribute("register", register);
		request.getRequestDispatcher("register/look.jsp").forward(request, response);
	}

	// 回显
	public void toUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String rid = request.getParameter("rid");
		String department = request.getParameter("department");
		List<Doctor> doctorList = doctorService.findDoctorsByDepartment(Integer.valueOf(department));
		Register register = registerService.findRegisterByRid(rid);
		request.setAttribute("register", register);
		request.setAttribute("doctorList", doctorList);
		request.getRequestDispatcher("register/edit.jsp").forward(request, response);
	}

	// 修改挂号病人
	public void updateRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//从前端页面获取用户信息
		String rid = request.getParameter("rid");
		String name = request.getParameter("name");
		String idCard = request.getParameter("idCard");
		String siNumber = request.getParameter("siNumber");
		String registerMoney = request.getParameter("registerMoney");
		String phone = request.getParameter("phone");
		String isPay = request.getParameter("isPay");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String consultation = request.getParameter("consultation");
		String department = request.getParameter("department");
		String did = request.getParameter("did");
		String remark = request.getParameter("remark");

		// 实例化实体类
		Register register = new Register(rid, name, idCard, siNumber, Double.valueOf(registerMoney), phone,
				Integer.valueOf(isPay), Integer.valueOf(sex), Integer.valueOf(age), Integer.valueOf(consultation),
				Integer.valueOf(department), Integer.valueOf(did), new Date(), remark);
		boolean flag = registerService.updateRegister(register);
		if (flag) {
			request.getRequestDispatcher("Register?method=findRegisterByPage").forward(request, response);
		}
	}
	// 删除挂号病人
	public void deleteRegister(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String ids = request.getParameter("ids");
		boolean flag=registerService.deleteRegister(ids);
		if (flag) {
			request.getRequestDispatcher("Register?method=findRegisterByPage").forward(request, response);
		}
	}
}
