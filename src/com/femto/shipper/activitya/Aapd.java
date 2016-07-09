package com.femto.shipper.activitya;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ZoomControls;
import com.alipay.sdk.app.PayTask;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.MapStatus.Builder;
import com.baidu.mapapi.model.LatLng;
import com.femto.shipper.R;
import com.femto.shipper.activity.OrderActivity;
import com.femto.shipper.activitya.Mydialogo.Dialogcallbacko;
import com.femto.shipper.activitya.Mydialogp.Dialogcallbackp;
import com.femto.shipper.activitya.Mydialogq.Dialogcallbackq;
import com.femto.shipper.alipay.PayResult;
import com.femto.shipper.alipay.SignUtils;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.SiJiXinXiBean;
import com.femto.shipper.bean.StatusBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.analytics.MobclickAgent;

@SuppressLint("HandlerLeak")
public class Aapd extends BaseActivity implements OnClickListener,
		BDLocationListener {
	private SharedPreferences sharedpreferencesc, sharedpreferencesd;
	@SuppressWarnings("unused")
	private String phonea, passworda, orderamounta, orderno, paymentid;
	private Editor editor = null;
	private MapView mapview = null;
	private BaiduMap baidumap = null;
	private LocationClient locationclient = null;
	private boolean isFirstLoc = true;
	private LocationClientOption locationclientoption;
	private MyLocationData mylocationdata;
	private LatLng latlng;
	private Builder builder;
	private View baiduview;
	private int i = 0, heighta, heightb, heightc, heightd, heighte, carsl = 0,
			jsdqpd = 0, djs = 6000, cxpdcs = 0, tzjs = 0, jfsj = 0, zfz = 0;
	public Myservice myservice = null;
	private RelativeLayout rla, rlb, rlc, rle, rld;
	private LayoutParams layoutparamsa, layoutparamsb, layoutparamsc,
			layoutparamsd, layoutparamse;
	private TextView apdtva, apdtvc, apdtvd, bpdtva, bpdtvc, bpdtvd, cpdtva,
			cpdtvc, cpdtvd, dpdtva, dpdtvc, dpdtvd, epdtva, epdtvc, epdtvd,
			pdclsltvb, pddsjtvb;
	private ImageView apdiva, bpdiva, cpdiva, dpdiva, epdiva, apdivb, bpdivb,
			cpdivb, dpdivb, epdivb, iv;
	private CountDownTimer countdowntimer = null, countdowntimera = null;
	private IntentFilter filter;// filtera
	private static int online = 0;

	// private Aapdbroad aapdbroad;
	@Override
	protected void onDestroy() {
		online = 0;
		if (countdowntimer != null) {
			countdowntimer.cancel();
		}
		if (countdowntimera != null) {
			countdowntimera.cancel();
		}
		if (mapview != null) {
			mapview.removeAllViews();
		}
		if (editor != null) {
			editor.clear();
		}
		if (baidumap != null) {
			baidumap.clear();
		}
		if (locationclient != null) {
			locationclient.stop();
		}
		Aapd.this.unregisterReceiver(myservice);
		// Aapd.this.unregisterReceiver(aapdbroad);
		// baidumap.clear();
		// Intent intent = new Intent(Intent.ACTION_MAIN);
		// intent.addCategory(Intent.CATEGORY_HOME);
		// intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// this.startActivity(intent);
		// System.exit(0);
		// android.os.Process.killProcess(android.os.Process.myPid());
		super.onDestroy();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.aapd);
		// sharedpreferencesa = getSharedPreferences("onesql",
		// Activity.MODE_PRIVATE);
		// one = sharedpreferencesa.getString("one", "");
		sharedpreferencesc = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedpreferencesc.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedpreferencesc.getString(
				getResources().getString(R.string.pwd), "");
		sharedpreferencesd = getSharedPreferences("allnoandcls",
				Activity.MODE_PRIVATE);
		carsl = Integer.valueOf(sharedpreferencesd
				.getString("car_quantity", ""));
		orderamounta = sharedpreferencesd.getString("orderamounta", "");
		orderno = sharedpreferencesd.getString("orderno", "");
		paymentid = sharedpreferencesd.getString("payment_id", "");
		// if (!one.equals("two")) {
		// Mydialogone mydialogone = new Mydialogone(Aapd.this);
		// // mydialogp.setDialogCallbackp(mydialogpdissmiss);
		// mydialogone.show();
		// }
		mapview = (MapView) findViewById(R.id.pdmapview);
		rla = (RelativeLayout) findViewById(R.id.rla);
		rlb = (RelativeLayout) findViewById(R.id.rlb);
		rlc = (RelativeLayout) findViewById(R.id.rlc);
		rld = (RelativeLayout) findViewById(R.id.rld);
		rle = (RelativeLayout) findViewById(R.id.rle);
		apdtva = (TextView) findViewById(R.id.apdtva);
		apdtvc = (TextView) findViewById(R.id.apdtvc);
		apdtvd = (TextView) findViewById(R.id.apdtvd);
		apdiva = (ImageView) findViewById(R.id.apdiva);
		bpdtva = (TextView) findViewById(R.id.bpdtva);
		bpdtvc = (TextView) findViewById(R.id.bpdtvc);
		bpdtvd = (TextView) findViewById(R.id.bpdtvd);
		bpdiva = (ImageView) findViewById(R.id.bpdiva);
		cpdtva = (TextView) findViewById(R.id.cpdtva);
		cpdtvc = (TextView) findViewById(R.id.cpdtvc);
		cpdtvd = (TextView) findViewById(R.id.cpdtvd);
		cpdiva = (ImageView) findViewById(R.id.cpdiva);
		dpdtva = (TextView) findViewById(R.id.dpdtva);
		dpdtvc = (TextView) findViewById(R.id.dpdtvc);
		dpdtvd = (TextView) findViewById(R.id.dpdtvd);
		dpdiva = (ImageView) findViewById(R.id.dpdiva);
		epdtva = (TextView) findViewById(R.id.epdtva);
		epdtvc = (TextView) findViewById(R.id.epdtvc);
		epdtvd = (TextView) findViewById(R.id.epdtvd);
		pdclsltvb = (TextView) findViewById(R.id.pdclsltvb);
		pddsjtvb = (TextView) findViewById(R.id.pddsjtvb);
		epdiva = (ImageView) findViewById(R.id.epdiva);
		epdivb = (ImageView) findViewById(R.id.epdivb);
		apdivb = (ImageView) findViewById(R.id.apdivb);
		bpdivb = (ImageView) findViewById(R.id.bpdivb);
		cpdivb = (ImageView) findViewById(R.id.cpdivb);
		dpdivb = (ImageView) findViewById(R.id.dpdivb);
		rla.measure(0, 0);
		rlb.measure(0, 0);
		rlc.measure(0, 0);
		rld.measure(0, 0);
		rle.measure(0, 0);
		heighta = rla.getMeasuredHeight();
		heightb = rlb.getMeasuredHeight();
		heightc = rlc.getMeasuredHeight();
		heightd = rld.getMeasuredHeight();
		heighte = rle.getMeasuredHeight();
		layoutparamsa = (LayoutParams) rla.getLayoutParams();
		layoutparamsa.height = 0;
		rla.setLayoutParams(layoutparamsa);
		layoutparamsb = (LayoutParams) rlb.getLayoutParams();
		layoutparamsb.height = 0;
		rlb.setLayoutParams(layoutparamsb);
		layoutparamsc = (LayoutParams) rlc.getLayoutParams();
		layoutparamsc.height = 0;
		rlc.setLayoutParams(layoutparamsc);
		layoutparamsd = (LayoutParams) rld.getLayoutParams();
		layoutparamsd.height = 0;
		rld.setLayoutParams(layoutparamsd);
		layoutparamse = (LayoutParams) rle.getLayoutParams();
		layoutparamse.height = 0;
		rle.setLayoutParams(layoutparamse);
		baidumap = mapview.getMap();
		baidumap.setMyLocationEnabled(true);
		locationclient = new LocationClient(this);
		locationclient.registerLocationListener(this);
		locationclientoption = new LocationClientOption();
		locationclientoption.setLocationMode(LocationMode.Hight_Accuracy);
		locationclientoption.setOpenGps(true);
		locationclientoption.setCoorType("bd09ll");
		locationclientoption.setScanSpan(1000);
		locationclientoption.setIsNeedAddress(true);
		locationclientoption.setNeedDeviceDirect(true);
		locationclient.setLocOption(locationclientoption);
		locationclient.start();
		baiduview = mapview.getChildAt(1);
		if (baiduview != null
				&& (baiduview instanceof ImageView || baiduview instanceof ZoomControls)) {
			baiduview.setVisibility(View.GONE);
		}
		mapview.showZoomControls(false);
		// sharedpreferencesb = getSharedPreferences("onesql",
		// Activity.MODE_PRIVATE);
		// editor = sharedpreferencesb.edit();
		// editor.putString("one", "two");
		// editor.commit();
		// aapdbroad = new Aapdbroad();
		// filtera = new IntentFilter();
		// filtera.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		// filtera.addAction("cn.jpush.android.intent.REGISTRATION");
		// filtera.addAction("cn.jpush.android.intent.UNREGISTRATION");
		// filtera.addAction("cn.jpush.android.intent.MESSAGE_RECEIVED");
		// filtera.addAction("cn.jpush.android.intent.NOTIFICATION_RECEIVED");
		// filtera.addAction("cn.jpush.android.intent.NOTIFICATION_OPENED");
		// filtera.addAction("cn.jpush.android.intent.ACTION_RICHPUSH_CALLBACK");
		// filtera.addAction("cn.jpush.android.intent.CONNECTION");
		// Aapd.this.registerReceiver(aapdbroad, filtera);
		myservice = new Myservice();
		filter = new IntentFilter();
		filter.addAction("com.femto.shipper.activitya.Aapdbroad");
		Aapd.this.registerReceiver(myservice, filter);
		pdclsltvb.setText(carsl + "");
		djs = carsl * 5000;
		djsks(djs);
		online = 1;
		// Log.e("pdcarsl>>>>>>>>", carsl + "");
		// Log.e("pdorderamounta>>>>>>>>", orderamounta);
		// Log.e("pdorderno>>>>>>>>", orderno);
	}

	class Myservice extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			Bundle bundle = arg1.getExtras();
			String send = bundle.getString("send");
			if (send.equals("driver_send_succ")) {
				String driverid = bundle.getString("driver_id");
				// Log.e("countdriverid", driverid);
				hdsjxx(driverid);
			}
			// else if (send.equals("success"))
			// {
			// Log.e("pdsuccess", "pdsuccess");
			// Mydialogo mydialogo = new Mydialogo(Aapd.this);
			// mydialogo.setDialogCallbacko(mydialogodissmiss);
			// mydialogo.show();
			// }
			else if (send.equals("send_not_enough")) {
				tzjs = 1;
				Mydialogq mydialogq = new Mydialogq(Aapd.this, i, carsl - i);
				mydialogq.setDialogCallbackq(mydialogqdissmiss);
				mydialogq.show();
				countdowntimer.cancel();
			} else if (send.equals("send_nocar")) {
				tzjs = 1;
				Mydialogp mydialogp = new Mydialogp(Aapd.this);
				mydialogp.setDialogCallbackp(mydialogpdissmiss);
				mydialogp.show();
				countdowntimer.cancel();
			}
			// String lat = bundle.getString("lat");
			// String lon = bundle.getString("lon");
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			qxddpd();
			return false;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	private void hdsjxx(String driverid) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", "driver_info");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("driver_id", driverid);
		String jiami = ToolUtils.JiaMi(map);
		String url = Net.cyadd + jiami;
		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				SiJiXinXiBean sijixinxibean = GsonUtils.json2Bean(arg0.result,
						SiJiXinXiBean.class);
				if (sijixinxibean.status.equals("0")) {
					String sijiname = sijixinxibean.nick_name;
					String sijicredit = sijixinxibean.credit;
					String sijinumber = sijixinxibean.plate_number;
					String sijinumberb = sijinumber.substring(2);
					String sijinumbera = sijinumber.substring(0, 2);
					i++;
					if (i == 1) {
						apdtva.setText(sijiname);
						apdtvc.setText(sijinumbera);
						apdtvd.setText(sijinumberb);
						iv = apdivb;
						pj(iv, sijicredit);
						ImageLoader.getInstance().displayImage(
								Net.PICURL + sijixinxibean.avatar, apdiva,
								application.options);
						pdclsltvb.setText(carsl - i + "");
					} else if (i == 2) {
						bpdtva.setText(sijiname);
						bpdtvc.setText(sijinumbera);
						bpdtvd.setText(sijinumberb);
						iv = bpdivb;
						pj(iv, sijicredit);
						ImageLoader.getInstance().displayImage(
								Net.PICURL + sijixinxibean.avatar, bpdiva,
								application.options);
						pdclsltvb.setText(carsl - i + "");
					} else if (i == 3) {
						cpdtva.setText(sijiname);
						cpdtvc.setText(sijinumbera);
						cpdtvd.setText(sijinumberb);
						iv = cpdivb;
						pj(iv, sijicredit);
						ImageLoader.getInstance().displayImage(
								Net.PICURL + sijixinxibean.avatar, cpdiva,
								application.options);
						pdclsltvb.setText(carsl - i + "");
					} else if (i == 4) {
						dpdtva.setText(sijiname);
						dpdtvc.setText(sijinumbera);
						dpdtvd.setText(sijinumberb);
						iv = dpdivb;
						pj(iv, sijicredit);
						ImageLoader.getInstance().displayImage(
								Net.PICURL + sijixinxibean.avatar, dpdiva,
								application.options);
						pdclsltvb.setText(carsl - i + "");
					} else if (i == 5) {
						epdtva.setText(sijiname);
						epdtvc.setText(sijinumbera);
						epdtvd.setText(sijinumberb);
						iv = epdivb;
						pj(iv, sijicredit);
						ImageLoader.getInstance().displayImage(
								Net.PICURL + sijixinxibean.avatar, epdiva,
								application.options);
						pdclsltvb.setText(carsl - i + "");
					}
					toggle();
					if (carsl == i) {
						Mydialogo mydialogo = new Mydialogo(Aapd.this);
						mydialogo.setDialogCallbacko(mydialogodissmiss);
						mydialogo.show();
						countdowntimer.cancel();
						if (paymentid.equals("2")) {
							zfdjs(300000);
							// zfdjs(5000);
						} else if (paymentid.equals("1")) {
							yjzf();
						}
					}
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast(getResources().getString(R.string.wlyc));
			}
		});
	}

	private void yjzf() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", "confirm_order_pay");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("order_no", orderno);
		String jiami = ToolUtils.JiaMi(map);
		String url = Net.cyadd + jiami;
		showProgressDialog(getResources().getString(R.string.jzz));
		application.doget(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				dismissProgressDialog();
				showToast(getResources().getString(R.string.wlyc));
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				dismissProgressDialog();
				Apdjsdqpdhelper apdjsdqpdhelper = GsonUtils.json2Bean(
						arg0.result, Apdjsdqpdhelper.class);
				if (apdjsdqpdhelper.status.equals("0")) {
					if (jsdqpd == 1) {
						jsdqpd();
					}
				} else {
					showToast(apdjsdqpdhelper.status);
				}
			}
		});
	}

	private void pj(ImageView iv, String a) {
		if (Float.valueOf(a) == 3) {
			iv.setBackgroundResource(R.drawable.x3);
		} else if (Float.valueOf(a) >= 3 && Float.valueOf(a) < 3.5) {
			iv.setBackgroundResource(R.drawable.x3);
		} else if (Float.valueOf(a) >= 3.5 && Float.valueOf(a) < 4) {
			iv.setBackgroundResource(R.drawable.x3b);
		} else if (Float.valueOf(a) >= 4 && Float.valueOf(a) < 4.5) {
			iv.setBackgroundResource(R.drawable.x4);
		} else if (Float.valueOf(a) >= 4.5 && Float.valueOf(a) < 5) {
			iv.setBackgroundResource(R.drawable.x4b);
		} else if (Float.valueOf(a) == 5) {
			iv.setBackgroundResource(R.drawable.x5);
		} else if (Float.valueOf(a) == 0) {
			iv.setBackgroundResource(R.drawable.kx5);
		}
	}

	private void toggle() {
		if (i == 1) {
			int starta = 0;
			int enda = heighta;
			doanimationa(starta, enda);
		} else if (i == 2) {
			int starta = 0;
			int enda = heightb;
			doanimationb(starta, enda);
		} else if (i == 3) {
			int starta = 0;
			int enda = heightc;
			doanimationc(starta, enda);
		} else if (i == 4) {
			int starta = 0;
			int enda = heightd;
			doanimationd(starta, enda);
		} else if (i == 5) {
			int starta = 0;
			int enda = heighte;
			doanimatione(starta, enda);
		}
	}

	private void doanimationa(int start, int end) {
		ValueAnimator valueanimator = new ValueAnimator();
		valueanimator.setDuration(300);
		valueanimator.setIntValues(start, end);
		valueanimator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				int value = (Integer) arg0.getAnimatedValue();
				LayoutParams layoutparams = (LayoutParams) rla
						.getLayoutParams();
				layoutparams.height = value;
				rla.setLayoutParams(layoutparams);
			};
		});
		valueanimator.start();
	}

	private void doanimationb(int start, int end) {
		ValueAnimator valueanimator = new ValueAnimator();
		valueanimator.setDuration(300);
		valueanimator.setIntValues(start, end);
		valueanimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				int value = (Integer) arg0.getAnimatedValue();
				LayoutParams layoutparams = (LayoutParams) rlb
						.getLayoutParams();
				layoutparams.height = value;
				rlb.setLayoutParams(layoutparams);
			};
		});
		valueanimator.start();
	}

	private void doanimationc(int start, int end) {
		ValueAnimator valueanimator = new ValueAnimator();
		valueanimator.setDuration(300);
		valueanimator.setIntValues(start, end);
		valueanimator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				int value = (Integer) arg0.getAnimatedValue();
				LayoutParams layoutparams = (LayoutParams) rlc
						.getLayoutParams();
				layoutparams.height = value;
				rlc.setLayoutParams(layoutparams);
			};
		});
		valueanimator.start();
	}

	private void doanimationd(int start, int end) {
		ValueAnimator valueanimator = new ValueAnimator();
		valueanimator.setDuration(300);
		valueanimator.setIntValues(start, end);
		valueanimator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				int value = (Integer) arg0.getAnimatedValue();
				LayoutParams layoutparams = (LayoutParams) rld
						.getLayoutParams();
				layoutparams.height = value;
				rld.setLayoutParams(layoutparams);
			};
		});
		valueanimator.start();
	}

	private void doanimatione(int start, int end) {
		ValueAnimator valueanimator = new ValueAnimator();
		valueanimator.setDuration(300);
		valueanimator.setIntValues(start, end);
		valueanimator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				int value = (Integer) arg0.getAnimatedValue();
				LayoutParams layoutparams = (LayoutParams) rle
						.getLayoutParams();
				layoutparams.height = value;
				rle.setLayoutParams(layoutparams);
			};
		});
		valueanimator.start();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// JPushInterface.onResume(mContext);
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		// aapd = "hello";
		MobclickAgent.onPause(this);
		
	}

	@Override
	public void onClick(View arg0) {
	}

	@Override
	public void onReceiveLocation(BDLocation arg0) {
		if (arg0 == null || mapview == null) {
			return;
		}
		mylocationdata = new MyLocationData.Builder()
				.accuracy(arg0.getRadius()).direction(100)
				.latitude(arg0.getLatitude()).longitude(arg0.getLongitude())
				.build();
		baidumap.setMyLocationData(mylocationdata);
		if (isFirstLoc) {
			isFirstLoc = false;
			latlng = new LatLng(arg0.getLatitude(), arg0.getLongitude());
			builder = new MapStatus.Builder();
			builder.target(latlng).zoom(18.0f);
			baidumap.animateMapStatus(MapStatusUpdateFactory
					.newMapStatus(builder.build()));
			locationclient.stop();
		}
	}

	private void djsks(int djsa) {
		// CountDownTimer countdowntimer = new CountDownTimer(djsa, 1000)
		countdowntimer = new CountDownTimer(32000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				int k = (int) (millisUntilFinished / 1000);
				if (k < 10) {
					pddsjtvb.setText("00:0" + k);
				} else {
					pddsjtvb.setText("00:" + k);
				}
			}

			@Override
			public void onFinish() {
				if (tzjs == 0) {
					if (i == 0) {
						Mydialogp mydialogp = new Mydialogp(Aapd.this);
						mydialogp.setDialogCallbackp(mydialogpdissmiss);
						mydialogp.show();
					} else if (carsl != i && carsl - i != 0) {
						Mydialogq mydialogq = new Mydialogq(Aapd.this, i, carsl
								- i);
						mydialogq.setDialogCallbackq(mydialogqdissmiss);
						mydialogq.show();
					}
					pddsjtvb.setText("00:00");
				}
			}
		}.start();
	}

	private void zfdjs(int djsa) {
		countdowntimera = new CountDownTimer(djsa, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				jfsj = 1;
			}

			@Override
			public void onFinish() {
				qxpd();
			}
		}.start();
	}

	Dialogcallbackq mydialogqdissmiss = new Dialogcallbackq() {

		@Override
		public void dialogdoq(int a) {
			if (a == 1) {
				if (cxpdcs < 2) {
					cxpdcs++;
					cxpd();
				} else {
					showToast(getResources().getString(R.string.tjcsgd));
					Aapd.this.finish();
					// startActivity(new Intent(Aapd.this, Ycactivitya.class));
				}
			} else if (a == 2) {
				jsdqpd = 1;
				yjzf();
				zfdjs(300000);
				// zfdjs(5000);
			} else if (a == 3) {
				qxpd();
			}
		}
	};
	Dialogcallbacko mydialogodissmiss = new Dialogcallbacko() {
		@Override
		public void dialogdoo(int a) {
			if (a == 1) {
				if (paymentid.equals("2")) {
					// pay(orderno, orderno, orderamounta);
					zfz = 1;
					pay(orderno, orderno, orderamounta);
				} else if (paymentid.equals("1")) {
					Ycactivitya.ycactivitya.finish();
					Aapd.this.finish();
					countdowntimer.cancel();
					if (jfsj == 1) {
						countdowntimera.cancel();
					}
					startActivity(new Intent(Aapd.this, OrderActivity.class));
				}
			}
		}
	};
	Dialogcallbackp mydialogpdissmiss = new Dialogcallbackp() {
		@Override
		public void dialogdop(int a) {
			if (a == 1) {
				qxpd();
			} else if (a == 2) {
				if (cxpdcs < 2) {
					cxpdcs++;
					cxpd();
				} else {
					showToast(getResources().getString(R.string.tjcsgd));
					Aapd.this.finish();
					// startActivity(new Intent(Aapd.this, Ycactivitya.class));
				}
			}
		}
	};

	private void jsdqpd() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", "agree_order");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("order_no", orderno);
		String jiami = ToolUtils.JiaMi(map);
		String url = Net.Car + jiami;
		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				System.out.println("--------------->>>" + arg0.result);
				Apdjsdqpdhelper statusbean = GsonUtils.json2Bean(arg0.result,
						Apdjsdqpdhelper.class);
				if (statusbean.status.equals("0")) {
					showToast(getResources().getString(R.string.jsdqpdcg));
					// String strmoney = statusbean.money;
					// pay(orderno, orderno, strmoney);
					mydialogodissmiss.dialogdoo(1);
					// pay(orderno, orderno, "0.01");
					// zfdjs(600000);

				} else {
					showToast(getResources().getString(R.string.jsdqpdsb)
							+ statusbean.status + statusbean.msg);
					Aapd.this.finish();
					countdowntimer.cancel();
					if (jfsj == 1) {
						countdowntimera.cancel();
					}
					startActivity(new Intent(Aapd.this, OrderActivity.class));
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast(getResources().getString(R.string.wlyc));
				showToast(getResources().getString(R.string.cxpdsb));
				Aapd.this.finish();
				countdowntimer.cancel();
				if (jfsj == 1) {
					countdowntimera.cancel();
				}
				// startActivity(new Intent(Aapd.this, Ycactivitya.class));
			}
		});
	}

	private void cxpd() {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", "send_again");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("order_no", orderno);
		String jiami = ToolUtils.JiaMi(map);
		String url = Net.Car + jiami;
		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				StatusBean statusbean = GsonUtils.json2Bean(arg0.result,
						StatusBean.class);
				if (statusbean.status.equals("0")) {
					// startActivity(new Intent(Aapd.this, Aapd.class));
					// Aapd.this.finish();
					tzjs = 0;
					countdowntimer.cancel();
					if (jfsj == 1) {
						countdowntimera.cancel();
					}
					djsks(djs);
				} else {
					showToast(getResources().getString(R.string.cxpdsb)
							+ statusbean.status + statusbean.msg);
					Aapd.this.finish();
					countdowntimer.cancel();
					if (jfsj == 1) {
						countdowntimera.cancel();
					}
					// startActivity(new Intent(Aapd.this, Ycactivitya.class));
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast(getResources().getString(R.string.wlyc));
				showToast(getResources().getString(R.string.cxpdsb));
				Aapd.this.finish();
				countdowntimer.cancel();
				if (jfsj == 1) {
					countdowntimera.cancel();
				}
				// startActivity(new Intent(Aapd.this, Ycactivitya.class));
			}
		});
	}

	private void qxpd() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", "cancel_order");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("order_no", orderno);
		String jiami = ToolUtils.JiaMi(map);
		String url = Net.Car + jiami;
		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				StatusBean statusbean = GsonUtils.json2Bean(arg0.result,
						StatusBean.class);
				if (statusbean.status.equals("0")) {
					if (zfz == 0) {
						showToast(getResources().getString(R.string.qxddcg));
						Aapd.this.finish();
						countdowntimer.cancel();
						if (jfsj == 1) {
							countdowntimera.cancel();
						}
					} else if (zfz == 1) {
						showToast(getResources().getString(R.string.qxddcgwxzf));
						Aapd.this.finish();
						countdowntimer.cancel();
						if (jfsj == 1) {
							countdowntimera.cancel();
						}
					}
					// startActivity(new Intent(Aapd.this, Ycactivitya.class));
				} else if (statusbean.status.equals("3")) {
					showToast(getResources().getString(R.string.qxddcg));
					countdowntimer.cancel();
					if (jfsj == 1) {
						countdowntimera.cancel();
					}
					Aapd.this.finish();
					// startActivity(new Intent(Aapd.this, Ycactivitya.class));
				} else {
					countdowntimer.cancel();
					if (jfsj == 1) {
						countdowntimera.cancel();
					}
					showToast(getResources().getString(R.string.qxddsb)
							+ statusbean.status + statusbean.msg);
					Aapd.this.finish();
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast(getResources().getString(R.string.wlyc));
				showToast(getResources().getString(R.string.qxddsb));
				Aapd.this.finish();
				countdowntimer.cancel();
				if (jfsj == 1) {
					countdowntimera.cancel();
				}
				// startActivity(new Intent(Aapd.this, Ycactivitya.class));
			}
		});
	}

	private void qxddpd() {
		AlertDialog.Builder builder = new AlertDialog.Builder(Aapd.this);
		builder.setMessage(getResources().getString(R.string.sfqxdd));
		builder.setTitle(getResources().getString(R.string.wxts));
		builder.setPositiveButton(getResources().getString(R.string.ss),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						qxpd();
						arg0.dismiss();
					}
				});
		builder.setNegativeButton(getResources().getString(R.string.f),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						arg0.dismiss();
					}
				});
		builder.create().show();
	}

	private void sfpd() {
		AlertDialog.Builder builder = new AlertDialog.Builder(Aapd.this);
		builder.setMessage(getResources().getString(R.string.sfqxdd));
		builder.setTitle(getResources().getString(R.string.wxts));
		builder.setPositiveButton(getResources().getString(R.string.ss),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						qxpd();
						arg0.dismiss();
					}
				});
		builder.setNegativeButton(getResources().getString(R.string.f),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// pay(orderno, orderno, orderamounta);
						zfz = 1;
						pay(orderno, orderno, orderamounta);
						arg0.dismiss();
					}
				});
		builder.create().show();
	}

	// zfb
	public static final String PARTNER = "2088021410599375";
	public static final String SELLER = "david@genmao.com.cn";
	public static final String RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMEKAdvkh0NvO36/5uX3Yp3g"
			+ "QauNK1P6hLjPCPDtLrE0YbnGmJwqzwATICBfr2o1zfbap1+qtlTN09RHQbJaFlLw2g94Mf7syTcTw2tOHkOixF/W5fFFLncGXiod7OHe"
			+ "KZWsDpMerai9lMnXUA1ZcoWroIBo4284cur7HmY0/mhzAgMBAAECgYEAsQrGja/YRoaboDHMSJlMvtLQJ9A/LrQSSZTH9H7zVd5eyo1T"
			+ "pda1JrJjwo0/Sj+yeHYtD1Z4BusHHieEH1j0IuCMECEpzL4pzseCOzXDtEJzOyfpy60yp182ghqHtZpuKjl6+Jb7OYGT6+ORWVu0qf8C"
			+ "tU7hDJF4rBXiOsh55dECQQDvozRzgr1EIOYHn/3i59pXc7FL7XSaIjQFAQDmoopNH/3fCce5d2f7kBvVw5oLfsSWioCyoF4IBnj6YUvO"
			+ "cz/vAkEAzjhGdEZAgk5Klb85SXNgZoQmpVJ508xHSmREpiGQgI46d6+cgsv1sPvxNX8SoEF3wiPxI5EsdDchmt3/dnYbvQJAYIVvEfu"
			+ "d+jv6CyYxH9zdQA40Ym3BYnfWVhGiaEMdy5TQL68DyDtFIblYzNAdUvX7fttInSok1bytq8PWwc//9wJBALrYml132bM3JLURkeJPO6Un"
			+ "T6yzUdbpXB63QswT1U3lkGwpHXIVQwOdAywpesLhDpVE45QKUXDrEvOy+WhoEzECQQC/XSdKmSL7X77M2hAYkBuVt5cr2q25zaLp5SfgF"
			+ "8rJhaj+WtIgFCuRwXys1GJU4GKM7AY+b411vgBc3mlGhUF7";
	public static final String RSA_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuB"
			+ "V64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_CHECK_FLAG = 2;
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				PayResult payResult = new PayResult((String) msg.obj);
				String resultStatus = payResult.getResultStatus();
				if (zfz == 0) {
					if (TextUtils.equals(resultStatus, "9000")) {
						showToast(getResources().getString(R.string.zfcg));
						Aapd.this.finish();
						Ycactivitya.ycactivitya.finish();
						countdowntimer.cancel();
						countdowntimera.cancel();
						startActivity(new Intent(Aapd.this, OrderActivity.class));
					} else {
						if (TextUtils.equals(resultStatus, "8000")) {
							showToast(getResources()
									.getString(R.string.zfjgqrz));
						} else {
							showToast(getResources().getString(R.string.zfsb));
							sfpd();
						}
					}
				} else {
					if (TextUtils.equals(resultStatus, "9000")) {
						showToast(getResources().getString(R.string.zfcg));
						if (online == 1) {
							Aapd.this.finish();
							Ycactivitya.ycactivitya.finish();
							countdowntimer.cancel();
							countdowntimera.cancel();
							startActivity(new Intent(Aapd.this,
									OrderActivity.class));
						}
					}

					else {
						if (TextUtils.equals(resultStatus, "8000")) {
							showToast(getResources()
									.getString(R.string.zfjgqrz));
						} else {
							showToast(getResources().getString(R.string.zfsb));
							if (online == 1) {
								sfpd();
							}
						}
					}
				}
				break;
			}
			case SDK_CHECK_FLAG: {
				showToast(getResources().getString(R.string.jcjgw) + msg.obj);
				break;
			}
			}
		};
	};

	public void pay(String order_no, String order_no2, String amount) {
		if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE)
				|| TextUtils.isEmpty(SELLER)) {
			new AlertDialog.Builder(mContext)
					.setTitle(getResources().getString(R.string.jg))
					.setMessage(
							getResources().getString(R.string.xypz)
									+ "PARTNER|RSA_PRIVATE|SELLER")
					.setPositiveButton(getResources().getString(R.string.qd),
							new DialogInterface.OnClickListener() {
								public void onClick(
										DialogInterface dialoginterface, int i) {
									// Aapd.this.finish();
									countdowntimer.cancel();
									if (jfsj == 1) {
										countdowntimera.cancel();
									}
								}
							}).show();
			return;
		}
		String orderInfo = getOrderInfo(order_no, order_no2, amount);
		String sign = sign(orderInfo);
		try {
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
				+ getSignType();
		Runnable payRunnable = new Runnable() {
			@Override
			public void run() {
				PayTask alipay = new PayTask(Aapd.this);
				String result = alipay.pay(payInfo);
				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	public void getSDKVersion() {
		PayTask payTask = new PayTask(Aapd.this);
		String version = payTask.getVersion();
		Toast.makeText(mContext, version, Toast.LENGTH_SHORT).show();
	}

	public String getOrderInfo(String subject, String body, String price) {
		String orderInfo = "partner=" + "\"" + PARTNER + "\"";
		orderInfo += "&seller_id=" + "\"" + SELLER + "\"";
		orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";
		orderInfo += "&subject=" + "\"" + subject + "\"";
		orderInfo += "&body=" + "\"" + body + "\"";
		orderInfo += "&total_fee=" + "\"" + price + "\"";
		orderInfo += "&notify_url=" + "\"" + Net.BASE
				+ "api/payment/alipaypc/notify_url.aspx" + "\"";
		orderInfo += "&service=\"mobile.securitypay.pay\"";
		orderInfo += "&payment_type=\"1\"";
		orderInfo += "&_input_charset=\"utf-8\"";
		orderInfo += "&it_b_pay=\"30m\"";
		orderInfo += "&return_url=\"m.alipay.com\"";
		return orderInfo;
	}

	public String getOutTradeNo() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
				Locale.getDefault());
		Date date = new Date();
		String key = format.format(date);
		Random r = new Random();
		key = key + r.nextInt();
		key = key.substring(0, 15);
		return key;
	}

	public String sign(String content) {
		return SignUtils.sign(content, RSA_PRIVATE);
	}

	public String getSignType() {
		return "sign_type=\"RSA\"";
	}
}