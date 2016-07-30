package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.easemob.EMCallBack;
import com.easemob.EMConnectionListener;
import com.easemob.EMError;
import com.easemob.applib.controller.HXSDKHelper;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMContactListener;
import com.easemob.chat.EMContactManager;
import com.easemob.chatuidemo.activity.ChatActivity;
import com.easemob.chatuidemo.activity.LoginActivity;
import com.easemob.chatuidemo.db.InviteMessgeDao;
import com.easemob.chatuidemo.db.UserDao;
import com.easemob.chatuidemo.domain.InviteMessage;
import com.easemob.chatuidemo.domain.InviteMessage.InviteMesageStatus;
import com.easemob.chatuidemo.domain.User;
import com.easemob.util.HanziToPinyin;
import com.femto.shipper.R;
import com.femto.shipper.activitya.Bimp;
import com.femto.shipper.activitya.Internetfx;
import com.femto.shipper.activitya.LanActivity;
import com.femto.shipper.activitya.Ycactivitya;
import com.femto.shipper.application.Constant;
import com.femto.shipper.application.DemoHXSDKHelper;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.GengXing;
import com.femto.shipper.bean.My_XinXiBean;
import com.femto.shipper.bean.StatusBean;
import com.femto.shipper.utils.Constants;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.femto.shipper.utils.Utils_GX;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

@SuppressLint("ShowToast")
public class MainActivity extends BaseActivity implements OnClickListener {
	private int old_version;
	private long exetime = 0;
	private boolean ntype = false;
	private TextView text_cirty;
	private RelativeLayout rela_xianshi;
	private TextView text_number;
	private My_XinXiBean my_xinxibean;
	private String phonea, passworda;
	public static Activity mainactivity;
	private LocationClient mLocationClient = null;
	protected static final String TAG = "MainActivity";
	public boolean isConflict = false;
	private MyConnectionListener connectionListener = null;
	private InviteMessgeDao inviteMessgeDao;
	private UserDao userDao;
	private BroadcastReceiver internalDebugReceiver = null;
	private BDLocationListener myListener = new MyLocationListener();
	private String newcity, oldtimestamp;
	private SharedPreferences sharedpreferencesb, mySharedPreferences,
			sharedPreferences, mySharedPreferencesa;
	private Editor editora = null, editorc = null, editord = null,
			editore = null;
	private int xzlcs = 0;
	private ConnectivityManager connectivitymanagera;
	private NetworkInfo networkinfoa;

	@Override
	protected void onDestroy() {
		// if (editor != null) {
		// editor.clear();
		// }
		if (editorc != null) {
			editorc.clear();
		}
		if (editore != null) {
			editore.clear();
		}
		if (editora != null) {
			editora.clear();
		}
		if (editord != null) {
			editord.clear();
		}
		if (mLocationClient != null) {
			mLocationClient.stop();
		}
		if (internalDebugReceiver != null) {
			unregisterReceiver(internalDebugReceiver);
		}
		super.onDestroy();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_my_main);
		mainactivity = this;
		gengxing();
		mySharedPreferences = getSharedPreferences("dinwei_xinxi",
				Activity.MODE_PRIVATE);
		editord = mySharedPreferences.edit();
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		editorc = sharedPreferences.edit();
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		sharedpreferencesb = getSharedPreferences("yball",
				Activity.MODE_PRIVATE);
		editora = sharedpreferencesb.edit();
		// sharedpreferencesa = getSharedPreferences("ccjghqbjg",
		// Activity.MODE_PRIVATE);
		// editor = sharedpreferencesa.edit();
		mySharedPreferencesa = getSharedPreferences("user_xinxi",
				Activity.MODE_PRIVATE);
		initHuanXin();// //环信状态
		findViewById(R.id.rela_find).setOnClickListener(this);
		findViewById(R.id.rela_yueche).setOnClickListener(this);
		findViewById(R.id.line_back).setOnClickListener(this);
		findViewById(R.id.rela_zz).setOnClickListener(this);
		findViewById(R.id.rela_yuyueyongche).setOnClickListener(this);
		findViewById(R.id.rela_order).setOnClickListener(this);
		rela_xianshi = (RelativeLayout) findViewById(R.id.rela_xianshi);
		rela_xianshi.setOnClickListener(this);
		text_number = (TextView) findViewById(R.id.text_number);
		ImageView img_left = (ImageView) findViewById(R.id.img_left);
		text_cirty = (TextView) findViewById(R.id.text_cirty);
		text_cirty.setOnClickListener(this);
		img_left.setImageResource(R.drawable.nva_user);
		getDataForNet();// //获取用户信息
		// hdqsjghccjg();
		// initHuanXin()
		initLocation();
		try {
			JPushInterface.resumePush(getApplicationContext());
			JPushInterface.setAliasAndTags(getApplicationContext(), phonea,
					null, mAliasCallback);
		} catch (Exception e) {

		}
	}

	private void getDataForNet(final String city) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "driver_count");
		map.put("city", city);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.FUJINCARNUMBER + jiaMi;
		showProgressDialog("正在获取本地区的可用车数量...");
		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				dismissProgressDialog();
				xzlcs = 0;
				StatusBean statusBean = GsonUtils.json2Bean(arg0.result,
						StatusBean.class);
				if (statusBean.status.equals("0")) {
					rela_xianshi.setVisibility(View.VISIBLE);
					text_number.setText(statusBean.count);
				} else {
					text_number.setText("0");
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				dismissProgressDialog();
				showToast("网络异常");
				text_number.setText("0");
				xzlcs = 0;
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - exetime) > 2000) {
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				exetime = System.currentTimeMillis();
			} else {
				// JPushInterface.setAliasAndTags(getApplicationContext(),
				// "djkasbdjkasbdjk", null, mAliasCallback);
				// finish();
				Intent intent = new Intent();
				intent.setAction("ExitApp");
				this.sendBroadcast(intent);
				super.finish();
				// Intent it = new Intent(MainActivity.this, LanActivity.class);
				// startActivity(it);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
		@Override
		public void gotResult(int code, String alias, Set<String> tags) {
			switch (code) {
			case 0:
				break;
			case 6002:
				break;
			default:
				break;
			}
		}
	};

	@Override
	public void onClick(View v) {
		if (isFastDoubleClick()) {
			return;
		}
		switch (v.getId()) {
		case R.id.rela_order:// // 订单查询
			startActivity(new Intent(mContext, OrderActivity.class));
			break;
		case R.id.rela_zz:// /更多物流.. 附近车辆 地图覆盖物
			// startActivity(MulCarActivity.class);
			startActivity(new Intent(mContext, Internetfx.class));
			break;
		case R.id.text_cirty:// /服务城市
			Intent intent = new Intent(mContext, HotCityActivity.class);
			startActivityForResult(intent, Constants.SELECT_CITY);
			break;
		case R.id.rela_yueche:// 马上用车
			msyc();
			break;
		case R.id.line_back:// 个人主页
			startActivity(new Intent(mContext, MyInterspaceActivity.class));
			break;
		case R.id.rela_find:// //发现
			startActivity(new Intent(mContext, Internetfx.class));
			break;
		case R.id.rela_yuyueyongche:// /预约用车
			yyyc();
			break;
		case R.id.rela_xianshi:// //////////附近车辆
			String string = text_number.getText().toString();
			if (!TextUtils.isEmpty(string)) {
				Intent it = new Intent(mContext, MulCarActivity.class);
				startActivityForResult(it, Constants.SELECT_CITY);
			}
			break;
		}
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

	private void yyyc() {
		qcsj();
		Intent intenta = new Intent(mContext, Ycactivitya.class);
		Ycactivitya.msychsyuyc = 1;
		startActivity(intenta);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// if (requestCode == 3) {
		// showToast("dsad");
		// xzlcs = 1;
		// String remenchengshi = data.getStringExtra("remenchengshi");
		// text_cirty.setText(remenchengshi + "市");
		// getDataForNet(remenchengshi + "市");
		//
		// }
		// if (requestCode == 6) {
		// showToast("666");
		// }
		if (resultCode == 5) {
			// String carcls = data.getStringExtra("carcls");
			newcity = data.getStringExtra("city");
			if (newcity == null || newcity.equals("")) {
			} else {
				text_cirty.setText(newcity);
			}
			// if (carcls.equals("number")) {
			xzlcs = 1;
			getDataForNet(newcity);
			// }
		}
	}

	// ///////////////////////////////获取用户信息，临时保存//////////////////////////////////////////////
	private void getDataForNet() {
		oldtimestamp = mySharedPreferencesa.getString("timestamp", "").trim();
		if (oldtimestamp == null || oldtimestamp.equals("")) {
			oldtimestamp = "0";
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "user_info");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("timestamp", "0");
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.YONGHUXINXI + jiaMi;
		application.doGet(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				my_xinxibean = GsonUtils.json2Bean(arg0.result,
						My_XinXiBean.class);
				if (my_xinxibean.status.equals("0")) {
					editore = mySharedPreferencesa.edit();
					editore.putString("timestamp", my_xinxibean.timestamp);
					editore.putString("nc", my_xinxibean.nick_name);
					editore.putString("mobile", my_xinxibean.mobile);
					editore.putString("avatar", my_xinxibean.avatar);
					editore.putString("role_name", my_xinxibean.role_name);
					editore.putString("group_id", my_xinxibean.group_id);
					editore.putString("email", my_xinxibean.email);
					editore.putString("myclsl", my_xinxibean.carlist_count);
					editore.putString("mylxsl", my_xinxibean.myline_count);
					editore.putString("mydkjsl", my_xinxibean.coupon_count);
					editore.commit();
				} else if (my_xinxibean.status.equals("200")) {

				} else {
					showToast("数据异常!");
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast("网络异常.....");
			}
		});
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	// //////////////////////////返回手机型号信息//////////////////////////////////////////////////////
	public static String getDeviceInfo(Context context) {
		try {
			org.json.JSONObject json = new org.json.JSONObject();
			android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);

			String device_id = tm.getDeviceId();

			android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);

			String mac = wifi.getConnectionInfo().getMacAddress();
			json.put("mac", mac);

			if (TextUtils.isEmpty(device_id)) {
				device_id = mac;
			}

			if (TextUtils.isEmpty(device_id)) {
				device_id = android.provider.Settings.Secure.getString(
						context.getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);
			}

			json.put("device_id", device_id);

			LogUtils.i("--------------返回的json信息--------------->>>"
					+ json.toString());
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void initLocation() {
		showProgressDialog("正在获取本地区的可用车数量...");
		mLocationClient = new LocationClient(this);
		mLocationClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);
		// option.setPriority(LocationClientOption.GpsFirst);
		option.setCoorType("bd09ll");
		option.setScanSpan(5000);
		option.setIsNeedAddress(true);
		option.setNeedDeviceDirect(true);
		mLocationClient.setLocOption(option);
		connectivitymanagera = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		networkinfoa = connectivitymanagera.getActiveNetworkInfo();
		if (networkinfoa == null) {
			ntype = false;
		} else {
			ntype = connectivitymanagera.getActiveNetworkInfo().isAvailable();
		}
		if (ntype == true) {
			mLocationClient.start();
		} else {
			dismissProgressDialog();
			showToast(getResources().getString(R.string.wlyc));
		}
	}

	public class MyLocationListener implements BDLocationListener {
		@Override
		public void onReceiveLocation(BDLocation location) {
			String sheng = location.getProvince();
			newcity = location.getCity();
			double lat = location.getLatitude();
			double lng = location.getLongitude();
			String address = location.getAddrStr();
			editord.putString("sheng", sheng);
			editord.putString("city", newcity);
			editord.putString("lat", String.valueOf(lat));
			editord.putString("lng", String.valueOf(lng));
			editord.putString("address", address);
			editord.commit();
			// showToast("dist:" + location.getDistrict());
			text_cirty.setText(newcity);
			getDataForNet(newcity);
			mLocationClient.stop();
		}
	}

	public void onResume() {
		super.onResume();
		if (xzlcs == 0) {
			// initLocation();
		}
		if (newcity != null) {
			getDataForNet(newcity);
		}
	}

	public void onPause() {
		super.onPause();
	}

	private void initHuanXin() {
		inviteMessgeDao = new InviteMessgeDao(this);
		userDao = new UserDao(this);

		// setContactListener监听联系人的变化等
		EMContactManager.getInstance().setContactListener(
				new MyContactListener());
		// 注册一个监听连接状态的listener

		connectionListener = new MyConnectionListener();
		EMChatManager.getInstance().addConnectionListener(connectionListener);
		// groupChangeListener = new MyGroupChangeListener();
		// // 注册群聊相关的listener
		// EMGroupManager.getInstance().addGroupChangeListener(groupChangeListener);

		// 内部测试方法，请忽略
		registerInternalDebugReceiver();

	}

	/***
	 * 好友变化listener
	 * 
	 */
	public class MyContactListener implements EMContactListener {

		@Override
		public void onContactAdded(List<String> usernameList) {
			// 保存增加的联系人
			Map<String, User> localUsers = ((DemoHXSDKHelper) HXSDKHelper
					.getInstance()).getContactList();
			Map<String, User> toAddUsers = new HashMap<String, User>();
			for (String username : usernameList) {
				User user = setUserHead(username);
				// 添加好友时可能会回调added方法两次
				if (!localUsers.containsKey(username)) {
					userDao.saveContact(user);
				}
				toAddUsers.put(username, user);
			}
			localUsers.putAll(toAddUsers);
		}

		@Override
		public void onContactDeleted(final List<String> usernameList) {
			// 被删除
			Map<String, User> localUsers = ((DemoHXSDKHelper) HXSDKHelper
					.getInstance()).getContactList();
			for (String username : usernameList) {
				localUsers.remove(username);
				userDao.deleteContact(username);
				inviteMessgeDao.deleteMessage(username);
			}
			runOnUiThread(new Runnable() {
				public void run() {
					// 如果正在与此用户的聊天页面
					String st10 = getResources().getString(
							R.string.have_you_removed);
					if (ChatActivity.activityInstance != null
							&& usernameList
									.contains(ChatActivity.activityInstance
											.getToChatUsername())) {
						Toast.makeText(
								MainActivity.this,
								ChatActivity.activityInstance
										.getToChatUsername() + st10, 1).show();
						ChatActivity.activityInstance.finish();
					}
				}
			});

		}

		@Override
		public void onContactInvited(String username, String reason) {

			// 接到邀请的消息，如果不处理(同意或拒绝)，掉线后，服务器会自动再发过来，所以客户端不需要重复提醒
			List<InviteMessage> msgs = inviteMessgeDao.getMessagesList();

			for (InviteMessage inviteMessage : msgs) {
				if (inviteMessage.getGroupId() == null
						&& inviteMessage.getFrom().equals(username)) {
					inviteMessgeDao.deleteMessage(username);
				}
			}
			// 自己封装的javabean
			InviteMessage msg = new InviteMessage();
			msg.setFrom(username);
			msg.setTime(System.currentTimeMillis());
			msg.setReason(reason);
			Log.d(TAG, username + "请求加你为好友,reason: " + reason);
			// 设置相应status
			msg.setStatus(InviteMesageStatus.BEINVITEED);
			// notifyNewIviteMessage(msg);

		}

		@Override
		public void onContactAgreed(String username) {
			List<InviteMessage> msgs = inviteMessgeDao.getMessagesList();
			for (InviteMessage inviteMessage : msgs) {
				if (inviteMessage.getFrom().equals(username)) {
					return;
				}
			}
			// 自己封装的javabean
			InviteMessage msg = new InviteMessage();
			msg.setFrom(username);
			msg.setTime(System.currentTimeMillis());
			Log.d(TAG, username + "同意了你的好友请求");
			msg.setStatus(InviteMesageStatus.BEAGREED);
			// notifyNewIviteMessage(msg);

		}

		@Override
		public void onContactRefused(String username) {

			// 参考同意，被邀请实现此功能,demo未实现
			Log.d(username, username + "拒绝了你的好友请求");
		}

	}

	/**
	 * set head
	 * 
	 * @param username
	 * @return
	 */
	User setUserHead(String username) {
		User user = new User();
		user.setUsername(username);
		String headerName = null;
		if (!TextUtils.isEmpty(user.getNick())) {
			headerName = user.getNick();
		} else {
			headerName = user.getUsername();
		}
		if (username.equals(Constant.NEW_FRIENDS_USERNAME)) {
			user.setHeader("");
		} else if (Character.isDigit(headerName.charAt(0))) {
			user.setHeader("#");
		} else {
			user.setHeader(HanziToPinyin.getInstance()
					.get(headerName.substring(0, 1)).get(0).target.substring(0,
					1).toUpperCase());
			char header = user.getHeader().toLowerCase().charAt(0);
			if (header < 'a' || header > 'z') {
				user.setHeader("#");
			}
		}
		return user;
	}

	/**
	 * 内部测试代码，开发者请忽略
	 */
	private void registerInternalDebugReceiver() {
		internalDebugReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				DemoHXSDKHelper.getInstance().logout(true, new EMCallBack() {

					@Override
					public void onSuccess() {
						runOnUiThread(new Runnable() {
							public void run() {
								// 重新显示登陆页面
								finish();
								startActivity(new Intent(MainActivity.this,
										LoginActivity.class));

							}
						});
					}

					@Override
					public void onProgress(int progress, String status) {
					}

					@Override
					public void onError(int code, String message) {
					}
				});
			}
		};
		IntentFilter filter = new IntentFilter(getPackageName()
				+ ".em_internal_debug");
		registerReceiver(internalDebugReceiver, filter);
	}

	/**
	 * 连接监听listener
	 * 
	 */
	public class MyConnectionListener implements EMConnectionListener {

		@Override
		public void onConnected() {
			boolean groupSynced = HXSDKHelper.getInstance()
					.isGroupsSyncedWithServer();
			boolean contactSynced = HXSDKHelper.getInstance()
					.isContactsSyncedWithServer();

			// in case group and contact were already synced, we supposed to
			// notify sdk we are ready to receive the events
			if (groupSynced && contactSynced) {
				new Thread() {
					@Override
					public void run() {
						HXSDKHelper.getInstance().notifyForRecevingEvents();
					}
				}.start();
			} else {
				if (!groupSynced) {
					asyncFetchGroupsFromServer();
				}
			}
		}

		@Override
		public void onDisconnected(final int error) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (error == EMError.USER_REMOVED) {
						// 显示帐号已经被移除
						showAccountRemovedDialog();
					} else if (error == EMError.CONNECTION_CONFLICT) {
						// 显示帐号在其他设备登陆dialog
						showConflictDialog();
					} else {

					}
				}

			});
		}
	}

	/**
	 * 显示帐号在别处登录dialog
	 */
	private void showConflictDialog() {
		exit();
	}

	/**
	 * 帐号被移除的dialog
	 */
	private void showAccountRemovedDialog() {
		exit();
	}

	private void exit() {
		editorc.putString("pwd", "");
		editorc.commit();
		JPushInterface.setAliasAndTags(getApplicationContext(),
				"djkasbdjkasbdjk", null, mAliasCallback);
		loginout();
		LanActivity.tc = 1;
		Intent intent = new Intent();
		intent.setAction("ExitApp");
		this.sendBroadcast(intent);
		super.finish();
		startActivity(new Intent(MainActivity.this, LanActivity.class));
	}

	private void loginout() {
		DemoHXSDKHelper.getInstance().logout(true, new EMCallBack() {

			@Override
			public void onError(int arg0, String arg1) {
			}

			@Override
			public void onProgress(int arg0, String arg1) {
			}

			@Override
			public void onSuccess() {
			}
		});
	}

	static void asyncFetchGroupsFromServer() {
		HXSDKHelper.getInstance().asyncFetchGroupsFromServer(new EMCallBack() {
			@Override
			public void onSuccess() {
				HXSDKHelper.getInstance().noitifyGroupSyncListeners(true);
				if (HXSDKHelper.getInstance().isContactsSyncedWithServer()) {
					HXSDKHelper.getInstance().notifyForRecevingEvents();
				}
			}

			@Override
			public void onError(int code, String message) {
				HXSDKHelper.getInstance().noitifyGroupSyncListeners(false);
			}

			@Override
			public void onProgress(int progress, String status) {
			}
		});
	}

	private void gengxing() {
		application
				.doGet("http://api.fir.im/apps/latest/com.femto.shipper?api_token=358e7b651dc999c3e696c202a9595a6f&type=android",
						new RequestCallBack<String>() {
							@Override
							public void onSuccess(ResponseInfo<String> arg0) {
								GengXing qianbaoBean = GsonUtils.json2Bean(
										arg0.result, GengXing.class);
								if (!qianbaoBean.name.equals("")) {
									String gx_rizi = qianbaoBean.changelog;
									String gx_ver = qianbaoBean.versionShort;
									String gx_version = qianbaoBean.version;
									String apkUrl = qianbaoBean.install_url;
									String vername = "_V" + qianbaoBean.version;
									PackageManager manager = getPackageManager();
									PackageInfo info;
									try {
										info = manager.getPackageInfo(
												getPackageName(), 0);
										old_version = info.versionCode;
									} catch (NameNotFoundException e) {
										e.printStackTrace();
									}
									// //判断版本号是不是最新的
									if (Double.valueOf(gx_version) > old_version) {
										Utils_GX.dialog_gengxing(gx_rizi,
												gx_ver, gx_version, apkUrl,
												vername,
												MainActivity.mainactivity);
									}
								}
							}

							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								showToast("网络异常.....");
							}
						});
	}
}