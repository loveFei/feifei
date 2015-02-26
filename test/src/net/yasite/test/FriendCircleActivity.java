package net.yasite.test;

import android.content.Context;
import android.os.Message;
import net.yasite.adapter.GoodsListAdapter;
import net.yasite.entity.GoodsListEntity;
import net.yasite.model.GoodsModel;
import net.yasite.net.HandlerHelp;
import net.yasite.view.XListView;

/**
 * @author 孟文斌
 */

public class FriendCircleActivity extends BaseNewActivity {
	
	XListView xListView;
	GoodsModel goodsModel;
	GoodsListEntity goodsListEntity;
	GoodsListAdapter adapter;
	
	//setup设置view组件
	@Override
	public void setupView() {
		// TODO Auto-generated method stub
		xListView = (XListView) findViewById(R.id.list_view);
	}

	//传一个setContextView，加载布局文件
	@Override
	public void setContent() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_main);
	}
	//c掉m，activity调用Model，用于实例化model
	@Override
	public void setModel() {
		// TODO Auto-generated method stub
		/**
		 *该context在BaseNewActivity抽象类中已经定义过了*/
		goodsModel = new GoodsModel(context);
		adapter = new GoodsListAdapter(context);
		/**
		 * 启动线程
		 */
		new GetGoodsList(context).execute();
		
	}
	//Intent有没有返回value值，如果不需要，return true
	@Override
	public boolean getIntentValue() {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
	 * 线程和handler
	 * @author 孟文斌
	 */
	class GetGoodsList extends HandlerHelp{

		public GetGoodsList(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
		
		//在doTast或者doTaskAsNoNetWork方法调用完后，执行UI更新
		@Override
		public void updateUI() {
			// TODO Auto-generated method stub
			if(goodsListEntity!=null){
				System.out.println(goodsListEntity.getData().size());
				xListView.setAdapter(adapter);
				adapter.setList(goodsListEntity.getData());
				adapter.notifyDataSetChanged();
			}
			
			
		}
		//有网络的时候调用该方法
		@Override
		public void doTask(Message msg) throws Exception {
			// TODO Auto-generated method stub
			goodsListEntity = goodsModel.RequestGoodsList(null);
			
		}
		//无网络的时候会调用该方法
		@Override
		public void doTaskAsNoNetWork(Message msg) throws Exception {
			// TODO Auto-generated method stub
			
		}
		
	}

}
