package org.suNing.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.suNing.utli.JsonDateValueProcessor;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@WebServlet(urlPatterns="/accountGetUserInfo.do")
public class SelectUserInfoServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		String userAccount = request.getParameter("userAccount");
		
		UserInfoDao infodao = new UserInfoDaoImpl();
		// 这是通过账户获取用户信息
		UserInfo userInfo = infodao.accountGetUserInfo(userAccount);
		JsonConfig jsonConfig = new JsonConfig(); // 创建json对象的配置对象
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());  // 传入配置对象的参数
		if(userInfo != null){
			JSONObject jso = JSONObject.fromObject(userInfo,jsonConfig);
			PrintWriter out = response.getWriter();
			
			out.print(jso.toString());
		}
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}
