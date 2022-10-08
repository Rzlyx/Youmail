package org.suNing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.suNing.dao.CommodityStatusDao;
import org.suNing.entity.CommodityStatus;
import org.suNing.utli.BaseDao;

public class CommodityStatusDaoImpl extends BaseDao implements CommodityStatusDao {

	@Override
	public List<CommodityStatus> getAllCommodityStatus() {
		List<CommodityStatus> list = new ArrayList<CommodityStatus>();
		String sql = "select id, commodityStatusType from Tbl_CommodityStatus";
		ResultSet res = super.executeQuery(sql);
		try {
			while(res.next()){
				CommodityStatus status = new CommodityStatus();
				status.setId(res.getInt("id"));
				status.setCommodityStatusType(res.getString("commodityStatusType"));
				list.add(status);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				super.closeConnection(res, res.getStatement(), res.getStatement().getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
