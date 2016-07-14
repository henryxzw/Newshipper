package com.femto.shipper.activitya;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Addressposition extends BaseActivity implements OnClickListener,
		BDLocationListener {
	private RelativeLayout addporla;
	private EditText addpetb, addpetc;
	private TextView addptva;
	private Button addpbtna, addressdqwzbtn;
	private Intent intenta, intentb;
	private Sqlhelperhistory sh = null;
	public static Cursor cursora;
	private SQLiteDatabase db = null;
	public static int mapclose = 0;
	private Bundle bundlea, bundleb, bundlec, bundled;
	@SuppressWarnings("unused")
	private String address = "address", lat = "lat", lon = "lon",
			name = "name", tel = "tel", getaddress, getlat, getlon, etname,
			ettel, getname, gettel, djddf, mytime, xzdsj, straddptva, ztdalat,
			ztdalon, ztdblat, ztdblon, ztdclat, ztdclon, ztddlat, ztddlon,
			zhdd, zhname, zhtel, xhdd, xhname, xhtel, ztadd, ztbdd, ztcdd,
			ztddd, ztedd, ztaname, ztbname, ztcname, ztdname, ztename, ztdelat,
			xiugainame, xiugaitel, straddress, strlat, strlon, eyyq, strycatvb,
			ztatel, ztbtel, ztctel, ztdtel, ztetel, ztdelon, zhlat, zhlon,
			lxraddress, xhlat, xhlon, add;
	public static String adb = "hello";
	private RelativeLayout ycatxlrl;
	private int ztdsl, sfxt = 0, bcztqcgs, bczthgjg, bcztbswl, bcztdwb,
			bcztdcm, bcztscm;
	private boolean ntype = false;
	public static Activity addressposition;
	// private SharedPreferences sharedPreferencesa;
	private ConnectivityManager connectivitymanagera;
	private NetworkInfo networkinfoa;
	private LocationClient loclient = null;
	private LocationClientOption loclientoption;

	@Override
	protected void onDestroy() {
		loclientoption = null;
		connectivitymanagera = null;
		networkinfoa = null;
		if (loclient != null) {
			loclient.stop();
		}
		if (sh != null) {
			sh.close();
		}
		if (db != null) {
			db.close();
		}
		if (bundlea != null) {
			bundlea.clear();
		}
		if (bundlec != null) {
			bundlec.clear();
		}
		if (bundleb != null) {
			bundleb.clear();
		}
		if (bundlea != null) {
			bundlea.clear();
		}
		if (bundled != null) {
			bundled.clear();
		}
		super.onDestroy();
	}

	private void dw() {
		loclient = new LocationClient(this);
		loclient.registerLocationListener(this);
		loclientoption = new LocationClientOption();
		loclientoption.setLocationMode(LocationMode.Hight_Accuracy);
		// loclientoption.setOpenGps(true);
		loclientoption.setCoorType("bd09ll");
		loclientoption.setAddrType("all");
		// loclientoption.setPriority(LocationClientOption.NetWorkFirst);
		loclientoption.setScanSpan(1000);
		loclientoption.disableCache(true);
		loclientoption.setIsNeedLocationPoiList(true);
		loclientoption.setIsNeedAddress(true);
		loclientoption.setNeedDeviceDirect(true);
		loclient.setLocOption(loclientoption);
		try {
			connectivitymanagera = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
			networkinfoa = connectivitymanagera.getActiveNetworkInfo();
		} catch (Exception e) {
			connectivitymanagera = null;
			networkinfoa = null;
		}
		if (networkinfoa != null) {
			ntype = connectivitymanagera.getActiveNetworkInfo().isAvailable();
		} else {
			ntype = false;
		}
		if (ntype == true) {
			loclient.start();
		} else {
			showToast(getResources().getString(R.string.wlyc));
		}
	}

	@Override
	public void onReceiveLocation(BDLocation arg0) {
		// if (arg0 == null) {
		// return;
		// }
		straddress = arg0.getAddrStr();
		// showToast("启动定位");
		if (straddress == null) {
			showToast(getResources().getString(R.string.wlyc));
			loclient.requestLocation();
			loclient.stop();
			return;
		}
		// showToast("当前位置：" + straddress);
		strlat = String.valueOf(arg0.getLatitude());
		strlon = String.valueOf(arg0.getLongitude());
		getaddress = straddress;
		getlat = strlat;
		getlon = strlon;
		addptva.setText(straddress);
		addptva.setTextColor(Color.BLACK);
		loclient.requestLocation();
		loclient.stop();
		// }
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addressposition);
		addressposition = this;
		// sharedPreferencesa = getSharedPreferences("dinwei_xinxi",
		// Activity.MODE_PRIVATE);
		// straddress = sharedPreferencesa.getString("address", "");
		// strlat = sharedPreferencesa.getString("lat", "");
		// strlon = sharedPreferencesa.getString("lng", "");
		addporla = (RelativeLayout) findViewById(R.id.addporla);
		addressdqwzbtn = (Button) findViewById(R.id.addressdqwzbtn);
		addptva = (TextView) findViewById(R.id.addptva);
		addpetb = (EditText) findViewById(R.id.addpetb);
		addpetc = (EditText) findViewById(R.id.addpetc);
		addpbtna = (Button) findViewById(R.id.addpbtna);
		ycatxlrl = (RelativeLayout) findViewById(R.id.ycatxlrl);
		// dw();
		sh = new Sqlhelperhistory(this);
		db = sh.getWritableDatabase();
		addporla.setOnClickListener(this);
		addpbtna.setOnClickListener(this);
		addptva.setOnClickListener(this);
		addpetb.setOnClickListener(this);
		addpetc.setOnClickListener(this);
		ycatxlrl.setOnClickListener(this);
		addressdqwzbtn.setOnClickListener(this);
		mappd();
	}

	@Override
	protected void onResume() {
		MobclickAgent.onResume(this);
		super.onResume();
	}

	@Override
	protected void onPause() {
		MobclickAgent.onPause(this);
		super.onPause();
	}

	private void mappd() {
		if (adb.equals("yato")) {
			bundlec = new Bundle();
			bundlec = getIntent().getExtras();
			djddf = bundlec.getString("djddf");
			mytime = bundlec.getString("mytime");
			xzdsj = bundlec.getString("xzdsj");
			ztdsl = bundlec.getInt("ztdsl");
			ztdalat = bundlec.getString("ztdalat");
			ztdalon = bundlec.getString("ztdalon");
			ztdblat = bundlec.getString("ztdblat");
			ztdblon = bundlec.getString("ztdblon");
			ztdclat = bundlec.getString("ztdclat");
			ztdclon = bundlec.getString("ztdclon");
			ztddlat = bundlec.getString("ztddlat");
			ztddlon = bundlec.getString("ztddlon");
			ztdelat = bundlec.getString("ztdelat");
			ztdelon = bundlec.getString("ztdelon");
			zhlat = bundlec.getString("zhlat");
			zhlon = bundlec.getString("zhlon");
			xhlat = bundlec.getString("xhlat");
			xhlon = bundlec.getString("xhlon");
			zhdd = bundlec.getString("zhdd");
			zhname = bundlec.getString("zhname");
			zhtel = bundlec.getString("zhtel");
			xhdd = bundlec.getString("xhdd");
			xhname = bundlec.getString("xhname");
			xhtel = bundlec.getString("xhtel");
			ztadd = bundlec.getString("ztadd");
			ztbdd = bundlec.getString("ztbdd");
			ztcdd = bundlec.getString("ztcdd");
			ztddd = bundlec.getString("ztddd");
			ztedd = bundlec.getString("ztedd");
			ztaname = bundlec.getString("ztaname");
			ztbname = bundlec.getString("ztbname");
			ztcname = bundlec.getString("ztcname");
			ztdname = bundlec.getString("ztdname");
			ztename = bundlec.getString("ztename");
			ztatel = bundlec.getString("ztatel");
			ztbtel = bundlec.getString("ztbtel");
			ztctel = bundlec.getString("ztctel");
			ztdtel = bundlec.getString("ztdtel");
			ztetel = bundlec.getString("ztetel");
			eyyq = bundlec.getString("eyyq");
			bcztqcgs = bundlec.getInt("bcztqcgs");
			bczthgjg = bundlec.getInt("bczthgjg");
			bcztbswl = bundlec.getInt("bcztbswl");
			bcztdwb = bundlec.getInt("bcztdwb");
			bcztdcm = bundlec.getInt("bcztdcm");
			bcztscm = bundlec.getInt("bcztscm");
			strycatvb = bundlec.getString("strycatvb");
			if (djddf.equals("zhdd")) {
				getaddress = zhdd;
				getlat = zhlat;
				getlon = zhlon;
				if (!zhdd.trim().equals("null")) {
					addptva.setText(zhdd);
					if (!zhname.trim().equals("null")) {
						addpetb.setText(zhname);
					}
					if (!zhtel.trim().equals("null")) {
						addpetc.setText(zhtel);
					}
				}
				if (!zhname.trim().equals("null")) {
					addpetb.setText(zhname);
				}
				if (!zhtel.trim().equals("null")) {
					addpetc.setText(zhtel);
				}
			} else if (djddf.equals("xhdd")) {
				getaddress = xhdd;
				getlat = xhlat;
				getlon = xhlon;
				if (!xhdd.trim().equals("null")) {
					addptva.setText(xhdd);
					if (!xhname.trim().equals("null")) {
						addpetb.setText(xhname);
					}
					if (!xhtel.trim().equals("null")) {
						addpetc.setText(xhtel);
					}
				}
			} else if (djddf.equals("ztdda")) {
				getaddress = ztadd;
				getlat = ztdalat;
				getlon = ztdalon;
				if (!ztadd.trim().equals("null")) {
					addptva.setText(ztadd);
					if (!ztaname.trim().equals("null")) {
						addpetb.setText(ztaname);
					}
					if (!ztatel.trim().equals("null")) {
						addpetc.setText(ztatel);
					}
				}
			} else if (djddf.equals("ztddb")) {
				getaddress = ztbdd;
				getlat = ztdblat;
				getlon = ztdblon;
				if (!ztbdd.trim().equals("null")) {
					addptva.setText(ztbdd);
					if (!ztbname.trim().equals("null")) {
						addpetb.setText(ztbname);
					}
					if (!ztbtel.trim().equals("null")) {
						addpetc.setText(ztbtel);
					}
				}
			} else if (djddf.equals("ztddc")) {
				getaddress = ztcdd;
				getlat = ztdclat;
				getlon = ztdclon;
				if (!ztcdd.trim().equals("null")) {
					addptva.setText(ztcdd);
					if (!ztcname.trim().equals("null")) {
						addpetb.setText(ztcname);
					}
					if (!ztctel.trim().equals("null")) {
						addpetc.setText(ztctel);
					}
				}
			} else if (djddf.equals("ztddd")) {
				getaddress = ztddd;
				getlat = ztddlat;
				getlon = ztddlon;
				if (!ztddd.trim().equals("null")) {
					addptva.setText(ztddd);
					if (!ztdname.trim().equals("null")) {
						addpetb.setText(ztdname);
					}
					if (!ztdtel.trim().equals("null")) {
						addpetc.setText(ztdtel);
					}
				}
			} else if (djddf.equals("ztdde")) {
				getaddress = ztedd;
				getlat = ztdelat;
				getlon = ztdelon;
				if (!ztedd.trim().equals("null")) {
					addptva.setText(ztedd);
					if (!ztename.trim().equals("null")) {
						addpetb.setText(ztename);
					}
					if (!ztetel.trim().equals("null")) {
						addpetc.setText(ztetel);
					}
				}
			}
		}
		if (mapclose == 1) {
			bundlea = new Bundle();
			bundlea = getIntent().getExtras();
			getaddress = bundlea.getString(address);
			getlat = bundlea.getString(lat);
			getlon = bundlea.getString(lon);
			djddf = bundlea.getString("djddf");
			mytime = bundlea.getString("mytime");
			xzdsj = bundlea.getString("xzdsj");
			ztdalat = bundlea.getString("ztdalat");
			ztdalon = bundlea.getString("ztdalon");
			ztdblat = bundlea.getString("ztdblat");
			ztdblon = bundlea.getString("ztdblon");
			ztdclat = bundlea.getString("ztdclat");
			ztdclon = bundlea.getString("ztdclon");
			ztddlat = bundlea.getString("ztddlat");
			ztddlon = bundlea.getString("ztddlon");
			ztdelat = bundlea.getString("ztdelat");
			ztdelon = bundlea.getString("ztdelon");
			zhlat = bundlea.getString("zhlat");
			zhlon = bundlea.getString("zhlon");
			xhlat = bundlea.getString("xhlat");
			xhlon = bundlea.getString("xhlon");
			zhdd = bundlea.getString("zhdd");
			zhname = bundlea.getString("zhname");
			zhtel = bundlea.getString("zhtel");
			xhdd = bundlea.getString("xhdd");
			xhname = bundlea.getString("xhname");
			xhtel = bundlea.getString("xhtel");
			ztadd = bundlea.getString("ztadd");
			ztbdd = bundlea.getString("ztbdd");
			ztcdd = bundlea.getString("ztcdd");
			ztddd = bundlea.getString("ztddd");
			ztedd = bundlea.getString("ztedd");
			ztaname = bundlea.getString("ztaname");
			ztbname = bundlea.getString("ztbname");
			ztcname = bundlea.getString("ztcname");
			ztdname = bundlea.getString("ztdname");
			ztename = bundlea.getString("ztename");
			ztatel = bundlea.getString("ztatel");
			ztbtel = bundlea.getString("ztbtel");
			ztctel = bundlea.getString("ztctel");
			ztdtel = bundlea.getString("ztdtel");
			ztetel = bundlea.getString("ztetel");
			bcztqcgs = bundlea.getInt("bcztqcgs");
			bczthgjg = bundlea.getInt("bczthgjg");
			bcztbswl = bundlea.getInt("bcztbswl");
			bcztdwb = bundlea.getInt("bcztdwb");
			bcztdcm = bundlea.getInt("bcztdcm");
			bcztscm = bundlea.getInt("bcztscm");
			ztdsl = bundlea.getInt("ztdsl");
			eyyq = bundlea.getString("eyyq");
			strycatvb = bundlea.getString("strycatvb");
			xiugainame = bundlea.getString("xiugainame");
			xiugaitel = bundlea.getString("xiugaitel");
			if (!xiugainame.equals("")) {
				addpetb.setText(xiugainame);
			}
			if (!xiugaitel.equals("")) {
				addpetc.setText(xiugaitel);
			}
			addptva.setText(getaddress);
		} else if (mapclose == 2) {
			bundlea = new Bundle();
			bundlea = getIntent().getExtras();
			getaddress = bundlea.getString("newaddress");
			gettel = bundlea.getString("newtel");
			getlat = bundlea.getString("newlat");
			getname = bundlea.getString("newname");
			getlon = bundlea.getString("newlon");
			djddf = bundlea.getString("djddf");
			mytime = bundlea.getString("mytime");
			xzdsj = bundlea.getString("xzdsj");
			ztdalat = bundlea.getString("ztdalat");
			ztdalon = bundlea.getString("ztdalon");
			ztdblat = bundlea.getString("ztdblat");
			ztdblon = bundlea.getString("ztdblon");
			ztdclat = bundlea.getString("ztdclat");
			ztdclon = bundlea.getString("ztdclon");
			ztddlat = bundlea.getString("ztddlat");
			ztddlon = bundlea.getString("ztddlon");
			ztdelat = bundlea.getString("ztdelat");
			ztdelon = bundlea.getString("ztdelon");
			zhlat = bundlea.getString("zhlat");
			zhlon = bundlea.getString("zhlon");
			xhlat = bundlea.getString("xhlat");
			xhlon = bundlea.getString("xhlon");
			zhdd = bundlea.getString("zhdd");
			zhname = bundlea.getString("zhname");
			zhtel = bundlea.getString("zhtel");
			xhdd = bundlea.getString("xhdd");
			xhname = bundlea.getString("xhname");
			xhtel = bundlea.getString("xhtel");
			ztadd = bundlea.getString("ztadd");
			ztbdd = bundlea.getString("ztbdd");
			ztcdd = bundlea.getString("ztcdd");
			ztddd = bundlea.getString("ztddd");
			ztedd = bundlea.getString("ztedd");
			ztaname = bundlea.getString("ztaname");
			ztbname = bundlea.getString("ztbname");
			ztcname = bundlea.getString("ztcname");
			ztdname = bundlea.getString("ztdname");
			ztename = bundlea.getString("ztename");
			ztatel = bundlea.getString("ztatel");
			ztbtel = bundlea.getString("ztbtel");
			ztctel = bundlea.getString("ztctel");
			ztdtel = bundlea.getString("ztdtel");
			ztetel = bundlea.getString("ztetel");
			xiugainame = bundlea.getString("xiugainame");
			xiugaitel = bundlea.getString("xiugaitel");
			ztdsl = bundlea.getInt("ztdsl");
			eyyq = bundlea.getString("eyyq");
			strycatvb = bundlea.getString("strycatvb");
			bcztqcgs = bundlea.getInt("bcztqcgs");
			bczthgjg = bundlea.getInt("bczthgjg");
			bcztbswl = bundlea.getInt("bcztbswl");
			bcztdwb = bundlea.getInt("bcztdwb");
			bcztdcm = bundlea.getInt("bcztdcm");
			bcztscm = bundlea.getInt("bcztscm");
			addptva.setText(getaddress);
			if (getname.trim().equals("")) {
				addpetb.setText(xiugainame);
			} else {
				addpetb.setText(getname);
			}
			if (gettel.trim().equals("")) {
				addpetc.setText(xiugaitel);
			} else {
				addpetc.setText(gettel);
			}
		}
		straddptva = addptva.getText().toString().trim();
		if (straddptva.equals(getResources().getString(R.string.xzdz))) {
			addptva.setTextColor(Color.parseColor("#848282"));
		} else {
			addptva.setTextColor(Color.parseColor("#000000"));
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			ContentResolver reContentResolverol = getContentResolver();
			Uri contactData = data.getData();
			@SuppressWarnings("deprecation")
			Cursor cursor = managedQuery(contactData, null, null, null, null);
			cursor.moveToFirst();
			String username = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			String contactId = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts._ID));
			Cursor phone = reContentResolverol.query(
					ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
							+ contactId, null, null);
			while (phone.moveToNext()) {
				String usernumber = phone
						.getString(phone
								.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
				addpetb.setText(username);
				addpetc.setText(usernumber);
			}
		}
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.addporla:
			mapclose = 0;
			adb = "hello";
			Addressposition.this.finish();
			break;
		case R.id.addptva:
			mapclose = 0;
			adb = "hello";
			etname = addpetb.getText().toString().trim();
			ettel = addpetc.getText().toString().trim();
			intenta = new Intent(Addressposition.this, Baidumap.class);
			bundled = new Bundle();
			bundled.putString("djddf", djddf);
			bundled.putString("mytime", mytime);
			bundled.putString("xzdsj", xzdsj);
			bundled.putString("ztdalat", ztdalat);
			bundled.putString("ztdalon", ztdalon);
			bundled.putString("ztdblat", ztdblat);
			bundled.putString("ztdblon", ztdblon);
			bundled.putString("ztdclat", ztdclat);
			bundled.putString("ztdclon", ztdclon);
			bundled.putString("ztddlat", ztddlat);
			bundled.putString("ztddlon", ztddlon);
			bundled.putString("ztdelat", ztdelat);
			bundled.putString("ztdelon", ztdelon);
			bundled.putString("zhlat", zhlat);
			bundled.putString("zhlon", zhlon);
			bundled.putString("xhlat", xhlat);
			bundled.putString("xhlon", xhlon);
			bundled.putString("zhdd", zhdd);
			bundled.putString("zhname", zhname);
			bundled.putString("zhtel", zhtel);
			bundled.putString("xhdd", xhdd);
			bundled.putString("xhname", xhname);
			bundled.putString("xhtel", xhtel);
			bundled.putString("ztadd", ztadd);
			bundled.putString("ztbdd", ztbdd);
			bundled.putString("ztcdd", ztcdd);
			bundled.putString("ztddd", ztddd);
			bundled.putString("ztedd", ztedd);
			bundled.putString("ztaname", ztaname);
			bundled.putString("ztbname", ztbname);
			bundled.putString("ztcname", ztcname);
			bundled.putString("ztdname", ztdname);
			bundled.putString("ztename", ztename);
			bundled.putString("ztatel", ztatel);
			bundled.putString("ztbtel", ztbtel);
			bundled.putString("ztctel", ztctel);
			bundled.putString("ztdtel", ztdtel);
			bundled.putString("ztetel", ztetel);
			bundled.putString("xiugainame", etname);
			bundled.putString("xiugaitel", ettel);
			bundled.putInt("ztdsl", ztdsl);
			bundled.putString("eyyq", eyyq);
			bundled.putString("strycatvb", strycatvb);
			bundled.putInt("bcztqcgs", bcztqcgs);
			bundled.putInt("bczthgjg", bczthgjg);
			bundled.putInt("bcztbswl", bcztbswl);
			bundled.putInt("bcztdwb", bcztdwb);
			bundled.putInt("bcztdcm", bcztdcm);
			bundled.putInt("bcztscm", bcztscm);
			// showToast("strycatvb>>>>>>>" + strycatvb);
			intenta.putExtras(bundled);
			startActivity(intenta);
			break;
		case R.id.addpbtna:
			add = addptva.getText().toString();
			if (add.equals(getResources().getString(R.string.xzdz))) {
				showToast(getResources().getString(R.string.qxzzqddz));
			} else {
				Ycactivitya.ycactivitya.finish();
				mapclose = 0;
				adb = "hello";
				Ycactivitya.ada = "addto";
				etname = addpetb.getText().toString().trim();
				ettel = addpetc.getText().toString().trim();
				cursora = db.query("sqlhistorya", null, null, null, null, null,
						null);
				for (cursora.moveToFirst(); !cursora.isAfterLast(); cursora
						.moveToNext()) {
					if (add.equals(cursora.getString(1))) {
						sfxt = 1;
						break;
					}
				}
				if (sfxt == 0) {
					int intcur = cursora.getCount();
					if (intcur > 9) {
						if (cursora.moveToFirst()) {
							sh.delete(cursora.getInt(0));
						}
					}
					sh.insert(add, etname, ettel, getlat, getlon);
				}
				cursora.close();
				intentb = new Intent(Addressposition.this, Ycactivitya.class);
				bundleb = new Bundle();
				bundleb.putString("newaddress", add);
				bundleb.putString("newname", etname);
				bundleb.putString("newtel", ettel);
				bundleb.putString("newlat", getlat);
				bundleb.putString("newlon", getlon);
				bundleb.putString("djddf", djddf);
				bundleb.putString("mytime", mytime);
				bundleb.putString("xzdsj", xzdsj);
				bundleb.putInt("ztdsl", ztdsl);
				bundleb.putString("ztdalat", ztdalat);
				bundleb.putString("ztdalon", ztdalon);
				bundleb.putString("ztdblat", ztdblat);
				bundleb.putString("ztdblon", ztdblon);
				bundleb.putString("ztdclat", ztdclat);
				bundleb.putString("ztdclon", ztdclon);
				bundleb.putString("ztddlat", ztddlat);
				bundleb.putString("ztddlon", ztddlon);
				bundleb.putString("ztdelat", ztdelat);
				bundleb.putString("ztdelon", ztdelon);
				bundleb.putString("zhlat", zhlat);
				bundleb.putString("zhlon", zhlon);
				bundleb.putString("xhlat", xhlat);
				bundleb.putString("xhlon", xhlon);
				bundleb.putString("zhdd", zhdd);
				bundleb.putString("zhname", zhname);
				bundleb.putString("zhtel", zhtel);
				bundleb.putString("xhdd", xhdd);
				bundleb.putString("xhname", xhname);
				bundleb.putString("xhtel", xhtel);
				bundleb.putString("ztadd", ztadd);
				bundleb.putString("ztbdd", ztbdd);
				bundleb.putString("ztcdd", ztcdd);
				bundleb.putString("ztddd", ztddd);
				bundleb.putString("ztedd", ztedd);
				bundleb.putString("ztaname", ztaname);
				bundleb.putString("ztbname", ztbname);
				bundleb.putString("ztcname", ztcname);
				bundleb.putString("ztdname", ztdname);
				bundleb.putString("ztename", ztename);
				bundleb.putString("ztatel", ztatel);
				bundleb.putString("ztbtel", ztbtel);
				bundleb.putString("ztctel", ztctel);
				bundleb.putString("ztdtel", ztdtel);
				bundleb.putString("ztetel", ztetel);
				bundleb.putString("eyyq", eyyq);
				bundleb.putString("strycatvb", strycatvb);
				bundleb.putInt("bcztqcgs", bcztqcgs);
				bundleb.putInt("bczthgjg", bczthgjg);
				bundleb.putInt("bcztbswl", bcztbswl);
				bundleb.putInt("bcztdwb", bcztdwb);
				bundleb.putInt("bcztdcm", bcztdcm);
				bundleb.putInt("bcztscm", bcztscm);
				// showToast("strycatvb>>>>>>>" + strycatvb);
				intentb.putExtras(bundleb);
				Ycactivitya.ycactivitya.finish();
				Addressposition.this.finish();
				startActivity(intentb);
			}
			break;
		case R.id.ycatxlrl:
			startActivityForResult(new Intent(Intent.ACTION_PICK,
					ContactsContract.Contacts.CONTENT_URI), 0);
			break;
		case R.id.addressdqwzbtn:
			// getaddress = straddress;
			// getlat = strlat;
			// getlon = strlon;
			// addptva.setText(straddress);
			// addptva.setTextColor(Color.BLACK);
			dw();
			break;
		}
	}
}