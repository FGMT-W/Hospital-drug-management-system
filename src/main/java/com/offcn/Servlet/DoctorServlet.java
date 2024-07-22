package com.offcn.Servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.offcn.Entity.Doctor;
import com.offcn.Service.DoctorService;
import com.offcn.utils.DateTool;
import com.offcn.utils.PageTool;

@WebServlet("/Doctor")
@MultipartConfig
public class DoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DoctorService doctorService = new DoctorService();
	
	public DoctorServlet() {
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
		case "findDoctorsByPage":
			findDoctorsByPage(request,response); 
			break;
		case "findDoctorByDid":
			findDoctorByDid(request,response); 
			break;
		case "toUpdate":
			toUpdate(request,response);
			break;
		case "updateDoctor":
			updateDoctor(request,response);
			break;
		case "insertDoctor":
			insertDoctor(request,response);
			break;
		case "deleteDoctor":
			deleteDoctor(request,response);
			break;
		case "findDoctorsByDepartment":
			findDoctorsByDepartment(request,response);
			break;
		default:
			break;
		}
	}
	

	// 分页查询医生信息
	public void findDoctorsByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String department = request.getParameter("department");
		Doctor doctor = new Doctor(name);
		if (department != null) {
			doctor.setDepartment(Integer.valueOf(department));
		}
		int totalCount = doctorService.getTotalCount(doctor);
		String currentPage = request.getParameter("currentPage");
		PageTool pageTool = new PageTool(currentPage,totalCount);
		List<Doctor> doctorList = doctorService.findDoctorsByPage(doctor, pageTool);
		System.out.println(doctorList.size());
		request.setAttribute("doctor", doctor);
		request.setAttribute("pageTool", pageTool);
		request.setAttribute("doctorList", doctorList);
		request.getRequestDispatcher("/doctor/index.jsp").forward(request, response);
	}

	// 通过id查询医生信息
	public void findDoctorByDid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String did = request.getParameter("did");
		Doctor doctor = doctorService.findDoctorByDid(Integer.valueOf(did));
		request.setAttribute("doctor", doctor);
		request.getRequestDispatcher("/doctor/look.jsp").forward(request, response);
	}

	// 回显
	public void toUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String did = request.getParameter("did");
		Doctor doctor = doctorService.findDoctorByDid(Integer.valueOf(did));
		request.setAttribute("doctor", doctor);
		request.getRequestDispatcher("/doctor/edit.jsp").forward(request, response);
	}

	// 修改医生信息
	public void updateDoctor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String did = request.getParameter("did");
		String name = request.getParameter("name");
		String cardno = request.getParameter("cardno");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		String department = request.getParameter("department");
		String education = request.getParameter("education");
		String remark = request.getParameter("remark");
		// System.out.println(did+"\t"+sex+"\t"+age+"\t"+department+"\t"+education);
		Doctor doctor = new Doctor(Integer.valueOf(did), name, cardno, phone, Integer.valueOf(sex),
				Integer.valueOf(age), DateTool.stringToDate(birthday), email, Integer.valueOf(department),
				Integer.valueOf(education), remark);
		boolean flag = doctorService.updateDoctor(doctor);
		System.out.println(flag);
		if (flag) {
			request.getRequestDispatcher("Doctor?method=findDoctorsByPage").forward(request, response);
		}
	}

	// 添加医生
	public void insertDoctor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String cardno = request.getParameter("cardno");
		String phone = request.getParameter("phone");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		String department = request.getParameter("department");
		String education = request.getParameter("education");
		String remark = request.getParameter("remark");
		// System.out.println(did+"\t"+sex+"\t"+age+"\t"+department+"\t"+education);
		Doctor doctor = new Doctor(name, cardno, phone, Integer.valueOf(sex), Integer.valueOf(age),
				DateTool.stringToDate(birthday), email, Integer.valueOf(department), Integer.valueOf(education),
				remark);
		boolean flag = doctorService.insertDoctor(doctor);
		if (flag) {
			request.getRequestDispatcher("Doctor?method=findDoctorsByPage").forward(request, response);
		}
	}

	// 删除医生
	public void deleteDoctor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		System.out.println(ids);
		boolean flag = doctorService.deleteDoctor(ids);
		if (flag) {
			request.getRequestDispatcher("Doctor?method=findDoctorsByPage").forward(request, response);
		}
	}

	// 根据科室查询医生信息
	public void findDoctorsByDepartment(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取科室信息
		String department = request.getParameter("department");
		List<Doctor> doctors = doctorService.findDoctorsByDepartment(Integer.valueOf(department));
		ObjectMapper mapper=new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(doctors);
		System.out.println("jsonStr"+jsonStr);
		response.getWriter().print(jsonStr);
	}
}
