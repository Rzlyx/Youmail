package org.suNing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.suNing.dao.UpCityManagerDao;
import org.suNing.entity.UpCityManager;
import org.suNing.utli.BaseDao;

public class UpCityManagerDaoImpl extends BaseDao implements UpCityManagerDao {

	@Override
	public UpCityManager getManager(String account, String pwd) {
		
		String sql = "select mangaerAccount,managerPwd,managerNickName from Tbl_UpCityManager "
									+ "where mangaerAccount=? and managerPwd=?";
		UpCityManager manager = null;
		ResultSet res = super.executeQuery(sql, account,pwd);
		if(res != null){
			try {
				if(res.next()){
					manager = new UpCityManager();
					manager.setManagerAccount(res.getString("mangaerAccount"));
					manager.setManagerPwd(res.getString("managerPwd"));
					manager.setManagerNickName(res.getString("managerNickName"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					super.closeConnection(res, res.getStatement(), res.getStatement().getConnection());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
		
		return manager;
	}

	@Override
	public int selectManageAccount(String account) {

		String sql = "select count(1) c from Tbl_UpCityManager where mangaerAccount =?";
		int result = 0;
		ResultSet res = super.executeQuery(sql, account);
		if(res != null){
			try {
				if(res.next()){
					result = res.getInt("c");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					super.closeConnection(res, res.getStatement(), res.getStatement().getConnection());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}

	@Override
	public int updateManagePwd(String managePwd,String managerAccount) {
		
		String sql = "update Tbl_UpCityManager set managerPwd=?  where mangaerAccount = ? " ;
		
		return super.executeUpdate(sql, managePwd,managerAccount);
	}

}
