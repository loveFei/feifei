package net.yasite.service;

import net.yasite.api.BaseAPI;
import net.yasite.api.ClassifyAPI;
import net.yasite.api.params.ClassifyParam;
import net.yasite.entity.ClassifyInfoEntity;
import android.content.Context;

public class ClassifyService extends BaseService {

	public ClassifyService(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
	}
	
	public ClassifyInfoEntity requestClassify(int id){
		ClassifyParam pm = new ClassifyParam();
		pm.setId(id);
		BaseAPI api = new ClassifyAPI(context, pm);
		try {
			if(api.doGet()){
				return (ClassifyInfoEntity) api.getHandleResult();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
}
