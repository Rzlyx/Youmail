package org.suNing.entity;


/**
 * 商品状态实体类
 * @author ASUS
 *
 */
public class CommodityStatus {
	
	
	private int id;
	
	private String commodityStatusType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 商品状态名称
	 * @return
	 */
	public String getCommodityStatusType() {
		return commodityStatusType;
	}
	/**
	 * 商品状态名称
	 * @return
	 */
	public void setCommodityStatusType(String commodityStatusType) {
		this.commodityStatusType = commodityStatusType;
	}

}
