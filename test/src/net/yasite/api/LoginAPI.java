package net.yasite.api;

import java.util.List;

import net.yasite.entity.GoodsListEntity;
import net.yasite.entity.UserMsg;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.content.Context;

public class LoginAPI extends BaseAPI{
	
	private String path0="http://www.yasite.net/shopapi/index.php/userController/login";
	private String path1="http://www.yasite.net/shopapi/index.php/userController/register";
	
	public int who = 0;
		
	public void setWho(int who) {
		this.who = who;
	}
	// who 的值0代表是login请求过来的值，1代表是regist请求过来的值
	public LoginAPI(Context context, List<NameValuePair> pm, int who) {
		super(context, pm);
		// TODO Auto-generated constructor stub
		if(who == 0){
			setMethod(path0);
		}else if(who == 1){
			setMethod(path1);
		}
		
	}
	
	@Override
	public UserMsg handlerResult(JSONObject json) throws JSONException {
		// TODO Auto-generated method stub
		return new Gson().fromJson(json.toString(), UserMsg.class);
	}

}
