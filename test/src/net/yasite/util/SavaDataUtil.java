package net.yasite.util;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SavaDataUtil {
	
	private Context context;
	
	public SavaDataUtil(Context context) {
		super();
		this.context = context;
	}
	
	public void saveData(String name,int mode,Map<String, String> map){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		Editor edit = sp.edit();
		for(Map.Entry<String ,String> m:map.entrySet()){
			String key = m.getKey();
			String value = m.getValue();
			edit.putString(key,value);
			edit.commit();
		}
	}
	
	/**
	 * 得到单条String类型数据*/
	public String getData(String name, int mode, String key){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		return sp.getString(key, ""); //得到数据
	}
	
	/**
	 * 得到所有数据Map集合*/
	public Map<String, ?> getAllDate(String name,int mode){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		return sp.getAll();
	}

}
