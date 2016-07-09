///**
// * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *     http://www.apache.org/licenses/LICENSE-2.0
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.femto.shipper.activity;
//
//import java.util.List;
//
//import android.app.AlertDialog;
//import android.app.AlertDialog.Builder;
//import android.app.ProgressDialog;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnCancelListener;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.View.OnTouchListener;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.EditText;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout.LayoutParams;
//import android.widget.Button;
//import android.widget.PopupWindow;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.baidu.location.BDLocation;
//import com.baidu.location.BDLocationListener;
//import com.baidu.location.BDNotifyListener;
//import com.baidu.location.LocationClient;
//import com.baidu.location.LocationClientOption;
//import com.baidu.mapapi.SDKInitializer;
//import com.baidu.mapapi.map.BaiduMap;
//import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
//import com.baidu.mapapi.map.BaiduMapOptions;
//import com.baidu.mapapi.map.BitmapDescriptor;
//import com.baidu.mapapi.map.BitmapDescriptorFactory;
//import com.baidu.mapapi.map.MapPoi;
//import com.baidu.mapapi.map.MapStatus;
//import com.baidu.mapapi.map.MapStatusUpdate;
//import com.baidu.mapapi.map.MapStatusUpdateFactory;
//import com.baidu.mapapi.map.MapView;
//import com.baidu.mapapi.map.Marker;
//import com.baidu.mapapi.map.MarkerOptions;
//import com.baidu.mapapi.map.MyLocationConfiguration;
//import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
//import com.baidu.mapapi.map.OverlayOptions;
//import com.baidu.mapapi.model.LatLng;
//import com.baidu.mapapi.search.core.PoiInfo;
//import com.baidu.mapapi.search.core.SearchResult;
//import com.baidu.mapapi.search.geocode.GeoCodeResult;
//import com.baidu.mapapi.search.geocode.GeoCoder;
//import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
//import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
//import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
//import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
//import com.baidu.mapapi.search.poi.PoiCitySearchOption;
//import com.baidu.mapapi.search.poi.PoiDetailResult;
//import com.baidu.mapapi.search.poi.PoiResult;
//import com.baidu.mapapi.search.poi.PoiSearch;
//import com.femto.shipper.R;
//import com.femto.shipper.activitya.Addresspo;
//import com.femto.shipper.activitya.Addressposition;
//import com.femto.shipper.activitya.Dutu;
//import com.femto.shipper.activitya.Historyjl;
//import com.femto.shipper.adapter.ListViewAdapter;
//import com.femto.shipper.base.BaseActivity;
//import com.femto.shipper.fragment.SelectMapFragment;
//import com.femto.shipper.utils.LogUtils;
//import com.femto.shipper.view.XListView;
//import com.femto.shipper.view.XListView.IXListViewListener;
//
//public class BaiduMapSelectActivity extends BaseActivity implements OnClickListener
//{
//	private PopupWindow mPopupWindow = null;
//	private XListView popupList = null;
//	private PopupListAddressAdapter mPopupListAddressAdapter = null;
//	private AlertDialog show;
//	static MapView mMapView = null;
//	FrameLayout mMapViewContainer = null;
//	LocationClient mLocClient;// 定位相关
//	public MyLocationListenner myListener = new MyLocationListenner();
//	EditText indexText = null;
//	int index = 0;
//	static BDLocation lastLocation = null;
//	public static BaiduMapSelectActivity instance = null;
//	ProgressDialog progressDialog;
//	private BaiduMap mBaiduMap;
//	private boolean is_first_dingwei = true;
//	private LocationMode mCurrentMode;
//	private ImageView iv_serch;
//	private EditText et_serchText;
//	private RelativeLayout ll_serch;
//	private PoiSearch mPoiSearch;
//	private int position;
//	private String zuihouweizhi;
//	private String sheng;
//	String dizi, a, dnl;
//	String zzdizi = "hello";
//	private Bundle bundlea;
//	private String ycatvastr, zhdd, zhname, zhtel, xhdd, xhname, xhtel, ztadd, ztaname, ztatel, ztbdd, ztbname, ztbtel, ztcdd, ztcname, ztctel,
//			ztddd, ztdname, ztdtel, ztedd, ztename, ztetel, djzx;
//	private int ztdsl;
//	private Intent inta, intb;
//
//	@Override
//	public void onCreate(Bundle savedInstanceState)
//	{
//		super.onCreate(savedInstanceState);
//		SDKInitializer.initialize(getApplicationContext());
//		setContentView(R.layout.activity_baidumap_select);
//		findViewById(R.id.iv_back).setOnClickListener(this);
//		tv_back = (TextView) findViewById(R.id.tv_back);
//		tv_back.setOnClickListener(this);
//		iv_serch = (ImageView) findViewById(R.id.iv_serch);
//		iv_serch.setOnClickListener(this);// 暂时注掉
//		et_serchText = (EditText) findViewById(R.id.et_serchText);
//		ll_serch = (RelativeLayout) findViewById(R.id.ll_serch);
//		initMapView();
//		position = getIntent().getIntExtra("position", 0);
//		zuihouweizhi = getIntent().getStringExtra("zuihouweizhi");
//
//		mMapView = new MapView(this, new BaiduMapOptions());
//		mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(mCurrentMode, true, null));
//		showMapWithLocationClient();
//
//		// 注册 SDK 广播监听者
//		IntentFilter iFilter = new IntentFilter();
//		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
//		iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
//		mBaiduReceiver = new BaiduSDKReceiver();
//		registerReceiver(mBaiduReceiver, iFilter);
//		et_serchText.setOnClickListener(this);
//		// init();
//		ddz();
//	}
//
//	private void ddz()
//	{
//		bundlea = new Bundle();
//		bundlea = getIntent().getExtras();
//		djzx = bundlea.getString("djzx");
//		ztdsl = bundlea.getInt("ztdsl");
//		zhdd = bundlea.getString("zhdd");
//		zhname = bundlea.getString("zhname");
//		zhtel = bundlea.getString("zhtel");
//		xhdd = bundlea.getString("xhdd");
//		xhname = bundlea.getString("xhname");
//		xhtel = bundlea.getString("xhtel");
//		ztadd = bundlea.getString("ztadd");
//		ztbdd = bundlea.getString("ztbdd");
//		ztcdd = bundlea.getString("ztcdd");
//		ztddd = bundlea.getString("ztddd");
//		ztedd = bundlea.getString("ztedd");
//		ztaname = bundlea.getString("ztaname");
//		ztbname = bundlea.getString("ztbname");
//		ztcname = bundlea.getString("ztcname");
//		ztdname = bundlea.getString("ztdname");
//		ztename = bundlea.getString("ztename");
//		ztatel = bundlea.getString("ztatel");
//		ztbtel = bundlea.getString("ztbtel");
//		ztctel = bundlea.getString("ztctel");
//		ztdtel = bundlea.getString("ztdtel");
//		ztetel = bundlea.getString("ztetel");
//		ycatvastr = bundlea.getString("ycatvastr");
//	}
//
//	private void init()
//	{
//		bundlea = getIntent().getExtras();
//		dizi = bundlea.getString("dizi");
//		a = bundlea.getString("a");
//		dnl = bundlea.getString("dnl");
//	}
//
//	@Override
//	public void onClick(View v)
//	{
//		switch (v.getId())
//		{
//		case R.id.iv_back:
//			AlertDialog.Builder builder = new Builder(mContext);
//			builder.setMessage("可以点击右上的'确定'保存选择的地址.");
//			builder.setPositiveButton("确定", null);
//			builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
//			{
//				@Override
//				public void onClick(DialogInterface dialog, int which)
//				{
//					finish();
//				}
//			});
//			show = builder.show();
//
//			break;
//		case R.id.tv_back:
//
//			String trim = et_serchText.getText().toString().trim();
//			if (TextUtils.isEmpty(trim))
//			{
//				showToast("请选择地址");
//				return;
//			}
//
//			if (mLatLng == null)
//			{
//				showToast("请选择正确的地址");
//				return;
//			}
//
//			// Intent intenta = new Intent(BaiduMapSelectActivity.this,
//			// ImmediatelyCarActivity.class);
//			// Bundle bundleb = new Bundle();
//			// bundleb.putString("dizi", dizi);
//			// bundleb.putString("a", a);
//			// bundleb.putString("dnl", dnl);
//			// if (mLatLng != null)
//			// {
//			// zzdizi = trim;
//			// // Bundle bundle = new Bundle();
//			// // bundle.putString("latitude",//经纬度
//			// // String.valueOf(mLatLng.latitude));
//			// // bundle.putString("longitude",
//			// // String.valueOf(mLatLng.longitude));
//			// // bundle.putString("address", address);
//			// // bundle.putInt("position", position);
//			// // bundle.putString("sheng", sheng);//省份和市
//			// // bundle.putString("shi", city);
//			// }
//			// new ImmediatelyCarActivity().zzdizi = zzdizi;
//			// intenta.putExtras(bundleb);
//			// startActivity(intenta);
//
//			if (djzx.equals("zh") || djzx.equals("xh"))
//			{
//				tz();
//			}
//			if (djzx.equals("zta") || djzx.equals("ztb") || djzx.equals("ztc") || djzx.equals("ztc") || djzx.equals("ztd") || djzx.equals("zte"))
//			{
//				tza();
//			}
//			break;
//		case R.id.iv_serch:
//			LogUtils.i("城市:" + city);
//			String qureLocation = et_serchText.getText().toString().trim();
//			if (TextUtils.isEmpty(qureLocation))
//			{
//				showToast("请输入查询的地点名");
//				return;
//			} else
//			{
//				getPopupWindow().showAsDropDown(ll_serch);
//				indexPage = 1;
//				mPoiSearch.searchInCity((new PoiCitySearchOption()).city("汕头").keyword(qureLocation).pageNum(1));
//				// mPoiSearch.searchNearby(new
//				// PoiNearbySearchOption().keyword(qureLocation).radius(Integer.MAX_VALUE).location(mLatLng)
//				// .pageNum(indexPage));
//				hideKeyboard();
//			}
//			break;
//		case R.id.et_serchText:
//			if (mPopupWindow != null)
//			{
//				mPopupWindow.dismiss();
//			}
//
//			break;
//		}
//	}
//
//	private void tz()
//	{
//		inta = new Intent(BaiduMapSelectActivity.this, Addressposition.class);
//		String trim = et_serchText.getText().toString().trim();
////		new Addressposition().haa = "a";
//		Bundle bda = new Bundle();
//		bda.putString("address", trim);
//		bda.putString("djzx", djzx);
//		bda.putInt("ztdsl", ztdsl);
//		bda.putString("zhdd", zhdd);
//		bda.putString("zhname", zhname);
//		bda.putString("zhtel", zhtel);
//		bda.putString("xhdd", xhdd);
//		bda.putString("xhname", xhname);
//		bda.putString("xhtel", xhtel);
//		bda.putString("ztadd", ztadd);
//		bda.putString("ztbdd", ztbdd);
//		bda.putString("ztcdd", ztcdd);
//		bda.putString("ztddd", ztddd);
//		bda.putString("ztedd", ztedd);
//		bda.putString("ztaname", ztaname);
//		bda.putString("ztbname", ztbname);
//		bda.putString("ztcname", ztcname);
//		bda.putString("ztdname", ztdname);
//		bda.putString("ztename", ztename);
//		bda.putString("ztatel", ztatel);
//		bda.putString("ztbtel", ztbtel);
//		bda.putString("ztctel", ztctel);
//		bda.putString("ztdtel", ztdtel);
//		bda.putString("ztetel", ztetel);
//		bda.putString("ycatvastr", ycatvastr);
//		inta.putExtras(bda);
//		new Historyjl().finish();
//		startActivity(inta);
//		finish();
//	}
//
//	@SuppressWarnings("static-access")
//	private void tza()
//	{
//		intb = new Intent(BaiduMapSelectActivity.this, Addresspo.class);
//		String trim = et_serchText.getText().toString().trim();
//		new Addresspo().haa = "a";
//		Bundle bda = new Bundle();
//		bda.putString("address", trim);
//		bda.putString("djzx", djzx);
//		bda.putInt("ztdsl", ztdsl);
//		bda.putString("zhdd", zhdd);
//		bda.putString("zhname", zhname);
//		bda.putString("zhtel", zhtel);
//		bda.putString("xhdd", xhdd);
//		bda.putString("xhname", xhname);
//		bda.putString("xhtel", xhtel);
//		bda.putString("ztadd", ztadd);
//		bda.putString("ztbdd", ztbdd);
//		bda.putString("ztcdd", ztcdd);
//		bda.putString("ztddd", ztddd);
//		bda.putString("ztedd", ztedd);
//		bda.putString("ztaname", ztaname);
//		bda.putString("ztbname", ztbname);
//		bda.putString("ztcname", ztcname);
//		bda.putString("ztdname", ztdname);
//		bda.putString("ztename", ztename);
//		bda.putString("ztatel", ztatel);
//		bda.putString("ztbtel", ztbtel);
//		bda.putString("ztctel", ztctel);
//		bda.putString("ztdtel", ztdtel);
//		bda.putString("ztetel", ztetel);
//		bda.putString("ycatvastr", ycatvastr);
//		intb.putExtras(bda);
//		new Historyjl().finish();
//		startActivity(intb);
//		finish();
//	}
//
//	private int indexPage = 1;
//	private TextView tv_back = null;
//
//	/**
//	 * 构造广播监听类，监听 SDK key 验证以及网络异常广播
//	 */
//	public class BaiduSDKReceiver extends BroadcastReceiver
//	{
//		public void onReceive(Context context, Intent intent)
//		{
//			String s = intent.getAction();
//			if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR))
//			{
//				Toast.makeText(instance, "获取失败,请检查网络或稍后再试", Toast.LENGTH_SHORT).show();
//			} else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR))
//			{
//				Toast.makeText(instance, "网络出错", Toast.LENGTH_SHORT).show();
//			}
//		}
//	}
//
//	private BaiduSDKReceiver mBaiduReceiver;
//
//	private void initMapView()
//	{
//		mMapView = (MapView) findViewById(R.id.bmapView);
//		mMapView.showZoomControls(false);// 隐藏缩放控件
//		mMapView.setLongClickable(true);
//		mCurrentMode = LocationMode.NORMAL;
//		mBaiduMap = mMapView.getMap();
//		MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
//		mBaiduMap.setMapStatus(msu);
//		mBaiduMap.setOnMapClickListener(onMapClickListener);
//		mSearch = GeoCoder.newInstance();
//		mSearch.setOnGetGeoCodeResultListener(listener);
//		mPoiSearch = PoiSearch.newInstance();
//		mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
//	}
//
//	private void showMapWithLocationClient()
//	{
//		progressDialog = new ProgressDialog(this);
//		progressDialog.setCanceledOnTouchOutside(false);
//		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//		progressDialog.setMessage("正在确定你的位置...");
//		progressDialog.setOnCancelListener(new OnCancelListener()
//		{
//			public void onCancel(DialogInterface arg0)
//			{
//				if (progressDialog.isShowing())
//				{
//					progressDialog.dismiss();
//				}
//				Log.d("map", "cancel retrieve location");
//				finish();
//			}
//		});
//
//		progressDialog.show();
//		mLocClient = new LocationClient(this);
//		mLocClient.registerLocationListener(myListener);
//		LocationClientOption option = new LocationClientOption();
//		option.setOpenGps(true);// 打开gps
//		// option.setCoorType("bd09ll"); //设置坐标类型
//		// Johnson change to use gcj02 coordination. chinese national standard
//		// so need to conver to bd09 everytime when draw on baidu map
//		option.setCoorType("gcj02");
//		option.setScanSpan(30000);
//		option.setAddrType("all");
//		mLocClient.setLocOption(option);
//	}
//
//	@Override
//	protected void onPause()
//	{
//		mMapView.onPause();
//		if (mLocClient != null)
//		{
//			mLocClient.stop();
//		}
//		super.onPause();
//		lastLocation = null;
//	}
//
//	@Override
//	protected void onResume()
//	{
//		mMapView.onResume();
//		if (mLocClient != null)
//		{
//			mLocClient.start();
//		}
//		super.onResume();
//	}
//
//	@Override
//	protected void onDestroy()
//	{
//		if (mLocClient != null)
//			mLocClient.stop();
//		mMapView.onDestroy();
//		unregisterReceiver(mBaiduReceiver);
//		super.onDestroy();
//	}
//
//	/**
//	 * 地图上选取的地址
//	 */
//	private String address = "";
//
//	private GeoCoder mSearch;//
//	// 地理位置检索监听
//	OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener()
//	{
//		public void onGetGeoCodeResult(GeoCodeResult result)
//		{
//			if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR)
//			{
//				// 没有检索到结果
//				Toast.makeText(BaiduMapSelectActivity.this, "抱歉，未能找到结果..", Toast.LENGTH_LONG).show();
//				return;
//			}
//		}
//
//		@Override
//		public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result)
//		{
//			if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR)
//			{
//				Toast.makeText(BaiduMapSelectActivity.this, "抱歉，未能找到结果...", Toast.LENGTH_LONG).show();
//				return;
//			}
//			address = result.getAddress();
//			et_serchText.setText(address);
//			LogUtils.i("onGetReverseGeoCodeResult" + result.getAddress());
//		}
//	};
//	private OnMapClickListener onMapClickListener = new OnMapClickListener()
//	{
//		/**
//		 * 地图单击事件回调函数
//		 * 
//		 * @param point
//		 *            点击的地理坐标
//		 */
//		public void onMapClick(LatLng point)
//		{
//			popWin(point);
//			// 反Geo搜索
//			mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(point));
//		}
//
//		/**
//		 * 地图内 Poi 单击事件回调函数
//		 * 
//		 * @param poi
//		 *            点击的 poi 信息
//		 */
//		public boolean onMapPoiClick(MapPoi poi)
//		{
//			// 反Geo搜索
//			popWin(poi.getPosition());
//			mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(poi.getPosition()));
//			return true;
//		}
//	};
//	/**
//	 * 保存获取POI检索结果
//	 */
//	private List<PoiInfo> allPoi = null;
//	/**
//	 * Poi检索
//	 */
//	private OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener()
//	{
//		public void onGetPoiResult(PoiResult result)
//		{
//			// 获取POI检索结果
//			allPoi = result.getAllPoi();
//			if (allPoi != null && allPoi.size() > 0)
//			{
//				runOnUiThread(new Runnable()
//				{
//					public void run()
//					{
//						// if(mPopupListAddressAdapter==null){
//						// getPopupWindow().showAsDropDown(ll_serch);
//						// }else{
//						mPopupListAddressAdapter.addList(allPoi);
//						popupList.stopLoadMore();
//						// }
//					}
//				});
//
//			} else
//			{
//				et_serchText.setText("");
//				mPopupWindow.dismiss();
//				popupList.stopLoadMore();
//				Toast.makeText(BaiduMapSelectActivity.this, "抱歉，未能找到结果.", Toast.LENGTH_LONG).show();
//			}
//		}
//
//		public void onGetPoiDetailResult(PoiDetailResult result)
//		{
//			// 获取Place详情页检索结果
//		}
//	};
//	/**
//	 * 地图上选取的坐标
//	 */
//	private LatLng mLatLng = null;
//
//	// 自定义提示图标
//	public void popWin(LatLng point)
//	{
//		mLatLng = point;
//		if (mMarker != null)
//			mMarker.remove();
//		// 构建Marker图标
//		BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_location5);
//		// 构建MarkerOption，用于在地图上添加Marker
//		OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
//		// 在地图上添加Marker，并显示
//		mMarker = (Marker) mBaiduMap.addOverlay(option);
//	}
//
//	/**
//	 * 点击定位的覆盖物
//	 */
//	private Marker mMarker = null;
//	private String city = "";// 得到当前位置的城市。默认为深圳
//
//	/**
//	 * 监听函数，有新位置的时候，格式化成字符串，输出到屏幕中
//	 */
//	public class MyLocationListenner implements BDLocationListener
//	{
//		@Override
//		public void onReceiveLocation(BDLocation location)
//		{
//			if (location == null)
//			{
//				return;
//			}
//			tv_back.setEnabled(true);
//			if (progressDialog != null)
//			{
//				progressDialog.dismiss();
//			}
//			if (lastLocation != null)
//			{
//				if (lastLocation.getLatitude() == location.getLatitude() && lastLocation.getLongitude() == location.getLongitude())
//				{
//					Log.d("map", "same location, skip refresh");
//					// mMapView.refresh(); //need this refresh?
//					return;
//				}
//			}
//
//			if (is_first_dingwei)
//			{
//				mapCenterPoint(location.getLatitude(), location.getLongitude());
//				sheng = location.getProvince();
//
//				address = location.getAddrStr();
//				String city2 = location.getCity();
//				if (city2 != null && !city2.equals(""))
//				{
//					city = city2;
//				}
//				LogUtils.i("定位当地城市:" + city);// 可能为空?
//				lastLocation = location;
//				mBaiduMap.clear();
//				LatLng llA = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
//
//				// sheng;
//
//				is_first_dingwei = false;
//			}
//		}
//	}
//
//	public class NotifyLister extends BDNotifyListener
//	{
//		public void onNotify(BDLocation mlocation, float distance)
//		{
//
//		}
//	}
//
//	/**
//	 * 初始化PopupWindow
//	 * 
//	 * @return
//	 */
//	private PopupWindow getPopupWindow()
//	{
//		mPopupWindow = new PopupWindow(getApplicationContext());
//		final View mMenuView = View.inflate(this, R.layout.popupwindow_address, null);
//		popupList = (XListView) mMenuView.findViewById(R.id.clv_address);
//		mPopupListAddressAdapter = new PopupListAddressAdapter(BaiduMapSelectActivity.this);
//
//		popupList.setPullLoadEnable(true);// 设置是否可以上拉 加载第二条
//		popupList.setPullRefreshEnable(false);
//		popupList.setXListViewListener(mIXListViewListener);
//
//		// mPopupListAddressAdapter.setList(allPoi);
//		popupList.setAdapter(mPopupListAddressAdapter);
//
//		mPopupWindow.setContentView(mMenuView);
//		mPopupWindow.setWidth(LayoutParams.WRAP_CONTENT);
//		mPopupWindow.setHeight(LayoutParams.WRAP_CONTENT);
//		mPopupWindow.setFocusable(false);
//		mPopupWindow.setBackgroundDrawable(null);
//		mPopupWindow.setAnimationStyle(R.style.AnimBottomPopup);
//		mMenuView.setOnTouchListener(new OnTouchListener()
//		{
//			public boolean onTouch(View v, MotionEvent event)
//			{
//				int height = mMenuView.findViewById(R.id.ll_popupMenu).getTop();
//				int y = (int) event.getY();
//				if (event.getAction() == MotionEvent.ACTION_UP)
//				{
//					if (y > height)
//					{
//						mPopupWindow.dismiss();
//					}
//				}
//				return true;
//			}
//		});
//
//		return mPopupWindow;
//	}
//
//	private IXListViewListener mIXListViewListener = new IXListViewListener()
//	{
//		@Override
//		public void onRefresh()// 下拉刷新。
//		{
//		}
//
//		@Override
//		public void onLoadMore()// 上拉加载更多
//		{
//			indexPage = indexPage + 1;
//			// mPoiSearch.searchNearby(new
//			// PoiNearbySearchOption().keyword(et_serchText.getText().toString().trim()).radius(Integer.MAX_VALUE)
//			// .location(mLatLng).pageNum(indexPage));
//			mPoiSearch.searchInCity((new PoiCitySearchOption()).city(city).keyword(et_serchText.getText().toString().trim()).pageNum(indexPage));
//		}
//	};
//
//	private class PopupListAddressAdapter extends ListViewAdapter<PoiInfo>
//	{
//		public PopupListAddressAdapter(Context context)
//		{
//			super(context);
//		}
//
//		@Override
//		public View getView(final int position, View v, ViewGroup parent)
//		{
//			Holder holder;
//			if (v == null)
//			{
//				holder = new Holder();
//				v = View.inflate(context, R.layout.view_address_item, null);
//				holder.textView1 = (TextView) v.findViewById(R.id.textView1);
//				holder.textView2 = (TextView) v.findViewById(R.id.textView2);
//				v.setTag(holder);
//			} else
//			{
//				holder = (Holder) v.getTag();
//
//			}
//			holder.textView1.setText(myList.get(position).name);
//			holder.textView2.setText(myList.get(position).address);
//			holder.textView1.setOnClickListener(new OnClickListener()
//			{
//				@Override
//				public void onClick(View v)
//				{
//					serchMapResultShow(myList.get(position));
//					mPopupWindow.dismiss();
//				}
//			});
//			return v;
//		}
//	}
//
//	class Holder
//	{
//		TextView textView1, textView2;
//	}
//
//	/**
//	 * 搜索结果显示到地图上
//	 */
//	private void serchMapResultShow(PoiInfo mPoiInfo)
//	{
//		address = mPoiInfo.address;
//		et_serchText.setText(address);
//		mapCenterPoint(mPoiInfo.location.latitude, mPoiInfo.location.longitude);
//		popWin(mPoiInfo.location);
//	}
//
//	/**
//	 * 百度地图设置地图中心点
//	 * 
//	 * @param la
//	 * @param lo
//	 */
//	private void mapCenterPoint(double la, double lo)
//	{
//		// 设置地图中心
//		LatLng latlng = new LatLng(la, lo);
//		// 地图状态
//		MapStatus mapStatus = new MapStatus.Builder().target(latlng).zoom(16).build();
//		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(mapStatus));
//	}
//
//	/**
//	 * 隐藏软键盘
//	 */
//	private void hideKeyboard()
//	{
//		InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//		if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
//		{
//			if (getCurrentFocus() != null)
//				manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//		}
//	}
//}
