package org.suNing.controller.ManagerServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.UpCityManagerDao;
import org.suNing.dao.impl.UpCityManagerDaoImpl;
import org.suNing.entity.UpCityManager;

@WebServlet(urlPatterns="/ManagerLogin.do")

/**
 * 这是管理员登陆时验证的servelt
 * @author ASUS
 *
 */
public class ManagerLogin extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String account = request.getParameter("account");
			String pwd = request.getParameter("pwd");
			
			UpCityManagerDao manager = new UpCityManagerDaoImpl();
			UpCityManager man = manager.getManager(account, pwd);
			if(man != null){
				request.getSession().removeAttribute("err");
				request.getSession().setAttribute("manager", man);
				response.sendRedirect(request.getContextPath()+"/manager/ManagerBackStagePage.jsp");
			}else{
				request.getSession().setAttribute("err", "<script>alert('账户或密码错误')</script>");
				response.sendRedirect(request.getContextPath()+"/ManagerLogin.jsp");
			}
			
			
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
		
	}

}
