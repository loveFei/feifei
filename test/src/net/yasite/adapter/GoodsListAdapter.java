package net.yasite.adapter;

import java.util.ArrayList;
import java.util.List;

import net.yasite.entity.GoodsEntity;
import net.yasite.test.R;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsListAdapter extends YasiteAdapter{

	List<GoodsEntity> list = new ArrayList<GoodsEntity>();
	
	public GoodsListAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		layoutId = R.layout.goods_item;//动态加载子布局
		setImageLoader();//调父类中的该方法，用于加载图片
	}
	/**
	 * list的set get方法
	 * @return
	 */
	public List<GoodsEntity> getList() {
		return list;
	}

	public void setList(List<GoodsEntity> list) {
		this.list = list;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	//把getview拆分
	//设置子布局的view
	@Override
	protected void setupChildViews(View convertView, ViewHolder holder) {
		// TODO Auto-generated method stub
		GoodsHolder goodsHolder = (GoodsHolder) holder;
		goodsHolder.thumb = (ImageView) convertView.findViewById(R.id.thumb);
		goodsHolder.name = (TextView) convertView.findViewById(R.id.name);
		goodsHolder.market_price = (TextView) convertView.findViewById(R.id.market_price);
		goodsHolder.promote_price = (TextView) convertView.findViewById(R.id.promote_price);
		
	}
	//设置view的数据
	@Override
	protected void setChildViewData(ViewHolder holder, int position, Object obj) {
		// TODO Auto-generated method stub
		GoodsHolder goodsHolder = (GoodsHolder) holder;
		if(obj instanceof GoodsEntity){
			GoodsEntity good = (GoodsEntity) obj;
			goodsHolder.name.setText(good.getGoods_name());
			goodsHolder.market_price.setText(good.getMarket_price());
			goodsHolder.promote_price.setText(good.getShop_price());
			mImageLoader.displayImage("http://www.yasite.net/ecshop/"+good.getGoods_img(), goodsHolder.thumb);
		}
	}
	
	//111设置ViewHolder
	@Override
	protected ViewHolder setHolder() {
		// TODO Auto-generated method stub
		return new GoodsHolder();
	}
	//222ViewHolder
	class GoodsHolder extends ViewHolder{
		ImageView thumb;
		TextView name;
		TextView market_price;
		TextView promote_price;
	}

	@Override
	protected void setLayoutResource(int position) {
		// TODO Auto-generated method stub
		
	}
	
}
