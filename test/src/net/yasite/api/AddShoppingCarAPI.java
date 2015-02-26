package net.yasite.api;

import java.util.List;

import net.yasite.api.params.AddShoppingCarParams;
import net.yasite.api.params.BaseHttpParam;
import net.yasite.entity.ShoppingCarDataEntity;
import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.content.Context;
import android.util.Log;

public class AddShoppingCarAPI extends BaseAPI {

	public AddShoppingCarAPI(Context context, List<NameValuePair> pm,
			AddShoppingCarParams params) {
		super(context, pm, params);
		// TODO Auto-generated constructor stub
		setMethod("http://www.yasite.net/shopapi/index.php/cartController/addGood/"+params.getToken());
	}

	@Override
	public ShoppingCarDataEntity handlerResult(JSONObject json) throws JSONException {
		// TODO Auto-generated method stub
		return new Gson().fromJson(json.toString(), ShoppingCarDataEntity.class);
	}

}
