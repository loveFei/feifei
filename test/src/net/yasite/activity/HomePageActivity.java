package net.yasite.activity;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import net.yasite.test.BaseNewActivity;
import net.yasite.test.R;

public class HomePageActivity extends BaseNewActivity{

	private ViewPager vp;
	private int imageIds[];
	private ArrayList<ImageView> images;
	private ViewPagerAdapter adapter;
	private int currentItem; //当前页面
	private ScheduledExecutorService scheduledExecutorService;
	private EditText search;
	private ImageView erweima;
	
	@Override
	public void setupView() {
		// TODO Auto-generated method stub
		vp = (ViewPager) findViewById(R.id.viewpager);
		showViewPager();//viewpage自动轮播图片
		search = (EditText) findViewById(R.id.editText_search);
		erweima = (ImageView) findViewById(R.id.image_button_erweima);
		
		search.setOnClickListener(l);
		erweima.setOnClickListener(l);
	}
	
	OnClickListener l = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.editText_search:
				Intent in1 = new Intent(context, SearchActivity.class);
				startActivityForResult(in1, 1);
				break;
			case R.id.image_button_erweima:
				Intent in2 = new Intent(context, TabHostActivity.class);
				in2.putExtra("mtabIndex", 1);//跳转到分类界面
				startActivity(in2);
				break;

			default:
				break;
			}
		}
	};
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==1&&resultCode==RESULT_OK){
			String searchValue = data.getStringExtra("searchname");
			if(searchValue.equals("all")){
				Intent in3 = new Intent(context, ShowGoods.class);
				in3.putExtra("name", "all");
				startActivity(in3);
			}else{
				Intent in4 = new Intent(context, ShowGoods.class);
				in4.putExtra("name", searchValue);
				startActivity(in4);
			}
		}
	}

	@Override
	public void setContent() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_homepage);
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
	
	/**
	 * 以下内容是自动轮播图片的实现
	 */
	public void showViewPager(){
		 //图片ID
	    imageIds = new int[]{R.drawable.vp1,R.drawable.vp2,R.drawable.vp3,R.drawable.vp4};
	    images = new ArrayList<ImageView>();
	    for(int i =0; i < imageIds.length; i++){
	    	ImageView imageView = new ImageView(this);
//	    	imageView.setLayoutParams(new LayoutParams(320, 110));
	        imageView.setBackgroundResource(imageIds[i]);
	        images.add(imageView);
	    }
	    adapter = new ViewPagerAdapter(); 
	    vp.setAdapter(adapter);
	    vp.setOnPageChangeListener(new OnPageChangeListener() {
	        @Override
	        public void onPageSelected(int position) {
	        	currentItem = position;
	        }
	        @Override
	        public void onPageScrolled(int arg0, float arg1, int arg2) {
	        }
	        @Override
	        public void onPageScrollStateChanged(int arg0) {
	        }
	    });
	}
	@Override
	protected void onStart() {
	   // TODO Auto-generated method stub
	   super.onStart();
	   scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
	   //每隔2秒钟切换一张图片
	   scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 2, 2, TimeUnit.SECONDS);
	}
	//切换图片
	private class ViewPagerTask implements Runnable {
	   @Override
	   public void run() {
	     currentItem = (currentItem +1) % imageIds.length;
	     //更新界面
	     handler.obtainMessage().sendToTarget();
	   }
	}
	private Handler handler = new Handler(){
	   @Override
	   public void handleMessage(Message msg) {
	     //设置当前页面
		   vp.setCurrentItem(currentItem);
	   }
	};
	private class ViewPagerAdapter extends PagerAdapter {
	    @Override
	    public int getCount() {
	      return images.size();
	    }
	    //是否是同一张图片
	    @Override
	    public boolean isViewFromObject(View arg0, Object arg1) {
	      return arg0 == arg1;
	    }
	    @Override
	    public void destroyItem(ViewGroup view, int position, Object object) {
	      view.removeView(images.get(position));
	    }
	    @Override
	    public Object instantiateItem(ViewGroup view, int position) {
	      view.addView(images.get(position));
	      return images.get(position);
	    }
	}
}
