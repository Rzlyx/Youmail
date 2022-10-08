package org.suNing.controller.showServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.suNing.dao.CommodityDao;
import org.suNing.dao.CommodityInfoDao;
import org.suNing.dao.UserDao;
import org.suNing.dao.impl.CommodityDaoImpl;
import org.suNing.dao.impl.CommodityInfoDaoImpl;
import org.suNing.dao.impl.UserDaoImpl;
import org.suNing.entity.Commodity;
import org.suNing.entity.CommodityInfo;
import org.suNing.entity.User;
import org.suNing.entity.UserInfo;

@WebServlet(urlPatterns="/getCommodityInfo.do")

/**
 * 这是当点击商品时 跳转到这个servlet
 * @author ASUS
 *
 */
public class ShowCommodityInfoServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 这是获取商品id
		String comId = request.getParameter("comId");
		//这是获取要给用户推荐的商品大类的id
		String braodTypeId = request.getParameter("braodTypeId");
		//创建商品的接口实现类
		CommodityDao comdao = new CommodityDaoImpl();
		// 创建商品信息的接口实现类
		CommodityInfoDao cominfodao = new CommodityInfoDaoImpl();
		// 这是获取你查看的商品 对象
		Commodity commodity = comdao.idGetCommodity(Integer.parseInt(comId));
		// 这个是推荐的可能喜欢的商品集合
		List<Commodity> comlist = comdao.passCommodityBroadGet(4, Integer.parseInt(braodTypeId==""?"0":braodTypeId));
		//获取会话
		HttpSession session = request.getSession();
		// 获取session中储存的用户信息
		UserInfo sessionUserInfo = (UserInfo)session.getAttribute("userInfo");
		// 创建用户接口
		UserDao userdao = new UserDaoImpl();
		User user =null;
		if(sessionUserInfo != null && !"".equals(sessionUserInfo)){
			// 获取用户对象
			 user = userdao.accountGatUser(sessionUserInfo.getUserAccount());
		}
		
			if(commodity != null && comlist != null){
				// 获取到商品对象并且商品对象不为空的话就直接修改商品的浏览次数 
				if(comdao.updateCommodityBrowseCount(commodity.getCommodityBrowseCount(), Integer.parseInt(comId))>0)
					// 把浏览数量修改成功后在重新查询商品
					commodity = comdao.idGetCommodity(Integer.parseInt(comId));
					// 获取商品信息
					CommodityInfo cominfo = cominfodao.getCommodityInfo(Integer.parseInt(comId));
					// 商品
					request.setAttribute("commodity", commodity);
					// 这是获取 给用户推荐的商品 集合 一共显示4条数据 是通过商品大类id 获取的 也是通过这个id推荐的
					request.setAttribute("comlist", comlist);
					// 商品信息
					request.setAttribute("comInfo", cominfo);
					// 商品大类id
					request.setAttribute("braodTypeId", braodTypeId);
					
					// 用户
					request.setAttribute("user", user);
					request.getRequestDispatcher("CommodityInfoShow.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("CommodityInfoShow.jsp").forward(request, response);
			}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
		
	}

}
