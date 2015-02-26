package net.yasite.adapter;

import java.util.ArrayList;
import java.util.List;

import net.yasite.adapter.YasiteAdapter.ViewHolder;
import net.yasite.entity.ClassifyEntity;
import net.yasite.entity.GoodsEntity;
import net.yasite.test.R;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassifyAdapter extends YasiteAdapter{
	
	List<ClassifyEntity> list = new ArrayList<ClassifyEntity>();
	
	public List<ClassifyEntity> getList() {
		return list;
	}

	public void setList(List<ClassifyEntity> list) {
		this.list = list;
	}

	public ClassifyAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		layoutId = R.layout.fragment_main;//动态加载子布局
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
		ClassifyHolder viewholder = (ClassifyHolder) holder;
		viewholder.tv = (TextView) convertView.findViewById(R.id.tv);
		
	}

	@Override
	protected void setChildViewData(ViewHolder holder, int position, Object obj) {
		// TODO Auto-generated method stub
		ClassifyHolder viewholder = (ClassifyHolder) holder;
		if(obj instanceof ClassifyEntity){
			ClassifyEntity classify = (ClassifyEntity) obj;
			viewholder.tv.setText(classify.getCat_name());
		}
	}

	@Override
	protected ViewHolder setHolder() {
		// TODO Auto-generated method stub
		return new ClassifyHolder();
	}
	
	class ClassifyHolder extends ViewHolder{
		TextView tv;;
	}

	@Override
	protected void setLayoutResource(int position) {
		// TODO Auto-generated method stub
		
	}

}
