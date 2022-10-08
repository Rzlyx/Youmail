package org.suNing.dao;

import java.util.List;

import org.suNing.entity.Commodity;

public interface CommodityDao {
	/**
	 * 获取所有商品  商品销量排行调用了
	 * @return
	 */
	public List<Commodity> getAllCommodity();
	
	/**
	 * 通过商品大类获取商品
	 * @param CommodityType
	 * @param commodityCount  谁要调用该函数就必须的指定你要一下显示多少条数据
	 * @return
	 */
	public List<Commodity> passCommodityBroadGet(int commodityCount,int commodityBroadId);
	
	/**
	 * 带分页和条件查询
	 * @param pageSize
	 * @param pageNum
	 * @param condition  通过用户输入的数据 可以模糊查询  商品名称、商品类别、商品品牌名称
	 * @return
	 */
	public List<Commodity> searchResultConditionGetCommodity(int pageNum,Commodity condition);
	
	
	
	/**
	 * 带分页和条件查询
	 * @param pageSize  每页显示的条数
	 * @param pageNum  当前页
	 * @param commodity  查询条件
	 * @return
	 */
	public List<Commodity> passPageConditionGetCommodity(int pageSize,int pageNum,Commodity condition);
	/**
	 * 过去商品数量
	 * @return
	 */
	public int getCommodityCount();
	/**
	 * 通过商品添加查询商品数量
	 * @param condition
	 * @return
	 */
	public int getCommodityCount(Commodity condition);
	/**
	 * 删除商品
	 * @param id
	 * @return
	 */
	public int deleteCommodity(int id);
	
	/**
	 * 添加商品
	 * @param commodity
	 * @return
	 */
	public int addCommodity(Commodity commodity);
	/**
	 * 获取最大第商品id 用来直接添加商品的详情信息
	 * @return
	 */
	public int getMaxCommodityId();
	
	/**
	 * 通过商品id 获取商品
	 * @param commodityId
	 * @return
	 */
	public Commodity idGetCommodity(int commodityId);
	
	/**
	 * 修改商品
	 * @param commodity
	 * @return
	 */
	public int updateCommodity(Commodity commodity);
	
	/**
	 * 修改商品的浏览次数 
	 * @param comId  商品id
	 * @param thisCount 得到商品当前的浏览次数
	 * @return
	 */
	public int updateCommodityBrowseCount(int thisCount,int comId);
	
	
	/**
	 * 通过商品类型id 获取商品
	 * @param typeId
	 * @return
	 */
	public List<Commodity> typeIdGetCommodity(int typeId);
	
	/**
	 * 修改商品库存  减去商品库存
	 * @param commodityId
	 * @param commodityCount
	 * @return
	 */
	public int updateCommodityCount(int commodityId,int commodityCount);
	
	
	/**
	 * 这是修改商品的销量  通过商品id
	 *   
	 * @param commodityId
	 * @commodityCount 这是用户购买的商品数量 是根据你购买的商品数量来加上你的销量的
	 * @return
	 */
	public int udpateCommoditySalesVolume(int commodityCount ,int commodityId);
	
	
	
	
	
	

}
