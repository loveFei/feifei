package net.yasite.api;

import net.yasite.api.params.BaseHttpParam;
import net.yasite.api.params.ClassifySelectGoods;
import net.yasite.entity.GoodsListEntity;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import android.content.Context;
import android.util.Log;

public class GoodsListByNameAPI extends BaseAPI {

	public GoodsListByNameAPI(Context context, ClassifySelectGoods pm) {
		super(context, pm);
		// TODO Auto-generated constructor stub
		setMethod("http://www.yasite.net/shopapi/index.php/goodController/searchGoodList/"+pm.getName()+"/"+pm.getPage()+"");
	}

	@Override
	public GoodsListEntity handlerResult(JSONObject json) throws JSONException {
		// TODO Auto-generated method stub
		GoodsListEntity a = new Gson().fromJson(json.toString(), GoodsListEntity.class);
		Log.e("/////////////---------", a.getData().toString());
		return a;
	}

}
