package org.suNing.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.HomePageImgDao;
import org.suNing.dao.impl.HomePageImgDaoImpl;
import org.suNing.entity.HomePageImg;

@WebServlet(urlPatterns="/getHomePageImg.do")

/**
 * 这是获取 所有首页轮播图片的servlet 可以被首页和管理页面同时调用
 * @author ASUS
 *
 */
public class SelectAllHomePageImgServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HomePageImgDao hpd = new HomePageImgDaoImpl();
		List<HomePageImg> home = hpd.getAllHomePageImg();
		if(home != null){
			// 返回 首页图片
			request.setAttribute("home", home);
			
		}
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
