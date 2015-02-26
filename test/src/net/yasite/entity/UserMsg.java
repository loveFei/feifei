package net.yasite.entity;

import java.io.Serializable;

public class UserMsg implements Serializable{
	
	private UserMsgEntity data;
	private int res;
	
	public UserMsg(){}
	
	public UserMsg(UserMsgEntity data, int res) {
		super();
		this.data = data;
		this.res = res;
	}
	
	public UserMsgEntity getData() {
		return data;
	}
	public void setData(UserMsgEntity data) {
		this.data = data;
	}
	public int getRes() {
		return res;
	}
	public void setRes(int res) {
		this.res = res;
	}
	
	@Override
	public String toString() {
		return "UserMsg [data=" + data + ", res=" + res + "]";
	}
	
	
	
}
