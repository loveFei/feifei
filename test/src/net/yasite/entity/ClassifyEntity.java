package net.yasite.entity;

import java.io.Serializable;

public class ClassifyEntity implements Serializable{
	
	private String cat_id;
	private String cat_name;
	private String parent_id;
	private String sort_order;
	
	public ClassifyEntity(){}

	public ClassifyEntity(String cat_id, String cat_name, String parent_id,
			String sort_order) {
		super();
		this.cat_id = cat_id;
		this.cat_name = cat_name;
		this.parent_id = parent_id;
		this.sort_order = sort_order;
	}

	public String getCat_id() {
		return cat_id;
	}

	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getSort_order() {
		return sort_order;
	}

	public void setSort_order(String sort_order) {
		this.sort_order = sort_order;
	}
	
	
	
	

}
