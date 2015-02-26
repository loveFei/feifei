package net.yasite.api;

import net.yasite.api.params.BaseHttpParam;
import net.yasite.api.params.GoodInfoParams;
import net.yasite.api.params.Urls;
import net.yasite.entity.GoodEntity;
import net.yasite.entity.GoodsListEntity;
import net.yasite.entity.ShoppingCarDataEntity;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.content.Context;

public class GoodInfoAPI extends BaseAPI {

	public GoodInfoAPI(Context context, GoodInfoParams pm) {
		super(context, pm);
//		setMethod(Urls.WEB_SERVER_PATH 
//				+ Urls.EC + Urls.GOODSINFO 
//				+ "?id=" + pm.getId());
		setMethod("http://www.yasite.net/shopapi/index.php/goodController/getGoodInfo/"+pm.getId());
	}
	
	@Override
	public GoodsListEntity handlerResult(JSONObject json) throws JSONException {
		// TODO Auto-generated method stub
		return new Gson().fromJson(json.toString(), GoodsListEntity.class);
	}
}
