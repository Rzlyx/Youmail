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
import org.suNing.entity.CommodityLittleType;

@WebServlet(urlPatterns="/littleTypeAddandEdit.do")

/**
 * 执行修改和添加 的商品小类
 * @author ASUS
 *
 */
public class CommodityLittleTypeAddandEditServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		int result = 0;
		String littleTypeId = request.getParameter("hid_id");
		String littleTypeName = request.getParameter("littleType");
		String broadTypeId = request.getParameter("broadTypeId");
		CommodityLittleTypeDao littledao = new CommodityLittleTypeDaoImpl();
		CommodityLittleType littleType = new CommodityLittleType();
		littleType.setCltName(littleTypeName);
		littleType.setCommodityBroadId(Integer.parseInt(broadTypeId));
		if("null".equals(littleTypeId)){// 等于null的话表示 是添加
			result = littledao.addCommodityLittle(littleType);
			if(result >0){//表示添加成功
				response.sendRedirect(request.getContextPath()+"/getAllCommodityLittleType.do");
			}
		}else{// 表示 是修改
			littleType.setId(Integer.parseInt(littleTypeId));
			result = littledao.updateCommodityLittle(littleType);
			if(result >0){//表示修改成功
				response.sendRedirect(request.getContextPath()+"/getAllCommodityLittleType.do");
			}
		}
	}

		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			doGet(request, response);
			
	}

}
