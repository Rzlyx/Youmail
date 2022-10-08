package org.suNing.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.OrderFormStateDao;
import org.suNing.dao.UserOrderFormDao;
import org.suNing.dao.impl.OrderFormStateDaoImpl;
import org.suNing.dao.impl.UserOrderFormDaoImpl;
import org.suNing.entity.OrderFormState;
import org.suNing.entity.UserOrderForm;
import org.suNing.utli.PageBean;

@WebServlet(urlPatterns="/getAllOrderForm.do")

/**
 * 这是分页的查询所有的 所有用户订单servelt
 * @author ASUS
 *
 */
public class SelectAllUserOrderFormServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		// 创建状态接口
		OrderFormStateDao ofsdao = new OrderFormStateDaoImpl();
		List<OrderFormState> orfs =ofsdao.getAllOrderFormState();// 获取所有状态
		
		
		// 创建分页对象
		PageBean pageBean = new PageBean();
		String pageNum = request.getParameter("pageNum");
		if(pageNum != null && !"".equals(pageNum)){
			pageBean.setPageNum(Integer.parseInt(pageNum));
		}
		pageBean.setPageSize(10);// 初始化页面大小
		
		String userAccount = request.getParameter("userAccount");
		String orderFormStatus = request.getParameter("statusId");
		UserOrderFormDao uofdao = new UserOrderFormDaoImpl();
		pageBean.setTotalCount(uofdao.getConditionUserOrderForm(userAccount==null?"":userAccount, Integer.parseInt(orderFormStatus==null?"0":orderFormStatus)));
		List<UserOrderForm> uofs = uofdao.getAllUserOrderForm(pageBean.getPageNum(), userAccount==null?"":userAccount, Integer.parseInt(orderFormStatus==null?"0":orderFormStatus));
		
		if(uofs != null){
			request.setAttribute("orfs", orfs);// 订单状态
			request.setAttribute("pageBean", pageBean); // 分页对象
			request.setAttribute("uofs",uofs);// 所有订单信息
			request.setAttribute("userAccount", userAccount);
			request.setAttribute("orderFormStatus", orderFormStatus);
			request.getRequestDispatcher("manager/manageUserPage/UserOrderFormManage.jsp").forward(request, response);
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
