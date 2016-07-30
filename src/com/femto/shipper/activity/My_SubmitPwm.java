package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.femto.shipper.R;
import com.femto.shipper.application.DemoApplication;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.UpImageBean;
import com.femto.shipper.utils.Constants;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.SharedPreferencesUtils;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class My_SubmitPwm extends BaseActivity implements OnClickListener {
	private Button queren_submit;
	private EditText now_pwm, new_pwm;
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;
	private SharedPreferences sharedpreferencesb;
	private Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_submit_pwmactivity);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		info();
	}

	private void info() {
		now_pwm = (EditText) findViewById(R.id.now_pwm);
		new_pwm = (EditText) findViewById(R.id.new_pwm);
		queren_submit = (Button) findViewById(R.id.queren_submit);
		queren_submit.setOnClickListener(this);
		findViewById(R.id.left).setOnClickListener(this);

	}

	// 判断密码格式是否正确
	public boolean ispasswordFormat(String password) {
		String str = "^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~\\_]{6,15}$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(password);
		return m.matches();
	}

	@SuppressLint("InlinedApi")
	public void getupdatepwm(final String now_mm, final String new_mm) {
		LogUtils.i("---->" + now_mm + "---->>" + new_mm);
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "edit_pwd");
		map.put("username", phonea);
		map.put("old_pwd", now_mm);
		map.put("new_pwd", new_mm);

		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.SUBMIT_PWD + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");
		application.doGet(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				LogUtils.i("" + arg1);
				dismissProgressDialog();

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				LogUtils.i("getTuXiang" + arg0.result);
				dismissProgressDialog();
				UpImageBean statusBean = GsonUtils.json2Bean(arg0.result,
						UpImageBean.class);
				if (statusBean.status.equals("0")) {
					DemoApplication.getInstance().setPassword(new_mm);
					sharedpreferencesb = getSharedPreferences(getResources()
							.getString(R.string.sqlname),
							Activity.MODE_MULTI_PROCESS);
					editor = sharedpreferencesb.edit();
					editor.putString(getResources().getString(R.string.pwd),
							new_mm);
					editor.commit();
					showToast("修改成功");
					finish();
				} else {
					showToast(statusBean.msg.split("\\：")[1]);
					showToast("修改失败");
				}

			}
		});
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (isFastDoubleClick()) {
			return;
		}
		switch (arg0.getId()) {
		case R.id.left:
			finish();
			break;
		case R.id.queren_submit:

			String now_mm = now_pwm.getText().toString();
			String new_mm = new_pwm.getText().toString();
			if (now_mm.equals("")) {
				showToast("旧密码不能为空");
			} else {

				if (new_mm.equals("")) {
					showToast("新密码不能为空");
				} else {

					if (!passworda.equals(now_mm)) {
						showToast("旧密码输入错误");
					} else {
						getupdatepwm(now_mm, new_mm);
					}
				}

			}
			break;

		default:
			break;
		}
	}

	public void onResume() {
		super.onResume();
	}

	public void onPause() {
		super.onPause();
	}
}
