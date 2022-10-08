package org.suNing.dao;

import java.util.List;

import org.suNing.entity.HomePageImg;

/**
 * 首页轮播图片接口
 * @author ASUS
 *
 */
public interface HomePageImgDao {
	
	/**
	 * 获取所有图片
	 * @return
	 */
	public List<HomePageImg> getAllHomePageImg();
	
	/**
	 * 添加首页图片
	 * @param hpi
	 * @return
	 */
	public int addHomePageImg( HomePageImg hpi);
	/**
	 * 修改首页图片
	 * @param hpi
	 * @return
	 */
	public int UpdateHomePageImg( HomePageImg hpi);
	
	/**
	 * 删除首页图片
	 * @param hpi
	 * @return
	 */
	public int deleteHomePageImg(int hpi);

}
