package org.suNing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.suNing.dao.OrderFormStateDao;
import org.suNing.entity.OrderFormState;
import org.suNing.utli.BaseDao;

public class OrderFormStateDaoImpl extends BaseDao implements OrderFormStateDao {

	@Override
	public List<OrderFormState> getAllOrderFormState() {
		List<OrderFormState> list = new ArrayList<OrderFormState>();
		String sql = "select id , OrderFormType from Tbl_OrderFormState";
		ResultSet res = super.executeQuery(sql);
		try {
			while(res.next()){
				OrderFormState ofs = new OrderFormState();
				ofs.setId(res.getInt("id"));
				ofs.setOrderFormType(res.getString("OrderFormType"));
				list.add(ofs);
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
