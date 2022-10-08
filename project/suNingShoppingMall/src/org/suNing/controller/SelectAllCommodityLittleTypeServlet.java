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
import org.suNing.dao.CommodityLittleTypeDao;
import org.suNing.dao.impl.CommodityBroadDaoImpl;
import org.suNing.dao.impl.CommodityLittleTypeDaoImpl;
import org.suNing.entity.CommodityBroad;
import org.suNing.entity.CommodityLittleType;
import org.suNing.utli.PageBean;

@WebServlet(urlPatterns="/getAllCommodityLittleType.do")

/**
 * 显示所有小类
 * @author ASUS
 *
 */
public class SelectAllCommodityLittleTypeServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		// 创建 商品大类接口
		CommodityBroadDao cbd = new CommodityBroadDaoImpl();
		//接收所有商品大类 用来添加商品小类的时候 进行选择
		List<CommodityBroad> broadTypeList = cbd.getAllCommodityBroad();
		
		
		PageBean pagebean = new PageBean();
		//接收当前页的请求
		String pageNum = request.getParameter("pageNum");
		if(pageNum != null && !"".equals(pageNum)){
			pagebean.setPageNum(Integer.parseInt(pageNum));
		}
		// 接收条数请求
		String pageSize = request.getParameter("pageSize");
		if(pageSize != null && !"".equals(pageSize)){
			pagebean.setPageSize(Integer.parseInt(pageSize));
		}
		
		
		// 创建商品小类的实现类
		CommodityLittleTypeDao typedao = new CommodityLittleTypeDaoImpl();
		//获取总条数
		pagebean.setTotalCount(typedao.getCommodityLittleCount());
		List<CommodityLittleType> list = typedao.getAllCommodityLittle(pagebean.getPageSize(),pagebean.getPageNum());
		if(list != null){// 如果查询成功了
			request.setAttribute("broadTypeList", broadTypeList);
			request.setAttribute("page", pagebean);  
			request.setAttribute("list", list);
			request.getRequestDispatcher("manager/managePages/CommodityLittleType.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("manager/managePages/CommodityLittleType.jsp").forward(request, response);
		}
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
