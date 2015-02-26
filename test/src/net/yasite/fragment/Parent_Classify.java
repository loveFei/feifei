package net.yasite.fragment;

import java.util.ArrayList;
import java.util.List;

import net.yasite.adapter.ClassifyAdapter;
import net.yasite.entity.ClassifyEntity;
import net.yasite.entity.ClassifyInfoEntity;
import net.yasite.model.ClassifyModel;
import net.yasite.net.HandlerHelp;
import net.yasite.test.R;
import net.yasite.view.XListView;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class Parent_Classify extends Fragment{
	
	private Context context;
	private XListView xlistview;
	private ClassifyModel model;
	private int id1 = 0;
	private ClassifyInfoEntity info;
	private List<ClassifyEntity> classifys;
	private ClassifyAdapter adapter;
	private Son_Classify son;
	private FragmentManager fm;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		context = getActivity();
//		fm = getActivity().getSupportFragmentManager();
//		if(fm!=null){
//			Log.e("////", "/////buweikong");
//		}
//		son = (Son_Classify) fm.findFragmentById(R.id.fragment_classify_son);
		View parent_view = inflater.inflate(R.layout.fragment_parent_classify, null);
		xlistview = (XListView) parent_view.findViewById(R.id.list_view);
		xlistview.setOnItemClickListener(listener);
		setMode();
		return parent_view;
	}
	
	public void setMode(){
		model = new ClassifyModel(context);
		new Classify(context).execute();
	}
	
	OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			id1 = Integer.parseInt(classifys.get(position-1).getCat_id());
			Log.e("ididididid", id1+"");
			if(son!=null){
				son.changeShow(id1);
			}else{
				Log.e("null null null", "null  null");
				Son_Classify son = new Son_Classify();
				//给子分类传值
				Bundle args = new Bundle();
	            args.putInt("id", id1);
	            son.setArguments(args);
	            
	            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
	            // 用这个fragment替换任何在fragment_container中的东西
	            // 并添加事务到back stack中以便用户可以回退到之前的状态
	            transaction.replace(R.id.linearlayout_fragment, son);
	            transaction.addToBackStack(null);
	            // 提交事务
	            transaction.commit();
	            

			}
			
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
			
//			id1 = Integer.parseInt(classifys.get(0).getCat_id());
//			son.changeShow(id1);
			adapter = new ClassifyAdapter(context);
			adapter.setList(classifys);
			xlistview.setAdapter(adapter);
			
		}

		@Override
		public void doTask(Message msg) throws Exception {
			// TODO Auto-generated method stub
			info = model.requestClassify(id1);
			classifys = info.getData();
			Log.e("//////////", "*******");
		}
		
		@Override
		public void doTaskAsNoNetWork(Message msg) throws Exception {
			// TODO Auto-generated method stub
			
		}
	}

}
