package net.yasite.entity;

import java.io.Serializable;
import java.util.List;

public class GoodsListEntity implements Serializable{
	
	private List<GoodsEntity> data;
	private int res;
	
	public GoodsListEntity(){}
	
	public GoodsListEntity(List<GoodsEntity> data, int res) {
		super();
		this.data = data;
		this.res = res;
	}
	
	public List<GoodsEntity> getData() {
		return data;
	}
	public void setData(List<GoodsEntity> data) {
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
		return "GoodsListEntity [data=" + data + ", res=" + res + "]";
	}
	
	

}
