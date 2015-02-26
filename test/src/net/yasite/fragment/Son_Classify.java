package net.yasite.fragment;

import java.util.List;

import net.yasite.activity.ShowGoods;
import net.yasite.adapter.ClassifyAdapter;
import net.yasite.entity.ClassifyEntity;
import net.yasite.entity.ClassifyInfoEntity;
import net.yasite.model.ClassifyModel;
import net.yasite.net.HandlerHelp;
import net.yasite.test.R;
import net.yasite.view.XListView;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class Son_Classify extends Fragment{
	
	private Context context;
	private ClassifyInfoEntity info;
	private List<ClassifyEntity> classifys;
	private ClassifyModel model;
	private XListView xlistview;
	private int id = 1;
	private ClassifyAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		context = getActivity();
		View son_view = inflater.inflate(R.layout.fragment_son_classify, null);
		xlistview = (XListView) son_view.findViewById(R.id.list_view);
		xlistview.setOnItemClickListener(listener);
		setModel();
		return son_view;
	}
	
	public void setModel(){
		model = new ClassifyModel(context);
		
		Bundle b = getArguments();
		if(b!=null){
			this.id = getArguments().getInt("id");
		}
		new Classify(context).execute();
	}
	//更新列表显示
	public void changeShow(int id){
		this.id = id;
		new Classify(context).execute();
	}
	
	OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			String cat_id = classifys.get(position).getParent_id();
			Intent in = new Intent(context, ShowGoods.class);
			in.putExtra("name", "all");
			startActivity(in);
		}

	};
	
	class Classify extends HandlerHelp{

		public Classify(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void updateUI() {
			// TODO Auto-generated method stub
			adapter = new ClassifyAdapter(context);
			adapter.setList(classifys);
			xlistview.setAdapter(adapter);
		}

		@Override
		public void doTask(Message msg) throws Exception {
			// TODO Auto-generated method stub
			info = model.requestClassify(id);
			classifys = info.getData();
		}
		
		@Override
		public void doTaskAsNoNetWork(Message msg) throws Exception {
			// TODO Auto-generated method stub
			
		}
	}

}
