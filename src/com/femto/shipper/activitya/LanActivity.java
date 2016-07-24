package com.femto.shipper.activitya;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.femto.shipper.bean.GengXing;
import com.femto.shipper.bean.StatusBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.femto.shipper.utils.Utils_GX;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.analytics.MobclickAgent;

@SuppressLint("NewApi")
public class LanActivity extends BaseActivity implements OnClickListener {

	private Button btn_denglu;
	private int old_version;
	private TextView text_zuce, text_dianji;
	private EditText editext_phone, editext_password;
	private String phone, pwd, telmanagerid, androidid, serialnumber, wybs,
			password, phonea, jiamia, urla;
	private TelephonyManager telmanager;
	public static Activity lanactivity;
	private SharedPreferences sharedpreferencesa, sharedpreferencesb;
	private Editor editor = null;
	private HashMap<String, String> map;
	private StatusBean statusbean;
	private Intent intenta;
	public static int tc = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_lan);
		lanactivity = this;
		btn_denglu = (Button) findViewById(R.id.btn_denglu);
		text_dianji = (TextView) findViewById(R.id.text_dianji);
		text_zuce = (TextView) findViewById(R.id.text_zuce);
		editext_phone = (EditText) findViewById(R.id.editext_phone);
		editext_password = (EditText) findViewById(R.id.editext_password);
		text_dianji.setOnClickListener(this);
		text_zuce.setOnClickListener(this);
		btn_denglu.setOnClickListener(this);
		gengxing();
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
												LanActivity.lanactivity);
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

	private void showdialog() {
		AlertDialog.Builder builder = new Builder(LanActivity.this);
		builder.setMessage(getResources().getString(R.string.connect_conflict));
		builder.setTitle(getResources().getString(R.string.Logoff_notification));
		builder.setPositiveButton(getResources().getString(R.string.ok),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						tc = 0;
						arg0.dismiss();
					}
				});
		builder.create().show();
		builder.setCancelable(false);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_denglu:
			Lan();
			break;
		case R.id.text_dianji:
			phone = editext_phone.getText().toString().trim();
			if (editext_phone.getText().length() == 11) {
				sharedpreferencesb = getSharedPreferences(getResources()
						.getString(R.string.sqlname), Activity.MODE_PRIVATE);
				editor = sharedpreferencesb.edit();
				editor.putString(getResources().getString(R.string.phone),
						phone);
				editor.commit();
			}
			startActivity(new Intent(mContext, ForgetPasswordActivity.class));
			break;
		case R.id.text_zuce:
			startActivity(new Intent(mContext, Registera.class));
			break;
		}
	}

	@SuppressWarnings("deprecation")
	private void Lan() {
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
		// wybs = "86766002130107577be2d176fd4eae5c2ad38";
		// wybs = "352246068482366360d43b04718eae4HC47RWW04740";
		// wybs = "3522460684823663d061f882ef775c6HC47RWW04740";
		phone = editext_phone.getText().toString().trim();
		pwd = editext_password.getText().toString().trim();
		if (editext_phone.getText().length() != 11
				|| editext_phone.getText().equals("")) {
			showToast(getResources().getString(R.string.qsrhfdsjhm));
		} else {
			if (TextUtils.isEmpty(pwd)) {
				showToast(getResources().getString(R.string.qsrdlmm));
			} else {
				if (ToolUtils.panduanteshuzhifu(pwd) == false) {
					showToast(getResources().getString(
							R.string.srdmmbnbkbdfhdtszf));
					return;
				}
				map = new HashMap<String, String>();
				map.put("key", "user_login");
				map.put("username", phone);
				map.put("pwd", pwd);
				map.put("code", wybs);
				jiamia = ToolUtils.JiaMi(map);
				urla = Net.DENGLU + jiamia;
				showProgressDialog(getResources().getString(R.string.jzz));
				application.doget(urla, new RequestCallBack<String>() {
					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						dismissProgressDialog();
						statusbean = GsonUtils.json2Bean(arg0.result,
								StatusBean.class);
						if (statusbean.status.equals("0")) {
							sharedpreferencesb = getSharedPreferences(
									getResources().getString(R.string.sqlname),
									Activity.MODE_PRIVATE);
							editor = sharedpreferencesb.edit();
							editor.putString(
									getResources().getString(R.string.phone),
									phone);
							editor.putString(
									getResources().getString(R.string.pwd), pwd);
							editor.commit();
							JPushInterface.setAliasAndTags(
									getApplicationContext(), phone, null,
									mAliasCallback);
							lanHuanXin(phone, pwd);
							startActivity(new Intent(LanActivity.this,
									MainActivity.class));
							// try {
							// JPushInterface
							// .resumePush(getApplicationContext());
							// JPushInterface.setAliasAndTags(
							// getApplicationContext(), phone, null,
							// mAliasCallback);
							// } catch (Exception e) {
							// }
							finish();
						} else if (statusbean.status.equals("3")) {
							intenta = new Intent(LanActivity.this,
									YiDiDengluActivity.class);
							intenta.putExtra("phone", phone);
							startActivity(intenta);
						} else if (statusbean.status.equals("2")
								|| statusbean.status.equals("1")
								|| statusbean.status.equals("4")
								|| statusbean.status.equals("5")) {
							showToast(statusbean.msg);
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
										R.string.Login_failed));
							}
						});
					}
				});
	}

	@Override
	protected void onDestroy() {
		tc = 0;
		if (editor != null) {
			editor.clear();
		}
		super.onDestroy();
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
		UserDao dao = new UserDao(LanActivity.this);
		List<User> users = new ArrayList<User>(userlist.values());
		dao.saveContactList(users);
	}

	@Override
	protected void onResume() {
		super.onResume();
		sharedpreferencesa = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedpreferencesa.getString(
				getResources().getString(R.string.phone), "");
		password = sharedpreferencesa.getString(
				getResources().getString(R.string.pwd), "");
		if (!phonea.equals("") || phonea != null) {
			editext_phone.setText(phonea);
		}
		if (!password.equals("") || password != null) {
			editext_password.setText(password);
		}
		if (tc == 1) {
			showdialog();
		}
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