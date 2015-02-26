package net.yasite.activity;

import java.util.List;
import java.util.Map;

import cache.loader.ImageWorker;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import net.yasite.entity.GoodEntity;
import net.yasite.entity.GoodsEntity;
import net.yasite.entity.GoodsListEntity;
import net.yasite.entity.ShoppingCarDataEntity;
import net.yasite.entity.ShoppingCarEntity;
import net.yasite.model.GoodsModel;
import net.yasite.net.HandlerHelp;
import net.yasite.test.BaseNewActivity;
import net.yasite.test.R;
import net.yasite.util.SavaDataUtil;

public class GoodsInfoActivity extends BaseNewActivity{
	
	private TextView tv_back,tv_share,goodsname,marketprice,shopprice;
	private ImageView image_goods;
	private Button btn_look,btn_shoppingcar,btn_addGoods;
	private int requestCode = 0;//请求码
	private GoodsModel model;
	private String goodsid;
	
	private String userid,token;
	
	private ShoppingCarDataEntity carData;
	private ShoppingCarEntity car;
	
	private GoodsListEntity goodsinfos;
	private List<GoodsEntity> goodsinfo;
	private GoodsEntity goods;
	
	@Override
	public void setupView() {
		// TODO Auto-generated method stub
		tv_back = (TextView) findViewById(R.id.textView_back);
		tv_share = (TextView) findViewById(R.id.textView_share);
		
		image_goods = (ImageView) findViewById(R.id.imageView_goods);
		goodsname = (TextView) findViewById(R.id.textView_goods_name);
		marketprice = (TextView) findViewById(R.id.textView_market_price);
		shopprice = (TextView) findViewById(R.id.textView_shop_price);
		
		btn_look = (Button) findViewById(R.id.button_look);
		btn_shoppingcar = (Button) findViewById(R.id.button_shopping_car);
		btn_addGoods = (Button) findViewById(R.id.button_addToShoppingCar);
		
		btn_look.setOnClickListener(l);
		btn_shoppingcar.setOnClickListener(l);
		btn_addGoods.setOnClickListener(l);
		
	}
	
	public void executeRequestId(){
		Intent in = getIntent();
		goodsid = in.getStringExtra("goods_id");
		requestCode = 0;//请求码=0，请求商品数据
		new AddShoppingCar(context).execute();
	}
	
	OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.button_look:
				
				break;
			case R.id.button_shopping_car:
				
				break;
			case R.id.button_addToShoppingCar:
				Toast.makeText(context, "加入购物车", 0).show();
				addToShoppingCar();
				break;

			default:
				break;
			}
		}
		
	};
	
	public void addToShoppingCar(){
		requestCode = 1;//请求码=0，请求add购物车
		SavaDataUtil util = new SavaDataUtil(context);
		Map<String, ?> map = util.getAllDate("userinfo", MODE_PRIVATE);
		if(!map.isEmpty()){
			token = (String) map.get("token");
			if(!token.equals("")&&token != null){
				userid = (String) map.get("user_id");
			}else{
				Intent in2 = new Intent(context, LoginActivity.class);
				in2.putExtra("GoodsInfoActivity", "GoodsInfoActivity");
				startActivity(in2);
			}
		}else{
			Intent in1 = new Intent(context, LoginActivity.class);
			in1.putExtra("GoodsInfoActivity", "GoodsInfoActivity");
			startActivity(in1);
		}
		
		new AddShoppingCar(context).execute();
	}
	
	@Override
	public void setContent() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_goodsinfo);
		
	}

	@Override
	public void setModel() {
		// TODO Auto-generated method stub
		model = new GoodsModel(context);
		executeRequestId();
	}
	
	@Override
	public boolean getIntentValue() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public class AddShoppingCar extends HandlerHelp{

		public AddShoppingCar(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			
		}

		@Override
		public void updateUI() {
			// TODO Auto-generated method stub
			ImageWorker.getImage(context, "http://www.yasite.net/ecshop/"+goods.getGoods_img(), image_goods, R.drawable.ic_launcher);
			goodsname.setText(goods.getGoods_name());
			marketprice.setText("市场价:"+goods.getMarket_price());
			shopprice.setText("本店价:"+goods.getShop_price());
			if(car!=null){
				Toast.makeText(context, "添加成功", 1).show();
			}
		}

		@Override
		public void doTask(Message msg) throws Exception {
			// TODO Auto-generated method stub
			if(requestCode == 0){// goods info
				goodsinfos = model.requestGoodsInfoById(goodsid);
				goodsinfo =  goodsinfos.getData();
				goods = goodsinfo.get(0);
			}else if(requestCode == 1){//add shopping car
				Log.e("***444***", "token:"+token+" userid:"+userid+" "+Integer.parseInt(goods.getGoods_id())+" "+goods.getGoods_sn()+" "+goods.getGoods_name()+" "+Float.parseFloat(goods.getMarket_price())+" "+Float.parseFloat(goods.getShop_price())+" 1");
				carData = model.requestAddShoppingCar(token, userid, Integer.parseInt(goods.getGoods_id()), goods.getGoods_sn(), goods.getGoods_name(), Float.parseFloat(goods.getMarket_price()), Float.parseFloat(goods.getShop_price()), 1);
				car = carData.getData();
			}
			
		}

		@Override
		public void doTaskAsNoNetWork(Message msg) throws Exception {
			// TODO Auto-generated method stub
			
		}
		
	}

}
