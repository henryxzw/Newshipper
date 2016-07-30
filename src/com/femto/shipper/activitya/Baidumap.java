package com.femto.shipper.activitya;

import java.util.List;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapStatusChangeListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.femto.shipper.R;
import com.femto.shipper.activitya.FilpperListvew.FilpperDeleteListener;
import com.femto.shipper.adapter.ListViewAdapter;
import com.femto.shipper.base.BaseActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.CursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.animation.Animation.AnimationListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

public class Baidumap extends BaseActivity implements OnClickListener,
		OnGetGeoCoderResultListener, TextWatcher, OnMapStatusChangeListener,
		BDLocationListener, OnItemLongClickListener, FilpperDeleteListener {
	private PoiSearch poisearch = null, poisearchc = null;
	private EditText bmeta;
	private LocationClient loclient = null;
	private boolean ntypea = false, ntypeb = false;
	private LocationClientOption loclientoption;
	private String locationcity, nextcity, changeaddress, locationcitya,
			locationcityb, locationcityc, nextcitya, changeaddressa,
			changeaddressd, changeaddressb, changeaddressc, la, lo,
			qcdaddress = "null", acdname, qcdla, qcdlo, qcdaddressb = "null",
			acdnameb, qcdlab, qcdlob, ztdalat, ztdalon, ztdblat, ztdblon,
			ztdclat, ztdclon, ztddlat, mapaddress = "null", ztdelon, ztdtel,
			address = "address", lat = "lat", lon = "lon", eyyq, strycatvb,
			histaddress, histname, histtel, histla, ztddlon, zhdd, zhname,
			zhtel, xhdd, xhname, xhtel, ztadd, ztbdd, ztcdd, ztddd, ztedd,
			ztaname, ztbname, ztcname, ztdname, ztename, ztdelat, zhlat, zhlon,
			xhlat, xhlon, ztatel, ztbtel, ztctel, djddf, ztetel, mytime, xzdsj,
			histlo, xiugainame, xiugaitel;
	private String citysza[], nextcitysza[], cityszb[];
	private int changeaddresslength, inta = 0, sdj, ztdsl, baidu;
	private Sqlhelper sh = null;
	private Sqlhelperb shb = null;
	private Sqlinsertcity shd = null;
	private Sqlhelperhistory shc = null;
	public static Cursor cursor = null, cursorb = null, cursorc = null,
			cursord = null, cursore = null;
	private ListView bmlva, bmlvb;
	private FilpperListvew bmlvc;
	private SimpleCursorAdapter simplecursoradaptera, simplecursoradapterc;
	private Mysimplecursoradapterb mysimplecursoradapterb;
	private static final String keya = "name", keyb = "address", keyc = "tel";
	private LinearLayout baidull;
	private ImageButton bmibtna;
	private int bhcs = 0, mapauto = 0, bcztqcgs, bczthgjg, bcztbswl, bcztdwb,
			bcztdcm, bcztscm;
	private MapView bmapview = null;
	private MyLocationData mylocationdata;
	private boolean isloc = true;
	private LatLng latlng, latlnga, latlngc, latlngd, latlngb;
	private BaiduMap baidumap = null;
	private MapStatus mapstatus;
	private Marker marker = null;
	private BitmapDescriptor bitmapdescriptor = null;
	private OverlayOptions overlayoptions;
	private double lata, lona;
	private GeoCoder geocoder;
	private Toast toast;
	private TextView baidutstv;
	private View baiduview;
	private InputMethodManager inputmethodmanager;
	private Intent intenta, intentb;
	private Bundle bundlea = null, bundleb = null, bundlec = null;
	private Button bmibtnb, baidumapqklibtn;
	private static int ida;
	public static Activity baidumapactivity;
	private SQLiteDatabase db = null, dba = null;
	private ImageView ivc = null;
	private NetworkInfo networkinfoa, networkinfob;
	private ConnectivityManager connectivitymanagera, connectivitymanagerb;
	private SharedPreferences sharedpreferences;
	private List<PoiInfo> allAddrList = null;

	@Override
	protected void onDestroy() {
		if (allAddrList != null) {
			allAddrList.clear();
		}
		if (db != null) {
			db.close();
		}
		if (dba != null) {
			dba.close();
		}
		if (bundlea != null) {
			bundlea.clear();
		}
		if (bundleb != null) {
			bundleb.clear();
		}
		if (bundlec != null) {
			bundlec.clear();
		}
		if (poisearch != null) {
			poisearch.destroy();
		}
		if (bmapview != null) {
			bmapview.removeAllViews();
		}
		if (geocoder != null) {
			geocoder.destroy();
		}
		if (poisearchc != null) {
			poisearchc.destroy();
		}
		if (baidumap != null) {
			baidumap.clear();
		}
		if (loclient != null) {
			loclient.stop();
		}
		if (bitmapdescriptor != null) {
			bitmapdescriptor.recycle();
		}
		if (sh != null) {
			sh.close();
		}
		if (shb != null) {
			shb.close();
		}
		if (shc != null) {
			shc.close();
		}
		if (shd != null) {
			shd.close();
		}
		if (cursor != null) {
			cursor.close();
		}
		if (cursorb != null) {
			cursorb.close();
		}
		if (cursorc != null) {
			cursorc.close();
		}
		if (cursord != null) {
			cursord.close();
		}
		if (cursore != null) {
			cursore.close();
		}
		super.onDestroy();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		SDKInitializer.initialize(getApplicationContext());
		Baidumap.this.setTheme(R.style.All);
		setContentView(R.layout.baidumap);
		baidumapactivity = this;
		cshid();
		bundle();
	}

	private class Mysimplecursoradapterb extends SimpleCursorAdapter {
		// private LayoutInflater layoutinflater;

		@SuppressWarnings("deprecation")
		public Mysimplecursoradapterb(Context context, int layout, Cursor c,
				String[] from, int[] to) {
			super(context, layout, c, from, to);
		}

		// @Override
		// public void bindView(View view, Context context, Cursor cursor) {
		// View convertview = null;
		// if (view == null) {
		// convertview = layoutinflater.inflate(R.layout.zdssa, null);
		// } else {
		// convertview = view;
		// }
		// ivall = (ImageView) convertview.findViewById(R.id.itemivbbb);
		// ivall.setVisibility(View.GONE);
		// super.bindView(view, context, cursor);
		// }
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	private void bundle() {
		bundlec = new Bundle();
		bundlec = getIntent().getExtras();
		djddf = bundlec.getString("djddf");
		mytime = bundlec.getString("mytime");
		xzdsj = bundlec.getString("xzdsj");
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
		bcztqcgs = bundlec.getInt("bcztqcgs");
		bczthgjg = bundlec.getInt("bczthgjg");
		bcztbswl = bundlec.getInt("bcztbswl");
		bcztdwb = bundlec.getInt("bcztdwb");
		bcztdcm = bundlec.getInt("bcztdcm");
		bcztscm = bundlec.getInt("bcztscm");
		ztdsl = bundlec.getInt("ztdsl");
		eyyq = bundlec.getString("eyyq");
		strycatvb = bundlec.getString("strycatvb");
		xiugainame = bundlec.getString("xiugainame");
		xiugaitel = bundlec.getString("xiugaitel");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.bmibtnb:
			if (mapauto == 1) {
				intenta = new Intent(Baidumap.this, Addressposition.class);
				bundlea = new Bundle();
				if (sdj == 1) {
					bundlea.putString(address,  acdname);
					bundlea.putString("addressDetail", qcdaddress );
					bundlea.putString(lat, qcdla);
					bundlea.putString(lon, qcdlo);
				} else if (sdj == 2) {
					bundlea.putString(address,  acdnameb);
					bundlea.putString("addressDetail", qcdaddress );
					bundlea.putString(lat, qcdlab);
					bundlea.putString(lon, qcdlob);
				} else if (sdj == 3) {
					if (mapaddress.equals(getResources().getString(
							R.string.cclfwqcxxz))) {
						Toast.makeText(Baidumap.this,
								getResources().getString(R.string.qxzzqddz),
								Toast.LENGTH_SHORT).show();
						break;
					} else {
						bundlea.putString(address, mapaddress);
						bundlea.putString(lat, String.valueOf(lata));
						bundlea.putString(lon, String.valueOf(lona));
					}
				}
				bundlea.putString("djddf", djddf);
				bundlea.putString("mytime", mytime);
				bundlea.putString("xzdsj", xzdsj);
				bundlea.putString("ztdalat", ztdalat);
				bundlea.putString("ztdalon", ztdalon);
				bundlea.putString("ztdblat", ztdblat);
				bundlea.putString("ztdblon", ztdblon);
				bundlea.putString("ztdclat", ztdclat);
				bundlea.putString("ztdclon", ztdclon);
				bundlea.putString("ztddlat", ztddlat);
				bundlea.putString("ztddlon", ztddlon);
				bundlea.putString("ztdelat", ztdelat);
				bundlea.putString("ztdelon", ztdelon);
				bundlea.putString("zhlat", zhlat);
				bundlea.putString("zhlon", zhlon);
				bundlea.putString("xhlat", xhlat);
				bundlea.putString("xhlon", xhlon);
				bundlea.putString("zhdd", zhdd);
				bundlea.putString("zhname", zhname);
				bundlea.putString("zhtel", zhtel);
				bundlea.putString("xhdd", xhdd);
				bundlea.putString("xhname", xhname);
				bundlea.putString("xhtel", xhtel);
				bundlea.putString("ztadd", ztadd);
				bundlea.putString("ztbdd", ztbdd);
				bundlea.putString("ztcdd", ztcdd);
				bundlea.putString("ztddd", ztddd);
				bundlea.putString("ztedd", ztedd);
				bundlea.putString("ztaname", ztaname);
				bundlea.putString("ztbname", ztbname);
				bundlea.putString("ztcname", ztcname);
				bundlea.putString("ztdname", ztdname);
				bundlea.putString("ztename", ztename);
				bundlea.putString("ztatel", ztatel);
				bundlea.putString("ztbtel", ztbtel);
				bundlea.putString("ztctel", ztctel);
				bundlea.putString("ztdtel", ztdtel);
				bundlea.putString("ztetel", ztetel);
				bundlea.putInt("ztdsl", ztdsl);
				bundlea.putInt("bcztqcgs", bcztqcgs);
				bundlea.putInt("bczthgjg", bczthgjg);
				bundlea.putInt("bcztbswl", bcztbswl);
				bundlea.putInt("bcztdwb", bcztdwb);
				bundlea.putInt("bcztdcm", bcztdcm);
				bundlea.putInt("bcztqcgs", bcztqcgs);
				bundlea.putInt("bczthgjg", bczthgjg);
				bundlea.putInt("bcztbswl", bcztbswl);
				bundlea.putInt("bcztdwb", bcztdwb);
				bundlea.putInt("bcztdcm", bcztdcm);
				bundlea.putInt("bcztscm", bcztscm);
				bundlea.putInt("bcztscm", bcztscm);
				bundlea.putString("xiugainame", xiugainame);
				bundlea.putString("xiugaitel", xiugaitel);
				bundlea.putString("eyyq", eyyq);
				bundlea.putString("strycatvb", strycatvb);
				Addressposition.mapclose = 1;
				intenta.putExtras(bundlea);
				Addressposition.addressposition.finish();
				Baidumap.this.finish();
				startActivity(intenta);
			} else if (mapauto == 0) {
				dj();
			}
			break;
		case R.id.baidumapqklibtn:
			shc.deletehi();
			cursorc.requery();
			baidutstv.setVisibility(View.VISIBLE);
			break;
		case R.id.bmibtna:
			finish();
			break;
		}
	}

	@SuppressWarnings("deprecation")
	private void dj() {
		connectivitymanagerb = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		networkinfob = connectivitymanagerb.getActiveNetworkInfo();
		if (networkinfob != null) {
			ntypeb = connectivitymanagerb.getActiveNetworkInfo().isAvailable();
		} else {
			showToast(getResources().getString(R.string.wlyc));
			return;
		}
		if (ntypeb == false) {
			showToast(getResources().getString(R.string.wlyc));
			return;
		}
		baidu = 0;
		sh.deletehi();
		cursor.requery();
		cursorb.requery();
		changeaddress = bmeta.getText().toString().trim();
		changeaddresslength = bmeta.getText().length();
		if (changeaddress.equals("1") || changeaddress.equals("2")
				|| changeaddress.equals("3") || changeaddress.equals("4")
				|| changeaddress.equals("5") || changeaddress.equals("6")
				|| changeaddress.equals("7") || changeaddress.equals("8")
				|| changeaddress.equals("9")) {
			toast = Toast.makeText(Baidumap.this,
					getResources().getString(R.string.wzdjg),
					Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.TOP, 0, 80);
			toast.show();
		} else {
			if (changeaddresslength == 0) {
				baidull.setVisibility(View.VISIBLE);
				bmapview.setVisibility(View.GONE);
				bmlvb.setVisibility(View.GONE);
				bmlva.setVisibility(View.GONE);
			} else {
				bhcs++;
				baidull.setVisibility(View.GONE);
				if (mapauto == 0) {
					if (changeaddress.equals(acdname)) {
						mapauto = 1;
						pdbtn();
					} else {
						mapauto = 0;
						bmapview.setVisibility(View.GONE);
						bmlvb.setVisibility(View.GONE);
						bmlva.setVisibility(View.VISIBLE);
						showProgressDialog("加载中...");
						poisearchway();
						pdbtn();
					}
				} else {
					if (changeaddress.equals(mapaddress)
							|| changeaddress.equals(acdname)
							|| changeaddress.equals(acdnameb)) {
						mapauto = 1;
						pdbtn();
					} else {

						mapauto = 0;
						inta = 0;
						bmapview.setVisibility(View.GONE);
						bmlvb.setVisibility(View.GONE);
						bmlva.setVisibility(View.VISIBLE);
						showProgressDialog("加载中...");
						poisearchway();
						pdbtn();
					}
				}
				if (bhcs == 0) {
					if (locationcity == null) {
						Toast.makeText(
								Baidumap.this,
								getResources().getString(
										R.string.bsjdwsbyshmrcswbj),
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		}
	}

	// private void closejp() {
	// InputMethodManager imm = (InputMethodManager)
	// getSystemService(Context.INPUT_METHOD_SERVICE);
	// imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	// }

	@SuppressWarnings("deprecation")
	private void cshid() {
		sharedpreferences = getSharedPreferences("dinwei_xinxi",
				Activity.MODE_PRIVATE);
		locationcityb = sharedpreferences.getString("city", "");
		cityszb = locationcityb.split(getResources().getString(R.string.s));
		locationcityc = cityszb[0];
		baidumapqklibtn = (Button) findViewById(R.id.baidumapqklibtn);
		bmeta = (EditText) findViewById(R.id.bmeta);
		bmlva = (ListView) findViewById(R.id.bmlva);
		bmlvb = (ListView) findViewById(R.id.bmlvb);
		bmlvc = (FilpperListvew) findViewById(R.id.bmlvc);
		baidutstv = (TextView) findViewById(R.id.baidutstv);
		// baidumapqklibtn = (Button) findViewById(R.id.baidumapqklibtn);
		baidull = (LinearLayout) findViewById(R.id.baidull);
		bmibtnb = (Button) findViewById(R.id.bmibtnb);
		bmapview = (MapView) findViewById(R.id.bmapview);
		bmibtna = (ImageButton) findViewById(R.id.bmibtna);
		// DisplayMetrics dm = new DisplayMetrics();
		// getWindowManager().getDefaultDisplay().getMetrics(dm);
		// width = dm.widthPixels;
		baiduview = bmapview.getChildAt(1);
		if (baiduview != null
				&& (baiduview instanceof ImageView || baiduview instanceof ZoomControls)) {
			baiduview.setVisibility(View.GONE);
		}
		// bmapview.showZoomControls(false);

		baidumap = bmapview.getMap();
		// baidumap.setMyLocationEnabled(true);
		loclient = new LocationClient(this);
		loclient.registerLocationListener(this);
		loclientoption = new LocationClientOption();
		loclientoption.setLocationMode(LocationMode.Hight_Accuracy);
		loclientoption.setOpenGps(true);
		loclientoption.setCoorType("bd09ll");
		loclientoption.setAddrType("all");
		loclientoption.setPriority(LocationClientOption.NetWorkFirst);
		loclientoption.setScanSpan(1000);
		loclientoption.setIsNeedAddress(true);
		loclientoption.setNeedDeviceDirect(true);
		loclient.setLocOption(loclientoption);
		connectivitymanagera = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		networkinfoa = connectivitymanagera.getActiveNetworkInfo();
		if (networkinfoa != null) {
			ntypea = connectivitymanagera.getActiveNetworkInfo().isAvailable();
		} else {
			ntypea = false;
			showToast(getResources().getString(R.string.wlyc));
		}
		if (ntypea == true) {
			loclient.start();
		}
		bmapview.setVisibility(View.GONE);
		bmlva.setVisibility(View.GONE);
		bmlvb.setVisibility(View.GONE);
		baidull.setVisibility(View.VISIBLE);
		poisearch = PoiSearch.newInstance();
		poisearch.setOnGetPoiSearchResultListener(poisearchlistenera);
		poisearchc = PoiSearch.newInstance();
		poisearchc.setOnGetPoiSearchResultListener(poisearchlistenerc);
		geocoder = GeoCoder.newInstance();
		geocoder.setOnGetGeoCodeResultListener(this);
		bmeta.addTextChangedListener(this);
		sh = new Sqlhelper(this);
		shb = new Sqlhelperb(this);
		shc = new Sqlhelperhistory(this);
		shd = new Sqlinsertcity(this);
		db = shd.getWritableDatabase();
		dba = shc.getWritableDatabase();
		cursorc = shc.select();
		cursorb = shb.select();
		cursor = sh.select();
		cursord = shd.select();
		sh.deletehi();
		CursorAdapter cursorAdapter = new CursorAdapter(this,cursorc) {
			
			@Override
			public View newView(Context arg0, Cursor arg1, ViewGroup arg2) {
				View view = View.inflate(arg0, R.layout.history, null);
				return view;
			}
			
			@Override
			public void bindView(View arg0, Context arg1, Cursor arg2) {
				String address = arg2.getString(arg2.getColumnIndex(keyb));
				String[] addrs = address.split("&");
				if(addrs.length>1)
				{
					address = addrs[1];
				}
				String name = arg2.getString(arg2.getColumnIndex(keya));
				String tel = arg2.getString(arg2.getColumnIndex(keyc));
				TextView tv_address = (TextView)arg0.findViewById(R.id.listtxta);
				TextView tv_name = (TextView)arg0.findViewById(R.id.listtxtb);
				TextView tv_tel = (TextView)arg0.findViewById(R.id.listtxtc);
				tv_address.setText(address);
				tv_name.setText(name);
				tv_tel.setText(tel);
			}
		};

//		simplecursoradapterc = new SimpleCursorAdapter(this, R.layout.history,
//				cursorc, new String[] { keyb, keya, keyc }, new int[] {
//						R.id.listtxta, R.id.listtxtb, R.id.listtxtc });
		bmlvc.setAdapter(cursorAdapter);
		simplecursoradaptera = new SimpleCursorAdapter(this, R.layout.zdssa,
				cursor, new String[] { keya, keyb }, new int[] { R.id.zdssatva,
						R.id.zdssatvb });
		bmlva.setAdapter(simplecursoradaptera);
		mysimplecursoradapterb = new Mysimplecursoradapterb(this,
				R.layout.zdssa, cursorb, new String[] { keya, keyb },
				new int[] { R.id.zdssatva, R.id.zdssatvb });
		bmlvb.setAdapter(mysimplecursoradapterb);
		bmibtnb.setOnClickListener(this);
		bmlva.setOnItemClickListener(onitemlistenera);
		bmlvb.setOnItemClickListener(onitemlistenerb);
		bmlvc.setOnItemClickListener(onitemlistenerc);
		bmlvc.setOnItemLongClickListener(this);
		bmlvc.setFilpperDeleteListener(this);
		baidumap.setOnMapStatusChangeListener(this);
		baidumapqklibtn.setOnClickListener(this);
		bmibtna.setOnClickListener(this);
		cursore = dba.query("sqlhistorya", null, null, null, null, null, null);
		if (cursore.moveToFirst()) {
			baidutstv.setVisibility(View.GONE);
		}
	}

	OnGetPoiSearchResultListener poisearchlistenera = new OnGetPoiSearchResultListener() {
		@Override
		public void onGetPoiDetailResult(PoiDetailResult arg0) {
		}

		@SuppressWarnings("deprecation")
		@Override
		public void onGetPoiResult(PoiResult arg0) {
			dismissProgressDialog();
			if (arg0 == null
					|| arg0.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
				toast = Toast.makeText(Baidumap.this,
						getResources().getString(R.string.wzdjg),
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.TOP, 0, 80);
				toast.show();
				return;
			}
			if (arg0.error == SearchResult.ERRORNO.NO_ERROR) {
				for (int i = 0; i < arg0.getAllPoi().size(); i++) {
					la = String
							.valueOf(arg0.getAllPoi().get(i).location.latitude);
					lo = String
							.valueOf(arg0.getAllPoi().get(i).location.longitude);
					sh.insert(arg0.getAllPoi().get(i).name, arg0.getAllPoi()
							.get(i).address, la, lo);
				}
				cursor.requery();
				return;
			}
			if (arg0.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
				shd.deletehi();
				for (CityInfo cityInfo : arg0.getSuggestCityList()) {
					nextcitya = cityInfo.city.trim();
					nextcitysza = nextcitya.split(getResources().getString(
							R.string.s));
					nextcity = nextcitysza[0];
					shd.insert(nextcity);
				}
				cursord = db.query("sqlinsertcitya", null, null, null, null,
						null, null);
				int i = cursord.getCount();
				if (baidu == 0) {
					if (cursord.moveToFirst()) {
						String citya = cursord.getString(1);
						poisearch.searchInCity((new PoiCitySearchOption())
								.city(citya).keyword(changeaddress).pageNum(0));
					}
					baidu = 1;
				}
				if (baidu == 1 && i > 1) {
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							int i = cursord.getCount();
							if (i > 0) {
								if (cursord.moveToNext()) {
									String acityb = cursord.getString(1);
									poisearch
											.searchInCity((new PoiCitySearchOption())
													.city(acityb)
													.keyword(changeaddress)
													.pageNum(0));
								}
							}
						}
					}, 500);
					baidu = 2;
				}
				if (baidu == 2 && i > 2) {
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							int i = cursord.getCount();
							if (i > 1) {
								if (cursord.move(2)) {
									String acityb = cursord.getString(1);
									poisearch
											.searchInCity((new PoiCitySearchOption())
													.city(acityb)
													.keyword(changeaddress)
													.pageNum(0));
								}
							}
						}
					}, 1000);
				}
				return;
			}
		}
	};

	OnGetPoiSearchResultListener poisearchlistenerc = new OnGetPoiSearchResultListener() {
		@Override
		public void onGetPoiDetailResult(PoiDetailResult arg0) {
		}

		@SuppressWarnings("deprecation")
		@Override
		public void onGetPoiResult(PoiResult arg0) {
			if (arg0 == null
					|| arg0.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
				toast = Toast.makeText(Baidumap.this,
						getResources().getString(R.string.wzdjg),
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.TOP, 0, 80);
				toast.show();
				return;
			}
			if (arg0.error == SearchResult.ERRORNO.NO_ERROR) {
				for (int i = 0; i < arg0.getAllPoi().size(); i++) {
					la = String
							.valueOf(arg0.getAllPoi().get(i).location.latitude);
					lo = String
							.valueOf(arg0.getAllPoi().get(i).location.longitude);
					sh.insert(arg0.getAllPoi().get(i).name, arg0.getAllPoi()
							.get(0).address, la, lo);
				}
				cursor.requery();
				return;
			}
			if (arg0.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
				for (CityInfo cityInfo : arg0.getSuggestCityList()) {
					nextcitya = cityInfo.city.trim();
					nextcitysza = nextcitya.split(getResources().getString(
							R.string.s));
					nextcity = nextcitysza[0];
				}
				poisearchc.searchInCity((new PoiCitySearchOption())
						.city(nextcity).keyword(changeaddress).pageNum(0));
				return;
			}
		}
	};

	// OnGetPoiSearchResultListener poisearchlistenerb = new
	// OnGetPoiSearchResultListener() {
	// @Override
	// public void onGetPoiDetailResult(PoiDetailResult arg0) {
	// }
	//
	// @SuppressWarnings("deprecation")
	// @Override
	// public void onGetPoiResult(PoiResult arg0) {
	// if (arg0.error == SearchResult.ERRORNO.NO_ERROR) {
	// for (int i = 0; i < arg0.getAllPoi().size(); i++) {
	// lab = String
	// .valueOf(arg0.getAllPoi().get(i).location.latitude);
	// lob = String
	// .valueOf(arg0.getAllPoi().get(i).location.longitude);
	// shb.insert(arg0.getAllPoi().get(i).name, arg0.getAllPoi()
	// .get(i).address, lab, lob);
	// }
	// cursorb.requery();
	// return;
	// }
	// }
	// };

	private void pdbtn() {
		if (mapauto == 0) {
			// bmibtnb.setBackgroundResource(bmibtnbback[0]);
			bmibtnb.setText(getResources().getString(R.string.sous));
		} else {
			bmibtnb.setText(getResources().getString(R.string.qd));
			// bmibtnb.setBackgroundResource(bmibtnbback[1]);
		}
	}

	private void mapcenter(double la, double lo) {
		latlnga = new LatLng(la, lo);
		mapstatus = new MapStatus.Builder().target(latlnga).zoom(16).build();
		baidumap.setMapStatus(MapStatusUpdateFactory.newMapStatus(mapstatus));
	}

	public void markerlatlng(LatLng markerlatlng) {
		if (marker != null) {
			marker.remove();
		}
		bitmapdescriptor = BitmapDescriptorFactory.fromResource(R.drawable.dw);
		overlayoptions = new MarkerOptions().position(markerlatlng).icon(
				bitmapdescriptor);
		marker = (Marker) baidumap.addOverlay(overlayoptions);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void afterTextChanged(Editable arg0) {
		// bmeta.setSelectAllOnFocus(true);
		// bmeta.selectAll();
		baidu = 0;
		sh.deletehi();
		cursor.requery();
		cursorb.requery();
		changeaddress = arg0.toString().trim();
		changeaddresslength = changeaddress.length();
		// if (changeaddress.equals("1") || changeaddress.equals("2") ||
		// changeaddress.equals("3") || changeaddress.equals("4")
		// || changeaddress.equals("5") || changeaddress.equals("6") ||
		// changeaddress.equals("7") || changeaddress.equals("8")
		// || changeaddress.equals("9"))
		// {
		// toast = Toast.makeText(Baidumap.this,
		// getResources().getString(R.string.wzdjg), Toast.LENGTH_SHORT);
		// toast.setGravity(Gravity.TOP, 0, 80);
		// toast.show();
		// } else
		// {
		if (changeaddresslength == 0) {
			baidull.setVisibility(View.VISIBLE);
			bmapview.setVisibility(View.GONE);
			bmlvb.setVisibility(View.GONE);
			bmlva.setVisibility(View.GONE);
		}
		// } else
		// {
		// bhcs++;
		// baidull.setVisibility(View.GONE);
		// if (mapauto == 0)
		// {
		// if (changeaddress.equals(acdname))
		// {
		// mapauto = 1;
		// pdbtn();
		// } else
		// {
		// mapauto = 0;
		// bmapview.setVisibility(View.GONE);
		// bmlvb.setVisibility(View.GONE);
		// bmlva.setVisibility(View.VISIBLE);
		// poisearchway();
		// pdbtn();
		// }
		// } else
		// {
		if (changeaddress.equals(mapaddress) || changeaddress.equals(acdname)
				|| changeaddress.equals(acdnameb)) {
			mapauto = 1;
			pdbtn();

		} else {
			mapauto = 0;
			inta = 0;
			bmapview.setVisibility(View.GONE);
			bmlvb.setVisibility(View.GONE);
			bmlva.setVisibility(View.VISIBLE);
			baidull.setVisibility(View.VISIBLE);
			// poisearchway();
			pdbtn();
		}
		// }
		// if (bhcs == 0)
		// {
		// if (locationcity == null)
		// {
		// Toast.makeText(Baidumap.this,
		// getResources().getString(R.string.bsjdwsbyshmrcswbj),
		// Toast.LENGTH_SHORT).show();
		// }
		// }
		// }
		// }
	}

	private void poisearchway() {
		if (locationcity == null) {
			if (changeaddresslength > 3) {
				changeaddressa = changeaddress.substring(2);
				changeaddressb = changeaddressa.substring(0, 1);
				changeaddressc = changeaddressa.substring(1);
				changeaddressd = changeaddress.substring(0, 2);
				if (changeaddressb.equals(getResources().getString(R.string.s))) {
					poisearch.searchInCity((new PoiCitySearchOption())
							.city(changeaddressd).keyword(changeaddressc)
							.pageNum(0));
				} else {
					if (locationcityc == null || locationcityc.equals("")) {
						poisearch.searchInCity((new PoiCitySearchOption())
								.city(getResources().getString(R.string.bj))
								.keyword(changeaddress).pageNum(0));
					} else {
						poisearch.searchInCity((new PoiCitySearchOption())
								.city(locationcityc).keyword(changeaddress)
								.pageNum(0));
					}
				}
			} else {
				if (locationcityc == null || locationcityc.equals("")) {
					poisearch.searchInCity((new PoiCitySearchOption())
							.city(getResources().getString(R.string.bj))
							.keyword(changeaddress).pageNum(0));
				} else {
					poisearch.searchInCity((new PoiCitySearchOption())
							.city(locationcityc).keyword(changeaddress)
							.pageNum(0));
				}
			}
		} else {
			if (changeaddresslength > 3) {
				changeaddressa = changeaddress.substring(2);
				changeaddressb = changeaddressa.substring(0, 1);
				changeaddressc = changeaddressa.substring(1);
				changeaddressd = changeaddress.substring(0, 2);
				if (changeaddressb.equals(getResources().getString(R.string.s))) {
					poisearch.searchInCity((new PoiCitySearchOption())
							.city(changeaddressd).keyword(changeaddressc)
							.pageNum(0));
				} else {
					poisearch.searchInCity((new PoiCitySearchOption())
							.city(locationcity).keyword(changeaddress)
							.pageNum(0));
				}
			} else {
				poisearch.searchInCity((new PoiCitySearchOption())
						.city(locationcity).keyword(changeaddress).pageNum(0));
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
		if (arg0.error == SearchResult.ERRORNO.NO_ERROR) {
			mapaddress = arg0.getAddress().trim();
			if (mapaddress.equals("")) {
				mapaddress = getResources().getString(R.string.cclfwqcxxz);
				bmeta.setText(mapaddress);
			} else {
				bmeta.setText(mapaddress);
				try {
					allAddrList = arg0.getPoiList();
					for (PoiInfo info : allAddrList) {
						shb.insert(info.name, info.address,
								String.valueOf(info.location.latitude),
								String.valueOf(info.location.longitude));
					}
					cursorb.requery();
				} catch (Exception e) {

				}
			}
			return;
		}
	}

	@Override
	public void onReceiveLocation(BDLocation arg0) {
		if (arg0 == null || bmapview == null) {
			return;
		}
		// loclient.start();
		mylocationdata = new MyLocationData.Builder()
				.accuracy(arg0.getRadius()).direction(100)
				.latitude(arg0.getLatitude()).longitude(arg0.getLongitude())
				.build();
		baidumap.setMyLocationData(mylocationdata);
		if (isloc) {
			isloc = false;
			latlng = new LatLng(arg0.getLatitude(), arg0.getLongitude());
			MapStatus.Builder builder = new MapStatus.Builder();
			builder.target(latlng).zoom(18.0f);
			baidumap.animateMapStatus(MapStatusUpdateFactory
					.newMapStatus(builder.build()));
			loclient.stop();
		}
		locationcitya = arg0.getCity();
		if (locationcitya != null) {
			citysza = locationcitya.split(getResources().getString(R.string.s));
			locationcity = citysza[0];
		}
	}

	OnItemClickListener onitemlistenera = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			inputmethodmanager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			inputmethodmanager.hideSoftInputFromWindow(bmeta.getWindowToken(),
					0);
			bmlva.setVisibility(View.GONE);
			bmapview.setVisibility(View.VISIBLE);
			bmlvb.setVisibility(View.VISIBLE);
			acdname = cursor.getString(1);
			qcdaddress = cursor.getString(2);
			qcdla = cursor.getString(3);
			qcdlo = cursor.getString(4);
			shb.deletehi();
			latlngc = new LatLng(Double.parseDouble(qcdla),
					Double.parseDouble(qcdlo));
			mapcenter(Double.parseDouble(qcdla), Double.parseDouble(qcdlo));
			markerlatlng(latlngc);
			bmeta.setText(acdname);
			sh.deletehi();
			mapauto = 1;
			pdbtn();
			geocoder.reverseGeoCode(new ReverseGeoCodeOption()
					.location(latlngc));
			// poisearchb.searchNearby(new PoiNearbySearchOption()
			// .location(latlngc).radius(50).keyword(changeaddress)
			// .pageNum(0));
			sdj = 1;
		}
	};
	OnItemClickListener onitemlistenerb = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// int allcursorb = cursorb.getCount();
			// ivall.setVisibility(View.GONE);
			if (ivc != null) {
				ivc.setVisibility(View.GONE);
			}
			ImageView ivb = (ImageView) arg1.findViewById(R.id.itemivbbb);
			ivb.setVisibility(View.VISIBLE);
			ivc = ivb;

			acdnameb = cursorb.getString(1);
			qcdaddressb = cursorb.getString(2);
			qcdlab = cursorb.getString(3);
			qcdlob = cursorb.getString(4);
			latlngb = new LatLng(Double.parseDouble(qcdlab),
					Double.parseDouble(qcdlob));
			mapcenter(Double.parseDouble(qcdlab), Double.parseDouble(qcdlob));
			markerlatlng(latlngb);
			bmeta.setText(acdnameb);
			sdj = 2;
			registerForContextMenu(bmlvb);
		}
	};
	OnItemClickListener onitemlistenerc = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			histaddress = cursorc.getString(1);
			String[] addrs = histaddress.split("&");
			if(addrs.length>1)
			{
				histaddress = addrs[1];
			}
			histname = cursorc.getString(2);
			histtel = cursorc.getString(3);
			histla = cursorc.getString(4);
			histlo = cursorc.getString(5);
			intentb = new Intent(Baidumap.this, Addressposition.class);
			bundleb = new Bundle();
			Addressposition.mapclose = 2;
			bundleb.putString("newaddress", histaddress);
			bundleb.putString("newname", histname);
			bundleb.putString("newtel", histtel);
			bundleb.putString("newlat", histla);
			bundleb.putString("newlon", histlo);
			bundleb.putString("djddf", djddf);
			bundleb.putString("mytime", mytime);
			bundleb.putString("xzdsj", xzdsj);
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
			bundleb.putInt("bcztqcgs", bcztqcgs);
			bundleb.putInt("bczthgjg", bczthgjg);
			bundleb.putInt("bcztbswl", bcztbswl);
			bundleb.putInt("bcztdwb", bcztdwb);
			bundleb.putInt("bcztdcm", bcztdcm);
			bundleb.putInt("bcztscm", bcztscm);
			bundleb.putInt("ztdsl", ztdsl);
			bundleb.putString("xiugainame", xiugainame);
			bundleb.putString("xiugaitel", xiugaitel);
			bundleb.putString("eyyq", eyyq);
			bundleb.putString("strycatvb", strycatvb);
			intentb.putExtras(bundleb);
			Addressposition.addressposition.finish();
			Baidumap.this.finish();
			startActivity(intentb);
		}
	};

	@Override
	public void onMapStatusChangeFinish(MapStatus arg0) {
		geocoder.reverseGeoCode(new ReverseGeoCodeOption().location(latlngd));
		shb.deletehi();
		if (ivc != null) {
			ivc.setVisibility(View.GONE);
		}
		// poisearchb.searchNearby(new PoiNearbySearchOption().location(latlngd)
		// .radius(50).keyword(mapaddress).pageNum(0));
		sdj = 3;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		disloga();
		return true;
	}

	private void disloga() {
		AlertDialog.Builder builder = new AlertDialog.Builder(Baidumap.this);
		builder.setMessage(getResources().getString(R.string.qdsccjl));
		builder.setTitle(getResources().getString(R.string.wxts));
		builder.setPositiveButton(getResources().getString(R.string.qd),
				new DialogInterface.OnClickListener() {
					@SuppressWarnings("deprecation")
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						ida = cursorc.getInt(0);
						shc.delete(ida);
						cursorc.requery();
						cursore = dba.query("sqlhistorya", null, null, null,
								null, null, null);
						if (cursore.moveToFirst()) {
							baidutstv.setVisibility(View.GONE);
						} else {
							baidutstv.setVisibility(View.VISIBLE);
						}
						arg0.dismiss();
					}
				});
		builder.setNegativeButton(getResources().getString(R.string.qx),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						arg0.dismiss();
					}
				});
		builder.create().show();
	}

	@Override
	public void onMapStatusChange(MapStatus arg0) {
		if (inta == 0) {
			inta++;
		} else {
			lata = baidumap.getMapStatus().target.latitude;
			lona = baidumap.getMapStatus().target.longitude;
			latlngd = new LatLng(lata, lona);
			markerlatlng(latlngd);
		}
	}

	@Override
	public void filpperDelete(float xPosition, float yPosition) {
		if (bmlvc.getChildCount() == 0)
			return;
		final int index = bmlvc.pointToPosition((int) xPosition,
				(int) yPosition);
		int firstVisiblePostion = bmlvc.getFirstVisiblePosition();
		View view = bmlvc.getChildAt(index - firstVisiblePostion);
		TranslateAnimation tranAnimation = new TranslateAnimation(0, 0, 0, 0);
		tranAnimation.setDuration(300);
		tranAnimation.setFillAfter(true);
		view.startAnimation(tranAnimation);
		tranAnimation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation arg0) {
				disloga();
			}

			@Override
			public void onAnimationEnd(Animation arg0) {
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}
		});
	}

	@Override
	public void onGetGeoCodeResult(GeoCodeResult arg0) {
	}

	@Override
	public void onMapStatusChangeStart(MapStatus arg0) {
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
	}
}