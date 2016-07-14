package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class ForgetPasswordActivity extends BaseActivity implements OnClickListener
{

	private Button text_daojishi;
	private EditText editext_xinmima;
	private String phone;
	private String code;
	private String password;
	private EditText editext_phone;
	private EditText editext_code;
	private ImageButton wjimbtn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_forget_password);
		wjimbtn = (ImageButton) findViewById(R.id.wjimbtn);
		text_daojishi = (Button) findViewById(R.id.text_daojishi);
		text_daojishi.setOnClickListener(this);
		editext_phone = (EditText) findViewById(R.id.editext_phone);
		editext_code = (EditText) findViewById(R.id.editext_code);
		editext_xinmima = (EditText) findViewById(R.id.editext_xinmima);
		findViewById(R.id.btn_denglu).setOnClickListener(this);
		wjimbtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.wjimbtn:
			finish();
			break;
		case R.id.text_daojishi:// 倒计时
			GetCode();
			break;
		case R.id.btn_denglu:
			ChangPassword();
			break;
		}
	}

	// 更改密码
	private void ChangPassword()
	{

		phone = editext_phone.getText().toString();
		code = editext_code.getText().toString();
		password = editext_xinmima.getText().toString();

		if (!ToolUtils.isMobileNO(phone))
		{
			showToast("请输入11位合法的手机号码");
			return;
		}

		if (TextUtils.isEmpty(code))
		{
			showToast("请输入验证码");
			return;
		}

		if (TextUtils.isEmpty(password))
		{
			showToast("请输入密码");
			return;
		}
		if (editext_xinmima.getText().length() < 6)
		{
			showToast("密码不能小于6位！");
		} else
		{
			Map<String, String> map = new HashMap<String, String>();
			map.put("key", "verify_sms_code");
			map.put("code_type", "pwd");
			map.put("mobile", phone);
			map.put("code", code);

			// 这个接口 没用到密码 在最后才用密码
			String jiaMi = ToolUtils.JiaMi(map);
			String url = Net.YANZHENGCODE + jiaMi;
			LogUtils.i("请求的url:" + url);
			showProgressDialog("修改中...");
			application.doGet(url, new RequestCallBack<String>()
			{
				@Override
				public void onSuccess(ResponseInfo<String> arg0)
				{
					LogUtils.i("" + arg0.result);
					StatusBean statusBean = GsonUtils.json2Bean(arg0.result, StatusBean.class);
					if (statusBean.status.equals("0"))
					{
						PostData();
					} else
					{
						showToast("验证码错误！");
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
	}

	// 最后一步修改
	private void PostData()
	{

		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "forget_pwd");
		map.put("username", phone);
		map.put("new_pwd", password);// 新密码

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
				if (statusBean.status.equals("0"))
				{
					showToast("修改成功");
					finish();
				} else
				{
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

	// 验证手机没有被使用则请求获取验证码
	private void GetCode()
	{
		String phone = editext_phone.getText().toString();
		if (TextUtils.isEmpty(phone))
		{
			showToast("请输入手机号码");
			return;
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "send_sms");
		map.put("code_type", "pwd");
		map.put("mobile", phone);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.GETCODE + jiaMi;
		LogUtils.i("请求的url:" + url);

		countDownTimer.start();// 开启倒计时
		application.doGet(url, new RequestCallBack<String>()
		{
			@Override
			public void onSuccess(ResponseInfo<String> arg0)
			{
				LogUtils.i("获取手机验证码" + arg0.result);
				StatusBean statusBean = GsonUtils.json2Bean(arg0.result, StatusBean.class);
				if (statusBean.status.equals("0"))
				{
					showToast("验证码已发送到手机中...");
				} else
				{
					showToast(statusBean.msg);
				}
				dismissProgressDialog();
			}

			@Override
			public void onFailure(HttpException arg0, String arg1)
			{
				LogUtils.i("" + arg1);
				dismissProgressDialog();
			}
		});
	}

	private CountDownTimer countDownTimer = new CountDownTimer(60000, 1000)
	{
		@Override
		public void onTick(long millisUntilFinished)
		{
			int k = (int) (millisUntilFinished / 1000);
			text_daojishi.setText(k + "");
			text_daojishi.setEnabled(false);
			text_daojishi.setTextColor(getResources().getColor(R.color.text_gray2));
		}

		@Override
		public void onFinish()
		{
			text_daojishi.setText("没收到");
			text_daojishi.setTextColor(getResources().getColor(R.color.baise));
			text_daojishi.setEnabled(true);
		}
	};

}
