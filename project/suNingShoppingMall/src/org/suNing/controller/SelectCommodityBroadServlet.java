package org.suNing.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.CommodityBroadDao;
import org.suNing.dao.impl.CommodityBroadDaoImpl;
import org.suNing.entity.CommodityBroad;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns="/selectIdCommodityBroad.do")

/**
 * 通过商品大类id 查询大类
 * @author ASUS
 *
 */
public class SelectCommodityBroadServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应时的内容编码
		response.setContentType("text/html;charset=utf-8");
		String commId = request.getParameter("commid");// 接收传过来的大类id
		
		CommodityBroadDao commdao = new CommodityBroadDaoImpl();
		//进行查询并放回查询结果
		CommodityBroad broadType = commdao.getCommodityBroad(Integer.parseInt(commId));
		if(broadType != null){
			// 接收一个json对象 
			JSONObject jo = JSONObject.fromObject(broadType);
			/*System.out.println(jo.toString());*/
			//  获得一个输出对象
			PrintWriter out = response.getWriter();
			// 返回 json对象
			out.print(jo.toString());
		}else{
			
		}
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
