package org.suNing.controller.ManagerServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.HomePageImgDao;
import org.suNing.dao.impl.HomePageImgDaoImpl;

@WebServlet(urlPatterns="/deleteHome.do")

public class HomePageDelete extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String homeId = request.getParameter("id");
		
		HomePageImgDao homedao = new HomePageImgDaoImpl();
		int result = homedao.deleteHomePageImg(Integer.parseInt(homeId));
		if(result > 0){
			response.sendRedirect(request.getContextPath()+"/entranceHome.do");
		}else{
			response.sendRedirect(request.getContextPath()+"/entranceHome.do");
		}
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
