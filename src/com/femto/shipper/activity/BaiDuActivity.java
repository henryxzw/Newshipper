package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.OrderState;
import com.femto.shipper.utils.Constants;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.SharedPreferencesUtils;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class BaiDuActivity extends BaseActivity implements OnClickListener {

	private ImageView img_left;
	private MapView mMapView;
	private BaiduMap mBaidumap;
	private LocationClient mLocationClient;
	boolean isFirst = true;
	private BDLocationListener myListener = new MyLocationListener();
	BitmapDescriptor mCurrentMarker;
	
	private com.baidu.mapapi.map.MyLocationConfiguration.LocationMode mCurrentMode;
	private RelativeLayout rela_siji_xinxi;
	private TextView text_state;//中间订单状态
	private TextView text_cirty;//右上角取消订单按钮
	private ImageView img_touxiang;
	private TextView text_siji_name;
	private TextView tex_pinf;
	private TextView text_chepaihao;
	private TextView text_huowu_type;
	private ImageView img_1;
	private ImageView img_2;
	private ImageView img_3;
	private String phone;
	private String password;
	private String order_no;
	private String type;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_baidu);
		img_left = (ImageView) findViewById(R.id.img_left);
		img_left.setImageResource(R.drawable.nva_user);
		img_left.setOnClickListener(this);
		Intent intent = getIntent();
		type = intent.getStringExtra("type");
		order_no = intent.getStringExtra("order_no");
		application.userPhone =  phone = SharedPreferencesUtils.getString(mContext, Constants.USERPHONE, "");
		application.password = password = SharedPreferencesUtils.getString(mContext, Constants.USERPASSWORD, "");
		
		text_cirty = (TextView) findViewById(R.id.text_cirty);
		rela_siji_xinxi = (RelativeLayout) findViewById(R.id.rela_siji_xinxi);
		text_state = (TextView) findViewById(R.id.text_state);
		
		img_touxiang = (ImageView) findViewById(R.id.img_touxiang);
		text_siji_name = (TextView) findViewById(R.id.text_siji_name);
		
		tex_pinf = (TextView) findViewById(R.id.tex_pinf);
		text_chepaihao = (TextView) findViewById(R.id.text_chepaihao);
		text_huowu_type = (TextView) findViewById(R.id.text_huowu_type);
		img_1 = (ImageView) findViewById(R.id.img_1);
		img_2 = (ImageView) findViewById(R.id.img_2);
		img_3 = (ImageView) findViewById(R.id.img_3);
		
	    mMapView = (MapView) findViewById(R.id.map);
        mBaidumap = mMapView.getMap();
        UiSettings uiSettings = mBaidumap.getUiSettings();
        uiSettings.setCompassEnabled(false);//关闭指南针
        mMapView.showZoomControls(false);//隐藏缩放空间
        mCurrentMode =com.baidu.mapapi.map.MyLocationConfiguration.LocationMode.NORMAL;
        
        if(!TextUtils.isEmpty(type)){
        	 switch (type)
     		{
     		case "OrderConfrim":
     			text_state.setText("司机确认接单并在装货途中");
     			break;
     		case "OrderStart":
     			text_state.setText("司机出发装货");
     			break;
     		case "OrderArrive":
     			text_state.setText("司机到达装货点");
     			break;
     		case "OrderExpress":
     			text_state.setText("司机送货途中");
     			break;
     		case "OrderSign":
     			text_state.setText("签收订单");
     			break;
     		case "OrderComplete":
     			text_state.setText("完成订单");
     			break;
     		}
        }
       
        
        initLocation();
        getDataFoeNet();
        
	}

	@Override
	protected void onNewIntent(Intent intent)
	{
		super.onNewIntent(intent);
		LogUtils.i("onNewIntent");
		type = intent.getStringExtra("type");
		order_no = intent.getStringExtra("order_no");
	       if(!TextUtils.isEmpty(type)){
	    	   switch (type)
	     		{
	     		case "OrderConfrim":
	     			text_state.setText("司机确认接单并在装货途中");
	     			break;
	     		case "OrderStart":
	     			text_state.setText("司机出发装货");
	     			break;
	     		case "OrderArrive":
	     			text_state.setText("司机到达装货点");
	     			break;
	     		case "OrderExpress":
	     			text_state.setText("司机送货途中");
	     			break;
	     		case "OrderSign":
	     			text_state.setText("签收订单");
	     			break;
	     		case "OrderComplete":
	     			text_state.setText("完成订单");
	     			break;
	     		}
	        }
		application.userPhone =  phone = SharedPreferencesUtils.getString(mContext, Constants.USERPHONE, "");
		application.password = password = SharedPreferencesUtils.getString(mContext, Constants.USERPASSWORD, "");
		getDataFoeNet();
	}
	
	
	private void getDataFoeNet()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "push_order_info");
		map.put("username", phone);
		map.put("pwd", password);
		map.put("order_no", order_no);

		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.ORDERXINXI + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");

		application.doGet(url, new RequestCallBack<String>()
		{
			@Override
			public void onSuccess(ResponseInfo<String> arg0)
			{
				LogUtils.i(arg0.result);
				dismissProgressDialog();
				
				OrderState orderState = GsonUtils.json2Bean(arg0.result, OrderState.class);
				if(orderState.status.equals("0")){
					imageLoader.displayImage(Net.PICURL+orderState.order.avatar, img_touxiang, application.options);
					imageLoader.displayImage(Net.PICURL+orderState.order.p1, img_1, application.options);
					imageLoader.displayImage(Net.PICURL+orderState.order.p2, img_2, application.options);
					imageLoader.displayImage(Net.PICURL+orderState.order.p3, img_3, application.options);
					text_siji_name.setText(orderState.order.nick_name);
					tex_pinf.setText(orderState.order.credit);
					text_chepaihao.setText(orderState.order.plate_number);
					text_huowu_type.setText(orderState.order.car_type);
					
				}else{
					showToast(orderState.msg);
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1)
			{
				LogUtils.i("" + arg1);
				dismissProgressDialog();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_left:
			startActivity(new Intent(mContext,MyInterspaceActivity.class));
			finish();
			break;
		}
	}
	
	
	
	private void initLocation()
	{
		
		mBaidumap.setMyLocationEnabled(true);// 开启定位图层
		mLocationClient = new LocationClient(this); // 声明LocationClient类
		mLocationClient.registerLocationListener(myListener); // 注册监听函数
		initLocation2();
	}
	/**
	 * 初始化定位信息
	 */
	private void initLocation2()
	{
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 设置定位模式
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.setScanSpan(50000);// 设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
		option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
		mLocationClient.setLocOption(option);
		mLocationClient.start();
	}
	
	
	public class MyLocationListener implements BDLocationListener
	{

		@Override
		public void onReceiveLocation(BDLocation location)
		{
			LogUtils.i("定位:onReceiveLocation:"+location.getAddrStr());
			LogUtils.i("经纬度:"+location.getLongitude()+";;"+location.getLatitude());
			mCurrentMarker = BitmapDescriptorFactory
					.fromResource(R.drawable.icon_geo);//自定义图标
			mapCenterPoint(location.getLatitude(),location.getLongitude());
		       mBaidumap//跟随模式
		        .setMyLocationConfigeration(new MyLocationConfiguration(
		        		mCurrentMode, true, null));
		       
		       MyLocationData locData = new MyLocationData.Builder()
               .accuracy(location.getRadius())
                       // 此处设置开发者获取到的方向信息，顺时针0-360
               .direction(100).latitude(location.getLatitude())
               .longitude(location.getLongitude()).build();
		       mBaidumap.setMyLocationData(locData);
		       if (isFirst) {
		    	   isFirst = false;
		           LatLng ll = new LatLng(location.getLatitude(),
		                   location.getLongitude());
		           MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
		           mBaidumap.animateMapStatus(u);
		       }
		}
	}
	

	/**
	 * 百度地图设置地图中心点
	 */
	private void mapCenterPoint(double la, double lo)
	{
		// 设置地图中心
		LatLng latlng = new LatLng(la, lo);//currentZoom = 14;// 地图状态
		MapStatus mapStatus = new MapStatus.Builder().target(latlng).zoom(19).build(); // 地图状态
		mBaidumap.setMapStatus(MapStatusUpdateFactory.newMapStatus(mapStatus));
		
	}
	
	 @Override
	    protected void onPause() {
	        mMapView.onPause();
	        super.onPause();
	    }

	    @Override
	    protected void onResume() {
	        mMapView.onResume();
	        super.onResume();
	    }

	    @Override
	    protected void onDestroy() {
	    	mLocationClient.stop();
	        mMapView.onDestroy();
	        super.onDestroy();
	    }
    

}
