package org.suNing.entity;

/**
 * 商品小类别 实体类
 * @author ASUS
 *
 */
public class CommodityLittleType {
	
	private int id;
	
	private String cltName;
	
	private int commodityBroadId;
	
	
	private CommodityBroad commodityBroad = new CommodityBroad();
	/**
	 * 商品小类别id
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * 商品小类别id
	 * @return
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 商品小类别名称
	 * @return
	 */
	public String getCltName() {
		return cltName;
	}
	/**
	 * 商品小类别名称
	 * @return
	 */
	public void setCltName(String cltName) {
		this.cltName = cltName;
	}
	/**
	 * 商品小类别引用的大类对象
	 * @return
	 */
	public CommodityBroad getCommodityBroad() {
		return commodityBroad;
	}
	/**
	 * 商品小类别引用的大类对象
	 * @return
	 */
	public void setCommodityBroad(CommodityBroad commodityBroad) {
		this.commodityBroad = commodityBroad;
	}
	/**
	 * 商品小类引用的大类id
	 * @param commodityBroadId
	 */
	public void setCommodityBroadId(int commodityBroadId) {
		this.commodityBroadId = commodityBroadId;
	}
	
	/**
	 * 商品小类引用的大类id
	 * @param commodityBroadId
	 */
	public int getCommodityBroadId() {
		return commodityBroadId;
	}

}
