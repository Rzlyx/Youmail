package org.suNing.entity;


/**
 * 商品实体类
 * @author ASUS
 *
 */
public class Commodity {
	
	
	private int id;
	/**
	 * 商品类别id
	 */
	private int commodityTypeId;
	
	/**
	 * 商品类别对象
	 */
	private CommodityLittleType commodityLittle = new CommodityLittleType();
	
	public CommodityLittleType getCommodityLittle() {
		return commodityLittle;
	}
	public void setCommodityLittle(CommodityLittleType commodityLittle) {
		this.commodityLittle = commodityLittle;
	}
	/**
	 * 商品名称
	 */
	private String commodityName;
	/**
	 * 商品价格
	 */
	private double commodityPrice;
	/**
	 * 品牌名称
	 */
	private String commodityBrandName;
	/**
	 * 库存数量
	 */
	private int commodityInventory;
	/**
	 * 销售数量
	 */
	private int commoditySalesVolume;
	/**
	 * 浏览次数
	 */
	private int commodityBrowseCount;
	/**
	 * 图片
	 */
	private String commodityImage;
	/**
	 * 商品状态
	 */
	private int commodityStatusTypeId;
	/*
	 * 商品状态类
	 */
	private CommodityStatus commodityStats = new CommodityStatus();
	
	/**
	 * 商品大类id
	 */
	private int CommodityBroadId;
	
	
	/**
	 * 商品大类id
	 * @return
	 */
	public int getCommodityBroadId() {
		return CommodityBroadId;
	}
	/**
	 * 商品大类
	 * @param commodityBroadId
	 */
	public void setCommodityBroadId(int commodityBroadId) {
		CommodityBroadId = commodityBroadId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 类别id
	 * @return
	 */
	public int getCommodityTypeId() {
		return commodityTypeId;
	}
	/**
	 * 类别id
	 * @param commodityTypeId
	 */
	public void setCommodityTypeId(int commodityTypeId) {
		this.commodityTypeId = commodityTypeId;
	}
	/**
	 * 名称
	 * @return
	 */
	public String getCommodityName() {
		return commodityName;
	}
	/**
	 * 名称
	 * @param commodityName
	 */
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	/**
	 * 价格
	 * @return
	 */
	public double getCommodityPrice() {
		return commodityPrice;
	}
	/**
	 * 价格
	 * @param commodityPrice
	 */
	public void setCommodityPrice(double commodityPrice) {
		this.commodityPrice = commodityPrice;
	}
	/**
	 * 品牌名称
	 * @return
	 */
	public String getCommodityBrandName() {
		return commodityBrandName;
	}
	/**
	 * 品牌名称
	 * @param commodityBrandName
	 */
	public void setCommodityBrandName(String commodityBrandName) {
		this.commodityBrandName = commodityBrandName;
	}
	/**
	 * 库存数量
	 * @return
	 */
	public int getCommodityInventory() {
		return commodityInventory;
	}
	/**
	 * 库存
	 * @param commodityInventory
	 */
	public void setCommodityInventory(int commodityInventory) {
		this.commodityInventory = commodityInventory;
	}
	/**
	 * 销售数量
	 * @return
	 */
	public int getCommoditySalesVolume() {
		return commoditySalesVolume;
	}
	/**
	 * 销售数量
	 * @param commoditySalesVolume
	 */
	public void setCommoditySalesVolume(int commoditySalesVolume) {
		this.commoditySalesVolume = commoditySalesVolume;
	}
	/**
	 * 浏览次数
	 * @return
	 */
	public int getCommodityBrowseCount() {
		return commodityBrowseCount;
	}
	/**
	 * 浏览次数
	 * @param commodityBrowseCount
	 */
	public void setCommodityBrowseCount(int commodityBrowseCount) {
		this.commodityBrowseCount = commodityBrowseCount;
	}
	/**
	 * 图片
	 * @return
	 */
	public String getCommodityImage() {
		return commodityImage;
	}
	/**
	 * 图片
	 * @param commodityImage
	 */
	public void setCommodityImage(String commodityImage) {
		this.commodityImage = commodityImage;
	}
	/**
	 * 商品状态
	 * @return
	 */
	public int getCommodityStatusTypeId() {
		return commodityStatusTypeId;
	}
	/**
	 * 商品状态
	 * @param commodityStatusTypeId
	 */
	public void setCommodityStatusTypeId(int commodityStatusTypeId) {
		this.commodityStatusTypeId = commodityStatusTypeId;
	}
	/**
	 * 商品状态实体类
	 * @return
	 */
	public CommodityStatus getCommodityStats() {
		return commodityStats;
	}
	/**
	 * 商品状态实体类
	 * @param commodityStats
	 */
	public void setCommodityStats(CommodityStatus commodityStats) {
		this.commodityStats = commodityStats;
	}
	
	

}
