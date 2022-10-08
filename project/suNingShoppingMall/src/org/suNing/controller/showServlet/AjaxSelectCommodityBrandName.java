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

import net.sf.json.JSONArray;

@WebServlet(urlPatterns="/getAllCommodityBrandName.do")

/**
 * 通过ajax 调用这个servlet 实现查询商品品牌 用于导航栏
 * @author ASUS
 *
 */
public class AjaxSelectCommodityBrandName extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		String typeId = request.getParameter("typeId");
		
		CommodityDao comdao = new CommodityDaoImpl();
		List<Commodity> commodity = comdao.typeIdGetCommodity(Integer.parseInt(typeId));
		
		JSONArray jsarr =JSONArray.fromObject(commodity);
		
		PrintWriter out = response.getWriter();
		
		out.print(jsarr);
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
		
	}

}
