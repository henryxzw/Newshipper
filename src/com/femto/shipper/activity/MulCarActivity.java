package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.femto.shipper.R;
import com.femto.shipper.activitya.Bimp;
import com.femto.shipper.activitya.Ycactivitya;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.CarNumberBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.analytics.MobclickAgent;

public class MulCarActivity extends BaseActivity implements OnClickListener {
	private MapView mMapView = null;
	private BaiduMap mBaidumap = null;
	private BDLocationListener myListener = new MyLocationListener();
	private TextView text_address;
	private LocationClient mLocationClient = null;
	private TextView text_car_time;
	private TextView text_car_number;
	private BitmapDescriptor bdA = null, bitmap = null;
	private String lat = "", carcls = "0", lng = "";
	private SharedPreferences sharedpreferencesb, mySharedPreferences;
	private String city = "";
	private Editor editora = null, editord = null;

	@Override
	protected void onDestroy() {
		if (mLocationClient != null) {
			mLocationClient.stop();
		}
		if (mBaidumap != null) {
			mBaidumap.clear();
		}
		if (mMapView != null) {
			mMapView.onDestroy();
		}
		if (bdA != null) {
			bdA.recycle();
		}
		if (bitmap != null) {
			bitmap.recycle();
		}
		if (editora != null) {
			editora.clear();
		}
		if (editord != null) {
			editord.clear();
		}
		super.onDestroy();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mulcar);
		sharedpreferencesb = getSharedPreferences("yball",
				Activity.MODE_PRIVATE);
		editora = sharedpreferencesb.edit();
		mySharedPreferences = getSharedPreferences("dinwei_xinxi",
				Activity.MODE_PRIVATE);
		editord = mySharedPreferences.edit();
		text_address = (TextView) findViewById(R.id.text_address);
		findViewById(R.id.left).setOnClickListener(this);
		text_car_time = (TextView) findViewById(R.id.text_car_time);
		text_car_number = (TextView) findViewById(R.id.text_car_number);
		mMapView = (MapView) findViewById(R.id.map);
		// LogUtils.i("求的请4:");
		mBaidumap = mMapView.getMap();
		mLocationClient = new LocationClient(this);
		findViewById(R.id.rela_find).setOnClickListener(this);
		UiSettings uiSettings = mBaidumap.getUiSettings();
		uiSettings.setCompassEnabled(false);// 关闭指南针
		mMapView.showZoomControls(false);// 隐藏缩放空间
		mMapView.showScaleControl(false);
	}

	private void getDataForNet() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "nearby_car_list");
		map.put("lng", lng);
		map.put("lat", lat);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.HUOQUXIANSHANGCHELIANG + jiaMi;
		application.doGet(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				LogUtils.i(arg0.result);
				dismissProgressDialog();
				try {
					CarNumberBean carNumberBean = GsonUtils.json2Bean(
							arg0.result, CarNumberBean.class);
					if (carNumberBean.status.equals("0")) {
						carcls = carNumberBean.count;
						text_car_number.setText(carcls + "辆");
						text_car_time.setText(carNumberBean.distance + "km");
						if (carNumberBean.list.size() != 0) {
							for (int i = 0; i < carNumberBean.list.size(); i++) {// 添加覆盖物功能
								bdA = BitmapDescriptorFactory
										.fromResource(R.drawable.blue_north_21);
								float jingdu = Float
										.parseFloat(carNumberBean.list.get(i).lng);
								float weidu = Float
										.parseFloat(carNumberBean.list.get(i).lat);
								LatLng llA = new LatLng(weidu, jingdu);
								MarkerOptions ooA = new MarkerOptions()
										.position(llA).icon(bdA);
								mBaidumap.addOverlay(ooA);
							}
						} else if (carNumberBean.status.equals("3")) {
							showToast("司机没有上线");

						} else {
							showToast("附近没有车辆");
						}
					}
				} catch (Exception e) {
					showToast("附近没有车辆");
				}

			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				LogUtils.i("" + arg1);
				dismissProgressDialog();
				showToast(getResources().getString(R.string.wlyc));
			}
		});
	}

	private void qcsj() {
		editora.putString("bz", "");
		editora.putString("fzrxm", "");
		editora.putString("fzrdh", "");
		editora.putString("hwsx", "");
		editora.putString("bzzl", "");
		editora.putString("shi", "");
		editora.putString("xhi", "");
		editora.putString("sli", "");
		editora.putString("dt", "");
		editora.putString("slcs", "");
		editora.putString("gci", "");
		editora.putString("gcrs", "");
		editora.commit();
		Bimp.tempSelectBitmap.clear();
	}

	private void msyc() {
		qcsj();
		Intent intenta = new Intent(mContext, Ycactivitya.class);
		Ycactivitya.msychsyuyc = 0;
		startActivity(intenta);
	}

	@Override
	public void onClick(View v) {
		if (isFastDoubleClick()) {
			return;
		}
		switch (v.getId()) {
		case R.id.rela_find:
			msyc();
			break;
		case R.id.left:
			Intent intent = new Intent();
			intent.putExtra("carcls", carcls);
			intent.putExtra("city", city);
			this.setResult(5, intent);
			finish();
			// try {
			// String a = city.split("市")[0];
			//
			// intent.putExtra("remenchengshi", a);
			// setResult(Activity.RESULT_OK, intent);
			// mLocationClient.stop();
			// if (bdA != null) {
			// LogUtils.i("回收bitmap");
			// bdA.recycle();
			// }
			// finish();
			// } catch (Exception e) {
			// // TODO: handle exception
			// finish();
			// startActivity(MainActivity.class);
			// }
			break;
		}
	}

	private void initLocation() {
		// mapView.setBuiltInZoomControls(true);//表示可以设置缩放功能
		// mapController = mapView.getController();//获取缩放控件
		// GeoPoint geoPoint = new
		// GeoPoint((int)(39.915*1E6),(int)(116.404*1E6));//获取经纬度坐标
		// mapController.setCenter(geoPoint);//将该点设置为地图中心点坐标
		// mapController.setZoom(12);//设置缩放程度为12

		// 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		// / option.setCoorType("gcj02");
		option.setScanSpan(50000);// 设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
		option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
		option.setOpenGps(false);
		mLocationClient.setLocOption(option);
		mLocationClient.start();
		showProgressDialog(getResources().getString(R.string.jzz));
	}

	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			city = location.getCity();
			lat = String.valueOf(location.getLatitude());
			lng = String.valueOf(location.getLongitude());
			text_address.setText(location.getAddrStr());
			editord.putString("sheng", location.getProvince());
			editord.putString("city", city);
			editord.putString("lat", lat);
			editord.putString("lng", lng);
			editord.putString("address", location.getAddrStr());
			editord.commit();
			mapCenterPoint(Double.valueOf(lat), Double.valueOf(lng));
			MapAddOverlay(location.getLatitude(), location.getLongitude(),
					R.drawable.icon_location4);
			mLocationClient.stop();
			getDataForNet();
		}
	}

	private void MapAddOverlay(double la, double lo, int resId) {
		LatLng point = new LatLng(la, lo);// 定义Maker坐标点
		bitmap = BitmapDescriptorFactory.fromResource(resId);// 构建Marker图标
		OverlayOptions option = new MarkerOptions().position(point)
				.icon(bitmap);// 构建MarkerOption，用于在地图上添加Marker
		mBaidumap.addOverlay(option);// 在地图上添加Marker，并显示
		mapCenterPoint(la, lo);
	}

	/**
	 * 百度地图设置地图中心点
	 */
	private void mapCenterPoint(double la, double lo) {
		// 设置地图中心
		LatLng latlng = new LatLng(la, lo);
		// 地图状态
		MapStatus mapStatus = new MapStatus.Builder().target(latlng).zoom(16)
				.build();
		MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(latlng, 16);// 设置地图中心点以及缩放级别
		mBaidumap.animateMapStatus(u);
		mBaidumap.setMapStatus(MapStatusUpdateFactory.newMapStatus(mapStatus));
	}

	//
	// // ////////////单击返回//////////////////////
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 按下键盘上的返回按钮
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// String a = city.split("市")[0];
			// Intent intent = new Intent();
			// intent.putExtra("remenchengshi", a);
			// setResult(Activity.RESULT_OK, intent);
			// mLocationClient.stop();
			// if (bdA != null) {
			// LogUtils.i("回收bitmap");
			// bdA.recycle();
			// }
			Intent intent = new Intent();
			intent.putExtra("carcls", carcls);
			intent.putExtra("city", city);
			this.setResult(5, intent);
			finish();
		}
		return super.onKeyDown(keyCode, event);

	}

	public void onResume() {
		super.onResume();
		initLocation();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		Log.e("onddd", "onPause");
		MobclickAgent.onPause(this);
	}

}
