package net.yasite.activity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import net.yasite.test.BaseNewActivity;
import net.yasite.test.R;

public class SearchActivity extends BaseNewActivity{
	
	private AutoCompleteTextView auto;
	private Button btn;
	private String name;
	
	@Override
	public void setupView() {
		// TODO Auto-generated method stub
		auto = (AutoCompleteTextView) findViewById(R.id.editText_search);
		btn = (Button) findViewById(R.id.button_search);
		btn.setOnClickListener(l);
	}
	OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			name = auto.getText().toString();
			if(name.equals("")||name == null){
				name = "all";
			}
			Intent intent = new Intent();
			intent.putExtra("searchname", name);
			setResult(RESULT_OK, intent);
			btn.setOnClickListener(l);
			finish(); //关闭本Activity，回到上一个Activity，相当于点击返回按钮
		}
	};

	@Override
	public void setContent() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_search);
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
