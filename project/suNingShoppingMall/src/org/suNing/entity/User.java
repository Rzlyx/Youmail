package org.suNing.entity;

/**
 *   用户账户实体类
 * @author ASUS
 *
 */
public class User {
	
	
	private String userAccount;
	
	private String userPwd;
	
	private double accountBalance;
	
	
	/**
	 *   用户账号
	 * @return
	 */
	public String getUserAccount() {
		return userAccount;
	}
	
	
	/**
	 * 用户账号
	 * @param userAccount
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	/**
	 * 用户密码
	 * @return
	 */
	public String getUserPwd() {
		return userPwd;
	}

	/**
	 *  用户密码
	 * @param userPwd
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	/**
	 * 用户余额
	 * @return
	 */
	public double getAccountBalance() {
		return accountBalance;
	}

	/**
	 * 用户余额
	 * @param accountBalance
	 */
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

}
