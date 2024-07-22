package com.offcn.Servlet;

import java.io.File;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.offcn.Entity.Medicine;
import com.offcn.Service.MedicineService;
import com.offcn.utils.PageTool;

@WebServlet("/Medicine")
@MultipartConfig
public class MedicineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MedicineService medicineService = new MedicineService();

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
		case "findMedicineByPage":
			findMedicineByPage(request,response); 
			break;
		case "insertMedicine":
			insertMedicine(request,response); 
			break;
		case "deleteMedicine":
			deleteMedicine(request,response);
			break;
		case "findMedicineByMid":
			findMedicineByMid(request,response);
			break;
		case "toUpdate":
			toUpdate(request,response);
			break;
		case "updateMedicine":
			updateMedicine(request,response);
			break;
		default:
			break;
		}
	}
	
	// 分页查询药品信息
	public void findMedicineByPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取药品名称、类型
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		
		Medicine medicine = new Medicine(name);
		if (type != null) {
			medicine.setType(Integer.valueOf(type));
		}
		int totalCount = medicineService.getTotalCount(medicine);
		String currentPage = request.getParameter("currentPage");
		PageTool pageTool = new PageTool(currentPage,totalCount);
		List<Medicine> medicineList = medicineService.findMedicineByPage(medicine, pageTool);
		request.setAttribute("medicine", medicine);
		request.setAttribute("pageTool", pageTool);
		request.setAttribute("medicineList", medicineList);
		request.getRequestDispatcher("/medicine/index.jsp").forward(request, response);
	}

	// 添加药品
	public void insertMedicine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mid = request.getParameter("mid");
		String picture = "";
		String inPrice = request.getParameter("inPrice");
		String salPrice = request.getParameter("salPrice");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String descs = request.getParameter("descs");
		String qualityDate = request.getParameter("qualityDate");
		String description = request.getParameter("description");
		String produceFirm = request.getParameter("produceFirm");
		String readme = request.getParameter("readme");
		String remark = request.getParameter("remark");
		// 添加图片
		Part part = request.getPart("picture");
		String header = part.getHeader("Content-Disposition");
		picture = header.substring(header.lastIndexOf("filename") + 10, header.length() - 1);
		picture = UUID.randomUUID() + picture;
		
		String path = "E:/JavaProject/hospital/resources";
		
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		part.write(path + "/" + picture);
		Medicine medicine = new Medicine(mid, picture, Double.valueOf(inPrice), Double.valueOf(salPrice), name,
				Integer.valueOf(type), descs, Integer.valueOf(qualityDate), description, produceFirm, readme, remark);
		boolean flag = medicineService.insertMedicine(medicine);
		if (flag) {
			request.getRequestDispatcher("Medicine?method=findMedicineByPage").forward(request, response);
		}
	}

	// 删除药品
	public void deleteMedicine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String ids = request.getParameter("ids");
		boolean flag = medicineService.deleteMedicine(ids);
		if (flag) {
			request.getRequestDispatcher("Medicine?method=findMedicineByPage").forward(request, response);
		}
	}

	// 通过id查询药品
	public void findMedicineByMid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mid = request.getParameter("mid");
		Medicine medicine = medicineService.findMedicineByMid(mid);
		request.setAttribute("medicine", medicine);
		request.getRequestDispatcher("/medicine/look.jsp").forward(request, response);
	}

	// 回显
	public void toUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mid = request.getParameter("mid");
		Medicine medicine = medicineService.findMedicineByMid(mid);
		request.setAttribute("medicine", medicine);
		request.getRequestDispatcher("/medicine/edit.jsp").forward(request, response);
	}

	// 修改药品信息
	public void updateMedicine(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mid = request.getParameter("mid");
		String picture = "";// ͼƬ
		String inPrice = request.getParameter("inPrice");
		String salPrice = request.getParameter("salPrice");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String descs = request.getParameter("descs");
		String qualityDate = request.getParameter("qualityDate");
		String description = request.getParameter("description");
		String produceFirm = request.getParameter("produceFirm");
		String readme = request.getParameter("readme");
		String remark = request.getParameter("remark");
		// 上传图片
		Part part = request.getPart("picture");
		if (part.getSize()==0) {
			picture=request.getParameter("oldpicture");
		}else {
			String header = part.getHeader("Content-Disposition");
			picture = header.substring(header.lastIndexOf("filename") + 10, header.length() - 1);
			picture = UUID.randomUUID() + picture;
			String path = "E:/JavaProject/hospital/resources";
			File file = new File(path);
			if (!file.exists()) {
				file.mkdir();
			}
			part.write(path + "/" + picture);
		}
		Medicine medicine = new Medicine(mid, picture, Double.valueOf(inPrice), Double.valueOf(salPrice), name,
				Integer.valueOf(type), descs, Integer.valueOf(qualityDate), description, produceFirm, readme, remark);
		boolean flag = medicineService.updateMedicine(medicine);
		if (flag) {
			request.getRequestDispatcher("Medicine?method=findMedicineByPage").forward(request, response);
		}
	}

}
