package org.suNing.controller.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.UserOrderFormDao;
import org.suNing.dao.impl.UserOrderFormDaoImpl;
import org.suNing.entity.UserOrderForm;
import org.suNing.utli.SuNingUtil;

@WebServlet(urlPatterns="/addOrderForm.do")

/**
 * 添加订单 servlet  用ajax 掉用的
 * @author ASUS
 *
 */
public class AddUserOrderFormServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		
		String commodityId = request.getParameter("comId");
		String userAccount = request.getParameter("userAccount");
		String commodityCount = request.getParameter("commodityCount");
		String orederFormNumber = SuNingUtil.generateRandom(); // 获取自动生成的订单编号
		//创建订单对象 并对其属性进行封装
		UserOrderForm uof = new UserOrderForm();
		uof.setUserAccount(userAccount);
		uof.setCommodityId(Integer.parseInt(commodityId));
		uof.setAddCommodityCount(Integer.parseInt(commodityCount));
		uof.setOrderFormNumber(orederFormNumber);
		UserOrderFormDao uofd = new UserOrderFormDaoImpl();
		PrintWriter out = response.getWriter();
		int result = uofd.addOrderForm(uof);
		if(result > 0){// 添加成功  并把 添加成功的数字 返回前 用来判断是否添加成功
			result = uofd.getNewAddOrderForm();  // 这个是获取刚刚在上一步添加完成的订单的id
			if(result >0)// 并赋值给 result 带到页面上去 用来 完成购买商品的后续步骤
				out.print(result);  // 最新添加的订单id
		}else{
			out.print(result);
		}
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
