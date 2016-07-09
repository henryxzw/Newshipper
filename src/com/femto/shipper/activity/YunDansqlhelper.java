package com.femto.shipper.activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class YunDansqlhelper extends SQLiteOpenHelper {
	private final static int sqlversion = 1;
	private final static String sqlname = "yundana.db";
	private final static String tablename = "yundana";

	public YunDansqlhelper(Context context) {
		super(context, sqlname, null, sqlversion);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL("create table "
				+ tablename
				+ "( _id integer primary key autoincrement,"
				+ "avatar text,credit text,yid text,sjname text,carnumber text,mobile text,ismycar text,carstatus text,driverid text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		String sql = "DROP TABLE IF EXISTS " + tablename;
		arg0.execSQL(sql);
		onCreate(arg0);
	}

	public void insert(String avatar, String credit, String yid, String sjname,
			String carnumber, String mobile, String ismycar, String carstatus,
			String driverid) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(
				"insert into "
						+ tablename
						+ "(avatar,credit,yid,sjname,carnumber,mobile,ismycar,carstatus,driverid) values (?,?,?,?,?,?,?,?,?)",
				new String[] { avatar, credit, yid, sjname, carnumber, mobile,
						ismycar, carstatus, driverid });
	}

	public Cursor select() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(tablename, null, null, null, null, null, null,
				null);
		return cursor;
	}

	public Cursor selectnumber(String number) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(tablename, null, null, null, null, null, null,
				number);
		return cursor;
	}

	public Cursor selectlike(String like, String number) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(tablename, null, "orderno like '%" + like
				+ "%'", null, null, null, null, number);
		return cursor;
	}

	public Cursor selectwhereor(String where, String number) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(tablename, null, "yid like '%" + where + "%'",
				null, null, null, null, number);
		// Cursor cursor = db
		// .rawQuery(
		// "select * from "
		// + tablename
		// +
		// " where caraststus=? or carbststus=? or carcststus=? or cardststus=? or careststus=?",
		// new String[] { where, where, where, where, where });
		return cursor;
	}

	public Cursor selectwhereand(String wherea, String whereb, String number) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(tablename, null, "yid like '%" + wherea
				+ "%' and carstatus like '%" + whereb + "%'", null, null, null,
				null, number);
		// Cursor cursor = db
		// .rawQuery(
		// "select * from "
		// + tablename
		// +
		// " where caraststus=? or carbststus=? or carcststus=? or cardststus=? or careststus=?",
		// new String[] { where, where, where, where, where });
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
