package org.suNing.controller.userServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.UserConsigneeAddressDao;
import org.suNing.dao.impl.UserConsigneeAddressDaoImpl;
import org.suNing.entity.UserConsigneeAddress;

@WebServlet(urlPatterns="/userAddaddress.do")

/**
 * 执行添加 用户地址 的servlet
 * @author ASUS
 *
 */
public class UserAddConsigneeAddress extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String userAccount = request.getParameter("userAccount");
		String consigneeAddress = request.getParameter("consigneeAddress");
		// 添加地址前先把这个用户的所有地址状态修改成不是默认的 因为只要新添加的地址都是默认的
		UserConsigneeAddressDao ucadao = new UserConsigneeAddressDaoImpl();
		int result = ucadao.updateUseracquiesceStatus(userAccount);
		if(result <= 0){
			System.out.println("修改用户地址状态失败");
			
		}
		UserConsigneeAddress ucadd = new UserConsigneeAddress();
		ucadd.setUserAccount(userAccount);
		ucadd.setConsigneeAddress(consigneeAddress);
		// 添加地址
		result = ucadao.addUserConsigneeAddress(ucadd);
		if(result <= 0){
			System.out.println("地址添加失败");
			return;
		}
		response.sendRedirect(request.getContextPath()+"/showUserOrderFormInfo.do");
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
