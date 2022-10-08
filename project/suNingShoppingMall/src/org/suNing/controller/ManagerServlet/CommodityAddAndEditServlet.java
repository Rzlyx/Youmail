package org.suNing.controller.ManagerServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.CommodityDao;
import org.suNing.dao.CommodityInfoDao;
import org.suNing.dao.impl.CommodityDaoImpl;
import org.suNing.dao.impl.CommodityInfoDaoImpl;
import org.suNing.entity.Commodity;
import org.suNing.entity.CommodityInfo;
import org.suNing.utli.SuNingUtil;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

@WebServlet(urlPatterns="/addAndEditCommodity.do")

/**
 * 商品和商品信息的添加和修改servlet
 * @author ASUS
 *
 */
public class CommodityAddAndEditServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 创建商品图片的对象
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
				
				newFileImgName = "images/commodityImgs/"+SuNingUtil.generateFileName(extension);
				try {
					file.saveAs(newFileImgName, SmartUpload.SAVE_VIRTUAL);
				} catch (SmartUploadException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		
		Request req = smartUpload.getRequest();
		//获取商品的request请求 
		String commodityId = req.getParameter("commodityId");// 用该属性来判断是添加还是修改
		String commodityName = req.getParameter("commodityName2"); 
		String commodityBrandName = req.getParameter("commodityBrandName");
		String commodityTypeId = req.getParameter("commodityType");
		String commodityStatusTypeId = req.getParameter("commodityStatus");
		String commodityPrice = req.getParameter("commodityPrice");
		String commodityInventory = req.getParameter("commodityInventory");
		String commodityImage = req.getParameter("commodityImage");
		String pageNum2 = req.getParameter("pageNum2"); //  就只有当修改的时候把当前页给发过去
		// 获取商品信息的request请求
 		String commodityProducing = req.getParameter("commodityProducing");
		String productType = req.getParameter("productType");
		String commodityRemark = req.getParameter("commodityRemark");
		
		//创建商品对象
		Commodity commodity = new Commodity();
		//对商品进行封装
		commodity.setCommodityName(commodityName);
		//commodity.setCommodityImage(commodityImage);
		commodity.setCommodityBrandName(commodityBrandName);
		commodity.setCommodityPrice(Integer.parseInt(commodityPrice));
		commodity.setCommodityTypeId(Integer.parseInt(commodityTypeId));
		commodity.setCommodityInventory(Integer.parseInt(commodityInventory));
		commodity.setCommodityStatusTypeId(Integer.parseInt(commodityStatusTypeId));
		//创建商品信息对象 
		CommodityInfo commodityInfo = new CommodityInfo();
		//对商品信息进行封装
 		commodityInfo.setCommodityProducing(commodityProducing);
		commodityInfo.setProductType(productType);
		commodityInfo.setCommodityRemark(commodityRemark);
		
		int result = 0;  // 表示添加或修改的结果
		//创建商品对象
		CommodityDao comdao = new CommodityDaoImpl();
		//创建商品信息对象
		CommodityInfoDao cominfodao = new CommodityInfoDaoImpl();
		if(commodityId.equals("null")){  // 表示添加
			commodity.setCommodityImage(newFileImgName);
			result = comdao.addCommodity(commodity);
			if(result > 0){ // 如果大于0的话表示添加商品成功
				//查询出刚添加成功的商品id获取到后就添加到商品信息的属性中进行添加商品的信息
				int maxid = comdao.getMaxCommodityId();
				commodityInfo.setCommodityId(maxid);
				//拿到刚添加的商品id后就进行添加商品信息
				if(cominfodao.addCommodityInfo(commodityInfo)>0){// 表示商品信息添加成功
						response.sendRedirect(request.getContextPath()+"/pageSelectCommodity.do");
				}else{// 表示商品信息添加失败
					
				}
			}else{//表示商品添加失败
				
			}
		}else{
			commodity.setId(Integer.parseInt(commodityId));
			commodityInfo.setCommodityId(Integer.parseInt(commodityId));
			if(newFileImgName == ""){ // 如果是空了 就不是没有修改图片
				commodity.setCommodityImage(commodityImage);
			}else{ // 表示修改了图片
				commodity.setCommodityImage(newFileImgName);
			}
			result = comdao.updateCommodity(commodity);
			if(result > 0){ // 表示修改商品成功
				result = cominfodao.updateCommodityInfo(commodityInfo);
				if(result > 0){  // 表示修改商品信息成功
					// 当修该成功后把 修改的时候的当前页也给传过去
					request.setAttribute("pageNum2", pageNum2);
					//response.sendRedirect(request.getContextPath()+"/pageSelectCommodity.do");
					request.getRequestDispatcher("pageSelectCommodity.do").forward(request, response);
				}else{ // 表示修改商品信息失败
					
				}
			}else{//表示修改商品失败
				
			}
		}
		
		
		
		
		
		
		
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
			
	}

}
