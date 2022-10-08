package org.suNing.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.CommodityLittleTypeDao;
import org.suNing.dao.impl.CommodityLittleTypeDaoImpl;
import org.suNing.entity.CommodityLittleType;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns="/selectCommodityType.do")

/**
 * 执行修改前的 ajax 查询并反会对象 商品小类
 * @author ASUS
 *
 */
public class SelectCommodityLittleType extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		String littleTypeId = request.getParameter("littleTypeId");
		CommodityLittleTypeDao typedao = new CommodityLittleTypeDaoImpl();
		CommodityLittleType littleType = typedao.getCommodityLittle(Integer.parseInt(littleTypeId)); 
		
		if(littleType != null){
			// 创建json对象 并且把 查询出来的大类添加进去
			JSONObject job = JSONObject.fromObject(littleType);
			/*System.out.println(job.toString());*/
			// 接收 一个 输出对象
			PrintWriter out = response.getWriter();
			//把获得的对象给输出去
			out.print(job.toString());
		}else{
			
		}
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
