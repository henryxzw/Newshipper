package com.femto.shipper.activitya;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.easemob.EMCallBack;
import com.easemob.applib.controller.HXSDKHelper;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.easemob.chatuidemo.db.UserDao;
import com.easemob.chatuidemo.domain.User;
import com.femto.shipper.R;
import com.femto.shipper.activity.MainActivity;
import com.femto.shipper.application.Constant;
import com.femto.shipper.application.DemoApplication;
import com.femto.shipper.application.DemoHXSDKHelper;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.StatusBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.Window;
import android.view.WindowManager;

@SuppressLint("NewApi")
public class Welcomea extends BaseActivity {
	private String telmanagerid, androidid, serialnumber, wybs, jiami, url,
			phonea, passworda;
	private TelephonyManager telmanager;
	private SharedPreferences sharedPreferences;
	private HashMap<String, String> map;
	private Intent inta;
	private StatusBean statusbean;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.welcomea);
		loginout();
		freelogin();
		AnalyticsConfig.enableEncrypt(true);
		MobclickAgent.setDebugMode(true);
	}

	@SuppressWarnings("deprecation")
	public void freelogin() {
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		try {
			telmanager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
			telmanagerid = telmanager.getDeviceId();
		} catch (Exception e) {
			telmanagerid = "0";
		}
		try {
			androidid = Settings.System.getString(getContentResolver(),
					Settings.System.ANDROID_ID);
		} catch (Exception e) {
			androidid = "0";
		}
		try {
			serialnumber = android.os.Build.SERIAL;
		} catch (Exception e) {
			serialnumber = "0";
		}
		if (telmanagerid == null || telmanagerid.equals("")) {
			telmanagerid = "0";
		}
		if (androidid == null || androidid.equals("")) {
			androidid = "0";
		}
		if (serialnumber == null || serialnumber.equals("")) {
			serialnumber = "0";
		}
		wybs = telmanagerid + androidid + serialnumber;
		// wybs="86766002130107577be2d176fd4eae5c2ad38";
		// wybs = "352246068482366360d43b04718eae4HC47RWW04740";
		// wybs = "3522460684823663d061f882ef775c6HC47RWW04740";
		map = new HashMap<String, String>();
		map.put("key", "user_login");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("code", wybs);
		jiami = ToolUtils.JiaMi(map);
		url = Net.DENGLU + jiami;
		if (phonea == null || passworda == null || phonea.equals("")
				|| passworda.equals("") || phonea.equals("null")) {
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					inta = new Intent(Welcomea.this, LanActivity.class);
					startActivity(inta);
					finish();
				}
			}, 1000);
		} else {
			showProgressDialog(getResources().getString(R.string.jzz));
			application.doget(url, new RequestCallBack<String>() {
				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					dismissProgressDialog();
					statusbean = GsonUtils.json2Bean(arg0.result,
							StatusBean.class);
					if (statusbean.status.equals("0")) {

						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								JPushInterface.setAliasAndTags(
										getApplicationContext(), phonea, null,
										mAliasCallback);
								lanHuanXin(phonea, passworda);
								inta = new Intent(Welcomea.this,
										MainActivity.class);
								startActivity(inta);
								finish();
							}
						}, 1000);
					} else {
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								inta = new Intent(Welcomea.this,
										LanActivity.class);
								startActivity(inta);
								finish();
							}
						}, 1000);
					}
				}

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					dismissProgressDialog();
					showToast(getResources().getString(R.string.wlyc));
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							inta = new Intent(Welcomea.this, LanActivity.class);
							startActivity(inta);
							finish();
						}
					}, 1000);
				}
			});
		}
	}

	private void loginout() {
		JPushInterface.setAliasAndTags(getApplicationContext(),
				"djkasbdjkasbdjk", null, mAliasCallback);
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

	private void lanHuanXin(final String currentUsername,
			final String currentPassword) {
		EMChatManager.getInstance().login(currentUsername, currentPassword,
				new EMCallBack() {
					@Override
					public void onSuccess() {
						DemoApplication.getInstance().setUserName(
								currentUsername);
						DemoApplication.getInstance().setPassword(
								currentPassword);
						try {
							EMGroupManager.getInstance().loadAllGroups();
							EMChatManager.getInstance().loadAllConversations();
							initializeContacts();
						} catch (Exception e) {
							e.printStackTrace();
							runOnUiThread(new Runnable() {
								public void run() {
									DemoHXSDKHelper.getInstance().logout(true,
											null);
									showToast(getResources().getString(
											R.string.login_failure_failed));
								}
							});
							return;
						}
					}

					@Override
					public void onProgress(int progress, String status) {
					}

					@Override
					public void onError(final int code, final String message) {
						runOnUiThread(new Runnable() {
							public void run() {
								showToast(getResources().getString(
										R.string.Login_failed)
										+ message);
							}
						});
					}
				});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initializeContacts() {
		HashMap userlist = new HashMap<String, User>();
		User newFriends = new User();
		newFriends.setUsername(Constant.NEW_FRIENDS_USERNAME);
		String strChat = getResources().getString(
				R.string.Application_and_notify);
		newFriends.setNick(strChat);
		userlist.put(Constant.NEW_FRIENDS_USERNAME, newFriends);
		User groupUser = new User();
		String strGroup = getResources().getString(R.string.group_chat);
		groupUser.setUsername(Constant.GROUP_USERNAME);
		groupUser.setNick(strGroup);
		groupUser.setHeader("");
		userlist.put(Constant.GROUP_USERNAME, groupUser);
		User robotUser = new User();
		String strRobot = getResources().getString(R.string.robot_chat);
		robotUser.setUsername(Constant.CHAT_ROBOT);
		robotUser.setNick(strRobot);
		robotUser.setHeader("");
		userlist.put(Constant.CHAT_ROBOT, robotUser);
		((DemoHXSDKHelper) HXSDKHelper.getInstance()).setContactList(userlist);
		UserDao dao = new UserDao(Welcomea.this);
		List<User> users = new ArrayList<User>(userlist.values());
		dao.saveContactList(users);
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
		JPushInterface.onResume(mContext);
	}

	@Override
	protected void onStart() {
		super.onStart();

	}

	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(mContext);
		MobclickAgent.onPause(this);
	}
}