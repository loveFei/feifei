package net.yasite.entity;

import java.io.Serializable;

public class ShoppingCarEntity implements Serializable{

	private String rec_id;
	private String user_id;
	private String goods_id;
	private String goods_sn;
	private String goods_name;
	private String market_price;
	private String goods_price;
	private String goods_number;
	private String session_id;
	private boolean ischecked = false;
	
	public ShoppingCarEntity(){}

	public ShoppingCarEntity(String rec_id, String user_id, String goods_id,
			String goods_sn, String goods_name, String market_price,
			String goods_price, String goods_number, String session_id) {
		super();
		this.rec_id = rec_id;
		this.user_id = user_id;
		this.goods_id = goods_id;
		this.goods_sn = goods_sn;
		this.goods_name = goods_name;
		this.market_price = market_price;
		this.goods_price = goods_price;
		this.goods_number = goods_number;
		this.session_id = session_id;
	}

	public String getRec_id() {
		return rec_id;
	}

	public void setRec_id(String rec_id) {
		this.rec_id = rec_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getGoods_sn() {
		return goods_sn;
	}

	public void setGoods_sn(String goods_sn) {
		this.goods_sn = goods_sn;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getMarket_price() {
		return market_price;
	}

	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}

	public String getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}

	public String getGoods_number() {
		return goods_number;
	}

	public void setGoods_number(String goods_number) {
		this.goods_number = goods_number;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	//**********
	
	public boolean isIschecked() {
		return ischecked;
	}

	public void setIschecked(boolean ischecked) {
		this.ischecked = ischecked;
	}

	@Override
	public String toString() {
		return "ShoppingCarEntity [rec_id=" + rec_id + ", user_id=" + user_id
				+ ", goods_id=" + goods_id + ", goods_sn=" + goods_sn
				+ ", goods_name=" + goods_name + ", market_price="
				+ market_price + ", goods_price=" + goods_price
				+ ", goods_number=" + goods_number + ", session_id="
				+ session_id + "]";
	}

	
	
	
	
}
