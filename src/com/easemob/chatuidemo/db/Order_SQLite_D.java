package com.easemob.chatuidemo.db;

import com.femto.shipper.bean.OrderListBean;
import com.femto.shipper.utils.GsonUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Order_SQLite_D extends DatabaseHelper<OrderListBean>
{

	public Order_SQLite_D(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTableName()
	{
		// TODO Auto-generated method stub
		return ORDER_DETAIL_TABLE_NAME;
	}

	@Override
	public ContentValues insertContentValues(OrderListBean object, String json, String type)
	{
		// TODO Auto-generated method stub
		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_OF_ORDER_DETAIL_NUMBER, type);
		contentValues.put(KEY_OF_ORDER_DETAIL_CONTENT, json);
		return contentValues;
	}

	@Override
	public OrderListBean parseObject(String json)
	{
		// TODO Auto-generated method stub
		return GsonUtils.json2Bean(json, OrderListBean.class);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
	{
		// TODO Auto-generated method stub
		
	}

}
