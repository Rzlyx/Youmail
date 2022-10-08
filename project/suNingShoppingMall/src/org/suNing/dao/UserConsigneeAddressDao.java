package org.suNing.dao;

import java.util.List;

import org.suNing.entity.UserConsigneeAddress;

/**
 * 这是用户地址的接口
 * @author ASUS
 *
 */
public interface UserConsigneeAddressDao {
	
	/**
	 * 通过用户账户获取地址
	 * @param userAccount
	 * @return
	 */
	public List<UserConsigneeAddress> accountGetUserConsigneeAddress(String userAccount);
	
	/**
	 * 添加地址的是后会把该用户之前的地址状态 全部改成 不是默认的 把新添加的地址都会设置成默认的
	 *  只有添加地址的是后才会调用该方法
	 *    这是修改用户默认地址的方法
	 * @param userAccount
	 * @return
	 */
	public int updateUseracquiesceStatus(String userAccount);
	
	/**
	 * 添加地址 添加完 地址就是默认就是
	 * @param userAddress
	 * @return
	 */
	public int addUserConsigneeAddress(UserConsigneeAddress userAddress);
	
	/**
	 * 这是删除用户地址
	 * @param addressId
	 * @return
	 */
	public int deleteUserConsigneeAddress(int addressId);
	
	/**
	 * 这是把用户地址更改为默认的地址
	 * @param addressId  更改默认地址 地址id
	 * @return
	 */
	public int updateacquiesceStatus(int addressId);

}
