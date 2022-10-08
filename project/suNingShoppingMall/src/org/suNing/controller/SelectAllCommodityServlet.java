package org.suNing.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.CommodityDao;
import org.suNing.dao.CommodityLittleTypeDao;
import org.suNing.dao.CommodityStatusDao;
import org.suNing.dao.impl.CommodityDaoImpl;
import org.suNing.dao.impl.CommodityLittleTypeDaoImpl;
import org.suNing.dao.impl.CommodityStatusDaoImpl;
import org.suNing.entity.Commodity;
import org.suNing.entity.CommodityLittleType;
import org.suNing.entity.CommodityStatus;
import org.suNing.utli.PageBean;

@WebServlet(urlPatterns="/pageSelectCommodity.do")

/**
 * 对商品页面的 分页查询
 * @author ASUS
 *
 */
public class SelectAllCommodityServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		CommodityLittleTypeDao little = new CommodityLittleTypeDaoImpl();
		List<CommodityLittleType> littleType = little.getAllCommodityLittle();
		//创建商品状态 接口
		CommodityStatusDao statusdao = new CommodityStatusDaoImpl();
		//接收商品状态
		List<CommodityStatus> commodityStatus = statusdao.getAllCommodityStatus();
		
		
		PageBean pagebean = new PageBean();
		// 这是查询初始化和翻页时传过来的当前页数
		String pageNum = request.getParameter("pageNum");
		if((pageNum != null && !"".equals(pageNum)) ){
			pagebean.setPageNum(Integer.parseInt(pageNum));
		}
		String pageNum2 = (String)request.getAttribute("pageNum2");// 这是修改成功后传过来的当前页数
		// 判断传过来的页数是否为空
		if(pageNum2 != null && !"".equals(pageNum)){
			pagebean.setPageNum(Integer.parseInt(pageNum2));
		}
		
		
		// 这个现在是 可以模糊查询  商品名称、商品类别、商品品牌名称
		String commodityName = request.getParameter("commodityName");
		String commodityTypeId = request.getParameter("commodityTypeId");
		String commodityStatusTypeId = request.getParameter("commodityStatusTypeId");
		//对请求数据进行封装
		Commodity commodityCondition = new Commodity();
		// 这个现在是 可以模糊查询  商品名称、商品类别、商品品牌名称
		commodityCondition.setCommodityName(commodityName);
		commodityCondition.setCommodityTypeId(Integer.parseInt(commodityTypeId==null?"0":commodityTypeId));
		commodityCondition.setCommodityStatusTypeId(Integer.parseInt(commodityStatusTypeId==null?"0":commodityStatusTypeId));
		
		
		// 创建 商品dao接口
		CommodityDao commoditydao = new CommodityDaoImpl();
		// 接收商品数量
		pagebean.setTotalCount(commoditydao.getCommodityCount(commodityCondition));
		// 获取查询出来的对象
		List<Commodity> listCommodity = commoditydao.passPageConditionGetCommodity(pagebean.getPageSize(), pagebean.getPageNum(), commodityCondition);
		if(listCommodity != null){
			
			request.setAttribute("list", listCommodity);  //所有商品集合
			request.setAttribute("page", pagebean);  //分页 对象
			request.setAttribute("commodityType", littleType);  // 所有商品类型集合
			request.setAttribute("commodity", commodityCondition); // 把所有的查询条件给添加到请求里
			request.setAttribute("commodityStatus", commodityStatus);  // 所有商品状态集合
			request.getRequestDispatcher("manager/managePages/CommodityPage.jsp").forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/manager/managePages/CommodityPage.jsp");
			
		}
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
