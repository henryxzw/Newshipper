package com.easemob.chatuidemo.db;

import java.util.ArrayList;
import java.util.List;

import com.femto.shipper.utils.LogUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DatabaseHelper<T> extends SQLiteOpenHelper
{
	public static final String DIDI_DATABASE_NAME = "orderdyliebiao";
	public static final int DIDI_DATEBASE_VERSION = 2;
	protected static final String ORDER_DETAIL_TABLE_NAME = "order_liebiao";

	protected static final String KEY_OF_ORDER_DETAIL_ID = "id";
	protected static final String KEY_OF_ORDER_DETAIL_NUMBER = "type";
	protected static final String KEY_OF_ORDER_DETAIL_CONTENT = "content";

	// 订单预约表
	private String table_create_describe_1 = "CREATE TABLE IF NOT EXISTS " + ORDER_DETAIL_TABLE_NAME + " (" + KEY_OF_ORDER_DETAIL_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_OF_ORDER_DETAIL_NUMBER + " VARCHAR(100)," + KEY_OF_ORDER_DETAIL_CONTENT + " TEXT" + ");";

	public DatabaseHelper(Context context)
	{
		this(context, DIDI_DATABASE_NAME, null, DIDI_DATEBASE_VERSION);
	}

	public DatabaseHelper(Context context, String name, CursorFactory factory, int version)
	{
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		// 创建数据表
		db.execSQL(table_create_describe_1);
	}

	public abstract String getTableName();

	public long push(String json, String type)
	{
		SQLiteDatabase database = getWritableDatabase();
		T object = parseObject(json);
		// 否有相同，相同数据则删除旧数据
		Cursor cursor = database.rawQuery("select * from " + getTableName() + " where " + KEY_OF_ORDER_DETAIL_NUMBER + "=?", new String[] { type });
		if (cursor.moveToNext())
		{
			database.delete(getTableName(), KEY_OF_ORDER_DETAIL_NUMBER + "=?", new String[] { type });
		}
		cursor.close();
		// 插入新数据
		long insertId = database.insert(getTableName(), null, insertContentValues(object, json, type));
		database.close();
		return insertId;
	}

	// //添加
	public void intser(String json, String type)
	{
		SQLiteDatabase database = getWritableDatabase();
		T object = parseObject(json);
		LogUtils.i("---------添加数据------------->>>" + object);
		// 插入新数据
		database.insert(getTableName(), null, insertContentValues(object, json, "order" + type));
	}

	// /// 删除
	public void delect(String type)
	{
		SQLiteDatabase database = getWritableDatabase();
		// 否有相同，相同数据则删除旧数据
		database.delete(getTableName(), KEY_OF_ORDER_DETAIL_NUMBER + " = ? ", new String[] { type });

	}

	// /// 删除
	public void delectAll(String type)
	{
		SQLiteDatabase database = getWritableDatabase();
		// 否有相同，相同数据则删除旧数据
		database.delete(getTableName(), KEY_OF_ORDER_DETAIL_NUMBER + " like '" + type + "%'", null);

	}

	public abstract T parseObject(String json);

	public abstract ContentValues insertContentValues(T object, String json, String type);

	// ////////查数据///////根据type类型查数据
	public T pull(String type)
	{
		SQLiteDatabase database = getReadableDatabase();
		List<T> list = new ArrayList<T>();
		Cursor cursor = database.rawQuery("select * from " + getTableName() + " where " + KEY_OF_ORDER_DETAIL_NUMBER + "=?", new String[] { type });
		if (cursor.moveToNext())
		{
			String orderContent = cursor.getString(cursor.getColumnIndex(KEY_OF_ORDER_DETAIL_CONTENT));
			T object = parseObject(orderContent);
			list.add(object);
			cursor.close();
			database.close();
			return object;
		}
		cursor.close();
		database.close();
		return null;
	}

	// ////////查数据///////根据type类型查数据所有
	public List<T> selectAll(String type)
	{
		SQLiteDatabase database = getReadableDatabase();
		List<T> list = new ArrayList<T>();
		Cursor cursor = database.rawQuery("select * from " + getTableName() + " where  type like '" + type + "%'", null);
		cursor.moveToFirst();
		for (int i = 0; i < cursor.getCount(); i++)
		{
			String orderContent = cursor.getString(cursor.getColumnIndex(KEY_OF_ORDER_DETAIL_CONTENT));
			T object = parseObject(orderContent);
			list.add(object);
			cursor.moveToNext();
		}
		cursor.close();
		database.close();
		return list;
	}

	public void clear()
	{
		SQLiteDatabase database = getWritableDatabase();
		database.execSQL("delete from " + getTableName());
		database.close();
	}

}
