package net.yasite.entity;

import java.io.Serializable;

public class UserMsgEntity implements Serializable{
	
	private String birthday;
	private String user_id;
	private String email;
	private String user_name;
	private String password;
	private String sex;
	private Long last_login;
	private String qq;
	private String mobile_phone;
	private String headimg;
	private String token;
	
	public UserMsgEntity(){}

	public UserMsgEntity(String birthday, String user_id, String email,
			String user_name, String password, String sex, Long last_login,
			String qq, String mobile_phone, String headimg, String token) {
		super();
		this.birthday = birthday;
		this.user_id = user_id;
		this.email = email;
		this.user_name = user_name;
		this.password = password;
		this.sex = sex;
		this.last_login = last_login;
		this.qq = qq;
		this.mobile_phone = mobile_phone;
		this.headimg = headimg;
		this.token = token;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Long getLast_login() {
		return last_login;
	}

	public void setLast_login(Long last_login) {
		this.last_login = last_login;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMobile_phone() {
		return mobile_phone;
	}

	public void setMobile_phone(String mobile_phone) {
		this.mobile_phone = mobile_phone;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserMsgEntity [birthday=" + birthday + ", user_id=" + user_id
				+ ", email=" + email + ", user_name=" + user_name
				+ ", password=" + password + ", sex=" + sex + ", last_login="
				+ last_login + ", qq=" + qq + ", mobile_phone=" + mobile_phone
				+ ", headimg=" + headimg + ", token=" + token + "]";
	}
	
	
	
}
