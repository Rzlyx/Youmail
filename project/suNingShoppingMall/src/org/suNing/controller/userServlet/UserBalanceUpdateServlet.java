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

@WebServlet(urlPatterns="/userBalanceUpdate.do")

/**
 * 这是用户对账户余额进行的操作servlet
 * @author ASUS
 *
 */
public class UserBalanceUpdateServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out  = response.getWriter();
		String userAccount = request.getParameter("userAcount");
		String cspMoney = request.getParameter("cspMoney");
		
		
		UserDao userdao = new UserDaoImpl();
		int result = userdao.updateAccountBalance(Integer.parseInt(cspMoney), userAccount);
		if(result >0 ){
			out.print(result);
		}else{
			out.print(result);
		}
		
		
		
		
		
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
