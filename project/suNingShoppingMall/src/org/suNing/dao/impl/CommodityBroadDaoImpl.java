package org.suNing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.suNing.dao.CommodityBroadDao;
import org.suNing.entity.CommodityBroad;
import org.suNing.utli.BaseDao;

public class CommodityBroadDaoImpl extends BaseDao implements CommodityBroadDao {

	@Override
	public List<CommodityBroad> getAllCommodityBroad() {
		List<CommodityBroad> list = new ArrayList<CommodityBroad>();
		String sql = "select id,commodityBroadName,commodityRemark,commodityBroadImage from Tbl_CommodityBroad";
		ResultSet res = super.executeQuery(sql);
		try {
			while(res.next()){
				CommodityBroad com = new CommodityBroad();
				com.setId(res.getInt("id"));
				com.setCommodityBroadName(res.getString("commodityBroadName"));
				com.setCommodityRemark(res.getString("commodityRemark"));
				com.setCommodityBroadImage(res.getString("commodityBroadImage"));
				list.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				super.closeConnection(res, res.getStatement(), res.getStatement().getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
	}

	@Override
	public int deleteCommodityBroad(int commId) {
		String sql = "delete from Tbl_CommodityBroad where id=?";
		return super.executeUpdate(sql,commId);
	}

	@Override
	public int addCommodityBroad(CommodityBroad broadType) {
		String sql = "insert into Tbl_CommodityBroad(commodityBroadName,commodityRemark,commodityBroadImage)"
				+ "values(?,?,?)";
		Object[] params = {broadType.getCommodityBroadName(),broadType.getCommodityRemark(),broadType.getCommodityBroadImage()};
		return super.executeUpdate(sql, params);
	}

	@Override
	public int updateCommodityBroad(CommodityBroad broadType) {
		String sql = "update Tbl_CommodityBroad set commodityBroadName =? ,commodityRemark=?,commodityBroadImage=? where id =?";
		Object[] params = {broadType.getCommodityBroadName(),broadType.getCommodityRemark(),broadType.getCommodityBroadImage(),broadType.getId()};
		return super.executeUpdate(sql, params);
	}

	@Override
	public CommodityBroad getCommodityBroad(int id) {
		CommodityBroad com = null;
		String sql = "select id,commodityBroadName,commodityRemark,commodityBroadImage from Tbl_CommodityBroad where id = ?";
		ResultSet res = super.executeQuery(sql,id);
		try {
			if(res.next()){
				com = new CommodityBroad();
				com.setId(res.getInt("id"));
				com.setCommodityBroadName(res.getString("commodityBroadName"));
				com.setCommodityRemark(res.getString("commodityRemark"));
				com.setCommodityBroadImage(res.getString("commodityBroadImage"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				super.closeConnection(res, res.getStatement(), res.getStatement().getConnection());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return com;
	}

}
