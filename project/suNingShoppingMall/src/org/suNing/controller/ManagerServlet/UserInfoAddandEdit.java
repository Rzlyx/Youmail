package org.suNing.controller.ManagerServlet;

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

@WebServlet(urlPatterns="/userInfoAddandEdit.do")


/**
 * 用户信息的添加个修改servlet
 * @author ASUS
 *
 */
public class UserInfoAddandEdit extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		// 接收所有的用户信息
		String isEdit = request.getParameter("isEdit");
		String userAccount = request.getParameter("userAccount");
		String userPwd = request.getParameter("userPwd");
		String accountBalance = request.getParameter("accountBalance");
		String userName = request.getParameter("userName");
		String userGender = request.getParameter("userGender");
		String userBirthDate = request.getParameter("userBirthDate");
		String userIdentityMark = request.getParameter("userIdentityMark");
		String userEmail = request.getParameter("userEmail");
		String userPhone = request.getParameter("userPhone");
		// 创建用户实体类 对用户进行封装
		User user = new User();
		user.setUserAccount(userAccount);
		user.setUserPwd(userPwd);
		user.setAccountBalance(Integer.parseInt(accountBalance));
		UserDao userdao = new UserDaoImpl();// 创建用户接口
		
		
		// 创建用户信息对象 并对信息进行封装
		UserInfo userinfo = new UserInfo();
		
		userinfo.setUserAccount(userAccount);
		userinfo.setUserName(userName);
		userinfo.setUserGender(userGender);
		userinfo.setUserIdentityMark(userIdentityMark);
		userinfo.setUserEmail(userEmail);
		userinfo.setUserPhone(userPhone);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			Date birthDate  =  sdf.parse(userBirthDate);  // 把字符串转换成 日期类型
			userinfo.setUserBirthDate(birthDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		UserInfoDao infodao = new UserInfoDaoImpl();  // 创建用户信息接口
		// 如果等于1表示添加 用户
		if(Integer.parseInt(isEdit) == 1){ 
			if(userdao.addUser(user)<= 0){  // 表示用户添加失败后 跳转到指定页面
				response.sendRedirect(request.getContextPath()+"/getAllUserInfo.do");
			}else{// 表示用户添加成功
				if(infodao.addUserInfo(userinfo)>0){// 表示用户信息添加成功
					response.sendRedirect(request.getContextPath()+"/getAllUserInfo.do");
				}
			}
			
		}else{  // 表示修改用户
			if(userdao.updateUser(user)>0){// 表示用户修改成功
				if(infodao.updateUserInfo(userinfo)>0){//表示用户信息修改成功
					response.sendRedirect(request.getContextPath()+"/getAllUserInfo.do");
				}
			}else{
				response.sendRedirect(request.getContextPath()+"/getAllUserInfo.do");
			}
		}
		
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
