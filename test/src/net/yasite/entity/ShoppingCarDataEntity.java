package net.yasite.entity;

import java.io.Serializable;
import java.util.List;

public class ShoppingCarDataEntity implements Serializable{
	
	private ShoppingCarEntity data;
	private int res;
	
	public ShoppingCarDataEntity(){}

	public ShoppingCarDataEntity(ShoppingCarEntity data, int res) {
		super();
		this.data = data;
		this.res = res;
	}

	public ShoppingCarEntity getData() {
		return data;
	}

	public void setData(ShoppingCarEntity data) {
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
		return "ShoppingCarDataEntity [data=" + data + ", res=" + res + "]";
	}
	
	
	

}
