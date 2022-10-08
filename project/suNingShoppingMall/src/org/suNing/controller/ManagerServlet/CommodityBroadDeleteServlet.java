package org.suNing.controller.ManagerServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.CommodityBroadDao;
import org.suNing.dao.impl.CommodityBroadDaoImpl;

@WebServlet(urlPatterns="/commodityBroadDelete.do")

/**
 * 删除商品大类servlet
 * @author ASUS
 *
 */
public class CommodityBroadDeleteServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收 要删除的大类id
		String BroadTypeId = request.getParameter("BroadTypeId");
		
		CommodityBroadDao comm = new CommodityBroadDaoImpl();
		// 执行删除并接收 删除结果
		int result = comm.deleteCommodityBroad(Integer.parseInt(BroadTypeId));
		if(result >= 0){//  表示删除成功  跳转到显示页面
			response.sendRedirect(request.getContextPath()+"/entranceBroadType.do");
		}else{
			response.sendRedirect(request.getContextPath()+"/entranceBroadType.do");
		}
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
