package net.yasite.model;

import net.yasite.entity.ClassifyInfoEntity;
import net.yasite.entity.UserMsg;
import net.yasite.service.ClassifyService;
import android.content.Context;

public class ClassifyModel extends Model{

	private ClassifyService service;
	
	public ClassifyModel(Context context){
		this.context = context;
		service = new ClassifyService(context);
	}
	
	//请求分类
	public ClassifyInfoEntity requestClassify(int id){
		return service.requestClassify(id);
	}
	
}
