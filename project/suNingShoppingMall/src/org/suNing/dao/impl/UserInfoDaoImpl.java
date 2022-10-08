package org.suNing.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.suNing.dao.UserInfoDao;
import org.suNing.entity.UserInfo;
import org.suNing.utli.BaseDao;

public class UserInfoDaoImpl extends BaseDao implements UserInfoDao {

	@Override
	public List<UserInfo> getAllUserInfopage(int pageNum, String userInfo) {
		List<UserInfo> list = new ArrayList<UserInfo>();
		String sql ="select top 10 id,U.userAccount ua,U.userPwd,U.accountBalance,userName, userGender, "
				+ "userBirthDate, userIdentityMark, userEmail, userPhone,userPhoto  "
				+ "from Tbl_UserInfo as I inner join Tbl_User as U on I.userAccount=U.userAccount where I.id not in"
				+ "  (select top (?) id from Tbl_UserInfo where userName like ? or userAccount like ? or userGender like ?) "
				+ "  and userName like ?  or U.userAccount like ? or userGender like ?";
		Object[] params = new Object [7];
		// 循环给 查询条件赋值
		for (int i = 0; i < params.length; i++) {
			if(i == 0){
				params[i] = (pageNum-1)*10;
			}else{
				params[i] = "%"+userInfo+"%";
			}
		}
		
		ResultSet res = super.executeQuery(sql, params);
		try {
			while(res.next()){
				UserInfo userinfo = new UserInfo();
				userinfo.setId(res.getInt("id"));
				userinfo.getUser().setUserAccount(res.getString("ua"));
				userinfo.getUser().setUserPwd(res.getString("userPwd"));	
				userinfo.getUser().setAccountBalance(res.getDouble("accountBalance"));
				userinfo.setUserName(res.getString("userName"));
				userinfo.setUserGender(res.getString("userGender"));
				userinfo.setUserBirthDate(res.getDate("userBirthDate"));
				userinfo.setUserIdentityMark(res.getString("userIdentityMark"));
				userinfo.setUserEmail(res.getString("userEmail"));
				userinfo.setUserPhone(res.getString("userPhone"));
				userinfo.setUserPhoto(res.getString("userPhoto"));
				list.add(userinfo);
				
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
	
	/**
	 * 这是 带条件查询用户信息数量的函数
	 * @param userInfo
	 * @return
	 */
	public int getUserInfoConditionCount(String userInfo){
		
		String sql = "select count(1) c from Tbl_UserInfo where userName like ?";
		int result  = 0;
		ResultSet res = super.executeQuery(sql, "%"+userInfo+"%");
		try {
			if(res.next()){
				result = res.getInt("c");
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
		return result;
		
	}
	
	
	
	@Override
	public UserInfo accountGetUserInfo(String userAccount) {
		UserInfo userinfo = null;
		String sql ="select id,U.userAccount ua,U.userPwd,u.accountBalance,userName, userGender,"
				+ " userBirthDate, userIdentityMark, userEmail, userPhone,userPhoto "
				+ "from Tbl_UserInfo as I inner join Tbl_User as U on I.userAccount=U.userAccount where u.useraccount = ?";
		ResultSet res = super.executeQuery(sql,userAccount);
		try {
			if(res.next()){
				 userinfo = new UserInfo();
				userinfo.setId(res.getInt("id"));
				userinfo.getUser().setUserAccount(res.getString("ua"));
				userinfo.getUser().setUserPwd(res.getString("userPwd"));	
				userinfo.getUser().setAccountBalance(res.getDouble("accountBalance"));
				userinfo.setUserAccount(res.getString("ua"));
				userinfo.setUserName(res.getString("userName"));
				userinfo.setUserGender(res.getString("userGender"));
				userinfo.setUserBirthDate(new java.util.Date(res.getDate("userBirthDate").getTime()) );
				userinfo.setUserIdentityMark(res.getString("userIdentityMark"));
				userinfo.setUserEmail(res.getString("userEmail"));
				userinfo.setUserPhone(res.getString("userPhone"));
				userinfo.setUserPhoto(res.getString("userPhoto"));
				
				
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
		
		return userinfo;
	}

	@Override
	public int deleteUserInfo(String userAccount) {
		String sql = "delete from Tbl_UserInfo where userAccount=?";
		return super.executeUpdate(sql, userAccount);
	}

	@Override
	public int updateUserInfo(UserInfo userInfo) {
		String sql = "update Tbl_UserInfo set userName=?,userGender=?,userBirthDate=?, userIdentityMark=?,"
										+ "  userEmail=?,userPhone=?  where userAccount =?";
		Date spldate = new Date(userInfo.getUserBirthDate().getTime());
		Object[] params = {userInfo.getUserName(),userInfo.getUserGender(),spldate,
				userInfo.getUserIdentityMark(),userInfo.getUserEmail(),userInfo.getUserPhone(),userInfo.getUserAccount()};
		return super.executeUpdate(sql, params);
	}

	@Override
	public int addUserInfo(UserInfo userInfo) {
		String sql = "insert into Tbl_UserInfo(userAccount,userName, userGender, userBirthDate, userIdentityMark, userEmail, userPhone,userPhoto)"
				+ "values(?,?,?,?,?,?,?,default)";
		Date sqldate = new Date(userInfo.getUserBirthDate().getTime());
		Object[] params = {userInfo.getUserAccount(),userInfo.getUserName(),userInfo.getUserGender(),
				sqldate,userInfo.getUserIdentityMark(),userInfo.getUserEmail(),userInfo.getUserPhone()};
		return super.executeUpdate(sql, params);
	}

}
