package com.femto.shipper.activitya;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Shelperhistory extends SQLiteOpenHelper {
	private final static int sqlversion = 1;
	private final static String sqlname = "historye.db";
	private final static String tablename = "historye";

	public Shelperhistory(Context context) {
		super(context, sqlname, null, sqlversion);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL("create table "
				+ tablename
				+ "( _id integer primary key autoincrement,address text,name text,tel text)");
//		arg0.execSQL("insert into " + tablename
//				+ "(address,name,tel) values(?,?,?)", new String[] { "��Ȫ·",
//				"ѧ��", "110" });
//		arg0.execSQL("insert into " + tablename
//				+ "(address,name,tel) values(?,?,?)", new String[] { "�κ���",
//				"ѧ��", "120" });
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		String sql = "DROP TABLE IF EXISTS " + tablename;
		arg0.execSQL(sql);
		onCreate(arg0);
	}

	public void insert(String address, String name, String tel) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("insert into " + tablename
				+ "(address,name,tel) values(?,?,?)", new String[] { address,
				name, tel });
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
		// String sql = "DROP TABLE IF EXISTS " + tablename;
		// db.execSQL(sql);
	}
}
