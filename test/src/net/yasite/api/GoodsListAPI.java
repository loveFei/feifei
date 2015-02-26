package net.yasite.api;

import java.util.List;

import net.yasite.api.params.BaseHttpParam;
import net.yasite.api.params.GoodsListParams;
import net.yasite.entity.GoodsListEntity;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.content.Context;

public class GoodsListAPI extends BaseAPI {

	//get请求，用这个构造方法
	public GoodsListAPI(Context context, GoodsListParams pm) {
		super(context, pm);
		// TODO Auto-generated constructor stub
		/**setMethod方法（BaseAPI） 设置api地址*///
		setMethod("http://www.yasite.net/shopapi/index.php/goodController/getGoodList");
	}
	
	//重写该方法，把object该成需要的
	@Override
	public GoodsListEntity handlerResult(JSONObject json) throws JSONException {
		// TODO Auto-generated method stub
		return new Gson().fromJson(json.toString(), GoodsListEntity.class);
	}

}
