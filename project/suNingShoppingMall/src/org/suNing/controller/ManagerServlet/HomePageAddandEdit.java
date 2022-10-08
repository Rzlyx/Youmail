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
import org.suNing.entity.HomePageImg;
import org.suNing.utli.SuNingUtil;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

@WebServlet(urlPatterns="/addAndeditHome.do")

public class HomePageAddandEdit extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		SmartUpload smartUpload = new SmartUpload();
//		smartUpload.setCharset("utf-8");
		smartUpload.initialize(this, request,response);
		String newFileImgName = "";// 图片名称
		try {
			smartUpload.upload();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		Files files = smartUpload.getFiles();
		for (int i = 0; i < files.getCount(); i++) {
			File file = files.getFile(i);
			if(! file.isMissing()){
				
				String extension = file.getFileExt();
				
				newFileImgName = "images/commodityHomePage/"+SuNingUtil.generateFileName(extension);
				try {
					file.saveAs(newFileImgName, SmartUpload.SAVE_VIRTUAL);
				} catch (SmartUploadException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		Request req = smartUpload.getRequest();
		
		HomePageImg home = new HomePageImg();
		home.setImg(newFileImgName);
		HomePageImgDao homedao = new HomePageImgDaoImpl();
		if(homedao.addHomePageImg(home)>0){
			response.sendRedirect(request.getContextPath()+"/entranceHome.do");
		}else{
			response.sendRedirect(request.getContextPath()+"/entranceHome.do");
		}
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
