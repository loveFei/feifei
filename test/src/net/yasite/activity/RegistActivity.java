package net.yasite.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import net.yasite.test.BaseNewActivity;
import net.yasite.test.R;

public class RegistActivity extends BaseNewActivity{
	
	private Button btn_user,btn_merchant,btn_back;

	@Override
	public void setupView() {
		// TODO Auto-generated method stub
		btn_back = (Button) findViewById(R.id.button_back);
		btn_user = (Button) findViewById(R.id.button_regist_user);
		btn_merchant = (Button) findViewById(R.id.button_regist_merchant);
		
		btn_back.setOnClickListener(l);
		btn_user.setOnClickListener(l);
		btn_merchant.setOnClickListener(l);
	}
	
	OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.button_regist_user:
				Intent in = new Intent(context, RegistUser.class);
				startActivity(in);
				break;
			case R.id.button_regist_merchant:
				Intent in2 = new Intent(context, RegistUser.class);
				startActivity(in2);
				break;
			case R.id.button_back:
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
		setContentView(R.layout.activity_regist);
	}

	@Override
	public void setModel() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean getIntentValue() {
		// TODO Auto-generated method stub
		return true;
	}

}
