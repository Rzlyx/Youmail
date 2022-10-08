package org.suNing.entity;

/**
 * 这是用户的地址实体类
 * @author ASUS
 *
 */
public class UserConsigneeAddress {
	
	
	private int id;
	
	private  String userAccount;
	/**
	 * 收件地址
	 */
	private String consigneeAddress;
	/**
	 * 是否是默认的
	 */
	private int acquiesceStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	/**
	 *  用户账户
	 * @return
	 */
	public String getUserAccount() {
		return userAccount;
	}
	/**
	 * 用户账户
	 * @param userAccount
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	/**
	 * 收件地址
	 * @return
	 */
	public String getConsigneeAddress() {
		return consigneeAddress;
	}
	/**
	 * 收件地址
	 * @param consigneeAddress
	 */
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}
	/**
	 * 是否是默认的地址
	 * @return
	 */
	public int getAcquiesceStatus() {
		return acquiesceStatus;
	}
	/**
	 * 是否是默认的地址
	 * @param acquiesceStatus
	 */
	public void setAcquiesceStatus(int acquiesceStatus) {
		this.acquiesceStatus = acquiesceStatus;
	}

}
