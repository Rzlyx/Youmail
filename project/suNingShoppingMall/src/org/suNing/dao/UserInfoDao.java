package org.suNing.dao;

import java.util.List;

import org.suNing.entity.UserInfo;

/**
 * 用户信息接口
 * @author ASUS
 *
 */
public interface UserInfoDao {
	
	/**
	 * 显示所有的 用户信息  通过分页显示 
	 * @param pageNum
	 * @param userInfo 这个是用户的昵称、模糊查询
	 * @return
	 */
	public List<UserInfo> getAllUserInfopage(int pageNum, String userInfo);
	
	/**
	 * 这是通过条件查询用户
	 * @param userInfo
	 * @return
	 */
	public int getUserInfoConditionCount(String userInfo);
	
	
	
	
	/**
	 * 这是通过用户的账户返回用户信息
	 * @param userAccount
	 * @return
	 */
	public UserInfo accountGetUserInfo(String userAccount);
	
	/**
	 *  通过用户账户删除用户
	 * @param userAccount
	 * @return
	 */
	public int deleteUserInfo(String userAccount);
	
	/**
	 * 修改用户信息
	 * @param userInfo
	 * @return
	 */
	public int updateUserInfo(UserInfo userInfo);
	
	/**
	 * 添加用户信息
	 * @param userInfo
	 * @return
	 */
	public int addUserInfo(UserInfo userInfo);
	
	
	

}
