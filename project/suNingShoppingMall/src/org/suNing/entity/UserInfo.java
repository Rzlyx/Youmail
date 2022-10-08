package org.suNing.entity;

import java.util.Date;

/**
 * 用户信息的实体类
 * @author ASUS
 *
 */
public class UserInfo {
	
	
	private int id;
	
	
	private String userAccount;
	
	private User user = new User();
	
	private String userName;
	
	private String userGender;
	
	private Date userBirthDate;
	
	private String userIdentityMark;
	
	private String userEmail;
	
	private String userPhone;
	
	private String userPhoto;
	
	/**
	 * 用户头像
	 * @return
	 */
	public String getUserPhoto() {
		return userPhoto;
	}
	/**
	 * 用户头像
	 * @param userPhoto
	 */
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	/**
	 *  账户
	 */
	public String getUserAccount() {
		return userAccount;
	}
	/**
	 *账户
	 * @param userAccount
	 */
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	/**
	 * 用户对象
	 * @return
	 */
	public User getUser() {
		return user;
	}
	/**
	 * 用户对象
	 * @param user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * 用户名
	 * @return
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 用户名
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 性别
	 * @return
	 */
	public String getUserGender() {
		return userGender;
	}
	/**
	 * 性别
	 * @param userGender
	 */
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	/**
	 * 出生日期
	 * @return
	 */
	public Date getUserBirthDate() {
		return userBirthDate;
	}
	/**
	 * 出生日期
	 * @param userBirthDate
	 */
	public void setUserBirthDate(Date userBirthDate) {
		this.userBirthDate = userBirthDate;
	}
	/**
	 * 身份证号
	 * @return
	 */
	public String getUserIdentityMark() {
		return userIdentityMark;
	}
	/**
	 * 身份证号
	 * @param userIdentityMark
	 */
	public void setUserIdentityMark(String userIdentityMark) {
		this.userIdentityMark = userIdentityMark;
	}
	/**
	 * 邮箱
	 * @return
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * 邮箱
	 * @param userEmail
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * 手机号
	 * @return
	 */
	public String getUserPhone() {
		return userPhone;
	}
	/**
	 * 手机号
	 * @param userPhone
	 */
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	
	
	

}
