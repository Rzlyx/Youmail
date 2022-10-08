package org.suNing.controller.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.ShoppingVehicleDao;
import org.suNing.dao.impl.ShoppingVehicleDaoImpl;

@WebServlet(urlPatterns="/userForShopping.do")

/**
 * 这是用户在购物车页面删除多个购物车商品时的servlet  ajax
 * @author ASUS
 *
 */
public class UserForDeleteShoppingCommoditServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		
		
		String[] shoppingId = request.getParameterValues("shoppingId");
		String sho = request.getParameter("shoppingId");
		ShoppingVehicleDao shvedao = new ShoppingVehicleDaoImpl();
		int result = 0;
		for (int i = 0; i < shoppingId.length; i++) {
			result = shvedao.deleteShoppingCommodity(Integer.parseInt(shoppingId[i]));
			
		}
		PrintWriter out = response.getWriter();
		if(result >0){
			out.print(result);
		}else{
			out.print(result);
		}
		
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
