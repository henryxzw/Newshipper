package com.femto.shipper.activitya;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.umeng.analytics.MobclickAgent;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Registerb extends BaseActivity implements OnClickListener {
	private TextView rbtktv;
	private EditText etrbdlmm, etrbqrmm, etrbdlch;
	private Button btnrb;
	private CheckBox rbcba;
	private Bundle bundlea;
	private String phone, telmanagerid, androidid, serialnumber, wybs,
			etrbdlmma, etrbqrmma, etrbnc, jiaMi, url;
	private TelephonyManager telmanager;
	private StatusBean statusbean;
	private SharedPreferences sharedpreferences;
	private Editor editor = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registerb);
		rbtktv = (TextView) findViewById(R.id.rbtktv);
		etrbdlmm = (EditText) findViewById(R.id.etrbdlmm);
		etrbqrmm = (EditText) findViewById(R.id.etrbqrmm);
		etrbdlch = (EditText) findViewById(R.id.etrbdlch);
		rbcba = (CheckBox) findViewById(R.id.rbcba);
		btnrb = (Button) findViewById(R.id.btnrb);
		findViewById(R.id.raimbtn).setOnClickListener(this);
		bundlea = new Bundle();
		bundlea = getIntent().getExtras();
		phone = bundlea.getString("phone");
		rbcba.setChecked(false);
		btnrb.setEnabled(false);
		btnrb.setBackgroundResource(R.drawable.btn_mycolor);
		btnrb.setOnClickListener(this);
		rbtktv.setOnClickListener(this);
		rbcba.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.raimbtn:
			finish();
			break;
		case R.id.btnrb:
			yzmm();
			break;
		case R.id.rbcba:
			if (rbcba.isChecked()) {
				btnrb.setBackgroundResource(R.drawable.button_land);
				btnrb.setEnabled(true);
			} else {
				btnrb.setBackgroundResource(R.drawable.btn_mycolor);
				btnrb.setEnabled(false);
			}
			break;
		case R.id.rbtktv:
			startActivity(new Intent(Registerb.this, Intentfwxy.class));
			break;
		}
	}

	@Override
	protected void onDestroy() {
		if (editor != null) {
			editor.clear();
		}
		super.onDestroy();
	}

	@SuppressWarnings("deprecation")
	private void yzmm() {
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
		// wybs = "352246068482366360d43b04718eae4HC47RWW04740";
		etrbdlmma = etrbdlmm.getText().toString().trim();
		etrbqrmma = etrbqrmm.getText().toString().trim();
		etrbnc = etrbdlch.getText().toString().trim();

		if (etrbnc.equals("")) {
			showToast(getResources().getString(R.string.qsrmm));
			return;
		}
		if (ToolUtils.panduanteshuzhifu(etrbqrmma) == false) {
			showToast(getResources().getString(R.string.srdmmbnbkbdfhdtszf));
			return;
		}

		if (etrbdlmm.getText().length() < 6) {
			showToast(getResources().getString(R.string.mmbnsylw));
		} else {
			if (!etrbdlmma.equals(etrbqrmma)) {
				etrbqrmm.setText("");
				etrbdlmm.setText("");
				showToast(getResources().getString(R.string.lcsrdmmbxt));
			} else {
				if (ToolUtils.panduanteshuzhifu(etrbdlmma) == false) {
					showToast(getResources().getString(R.string.lcsrdmmbxt));
					return;
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("key", "user_register");
				map.put("username", phone);
				map.put("pwd", etrbqrmma);
				map.put("code", wybs);
				map.put("user_group", "1");
				map.put("referee", "");
				map.put("nick_name", etrbnc);
				jiaMi = ToolUtils.JiaMi(map);
				url = Net.REGIST + jiaMi;
				showProgressDialog(getResources().getString(R.string.jzz));
				application.doget(url, new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						statusbean = GsonUtils.json2Bean(arg0.result,
								StatusBean.class);
						if (statusbean.status.equals("0")) {
							dismissProgressDialog();
							showToast(getResources().getString(R.string.zccg));
							sharedpreferences = getSharedPreferences(
									getResources().getString(R.string.sqlname),
									Activity.MODE_PRIVATE);
							editor = sharedpreferences.edit();
							editor.putString(
									getResources().getString(R.string.phone),
									phone);
							editor.putString(
									getResources().getString(R.string.pwd),
									etrbqrmma);
							editor.commit();
							JPushInterface.setAliasAndTags(
									getApplicationContext(), phone, null,
									mAliasCallback);
							lanHuanXin(phone, etrbqrmma);
							startActivity(new Intent(Registerb.this,
									MainActivity.class));
							LanActivity.lanactivity.finish();
							Registera.registera.finish();
							Registerb.this.finish();
						} else {
							dismissProgressDialog();
							showToast("注册失败！");
						}
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						dismissProgressDialog();
						showToast(getResources().getString(R.string.wlyc));
					}
				});
			}
		}
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
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
		UserDao dao = new UserDao(Registerb.this);
		List<User> users = new ArrayList<User>(userlist.values());
		dao.saveContactList(users);
	}

	@Override
	protected void onResume() {
		super.onResume();
		JPushInterface.onResume(mContext);
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		JPushInterface.onPause(mContext);
		MobclickAgent.onPause(this);
	}
}