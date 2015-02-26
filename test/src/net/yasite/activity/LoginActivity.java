package net.yasite.activity;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import net.yasite.entity.GoodsListEntity;
import net.yasite.entity.UserMsg;
import net.yasite.model.LoginModel;
import net.yasite.net.HandlerHelp;
import net.yasite.test.BaseNewActivity;
import net.yasite.test.R;
import net.yasite.util.SavaDataUtil;

public class LoginActivity extends BaseNewActivity {
	
	private EditText e_username,e_userpwd;
	private Button btn_login,btn_back;
	private TextView tv_regist;
	LoginModel loginmodel;
	private UserMsg usermsg;
	private String username,pwd;
	private int requestCode = 0;
	private String getintent = "";

	@Override
	public void setupView() {
		// TODO Auto-generated method stub
		e_username = (EditText) findViewById(R.id.editText_username);
		e_userpwd = (EditText) findViewById(R.id.editText_password);
		
		btn_login = (Button) findViewById(R.id.button_login);
		btn_back = (Button) findViewById(R.id.button_login_back);
		tv_regist = (TextView) findViewById(R.id.textView_regist);
		
		
		btn_back.setOnClickListener(l);
		btn_login.setOnClickListener(l);
		tv_regist.setOnClickListener(l);
	}
	
	OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.button_login:
				login();
				break;
			case R.id.textView_regist:
				Intent in = new Intent(context, RegistActivity.class);
				startActivity(in);
				break;
			case R.id.button_login_back:
				finish();
				break;

			default:
				break;
			}
		}
	};

	@Override
	public void setContent() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_login);
	}

	@Override
	public void setModel() {
		// TODO Auto-generated method stub
		loginmodel = new LoginModel(context);
	}
	
	@Override
	public boolean getIntentValue() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void login(){
		username = loginmodel.editText(e_username);
		pwd = loginmodel.editText(e_userpwd);
		loginmodel.checkPwdLocal(pwd);//判断密码是否为空
		new Login(context).execute();
	}
	class Login extends HandlerHelp{

		public Login(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void updateUI() {
			// TODO Auto-generated method stub
			//存储到本地SharedPreferences
			SavaDataUtil util = new SavaDataUtil(context);
			Map<String, String> map = new HashMap<String, String>();
			map.put("user_id", usermsg.getData().getUser_id()+"");
			map.put("user_name", usermsg.getData().getUser_name());
			map.put("password", usermsg.getData().getPassword());
			map.put("token", usermsg.getData().getToken());
			util.saveData("userinfo", MODE_PRIVATE, map);
			
			String imagePath = usermsg.getData().getHeadimg();//图片路径
			
			Intent infrom = getIntent();
			getintent = infrom.getStringExtra("GoodsInfoActivity");
			if(getintent!=null&&!getintent.equals("")&&!getintent.equals("GoodsInfoActivity")){
				requestCode = 1;
			}
			
			Intent in = new Intent(context, TabHostActivity.class);
			if(requestCode == 1){
				in.setClass(context, GoodsInfoActivity.class);
			}else{
				in.putExtra("mtabIndex", 3);//用户信息
			}
			startActivity(in);
			finish();
		}
		
		@Override
		public void doTask(Message msg) throws Exception {
			// TODO Auto-generated method stub
			int login=0;
			// who 的值0代表是login请求过来的值，1代表是regist请求过来的值
			usermsg = loginmodel.requestUserMessage(username,pwd,login);
		}

		@Override
		public void doTaskAsNoNetWork(Message msg) throws Exception {
			// TODO Auto-generated method stub
			
		}
		
	}

}
