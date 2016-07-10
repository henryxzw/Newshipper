package com.femto.shipper;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity implements BDLocationListener {
	private LocationClient mLocClient;
/**
 * 娴嬭瘯github 不是吧水电费水电费
 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maind);
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(this);
		LocationClientOption option = new LocationClientOption();
		option.setCoorType("bd09ll");
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
	}

	@Override
	public void onReceiveLocation(BDLocation arg0) {
		if (arg0 == null)
			return;
		Toast.makeText(MainActivity.this,
				arg0.getLatitude() + "," + arg0.getLongitude(),
				Toast.LENGTH_SHORT).show();

	}
}