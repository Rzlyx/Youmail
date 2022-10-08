package org.suNing.controller.userServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.UserOrderFormDao;
import org.suNing.dao.impl.UserOrderFormDaoImpl;

@WebServlet(urlPatterns="/orderFormStateUpdate.do")


/**
 * 用来修改 订单状态的servelt
 * @author ASUS
 *
 */
public class UserOrderFormStateUpdateServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String stateId = request.getParameter("stateId");
		String orderFormId = request.getParameter("orderFormId");
		
		UserOrderFormDao uofd = new UserOrderFormDaoImpl();
		int result = uofd.updateUserOrderState(Integer.parseInt(stateId),Integer.parseInt(orderFormId));
		if(result > 0 ){
			response.sendRedirect(request.getContextPath()+"/showUserOrderFormInfo.do");
		}else{
			response.sendRedirect(request.getContextPath()+"/showUserOrderFormInfo.do");
		}
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
