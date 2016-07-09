package com.femto.shipper.activity;

import java.util.ArrayList;
import java.util.List;
import com.femto.shipper.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class AlbumActivity extends Activity implements OnClickListener {
	private GridView xcgv;
	private AlbumGridViewAdapter albumgridviewadapter;
	private TextView xcqx, xcmzptv, xcqd;
	private ArrayList<ImageItem> dataList;
	private RelativeLayout xcrla;
	private AlbumHelper helper;
	public static List<ImageBucket> list;
	private int a[];

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.plugin_camera_album);
		init();
		initlistener();
		pdan();
	}

	private void init() {
		PublicWay.activityList.add(this);
		IntentFilter filter = new IntentFilter("data.broadcast.action");
		registerReceiver(broadcastreceiver, filter);
		helper = AlbumHelper.getHelper();
		helper.init(getApplicationContext());
		list = helper.getImagesBucketList(false);
		xcrla = (RelativeLayout) findViewById(R.id.xcrla);
		xcqx = (TextView) findViewById(R.id.xcqx);
		xcmzptv = (TextView) findViewById(R.id.xcmzptv);
		xcqd = (TextView) findViewById(R.id.xcqd);
		xcgv = (GridView) findViewById(R.id.xcgv);
		dataList = new ArrayList<ImageItem>();
		for (int i = 0; i < list.size(); i++) {
			dataList.addAll(list.get(i).imageList);
		}
		a = new int[dataList.size()];
		for (int i = 0; i < dataList.size(); i++) {
			a[i] = 0;
		}
		albumgridviewadapter = new AlbumGridViewAdapter(this, dataList,
				Bimp.tempSelectBitmap);
		xcgv.setAdapter(albumgridviewadapter);
		xcgv.setEmptyView(xcmzptv);
		xcrla.setOnClickListener(this);
		xcqx.setOnClickListener(this);
		xcqd.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(broadcastreceiver);
		super.onDestroy();
	}

	private void initlistener() {
		albumgridviewadapter
				.setOnItemClickListener(new AlbumGridViewAdapter.OnItemClickListener() {
					@Override
					public void onItemClick(final ToggleButton toggleButton,
							int position, boolean isChecked, Button chooseBt) {
						if (Bimp.tempSelectBitmap.size() >= PublicWay.num) {
							toggleButton.setChecked(false);
							chooseBt.setVisibility(View.GONE);
							if (!removeOneData(dataList.get(position))) {
								Toast.makeText(AlbumActivity.this,
										"亲,最多只能选3张图片哦!", Toast.LENGTH_SHORT)
										.show();
							}
							return;
						}
						if (isChecked) {
							a[position] = 1;
							chooseBt.setVisibility(View.VISIBLE);
							Bimp.tempSelectBitmap.add(dataList.get(position));
						} else {
							a[position] = 0;
							Bimp.tempSelectBitmap.remove(dataList.get(position));
							chooseBt.setVisibility(View.GONE);
						}
						pdan();
					}
				});
	}

	BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			albumgridviewadapter.notifyDataSetChanged();
		}
	};

	private void fh() {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == 1) {
				Bimp.tempSelectBitmap.remove(dataList.get(i));
			}
		}
		finish();
	}

	private boolean removeOneData(ImageItem imageItem) {
		if (Bimp.tempSelectBitmap.contains(imageItem)) {
			Bimp.tempSelectBitmap.remove(imageItem);
			pdan();
			return true;
		}
		return false;
	}

	public void pdan() {
		if (Bimp.tempSelectBitmap.size() > 0) {
			xcqd.setText(getResources().getString(R.string.qd) + "("
					+ Bimp.tempSelectBitmap.size() + "/" + PublicWay.num + ")");
			xcqd.setTextColor(Color.BLACK);
			xcqd.setClickable(true);
		} else {
			xcqd.setText(getResources().getString(R.string.qd) + "("
					+ Bimp.tempSelectBitmap.size() + "/" + PublicWay.num + ")");
			xcqd.setTextColor(Color.parseColor("#A1A1A1"));
			xcqd.setClickable(false);
		}
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			fh();
		}
		return false;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.xcrla:
			fh();
			break;
		case R.id.xcqx:
			fh();
			break;
		case R.id.xcqd:
			finish();
			break;
		}
	}
}