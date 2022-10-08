package org.suNing.dao;

import java.util.List;

import org.suNing.entity.CommodityLittleType;

/**
 * 商品小类 接口
 * @author ASUS
 *
 */
public interface CommodityLittleTypeDao {
	
	/**
	 * 获取所有的类别并分页显示
	 * @pageNum 当前页数
	 * @pageSize 显示条数
	 * @return
	 */
	public List<CommodityLittleType> getAllCommodityLittle(int pageSize,int pageNum);
	
	/**
	 * 获取所有 商品类别
	 * @return
	 */
	public List<CommodityLittleType> getAllCommodityLittle();
	
	/**
	 * 重写了 获取所有商品的方法
	 * 通过商品大类id 获取商品类别
	 * @param broadTypeId
	 * @return
	 */
	public List<CommodityLittleType> getAllCommodityLittle(int broadTypeId);
	
	
	/**
	 * 通过id 获取类别
	 * @param littleType
	 * @return
	 */
	public CommodityLittleType getCommodityLittle(int littleType);
	/**
	 * 删除类别
	 * @param littleTypeId
	 * @return
	 */
	public int deleteCommodityLittle(int littleTypeId);
	
	/**
	 * 修改类别
	 * @param littleType
	 * @return
	 */
	public int updateCommodityLittle(CommodityLittleType littleType);
	
	/**
	 * 添加类别
	 * @param littleType
	 * @return
	 */
	public int addCommodityLittle(CommodityLittleType littleType);
	
	/**
	 * 获取商品小类数量
	 * @return
	 */
	public int getCommodityLittleCount();

}
