package org.suNing.dao;

import org.suNing.entity.CommodityInfo;

public interface CommodityInfoDao {
	
	/**
	 * 添加商品信息
	 * @param cominfo
	 * @return
	 */
	public int addCommodityInfo(CommodityInfo cominfo);
	
	/**
	 * 删除商品信息 
	 * @param commodityId  通过商品id
	 * @return
	 */
	public int deleteCommodityInfo(int commodityId);
	
	/**
	 * 修改商品信息
	 * 
	 * @return
	 */
	public int updateCommodityInfo(CommodityInfo cominfo);
	
	/**
	 * 获取商品信息
	 * @param commodityId  通过商品id
	 * @return
	 */
	public CommodityInfo getCommodityInfo(int commodityId);

}
