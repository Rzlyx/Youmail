package org.suNing.entity;


/**
 * 商品详情信息实体类
 * @author ASUS
 *
 */
public class CommodityInfo {
	
	
	private int id;
	
	private String commodityProducing;
	
	private String productType;
	
	private String commodityRemark;
	
	private int commodityId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 商品的原产地
	 * @return
	 */
	public String getCommodityProducing() {
		return commodityProducing;
	}
	/**
	 * 商品的原产地
	 * @param commodityProducing
	 */
	public void setCommodityProducing(String commodityProducing) {
		this.commodityProducing = commodityProducing;
	}
	/**
	 * 产品规格类型
	 * @return
	 */
	public String getProductType() {
		return productType;
	}
	/**
	 * 产品规格类型
	 * @param productType
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}
	/**
	 * 产品具体信息
	 * @return
	 */
	public String getCommodityRemark() {
		return commodityRemark;
	}
	/**
	 * 产品具体信息
	 * @param commodityRemark
	 */
	public void setCommodityRemark(String commodityRemark) {
		this.commodityRemark = commodityRemark;
	}

	public int getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}
	
	

}
