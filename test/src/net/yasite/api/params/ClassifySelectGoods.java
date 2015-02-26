package net.yasite.api.params;

public class ClassifySelectGoods extends BaseHttpParam {
	
	private String name;
	private int page;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	

}
