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
import org.suNing.utli.PageBean;



/**
 * 这是从首页搜索那 跳转到这页
 * @author ASUS
 *
 */

@WebServlet(urlPatterns="/searchResult.do")
public class ShowSearchResultServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		request.setCharacterEncoding("utf-8");
		
		// 创建 分页对象
		PageBean pagebean = new PageBean();
		// 这是查询初始化和翻页时传过来的当前页数
		String pageNum = request.getParameter("pageNum");
		if((pageNum != null && !"".equals(pageNum)) ){
			pagebean.setPageNum(Integer.parseInt(pageNum));
		}
		// 初始设置 当前页的条数为10页
		pagebean.setPageSize(10);
		
		
		// 获取从首页传过来的 查询条件
		String  centent = request.getParameter("goodsName");
		//对请求数据进行封装
		Commodity commodityCondition = new Commodity();
		// 这个现在是 可以模糊查询  商品名称、商品类别、商品品牌名称
		commodityCondition.setCommodityName(centent==null?"":centent);
		
		// 创建 商品dao接口
		CommodityDao commoditydao = new CommodityDaoImpl();
		// 接收商品数量
		pagebean.setTotalCount(commoditydao.getCommodityCount(commodityCondition));
		// 获取查询出来的对象
		List<Commodity> listCommodity = commoditydao.searchResultConditionGetCommodity( pagebean.getPageNum(), commodityCondition);
		if(listCommodity != null){
			request.setAttribute("list", listCommodity);
			request.setAttribute("pagebean", pagebean);
			request.getRequestDispatcher("getSalesVolumeTop3.do").include(request, response);
			request.getRequestDispatcher("SearchResultPage.jsp").forward(request, response);
		}else{
			request.setAttribute("list", listCommodity);
			request.setAttribute("page", pagebean);
			request.getRequestDispatcher("SearchResultPage.jsp").forward(request, response);
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
