package com.femto.shipper.activitya;

import com.femto.shipper.R;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgentJSInterface;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Intentfwxy extends Activity implements OnClickListener
{
	private WebView mywebviewa;
	private TextView webtitletva;
	private RelativeLayout intentfwxy;

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.intentfwxy);
		intentfwxy = (RelativeLayout) findViewById(R.id.intentfwxy);
		mywebviewa = (WebView) findViewById(R.id.mywebviewa);
		new MobclickAgentJSInterface(this, mywebviewa);
		webtitletva = (TextView) findViewById(R.id.webtitletva);
		String a = "http://www.spvan.com/mycontent.aspx?page=user_terms";
		mywebviewa.loadUrl(a);
		intentfwxy.setOnClickListener(this);
		mywebviewa.setWebViewClient(new WebViewClient()
		{
			@Override
			public void onPageFinished(WebView view, String url)
			{
				webtitletva.setText(view.getTitle());
				super.onPageFinished(view, url);
			}
		});
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	public void onClick(View arg0)
	{
		switch (arg0.getId())
		{
		case R.id.intentfwxy:
			finish();
			break;
		}
	}
}
