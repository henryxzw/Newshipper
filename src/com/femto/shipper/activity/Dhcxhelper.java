package com.femto.shipper.activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dhcxhelper extends SQLiteOpenHelper {
	private final static int sqlversion = 1;
	private final static String sqlname = "ordera.db";
	private final static String tablename = "ordera";

	public Dhcxhelper(Context context) {
		super(context, sqlname, null, sqlversion);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		arg0.execSQL("create table " + tablename
				+ "( _id integer primary key autoincrement," + "orderno text,"
				+ "startadd text,startlat text,startlon text,"
				+ "endadd text,endlat text,endlon text," + "paytime text,"
				+ "carano text,caratype text,caraststus text,"
				+ "carbno text,carbtype text,carbststus text,"
				+ "carcno text,carctype text,carcststus text,"
				+ "cardno text,cardtype text,cardststus text,"
				+ "careno text,caretype text,careststus text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		String sql = "DROP TABLE IF EXISTS " + tablename;
		arg0.execSQL(sql);
		onCreate(arg0);
	}

	public void insert(String orderno, String startadd, String startlat,
			String startlon, String endadd, String endlat, String endlon,
			String paytime, String carano, String caratype, String caraststus,
			String carbno, String carbtype, String carbststus, String carcno,
			String carctype, String carcststus, String cardno, String cardtype,
			String cardststus, String careno, String caretype, String careststus) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL(
				"insert into "
						+ tablename
						+ "(orderno,startadd,startlat,startlon,endadd,endlat,endlon,paytime,carano,caratype,caraststus,carbno,carbtype,carbststus,carcno,carctype,carcststus,cardno,cardtype,cardststus,careno,caretype,careststus) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				new String[] { orderno, startadd, startlat, startlon, endadd,
						endlat, endlon, paytime, carano, caratype, caraststus,
						carbno, carbtype, carbststus, carcno, carctype,
						carcststus, cardno, cardtype, cardststus, careno,
						caretype, careststus });
	}

	public Cursor select() {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(tablename, null, null, null, null, null, null,
				null);
		return cursor;
	}

	public Cursor selectliked(String where, String like) {
		SQLiteDatabase db = this.getWritableDatabase();
		// Cursor cursor = db.query(tablename, null, "_id='" + where
		// + "' and orderno like '%" + like + "'", null, null, null, null,
		// null);
		Cursor cursor = db.rawQuery("select " + where + " from " + tablename
				+ " where _id=?", new String[] { like });
		return cursor;
	}

	public Cursor selectlikeb(String where, String like) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(tablename, null, "_id='" + where
				+ "' and orderno like '%" + like + "'", null, null, null, null,
				null);
		// Cursor cursor = db.rawQuery("select * from " + tablename
		// + " where _id=?", new String[] { like });
		return cursor;
	}

	public Cursor selectlikec(String where) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(tablename, null, "_id='" + where + "'", null,
				null, null, null, null);
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

	public Cursor selectlikea(String like) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(tablename, null, "orderno like '%" + like
				+ "%'", null, null, null, null, null);
		return cursor;
	}

	public Cursor selectwhereor(String where, String number) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(tablename, null, "caraststus like '%" + where
				+ "%' or carbststus like '%" + where
				+ "%' or carcststus like '%" + where
				+ "%' or cardststus like '%" + where
				+ "%' or careststus like '%" + where + "%'", null, null, null,
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

	public Cursor selectwhereand(String where, String number) {
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.query(tablename, null, "caraststus like '%" + where
				+ "%' and carbststus like '%" + where
				+ "%' and carcststus like '%" + where
				+ "%' and cardststus like '%" + where
				+ "%' and careststus like '%" + where + "%'", null, null, null,
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
