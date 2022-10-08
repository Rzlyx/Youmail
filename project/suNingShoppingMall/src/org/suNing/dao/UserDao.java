package org.suNing.dao;

import java.util.List;

import org.suNing.entity.User;

/**
 * 用户接口
 * @author ASUS
 *
 */
public interface UserDao {
	
	
	/**
	 * 添加 一个用户
	 * @return
	 */
	public int addUser(User user);
	
	/**
	 * 删除 用户账户
	 * @return
	 */
	public int deleteUser(String userAccount);
	/**
	 * 用户修改密码
	 * @param accaountAndPwd
	 * @return
	 */
	public int updatePwd(User accaountAndPwd);
	
	/**
	 *  这是用户修改密码时 输入原密码从数据库里确认
	 *   用户登录时也用该方法验证
	 * @param user
	 * @return
	 */
	public User pwdSelectAccount(User user);
	
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public int updateUser(User user);
	
	/**
	 * 修改用户账户余额
	 * @param account
	 * @return
	 */
	public int updateAccountBalance(int monvey,String account);
	
	/**
	 * 显示所有账户
	 * @return
	 */
	public List<User> getAllUser();
	
	/**
	 * 这是同过用户账户 查找用户
	 * @param userAccount
	 * @return
	 */
	public User accountGatUser(String userAccount);
	
	/**
	 * 通过账户 获取又没有重复的账号
	 * @param userAccount
	 * @return
	 */
	public int accountGatCount(String userAccount);
	
	
	
	
	

}
