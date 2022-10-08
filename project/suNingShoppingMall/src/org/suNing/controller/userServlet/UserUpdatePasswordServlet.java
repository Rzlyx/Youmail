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
import org.suNing.entity.User;

@WebServlet(urlPatterns="/userUpdatePwd.do")

/**
 *  这是用户用来修改 密码的servlet
 * @author ASUS
 *
 */

public class UserUpdatePasswordServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String userAccount = request.getParameter("userAccount");
		String userNewPwd = request.getParameter("newPwd");
		
		User user = new User();
		user.setUserAccount(userAccount);
		user.setUserPwd(userNewPwd);
		
		UserDao userdao = new UserDaoImpl();
		int result = userdao.updatePwd(user);
		PrintWriter out = response.getWriter();
		if(result>0){
			out.print(result); 
		}else{
			out.print(result); 
		}
		
		
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
