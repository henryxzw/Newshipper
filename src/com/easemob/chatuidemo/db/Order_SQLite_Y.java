package com.easemob.chatuidemo.db;

import com.femto.shipper.bean.YundanBean;
import com.femto.shipper.utils.GsonUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Order_SQLite_Y extends DatabaseHelper<YundanBean>
{

	public Order_SQLite_Y(Context context)
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
	public ContentValues insertContentValues(YundanBean object, String json, String type)
	{
		// TODO Auto-generated method stub
		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_OF_ORDER_DETAIL_NUMBER, type);
		contentValues.put(KEY_OF_ORDER_DETAIL_CONTENT, json);
		return contentValues;
	}

	@Override
	public YundanBean parseObject(String json)
	{
		// TODO Auto-generated method stub
		return GsonUtils.json2Bean(json, YundanBean.class);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
	{
		// TODO Auto-generated method stub
		
	}

}
