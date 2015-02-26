package net.yasite.entity;

import java.io.Serializable;
import java.util.List;

public class ShoppingCarInfoEntity implements Serializable{
	
	private List<ShoppingCarEntity> data;
	private int res;
	
	public ShoppingCarInfoEntity(){}
	
	public ShoppingCarInfoEntity(List<ShoppingCarEntity> data, int res) {
		super();
		this.data = data;
		this.res = res;
	}
	public List<ShoppingCarEntity> getData() {
		return data;
	}
	public void setData(List<ShoppingCarEntity> data) {
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
		return "ShoppingCarInfoEntity [data=" + data + ", res=" + res + "]";
	}
	
	

}
