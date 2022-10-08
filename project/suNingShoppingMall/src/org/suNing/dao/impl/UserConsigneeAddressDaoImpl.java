package org.suNing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.suNing.dao.UserConsigneeAddressDao;
import org.suNing.entity.UserConsigneeAddress;
import org.suNing.utli.BaseDao;

public class UserConsigneeAddressDaoImpl extends BaseDao implements UserConsigneeAddressDao {

	@Override
	public List<UserConsigneeAddress> accountGetUserConsigneeAddress(String userAccount) {
		List<UserConsigneeAddress> list = new ArrayList<UserConsigneeAddress>();
		String sql = "select id,userAccount,consigneeAddress,acquiesceStatus from Tbl_ConsigneeAddress where userAccount = ?";
		
		ResultSet res = super.executeQuery(sql, userAccount);
		try {
			while(res.next()){
				UserConsigneeAddress useradd = new UserConsigneeAddress();
				useradd.setId(res.getInt("id"));
				useradd.setUserAccount(res.getString("userAccount"));
				useradd.setConsigneeAddress(res.getString("consigneeAddress"));
				useradd.setAcquiesceStatus(res.getInt("acquiesceStatus"));
				list.add(useradd);
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
	public int updateUseracquiesceStatus(String userAccount) {
		String sql = "update Tbl_ConsigneeAddress set acquiesceStatus = 1 where userAccount =?";
		return super.executeUpdate(sql, userAccount);
	}

	@Override
	public int addUserConsigneeAddress(UserConsigneeAddress userAddress) {
		String sql = "insert into Tbl_ConsigneeAddress(userAccount,consigneeAddress,acquiesceStatus)values(?,?,0)";
		return super.executeUpdate(sql, userAddress.getUserAccount(),userAddress.getConsigneeAddress());
	}

	@Override
	public int deleteUserConsigneeAddress(int addressId) {
		
		String sql = "delete from Tbl_ConsigneeAddress where id = ?";
		return super.executeUpdate(sql, addressId);
	}

	@Override
	public int updateacquiesceStatus(int addressId) {
		String sql = "update Tbl_ConsigneeAddress set acquiesceStatus = 0 where id = ? ";
		return super.executeUpdate(sql, addressId);
	}
	
	

}
