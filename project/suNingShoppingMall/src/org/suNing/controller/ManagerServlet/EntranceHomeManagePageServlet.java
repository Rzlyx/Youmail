package org.suNing.controller.ManagerServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/entranceHome.do")

/**
 * entrance > 进入的意思
 * 这是进入 首页轮播图片管理页面的servlet
 * @author ASUS
 *
 */
public class EntranceHomeManagePageServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/getHomePageImg.do").include(request, response);
		// 进入首页图片管理页面
		request.getRequestDispatcher("/manager/managePages/HomePageManage.jsp").forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
