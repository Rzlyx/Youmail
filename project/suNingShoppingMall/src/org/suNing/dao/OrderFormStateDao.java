package org.suNing.dao;

import java.util.List;

import org.suNing.entity.OrderFormState;

/**
 * 订单状态的接口
 * @author ASUS
 *
 */
public interface OrderFormStateDao {
	
	/**
	 * 获取所有订单状态
	 * @return
	 */
	public List<OrderFormState> getAllOrderFormState();

}
