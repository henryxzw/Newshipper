package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;
import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.InvoiceBean;
import com.femto.shipper.bean.My_XinXiBean;
import com.femto.shipper.utils.Constants;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.SharedPreferencesUtils;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.analytics.MobclickAgent;

/**
 * @author mac 开取发票
 */
@SuppressLint("NewApi")
public class InvoiceActivity extends BaseActivity implements OnClickListener {

	private TextView text_kaipiaoedu;
	private LinearLayout rela_ll_layout1;
	private RelativeLayout RelativeLayout1;
	private boolean isOpened1 = false; // 默认是关闭
	private int height;
	private Button rela_duanxi, rela_dianhua, rela_qx;
	private String max_money;
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kaiqufapiao);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		rela_ll_layout1 = (LinearLayout) findViewById(R.id.rela_ll_layout1);
		rela_duanxi = (Button) findViewById(R.id.rela_duanxi);
		rela_dianhua = (Button) findViewById(R.id.rela_dianhua);
		rela_qx = (Button) findViewById(R.id.rela_qx);
		RelativeLayout1 = (RelativeLayout) findViewById(R.id.kaipiao_RelativeLayout);
		rela_duanxi.setOnClickListener(this);
		rela_dianhua.setOnClickListener(this);
		rela_qx.setOnClickListener(this);

		findViewById(R.id.left).setOnClickListener(this);
		findViewById(R.id.rela_kfp).setOnClickListener(this);
		findViewById(R.id.lin_kaipiaojilv).setOnClickListener(this);// 获取开票记录
		text_kaipiaoedu = (TextView) findViewById(R.id.text_kaipiaoedu);
		geint();
		// 测量高度
		rela_ll_layout1.measure(0, 0);
		height = rela_ll_layout1.getMeasuredHeight();
		// ///默认隐藏
		LayoutParams layoutParams1 = (LayoutParams) rela_ll_layout1
				.getLayoutParams();
		layoutParams1.height = 0;
		rela_ll_layout1.setLayoutParams(layoutParams1);

	}

	private void geint() {

		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "invoice_quota");
		map.put("username", phonea);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.YONGHUXINXI + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");

		application.doget(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				LogUtils.i("" + arg1);
				dismissProgressDialog();
				text_kaipiaoedu.setText("¥" + "0");
				max_money = "0";

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				LogUtils.i(arg0.result);
				dismissProgressDialog();
				InvoiceBean InvoiceBean = GsonUtils.json2Bean(arg0.result,
						InvoiceBean.class);
				if (InvoiceBean.status.equals("0")) {
					text_kaipiaoedu.setText("¥" + InvoiceBean.quota);
					max_money = InvoiceBean.quota;
				} else {
					showToast("访问出错");
					text_kaipiaoedu.setText("¥" + "0");
					max_money = "0";
				}
			}
		});

	}

	@Override
	public void onClick(View v) {
		if (isFastDoubleClick()) {
			return;
		}
		switch (v.getId()) {

		case R.id.rela_kfp:// 我要开票
			if (max_money.trim().equals("0")) {
				showToast("开票额度不足！");
			} else {
				toggle1(true);
			}
			break;
		case R.id.lin_kaipiaojilv:// 开票记录
			startActivity(KaiPiaoJiLuActivity.class);
			break;
		case R.id.rela_duanxi:// 普通发票
			LogUtils.i("-------------------->>>>普通发票");
			toggle1(true);
			Intent it = new Intent(mContext, KaiPiaoXinXiActivity.class);
			it.putExtra("max_money", max_money);
			startActivity(it);

			break;
		case R.id.rela_dianhua:// 增值发票

			toggle1(true);
			Intent it1 = new Intent(mContext, KaiPiao_ZengZhi.class);
			it1.putExtra("max_money", max_money);
			startActivity(it1);
			LogUtils.i("-------------------->>>>增值发票");
			break;
		case R.id.rela_qx:// 取消
			LogUtils.i("-------------------->>>>取消");
			toggle1(true);
			break;

		case R.id.left:
			finish();
			break;
		}
	}

	// 打开或是关闭
	private void toggle1(boolean animated) {

		if (isOpened1) {// 是打开的。关闭
			if (animated) {// 执行动画
				int start = height;
				int end = 0;
				doAnimation1(start, end);
			} else {
				LayoutParams layoutParams1 = (LayoutParams) rela_ll_layout1
						.getLayoutParams();
				layoutParams1.height = 0;
				rela_ll_layout1.setLayoutParams(layoutParams1);

			}
		} else {// 关闭状态，打开
			if (animated) {
				int start = 0;
				int end = height;
				doAnimation1(start, end);
			} else {
				LayoutParams layoutParams1 = (LayoutParams) rela_ll_layout1
						.getLayoutParams();
				layoutParams1.height = height;
				rela_ll_layout1.setLayoutParams(layoutParams1);
			}
		}
		isOpened1 = !isOpened1;
	}

	/**
	 * 执行动画
	 * 
	 * @param start
	 * @param end
	 */
	@SuppressLint("NewApi")
	private void doAnimation1(int start, int end) {
		ValueAnimator valueAnimator = new ValueAnimator();
		valueAnimator.setDuration(300);
		// 设置值的变化
		valueAnimator.setIntValues(start, end);
		// 设置监听
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				int value = (Integer) valueAnimator.getAnimatedValue();// 获取当前变化的值
				// 修改属性
				LayoutParams layoutParams = (LayoutParams) rela_ll_layout1
						.getLayoutParams();
				layoutParams.height = value;
				rela_ll_layout1.setLayoutParams(layoutParams);
			};
		});
		// 启动动画
		valueAnimator.start();
	}

	public void onResume() {
		geint();
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
