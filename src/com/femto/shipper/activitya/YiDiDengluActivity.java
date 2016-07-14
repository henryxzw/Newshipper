package com.femto.shipper.activitya;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class YiDiDengluActivity extends BaseActivity implements OnClickListener {
	private Button yidibtnyzm, yidibtn;
	private String phone, mycode, telmanagerid, serialnumber, androidid, wybs,
			myphone, mypwd, jiami, url, jiamib, urlb;
	private TelephonyManager telmanager;
	private EditText yidipwd, yidicode, yidiphone;
	private ImageButton yidifhbtn;
	private Map<String, String> map, mapb;
	private StatusBean statusbean, statusbeanb;
	private SharedPreferences sharedpreferences;
	private Editor editor = null;
	private Intent inta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_forget_passwod);
		yidipwd = (EditText) findViewById(R.id.yidipwd);
		yidicode = (EditText) findViewById(R.id.yidicode);
		yidiphone = (EditText) findViewById(R.id.yidiphone);
		yidibtnyzm = (Button) findViewById(R.id.yidibtnyzm);
		yidibtn = (Button) findViewById(R.id.yidibtn);
		yidifhbtn = (ImageButton) findViewById(R.id.yidifhbtn);
		yidiphone.setText(getIntent().getStringExtra("phone"));
		showDialogFirst();
		yidibtnyzm.setOnClickListener(this);
		yidibtn.setOnClickListener(this);
		yidifhbtn.setOnClickListener(this);
	}

	private void showDialogFirst() {
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setMessage(getResources().getString(R.string.gsjcyfcysbdlqcxyz));
		builder.setPositiveButton(getResources().getString(R.string.qd),
				new AlertDialog.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.show();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.yidibtn:
			JiaoYanMa();
			break;
		case R.id.yidibtnyzm:
			GetCode();
			countDownTimer.start();
			break;
		case R.id.yidifhbtn:
			finish();
			break;
		}
	}

	@SuppressWarnings("deprecation")
	private void JiaoYanMa() {
		myphone = yidiphone.getText().toString();
		mycode = yidicode.getText().toString();
		mypwd = yidipwd.getText().toString().trim();
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
		if (ToolUtils.panduanteshuzhifu(mypwd) == false) {
			showToast(getResources().getString(R.string.srdmmbnbkbdfhdtszf));
			return;
		}
		if (!myphone.equals("") || !mypwd.equals("")) {
			if (!mycode.equals("")) {
				map = new HashMap<String, String>();
				map.put("key", "validate_code");
				map.put("username", myphone);
				map.put("pwd", mypwd);
				map.put("new_code", wybs);
				map.put("code_type", "error");
				map.put("code", mycode);
				jiami = ToolUtils.JiaMi(map);
				url = Net.YIDIDENGLU + jiami;
				showProgressDialog(getResources().getString(R.string.jzz));
				application.doget(url, new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						dismissProgressDialog();
						statusbean = GsonUtils.json2Bean(arg0.result,
								StatusBean.class);
						if (statusbean.status.equals("0")) {
							sharedpreferences = getSharedPreferences(
									getResources().getString(R.string.sqlname),
									Activity.MODE_PRIVATE);
							editor = sharedpreferences.edit();
							editor.putString(
									getResources().getString(R.string.phone),
									myphone);
							editor.putString(
									getResources().getString(R.string.pwd),
									mypwd);
							editor.commit();
							JPushInterface.setAliasAndTags(
									getApplicationContext(), myphone, null,
									mAliasCallback);
							lanHuanXin(myphone, mypwd);
							inta = new Intent(YiDiDengluActivity.this,
									MainActivity.class);
							startActivity(inta);
							LanActivity.lanactivity.finish();
							YiDiDengluActivity.this.finish();
						} else {
							showToast(statusbean.msg);
						}
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						dismissProgressDialog();
						showToast(getResources().getString(R.string.wlyc));

					}
				});
			} else {
				showToast(getResources().getString(R.string.qsryzm));
			}
		} else {
			showToast(getResources().getString(R.string.qsrmm));
		}

	}

	private void GetCode() {
		phone = yidiphone.getText().toString();
		if (TextUtils.isEmpty(phone)) {
			showToast(getResources().getString(R.string.qsrsjhm));
			return;
		} else {
			if (yidiphone.getText().length() != 11) {
				showToast(getResources().getString(R.string.qsrhfdsjhm));
				return;
			} else {
				mapb = new HashMap<String, String>();
				mapb.put("key", "send_sms");
				mapb.put("code_type", "error");
				mapb.put("mobile", phone);
				jiamib = ToolUtils.JiaMi(mapb);
				urlb = Net.GETCODE + jiamib;
				// countDownTimer.start();
				application.doGet(urlb, new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						statusbeanb = GsonUtils.json2Bean(arg0.result,
								StatusBean.class);
						if (statusbeanb.status.equals("0")) {
							showToast(getResources().getString(
									R.string.yzmyfsdsj));
						}
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
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
		UserDao dao = new UserDao(YiDiDengluActivity.this);
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

	@Override
	protected void onDestroy() {
		if (editor != null) {
			editor.clear();
		}
		super.onDestroy();
	}

	private CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
		@Override
		public void onTick(long millisUntilFinished) {
			int k = (int) (millisUntilFinished / 1000);
			yidibtnyzm.setText(k + "");
			yidibtnyzm.setEnabled(false);
			yidibtnyzm
					.setTextColor(getResources().getColor(R.color.text_gray2));
		}

		@Override
		public void onFinish() {
			yidibtnyzm.setText(getResources().getString(R.string.msd));
			yidibtnyzm.setTextColor(getResources().getColor(R.color.baise));
			yidibtnyzm.setEnabled(true);
		}
	};
}