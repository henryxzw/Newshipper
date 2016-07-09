package com.femto.shipper.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;

public class FuWuXieYi_SPVAN extends BaseActivity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fuwuxieyi_spvan);
		findViewById(R.id.img_left).setOnClickListener(this);
	
	}

	@Override
	public void onClick(View arg0)
	{
		// TODO Auto-generated method stub
		switch (arg0.getId())
		{
		case R.id.img_left:
			finish();
			break;

		default:
			break;
		}
	}
	

}
