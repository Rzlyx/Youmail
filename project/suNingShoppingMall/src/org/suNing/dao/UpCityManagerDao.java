package org.suNing.dao;

import org.suNing.entity.UpCityManager;

public interface UpCityManagerDao {
	/**
	 *  通过账户密码获取管理员
	 * @param account
	 * @param pwd
	 * @return
	 */
	public UpCityManager getManager(String account,String pwd);
	
	/**
	 * 查看有没有该账户
	 * @param account
	 * @return
	 */
	public int selectManageAccount(String account);
	
	/**
	 * 修改管理员密码
	 * @param managePwd
	 * @return
	 */
	public int updateManagePwd( String managePwd,String managerAccount);

}
