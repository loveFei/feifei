package net.yasite.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import net.yasite.api.AddShoppingCarAPI;
import net.yasite.api.BaseAPI;
import net.yasite.api.DeleteShoppingCarAPI;
import net.yasite.api.GoodInfoAPI;
import net.yasite.api.GoodsListAPI;
import net.yasite.api.GoodsListByNameAPI;
import net.yasite.api.ShoppingCarAPI;
import net.yasite.api.params.AddShoppingCarParams;
import net.yasite.api.params.ClassifySelectGoods;
import net.yasite.api.params.GoodInfoParams;
import net.yasite.api.params.GoodsListParams;
import net.yasite.api.params.ShoppingCarParams;
import net.yasite.api.params.ShoppingCarTokenParams;
import net.yasite.entity.GoodEntity;
import net.yasite.entity.GoodsListEntity;
import net.yasite.entity.ShoppingCarDataEntity;
import net.yasite.entity.ShoppingCarInfoEntity;
import android.content.Context;

public class GoodsService extends BaseService {
	
	//修饰符是protect，没法实例化，要该成public
	public GoodsService(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	//查询商品
	public GoodsListEntity getGoodsList(String page){
		//请求参数
		GoodsListParams pm = new GoodsListParams();
		pm.setPage(page+"");
		//抽象声明GoodsListAPI，请求数据
		BaseAPI api = new GoodsListAPI(context, pm);
		try {
			if(api.doGet()){
				/**
				 * 处理服务端返回的结果. 返回数据类型,见BaseAPI子类中HandlerResult方法, 如果非正常返回则反回错误状态码
				 */
				return (GoodsListEntity) api.getHandleResult();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//如果发生异常，返回null
		return null;
		
	}
	
	//根据名字查询商品
	public GoodsListEntity getClassifyGoodsList(String name,int page){
		ClassifySelectGoods pm = new ClassifySelectGoods();
		pm.setName(name);
		pm.setPage(page);
		
		BaseAPI api = new GoodsListByNameAPI(context, pm);
		try {
			if(api.doGet()){
				/**
				 * 处理服务端返回的结果. 返回数据类型,见BaseAPI子类中HandlerResult方法, 如果非正常返回则反回错误状态码
				 */
				return (GoodsListEntity) api.getHandleResult();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//查询商品详情
	public GoodsListEntity getGoodsInfoById(String id){
		GoodInfoParams pm = new GoodInfoParams();
		pm.setId(id);
		BaseAPI api = new GoodInfoAPI(context, pm);
		try {
			if(api.doGet()){
				/**
				 * 处理服务端返回的结果. 返回数据类型,见BaseAPI子类中HandlerResult方法, 如果非正常返回则反回错误状态码
				 */
				return (GoodsListEntity) api.getHandleResult();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//添加到购物车
	public ShoppingCarDataEntity getAddShoppingCar(String token, String user_id, int goods_id,
			String goods_sn, String goods_name, float market_price,float goods_price,
			int goods_number){
		//get
		AddShoppingCarParams params = new AddShoppingCarParams();
		params.setToken(token);
		//post
		List<NameValuePair> pm = new ArrayList<NameValuePair>();
		pm.add(getValue("user_id", user_id));
		pm.add(getValue("goods_id", Integer.toString(goods_id)));
		pm.add(getValue("goods_sn", goods_sn));
		pm.add(getValue("goods_name", goods_name));
		pm.add(getValue("market_price", Float.toString(market_price)));
		pm.add(getValue("goods_price", Float.toString(goods_price)));
		pm.add(getValue("goods_number", Integer.toString(goods_number)));
		
		BaseAPI api = new AddShoppingCarAPI(context, pm, params);
		try {
			if(api.doPost()){
				return (ShoppingCarDataEntity) api.getHandleResult();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	//查询购物车
	public ShoppingCarInfoEntity getShoppingCar(String id,String token){
		ShoppingCarParams params = new ShoppingCarParams();
		params.setId(id);
		List<NameValuePair> pm = new ArrayList<NameValuePair>();
		pm.add(getValue("token", token));
		BaseAPI api = new ShoppingCarAPI(context, pm, params);
		try {
			if(api.doPost()){
				return (ShoppingCarInfoEntity) api.getHandleResult();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//删除购物车
	public void getDeleteShoppingCar(String token, String ids, String user_id) {
		ShoppingCarTokenParams params = new ShoppingCarTokenParams();
		params.setToken(token);
		
		List<NameValuePair> pm = new ArrayList<NameValuePair>();
		pm.add(getValue("ids", ids));
		pm.add(getValue("user_id", user_id));
		BaseAPI api = new DeleteShoppingCarAPI(context, pm, params);
		try {
			if (api.doPost()) {
				api.getHandleResult();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
