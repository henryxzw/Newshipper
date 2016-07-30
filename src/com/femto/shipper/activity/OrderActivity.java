package com.femto.shipper.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.easemob.chatuidemo.activity.MainActivity;
import com.easemob.chatuidemo.db.Order_SQLite_D;
import com.easemob.chatuidemo.db.Order_SQLite_Y;
import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.fragment.Order_DHCX;
import com.femto.shipper.fragment.YunDan_fragment;
import com.femto.shipper.utils.AllCapTransformationMethod;

/**
 * @author mac 我的订单
 */
@SuppressLint("ResourceAsColor")
public class OrderActivity extends BaseActivity implements OnClickListener {
	private Bundle bundle;
	private AlertDialog selfdialog;
	private String dy;
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private LinearLayout tupian;
	private TextView dindan, yundan;
	private EditText jiashuru;
	private ImageView queren;
	private static final String type2[] = { "全部", "已接单", "接货中", "装货中", "送货中",
			"卸货中", "已完成", "已取消" };
	private Spinner bt_shaixuan2;
	private ArrayAdapter<String> adapter2;
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;
	private String type = "";
	private String start_type;
	private String guanjianzhi, myyndantype = "", myyundanid = "";

	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		OrderActivity.this.setTheme(R.style.All);
		setContentView(R.layout.activity_order);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		findViewById(R.id.left).setOnClickListener(this);
		bt_shaixuan2 = (Spinner) findViewById(R.id.bt_shaixuan2);// 全部
		findViewById(R.id.quren).setOnClickListener(this);// /确认
		jiashuru = (EditText) findViewById(R.id.jiashuru);
		jiashuru.setOnClickListener(this);
		dindan = (TextView) findViewById(R.id.dindan);
		dindan.setOnClickListener(this);// /订单
		yundan = (TextView) findViewById(R.id.yundan);
		yundan.setOnClickListener(this);// /运单
		start_type = "1";// //默认订单状态
		dy = "D";
		jiashuru.setTransformationMethod(new AllCapTransformationMethod());
		guanjianzhi = jiashuru.getText().toString().trim();
		Spring_jinting();
		yundan.setBackgroundResource(R.drawable.kuang3);
		dindan.setBackgroundResource(R.drawable.kuang2);
		dindan.setTextColor(getResources().getColor(R.color.heise));
		yundan.setTextColor(getResources().getColor(R.color.huise));

	}

	private void Spring_jinting() {
		setFrament("");
		adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, type2);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		bt_shaixuan2.setVisibility(View.GONE);
		bt_shaixuan2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				guanjianzhi = "";
				// TODO Auto-generated method stub
				// { "全部", "", "", "", "", "", "已完成", "已取消" };
				if (type2[arg2].equals("全部")) {
					myyndantype = "";
					setyundanfragment(myyndantype, myyundanid);
				} else if (type2[arg2].equals("已接单")) {
					myyndantype = "已接单";
					setyundanfragment(myyndantype, myyundanid);
				} else if (type2[arg2].equals("接货中")) {
					myyndantype = "接货中";
					setyundanfragment(myyndantype, myyundanid);
				} else if (type2[arg2].equals("装货中")) {
					myyndantype = "装货中";
					setyundanfragment(myyndantype, myyundanid);
				} else if (type2[arg2].equals("送货中")) {
					myyndantype = "送货中";
					setyundanfragment(myyndantype, myyundanid);
				} else if (type2[arg2].equals("卸货中")) {
					myyndantype = "卸货中";
					setyundanfragment(myyndantype, myyundanid);
				} else if (type2[arg2].equals("已完成")) {
					myyndantype = "已完成";
					setyundanfragment(myyndantype, myyundanid);
				} else {
					myyndantype = "已取消";
					setyundanfragment(myyndantype, myyundanid);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

	}

	@Override
	public void onClick(View arg0) {
		if (isFastDoubleClick()) {
			return;
		}
		switch (arg0.getId()) {
		case R.id.left:
			Order_SQLite_D db1 = new Order_SQLite_D(this);
			db1.clear();
			Order_SQLite_Y db2 = new Order_SQLite_Y(this);
			db2.clear();
			finish();
			break;
		// /////////////////////////////////////////运单的监听//////////////////////////////////////////////////////////////////////////////////
		case R.id.yundan:// 点击运单
			guanjianzhi = "";
			dy = "Y";
			start_type = "2";
			// setyundanfragment("all");
			yundan.setBackgroundResource(R.drawable.kuang2);
			dindan.setBackgroundResource(R.drawable.kuang3);
			dindan.setTextColor(getResources().getColor(R.color.huise));
			yundan.setTextColor(getResources().getColor(R.color.heise));
			bt_shaixuan2.setVisibility(View.VISIBLE);
			bt_shaixuan2.setAdapter(adapter2);
			break;
		case R.id.dindan:// 点击订单
			guanjianzhi = "";
			start_type = "1";
			myyundanid = "";
			myyndantype = "";
			yundan.setBackgroundResource(R.drawable.kuang3);
			dindan.setBackgroundResource(R.drawable.kuang2);
			dindan.setTextColor(getResources().getColor(R.color.heise));
			yundan.setTextColor(getResources().getColor(R.color.huise));
			dy = "D";
			bt_shaixuan2.setVisibility(View.GONE);
			setFrament("");
			break;
		case R.id.jiashuru:// 输入框
			break;
		case R.id.quren:// 搜索按钮
			String gjz = jiashuru.getText().toString().trim();
			if (start_type.equals("1")) {
				// if (gjz.length() < 7) {
				// showToast("输入字符小于七位");
				// return;
				// }
				setFrament(gjz);
			}
			if (start_type.equals("2")) {
				// if (gjz.length() < 7) {
				// showToast("输入字符小于七位");
				// return;
				// }
				myyundanid = gjz;
				setyundanfragment(myyndantype, myyundanid);
			}
			break;
		default:
			break;
		}
	}

	// //运单
	private void setyundanfragment(String type, String id) {
		bundle = new Bundle();
		manager = this.getSupportFragmentManager();
		transaction = manager.beginTransaction();
		YunDan_fragment yundan = new YunDan_fragment();
		bundle.putString("type", type);
		bundle.putString("id", id);
		yundan.setArguments(bundle);
		transaction.replace(R.id.DY_frament, yundan);
		transaction.commit();
	}

	// /订单的方法
	private void setFrament(String check) {
		bundle = new Bundle();
		manager = this.getSupportFragmentManager();
		transaction = manager.beginTransaction();
		Order_DHCX order1 = new Order_DHCX();
		bundle.putString("check", check);
		order1.setArguments(bundle);
		transaction.replace(R.id.DY_frament, order1);
		transaction.commit();
	}

	public void onResume() {
		super.onResume();
	}

	public void onPause() {
		super.onPause();
	}
}
