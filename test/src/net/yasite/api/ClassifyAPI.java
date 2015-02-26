package net.yasite.api;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import net.yasite.api.params.BaseHttpParam;
import net.yasite.api.params.ClassifyParam;
import net.yasite.entity.ClassifyInfoEntity;
import android.content.Context;

public class ClassifyAPI extends BaseAPI{

	public ClassifyAPI(Context context, ClassifyParam pm) {
		super(context, pm);
		// TODO Auto-generated constructor stub
		setMethod("http://www.yasite.net/shopapi/index.php/goodController/getCategory/"+pm.getId());
	}
	
	@Override
	public Object handlerResult(JSONObject json) throws JSONException {
		// TODO Auto-generated method stub
		return new Gson().fromJson(json.toString(), ClassifyInfoEntity.class);
	}

}
