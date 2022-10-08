package org.suNing.controller.userServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.CommodityDao;
import org.suNing.dao.UserDao;
import org.suNing.dao.UserOrderFormDao;
import org.suNing.dao.impl.CommodityDaoImpl;
import org.suNing.dao.impl.UserDaoImpl;
import org.suNing.dao.impl.UserOrderFormDaoImpl;
import org.suNing.entity.Commodity;

@WebServlet(urlPatterns="/orderFormSettle.do")

/**
 * 这是用户通过 订单和商品信息页面结算商品的servelt
 * @author ASUS
 *
 */
public class UserOrderFormSettleServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		// 这一块执行用户余额的修改
		String userAccount = request.getParameter("userAccount");
		String userYueMouney = request.getParameter("YueMoney");
		UserDao userdao = new UserDaoImpl();// 修改用户 余额
		int resUser1 = userdao.updateAccountBalance(Integer.parseInt(userYueMouney), userAccount);
		if(resUser1 <= 0){
			System.out.println("余额更改失败");
		}
		
		//这一块执行商品库存的修改
		String commodityId = request.getParameter("commodityId");
		String commodityCount = request.getParameter("commodityCount");
		int comId = Integer.parseInt(commodityId);// 商品id
		int commCount = Integer.parseInt(commodityCount);//购买商品数量
		CommodityDao cmdDao = new CommodityDaoImpl();
		int resCommodity2 = cmdDao.updateCommodityCount(commCount,comId);// 修改商品数量
		if(resCommodity2 <= 0){
			System.out.println("库存更改失败");
		}
		// 修改商品的销量
		resCommodity2= cmdDao.udpateCommoditySalesVolume(commCount, comId);
		if(resCommodity2 <= 0){
			System.out.println("销量更改失败");
		}
		
		
		//这一块执行订单状态的修改
		String orderFormId = request.getParameter("orderFormId");
		String orderFormStateId = request.getParameter("orderFormStateId");
		UserOrderFormDao uofd = new UserOrderFormDaoImpl();
		int resOrderForm3 = uofd.updateUserOrderState(Integer.parseInt(orderFormStateId), Integer.parseInt(orderFormId));// 修改商品状态
		if(resOrderForm3 <= 0){
			System.out.println("状态更改失败");
		}
		
		response.sendRedirect(request.getContextPath()+"/showUserOrderFormInfo.do");
		
		
		
		
		
		
		
		
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
