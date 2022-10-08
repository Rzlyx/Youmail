package org.suNing.controller.ManagerServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.CommodityDao;
import org.suNing.dao.impl.CommodityDaoImpl;

@WebServlet(urlPatterns="/deleteCommodity.do")

public class CommodityDeleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String comid = (String)request.getAttribute("commodityId");
			   
		
		CommodityDao commoditydao = new CommodityDaoImpl();
		int result = commoditydao.deleteCommodity(Integer.parseInt(comid));
		if(result>0){
			response.sendRedirect(request.getContextPath()+"/pageSelectCommodity.do");
		}else{
			response.sendRedirect(request.getContextPath()+"/pageSelectCommodity.do");
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
