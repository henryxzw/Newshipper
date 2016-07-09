package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.StatusBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class ChangePasswordActivity extends BaseActivity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_changepassword);
		
		findViewById(R.id.btn_cgpsw).setOnClickListener(this);
		findViewById(R.id.left).setOnClickListener(this);
		TextView text_phone = (TextView) findViewById(R.id.text_phone);
		text_phone.setText(application.userPhone);

		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_cgpsw:
			getCodeForNet();

			break;
		case R.id.left:
			finish();
			break;
		}
	}

	private void getCodeForNet() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "send_sms");
		map.put("mobile", application.userPhone);
		map.put("code_type", "pwd");

		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.GETCODE + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("获取验证码中...");
		application.doGet(url, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				LogUtils.i(arg0.result);
				dismissProgressDialog();
				StatusBean statusBean = GsonUtils.json2Bean(arg0.result, StatusBean.class);
				if(statusBean.status.equals("0")){
					showToast("验证码已发送到手机");
					startActivity(new Intent(mContext,ChangePswActivity.class));
					finish();
				}else{
					showToast(statusBean.msg);
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				LogUtils.i("" + arg1);
				dismissProgressDialog();
			}
		});
	}
}
