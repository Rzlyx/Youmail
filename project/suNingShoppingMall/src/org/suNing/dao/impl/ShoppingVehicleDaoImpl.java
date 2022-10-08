package org.suNing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.suNing.dao.ShoppingVehicleDao;
import org.suNing.entity.ShoppingVehicle;
import org.suNing.utli.BaseDao;

public class ShoppingVehicleDaoImpl extends BaseDao implements ShoppingVehicleDao {

	@Override
	public int joinShoppingVehicle(ShoppingVehicle spve) {
		String sql = "insert into Tbl_ShoppingVehicle(userAccount,commodityId,commodityCount,"
								+ "addShoppingTime)values(?,?,?,getdate())";
		Object[] params = {spve.getUserAccount(),spve.getCommodityId(),spve.getCommodityCount()};
		return super.executeUpdate(sql, params);
	}

	@Override
	public List<ShoppingVehicle> accountGetShoppingCommodity(String account) {
		List<ShoppingVehicle> list = new ArrayList<ShoppingVehicle>();
		String sql = "select v.id vid,C.id cid,C.commodityName,C.commodityPrice, v.commodityCount, "
				+ " v.addShoppingTime,C.commodityImage ,U.userAccount acc  "
				+ " from Tbl_ShoppingVehicle as V inner join Tbl_User as U on "
				+ " V.userAccount=U.userAccount inner join Tbl_Commodity C on V.commodityId=C.id  "
				+ " where U.userAccount=?";
		ResultSet res = super.executeQuery(sql, account);
		try {
			while(res.next()){
				ShoppingVehicle shve = new ShoppingVehicle();
				shve.setId(res.getInt("vid"));
				shve.setUserAccount(res.getString("acc"));
				shve.setCommodityId(res.getInt("cid"));
				shve.getCommodity().setCommodityName(res.getString("commodityName"));
				shve.getCommodity().setCommodityPrice(res.getDouble("commodityPrice"));
				shve.setCommodityCount(res.getInt("commodityCount"));
				shve.getCommodity().setCommodityImage(res.getString("commodityImage"));
				list.add(shve);
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
	public int deleteShoppingCommodity(int shoopingId) {
		
		String sql  = "delete from Tbl_ShoppingVehicle where id = ?";
		
		return super.executeUpdate(sql, shoopingId);
	}

}
