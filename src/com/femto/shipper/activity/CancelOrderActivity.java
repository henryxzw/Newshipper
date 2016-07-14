package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * @author mac
 *取消订单
 */
public class CancelOrderActivity extends BaseActivity implements OnClickListener {
	
	private ImageView img_yuan1;
	private ImageView img_yuan2;
	private ImageView img_yuan3;
	private ImageView img_yuan4;
	String reason;//取消原因

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_cancel_order);
		findViewById(R.id.btn_quxiao).setOnClickListener(this);
		findViewById(R.id.btn_cancle).setOnClickListener(this);//取消订单
		
		
		findViewById(R.id.rela_xiugai).setOnClickListener(this);
		findViewById(R.id.rela_buding).setOnClickListener(this);
		findViewById(R.id.rela_buman).setOnClickListener(this);
		findViewById(R.id.rela_qita).setOnClickListener(this);
		
		img_yuan1 = (ImageView) findViewById(R.id.img_yuan1);
		img_yuan2 = (ImageView) findViewById(R.id.img_yuan2);
		img_yuan3 = (ImageView) findViewById(R.id.img_yuan3);
		img_yuan4 = (ImageView) findViewById(R.id.img_yuan4);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_quxiao:
			finish();
			break;
		case R.id.rela_xiugai:
			reason = "需要重新修改预订信息";
			img_yuan1.setImageResource(R.drawable.qidian_lvse);
			img_yuan2.setImageResource(R.drawable.process_unselect);
			img_yuan3.setImageResource(R.drawable.process_unselect);
			img_yuan4.setImageResource(R.drawable.process_unselect);
			break;
		case R.id.rela_buding:
			reason = "出行计划有变,不订了";
			img_yuan1.setImageResource(R.drawable.process_unselect);
			img_yuan2.setImageResource(R.drawable.qidian_lvse);
			img_yuan3.setImageResource(R.drawable.process_unselect);
			img_yuan4.setImageResource(R.drawable.process_unselect);
			break;
		case R.id.rela_buman:
			reason = "司机双约,我很不满";
			img_yuan1.setImageResource(R.drawable.process_unselect);
			img_yuan2.setImageResource(R.drawable.process_unselect);
			img_yuan3.setImageResource(R.drawable.qidian_lvse);
			img_yuan4.setImageResource(R.drawable.process_unselect);
			break;
		case R.id.rela_qita:
			reason = "其他:";
			img_yuan1.setImageResource(R.drawable.process_unselect);
			img_yuan2.setImageResource(R.drawable.process_unselect);
			img_yuan3.setImageResource(R.drawable.process_unselect);
			img_yuan4.setImageResource(R.drawable.qidian_lvse);
			break;
		case R.id.btn_cancle://取消订单
			PostDataForNet();
			break;
		}
	}

	private void PostDataForNet()
	{
		if(TextUtils.isEmpty(reason)){
			showToast("请选择取消原因");
			return;
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "cancel_order");
		map.put("username", application.userPhone);
		map.put("pwd", application.password);
		map.put("order_no","传进来的 订单号");
		map.put("reason", reason);

		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.QUXIAODINGDAN + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");

		application.doGet(url, new RequestCallBack<String>()
		{
			@Override
			public void onSuccess(ResponseInfo<String> arg0)
			{
				LogUtils.i(arg0.result);
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
}
