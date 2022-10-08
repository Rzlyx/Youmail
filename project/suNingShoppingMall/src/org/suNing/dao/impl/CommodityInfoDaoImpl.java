package org.suNing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.suNing.dao.CommodityInfoDao;
import org.suNing.entity.CommodityInfo;
import org.suNing.utli.BaseDao;

public class CommodityInfoDaoImpl extends BaseDao implements CommodityInfoDao {

	@Override
	public int addCommodityInfo(CommodityInfo cominfo) {
		String sql = "insert into Tbl_CommodityInfo(commodityProducing,productType,commodityRemark,commodityId)"
								+ "values(?,?,?,?)";
		Object[] params = {cominfo.getCommodityProducing(),cominfo.getProductType(),
												cominfo.getCommodityRemark(),cominfo.getCommodityId()};
		return super.executeUpdate(sql, params);
	}

	@Override
	public int deleteCommodityInfo(int commodityId) {
		String sql = "delete from Tbl_CommodityInfo where commodityId = ?";
		return super.executeUpdate(sql, commodityId);
	}

	@Override
	public int updateCommodityInfo(CommodityInfo cominfo) {
		String sql = "update Tbl_CommodityInfo set commodityProducing=?,productType=?,commodityRemark=? "
										+ "where commodityId =?";
		Object[] params = {cominfo.getCommodityProducing(),cominfo.getProductType(),
				cominfo.getCommodityRemark(),cominfo.getCommodityId()};
		return super.executeUpdate(sql, params);
	}

	@Override
	public CommodityInfo getCommodityInfo(int commodityId) {
		CommodityInfo commodityInfo = null;
		String sql = "select commodityProducing,productType,commodityRemark "
								+ "from Tbl_CommodityInfo where commodityId = ?";
		ResultSet res = super.executeQuery(sql, commodityId);
		try {
			if(res.next()){
				commodityInfo = new CommodityInfo();
				commodityInfo.setCommodityProducing(res.getString("commodityProducing"));
				commodityInfo.setProductType(res.getString("productType"));
				commodityInfo.setCommodityRemark(res.getString("commodityRemark"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commodityInfo;
	}

}
