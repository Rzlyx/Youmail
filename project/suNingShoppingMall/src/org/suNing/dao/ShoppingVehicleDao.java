package org.suNing.dao;

import java.util.List;

import org.suNing.entity.ShoppingVehicle;

/**
 *  购物车接口
 * @author ASUS
 *
 */
public interface ShoppingVehicleDao {
	
	/**
	 * 加入购物车 
	 * @param spve
	 * @return
	 */
	public int joinShoppingVehicle(ShoppingVehicle spve);
	
	/**
	 * 这是通过用户账户获取用户购物车里的商品信息
	 * @param account
	 * @return
	 */
	public List<ShoppingVehicle> accountGetShoppingCommodity(String account);
	
	/**
	 * 通过购物车id 删除购物车商品
	 * @param shoopingId
	 * @return
	 */
	public int deleteShoppingCommodity(int shoopingId);

}
