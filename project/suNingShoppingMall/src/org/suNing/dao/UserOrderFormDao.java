package org.suNing.dao;

import java.util.List;

import org.suNing.entity.UserOrderForm;

public interface UserOrderFormDao {
	
	/**
	 * 添加订单
	 * @param uof
	 * @return
	 */
	public int addOrderForm(UserOrderForm uof);
	
	/**
	 * 查看所有的订单  并且分页
	 * @pageNum  当前页
	 * @userAccount  通过用户账户查
	 * @orderFormStatus 通过订单状态查
	 * @return
	 */
	public List<UserOrderForm> getAllUserOrderForm(int pageNum, String userAccount,int orderFormStatus);
	
	/**
	 * 这是带条件查询的订单数量
	 * @userAccount  通过用户账户查
	 * @orderFormStatus 通过订单状态查
	 * @return
	 */
	public int getConditionUserOrderForm(String userAccount,int orderFormStatus);
	
	
	/**
	 * 删除用户订单未付款订单
	 * @param id
	 * @return
	 */
	public int deleteOrderForm(int id);
	
	/**
	 * 修改用户的 订单状态
	 * @param orderFomrStateId  状态id
	 * @param id  订单id
	 * @return
	 */
	public int updateUserOrderState(int orderFomrStateId,int OrderFormId);
	
	/**
	 * 这是获取 刚添加完的订单
	 * 用来给 商品信息页面 购买商品时用的
	 * @return
	 */
	public int getNewAddOrderForm();
	
	
	
	

}
