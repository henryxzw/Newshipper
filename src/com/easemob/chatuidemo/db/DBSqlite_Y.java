package com.easemob.chatuidemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSqlite_Y
{
	private static final String DB_NAME = "jiluy.db";
	private static final String TB_NAME = "jiluy";
	private static final int VERSION = 2;
	private static final String create_sql = "create table jiluy(" + "_id integer primary key," + "avatar nvarchar(100)," + "credit nvarchar(100),"
			+ "yid nvarchar(100)," + "nick_name nvarchar(100)," + "plate_number nvarchar(100)," + "mobile nvarchar(100)," + "ismycar nvarchar(100),"
			+ "car_status nvarchar(100)," + ""
					+ "driver_id nvarchar(100))";
	private Context context;
	private sqliteOpenHelper sqliteOpenHelper;
	private SQLiteDatabase sql;

	public DBSqlite_Y(Context context)
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

	public long insert(String avatar, String credit, String yid, String nick_name, String plate_number, String mobile, String ismycar,
			String car_status,String driver_id)
	{
		ContentValues cv = new ContentValues();
		cv.put("avatar", avatar);
		cv.put("credit", credit);
		cv.put("yid", yid);
		cv.put("nick_name", nick_name);
		cv.put("plate_number", plate_number);
		cv.put("mobile", mobile);
		cv.put("ismycar", ismycar);
		cv.put("car_status", car_status);
		cv.put("driver_id", driver_id);
		return sql.insert(TB_NAME, null, cv);
	}

	public Cursor select()
	{
		return sql.query(TB_NAME, null, null, null, null, null, null);
	}

	// ////
	public void delect()
	{
		sql.delete(TB_NAME, null, null);
	}

	// /删除单个的sid
	public void delectYid(String yid)
	{
		sql.delete(TB_NAME, "  yid=? ", new String[] { yid });
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
