package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easemob.EMError;
import com.easemob.chat.EMChatManager;
import com.easemob.exceptions.EaseMobException;
import com.femto.shipper.R;
import com.femto.shipper.application.DemoApplication;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.StatusBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * @author mac 注册
 */
public class MyRegisterActivity extends BaseActivity implements OnClickListener
{

	private TextView text_huozhu_duihao;
	private TextView text_jingjiren_duihao;
	private ImageView img_xieyi;
	String user_group = "1";// 1是货主, 默认为货主 2是经纪人
	boolean is_agree = true;// 同意为true
	private TextView text_daojishi;
	private EditText editext_phone;
	private EditText editext_xinmima;
	private EditText editext_password;
	private EditText editext_tuijian_phone;
	private AlertDialog alertDialog = null;
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_register);
		sharedPreferences = getSharedPreferences(getResources().getString(R.string.sqlname), Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(getResources().getString(R.string.pwd), "");
		editext_phone = (EditText) findViewById(R.id.editext_phone);
		editext_xinmima = (EditText) findViewById(R.id.editext_xinmima);
		findViewById(R.id.left).setOnClickListener(this);
		findViewById(R.id.btn_register).setOnClickListener(this);
		findViewById(R.id.rela_huozhu).setOnClickListener(this);// 货主
		findViewById(R.id.rela_jingjiren).setOnClickListener(this);// 经纪人
		findViewById(R.id.rela_xieyi).setOnClickListener(this);// 同意协议
		findViewById(R.id.text_xy).setOnClickListener(this);
		text_daojishi = (TextView) findViewById(R.id.text_daojishi);
		text_huozhu_duihao = (TextView) findViewById(R.id.text_huozhu_duihao);
		text_jingjiren_duihao = (TextView) findViewById(R.id.text_jingjiren_duihao);
		img_xieyi = (ImageView) findViewById(R.id.img_xieyi);
		text_daojishi.setOnClickListener(this);
		editext_password = (EditText) findViewById(R.id.editext_password);
		editext_tuijian_phone = (EditText) findViewById(R.id.editext_tuijian_phone);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.left:
			finish();
			break;
		case R.id.text_daojishi:// 倒计时
			GetCode();
			break;
		case R.id.rela_xieyi:// 同意协议

			if (is_agree)
			{
				img_xieyi.setImageResource(R.drawable.register_dui_lan);
			} else
			{
				img_xieyi.setImageResource(R.drawable.register_dui_hui);
			}
			break;
		case R.id.btn_register:
			ZhuCe();
			break;
		case R.id.rela_huozhu:
			user_group = "1";
			text_huozhu_duihao.setVisibility(View.VISIBLE);
			text_jingjiren_duihao.setVisibility(View.INVISIBLE);

			break;
		case R.id.rela_jingjiren:
			user_group = "2";
			text_huozhu_duihao.setVisibility(View.INVISIBLE);
			text_jingjiren_duihao.setVisibility(View.VISIBLE);
			break;
		case R.id.text_xy:// /跳到另一个页面显示服务协议
			startActivity(new Intent(mContext, FuWuXieYi_SPVAN.class));
			break;
		}
	}

	private void ZhuCe()
	{

		final String password = editext_xinmima.getText().toString();
		final String phone = editext_phone.getText().toString();
		String yanzhengCode = editext_password.getText().toString();

		if (!ToolUtils.isMobileNO(phone))
		{
			showToast("请输入合法的11位手机号码");
			return;
		}
		if (TextUtils.isEmpty(yanzhengCode))
		{
			showToast("请输入验证码");
			return;
		}
		if (TextUtils.isEmpty(password) || password.length() < 6 || password.length()

		> 32)
		{
			showToast("请输入6~32位的密码");
			return;
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "verify_sms_code");
		map.put("code_type", "reg");
		map.put("code", yanzhengCode);
		map.put("mobile", phone);

		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.YANZHENGCODE + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("注册中...");

		application.doGet(url, new RequestCallBack<String>()
		{
			@Override
			public void onSuccess(ResponseInfo<String> arg0)
			{
				LogUtils.i("验证码" + arg0.result);
				StatusBean statusBean = GsonUtils.json2Bean(arg0.result,

				StatusBean.class);
				if (statusBean.status.equals("0"))
				{
					PostRegist(phone, password);
				} else
				{
					showToast("验证码错误");
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

	// 开始注册
	private void PostRegist(final String phone, String password)
	{
		TelephonyManager tm = (TelephonyManager) mContext.getSystemService

		(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		String tuijianPhone = editext_tuijian_phone.getText().toString();
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "user_register");
		map.put("username", phone);
		map.put("pwd", password);
		map.put("code", deviceId);// 机器码
		map.put("user_group", user_group);
		if (!TextUtils.isEmpty(tuijianPhone))
		{
			map.put("referee", tuijianPhone);
		} else
		{
			map.put("referee", "");
		}
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.REGIST + jiaMi;
		LogUtils.i("请求的url:" + url);
		application.doGet(url, new RequestCallBack<String>()
		{

			@Override
			public void onSuccess(ResponseInfo<String> arg0)
			{
				LogUtils.i(arg0.result);
				dismissProgressDialog();
				StatusBean statusBean = GsonUtils.json2Bean(arg0.result,

				StatusBean.class);
				if (statusBean.status.equals("0"))
				{
					// 成功则弹窗
					AlertDialog.Builder alertBuilder = new

					AlertDialog.Builder(mContext);
					View view = View.inflate(mContext,

					R.layout.dialog_register_success, null);
					alertBuilder.setView(view);
					view.findViewById

					(R.id.btn_register).setOnClickListener(new OnClickListener()
					{

						@Override
						public void onClick(View v)
						{
							alertDialog.dismiss();
							finish();
						}
					});
					alertDialog = alertBuilder.show();
					// zhuceHuanXin(phone, "123456");
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

	// 获取验证码
	private void GetCode()
	{
		String phone = editext_phone.getText().toString();
		if (!ToolUtils.isMobileNO(phone))
		{
			showToast("请输入合法的11位手机号码");
			return;
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "send_sms");
		map.put("mobile", phone);
		map.put("code_type", "reg");

		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.GETCODE + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("正在获取验证码");

		application.doGet(url, new RequestCallBack<String>()
		{
			@Override
			public void onSuccess(ResponseInfo<String> arg0)
			{
				LogUtils.i("司机资料" + arg0.result);
				StatusBean statusBean = GsonUtils.json2Bean(arg0.result,

				StatusBean.class);
				if (statusBean.status.equals("0"))
				{
					showToast("验证码已发送到手机");
					countDownTimer.start();
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

	// 倒计时类
	private CountDownTimer countDownTimer = new CountDownTimer(60000, 1000)
	{
		@Override
		public void onTick(long millisUntilFinished)
		{
			int k = (int) (millisUntilFinished / 1000);
			text_daojishi.setText(k + "");
			text_daojishi.setEnabled(false);
			text_daojishi.setTextColor(getResources().getColor

			(R.color.text_gray2));
		}

		@Override
		public void onFinish()
		{
			text_daojishi.setText("没收到");
			text_daojishi.setTextColor(getResources().getColor(R.color.baise));
			text_daojishi.setEnabled(true);
		}
	};

	// 注册环信
	private void zhuceHuanXin(final String username, final String pwd)
	{

		if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd))
		{
			final ProgressDialog pd = new ProgressDialog(mContext);
			pd.setMessage(getResources().getString(R.string.Is_the_registered));
			pd.show();

			new Thread(new Runnable()
			{
				public void run()
				{
					try
					{
						// 调用sdk注册方法
						EMChatManager.getInstance

						().createAccountOnServer(username, pwd);
						runOnUiThread(new Runnable()
						{
							public void run()
							{
								pd.dismiss();
								// 保存用户名
								DemoApplication.getInstance

								().setUserName(username);

								// 成功则弹窗
								AlertDialog.Builder

								alertBuilder = new AlertDialog.Builder(mContext);
								View view = View.inflate

								(mContext, R.layout.dialog_register_success, null);
								alertBuilder.setView(view);
								view.findViewById

								(R.id.btn_register).setOnClickListener(new OnClickListener()
								{

									@Override
									public void onClick

									(View v)
									{

										alertDialog.dismiss();
										finish();
									}
								});
								alertDialog =

								alertBuilder.show();

								Toast.makeText(mContext,

								getResources().getString(R.string.Registered_successfully), 0).show();
							}
						});
					} catch (final EaseMobException e)
					{
						runOnUiThread(new Runnable()
						{
							public void run()
							{

								pd.dismiss();
								int errorCode = e.getErrorCode

								();
								if

								(errorCode == EMError.NONETWORK_ERROR)
								{
									Toast.makeText

									(mContext, getResources().getString(R.string.network_anomalies), Toast.LENGTH_SHORT).show();
								} else if (errorCode ==

								EMError.USER_ALREADY_EXISTS)
								{
									Toast.makeText

									(mContext, getResources().getString(R.string.User_already_exists), Toast.LENGTH_SHORT).show

									();
								} else if (errorCode ==

								EMError.UNAUTHORIZED)
								{
									Toast.makeText

									(mContext, getResources().getString(R.string.registration_failed_without_permission),

									Toast.LENGTH_SHORT).show();
								} else if (errorCode ==

								EMError.ILLEGAL_USER_NAME)
								{
									Toast.makeText(mContext,

									getResources().getString(R.string.illegal_user_name), Toast.LENGTH_SHORT).show();
								} else
								{
									Toast.makeText

									(mContext, getResources().getString(R.string.Registration_failed) + e.getMessage(),

									Toast.LENGTH_SHORT).show();
								}
							}
						});
					}
				}
			}).start();
		}
	}
}
