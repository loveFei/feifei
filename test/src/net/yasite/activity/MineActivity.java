package net.yasite.activity;

import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import net.yasite.net.HandlerHelp;
import net.yasite.test.BaseNewActivity;
import net.yasite.test.R;
import net.yasite.util.SavaDataUtil;

public class MineActivity extends BaseNewActivity{
	
	private ImageView iv_image;
	private TextView username,userinfo;
	private Button btn_order,btn_set,btn_merchant;
	private String s_name,s_pwd,s_userid,s_token;//保存到SharedPreferences中的值
	@Override
	public void setupView() {
		
		// TODO Auto-generated method stub
		iv_image = (ImageView) findViewById(R.id.imageView_user);
		username = (TextView) findViewById(R.id.textView_user_name);
		userinfo = (TextView)findViewById(R.id.textView_user_info);
		//按钮
		btn_order = (Button)  findViewById(R.id.button_order);//我的订单
		btn_set = (Button)  findViewById(R.id.button_set);//设置
		btn_merchant = (Button) findViewById(R.id.button_merchant);//成为商家
		//按钮监听
		userinfo.setOnClickListener(l);
		btn_order.setOnClickListener(l);
		btn_set.setOnClickListener(l);
		btn_merchant.setOnClickListener(l);
		
		//得到保存到SharedPreferences中的值
		if(s_name!=null){
			username.setText(s_name);
		}
		
	}
	
	OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.textView_user_info://用户详情
				
				break;
			case R.id.button_order://我的订单
				
				break;
			case R.id.button_set://设置
				
				break;
			case R.id.button_merchant://成为商家
				
				break;

			default:
				break;
			}
		}
	};

	@Override
	public void setContent() {
		// TODO Auto-generated method stub
		SavaDataUtil util = new SavaDataUtil(context);
		Map<String, ?> map = util.getAllDate("userinfo", MODE_PRIVATE);
		if(!map.isEmpty()){
			s_name = (String) map.get("user_name");
			if(!s_name.equals("")&&s_name != null){
				s_pwd =  (String) map.get("password");
				s_userid = (String) map.get("user_id");
				s_token = (String) map.get("token");
			}else{
				Intent in2 = new Intent(context, LoginActivity.class);
				startActivity(in2);
			}
		}else{
			Intent in1 = new Intent(context, LoginActivity.class);
			startActivity(in1);
		}
		setContentView(R.layout.activity_mine);
	}
	
	@Override
	public void setModel() {
		// TODO Auto-generated method stub
		
	}
	
	//返回值是true的话，setupView和setModel才会执行
	@Override
	public boolean getIntentValue() {
		// TODO Auto-generated method stub
		return true;
	}

}
