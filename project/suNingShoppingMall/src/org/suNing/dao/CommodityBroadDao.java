package org.suNing.dao;

import java.util.List;

import org.suNing.entity.CommodityBroad;

/**
 * 商品大类接口
 * @author ASUS
 *
 */
public interface CommodityBroadDao {
	
	/**
	 * 获取所有商品大类
	 * @return
	 */
	public List<CommodityBroad> getAllCommodityBroad();
	
	/**
	 * 同过id 查找指定类型 并返回该对象
	 * @param id
	 * @return
	 */
	public CommodityBroad getCommodityBroad(int id);
	
	/**
	 * 通过大类id 删除大类
	 * @param commId
	 * @return
	 */
	public int deleteCommodityBroad(int commId);
	
	/**
	 * 添加商品大类
	 * @param broadType
	 * @return
	 */
	public int addCommodityBroad(CommodityBroad broadType);
	
	/**
	 * 修改商品大类
	 * @param broadType
	 * @return
	 */
	public int updateCommodityBroad(CommodityBroad broadType);
	
	

}
