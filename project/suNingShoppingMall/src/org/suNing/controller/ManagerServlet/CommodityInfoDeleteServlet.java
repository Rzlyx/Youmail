package org.suNing.controller.ManagerServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.CommodityInfoDao;
import org.suNing.dao.impl.CommodityInfoDaoImpl;

@WebServlet(urlPatterns="/deleteCommodityInfo.do")

/**
 * 删除商品信息 
 * @author ASUS
 *
 */
public class CommodityInfoDeleteServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//商品id
		String comid = request.getParameter("comid");
		
		CommodityInfoDao cominfodao = new CommodityInfoDaoImpl();
		
		int result = cominfodao.deleteCommodityInfo(Integer.parseInt(comid));
		if(result > 0){
			//把商品id 传到 删除商品的servlet的页面去
			request.setAttribute("commodityId", comid);
			request.getRequestDispatcher("deleteCommodity.do").forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/pageSelectCommodity.do");
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
