package org.suNing.controller.showServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.suNing.dao.CommodityDao;
import org.suNing.dao.impl.CommodityDaoImpl;
import org.suNing.entity.Commodity;

public class BroadTypeIdGetCommodityTag extends SimpleTagSupport {

	private int  braodTypeId;
	
	

	public int getBraodTypeId() {
		return braodTypeId;
	}

	public void setBraodTypeId(int braodTypeId) {
		this.braodTypeId = braodTypeId;
	}

	@Override
	public void doTag() throws JspException, IOException {
		
		
		StringBuffer sb = new StringBuffer();
		
		CommodityDao comdao = new CommodityDaoImpl();
		List<Commodity> commoditys = comdao.passCommodityBroadGet(12,braodTypeId);
		
		if(commoditys != null){
			for (Commodity commodity : commoditys) {
				sb.append("<li>");
				sb.append("<a href='getCommodityInfo.do?comId="+commodity.getId()+"&braodTypeId="+braodTypeId+"' target='blank'><img src='"+commodity.getCommodityImage()+"' alt='商品'></a>");
				sb.append("<p>"+commodity.getCommodityName()+"</p>");
				sb.append("<span>"+commodity.getCommodityPrice()+"</span>");
				sb.append("</li>");
			}
		}
		
		
		 JspWriter out = this.getJspContext().getOut();
		out.print(sb.toString());
		
	}
	
	
}
