package com.easemob.chatuidemo.db;

import com.femto.shipper.utils.LogUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class My_DBSqlite
{
	private static final String DB_NAME = "myxinxi.db";
	private static final String TB_NAME = "myxinxi";
	private static final int VERSION = 2;
	private static final String create_sql = "create table myxinxi(" + "_id integer primary key," + "nick_name nvarchar(20),"
			+ "mobile nvarchar(100)," + "avatar nvarchar(100)," + "role_name nvarchar(100)," + "email nvarchar(100),"
			+ "carlist_count nvarchar(100)," + "myline_count nvarchar(100)," + "coupon_count nvarchar(100)," + "timestamp nvarchar(100))";
	private Context context;
	private sqliteOpenHelper sqliteOpenHelper;
	private SQLiteDatabase sql;

	public My_DBSqlite(Context context)
	{
		// TODO Auto-generated constructor stub
		this.context = context;
		open();
	}

	public void open()
	{
		sqliteOpenHelper = new sqliteOpenHelper(context, DB_NAME, null, VERSION);
		sql = sqliteOpenHelper.getWritableDatabase();
	}

	public void close()
	{
		if (sql != null)
		{
			sql.close();
		}
		sql = null;
	}

	public long insert(String nick_name, String mobile, String avatar, String role_name, String email, String carlist_count, String myline_count,
			String coupon_count, String timestamp)
	{
		ContentValues cv = new ContentValues();
		cv.put("nick_name", nick_name);
		cv.put("mobile", mobile);
		cv.put("avatar", avatar);
		cv.put("mobile", mobile);
		cv.put("role_name", role_name);
		cv.put("email", email);
		cv.put("carlist_count", carlist_count);
		cv.put("myline_count", myline_count);
		cv.put("coupon_count", coupon_count);
		cv.put("timestamp", timestamp);
		return sql.insert(TB_NAME, null, cv);
	}

	public long update(String nick_name, String mobile, String avatar, String role_name, String email, String carlist_count, String myline_count,
			String coupon_count)
	{
		ContentValues cv = new ContentValues();
		cv.put("nick_name", nick_name);
		cv.put("mobile", mobile);
		cv.put("avatar", avatar);
		cv.put("mobile", mobile);
		cv.put("role_name", role_name);
		cv.put("email", email);
		cv.put("carlist_count", carlist_count);
		cv.put("myline_count", myline_count);
		cv.put("coupon_count", coupon_count);
		return sql.update(TB_NAME, cv, " id = ?", new String[] { String.valueOf(1) });
	}

	public Cursor select()
	{
		return sql.query(TB_NAME, null, null, null, null, null, null);
	}

	// public Cursor selectphone()
	// {
	// return sql.query(TB_NAME, null, "id=? ", new String[] { String.valueOf(1)
	// }, null, null, null);
	// }
	// ////
	public void delect()
	{
		sql.delete(TB_NAME, null, null);
	}

	class sqliteOpenHelper extends SQLiteOpenHelper
	{

		public sqliteOpenHelper(Context context, String name, CursorFactory factory, int version)
		{
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase arg0)
		{
			// TODO Auto-generated method stub
			arg0.execSQL(create_sql);
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
		{
			// TODO Auto-generated method stub
			arg0.execSQL("drop table " + TB_NAME);
			onCreate(arg0);
		}

	}
}
