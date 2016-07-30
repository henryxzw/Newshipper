package com.femto.shipper.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.easemob.chatuidemo.db.DBSqlite;
import com.easemob.chatuidemo.db.Order_SQLite_Two_D;
import com.femto.shipper.R;
import com.femto.shipper.activity.OrderPropertyActivity;
import com.femto.shipper.adapter.ListViewAdapter;
import com.femto.shipper.base.BaseFragment;
import com.femto.shipper.bean.DanDin_SOSO;
import com.femto.shipper.bean.DanDin_SOSO.OrderListB;
import com.femto.shipper.utils.LogUtils;

public class Dindan_Fragment_two extends BaseFragment
{

	private List<Map<String, String>> list;
	private MyAdapter adapter;
	private Map<String, String> m1, m2, m3, m4, m5;
	private Map<String, String> map;
	private ListView listview;
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private Order_SQLite_Two_D db;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View inflate = View.inflate(mContext, R.layout.order_dhcx, null);
		db = new Order_SQLite_Two_D(getActivity());
		adapter = new MyAdapter(getActivity());
		if (db.selectAll("order").size() != 0)
		{
			adapter.setList(db.selectAll("order"));
		}
		listview = (ListView) inflate.findViewById(R.id.Order_listView);
		listview.setAdapter(adapter);
		return inflate;
	}

	class MyAdapter extends ListViewAdapter<DanDin_SOSO>
	{

		public MyAdapter(Context context)
		{
			super(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2)
		{
			Holder holder = null;
			if (convertView == null)
			{
				holder = new Holder();
				convertView = View.inflate(mContext, R.layout.order_dhcx_itme, null);
				holder.Order_dindanhao = (TextView) convertView.findViewById(R.id.Order_dindanhao);
				holder.Order_start = (TextView) convertView.findViewById(R.id.Order_start);
				holder.Order_end = (TextView) convertView.findViewById(R.id.Order_end);
				holder.Order_time = (TextView) convertView.findViewById(R.id.Order_time);
				holder.order_listview_itme = (ListView) convertView.findViewById(R.id.order_listview_itme);
				holder.listview_jianting = (LinearLayout) convertView.findViewById(R.id.listview_jianting);
				convertView.setTag(holder);
			} else
			{
				holder = (Holder) convertView.getTag();
			}
			final DanDin_SOSO DanDin_SOSO = myList.get(position);
			holder.Order_dindanhao.setText(DanDin_SOSO.orderinfo.order_no);
			String str = DanDin_SOSO.orderinfo.payment_time;
			holder.Order_time.setText(str.split("\\ ")[0].split("\\-")[1] + "-" + str.split("\\ ")[0].split("\\-")[2] + " "
					+ str.split("\\ ")[1].split("\\:")[0] + ":" + str.split("\\ ")[1].split("\\:")[1]);
			String start;
			String end;
			if (DanDin_SOSO.orderinfo.addr_start.split("\\|")[0].length() > 20)
			{
				start = DanDin_SOSO.orderinfo.addr_start.split("\\|")[0].substring(0, 19) + ".....";
			} else
			{
				start = DanDin_SOSO.orderinfo.addr_start.split("\\|")[0];
			}
			if (DanDin_SOSO.orderinfo.addr_end.split("\\|")[0].length() > 20)
			{
				end = DanDin_SOSO.orderinfo.addr_end.split("\\|")[0].substring(0, 19) + ".....";
			} else
			{
				end = DanDin_SOSO.orderinfo.addr_end.split("\\|")[0];
			}

			holder.Order_start.setText(start);
			holder.Order_end.setText(end);
			list = new ArrayList<Map<String, String>>();
			m1 = new HashMap<String, String>();
			m2 = new HashMap<String, String>();
			m3 = new HashMap<String, String>();
			m4 = new HashMap<String, String>();
			m5 = new HashMap<String, String>();
			if (DanDin_SOSO.orderinfo.car_list.size() == 0)
			{
				m1.put("plate_number", "");
				m1.put("car_type", "");
				m1.put("car_statusr", "");
				list.add(m1);
			}
			if (DanDin_SOSO.orderinfo.car_list.size() == 1)
			{
				m1.put("plate_number", DanDin_SOSO.orderinfo.car_list.get(0).plate_number);
				m1.put("car_type", DanDin_SOSO.orderinfo.car_list.get(0).car_type.split("\\,")[0]);
				m1.put("car_statusr", DanDin_SOSO.orderinfo.car_list.get(0).car_status);
				list.add(m1);
			}
			if (DanDin_SOSO.orderinfo.car_list.size() == 2)
			{
				m1.put("plate_number", DanDin_SOSO.orderinfo.car_list.get(0).plate_number);
				m1.put("car_type", DanDin_SOSO.orderinfo.car_list.get(0).car_type.split("\\,")[0]);
				m1.put("car_statusr", DanDin_SOSO.orderinfo.car_list.get(0).car_status);
				list.add(m1);
				m2.put("plate_number", DanDin_SOSO.orderinfo.car_list.get(1).plate_number);
				m2.put("car_type", DanDin_SOSO.orderinfo.car_list.get(1).car_type.split("\\,")[0]);
				m2.put("car_statusr", DanDin_SOSO.orderinfo.car_list.get(1).car_status);
				list.add(m2);
			}
			if (DanDin_SOSO.orderinfo.car_list.size() == 3)
			{
				m1.put("plate_number", DanDin_SOSO.orderinfo.car_list.get(0).plate_number);
				m1.put("car_type", DanDin_SOSO.orderinfo.car_list.get(0).car_type.split("\\,")[0]);
				m1.put("car_statusr", DanDin_SOSO.orderinfo.car_list.get(0).car_status);
				list.add(m1);
				m2.put("plate_number", DanDin_SOSO.orderinfo.car_list.get(1).plate_number);
				m2.put("car_type", DanDin_SOSO.orderinfo.car_list.get(1).car_type.split("\\,")[0]);
				m2.put("car_statusr", DanDin_SOSO.orderinfo.car_list.get(1).car_status);
				list.add(m2);
				m3.put("plate_number", DanDin_SOSO.orderinfo.car_list.get(2).plate_number);
				m3.put("car_type", DanDin_SOSO.orderinfo.car_list.get(2).car_type.split("\\,")[0]);
				m3.put("car_statusr", DanDin_SOSO.orderinfo.car_list.get(2).car_status);
				list.add(m3);
			}
			if (DanDin_SOSO.orderinfo.car_list.size() == 4)
			{
				m1.put("plate_number", DanDin_SOSO.orderinfo.car_list.get(0).plate_number);
				m1.put("car_type", DanDin_SOSO.orderinfo.car_list.get(0).car_type.split("\\,")[0]);
				m1.put("car_statusr", DanDin_SOSO.orderinfo.car_list.get(0).car_status);
				list.add(m1);
				m2.put("plate_number", DanDin_SOSO.orderinfo.car_list.get(1).plate_number);
				m2.put("car_type", DanDin_SOSO.orderinfo.car_list.get(1).car_type.split("\\,")[0]);
				m2.put("car_statusr", DanDin_SOSO.orderinfo.car_list.get(1).car_status);
				list.add(m2);
				m3.put("plate_number", DanDin_SOSO.orderinfo.car_list.get(2).plate_number);
				m3.put("car_type", DanDin_SOSO.orderinfo.car_list.get(2).car_type.split("\\,")[0]);
				m3.put("car_statusr", DanDin_SOSO.orderinfo.car_list.get(2).car_status);
				list.add(m3);
				m4.put("plate_number", DanDin_SOSO.orderinfo.car_list.get(3).plate_number);
				m4.put("car_type", DanDin_SOSO.orderinfo.car_list.get(3).car_type.split("\\,")[0]);
				m4.put("car_statusr", DanDin_SOSO.orderinfo.car_list.get(3).car_status);
				list.add(m4);
			}
			if (DanDin_SOSO.orderinfo.car_list.size() == 5)
			{
				m1.put("plate_number", DanDin_SOSO.orderinfo.car_list.get(0).plate_number);
				m1.put("car_type", DanDin_SOSO.orderinfo.car_list.get(0).car_type.split("\\,")[0]);
				m1.put("car_statusr", DanDin_SOSO.orderinfo.car_list.get(0).car_status);
				list.add(m1);
				m2.put("plate_number", DanDin_SOSO.orderinfo.car_list.get(1).plate_number);
				m2.put("car_type", DanDin_SOSO.orderinfo.car_list.get(1).car_type.split("\\,")[0]);
				m2.put("car_statusr", DanDin_SOSO.orderinfo.car_list.get(1).car_status);
				list.add(m2);
				m3.put("plate_number", DanDin_SOSO.orderinfo.car_list.get(2).plate_number);
				m3.put("car_type", DanDin_SOSO.orderinfo.car_list.get(2).car_type.split("\\,")[0]);
				m3.put("car_statusr", DanDin_SOSO.orderinfo.car_list.get(2).car_status);
				list.add(m3);
				m4.put("plate_number", DanDin_SOSO.orderinfo.car_list.get(3).plate_number);
				m4.put("car_type", DanDin_SOSO.orderinfo.car_list.get(3).car_type.split("\\,")[0]);
				m4.put("car_statusr", DanDin_SOSO.orderinfo.car_list.get(3).car_status);
				list.add(m4);
				m5.put("plate_number", DanDin_SOSO.orderinfo.car_list.get(4).plate_number);
				m5.put("car_type", DanDin_SOSO.orderinfo.car_list.get(4).car_type.split("\\,")[0]);
				m5.put("car_statusr", DanDin_SOSO.orderinfo.car_list.get(4).car_status);
				list.add(m5);
			}

			SimpleAdapter simple = new SimpleAdapter(getActivity(), list, R.layout.itme_order, new String[] { "plate_number", "car_type",
					"car_statusr" }, new int[] { R.id.jzb_time, R.id.jzb_title, R.id.jzb_money });
			holder.order_listview_itme.setAdapter(simple);
			setListViewHeightBasedOnChildren(holder.order_listview_itme);
			// list.clear();
			holder.listview_jianting.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View arg0)
				{
					// TODO Auto-generated method stub
					String order_no = DanDin_SOSO.orderinfo.order_no;
					Intent it = new Intent();
					it.putExtra("order_no", order_no);
					it.setClass(getActivity(), OrderPropertyActivity.class);
					startActivity(it);
				}
			});

			holder.listview_jianting.setOnLongClickListener(new OnLongClickListener()
			{
				@Override
				public boolean onLongClick(View arg0)
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
					builder.setMessage("是否删除？");
					builder.setPositiveButton("是", new DialogInterface.OnClickListener()
					{

						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							String order_no = DanDin_SOSO.orderinfo.order_no;
							LogUtils.i("---------删除---------------->>>");
							db.delect("order" + order_no);
							LogUtils.i("---------删除成功---------------->>>");
							manager = getActivity().getSupportFragmentManager();
							transaction = manager.beginTransaction();
							Dindan_Fragment_two dindan = new Dindan_Fragment_two();
							transaction.replace(R.id.fragment_two, dindan);
							transaction.commit();
							LogUtils.i("---------刷新---------------->>>");
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
					return true;
				}
			});

			return convertView;
		}
	}

	class Holder
	{
		TextView Order_dindanhao;
		TextView Order_start;
		TextView Order_end;
		TextView Order_time;
		ListView order_listview_itme;
		LinearLayout listview_jianting;

	}

	// ////设置listview的高度
	public void setListViewHeightBasedOnChildren(ListView listView)
	{

		ListAdapter listAdapter = listView.getAdapter();

		if (listAdapter == null)
		{
			return;
		}

		int totalHeight = 0;

		for (int i = 0; i < listAdapter.getCount(); i++)
		{
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();

		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

		((MarginLayoutParams) params).setMargins(10, 10, 10, 10); // 可删除

		listView.setLayoutParams(params);
	}

	public void onResume()
	{
		super.onResume();
	}

	public void onPause()
	{
		super.onPause();
	}
}
