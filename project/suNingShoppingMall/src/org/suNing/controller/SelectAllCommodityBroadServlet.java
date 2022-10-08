package org.suNing.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.CommodityBroadDao;
import org.suNing.dao.impl.CommodityBroadDaoImpl;
import org.suNing.entity.CommodityBroad;

@WebServlet(urlPatterns="/getCommodityBroad.do")
/**
 * 执行查询所有商品大类的servlet  可以被管理页面和商品页面同时调用
 * @author ASUS
 *
 */
public class SelectAllCommodityBroadServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		CommodityBroadDao cbd = new CommodityBroadDaoImpl();
		List<CommodityBroad> list = cbd.getAllCommodityBroad();
		if(list != null){
			request.setAttribute("list", list);
		}
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
