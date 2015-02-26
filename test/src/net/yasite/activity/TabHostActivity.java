package net.yasite.activity;


import java.lang.reflect.Field;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import net.yasite.test.BaseNewActivity;
import net.yasite.test.FriendCircleActivity;
import net.yasite.test.R;

public class TabHostActivity extends TabActivity{
	
	private View tab_homepage,tab_classify,tab_car,tab_my;
	//tabhost title
	private View[] tabs = new View[]{tab_homepage,tab_classify,tab_car,tab_my};
	//image
	private int[] tabhost_images = new int[]{R.drawable.tabhost_homepage,R.drawable.tabhost_class,R.drawable.tabhost_car,R.drawable.tabhost_my};
	//tabhost 的 content
	private Class[] activitys = new Class[]{HomePageActivity.class,ClassifyActivity.class,ShoppingCarActivity.class,MineActivity.class}; 
	//TabSpec
	private String[] title = new String[]{"tab1","tab2","tab3","tab4"};

	private Context context;
	
	private int mtabIndex = 3;//默认显示的界面
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabhost);
		context = this;
		TabHost tabhost = this.getTabHost();
		/**
		 * 
		 */
		Field mCurrentTab = null;
		try {
			mCurrentTab = tabhost.getClass()
			        .getDeclaredField("mCurrentTab");
			mCurrentTab.setAccessible(true);
			mCurrentTab.setInt(tabhost, -2);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		/**
		 * 其他Activity跳转到该Tabhost时候，可以指定显示那个TabSpec
		 * tab1 对应0  ---》默认显示
		 * tab2 对应1
		 * tab3 对应2
		 * tab4 对应3
		 */
		Intent intent = getIntent();
		if(intent!=null){
			mtabIndex = intent.getIntExtra("mtabIndex", 0);
		}
		
		
		for(int i=0;i<tabs.length;i++){
			 tabs[i] = (View) LayoutInflater.from(this).inflate(R.layout.activity_tabhost_widget, null);
			 ImageView image = (ImageView) tabs[i].findViewById(R.id.tab_image);
			 image.setImageResource(tabhost_images[i]);
		}
		tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator(tabs[0]).setContent(new Intent(context, HomePageActivity.class)));
		tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator(tabs[1]).setContent(new Intent(context, ClassifyActivity.class)));
		tabhost.addTab(tabhost.newTabSpec("tab3").setIndicator(tabs[2]).setContent(new Intent(context, ShoppingCarActivity.class)));
		tabhost.addTab(tabhost.newTabSpec("tab4").setIndicator(tabs[3]).setContent(new Intent(context, MineActivity.class))); 
		
		try {
            if (mtabIndex == 0) {
           	 mCurrentTab.setInt(tabhost, 1);
            }
            else {
           	 mCurrentTab.setInt(tabhost, 0);
            }
		  }
	     catch (Exception e){
	             e.printStackTrace();
	     }
		 
		tabhost.setCurrentTab(mtabIndex); //设置默认显示界面
		
	}

}
