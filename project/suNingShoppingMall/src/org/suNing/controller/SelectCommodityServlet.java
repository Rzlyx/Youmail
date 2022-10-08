package org.suNing.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.CommodityDao;
import org.suNing.dao.impl.CommodityDaoImpl;
import org.suNing.entity.Commodity;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns="/idGetCommodity.do")

/**
 *  用ajax放回商品
 * @author ASUS
 *
 */
public class SelectCommodityServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		
		String commodityId = request.getParameter("commodityId");
		CommodityDao comdao = new CommodityDaoImpl();
		Commodity commodity = comdao.idGetCommodity(Integer.parseInt(commodityId));
		if(commodity != null){
			JSONObject jobj = JSONObject.fromObject(commodity);
			
			PrintWriter out =  response.getWriter();
			out.print(jobj.toString());
		}else{
			
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
