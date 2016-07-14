package com.femto.shipper.activitya;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class Wdlxsqlhelper extends SQLiteOpenHelper
	{
		private final static int sqlversion = 1;
		private final static String sqlname = "wdlxadda.db";
		private final static String tablename = "wdlxadda";

		public Wdlxsqlhelper(Context context)
		{
			super(context, sqlname, null, sqlversion);
		}

		@Override
		public void onCreate(SQLiteDatabase arg0)
		{
			arg0.execSQL("create table " + tablename + "( _id integer primary key autoincrement,addid text,"
					+ "startadd text, startname text, starttel text, startlat text, startlon text, "
					+ "ztdaadd text, ztdaname text, ztdatel text, ztdalat text, ztdalon text, "
					+ "ztdbadd text, ztdbname text, ztdbtel text, ztdblat text, ztdblon text, "
					+ "ztdcadd text, ztdcname text, ztdctel text, ztdclat text, ztdclon text, "
					+ "ztddadd text, ztddname text, ztddtel text, ztddlat text, ztddlon text, "
					+ "ztdeadd text, ztdename text, ztdetel text, ztdelat text, ztdelon text, "
					+ "endadd text, endname text, endtel text, endlat text, endlon text)");
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
		{
			String sql = "DROP TABLE IF EXISTS " + tablename;
			arg0.execSQL(sql);
			onCreate(arg0);
		}

		public void insert(String addid, String startadd, String startname, String starttel, String startlat, String startlon, String ztdaadd,
				String ztdaname, String ztdatel, String ztdalat, String ztdalon, String ztdbadd, String ztdbname, String ztdbtel, String ztdblat,
				String ztdblon, String ztdcadd, String ztdcname, String ztdctel, String ztdclat, String ztdclon, String ztddadd, String ztddname,
				String ztddtel, String ztddlat, String ztddlon, String ztdeadd, String ztdename, String ztdetel, String ztdelat, String ztdelon,
				String endadd, String endname, String endtel, String endlat, String endlon)
		{
			SQLiteDatabase db = this.getWritableDatabase();
			db.execSQL(
					"insert into "
							+ tablename
							+ "(addid,startadd,startname,starttel,startlat,startlon,ztdaadd,ztdaname,ztdatel,ztdalat,ztdalon,ztdbadd,ztdbname,ztdbtel,ztdblat,ztdblon,"
							+ "ztdcadd,ztdcname,ztdctel,ztdclat,ztdclon,ztddadd,ztddname,ztddtel,ztddlat,ztddlon,ztdeadd,ztdename,ztdetel,ztdelat,ztdelon,endadd,endname,endtel,endlat,endlon) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					new String[] { addid, startadd, startname, starttel, startlat, startlon, ztdaadd, ztdaname, ztdatel, ztdalat, ztdalon, ztdbadd,
							ztdbname, ztdbtel, ztdblat, ztdblon, ztdcadd, ztdcname, ztdctel, ztdclat, ztdclon, ztddadd, ztddname, ztddtel, ztddlat,
							ztddlon, ztdeadd, ztdename, ztdetel, ztdelat, ztdelon, endadd, endname, endtel, endlat, endlon });
		}

		public Cursor select()
		{
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.query(tablename, null, null, null, null, null, null, null);
			return cursor;
		}

		public void delete(int id)
		{
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(tablename, "_id= ?", new String[] { Integer.toString(id) });
		}

		public void deletehi()
		{
			SQLiteDatabase db = this.getWritableDatabase();
			db.delete(tablename, null, null);
		}
	}
