package org.suNing.controller.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.suNing.dao.UserDao;
import org.suNing.dao.UserInfoDao;
import org.suNing.dao.impl.UserDaoImpl;
import org.suNing.dao.impl.UserInfoDaoImpl;
import org.suNing.entity.User;
import org.suNing.entity.UserInfo;

@WebServlet(urlPatterns="/checkUserLogIn.do")

/**
 * 这是用户登录时验证的servlet  和 验证密码是用的
 * @author ASUS
 *
 */
public class UserLogInCheckServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println(System.getProperty("java.library.path"));
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String checkLogInorpwd = request.getParameter("check"); // 用来判断 是登陆呢还是 验证密码用的
		String userAccount = request.getParameter("userAccount");
		String userPwd = request.getParameter("userPwd");
		//创建用户对象 堆属性进行封装
		User user = new User();
		user.setUserAccount(userAccount);
		user.setUserPwd(userPwd);
		//创建用户接口
		UserDao userdao = new UserDaoImpl();
		User user2 = userdao.pwdSelectAccount(user);
		// 创建用户信息接口
		UserInfoDao infodao = new UserInfoDaoImpl();
		int i = 0; // 用来记录 是否执行查询成功
		
		if("1".equals(checkLogInorpwd)){  // 如果 该变量是1的话就表示是用户登录来的
				if(user2 != null){// 表示登陆成功
					// 登陆成功后 通过登陆账户获取用户的整个信息 并放到session中
					UserInfo userInfo = infodao.accountGetUserInfo(userAccount);
					HttpSession session = request.getSession();
					session.setAttribute("userInfo", userInfo);
					i = 1;
					out.print(i);// 返回给页面一个1表示该用户登陆成
				}else{
					out.print(i); // 返回给页面一个0表示该用户登失败
				}
		}else{  // 表示是用来验证密码的

			if(user2 != null)
				out.print(i=1);
			else
				out.print(i);
		}
		
		
		
		
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
