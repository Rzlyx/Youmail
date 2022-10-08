package org.suNing.utli;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PageTag extends SimpleTagSupport {

	
	private String formId;
	
	private PageBean pageBean;
	
	
	public String getFormId() {
		return formId;
	}


	public void setFormId(String formId) {
		this.formId = formId;
	}


	public PageBean getPageBean() {
		return pageBean;
	}


	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}


	@Override
	public void doTag() throws JspException, IOException {
		
		
		
		StringBuffer sb = new StringBuffer();
		
		
		sb.append("<script >");
		sb.append("function pageSet(pageNo){"
				+ "document.getElementById('pagenum').value=pageNo;"
				+ "document.getElementById('"+formId+"').submit();"
				+ "}");
		sb.append("</script>");
		
		
		sb.append("<div id='page_style'>");
		sb.append("<a href='#' onclick='pageSet(1); return false'>首页</a>");
		sb.append("<a href='#' onclick='pageSet("+pageBean.getPrevPage()+"); return false'>上一页</a>");
		
		for (int i = 1; i < pageBean.getTotalPages(); i++) {
			
				if(pageBean.getPageNum() == i){
					sb.append("<a href='#'  onclick='return false' style=' color:red'>"+i+"</a>");
				}else{
					sb.append("<a href='#'  onclick='pageSet("+i+"); return false'>"+i+"</a>");
				}
			
		}
		sb.append("<a href='#'  onclick='pageSet("+pageBean.getNextPage()+");  return false'>下一页</a>");
		sb.append("<a href='#'  onclick=' return false'>共有"+pageBean.getTotalCount()+"条数据</a>");
		sb.append("<a href='#'  onclick='pageSet("+pageBean.getTotalPages()+"); return false'>末页</a>");
		sb.append("<input type='hidden' name='pageNum'  id='pagenum'  value='1' >");
		sb.append("</div>");
		
	    JspWriter out = this.getJspContext().getOut();
	    out.print(sb.toString());
		
		
		
		
		
		
	}

}
