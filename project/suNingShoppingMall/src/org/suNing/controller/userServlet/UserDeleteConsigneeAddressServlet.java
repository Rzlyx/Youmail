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

@WebServlet(urlPatterns="/deleteConsigneeAddress.do")

/**
 * 这个页面通过ajax 页面删除用户收货地址
 * @author ASUS
 *
 */
public class UserDeleteConsigneeAddressServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String addressId = request.getParameter("addressId");
		
		UserConsigneeAddressDao ucadao = new UserConsigneeAddressDaoImpl();
		int result = ucadao.deleteUserConsigneeAddress(Integer.parseInt(addressId));
		PrintWriter out = response.getWriter();
		if(result >0){
			out.print(result);
		}else{
			out.print(result);
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
