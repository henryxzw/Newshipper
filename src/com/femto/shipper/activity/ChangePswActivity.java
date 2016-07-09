package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

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

public class ChangePswActivity extends BaseActivity implements OnClickListener {
	
	private EditText editext_code;
	private EditText editext_password;
	private String code;
	private String password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_psw);
		findViewById(R.id.left).setOnClickListener(this);
		findViewById(R.id.btn_tijiao).setOnClickListener(this);
		
		editext_code = (EditText) findViewById(R.id.editext_code);
		editext_password = (EditText) findViewById(R.id.editext_password);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_tijiao://提交
			ChangPassword();
			break;
		case R.id.left:
			finish();
			break;
		}
	}

	//更改密码
	private void ChangPassword() {
		
		code = editext_code.getText().toString();
		password = editext_password.getText().toString();
		
		if(TextUtils.isEmpty(code)){
			showToast("请输入验证码");
			return;
		}
		
		if(TextUtils.isEmpty(password)){
			showToast("请输入密码");
			return;
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "verify_sms_code");
		map.put("code_type", "pwd");
		map.put("mobile", application.userPhone);
		map.put("code", code);
		
		String jiaMi = ToolUtils.JiaMi(map);
		String url =Net.YANZHENGCODE+ jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("修改中...");
		application.doGet(url, new RequestCallBack<String>()
		{
			@Override
			public void onSuccess(ResponseInfo<String> arg0)
			{
				LogUtils.i("" + arg0.result);
				StatusBean statusBean = GsonUtils.json2Bean(arg0.result, StatusBean.class);
				if(statusBean.status.equals("0")){
					PostData();
				}else{
					showToast(statusBean.msg);
					dismissProgressDialog();
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1)
			{
				LogUtils.i("" + arg1);
				dismissProgressDialog();
			}
		});
	}
	
	//最后一步修改
	private void PostData()
	{
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "forget_pwd");
		map.put("username",application.userPhone);
		map.put("new_pwd",password);// 新密码
		
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.FORGETPASSWORD + jiaMi;
		LogUtils.i("请求的url:" + url);
		application.doGet(url, new RequestCallBack<String>()
		{
			@Override
			public void onSuccess(ResponseInfo<String> arg0)
			{
				LogUtils.i("" + arg0.result);
				dismissProgressDialog();
				StatusBean statusBean = GsonUtils.json2Bean(arg0.result, StatusBean.class);
				if(statusBean.status.equals("0")){
					application.password = password;//修改成功后 .
					showToast("修改成功");
					finish();
				}else{
					showToast(statusBean.msg);
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1)
			{
				LogUtils.i("" + arg1);
				dismissProgressDialog();
			}
		});
	}
}
