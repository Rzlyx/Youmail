package org.suNing.controller.ManagerServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.UserDao;
import org.suNing.dao.UserInfoDao;
import org.suNing.dao.impl.UserDaoImpl;
import org.suNing.dao.impl.UserInfoDaoImpl;

@WebServlet(urlPatterns="/deleteUserAndUserInfo.do")

/**
 * 执行删除用户和用户信息servlet
 * @author ASUS
 *
 */
public class UserInfoDeleteAndUser extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String userAccount = request.getParameter("userAccount");
		UserInfoDao userinfo = new UserInfoDaoImpl();
		if(userinfo.deleteUserInfo(userAccount)>0){ // 表示用户信息删除成功
			UserDao user = new UserDaoImpl();
			if(user.deleteUser(userAccount)>0){// 表示用户删除成功
				out.print(1);
			}else{
				out.print(0);
			}
		}else{
			out.print(0);
		}
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
