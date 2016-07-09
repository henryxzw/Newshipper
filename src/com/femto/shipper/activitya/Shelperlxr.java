package com.femto.shipper.activitya;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Shelperlxr extends SQLiteOpenHelper {
	private final static int sqlversion = 2;
	private final static String sqlname = "lxra.db";
	private final static String tablename = "lxra";

	public Shelperlxr(Context context) {
		super(context, sqlname, null, sqlversion);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL("create table " + tablename
				+ "( _id integer primary key autoincrement,name text,tel text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		String sql = "DROP TABLE IF EXISTS " + tablename;
		arg0.execSQL(sql);
		onCreate(arg0);
	}

	public void insert(String name, String tel) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("insert into " + tablename + "(name,tel) values(?,?)",
				new String[] { name, tel });
	}

	public String selecttel(String name) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql = "select tel from " + tablename + "where name = ?";
		Cursor cursor = db.rawQuery(sql, new String[] { name });
		String tel = "null";
		if (cursor.moveToFirst()) {
			tel = cursor.getString(cursor.getColumnIndex("tel"));
		}
		return tel;
	}

	public Cursor select() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(tablename, null, null, null, null, null,
				"_id desc", null);
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
