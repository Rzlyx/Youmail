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
import org.suNing.entity.ShoppingVehicle;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet(urlPatterns="/joinShoppingVehicle.do")

public class UserJoinShoppingVehicleServelt extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String userAccount = request.getParameter("userAccount");
		String commodityId = request.getParameter("comid");
		String commodityCount  = request.getParameter("commodityCount");
		// 创建购物车对象 并对属性进行封装
		ShoppingVehicle sve = new ShoppingVehicle();
		sve.setUserAccount(userAccount);
		sve.setCommodityId(Integer.parseInt(commodityId));
		sve.setCommodityCount(Integer.parseInt(commodityCount));
		//创建购物车接口
		ShoppingVehicleDao svd = new ShoppingVehicleDaoImpl();
		int result = svd.joinShoppingVehicle(sve);
		if(result > 0){// 表示添加成
			out.print(result);
		}else{// 添加失败
			out.print(result);
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
