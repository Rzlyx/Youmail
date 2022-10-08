package org.suNing.controller.ManagerServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.CommodityLittleTypeDao;
import org.suNing.dao.impl.CommodityLittleTypeDaoImpl;

@WebServlet(urlPatterns="/littleTypeDelete.do")
/**
 * 删除商品小类
 * @author ASUS
 *
 */
public class CommodityLittleTypeDeleteServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String littleTypeId = request.getParameter("littleTypeId");
		
		CommodityLittleTypeDao typedao = new CommodityLittleTypeDaoImpl();
		int result = typedao.deleteCommodityLittle(Integer.parseInt(littleTypeId));
		if(result > 0){
			response.sendRedirect(request.getContextPath()+"/getAllCommodityLittleType.do");
		}else{
			response.sendRedirect(request.getContextPath()+"/getAllCommodityLittleType.do");
		}
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
