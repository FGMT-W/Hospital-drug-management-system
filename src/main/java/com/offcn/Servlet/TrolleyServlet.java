package com.offcn.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.offcn.Entity.Users;
import com.offcn.Service.MedicineService;
import com.offcn.Service.TrolleyService;
import com.offcn.utils.PageTool;
import com.offcn.Entity.Medicine;
import com.offcn.Entity.Trolley;

@WebServlet("/Trolley")
public class TrolleyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TrolleyService trolleyService = new TrolleyService();
    private MedicineService medicineService = new MedicineService();
    private String ids = "";//商品编号
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrolleyServlet() {
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
						
		String method = request.getParameter("method");
				
		switch(method) {
				case "addTrolley":
					addTrolley(request,response);
					break;
				case "findMedicineByPage":
					findMedicineByPage(request,response);
					break;
				case "findTrolleyList":
					findTrolleyList(request,response);
					break;
				case "updateNumber":
					updateNumber(request,response);
					break;
				case "deleteTrolley":
					deleteTrolley(request,response);
					break;
				case "pay":
					pay(request,response);
					break;
				case "PayFunction":
					PayFunction(request,response);
					break;
				case "PayFail":
					PayFail(request,response);
					break;
				default:
					break;
				}
		
		}


	//添加药品至订单
	private void addTrolley(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ids = request.getParameter("ids");//商品编号
		//获取session并从session中获取用户对象
		HttpSession session = request.getSession();
		Users users = (Users)session.getAttribute("users");
		//构建购物车的实体对象
		boolean flag = trolleyService.addTrolley(ids);
		if(flag) {
			//添加完成后返回添加药品专用的页面
			request.getRequestDispatcher("Trolley?method=findMedicineByPage").forward(request, response);
			response.getWriter().print(flag);
		}
	}
	
	//查询当前用户的所有购物车记录
	private void findTrolleyList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//当没有添加药品时，也能查看订单
		if(ids.equals("")||ids==null) {
			ids = 0+"";
		}
		List<Trolley> tlist = trolleyService.findTrolleyList();
		request.setAttribute("tlist", tlist);
		request.getRequestDispatcher("/Trolley/trolley.jsp").forward(request, response);
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
		//返回添加药品专用的页面
		request.getRequestDispatcher("/Trolley/index.jsp").forward(request, response);
	}
	
	//修改商品数量
	private void updateNumber(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tid = request.getParameter("tid");
		String number = request.getParameter("number");
		boolean flag = trolleyService.updateNumber(Integer.valueOf(tid),Integer.valueOf(number));
		if(flag) {
			findTrolleyList(request,response);
		}
	}
	
	//删除操作
	private void deleteTrolley(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tid = request.getParameter("tid");
		boolean flag = trolleyService.deleteTrolley(tid);
		if(flag) {
			findTrolleyList(request,response);
		}
	}

	//结算操作
	private void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag = trolleyService.pay();
		if(flag) {
			request.getRequestDispatcher("Trolley/Payment.jsp").forward(request, response);
		}
	}
	
	//跳转至支付方式
	private void PayFunction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Trolley/PayWay.jsp").forward(request, response);
	}

	//支付超时跳转
	private void PayFail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("Trolley/PayFail.jsp").forward(request, response);
	}
	
}
