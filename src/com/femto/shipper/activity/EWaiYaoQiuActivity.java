package com.femto.shipper.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;

import com.femto.shipper.R;
import com.femto.shipper.adapter.ListViewAdapter;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.XuQiuListBean;
import com.femto.shipper.bean.XuQiuListBean.XuQiuBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * @author mac
 *额外要求
 */
public class EWaiYaoQiuActivity extends BaseActivity implements OnClickListener {

	private MyAdapter adapter;
	private Button btn_queding;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ewaiyaoqiu);
		
		findViewById(R.id.left).setOnClickListener(this);
		btn_queding = (Button) findViewById(R.id.btn_queding);
		btn_queding.setOnClickListener(this);
		
		ListView listview = (ListView) findViewById(R.id.listview);
		adapter = new MyAdapter(mContext);
		listview.setAdapter(adapter);
		
		getDataForNet();
	}
	
	private void getDataForNet() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "order_request");

		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.EWAIYAOQIULIST + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");

		application.doGet(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				LogUtils.i(arg0.result);
				dismissProgressDialog();
				XuQiuListBean xuQiuListBean = GsonUtils.json2Bean(arg0.result, XuQiuListBean.class);
				if(xuQiuListBean.status.equals("0")){
					btn_queding.setVisibility(View.VISIBLE);
					adapter.setList(xuQiuListBean.list);
				}else{
					showToast(xuQiuListBean.msg);
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				LogUtils.i("" + arg1);
				dismissProgressDialog();
			}
		});
	}

	class MyAdapter extends ListViewAdapter<XuQiuBean>{

		public MyAdapter(Context context) {
			super(context);
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup arg2) {
			Holder holder ;
			if(convertView == null){
				holder = new Holder();
				convertView = View.inflate(mContext, R.layout.item_yaoqiu, null);
				holder.check_box = (CheckBox) convertView.findViewById(R.id.check_box);
				holder.text_leixing = (TextView) convertView.findViewById(R.id.text_leixing);
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			final XuQiuBean xuQiuBean = myList.get(position);
			holder.text_leixing.setText(xuQiuBean.title);
			
			
			holder.check_box.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					xuQiuBean.is_Select = isChecked;
				}
			});
			
			return convertView;
		}
	}
	
	class Holder{
		CheckBox check_box;
		TextView text_leixing;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_queding:
			ArrayList<CharSequence> arrayList = new ArrayList<CharSequence>();
			ArrayList<CharSequence> arrayListId = new ArrayList<CharSequence>();
			
			for (int i = 0; i < adapter.myList.size(); i++) {
				LogUtils.i("类型:"+adapter.myList.get(i).title+";状态:"+adapter.myList.get(i).is_Select);
				
				if(adapter.myList.get(i).is_Select){
					arrayList.add(adapter.myList.get(i).title);
					arrayListId.add(adapter.myList.get(i).id);
				}
			}
			
			Intent intent = new Intent();
			intent.putCharSequenceArrayListExtra("jihe",arrayList);
			intent.putCharSequenceArrayListExtra("arrayListId",arrayListId);
			
			setResult(Activity.RESULT_OK, intent);
			finish();
			break;
		case R.id.left:
			finish();
			break;
		}
	}
}
