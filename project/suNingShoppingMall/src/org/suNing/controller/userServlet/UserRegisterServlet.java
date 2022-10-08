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

import org.suNing.dao.UserDao;
import org.suNing.dao.UserInfoDao;
import org.suNing.dao.impl.UserDaoImpl;
import org.suNing.dao.impl.UserInfoDaoImpl;
import org.suNing.entity.User;
import org.suNing.entity.UserInfo;

@WebServlet(urlPatterns="/registerPage.do")
/**
 * 这是用户点击注册时servle页面 
 * @author ASUS
 *
 */
public class UserRegisterServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userAccount = request.getParameter("user-account");
		String userPwd = request.getParameter("user-pwd");
		// 创建用户对象并对用户属性进行封装
		User user = new User();
		user.setUserAccount(userAccount);
		user.setUserPwd(userPwd);
		user.setAccountBalance(0);
		UserDao userdao = new UserDaoImpl();
		// 这是个新注册的用户默认值 给完以后 让他们自己到个人资料那里去修改去
		UserInfo userInfo = new UserInfo();
		userInfo.setUserAccount(userAccount);
		userInfo.setUserName(userAccount);
		userInfo.setUserGender("男");
		userInfo.setUserEmail("未填写");
		userInfo.setUserIdentityMark("未填写");
		userInfo.setUserPhone("未填写");
		String data = "1990-01-01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			userInfo.setUserBirthDate(sdf.parse(data));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserInfoDao uifdao = new UserInfoDaoImpl();
		
		
		
		if(userdao.addUser(user)>0 ){// 如果注册成功就直接跳转到 登陆页面
			int res = uifdao.addUserInfo(userInfo); //添加用户的默认信息
			response.sendRedirect(request.getContextPath()+"/UserLogin.jsp");
		}else{// 否则在跳回去
			request.setAttribute("err", "<script>alert('注册失败-请重新注册！')</script>");
			request.getRequestDispatcher("UserRegister.jsp").forward(request, response);
			
		}
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
