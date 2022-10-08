package org.suNing.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.CommodityInfoDao;
import org.suNing.dao.impl.CommodityInfoDaoImpl;
import org.suNing.entity.CommodityInfo;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns="/idGetCommodityInfo.do")

/**
 * 商品信息 ajax 
 * @author ASUS
 *
 */
public class SelectCommodityInfoServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		String commodityId = request.getParameter("commid");
		
		CommodityInfoDao infodao = new CommodityInfoDaoImpl();
		CommodityInfo commodityInfo = infodao.getCommodityInfo(Integer.parseInt(commodityId));
		if(commodityInfo != null){
			JSONObject jobj = JSONObject.fromObject(commodityInfo);
			
			PrintWriter out = response.getWriter();
			out.print(jobj);
		}else{
			
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
