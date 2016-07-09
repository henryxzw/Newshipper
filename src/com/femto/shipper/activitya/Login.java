package com.femto.shipper.activitya;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.femto.shipper.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener
{
	private LocationClient mLocClient;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcomea);
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(new MyLocationListenner());
		setLocationOption();
	}

	private void setLocationOption()
	{
		LocationClientOption option = new LocationClientOption();
		option.setCoorType("bd09ll");
		option.setAddrType("all");
		option.setScanSpan(3000);
		option.setPriority(LocationClientOption.NetWorkFirst);
		option.disableCache(true);
		mLocClient.setLocOption(option);
		mLocClient.start();
	}

	public class MyLocationListenner implements BDLocationListener
	{

		@Override
		public void onReceiveLocation(BDLocation arg0)
		{
			if (arg0 == null)
				return;
			Toast.makeText(Login.this, arg0.getCity(), Toast.LENGTH_SHORT).show();

		}
	}

	@Override
	public void onClick(View arg0)
	{

	}

}
