package org.suNing.controller.userServlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.suNing.dao.CommodityDao;
import org.suNing.dao.ShoppingVehicleDao;
import org.suNing.dao.UserDao;
import org.suNing.dao.impl.CommodityDaoImpl;
import org.suNing.dao.impl.ShoppingVehicleDaoImpl;
import org.suNing.dao.impl.UserDaoImpl;
import org.suNing.dao.impl.UserOrderFormDaoImpl;
import org.suNing.entity.UserOrderForm;
import org.suNing.utli.SuNingUtil;

import net.sf.json.JSONArray;

@WebServlet(urlPatterns="/SettlementShoppingCartCommodity.do")

/**
 *  这是循环结算购物车商品servelt
 * @author ASUS
 *
 */

public class ForSettlementShoppingCartCommodityServelt extends HttpServlet {

	private UserOrderFormDaoImpl uofdao = new UserOrderFormDaoImpl(); // 用户订单接口
	
	private UserDao userdao = new UserDaoImpl();// 用户接口
	
	private CommodityDao cmdDao = new CommodityDaoImpl(); // 商品接口
	
	private ShoppingVehicleDao shve = new ShoppingVehicleDaoImpl(); // 购物车接口
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		
		String userAccount = request.getParameter("userAcc");  // 获取用户账户
		String userYueMouney = request.getParameter("shengyuMoney"); // 账户余额
		
		int userRes = excuteUpdateUserBalance(userAccount,userYueMouney); // 调用修改用户余额函数
		if(userRes <= 0){
			System.out.println("用户余额修改失败");
			return;
		}
		// 获取包括商品id 购买的商品数量 和购物车的id  json对象
		String orderFormObj = request.getParameter("orderFormObj");
		
		JSONArray jsonArray = JSONArray.fromObject(orderFormObj);// 把购物车里的商品信息转换成json对象
		
		
		for (int i = 0; i < jsonArray.size(); i++) {// 循环购买的购物车商品
			
			Map ts=(Map)jsonArray.get(i);  // 解析json对象
			String shoppingId = (String)ts.get("shoopId");// 购物车商品id
			String commodityId = (String)ts.get("commodityId"); // 商品id
			String commodityCount = (String)ts.get("commodityCount");// 购买的数量
			
			 // 进入执行更改商品、用户、购物车函数
			int result = excuteUpdate(userAccount,shoppingId,commodityId, commodityCount);
			if(result <= 0){
				return;
			}
			
		}
		response.sendRedirect(request.getContextPath()+"/showUserOrderFormInfo.do");
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}
	
	
	/**
	 * 执行更新 商品的属性、用户的余额、购物车的商品
	 * @param userAccount  用户账户
	 * @param shoppingId   购物车id
	 * @param commodityId  商品id
	 * @param commodityCount  购买的商品数量
	 * @return
	 */
	private int excuteUpdate(String userAccount,String shoppingId,String commodityId, String commodityCount){
		
		int result = excuteAddOrderForm(userAccount,commodityId,commodityCount);  // 调用添加订单函数
		
		if(result > 0){ // 表示订单添加成功
			result = excuteDeleteShoppingCart(shoppingId) ;// 调用删除购物车商品函数
			if(result >  0){// 判断是否删除成功
				result = excuteUpdateCommodityAttribute(commodityId,commodityCount); // 调用修改商品属性的函数
				if(result > 0){// 是否修改ok
					
				}else{
					System.out.println("修改商品失败");
					return result;
				}
			}else{
				System.out.println("删除购物车商品失败");
				return result;
			}
		}else{
			System.out.println("添加订单失败");
			return result;
		}
		
		return result;
	}
	
	
	
	
	/**
	 * 修改用户账户余额
	 * @param userAccount 账户
	 * @param userYueMouney  剩余余额
	 * @return
	 */
	private int excuteUpdateUserBalance(String userAccount ,String userYueMouney){
		
		int resUser1 = userdao.updateAccountBalance(Integer.parseInt(userYueMouney), userAccount);
		
		return resUser1;
		
	}
	
	
	
	/**
	 * 删除商品购物车商品函数
	 * @param shoppingId
	 * @return
	 */
	private int excuteDeleteShoppingCart(String shoppingId){
		
		
		int result = shve.deleteShoppingCommodity(Integer.parseInt(shoppingId));
		return result;
	}
	
	/**
	 * 执行添加用户订单函数
	 * @param userAccount  用户账户
	 * @param commodityId  商品id
	 * @param commodityCount  购买商品数量数量
	 * @return  是否执行成功
	 */
	private int excuteAddOrderForm(String userAccount,String commodityId,String commodityCount ){
		String orederFormNumber = SuNingUtil.generateRandom(); // 获取自动生成的订单编号
		
		UserOrderForm uof = new UserOrderForm();//创建订单对象 并对其属性进行封装
		uof.setUserAccount(userAccount);
		uof.setCommodityId(Integer.parseInt(commodityId));
		uof.setAddCommodityCount(Integer.parseInt(commodityCount));
		uof.setOrderFormNumber(orederFormNumber);
		int result = uofdao.addOrderForm2(uof);  // 执行添加添加用户订单
		
		return result;
	}
	
	/**
	 * 执行修改商品的 销量 和商品的库存 函数
	 * @param commodityId  商品id
	 * @param purchaseCount  购买的商品数量
	 * @return
	 */
	private int excuteUpdateCommodityAttribute(String commodityId,String purchaseCount){
		
		int comId = Integer.parseInt(commodityId);// 商品id
		int commCount = Integer.parseInt(purchaseCount);//购买商品数量
		
		int resCommodity2 = cmdDao.updateCommodityCount(commCount,comId);// 修改商品数量
		if(resCommodity2 <= 0){
			System.out.println("库存更改失败");
		}
		// 修改商品的销量
		resCommodity2= cmdDao.udpateCommoditySalesVolume(commCount, comId);
		if(resCommodity2 <= 0){
			System.out.println("销量更改失败");
		}
		
		return resCommodity2;
		
	}
	

}
