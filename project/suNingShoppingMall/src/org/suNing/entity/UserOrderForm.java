package org.suNing.entity;

import java.sql.Timestamp;

/**
 * 订单实体类
 * @author ASUS
 *
 */
public class UserOrderForm {
	
	
	private int id;
	
	
	private String orderFormNumber;

	private String userAccount;
	
	private User user = new User();
	
	private int commodityId;
	
	private Commodity commodity = new Commodity();
	
	private int orderFormStateId;
	
	private OrderFormState ofs = new OrderFormState();
	
	private int addCommodityCount;
	
	private Timestamp nextOneTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 订单编号
	 * @return
	 */
	public String getOrderFormNumber() {
		return orderFormNumber;
	}
	
	/**
	 * 订单编号
	 * @param orderFormNumber
	 */
	public void setOrderFormNumber(String orderFormNumber) {
		this.orderFormNumber = orderFormNumber;
	}
	/**
	 * 用户账户
	 * @return
	 */
	public String getUserAccount() {
		return userAccount;
	}
	/**
	 * 用户账户
	 * @param userAccount
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	/**
	 * 用户对象
	 * @return
	 */
	public User getUser() {
		return user;
	}
	/**
	 * 用户对象
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 商品id
	 * @return
	 */
	public int getCommodityId() {
		return commodityId;
	}
	/**
	 * 商品id
	 * @param commodityId
	 */
	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}
	/**
	 * 商品对象
	 * @return
	 */
	public Commodity getCommodity() {
		return commodity;
	}
	/**
	 * 商品对象
	 * @param commodity
	 */
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
	/**
	 * 商品状态id
	 * @return
	 */
	public int getOrderFormStateId() {
		return orderFormStateId;
	}
	/**
	 * 商品状态id
	 * @param orderFormStateId
	 */
	public void setOrderFormStateId(int orderFormStateId) {
		this.orderFormStateId = orderFormStateId;
	}
	/**
	 * 商品状态对象
	 * @return
	 */
	public OrderFormState getOfs() {
		return ofs;
	}
	/**
	 * 商品状态对象
	 * @param ofs
	 */
	public void setOfs(OrderFormState ofs) {
		this.ofs = ofs;
	}
	/**
	 * 商品状态对象
	 * @return
	 */
	public int getAddCommodityCount() {
		return addCommodityCount;
	}
	/**
	 * 下单数量
	 * @param addCommodityCount
	 */
	public void setAddCommodityCount(int addCommodityCount) {
		this.addCommodityCount = addCommodityCount;
	}
	/**
	 * 下单数量
	 * @return
	 */
	public Timestamp getNextOneTime() {
		return nextOneTime;
	}
	/**
	 * 下单时间
	 * @param nextOneTime
	 */
	public void setNextOneTime( Timestamp nextOneTime) {
		this.nextOneTime = nextOneTime;
	}
}
