package org.suNing.controller.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.UserConsigneeAddressDao;
import org.suNing.dao.impl.UserConsigneeAddressDaoImpl;

@WebServlet(urlPatterns="/updateAcquiesceStatus.do")

/**
 * 这个是 用户设置默认地址的servlet 
 * @author ASUS
 *
 */
public class UserUpdateAcquiesceStatus extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userAccount = request.getParameter("userAccount");
		String addressId = request.getParameter("addressId");
		
		UserConsigneeAddressDao ucadao = new UserConsigneeAddressDaoImpl();
		// 这个是先把用户的所有地址设置为 不是默认的 
		int res = ucadao.updateUseracquiesceStatus(userAccount);
		if(res <= 0){
			System.out.println("把用户的所有地址设置为不是默认的地址时失败！");
		}
		// 设置默认地址
		res = ucadao.updateacquiesceStatus(Integer.parseInt(addressId));
		if(res <= 0){
			System.out.println("把用户的地址设置为默认的地址时失败！");
		}
		PrintWriter out = response.getWriter();
		if(res >0){
			out.print(res);
		}else{
			out.print(res);
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
