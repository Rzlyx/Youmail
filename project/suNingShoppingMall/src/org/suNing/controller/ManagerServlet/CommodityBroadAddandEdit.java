package org.suNing.controller.ManagerServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.CommodityBroadDao;
import org.suNing.dao.impl.CommodityBroadDaoImpl;
import org.suNing.entity.CommodityBroad;
import org.suNing.utli.SuNingUtil;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

@WebServlet(urlPatterns="/addAndEditCommodityBroad.do")
/**
 * 执行 添加和修改商品大类的servlet
 * @author ASUS
 *
 */
public class CommodityBroadAddandEdit extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newimgName = "";// 上传图片的名称
		SmartUpload smarUpload = new SmartUpload();
//		smarUpload.setCharset("utf-8");
		smarUpload.initialize(this, request, response);
		try {
			smarUpload.upload();
		} catch (SmartUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Files files = smarUpload.getFiles();
		File file = files.getFile(0);
		if(!file.isMissing()){
			String extension = file.getFileExt();
			newimgName = "images/commodityBroad/"+SuNingUtil.generateFileName(extension);
			try {
				file.saveAs(newimgName,smarUpload.SAVE_VIRTUAL);
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		Request req = smarUpload.getRequest();
		CommodityBroadDao comdao = new CommodityBroadDaoImpl();// 创建dao层对象
		CommodityBroad broadType = new CommodityBroad();  // 创建实体类对象
		int result = 0;
		String broadId = req.getParameter("hid_id");
		String broadName = req.getParameter("commName");
		String broadImg = req.getParameter("hid_img");
		String broadRemark = req.getParameter("commRemark");
		
		
		
		
		broadType.setCommodityBroadName(broadName);
		broadType.setCommodityRemark(broadRemark);
		// 如果broadid 是null 就表示是要添加商品大类
		if(broadId.equals("null")){
			broadType.setCommodityBroadImage(newimgName);
			result = comdao.addCommodityBroad(broadType);
			if(result > 0){
				response.sendRedirect(request.getContextPath()+"/entranceBroadType.do");
			}else{//添加失败了也跳过去
				response.sendRedirect(request.getContextPath()+"/entranceBroadType.do");
			}
		}else{//执行 修改
			if("".equals(newimgName)){ // 如果没有修改图片 那newimgName 就是等于空 就把他的原有图片路径给附上值
				broadType.setCommodityBroadImage(broadImg);
			}else{// 如果newimgName 不等于空那就表示修改了图片 就不新的图片路径给赋上值
				broadType.setCommodityBroadImage(newimgName);
			}
			broadType.setId(Integer.parseInt(broadId));
			result = comdao.updateCommodityBroad(broadType);
			if(result >0){
				response.sendRedirect(request.getContextPath()+"/entranceBroadType.do");
			}else{
				response.sendRedirect(request.getContextPath()+"/entranceBroadType.do");
			}
			
			
			
		}
		
		
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
