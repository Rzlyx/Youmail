package org.suNing.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.suNing.dao.UserDao;
import org.suNing.entity.User;
import org.suNing.utli.BaseDao;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public int addUser(User user) {
		
		String sql = "insert into Tbl_User(userAccount,userPwd,accountBalance)values(?,?,?) ";
		Object[] params = {user.getUserAccount(),user.getUserPwd(),user.getAccountBalance()};
		return super.executeUpdate(sql, params);
	}

	@Override
	public int deleteUser(String userAccount) {
		String sql = "delete from Tbl_User where userAccount=?";
		return super.executeUpdate(sql, userAccount);
	}

	@Override
	public int updatePwd(User accaountAndPwd) {
		
		String sql = "update Tbl_User set userPwd=?  where userAccount = ?";
		
		return super.executeUpdate(sql, accaountAndPwd.getUserPwd(),accaountAndPwd.getUserAccount());
	}

	@Override
	public User pwdSelectAccount(User user) {
		User usr = null;
		String sql = "select userAccount,userPwd,accountBalance from Tbl_User where userAccount=? and userPwd=?";
		
		ResultSet res = super.executeQuery(sql, user.getUserAccount(),user.getUserPwd());
		try {
			System.out.println("***"+sql+user.getUserAccount()+user.getUserPwd());
			if(res.next()){
				System.out.println("rrr");
				usr = new User();
				usr.setUserAccount(res.getString("userAccount"));
				usr.setUserPwd(res.getString("userPwd"));
				usr.setAccountBalance(res.getDouble("accountBalance"));
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
		
		return usr;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User accountGatUser(String userAccount) {
		User user = null;
		String sql = "select userAccount,userPwd,accountBalance from Tbl_User where userAccount=?";
		ResultSet res = super.executeQuery(sql, userAccount);
		try {
			if(res.next()){
				user = new User();
				user.setUserAccount(res.getString("userAccount"));
				user.setUserPwd(res.getString("userPwd"));
				user.setAccountBalance(res.getDouble("accountBalance"));
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
		return user;
	}

	@Override
	public int accountGatCount(String userAccount) {
		String sql = "select count(1) c from Tbl_User where userAccount=?";
		ResultSet res = super.executeQuery(sql, userAccount);
		int result = 0;
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
	public int updateUser(User user) {
		
		String sql = "update Tbl_User set userPwd=?,accountBalance=?  where userAccount =?";
		return super.executeUpdate(sql, user.getUserPwd(),user.getAccountBalance(),user.getUserAccount());
	}

	@Override
	public int updateAccountBalance(int money,String account) {
		
		String sql = "update Tbl_User set accountBalance=?  where userAccount=?";
		
		return super.executeUpdate(sql, money,account);
	}

}
