package org.suNing.entity;

public class UpCityManager {
	
	private String managerAccount;
	
	private String managerPwd;
	
	private String managerNickName;
	
	/**
	 * 管理员账户
	 * @return
	 */
	public String getManagerAccount() {
		return managerAccount;
	}
	

	/**
	 * 管理员账户
	 * @return
	 */
	public void setManagerAccount(String mangaerAccount) {
		this.managerAccount = mangaerAccount;
	}
	/**
	 * 管理员密码
	 * @return
	 */
	public String getManagerPwd() {
		return managerPwd;
	}
	
	/**
	 * 管理员密码
	 * @return
	 */
	public void setManagerPwd(String managePwd) {
		this.managerPwd = managePwd;
	}

	/**
	 * 管理昵称
	 * @return
	 */
	public String getManagerNickName() {
		return managerNickName;
	}

	/**
	 * 管理昵称
	 * @return
	 */
	public void setManagerNickName(String managerNickName) {
		this.managerNickName = managerNickName;
	}

}
