package net.yasite.activity;

import net.yasite.test.FriendCircleActivity;
import net.yasite.test.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AnimationActivity extends Activity{
	
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_activity);
		context = this;
		Thread th = new Thread(runable);
		th.start();
	}
	
	Runnable runable = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Intent in = new Intent(context, TabHostActivity.class);
			startActivity(in);
		}
	};
}
