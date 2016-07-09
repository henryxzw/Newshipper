package com.femto.shipper.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;

/**
 * @author mac
 *发现
 */
public class FoundActivity extends BaseActivity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find);
		
		
		
		
		findViewById(R.id.left).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left:
			finish();
			break;
		}
	}
}
