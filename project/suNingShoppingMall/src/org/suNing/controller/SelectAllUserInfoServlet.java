package org.suNing.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.UserInfoDao;
import org.suNing.dao.impl.UserInfoDaoImpl;
import org.suNing.entity.UserInfo;
import org.suNing.utli.PageBean;

@WebServlet(urlPatterns="/getAllUserInfo.do")

/**
 * 这是分页查询所有的用户信息
 * @author ASUS
 *
 */
public class SelectAllUserInfoServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		PageBean pageBean = new PageBean();
		
		String pageNum = request.getParameter("pageNum");
		if(pageNum != null && !"".equals(pageNum)){
			pageBean.setPageNum(Integer.parseInt(pageNum));
		}
		pageBean.setPageSize(10);
		UserInfoDao usdao = new UserInfoDaoImpl();
		String userName = request.getParameter("userName");
		// 获取总条数 通过一个条件查询来获取总条数
		pageBean.setTotalCount(usdao.getUserInfoConditionCount(userName==null?"":userName));
		
		List<UserInfo> list = usdao.getAllUserInfopage(pageBean.getPageNum(), userName==null?"":userName);
		
		if(list != null){
			request.setAttribute("userName", userName);
			request.setAttribute("list", list);
			request.setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("manager/manageUserPage/UserInfoManage.jsp").forward(request, response);
			
		}else{
			request.getRequestDispatcher("UserInfoManage.jsp").forward(request, response);
		}
				
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
