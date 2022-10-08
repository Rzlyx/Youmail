package org.suNing.controller.ManagerServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/entranceBroadType.do")

/**
 * 这是进入商品大类管理页面的servelt
 * @author ASUS
 *
 */
public class EntranceCommodityBroadManagePageServelt extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		request.getRequestDispatcher("/getCommodityBroad.do").include(request, response);
		//跳转到 管理界面
		request.getRequestDispatcher("/manager/managePages/CommodityBroad.jsp").forward(request, response);
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
