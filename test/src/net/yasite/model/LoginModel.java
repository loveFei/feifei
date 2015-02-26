package net.yasite.model;

import java.util.HashMap;
import java.util.Map;

import net.yasite.entity.UserMsg;
import net.yasite.service.LoginService;
import android.content.Context;
import android.widget.EditText;

public class LoginModel extends Model {
	
	private LoginService loginservice;
	
	public LoginModel(Context context){
		this.context = context;
		loginservice = new LoginService(context);
	}
	//请求数据//who如果是0为login，1为regist
	// who 的值0代表是login请求过来的值，1代表是regist请求过来的值
	public UserMsg requestUserMessage(String name,String pwd,int who){
		return (UserMsg)loginservice.getUserMessage(name,pwd,who);
	}
	
	@Override//得到editText中的值
	public String editText(EditText et) {
		// TODO Auto-generated method stub
		return super.editText(et);
	}
	
	@Override//吐司
	public void reportToast(String message) {
		// TODO Auto-generated method stub
		super.reportToast(message);
	}
	
	@Override//请输入当前密码
	public boolean checkPwdLocal(String pwd) {
		// TODO Auto-generated method stub
		return super.checkPwdLocal(pwd);
	}
	
	@Override//验证密码一致性，密码和确认密码
	public boolean checkInfo(String pwd, String verify) {
		// TODO Auto-generated method stub
		return super.checkInfo(pwd, verify);
	}

}
