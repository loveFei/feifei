package net.yasite.entity;

import java.io.Serializable;
import java.util.List;

public class ClassifyInfoEntity implements Serializable{

	private List<ClassifyEntity> data;
	private int res;
	
	public ClassifyInfoEntity(){}
	
	public ClassifyInfoEntity(List<ClassifyEntity> data, int res) {
		super();
		this.data = data;
		this.res = res;
	}
	public List<ClassifyEntity> getData() {
		return data;
	}
	public void setData(List<ClassifyEntity> data) {
		this.data = data;
	}
	public int getRes() {
		return res;
	}
	public void setRes(int res) {
		this.res = res;
	}
	
	
	
}
