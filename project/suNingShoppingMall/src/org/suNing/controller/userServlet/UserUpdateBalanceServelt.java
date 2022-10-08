package org.suNing.controller.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.UserDao;
import org.suNing.dao.impl.UserDaoImpl;

@WebServlet(urlPatterns="/UserbalanceUpdate.do")


/**
 * 这是用户充值账户余额 的servlet
 * @author ASUS
 *
 */
public class UserUpdateBalanceServelt extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String userAccount = request.getParameter("userAccount");
		String balance = request.getParameter("newMoney");
		
		UserDao userdao = new UserDaoImpl();
		int result = userdao.updateAccountBalance(Integer.parseInt(balance), userAccount);
		PrintWriter out = response.getWriter();
		if(result > 0){
			out.print(result);
		}else{
			out.print(result);
		}
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
