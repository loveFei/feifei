package net.yasite.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import net.yasite.api.BaseAPI;
import net.yasite.api.GoodsListAPI;
import net.yasite.api.LoginAPI;
import net.yasite.api.params.GoodsListParams;
import net.yasite.entity.GoodsListEntity;
import net.yasite.entity.UserMsg;
import android.content.Context;
import android.util.Log;

public class LoginService extends BaseService{

	public LoginService(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public UserMsg getUserMessage(String name,String pwd,int who){
		//请求参数
		List<NameValuePair> pm = new ArrayList<NameValuePair>();
		pm.add(getValue("name", name));
		pm.add(getValue("pwd", pwd));
		//抽象声明LoginAPI，请求数据
		// who 的值0代表是login请求过来的值，1代表是regist请求过来的值
		BaseAPI api = new LoginAPI(context, pm, who);
		try {
			if(api.doPost()){
				/**
				 * 处理服务端返回的结果. 返回数据类型,见BaseAPI子类中HandlerResult方法, 如果非正常返回则反回错误状态码
				 */
				return (UserMsg)api.getHandleResult();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//如果发生异常，返回null
		return null;
		
	}

}
