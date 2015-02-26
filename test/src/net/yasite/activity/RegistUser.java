package net.yasite.activity;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import net.yasite.entity.UserMsg;
import net.yasite.model.LoginModel;
import net.yasite.net.HandlerHelp;
import net.yasite.test.BaseNewActivity;
import net.yasite.test.R;
import net.yasite.util.SavaDataUtil;

public class RegistUser extends BaseNewActivity{
	
	private Button btn_back,btn_ok,btn_getcode;
	private EditText e_phone,e_pwd,e_ok_code,e_pwd_ok;
	private LoginModel model;
	private String username,pwd,pwd2;
	private UserMsg usermsg;
	
	@Override
	public void setupView() {
		// TODO Auto-generated method stub
		e_phone = (EditText) findViewById(R.id.editText1);
		e_pwd = (EditText) findViewById(R.id.editText3);
		e_ok_code = (EditText) findViewById(R.id.editText2);
		e_pwd_ok = (EditText) findViewById(R.id.editText4);
		
		btn_back = (Button) findViewById(R.id.button_regist_back1);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		
		btn_back.setOnClickListener(l);
		btn_ok.setOnClickListener(l);
	}

	OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.button_regist_back1:
				finish();
				break;
			case R.id.btn_ok:
				regist();
				break;
				
			default:
				break;
			}
		}
	};
	
	public void regist(){
		username = model.editText(e_phone);
		pwd = model.editText(e_pwd);
		pwd2 = model.editText(e_pwd_ok);
		boolean b1= model.checkPwdLocal(pwd);//判断密码是否为空
		boolean b2 = model.checkInfo(pwd, pwd2);
		if(b1&&b2){
			new Regist(context).execute();
		}
		
	}
	@Override
	public void setContent() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_regist_user);
	}

	@Override
	public void setModel() {
		// TODO Auto-generated method stub
		model = new LoginModel(context);
	}

	@Override
	public boolean getIntentValue() {
		// TODO Auto-generated method stub
		return true;
	}
	
	class Regist extends HandlerHelp{

		public Regist(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void updateUI() {
			// TODO Auto-generated method stub
			SavaDataUtil util = new SavaDataUtil(context);
			Map<String, String> map = new HashMap<String, String>();
			map.put("user_id", usermsg.getData().getUser_id()+"");
			map.put("user_name", usermsg.getData().getUser_name());
			map.put("password", usermsg.getData().getPassword());
			map.put("token", usermsg.getData().getToken());
			util.saveData("userinfo", MODE_PRIVATE, map);
			
			String imagePath = usermsg.getData().getHeadimg();//图片路径
			
			Intent in = new Intent(context, TabHostActivity.class);
			startActivity(in);
			finish();
		}

		@Override
		public void doTask(Message msg) throws Exception {
			// TODO Auto-generated method stub
			int regist=1;
			// who 的值0代表是login请求过来的值，1代表是regist请求过来的值
			usermsg = model.requestUserMessage(username,pwd,regist);
		}

		@Override
		public void doTaskAsNoNetWork(Message msg) throws Exception {
			// TODO Auto-generated method stub
			
		}

		
	}

}
