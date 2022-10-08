package org.suNing.entity;


/**
 * 订单状态实体类
 * @author ASUS
 *
 */
public class OrderFormState {
	
	private int id;
	
	private String OrderFormType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderFormType() {
		return OrderFormType;
	}

	public void setOrderFormType(String orderFormType) {
		OrderFormType = orderFormType;
	}
	
	
	

}
