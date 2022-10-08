package org.suNing.controller.userServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.suNing.dao.ShoppingVehicleDao;
import org.suNing.dao.UserConsigneeAddressDao;
import org.suNing.dao.UserDao;
import org.suNing.dao.UserInfoDao;
import org.suNing.dao.UserOrderFormDao;
import org.suNing.dao.impl.ShoppingVehicleDaoImpl;
import org.suNing.dao.impl.UserConsigneeAddressDaoImpl;
import org.suNing.dao.impl.UserDaoImpl;
import org.suNing.dao.impl.UserInfoDaoImpl;
import org.suNing.dao.impl.UserOrderFormDaoImpl;
import org.suNing.entity.ShoppingVehicle;
import org.suNing.entity.User;
import org.suNing.entity.UserConsigneeAddress;
import org.suNing.entity.UserInfo;
import org.suNing.entity.UserOrderForm;

@WebServlet(urlPatterns="/showUserOrderFormInfo.do")


/**
 * 这是用户登录后 进入个人中心页面的servlet
 * @author ASUS
 *
 */
public class ShowUserOrderFormInfoServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		//过去状态id
	    String orderFormStateId = request.getParameter("orderFormStateId");
	    HttpSession session = request.getSession();
	    UserInfo sessionUserInfo = (UserInfo)session.getAttribute("userInfo");
//		if(Integer.parseInt(orderFormStateId)==0){
//			
//		}
		
		// 创建用户接口
		UserDao userdao = new UserDaoImpl();
		// 获取用户对象
		User user = userdao.accountGatUser(sessionUserInfo.getUserAccount());
		
		//创建用户信息接口
		UserInfoDao uidao = new UserInfoDaoImpl();
		// 获取用户信息对象
		UserInfo userInfo = uidao.accountGetUserInfo(sessionUserInfo.getUserAccount());
		
	    
	    // 创建订单接口
		UserOrderFormDao uofd = new UserOrderFormDaoImpl();
		// 获取所有订单
		List<UserOrderForm> uof = uofd.getAllUserOrderForm(1, sessionUserInfo.getUserAccount(), Integer.parseInt(orderFormStateId==null?"0":orderFormStateId));
		
		// 创建用户收件地址接口
		UserConsigneeAddressDao ucadao = new UserConsigneeAddressDaoImpl();
		// 获取指定用户的收件地址集合
		List<UserConsigneeAddress> userAddress = ucadao.accountGetUserConsigneeAddress(sessionUserInfo.getUserAccount());
		
		//创建用户购物车接口
		ShoppingVehicleDao spvedao = new ShoppingVehicleDaoImpl();
		List<ShoppingVehicle> shveList = spvedao.accountGetShoppingCommodity(sessionUserInfo.getUserAccount());
		
		if(uof != null){
			// 这是状态id 用来显示状态选项样式
			request.setAttribute("stateId", orderFormStateId);
			
			// 这是用户对象
			request.setAttribute("user", user);
			//这是用户信息
			request.setAttribute("userInfo", userInfo);
			// 这是订单集合
			request.setAttribute("uof", uof);
			//这是用户收件地址集合
			request.setAttribute("userAddress", userAddress);
			// 这是用户购物车集合
			request.setAttribute("shve", shveList);
			
			request.getRequestDispatcher("userPage/UserInfoShowPage.jsp").forward(request, response);
			
		}
		
		
		
		
		
		
		
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
