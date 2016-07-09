package com.femto.shipper.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemLongClickListener;

import com.easemob.chatuidemo.db.DBSqlite_Y;
import com.femto.shipper.R;
import com.femto.shipper.activity.Order_op_ydxq;
import com.femto.shipper.base.BaseFragment;
import com.femto.shipper.bean.YundanXQBean;
import com.femto.shipper.utils.Constants;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.SharedPreferencesUtils;
import com.femto.shipper.utils.ToolUtils;
import com.femto.shipper.utils.Utils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.umeng.analytics.MobclickAgent;

public class YunDan_fragment_two extends BaseFragment
{
	private ListView Order_listView;
	private DBSqlite_Y dbY;
	public List<Map<String, String>> list = new ArrayList<Map<String, String>>();
	private Map<String, String> map;
	private SimpleAdapter adapter;
	private int index = 0;// 长按删除指定数据的索引
	private FragmentManager manager;
	private FragmentTransaction transaction;

	ImageView img;
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		View inflate = View.inflate(mContext, R.layout.order_dhcx, null);
		Order_listView = (ListView) inflate.findViewById(R.id.Order_listView);
		dbY = new DBSqlite_Y(this.mContext);
		Cursor cs = dbY.select();
		sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.sqlname), Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(getResources().getString(R.string.pwd), "");
		while (cs.moveToNext())
		{
			String avatar = cs.getString(cs.getColumnIndex("avatar"));
			String credit = cs.getString(cs.getColumnIndex("credit"));
			String yid = cs.getString(cs.getColumnIndex("yid"));
			String nick_name = cs.getString(cs.getColumnIndex("nick_name"));
			String plate_number = cs.getString(cs.getColumnIndex("plate_number"));
			String car_status = cs.getString(cs.getColumnIndex("car_status"));

			map = new HashMap<String, String>();
			if (avatar.equals(""))
			{
				map.put("avatar", String.valueOf(R.drawable.siji));
			} else
			{
				String imagename = Utils.imgSDUrl + Utils.imagename(avatar);
				map.put("avatar", imagename);
			}

			map.put("credit", credit);
			map.put("yid", yid);
			map.put("nick_name", nick_name);
			map.put("plate_number", plate_number);
			map.put("car_status", car_status);
			if (Float.valueOf(credit) == 3)
			{
				map.put("credit", String.valueOf(R.drawable.x3));
			} else if (Float.valueOf(credit) > 3 && Float.valueOf(credit) < 3.5)
			{
				map.put("credit", String.valueOf(R.drawable.x3));
			} else if (Float.valueOf(credit) >= 3.5 && Float.valueOf(credit) < 4)
			{
				map.put("credit", String.valueOf(R.drawable.x3b));
			} else if (Float.valueOf(credit) >= 4 && Float.valueOf(credit) < 4.5)
			{
				map.put("credit", String.valueOf(R.drawable.x4));
			} else if (Float.valueOf(credit) >= 4.5 && Float.valueOf(credit) < 5)
			{
				map.put("credit", String.valueOf(R.drawable.x4b));
			} else if (Float.valueOf(credit) == 5)
			{
				map.put("credit", String.valueOf(R.drawable.x5));
			} else if (Float.valueOf(credit) == 0)
			{
				map.put("credit", String.valueOf(R.drawable.kx5));
			}
			list.add(map);
		}

		String from[] = new String[] { "avatar", "credit", "yid", "nick_name", "plate_number", "car_status" };
		adapter = new SimpleAdapter(mContext, list, R.layout.order_yundan, from, new int[] { R.id.tuoxiang, R.id.pingxing, R.id.yundanhao,
				R.id.nice_siji, R.id.car_xing, R.id.zhuangtai });
		Order_listView.setAdapter(adapter);

		// View iv = View.inflate(mContext, R.layout.order_yundan, null);
		// img=(ImageView) iv.findViewById(R.id.tuoxiang);
		// BaseAdapter baseadapter = new BaseAdapter(getActivity(), list,
		// R.layout.order_yundan,new String[]{"avatar"}, new int[] {
		// R.id.tuoxiang });

		Order_listView.setOnItemLongClickListener(new OnItemLongClickListener()
		{

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{

				// TODO Auto-generated method stub
				index = arg2;
				AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
				builder.setMessage("是否删除？");
				builder.setPositiveButton("是", new DialogInterface.OnClickListener()
				{

					@Override
					public void onClick(DialogInterface dialog, int which)
					{

						String B_hao = (String) list.get(index).get("yid");
						dbY.delectYid(B_hao);// /删除
						manager = getActivity().getSupportFragmentManager();
						transaction = manager.beginTransaction();
						YunDan_fragment_two yundan = new YunDan_fragment_two();
						transaction.replace(R.id.fragment_two, yundan);
						transaction.commit();
					}
				});
				builder.setNegativeButton("否", new DialogInterface.OnClickListener()
				{

					@Override
					public void onClick(DialogInterface arg0, int arg1)
					{
						// TODO Auto-generated method stub

					}

				});
				builder.show();
				return false;
			}
		});

		Order_listView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				// TODO Auto-generated method stub
				String yid = list.get(arg2).get("yid");

				getintentYD(yid);
			}
		});

		return inflate;
	}

	public void getintentYD(String yid)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "waybill_info");
		map.put("username", phonea);
		map.put("yid", yid);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.ORDERLIST + jiaMi; // 本工程的URL加密, 返回(string)encryptDES
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");
		application.doGet_kuaishu(url, new RequestCallBack<String>()
		{

			@Override
			public void onFailure(HttpException arg0, String arg1)
			{
				// TODO Auto-generated method stub
				LogUtils.i("" + arg1);
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0)
			{
				// TODO Auto-generated method stub
				LogUtils.i("arg0.result:" + arg0.result);
				dismissProgressDialog();// /取消加载框
				YundanXQBean yundanxqBean = GsonUtils.json2Bean(arg0.result, YundanXQBean.class);// //用Gson转成json对象
				if (yundanxqBean.status.equals("0"))
				{
					Intent it = new Intent();
					it.putExtra("driver_avatar", yundanxqBean.waybill_info.avatar);
					it.putExtra("driver_name", yundanxqBean.waybill_info.nick_name);
					it.putExtra("star", yundanxqBean.waybill_info.credit);
					it.putExtra("plate_number", yundanxqBean.waybill_info.plate_number);
					it.putExtra("state", yundanxqBean.waybill_info.car_status);
					it.putExtra("ismycar", yundanxqBean.waybill_info.ismycar);
					it.putExtra("yid", yundanxqBean.waybill_info.yid);
					it.putExtra("cid", yundanxqBean.waybill_info.cid);
					it.putExtra("start_time", yundanxqBean.waybill_info.arrive_time);
					it.putExtra("sign_time", yundanxqBean.waybill_info.sign_time);
					it.putExtra("mobile", yundanxqBean.waybill_info.mobile);
					it.putExtra("order_state", yundanxqBean.waybill_info.waybill_state);
					it.putExtra("driver_id", yundanxqBean.waybill_info.driver_id);
					it.setClass(getActivity(), Order_op_ydxq.class);
					startActivity(it);

				} else
				{
					showToast("没有运单信息");
				}
			}
		});

	}

	public void onResume()
	{
		super.onResume();
		MobclickAgent.onPageStart("YunDan_fragment_two"); // 统计页面，"MainScreen"为页面名称，可自定义
	}

	public void onPause()
	{
		super.onPause();
		MobclickAgent.onPageEnd("YunDan_fragment_two");
	}
}
