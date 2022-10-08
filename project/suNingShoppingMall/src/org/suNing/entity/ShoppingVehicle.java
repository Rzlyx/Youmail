package org.suNing.entity;

import java.util.Date;

/**
 * 商品购物车实体类
 * @author ASUS
 *
 */
public class ShoppingVehicle {
	
	
	private int id;
	
	private String userAccount ;
	
	private User user = new User();
	
	private int commodityId;
	
	private Commodity commodity = new Commodity();
	
	private int commodityCount;
	
	private Date addShoppingTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	 * 用户账户对象
	 * @return
	 */
	public User getUser() {
		return user;
	}
	/**
	 * 用户账户对象
	 * @param userInfo
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
	 * 商品数量
	 * @return
	 */
	public int getCommodityCount() {
		return commodityCount;
	}
	/**
	 * 商品数量
	 * @param commodityCount
	 */
	public void setCommodityCount(int commodityCount) {
		this.commodityCount = commodityCount;
	}
	/**
	 * 加入购物车时间
	 * @return
	 */
	public Date getAddShoppingTime() {
		return addShoppingTime;
	}
	/**
	 * 加入购物车时间
	 * @param addShoppingTime
	 */
	public void setAddShoppingTime(Date addShoppingTime) {
		this.addShoppingTime = addShoppingTime;
	}

}
