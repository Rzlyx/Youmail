package org.suNing.controller.showServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.CommodityDao;
import org.suNing.dao.impl.CommodityDaoImpl;
import org.suNing.entity.Commodity;

@WebServlet(urlPatterns="/getSalesVolumeTop3.do")

/**
 * 商品销量排行的servlet
 * @author ASUS
 *
 */
public class CommoditySalesVolume extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CommodityDao comdao = new CommodityDaoImpl();
		List<Commodity> commodity = comdao.getAllCommodity();
		if(commodity != null){
			//  被搜索结果页面调用
			request.setAttribute("commoditySalesVolume", commodity);
		}
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
