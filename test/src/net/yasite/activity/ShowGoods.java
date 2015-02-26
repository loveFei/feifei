package net.yasite.activity;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import net.yasite.adapter.GoodsListAdapter;
import net.yasite.entity.GoodsEntity;
import net.yasite.entity.GoodsListEntity;
import net.yasite.model.GoodsModel;
import net.yasite.net.HandlerHelp;
import net.yasite.test.BaseNewActivity;
import net.yasite.test.R;
import net.yasite.view.XListView;


public class ShowGoods extends BaseNewActivity {

	private EditText e_search;
	private ImageView iv_erweima;
	private RadioGroup radioGroup;
	private XListView xlistview;
	private int page = 1;
	private GoodsModel goodsmodel;
	private GoodsListEntity goodslist;
	private List<GoodsEntity> list;
	private String name = "all";
	private GoodsListAdapter adapter;
	private int requestcode = 1;
	private String pag = "";
	
	@Override
	public void setupView() {
		// TODO Auto-generated method stub
		e_search = (EditText) findViewById(R.id.editText_search);
		iv_erweima = (ImageView) findViewById(R.id.image_button_erweima);
		xlistview = (XListView) findViewById(R.id.list_view);
		
		e_search.setOnClickListener(l);
		iv_erweima.setOnClickListener(l);
		xlistview.setOnItemClickListener(listview_listener);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		radioGroup.setOnCheckedChangeListener(listener);
		
	}
	
	OnItemClickListener listview_listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			GoodsEntity goods = list.get(position);
			String goods_id = goods.getGoods_id();
			Intent intent = new Intent(context, GoodsInfoActivity.class);
			intent.putExtra("goods_id", goods_id);
			startActivity(intent);
		}
		
	};
	
	//search
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==1&&resultCode==RESULT_OK){
			String namevalue = data.getStringExtra("searchname");
			if(namevalue.equals("all")){
				requestcode = 0;//请求code == 0 代表，调用 综合查询 商品 
				new Showgoods(context).execute();
			}else{
				e_search.setText(namevalue);
				requestcode = 1;//请求code == 1 代表，调用 根据名字查询 商品 
				name = namevalue;
				new Showgoods(context).execute();
			}
		}
	}
	
	
	OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.editText_search:
				Intent intent = new Intent(context, SearchActivity.class);
				startActivityForResult(intent, 1);
				break;
			case R.id.image_button_erweima:
				Intent intent2 = new Intent(context, SearchActivity.class);
				startActivityForResult(intent2, 1);
				break;

			default:
				break;
			}
		}
	};
	
	OnCheckedChangeListener listener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			switch (checkedId) {
			case R.id.radio1://综合
				page = 2;
				executeThread();
				break;
			case R.id.radio2://销量
				page = 1;
				executeThread();
				break;
			case R.id.radio3://价格
				page = 3;
				executeThread();
				break;
			case R.id.radio4://筛选
				page = 4;
				executeThread();
				break;
				
			default:
				break;
			}
		}
	};
	
	@Override
	public void setContent() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_show_goods);
	}

	@Override
	public void setModel() {
		// TODO Auto-generated method stub
		goodsmodel = new GoodsModel(context);
		adapter = new GoodsListAdapter(context);
		executeThread();
	}

	@Override
	public boolean getIntentValue() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//执行线程
	public void executeThread(){
		Intent in = getIntent();
		name = in.getStringExtra("name");
		if(name.equals("all")){
			requestcode = 0;//请求code == 0 代表，调用 综合查询 商品 
			new Showgoods(context).execute();
		}else{
			requestcode = 1;//请求code == 1 代表，调用 根据名字查询 商品 
			new Showgoods(context).execute();
		}
	}
	
	public class Showgoods extends HandlerHelp{

		public Showgoods(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void updateUI() {
			// TODO Auto-generated method stub
			if(list!=null){
				System.out.println("//////*******"+goodslist.getData().toString());
				adapter.setList(list);
				xlistview.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}
		}

		@Override
		public void doTask(Message msg) throws Exception {
			// TODO Auto-generated method stub
			if(requestcode == 1){
				goodslist = goodsmodel.requestGoodsListByName(name, page);
			}else if(requestcode == 0){//查询所有商品
				goodslist = goodsmodel.RequestGoodsList(pag);
			}
			list = goodslist.getData();
		}

		@Override
		public void doTaskAsNoNetWork(Message msg) throws Exception {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
