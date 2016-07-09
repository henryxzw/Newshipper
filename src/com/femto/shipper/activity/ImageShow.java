package com.femto.shipper.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.utils.Net;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ImageShow extends BaseActivity {
	private ImageView imageshow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		imageshow = (ImageView) findViewById(R.id.imageshow);
		ImageLoader.getInstance().displayImage(
				Net.PICURL + getIntent().getStringExtra("image"), imageshow,
				application.options);
		findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
