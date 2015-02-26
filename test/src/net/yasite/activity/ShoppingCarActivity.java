package net.yasite.activity;

import java.util.List;
import java.util.Map;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import net.yasite.adapter.ShoppingCarAdapter;
import net.yasite.entity.ShoppingCarEntity;
import net.yasite.entity.ShoppingCarInfoEntity;
import net.yasite.model.GoodsModel;
import net.yasite.net.HandlerHelp;
import net.yasite.test.BaseNewActivity;
import net.yasite.test.R;
import net.yasite.util.SavaDataUtil;
import net.yasite.view.XListView;

public class ShoppingCarActivity extends BaseNewActivity {
	
	private String userid,token;
	private GoodsModel model;
	private ShoppingCarInfoEntity carinfo = null;
	private List<ShoppingCarEntity> data;
	private BroadcastReceiver broadcast;
	private XListView xlistview;
	private CheckBox check_box;
	private TextView sumprice;
	private Button btn_pay;
	private ShoppingCarAdapter adapter;
	private StringBuffer rec_ids = null;
	private String ids = null;
	private TextView bianji;
	
	@Override
	public void setupView() {
		// TODO Auto-generated method stub
		xlistview = (XListView) findViewById(R.id.list_view);
		check_box = (CheckBox) findViewById(R.id.checkBox_all);
		sumprice = (TextView) findViewById(R.id.textview_sumprice);
		btn_pay = (Button) findViewById(R.id.button_pay);
		bianji = (TextView) findViewById(R.id.textview_bianji);
		check_box.setOnCheckedChangeListener(listener);
		bianji.setOnClickListener(l);
		
		xlistview.setOnItemClickListener(lis);
		registerForContextMenu(xlistview);//注册上下文菜单
		
		broadcast = new MyBroadcast();//广播
		IntentFilter intentFilter = new IntentFilter("car");
		registerReceiver(broadcast, intentFilter);//注册广播
	}
	
	OnItemClickListener lis = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Toast.makeText(context, data.get(position).getRec_id()+"**********", 0).show();
		}
	};
	OnCheckedChangeListener listener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			for(ShoppingCarEntity g:data){
				g.setIschecked(isChecked);
			}
			adapter.notifyDataSetChanged();
		}
	};
	
	OnClickListener l = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			rec_ids = new StringBuffer("[");
			for(ShoppingCarEntity g:data){
				if(g.isIschecked()){
					rec_ids.append(g.getRec_id());
					rec_ids.append(",");
				}
			}
			rec_ids.deleteCharAt(rec_ids.length()-1);
			rec_ids.append("]");
			ids = rec_ids.toString();
			Toast.makeText(context, "删除成功", 0).show();
			new ShoppingCarInfo(context).execute();
			
			carinfo = null;
			setModel();
		}
	};

	@Override
	public void setContent() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_shoppingcar);
	}

	@Override
	public void setModel() {
		// TODO Auto-generated method stub
		model = new GoodsModel(context);
		adapter = new ShoppingCarAdapter(context);
		getUserInfo();
		
	}
	
	public void getUserInfo(){
		SavaDataUtil util = new SavaDataUtil(context);
		Map<String, ?> map = util.getAllDate("userinfo", MODE_PRIVATE);
		if(!map.isEmpty()){
			userid = (String) map.get("user_id");
			if(userid == null){
				Intent in2 = new Intent(context, LoginActivity.class);
				in2.putExtra("GoodsInfoActivity", "GoodsInfoActivity");
				startActivity(in2);
			}else{
				token = (String) map.get("token");
				Toast.makeText(context, "亲，已登录2"+userid+" "+token, 1).show();
				new ShoppingCarInfo(context).execute();
			}
		}else{
			Intent in1 = new Intent(context, LoginActivity.class);
			in1.putExtra("GoodsInfoActivity", "GoodsInfoActivity");
			startActivity(in1);
			Toast.makeText(context, "亲，还没有登录1", 1).show();
		}
	}
	
	@Override
	public boolean getIntentValue() {
		// TODO Auto-generated method stub
		return true;
	}
	//创建上下文菜单
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.shoppingcar, menu);
//		menu.setHeaderTitle("菜单");      
//        menu.add(0, 1, 0,"删除");
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	//上下文菜单的监听
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo)item.getMenuInfo();
		int position = menuInfo.position;
		switch (item.getItemId()) {
		case R.id.menu_delete:
			int rec_id = Integer.parseInt(data.get(position).getRec_id());
			rec_ids = new StringBuffer();
			rec_ids.append("[");
			rec_ids.append(rec_id+"");
			rec_ids.append("]");
			ids = rec_ids.toString();
			data.remove(position);
			new ShoppingCarInfo(context).execute();
			break;

		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
	
	class MyBroadcast extends BroadcastReceiver{
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if(intent.getAction().equals("car")){
				float price = intent.getFloatExtra("sum", 0);
				sumprice.setText(price+"");
			}
		}
		
	}
	
	
	public class ShoppingCarInfo extends HandlerHelp{

		public ShoppingCarInfo(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void updateUI() {
			// TODO Auto-generated method stub
			adapter.setList(data);//给适配器传值
			xlistview.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}

		@Override
		public void doTask(Message msg) throws Exception {
			// TODO Auto-generated method stub
			if(carinfo == null){
				carinfo = model.requestShoppingCarList(userid, token);
				data = carinfo.getData();
				Log.e("dddd", data.toString());
			}
			if(ids!=null){
				model.requestDeleteShoppingCar(token, ids, userid);
				ids = null;
			}
		}
		@Override
		public void doTaskAsNoNetWork(Message msg) throws Exception {
			// TODO Auto-generated method stub
			
		}
	}

}
