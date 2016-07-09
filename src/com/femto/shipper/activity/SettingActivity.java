package com.femto.shipper.activity;

import java.util.Set;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import com.easemob.EMCallBack;
import com.femto.shipper.R;
import com.femto.shipper.activitya.LanActivity;
import com.femto.shipper.adapter.SysApplication;
import com.femto.shipper.application.DemoHXSDKHelper;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.GengXing;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Utils_GX;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;

public class SettingActivity extends BaseActivity implements OnClickListener {
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;
	private RelativeLayout RelativeLayoutA, RelativeLayoutB, RelativeLayoutC,
			RelativeLayoutD;
	private Button button_A;
	private Intent it;
	private Editor editor, editorc;
	private TextView Text_Version;
	private int old_version;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		// SysApplication.getInstance().addActivity(this);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		editorc = sharedPreferences.edit();
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		Text_Version = (TextView) findViewById(R.id.Text_Version);
		findViewById(R.id.RelativeLayoutA).setOnClickListener(this);
		findViewById(R.id.RelativeLayoutB).setOnClickListener(this);
		findViewById(R.id.RelativeLayoutC).setOnClickListener(this);
		findViewById(R.id.button_A).setOnClickListener(this);
		findViewById(R.id.left).setOnClickListener(this);
		Text_Version.setText("V" + getVersion());
	}

	public void checkVersion(View view) {
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
									// //判断版本号是不是最新的
									if (Double.valueOf(gx_version) > old_version) {
										Utils_GX.dialog_gengxing(gx_rizi,
												gx_ver, gx_version, apkUrl,
												vername, SettingActivity.this);
									} else {
										showToast("当前是最新版本");
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

	/**
	 * 获取版本号
	 * 
	 * @return 当前应用的版本号
	 */
	public String getVersion() {
		try {
			PackageManager manager = this.getPackageManager();
			PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
			String version = info.versionName;
			old_version = info.versionCode;
			LogUtils.i("------------------>>>>>>>" + version);
			return version;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "无法获取版本号";
	}

	@Override
	public void onClick(View v) {
		it = new Intent();
		switch (v.getId()) {
		case R.id.RelativeLayoutA:// 鐢ㄦ埛鍙嶉
			startActivity(YiJianFanKuiActivity.class);
			break;
		case R.id.RelativeLayoutB:// 鐢ㄦ埛鍗忚
			it.putExtra("type", "A");
			it.setClass(this, Web_INTENT.class);
			startActivity(it);
			break;
		case R.id.RelativeLayoutC:// 鍏充簬
			it.putExtra("type", "B");
			it.setClass(this, Web_INTENT.class);
			startActivity(it);
			break;
		case R.id.button_A:// 閫�鍑虹櫥褰�
			tuichu();
			break;
		case R.id.left:// 杩斿洖
			finish();
			break;
		}
	}

	private void tuichu() {
		Builder builder = new Builder(this);
		builder.setTitle("确认退出吗？");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				final ProgressDialog pd = new ProgressDialog(
						SettingActivity.this);
				String st = getResources().getString(R.string.Are_logged_out);
				pd.setMessage(st);
				pd.setCanceledOnTouchOutside(false);
				pd.show();
				// sharedPreferences = getSharedPreferences("spinnera",
				// Activity.MODE_PRIVATE);
				// editor = sharedPreferences.edit();
				// editor.putString("pwd", "");
				// editor.commit();
				// JPushInterface.setAliasAndTags(getApplicationContext(),
				// "djkasbdjkasbdjk", null, mAliasCallback);
				// DemoHXSDKHelper.getInstance().logout(true, new EMCallBack() {
				//
				// @Override
				// public void onError(int arg0, String arg1) {
				// }
				//
				// @Override
				// public void onProgress(int arg0, String arg1) {
				// }
				//
				// @Override
				// public void onSuccess() {
				// }
				// });
				pd.dismiss();
				// Intent it = new Intent(SettingActivity.this,
				// LanActivity.class);
				// startActivity(it);
				// MainActivity.mainactivity.finish();
				// MyInterspaceActivity.myinterspaceactivity.finish();
				// SettingActivity.this.finish();
				tcdl();
				dialog.dismiss();
			}
		});

		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builder.show();
	}

	private void tcdl() {
		editorc.putString("pwd", "");
		editorc.commit();
		JPushInterface.setAliasAndTags(getApplicationContext(),
				"djkasbdjkasbdjk", null, mAliasCallback);
		loginout();
		// LanActivity.tc = 1;
		Intent intent = new Intent();
		intent.setAction("ExitApp");
		this.sendBroadcast(intent);
		super.finish();
		// ActivityManager am = (ActivityManager)
		// getSystemService(Context.ACTIVITY_SERVICE);
		// am.restartPackage(getPackageName());
		// android.os.Process.killProcess(android.os.Process.myPid());
		// ActivityManager manager = (ActivityManager)
		// getSystemService(Context.ACTIVITY_SERVICE);
		// manager.killBackgroundProcesses("PushService");
		// manager.killBackgroundProcesses("DaemonService");
		// manager.killBackgroundProcesses("EMChatService");
		startActivity(new Intent(SettingActivity.this, LanActivity.class));

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

	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
