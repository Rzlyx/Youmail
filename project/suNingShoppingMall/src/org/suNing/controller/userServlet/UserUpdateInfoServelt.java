package org.suNing.controller.userServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.UserInfoDao;
import org.suNing.dao.impl.UserInfoDaoImpl;
import org.suNing.entity.UserInfo;

@WebServlet(urlPatterns="/userUpdateInfo.do")

/**
 *   这是用户修改信息的servlet
 * @author ASUS
 *
 */
public class UserUpdateInfoServelt extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");
		
		String userAccount = request.getParameter("useracc");
		String userName = request.getParameter("userName");
		String userGender = request.getParameter("usersex");
		String birthDate  = request.getParameter("userdate");
		String userIdentityMark = request.getParameter("usermk");
		String userEmail = request.getParameter("userem");
		String userPhone = request.getParameter("userhe");
		
		UserInfo userInfo = new UserInfo();
		userInfo.setUserAccount(userAccount);
		userInfo.setUserName(userName);
		userInfo.setUserGender(userGender);
		userInfo.setUserEmail(userEmail);
		userInfo.setUserPhone(userPhone);
		userInfo.setUserIdentityMark(userIdentityMark);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(birthDate);
			userInfo.setUserBirthDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserInfoDao uidao = new UserInfoDaoImpl();
		int result = uidao.updateUserInfo(userInfo);
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
