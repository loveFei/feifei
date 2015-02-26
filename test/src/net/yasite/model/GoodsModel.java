package net.yasite.model;

import net.yasite.entity.GoodEntity;
import net.yasite.entity.GoodsListEntity;
import net.yasite.entity.ShoppingCarDataEntity;
import net.yasite.entity.ShoppingCarInfoEntity;
import net.yasite.service.GoodsService;
import android.content.Context;

public class GoodsModel extends Model {
	
	//Model调用Service
	GoodsService goodsService;
	
	//构造方法  context是Model父类中的属性
	public GoodsModel(Context context){
		this.context = context;
		goodsService = new GoodsService(context);
	}
	
	public GoodsListEntity RequestGoodsList(String page){
		return goodsService.getGoodsList(page);
	}
	
	//根据类别查询商品
	public GoodsListEntity requestGoodsListByName(String name,int page){
		return goodsService.getClassifyGoodsList(name, page);
	}
	
	//通过商品id查询商品详情
	public GoodsListEntity requestGoodsInfoById(String id){
		return goodsService.getGoodsInfoById(id);
	}
	
	//add购物车
	public ShoppingCarDataEntity requestAddShoppingCar(String token, String user_id, int goods_id,
			String goods_sn, String goods_name, float market_price,
			float goods_price, int goods_number){
		return goodsService.getAddShoppingCar(token, user_id, goods_id, goods_sn, goods_name, market_price, goods_price, goods_number);
	}
	//查询购物车
	public ShoppingCarInfoEntity requestShoppingCarList(String id,String token){
		return goodsService.getShoppingCar(id, token);
	}
	
	public void requestDeleteShoppingCar(String token, String ids, String user_id) {
		goodsService.getDeleteShoppingCar(token, ids, user_id);
	}
	
	
}
