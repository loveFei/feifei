package net.yasite.adapter;

import java.util.List;

import net.yasite.adapter.YasiteAdapter.ViewHolder;
import net.yasite.entity.ShoppingCarEntity;
import net.yasite.test.R;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ShoppingCarAdapter extends YasiteAdapter{
	
	private List<ShoppingCarEntity> list;
	
	public List<ShoppingCarEntity> getList() {
		return list;
	}

	public void setList(List<ShoppingCarEntity> list) {
		this.list = list;
	}

	public ShoppingCarAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		layoutId = R.layout.activity_shoppingcar_item;//动态加载子布局
		setImageLoader();//调父类中的该方法，用于加载图片
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	protected void setupChildViews(View convertView, ViewHolder holder) {
		// TODO Auto-generated method stub
		GoodsHolder goodsholder = (GoodsHolder) holder;
		
		goodsholder.check_box = (CheckBox) convertView.findViewById(R.id.checkBox_car);
		goodsholder.thumb = (ImageView) convertView.findViewById(R.id.thumb);
		goodsholder.name = (TextView) convertView.findViewById(R.id.textView_name);;
		goodsholder.price = (TextView) convertView.findViewById(R.id.textView_price);
		goodsholder.number = (TextView) convertView.findViewById(R.id.textView_num);
		
		goodsholder.sbtnum = (TextView) convertView.findViewById(R.id.textView_num_subtract);
		goodsholder.addnum = (TextView) convertView.findViewById(R.id.textView_num_add);
		
	}

	@Override
	protected void setChildViewData(ViewHolder holder, final int position, Object obj) {
		// TODO Auto-generated method stub
		final GoodsHolder goodsholder = (GoodsHolder) holder;
		goodsholder.name.setText(list.get(position).getGoods_name());
		goodsholder.price.setText(list.get(position).getGoods_price());
		goodsholder.number.setText(list.get(position).getGoods_number());
		
		goodsholder.addnum.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int num = Integer.parseInt(list.get(position).getGoods_number());
				num += 1;
				list.get(position).setGoods_number(num+"");
				goodsholder.number.setText(num+"");
			}
		});
		goodsholder.sbtnum.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int num = Integer.parseInt(list.get(position).getGoods_number());
				num -= 1;
				list.get(position).setGoods_number(num+"");
				goodsholder.number.setText(num+"");
			}
		});
		
		goodsholder.check_box.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				list.get(position).setIschecked(isChecked);
				float sum = 0;
				for(ShoppingCarEntity g:list){
					if(g.isIschecked()){
						float num = Float.parseFloat(list.get(position).getGoods_number());
						float price = Float.parseFloat(list.get(position).getGoods_price());
						sum += num*price;
					}
				}
				Intent intent = new Intent();
				intent.setAction("car");
				intent.putExtra("sum", sum);
				context.sendBroadcast(intent);
			}
		});
		goodsholder.check_box.setChecked(list.get(position).isIschecked());
		
	}
	
	@Override
	protected ViewHolder setHolder() {
		// TODO Auto-generated method stub
		return new GoodsHolder();
	}
	
	class GoodsHolder extends ViewHolder{
		ImageView thumb;
		CheckBox check_box;
		TextView name,price,number;
		TextView addnum,sbtnum;//加减按钮
	}
	
	@Override
	protected void setLayoutResource(int position) {
		// TODO Auto-generated method stub
		
	}
	
	
}
