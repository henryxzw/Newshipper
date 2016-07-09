package com.femto.shipper.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.femto.shipper.R;
import com.femto.shipper.adapter.ListViewAdapter;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * @author mac
 *收藏的司机
 */
public class CollectionDriverActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shoucangsiji);
		
		findViewById(R.id.left).setOnClickListener(this);
		ListView listview = (ListView) findViewById(R.id.listview);
		List list = new ArrayList();
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		list.add("1");
		MyAdapter adapter = new MyAdapter(mContext);
		adapter.setList(list);
		listview.setAdapter(adapter);
		
		getDataForNet();
		
	}

	private void getDataForNet()
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "driver_list");
		map.put("username", application.userPhone);

		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.SHOUCANGSIJILIST + jiaMi;
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

	class MyAdapter extends ListViewAdapter {

		public MyAdapter(Context context) {
			super(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup arg2) {
			convertView = View.inflate(mContext, R.layout.item_shoucangsiji, null);
			
			TextView text_state = (TextView) convertView.findViewById(R.id.text_state);
			
			
			return convertView;
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.line_back:
			finish();
			break;
		case R.id.left:
			finish();
			break;
		}
	}

	
	
	
	
}
