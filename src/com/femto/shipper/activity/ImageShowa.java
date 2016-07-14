package com.femto.shipper.activity;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageShowa extends BaseActivity {
	private ImageView imageshow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		imageshow = (ImageView) findViewById(R.id.imageshow);
		ImageLoader.getInstance().displayImage(
				ToolUtils.DownloadDemo(getIntent().getStringExtra("image")),
				imageshow, application.options);
		findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
}