package com.femto.shipper.activitya;

import com.femto.shipper.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Intentjfbz extends Activity implements OnClickListener
{
	private WebView mywebview;
	private TextView webtitletv;
	private RelativeLayout intentjfrl;

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.intentjfbz);
		intentjfrl = (RelativeLayout) findViewById(R.id.intentjfrl);
		mywebview = (WebView) findViewById(R.id.mywebview);
		webtitletv = (TextView) findViewById(R.id.webtitletv);
		String a = "http://www.spvan.com/mycontent.aspx?page=chargingstandard";
		mywebview.loadUrl(a);
		intentjfrl.setOnClickListener(this);
		mywebview.setWebViewClient(new WebViewClient()
		{
			@Override
			public void onPageFinished(WebView view, String url)
			{
				webtitletv.setText(view.getTitle());
				super.onPageFinished(view, url);
			}
		});

	}

	@Override
	protected void onResume()
	{
		super.onResume();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
	}

	@Override
	public void onClick(View arg0)
	{
		switch (arg0.getId())
		{
		case R.id.intentjfrl:
			finish();
			break;
		}
	}
}
