package com.femto.shipper.activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;

public class BaiduSoSo extends BaseActivity implements OnClickListener
{
	private EditText et_serchText;
	private ImageView iv_serch;
	private TextView text_jl, text_sc, ditusosuo;
	private ListView listview_xianshi;
	private Intent intent, intenta;
	Bundle bundlea = null;
	String dizi = "";
	String a = "";
	String dnl = "";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baidusoso);
		ditusosuo = (TextView) findViewById(R.id.ditusosuo);
		ditusosuo.setOnClickListener(this);
		// /输入
		et_serchText = (EditText) findViewById(R.id.et_serchText);
		// 搜索
		iv_serch = (ImageView) findViewById(R.id.iv_serch);
		iv_serch.setOnClickListener(this);
		// 搜索记录
		text_jl = (TextView) findViewById(R.id.text_jl);
		text_jl.setOnClickListener(this);
		// 收藏记录
		text_sc = (TextView) findViewById(R.id.text_sc);
		text_sc.setOnClickListener(this);
		// /listview
		listview_xianshi = (ListView) findViewById(R.id.listview_xianshi);
		// ///适配器显示布局文件sosobuju.xml
		intent = getIntent();
		info();

	}

	private void info()
	{
		bundlea = getIntent().getExtras();
		dizi = bundlea.getString("dizi");
		a = bundlea.getString("a");
		dnl = bundlea.getString("dnl");
	}

	@Override
	public void onClick(View arg0)
	{
		switch (arg0.getId())
		{
		case R.id.ditusosuo:
//			Intent intentb = new Intent(mContext, BaiduMapSelectActivity.class);
//			Bundle bundleb = new Bundle();
//			bundleb.putString("dizi", dizi);
//			bundleb.putString("a", a);
//			bundleb.putString("dnl", dnl);
//			intentb.putExtras(bundleb);
//			startActivity(intentb);
			break;
		// /搜索
		case R.id.iv_serch:
			break;
		// /搜索记录
		case R.id.text_jl:

			break;
		// /收藏记录
		case R.id.text_sc:

			break;

		default:
			break;
		}
	}

}
