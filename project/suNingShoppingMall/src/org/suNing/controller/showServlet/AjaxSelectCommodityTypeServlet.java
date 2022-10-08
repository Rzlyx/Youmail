package org.suNing.controller.showServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.CommodityLittleTypeDao;
import org.suNing.dao.impl.CommodityLittleTypeDaoImpl;
import org.suNing.entity.CommodityLittleType;

import net.sf.json.JSONArray;

@WebServlet(urlPatterns="/broadTypeIdGetAllCommodityType.do")
/**
 *  首页导航栏的 商品小类  
 * @author ASUS
 *
 */
public class AjaxSelectCommodityTypeServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取商品大类id
		response.setContentType("text/html;charset=utf-8");
		String broadTypeId = request.getParameter("typeId");
		CommodityLittleTypeDao typedao = new CommodityLittleTypeDaoImpl();
		List<CommodityLittleType> type = typedao.getAllCommodityLittle(Integer.parseInt(broadTypeId));
		
		JSONArray jsonarr = JSONArray.fromObject(type);
//		System.out.println(jsonarr.toString());
		PrintWriter out = response.getWriter();
		out.print(jsonarr);
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
