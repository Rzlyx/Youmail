package org.suNing.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.UserDao;
import org.suNing.dao.impl.UserDaoImpl;
import org.suNing.entity.User;

import net.sf.json.JSONObject;

@WebServlet(urlPatterns="/SelectUserCount.do")

/**
 * 这是通过 用户账户名 获取又没有重复的账户
 * @author ASUS
 *
 */
public class SelectUserCountServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		String userAccount = request.getParameter("userAccount");
		
		UserDao userdao = new UserDaoImpl();
		int usercount = userdao.accountGatCount(userAccount);
		
		 PrintWriter out =  response.getWriter();
		 out.print(usercount);
				
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
