package com.femto.shipper.activitya;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlinsertcity extends SQLiteOpenHelper {
	private final static int version = 2;
	private final static String name = "sqlinsertcitya.db";
	private final static String tablename = "sqlinsertcitya";

	public Sqlinsertcity(Context context) {
		super(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL("create table " + tablename
				+ "( _id integer primary key autoincrement,city text)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		String sql = "DROP TABLE IF EXISTS " + tablename;
		arg0.execSQL(sql);
		onCreate(arg0);
	}

	public void insert(String city) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("insert into " + tablename + "(city) values(?)",
				new String[] { city });
	}

	public Cursor select() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(tablename, null, null, null, null, null, null,
				null);
		return cursor;
	}

	public void delete(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(tablename, "_id= ?", new String[] { Integer.toString(id) });
	}

	public void deletehi() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(tablename, null, null);
	}
}