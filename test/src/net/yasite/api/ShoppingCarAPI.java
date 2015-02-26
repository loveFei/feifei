package net.yasite.api;

import java.util.List;

import net.yasite.api.params.BaseHttpParam;
import net.yasite.api.params.ShoppingCarParams;
import net.yasite.entity.ShoppingCarDataEntity;
import net.yasite.entity.ShoppingCarInfoEntity;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.content.Context;

public class ShoppingCarAPI extends BaseAPI{

	public ShoppingCarAPI(Context context, List<NameValuePair> pm,
			ShoppingCarParams params) {
		super(context, pm, params);
		// TODO Auto-generated constructor stub
		setMethod("http://www.yasite.net/shopapi/index.php/cartController/getGoodList/"+params.getId());
	}
	
	@Override
	public ShoppingCarInfoEntity handlerResult(JSONObject json) throws JSONException {
		// TODO Auto-generated method stub
		return new Gson().fromJson(json.toString(), ShoppingCarInfoEntity.class);
	}

}
