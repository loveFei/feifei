package net.yasite.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import net.yasite.fragment.Son_Classify;
import net.yasite.test.BaseNewActivity;
import net.yasite.test.R;

public class ClassifyActivity extends BaseNewActivity {

	FragmentTransaction transaction;
	
	@Override
	public void setupView() {
		// TODO Auto-generated method stub
		Son_Classify son = new Son_Classify();
		addFragment(son);
		
	}
	
	public void addFragment(Fragment fragment){
		transaction = getSupportFragmentManager().beginTransaction();
        // 用这个fragment替换任何在fragment_container中的东西
        // 并添加事务到back stack中以便用户可以回退到之前的状态
        transaction.add(R.id.linearlayout_fragment, fragment);
        transaction.addToBackStack(null);
        // 提交事务
        transaction.commit();
	}

	@Override
	public void setContent() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_classify);
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
