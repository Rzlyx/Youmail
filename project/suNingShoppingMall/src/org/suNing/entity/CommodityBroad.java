package org.suNing.entity;

/**
 * 商品大类
 * @author ASUS
 *
 */
public class CommodityBroad {
	
	private int id;
	
	private String commodityBroadName;
	
	private String commodityRemark ;
	
	private String commodityBroadImage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 大类
	 * @return
	 */
	public String getCommodityBroadName() {
		return commodityBroadName;
	}

	/**
	 * 大类名称
	 * @return
	 */
	public void setCommodityBroadName(String commodityBroadName) {
		this.commodityBroadName = commodityBroadName;
	}
	/**
	 * 大类备注
	 * @return
	 */
	public String getCommodityRemark() {
		return commodityRemark;
	}
	/**
	 * 大类备注
	 * @return
	 */
	public void setCommodityRemark(String commodityRemark) {
		this.commodityRemark = commodityRemark;
	}
	/**
	 * 大类图片
	 * @return
	 */
	public String getCommodityBroadImage() {
		return commodityBroadImage;
	}
	/**
	 * 大类图片
	 * @return
	 */
	public void setCommodityBroadImage(String commodityBroadImage) {
		this.commodityBroadImage = commodityBroadImage;
	}
	
	

}
