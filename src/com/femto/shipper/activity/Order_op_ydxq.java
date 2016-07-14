package com.femto.shipper.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.OnTrackListener;
import com.easemob.chatuidemo.activity.ChatActivity;
import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.YundanXQBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Order_op_ydxq extends BaseActivity implements OnClickListener {
	private ImageView orderB_tuoxiang, orderB_like, orderB_zhangkai,
			orderB_zhuangtaitu;
	private TextView orderB_name, orderB_xinxin, orderB_haoping,
			orderB_chepaihao, orderB_yunhou, orderB_zhuangtaitu_name;
	private LinearLayout orderB_weizi, orderB_lianxi, orderB_gongxiangweizi,
			orderB_quxiao, orderB_ll_layout, orderB_ll_layout1, yinchang1;
	private boolean isOpened = false, isOpened1 = false; // 默认是关闭
	private int height, height1, startTime, endTime, ycf = 0, wgjd = 0;
	private Intent it, itb;
	private SharedPreferences sharedPreferences;
	private boolean ture_flase = false;
	// lata, lnga, latb, lngb, latc, lngc, latd, lngd, late, lnge,latf,
	// lngf,latg, lngg
	private String sjid, ooa = "0001", kka = "0001", kkall, ooall, phonea,
			address_midway1str, address_midway2str, address_midway3str,
			address_midway4str, address_midway5str, sfendtime = "0001", mobile,
			driver_id, entityname, yid;
	private LBSTraceClient lbstraceclient;
	private long serviceid = 114430;
	private OnTrackListener ontracklistener;
	private MarkerOptions startmarker = null, endmarker = null;
	private PolylineOptions polylineoptions = null;
	private MapStatusUpdate mapstatusupdate = null;
	private MapView mMapView = null;
	private BaiduMap mBaiduMap = null;
	private LatLng startlatlng = null, endlatlng = null,
			address_midwaylatlng1 = null, address_midwaylatlng2 = null,
			address_midwaylatlng3 = null, address_midwaylatlng4 = null,
			address_midwaylatlng5 = null;
	private OverlayOptions overlayoptions1, overlayoptions2, overlayoptions3,
			overlayoptions4, overlayoptions5, overlayoptionsstart,
			overlayoptionsend;
	private BitmapDescriptor bitmapdescriptorstart = null,
			bitmapdescriptorend = null, bitmapdescriptorenda = null,
			bitmapdescriptorstarta = null, bitmapdescriptor1 = null,
			bitmapdescriptor2 = null, bitmapdescriptor3 = null,
			bitmapdescriptor4 = null, bitmapdescriptor5 = null;
	private Marker marker1 = null, marker2 = null, marker3 = null,
			marker4 = null, marker5 = null, markerstart = null,
			markerend = null;
	private Myservice myservice;
	private IntentFilter filter;

	@Override
	protected void onDestroy() {
		if (marker1 != null) {
			marker1.remove();
		}
		if (marker2 != null) {
			marker2.remove();
		}
		if (marker3 != null) {
			marker3.remove();
		}
		if (marker4 != null) {
			marker4.remove();
		}
		if (marker5 != null) {
			marker5.remove();
		}
		if (markerstart != null) {
			markerstart.remove();
		}
		if (markerend != null) {
			markerend.remove();
		}
		if (bitmapdescriptorstart != null) {
			bitmapdescriptorstart.recycle();
		}
		if (bitmapdescriptorend != null) {
			bitmapdescriptorend.recycle();
		}
		if (bitmapdescriptorstarta != null) {
			bitmapdescriptorstarta.recycle();
		}
		if (bitmapdescriptorenda != null) {
			bitmapdescriptorenda.recycle();
		}
		if (bitmapdescriptor1 != null) {
			bitmapdescriptor1.recycle();
		}
		if (bitmapdescriptor2 != null) {
			bitmapdescriptor2.recycle();
		}
		if (bitmapdescriptor3 != null) {
			bitmapdescriptor3.recycle();
		}
		if (bitmapdescriptor4 != null) {
			bitmapdescriptor4.recycle();
		}
		if (bitmapdescriptor5 != null) {
			bitmapdescriptor5.recycle();
		}
		mBaiduMap.clear();
		Order_op_ydxq.this.unregisterReceiver(myservice);
		super.onDestroy();
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.yundan_or_xq);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		info();
		orderB_ll_layout.measure(0, 0);
		height = orderB_ll_layout.getMeasuredHeight();
		LayoutParams layoutParams = (LayoutParams) orderB_ll_layout
				.getLayoutParams();
		layoutParams.height = 0;
		orderB_ll_layout.setLayoutParams(layoutParams);
		orderB_ll_layout1.measure(0, 0);
		height1 = orderB_ll_layout1.getMeasuredHeight();
		LayoutParams layoutParams1 = (LayoutParams) orderB_ll_layout1
				.getLayoutParams();
		layoutParams1.height = 0;
		orderB_ll_layout1.setLayoutParams(layoutParams1);
		mBaiduMap = mMapView.getMap();
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
		lbstraceclient = new LBSTraceClient(this);
		lbstraceclient
				.setLocationMode(com.baidu.trace.LocationMode.High_Accuracy);
		mMapView.setVisibility(View.VISIBLE);
		getintent();
		myservice = new Myservice();
		filter = new IntentFilter();
		filter.addAction("com.femto.shipper.activitya.Aapdbroad");
		Order_op_ydxq.this.registerReceiver(myservice, filter);
		// getintentYD(yid);
	}

	class Myservice extends BroadcastReceiver {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			Bundle bundle = arg1.getExtras();
			String send = bundle.getString("send");
			if (send.equals("sx")) {
				getintentYD(yid);
			}
		}
	}

	private void getintent() {
		itb = getIntent();
		// driver_id = it.getStringExtra("driver_id");
		// if (it.getStringExtra("driver_avatar").equals("")) {
		// orderB_tuoxiang.setImageResource(R.drawable.siji);
		// } else {
		// ImageLoader.getInstance().displayImage(
		// Net.PICURL + it.getStringExtra("driver_avatar"),
		// orderB_tuoxiang, application.options);
		// }
		// orderB_name.setText(it.getStringExtra("driver_name"));
		// orderB_haoping.setText(it.getStringExtra("star"));
		// orderB_chepaihao.setText(it.getStringExtra("plate_number"));
		// orderB_yunhou.setText(it.getStringExtra("state"));

		// if (it.getStringExtra("state").equals("已取消")
		// || it.getStringExtra("state").equals("已签收")
		// || it.getStringExtra("state").equals("已完成")) {
		// orderB_gongxiangweizi.setVisibility(View.GONE);
		// }
		// Float xingshu = Float.valueOf(it.getStringExtra("star"));
		// if (xingshu == 3) {
		// orderB_xinxin.setBackgroundResource(R.drawable.x3);
		// } else if (xingshu >= 3 && xingshu < 3.5) {
		// orderB_xinxin.setBackgroundResource(R.drawable.x3);
		// } else if (xingshu >= 3.5 && xingshu < 4) {
		// orderB_xinxin.setBackgroundResource(R.drawable.x3b);
		// } else if (xingshu >= 4 && xingshu < 4.5) {
		// orderB_xinxin.setBackgroundResource(R.drawable.x4);
		// } else if (xingshu >= 4.5 && xingshu < 5) {
		// orderB_xinxin.setBackgroundResource(R.drawable.x4b);
		// } else if (xingshu == 5) {
		// orderB_xinxin.setBackgroundResource(R.drawable.x5);
		// } else if (xingshu == 0) {
		// orderB_xinxin.setBackgroundResource(R.drawable.kx5);
		// }
		// if (it.getStringExtra("ismycar").equals("0")) {
		// orderB_like.setImageResource(R.drawable.like2);
		// } else if (it.getStringExtra("ismycar").equals("1")) {
		// orderB_like.setImageResource(R.drawable.like1);
		// }
		// if (Integer.valueOf(it.getStringExtra("order_state")) < 4) {
		// orderB_zhuangtaitu.setImageResource(R.drawable.quxiao);
		// orderB_zhuangtaitu_name.setText("取消订单");
		// } else if (Integer.valueOf(it.getStringExtra("order_state")) == 4) {
		// orderB_zhuangtaitu.setImageResource(R.drawable.pj1);
		// orderB_zhuangtaitu_name.setText("待评价");
		// }
		yid = itb.getStringExtra("yid");
		// mobile = it.getStringExtra("mobile");

	}

	public void getintentYD(String yid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "waybill_info");
		map.put("username", phonea);
		map.put("yid", yid);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.ORDERLIST + jiaMi;
		showProgressDialog(getResources().getString(R.string.jzz));
		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				yinchang1.setVisibility(View.GONE);
				dismissProgressDialog();
				showToast(getResources().getString(R.string.wlyc));
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				dismissProgressDialog();
				YundanXQBean yundanxqBean = GsonUtils.json2Bean(arg0.result,
						YundanXQBean.class);
				if (yundanxqBean.status.equals("0")) {
					if (Integer
							.valueOf(yundanxqBean.waybill_info.waybill_state) < 1) {
						orderB_weizi.setVisibility(View.VISIBLE);
					}
					sjid = yundanxqBean.waybill_info.driver_id;
					String kk = yundanxqBean.waybill_info.start_time;
					String oo = yundanxqBean.waybill_info.sign_time;
					kka = kk.substring(0, 4);
					String kkb = kk.substring(5, 7);
					String kkc = kk.substring(8, 10);
					String kkd = kk.substring(11, 13);
					String kke = kk.substring(14, 16);
					String kkf = kk.substring(17, 19);
					kkall = kka + "年" + kkb + "月" + kkc + "日" + kkd + "时" + kke
							+ "分" + kkf + "秒";
					ooa = oo.substring(0, 4);
					String oob = oo.substring(5, 7);
					String ooc = oo.substring(8, 10);
					String ood = oo.substring(11, 13);
					String ooe = oo.substring(14, 16);
					String oof = oo.substring(17, 19);
					ooall = ooa + "年" + oob + "月" + ooc + "日" + ood + "时" + ooe
							+ "分" + oof + "秒";
					orderB_chepaihao
							.setText(yundanxqBean.waybill_info.plate_number);
					orderB_name.setText(yundanxqBean.waybill_info.nick_name);
					orderB_yunhou.setText(yundanxqBean.waybill_info.car_status);
					String credit = yundanxqBean.waybill_info.credit;
					orderB_haoping.setText(credit);
					Float xingshu = Float.valueOf(credit);
					if (xingshu == 3) {
						orderB_xinxin.setBackgroundResource(R.drawable.x3);
					} else if (xingshu >= 3 && xingshu < 3.5) {
						orderB_xinxin.setBackgroundResource(R.drawable.x3);
					} else if (xingshu >= 3.5 && xingshu < 4) {
						orderB_xinxin.setBackgroundResource(R.drawable.x3b);
					} else if (xingshu >= 4 && xingshu < 4.5) {
						orderB_xinxin.setBackgroundResource(R.drawable.x4);
					} else if (xingshu >= 4.5 && xingshu < 5) {
						orderB_xinxin.setBackgroundResource(R.drawable.x4b);
					} else if (xingshu == 5) {
						orderB_xinxin.setBackgroundResource(R.drawable.x5);
					} else if (xingshu == 0) {
						orderB_xinxin.setBackgroundResource(R.drawable.kx5);
					}
					String sfsc = yundanxqBean.waybill_info.ismycar;
					if (sfsc.equals("0")) {
						orderB_like.setImageResource(R.drawable.like2);
					} else if (sfsc.equals("1")) {
						orderB_like.setImageResource(R.drawable.like1);
					}
					mobile = yundanxqBean.waybill_info.mobile;
					driver_id = yundanxqBean.waybill_info.driver_id;
					ImageLoader.getInstance().displayImage(
							Net.PICURL + yundanxqBean.waybill_info.avatar,
							orderB_tuoxiang, application.options);
					String startlatlngstr = yundanxqBean.waybill_info.addr_start;
					Double startlat = Double.valueOf(startlatlngstr
							.split("\\|")[1]);
					Double startlng = Double.valueOf(startlatlngstr
							.split("\\|")[2]);
					startlatlng = new LatLng(startlng, startlat);
					String endlatlngstr = yundanxqBean.waybill_info.address_end;
					Double endlat = Double.valueOf(endlatlngstr.split("\\|")[1]);
					Double endlng = Double.valueOf(endlatlngstr.split("\\|")[2]);
					endlatlng = new LatLng(endlng, endlat);
					address_midway1str = yundanxqBean.waybill_info.address_midway1;
					address_midway2str = yundanxqBean.waybill_info.address_midway2;
					address_midway3str = yundanxqBean.waybill_info.address_midway3;
					address_midway4str = yundanxqBean.waybill_info.address_midway4;
					address_midway5str = yundanxqBean.waybill_info.address_midway5;
					if (address_midway1str.equals("")
							|| address_midway1str == null) {
					} else {
						Double address_midway1lat = Double
								.valueOf(address_midway1str.split("\\|")[1]);
						Double address_midway1lng = Double
								.valueOf(address_midway1str.split("\\|")[2]);
						address_midwaylatlng1 = new LatLng(address_midway1lng,
								address_midway1lat);
					}
					if (address_midway2str.equals("")
							|| address_midway2str == null) {
					} else {
						Double address_midway2lat = Double
								.valueOf(address_midway2str.split("\\|")[1]);
						Double address_midway2lng = Double
								.valueOf(address_midway2str.split("\\|")[2]);
						address_midwaylatlng2 = new LatLng(address_midway2lng,
								address_midway2lat);
					}
					if (address_midway3str.equals("")
							|| address_midway3str == null) {
					} else {
						Double address_midway3lat = Double
								.valueOf(address_midway3str.split("\\|")[1]);
						Double address_midway3lng = Double
								.valueOf(address_midway3str.split("\\|")[2]);
						address_midwaylatlng3 = new LatLng(address_midway3lng,
								address_midway3lat);
					}
					if (address_midway4str.equals("")
							|| address_midway4str == null) {
					} else {
						Double address_midway4lat = Double
								.valueOf(address_midway4str.split("\\|")[1]);
						Double address_midway4lng = Double
								.valueOf(address_midway4str.split("\\|")[2]);
						address_midwaylatlng4 = new LatLng(address_midway4lng,
								address_midway4lat);
					}
					if (address_midway5str.equals("")
							|| address_midway5str == null) {
					} else {
						Double address_midway5lat = Double
								.valueOf(address_midway5str.split("\\|")[1]);
						Double address_midway5lng = Double
								.valueOf(address_midway5str.split("\\|")[2]);
						address_midwaylatlng5 = new LatLng(address_midway5lng,
								address_midway5lat);
					}
					if (startlatlng != null) {
						bitmapdescriptorstarta = BitmapDescriptorFactory
								.fromResource(R.drawable.qd1);
						overlayoptionsstart = new MarkerOptions().position(
								startlatlng).icon(bitmapdescriptorstarta);
						markerstart = (Marker) mBaiduMap
								.addOverlay(overlayoptionsstart);
					}
					if (endlatlng != null) {
						bitmapdescriptorenda = BitmapDescriptorFactory
								.fromResource(R.drawable.zd1);
						overlayoptionsend = new MarkerOptions().position(
								endlatlng).icon(bitmapdescriptorenda);
						markerend = (Marker) mBaiduMap
								.addOverlay(overlayoptionsend);
					}
					if (address_midwaylatlng1 != null) {
						bitmapdescriptor1 = BitmapDescriptorFactory
								.fromResource(R.drawable.ztd1);
						overlayoptions1 = new MarkerOptions().position(
								address_midwaylatlng1).icon(bitmapdescriptor1);
						marker1 = (Marker) mBaiduMap
								.addOverlay(overlayoptions1);
					}
					if (address_midwaylatlng2 != null) {
						bitmapdescriptor2 = BitmapDescriptorFactory
								.fromResource(R.drawable.ztd1);
						overlayoptions2 = new MarkerOptions().position(
								address_midwaylatlng2).icon(bitmapdescriptor2);
						marker2 = (Marker) mBaiduMap
								.addOverlay(overlayoptions2);
					}
					if (address_midwaylatlng3 != null) {
						bitmapdescriptor3 = BitmapDescriptorFactory
								.fromResource(R.drawable.ztd1);
						overlayoptions3 = new MarkerOptions().position(
								address_midwaylatlng3).icon(bitmapdescriptor3);
						marker3 = (Marker) mBaiduMap
								.addOverlay(overlayoptions3);
					}
					if (address_midwaylatlng4 != null) {
						bitmapdescriptor4 = BitmapDescriptorFactory
								.fromResource(R.drawable.ztd1);
						overlayoptions4 = new MarkerOptions().position(
								address_midwaylatlng4).icon(bitmapdescriptor4);
						marker4 = (Marker) mBaiduMap
								.addOverlay(overlayoptions4);
					}
					if (address_midwaylatlng5 != null) {
						bitmapdescriptor5 = BitmapDescriptorFactory
								.fromResource(R.drawable.ztd1);
						overlayoptions5 = new MarkerOptions().position(
								address_midwaylatlng5).icon(bitmapdescriptor5);
						marker5 = (Marker) mBaiduMap
								.addOverlay(overlayoptions5);
					}
					jiachanshu();
				} else {
					yinchang1.setVisibility(View.GONE);
					showToast(yundanxqBean.status + yundanxqBean.msg);
				}
			}
		});
	}

	private void mapcenter(LatLng latlng) {
		MapStatus mapstatus = new MapStatus.Builder().target(latlng).zoom(16)
				.build();
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(mapstatus));
	}

	private void jiachanshu() {
		HandlerThread myhandlethread = new HandlerThread("my_handler1");
		myhandlethread.start();
		MyHandle myhandle = new MyHandle(myhandlethread.getLooper());
		Message msg = myhandle.obtainMessage();
		msg.sendToTarget();
	}

	private void info() {
		mMapView = (MapView) findViewById(R.id.orderB_map);
		findViewById(R.id.left).setOnClickListener(this);
		findViewById(R.id.gengduoB).setOnClickListener(this);
		orderB_tuoxiang = (ImageView) findViewById(R.id.orderB_tuoxiang);
		orderB_like = (ImageView) findViewById(R.id.orderB_like);
		orderB_zhangkai = (ImageView) findViewById(R.id.orderB_zhangkai);
		orderB_name = (TextView) findViewById(R.id.orderB_name);
		orderB_xinxin = (TextView) findViewById(R.id.orderB_xinxin);
		orderB_haoping = (TextView) findViewById(R.id.orderB_haoping);
		orderB_chepaihao = (TextView) findViewById(R.id.orderB_chepaihao);
		orderB_yunhou = (TextView) findViewById(R.id.orderB_yunhou);
		orderB_weizi = (LinearLayout) findViewById(R.id.orderB_weizi);
		orderB_lianxi = (LinearLayout) findViewById(R.id.orderB_lianxi);
		orderB_gongxiangweizi = (LinearLayout) findViewById(R.id.orderB_gongxiangweizi);
		orderB_quxiao = (LinearLayout) findViewById(R.id.orderB_quxiao);
		orderB_zhuangtaitu = (ImageView) findViewById(R.id.orderB_zhuangtaitu);
		orderB_zhuangtaitu_name = (TextView) findViewById(R.id.orderB_zhuangtai_name);
		orderB_ll_layout = (LinearLayout) findViewById(R.id.orderB_ll_layout);
		orderB_ll_layout1 = (LinearLayout) findViewById(R.id.orderB_ll_layout1);
		yinchang1 = (LinearLayout) findViewById(R.id.yinchang1);
		orderB_weizi.setOnClickListener(this);
		orderB_lianxi.setOnClickListener(this);
		orderB_gongxiangweizi.setOnClickListener(this);
		orderB_quxiao.setOnClickListener(this);
		orderB_zhangkai.setOnClickListener(this);
		findViewById(R.id.duanxi).setOnClickListener(this);
		findViewById(R.id.dianhua).setOnClickListener(this);
		findViewById(R.id.qx).setOnClickListener(this);
		orderB_tuoxiang.setOnClickListener(this);
		mMapView.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		if (isFastDoubleClick()) {
			return;
		}
		switch (arg0.getId()) {

		case R.id.left:
			if (ture_flase) {
				HandlerThread myhandlethread = new HandlerThread("my_handler1");
				myhandlethread.interrupt();
			}
			finish();
			break;
		case R.id.gengduoB:
			it = new Intent(Order_op_ydxq.this, Order_gengduo.class);
			it.putExtra("yid", yid);
			startActivity(it);
			break;
		case R.id.orderB_zhangkai:
			toggle(true);
			if (isOpened1 == true) {
				// isOpened1 = true;
				toggle1(true);
			}
			break;
		case R.id.orderB_weizi:
			isOpened = true;
			toggle(true);
			getintentYD(yid);
			break;
		case R.id.orderB_lianxi:
			toggle1(true);
			isOpened = true;
			toggle(true);
			break;
		case R.id.duanxi:
			toggle1(true);
			startActivity(new Intent(Order_op_ydxq.this, ChatActivity.class)
					.putExtra("userId", mobile));
			break;
		case R.id.dianhua:
			toggle1(true);
			Uri uri = Uri.parse("tel:" + mobile);
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_DIAL);
			intent.setData(uri);
			Order_op_ydxq.this.startActivity(intent);
			break;
		case R.id.qx:
			toggle1(true);
			break;
		case R.id.orderB_quxiao:
			isOpened = true;
			toggle(true);
			if (orderB_zhuangtaitu_name.getText().toString().equals("待评价")) {
				it = new Intent(Order_op_ydxq.this, Order_pinjia.class);
				it.putExtra("yid", yid);
				startActivity(it);
			} else if (orderB_zhuangtaitu_name.getText().toString()
					.equals("取消订单")) {
				it = new Intent(Order_op_ydxq.this, Order_plContext.class);
				it.putExtra("yid", yid);
				it.putExtra("state", "state");
				startActivity(it);
			}
			break;
		case R.id.orderB_tuoxiang:
			Intent intent1 = new Intent(mContext,
					SiJi_informationActivity.class);
			intent1.putExtra("driver_id", driver_id);
			startActivity(intent1);
			break;
		case R.id.orderB_gongxiangweizi:
			isOpened = true;
			toggle(true);
			it = new Intent(Order_op_ydxq.this, Order_gengduo.class);
			it.putExtra("yid", yid);
			startActivity(it);
			break;
		default:
			break;
		}
	}

	private void yygj() {
		entityname = sjid;
		ontracklistener = new OnTrackListener() {

			@Override
			public void onRequestFailedCallback(String arg0) {
				Looper.prepare();
				Looper.loop();
			}

			@Override
			public void onQueryHistoryTrackCallback(String arg0) {
				super.onQueryHistoryTrackCallback(arg0);
				showHistoryTrack(arg0);
				showToast("0");
			}
		};
		lbstraceclient.setOnTrackListener(ontracklistener);
		startTime = Integer.parseInt(DateUtils.getTimeToStamp(kkall));
		if (ooa.equals(sfendtime)) {
			endTime = (int) (System.currentTimeMillis() / 1000);
		} else {
			endTime = Integer.parseInt(DateUtils.getTimeToStamp(ooall));
		}
		lbstraceclient.queryProcessedHistoryTrack(serviceid, entityname, 0, 1,
				startTime, endTime, 1000, 1, ontracklistener);
	}

	protected void showHistoryTrack(String historyTrack) {
		HistoryTrackData historyTrackData = GsonService.parseJson(historyTrack,
				HistoryTrackData.class);
		List<LatLng> latLngList = new ArrayList<LatLng>();
		if (historyTrackData != null && historyTrackData.getStatus() == 0) {
			if (historyTrackData.getListPoints() != null) {
				latLngList.addAll(historyTrackData.getListPoints());
			}
			drawHistoryTrack(latLngList, historyTrackData.distance);

		}
	}

	private void drawHistoryTrack(final List<LatLng> points,
			final double distance) {
		if (points == null || points.size() == 0) {
			Looper.prepare();
			if (wgjd == 0) {
				showToast("当前查询无轨迹点");
				wgjd = 1;
			}
			Looper.loop();
			resetMarker();
		} else if (points.size() > 1) {
			LatLng llC = points.get(0);
			LatLng llD = points.get(points.size() - 1);
			LatLngBounds bounds = new LatLngBounds.Builder().include(llC)
					.include(llD).build();
			mapstatusupdate = MapStatusUpdateFactory.newLatLngBounds(bounds);
			bitmapdescriptorstart = BitmapDescriptorFactory
					.fromResource(R.drawable.icon_st);
			bitmapdescriptorend = BitmapDescriptorFactory
					.fromResource(R.drawable.blue_north_21);
			startmarker = new MarkerOptions()
					.position(points.get(points.size() - 1))
					.icon(bitmapdescriptorstart).zIndex(9).draggable(true);
			endmarker = new MarkerOptions().position(points.get(0))
					.icon(bitmapdescriptorend).zIndex(9).draggable(true);
			polylineoptions = new PolylineOptions().width(10).color(Color.RED)
					.points(points);
			addMarker();
			Looper.prepare();
			showToast("当前轨迹里程为 : " + (int) distance + "米");
			Looper.loop();
		}
	}

	protected void addMarker() {
		mBaiduMap.clear();
		if (startlatlng != null) {
			bitmapdescriptorstarta = BitmapDescriptorFactory
					.fromResource(R.drawable.qd1);
			overlayoptionsstart = new MarkerOptions().position(startlatlng)
					.icon(bitmapdescriptorstarta);
			markerstart = (Marker) mBaiduMap.addOverlay(overlayoptionsstart);
		}
		if (endlatlng != null) {
			bitmapdescriptorenda = BitmapDescriptorFactory
					.fromResource(R.drawable.zd1);
			overlayoptionsend = new MarkerOptions().position(endlatlng).icon(
					bitmapdescriptorenda);
			markerend = (Marker) mBaiduMap.addOverlay(overlayoptionsend);
		}
		if (address_midwaylatlng1 != null) {
			bitmapdescriptor1 = BitmapDescriptorFactory
					.fromResource(R.drawable.ztd1);
			overlayoptions1 = new MarkerOptions().position(
					address_midwaylatlng1).icon(bitmapdescriptor1);
			marker1 = (Marker) mBaiduMap.addOverlay(overlayoptions1);
		}
		if (address_midwaylatlng2 != null) {
			bitmapdescriptor2 = BitmapDescriptorFactory
					.fromResource(R.drawable.ztd1);
			overlayoptions2 = new MarkerOptions().position(
					address_midwaylatlng2).icon(bitmapdescriptor2);
			marker2 = (Marker) mBaiduMap.addOverlay(overlayoptions2);
		}
		if (address_midwaylatlng3 != null) {
			bitmapdescriptor3 = BitmapDescriptorFactory
					.fromResource(R.drawable.ztd1);
			overlayoptions3 = new MarkerOptions().position(
					address_midwaylatlng3).icon(bitmapdescriptor3);
			marker3 = (Marker) mBaiduMap.addOverlay(overlayoptions3);
		}
		if (address_midwaylatlng4 != null) {
			bitmapdescriptor4 = BitmapDescriptorFactory
					.fromResource(R.drawable.ztd1);
			overlayoptions4 = new MarkerOptions().position(
					address_midwaylatlng4).icon(bitmapdescriptor4);
			marker4 = (Marker) mBaiduMap.addOverlay(overlayoptions4);
		}
		if (address_midwaylatlng5 != null) {
			bitmapdescriptor5 = BitmapDescriptorFactory
					.fromResource(R.drawable.ztd1);
			overlayoptions5 = new MarkerOptions().position(
					address_midwaylatlng5).icon(bitmapdescriptor5);
			marker5 = (Marker) mBaiduMap.addOverlay(overlayoptions5);
		}
		if (null != mapstatusupdate) {
			mBaiduMap.setMapStatus(mapstatusupdate);
		}
		// if (null != startmarker) {
		// mBaiduMap.addOverlay(startmarker);
		// }
		if (null != endmarker) {
			// if (!ooa.equals(sfendtime)) {
			mBaiduMap.addOverlay(endmarker);
			// }
		}

		if (null != polylineoptions) {
			mBaiduMap.addOverlay(polylineoptions);
		}
	}

	private void resetMarker() {
		startmarker = null;
		endmarker = null;
		polylineoptions = null;
	}

	class MyHandle extends Handler {
		public MyHandle(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			try {
				ture_flase = true;
				if (kka.equals(sfendtime)) {
					if (ycf == 0) {
						showToast("亲，司机还未出发哦！");
						ycf = 1;
						mapcenter(startlatlng);
					}
				} else {
					yygj();
				}
			} catch (Exception e) {
			}
		}
	}

	private void toggle(boolean animated) {

		if (isOpened) {
			if (animated) {
				int start = height;
				int end = 0;
				doAnimation(start, end);
			} else {
				LayoutParams layoutParams = (LayoutParams) orderB_ll_layout
						.getLayoutParams();
				layoutParams.height = 0;
				orderB_ll_layout.setLayoutParams(layoutParams);

			}
		} else {
			if (animated) {
				int start = 0;
				int end = height;
				doAnimation(start, end);
			} else {
				LayoutParams layoutParams = (LayoutParams) orderB_ll_layout
						.getLayoutParams();
				layoutParams.height = height;
				orderB_ll_layout.setLayoutParams(layoutParams);
			}
		}
		if (isOpened) {
			ObjectAnimator.ofFloat(orderB_zhangkai, "rotation", -180, 0)
					.start();
		} else {
			ObjectAnimator.ofFloat(orderB_zhangkai, "rotation", 0, -180)
					.start();
		}
		isOpened = !isOpened;
	}

	private void doAnimation(int start, int end) {
		ValueAnimator valueAnimator = new ValueAnimator();
		valueAnimator.setDuration(300);
		valueAnimator.setIntValues(start, end);
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				int value = (Integer) valueAnimator.getAnimatedValue();
				LayoutParams layoutParams = (LayoutParams) orderB_ll_layout
						.getLayoutParams();
				layoutParams.height = value;
				orderB_ll_layout.setLayoutParams(layoutParams);
			};
		});
		valueAnimator.start();
	}

	private void doAnimation1(int start, int end) {
		ValueAnimator valueAnimator = new ValueAnimator();
		valueAnimator.setDuration(300);
		valueAnimator.setIntValues(start, end);
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				int value = (Integer) valueAnimator.getAnimatedValue();
				LayoutParams layoutParams = (LayoutParams) orderB_ll_layout1
						.getLayoutParams();
				layoutParams.height = value;
				orderB_ll_layout1.setLayoutParams(layoutParams);
			};
		});
		valueAnimator.start();
	}

	private void toggle1(boolean animated) {
		if (isOpened1) {
			if (animated) {
				int start = height1;
				int end = 0;
				doAnimation1(start, end);
			} else {
				LayoutParams layoutParams1 = (LayoutParams) orderB_ll_layout1
						.getLayoutParams();
				layoutParams1.height = 0;
				orderB_ll_layout1.setLayoutParams(layoutParams1);

			}
		} else {
			if (animated) {
				int start = 0;
				int end = height1;
				doAnimation1(start, end);
			} else {
				LayoutParams layoutParams1 = (LayoutParams) orderB_ll_layout1
						.getLayoutParams();
				layoutParams1.height = height1;
				orderB_ll_layout1.setLayoutParams(layoutParams1);
			}
		}
		isOpened1 = !isOpened1;
	}

	private void mapCenterPoint(double la, double lo) {
		LatLng latlng = new LatLng(la, lo);
		MapStatus mapStatus = new MapStatus.Builder().target(latlng).zoom(15)
				.build();
		MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(latlng, 13);
		mBaiduMap.animateMapStatus(u);
		mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(mapStatus));
	}

	// private void initLocation() {
	// mLocationClient = new LocationClient(this);
	// mLocationClient.registerLocationListener(myListener);
	// LocationClientOption option = new LocationClientOption();
	// option.setLocationMode(LocationMode.Hight_Accuracy);
	// option.setCoorType("bd09ll");
	// option.setScanSpan(5000);
	// option.setIsNeedAddress(true);
	// option.setNeedDeviceDirect(true);// 返回的定位结果包含手机机头的方向
	// mLocationClient.setLocOption(option);
	// mLocationClient.start();
	// }
	@Override
	protected void onResume() {
		super.onResume();
		getintentYD(yid);
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
	// private void fwfwq() {
	// Map<String, String> map = new HashMap<String, String>();
	// map.put("key", "get_track");
	// map.put("username", phonea);
	// map.put("yid", yid);
	// map.put("pwd", passworda);
	// String jiaMi = ToolUtils.JiaMi(map);
	// String url = Net.cyadd + jiaMi;
	// showProgressDialog("加载中...");
	// application.doGet_kuaishu(url, new RequestCallBack<String>() {
	// @Override
	// public void onFailure(HttpException arg0, String arg1) {
	// dismissProgressDialog();
	// }
	//
	// @Override
	// public void onSuccess(ResponseInfo<String> arg0) {
	// dismissProgressDialog();
	// Hqgj hqgj = GsonUtils.json2Bean(arg0.result, Hqgj.class);
	// if (hqgj.status.equals("0")) {
	// if (hqgj.list.size() > 1) {
	// lata = hqgj.list.get(0).lat;
	// lnga = hqgj.list.get(0).lng;
	// latb = hqgj.list.get(1).lat;
	// lngb = hqgj.list.get(1).lng;
	//
	// if (hqgj.list.size() == 2) {
	// listsize = 2;
	// LatLng stlatlng = new LatLng(Float.valueOf(lata),
	// Float.valueOf(lnga));
	// LatLng enlatlng = new LatLng(Float.valueOf(latb),
	// Float.valueOf(lngb));
	// // showToast(lata + "," + latb + "," + lnga + ","
	// // + lngb);
	// PlanNode stNode = PlanNode.withLocation(stlatlng);
	// PlanNode enNode = PlanNode.withLocation(enlatlng);
	// routeplansearch
	// .drivingSearch((new DrivingRoutePlanOption())
	// .from(stNode).to(enNode));
	// }
	// if (hqgj.list.size() == 3) {
	// // showToast("hqgj.list.size()" + "=3");
	// latc = hqgj.list.get(2).lat;
	// lngc = hqgj.list.get(2).lng;
	//
	// listsize = 3;
	// list = new ArrayList<PlanNode>();
	// LatLng stlatlng = new LatLng(Float.valueOf(lata),
	// Float.valueOf(lnga));
	// // LatLng enlatlng = new LatLng(Float.valueOf(latb),
	// // Float.valueOf(lngb));
	// LatLng enlatlng = new LatLng(Float.valueOf(latc),
	// Float.valueOf(lngc));
	// PlanNode stNode = PlanNode.withLocation(stlatlng);
	// list.add(PlanNode.withLocation(new LatLng(Double
	// .parseDouble(latb), Double
	// .parseDouble(lngb))));
	// PlanNode enNode = PlanNode.withLocation(enlatlng);
	// DrivingRoutePlanOption drivingRoutePlanOption = new
	// DrivingRoutePlanOption();
	// drivingRoutePlanOption.from(stNode).passBy(list)
	// .to(enNode);
	// routeplansearch
	// .drivingSearch(drivingRoutePlanOption);
	// // Log.e("latlng>>>", lata + "," + latb + "," + latc
	// // + "," + lnga + "," + lngb + "," + lngc);
	// // routeplansearch
	// // .drivingSearch((new DrivingRoutePlanOption())
	// // .from(stNode).to(enNode));
	// }
	// if (hqgj.list.size() == 4) {
	// latc = hqgj.list.get(2).lat;
	// lngc = hqgj.list.get(2).lng;
	// latd = hqgj.list.get(3).lat;
	// lngd = hqgj.list.get(3).lng;
	// listsize = 4;
	// list = new ArrayList<PlanNode>();
	// LatLng stlatlng = new LatLng(Float.valueOf(lata),
	// Float.valueOf(lnga));
	// LatLng enlatlng = new LatLng(Float.valueOf(latd),
	// Float.valueOf(lngd));
	// PlanNode stNode = PlanNode.withLocation(stlatlng);
	// list.add(PlanNode.withLocation(new LatLng(Double
	// .parseDouble(latb), Double
	// .parseDouble(lngb))));
	// list.add(PlanNode.withLocation(new LatLng(Double
	// .parseDouble(latc), Double
	// .parseDouble(lngc))));
	// PlanNode enNode = PlanNode.withLocation(enlatlng);
	// DrivingRoutePlanOption drivingRoutePlanOption = new
	// DrivingRoutePlanOption();
	// drivingRoutePlanOption.from(stNode).passBy(list)
	// .to(enNode);
	// routeplansearch
	// .drivingSearch(drivingRoutePlanOption);
	// }
	// if (hqgj.list.size() == 5) {
	// latc = hqgj.list.get(2).lat;
	// lngc = hqgj.list.get(2).lng;
	// latd = hqgj.list.get(3).lat;
	// lngd = hqgj.list.get(3).lng;
	// late = hqgj.list.get(4).lat;
	// lnge = hqgj.list.get(4).lng;
	// list = new ArrayList<PlanNode>();
	// LatLng stlatlng = new LatLng(Float.valueOf(lata),
	// Float.valueOf(lnga));
	// LatLng enlatlng = new LatLng(Float.valueOf(late),
	// Float.valueOf(lnge));
	// PlanNode stNode = PlanNode.withLocation(stlatlng);
	// list.add(PlanNode.withLocation(new LatLng(Double
	// .parseDouble(latb), Double
	// .parseDouble(lngb))));
	// list.add(PlanNode.withLocation(new LatLng(Double
	// .parseDouble(latc), Double
	// .parseDouble(lngc))));
	// list.add(PlanNode.withLocation(new LatLng(Double
	// .parseDouble(latd), Double
	// .parseDouble(lngd))));
	// PlanNode enNode = PlanNode.withLocation(enlatlng);
	// DrivingRoutePlanOption drivingRoutePlanOption = new
	// DrivingRoutePlanOption();
	// drivingRoutePlanOption.from(stNode).passBy(list)
	// .to(enNode);
	// routeplansearch
	// .drivingSearch(drivingRoutePlanOption);
	// }
	// if (hqgj.list.size() == 6) {
	// latc = hqgj.list.get(2).lat;
	// lngc = hqgj.list.get(2).lng;
	// latd = hqgj.list.get(3).lat;
	// lngd = hqgj.list.get(3).lng;
	// late = hqgj.list.get(4).lat;
	// lnge = hqgj.list.get(4).lng;
	// latf = hqgj.list.get(5).lat;
	// lngf = hqgj.list.get(5).lng;
	// list = new ArrayList<PlanNode>();
	// LatLng stlatlng = new LatLng(Float.valueOf(lata),
	// Float.valueOf(lnga));
	// LatLng enlatlng = new LatLng(Float.valueOf(latf),
	// Float.valueOf(lngf));
	// PlanNode stNode = PlanNode.withLocation(stlatlng);
	// list.add(PlanNode.withLocation(new LatLng(Double
	// .parseDouble(latb), Double
	// .parseDouble(lngb))));
	// list.add(PlanNode.withLocation(new LatLng(Double
	// .parseDouble(latc), Double
	// .parseDouble(lngc))));
	// list.add(PlanNode.withLocation(new LatLng(Double
	// .parseDouble(latd), Double
	// .parseDouble(lngd))));
	// list.add(PlanNode.withLocation(new LatLng(Double
	// .parseDouble(late), Double
	// .parseDouble(lnge))));
	// PlanNode enNode = PlanNode.withLocation(enlatlng);
	// DrivingRoutePlanOption drivingRoutePlanOption = new
	// DrivingRoutePlanOption();
	// drivingRoutePlanOption.from(stNode).passBy(list)
	// .to(enNode);
	// routeplansearch
	// .drivingSearch(drivingRoutePlanOption);
	// }
	// if (hqgj.list.size() == 7) {
	// latc = hqgj.list.get(2).lat;
	// lngc = hqgj.list.get(2).lng;
	// latd = hqgj.list.get(3).lat;
	// lngd = hqgj.list.get(3).lng;
	// late = hqgj.list.get(4).lat;
	// lnge = hqgj.list.get(4).lng;
	// latf = hqgj.list.get(5).lat;
	// lngf = hqgj.list.get(5).lng;
	// latg = hqgj.list.get(6).lat;
	// lngg = hqgj.list.get(6).lng;
	// list = new ArrayList<PlanNode>();
	// LatLng stlatlng = new LatLng(Float.valueOf(lata),
	// Float.valueOf(lnga));
	// LatLng enlatlng = new LatLng(Float.valueOf(latg),
	// Float.valueOf(lngg));
	// PlanNode stNode = PlanNode.withLocation(stlatlng);
	// list.add(PlanNode.withLocation(new LatLng(Double
	// .parseDouble(latb), Double
	// .parseDouble(lngb))));
	// list.add(PlanNode.withLocation(new LatLng(Double
	// .parseDouble(latc), Double
	// .parseDouble(lngc))));
	// list.add(PlanNode.withLocation(new LatLng(Double
	// .parseDouble(latd), Double
	// .parseDouble(lngd))));
	// list.add(PlanNode.withLocation(new LatLng(Double
	// .parseDouble(late), Double
	// .parseDouble(lnge))));
	// list.add(PlanNode.withLocation(new LatLng(Double
	// .parseDouble(latg), Double
	// .parseDouble(lngg))));
	// PlanNode enNode = PlanNode.withLocation(enlatlng);
	// DrivingRoutePlanOption drivingRoutePlanOption = new
	// DrivingRoutePlanOption();
	// drivingRoutePlanOption.from(stNode).passBy(list)
	// .to(enNode);
	// routeplansearch
	// .drivingSearch(drivingRoutePlanOption);
	// }
	// }
	// } else {
	// showToast(hqgj.status + hqgj.msg);
	// }
	// }
	// });
	// }
	//
	// @Override
	// public void onGetDrivingRouteResult(DrivingRouteResult arg0) {
	// if (arg0 == null || arg0.error != SearchResult.ERRORNO.NO_ERROR) {
	// showToast("抱歉，未找到结果");
	// }
	// if (arg0.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
	// showToast("抱歉，起终点或途经点地址有岐义");
	// return;
	// }
	// if (arg0.error == SearchResult.ERRORNO.NO_ERROR) {
	// DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
	// mBaiduMap.setOnMarkerClickListener(overlay);
	// overlay.setData(arg0.getRouteLines().get(0));
	// overlay.addToMap();
	// overlay.zoomToSpan();
	// }
	//
	// }
	//
	// @Override
	// public void onGetBikingRouteResult(BikingRouteResult arg0) {
	// }
	//
	// @Override
	// public void onGetTransitRouteResult(TransitRouteResult arg0) {
	// }
	//
	// @Override
	// public void onGetWalkingRouteResult(WalkingRouteResult arg0) {
	// }
}