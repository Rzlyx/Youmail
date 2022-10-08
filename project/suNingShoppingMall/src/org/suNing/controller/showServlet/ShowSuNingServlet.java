package org.suNing.controller.showServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/showSuNing.do")

/**
 * 进入商品首页的 servlet
 * @author ASUS
 *
 */
public class ShowSuNingServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 这是吧 查看所有轮播图片给内嵌过来
		request.getRequestDispatcher("/getHomePageImg.do").include(request, response);
		//这是吧所以的商品大类 给内嵌过来
		request.getRequestDispatcher("/getCommodityBroad.do").include(request, response);
		request.getRequestDispatcher("/SuNing.jsp").forward(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
