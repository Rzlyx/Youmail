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

@WebServlet(urlPatterns="/userDeleteVehicleCommodity.do")

/**
 * 这是用户在购物车页面 单独删除一个购物车商品时执行的servelt 
 *  ajax
 * @author ASUS
 *
 */
public class UserDeleteShoppingVehicleCommodityServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String VehicleCommodityId = request.getParameter("shveId");
		
		ShoppingVehicleDao shvedao = new ShoppingVehicleDaoImpl();
		int result = shvedao.deleteShoppingCommodity(Integer.parseInt(VehicleCommodityId));
		PrintWriter out = response.getWriter();
		if(result > 0){
			out.print(result);
		}else{
			out.print(result);
		}
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
