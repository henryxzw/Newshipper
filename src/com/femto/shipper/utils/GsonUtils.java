package com.femto.shipper.utils;
import com.google.gson.Gson;


public class GsonUtils {
	public static <T> T json2Bean(String json,Class<T> clazz){
		////用Gson转成json对象
		Gson gson = new Gson();
		return gson.fromJson(json, clazz);
	}
}
