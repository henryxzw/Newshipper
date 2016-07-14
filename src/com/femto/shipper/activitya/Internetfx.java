package com.femto.shipper.activitya;

import com.femto.shipper.R;
import com.umeng.analytics.MobclickAgent;
import com.umeng.analytics.MobclickAgentJSInterface;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("SetJavaScriptEnabled")
public class Internetfx extends Activity implements OnClickListener {
	private WebView mywebview;
	private TextView webtitletv;
	private RelativeLayout intentjfrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.intentjfbz);
		intentjfrl = (RelativeLayout) findViewById(R.id.intentjfrl);
		mywebview = (WebView) findViewById(R.id.mywebview);
		new MobclickAgentJSInterface(this, mywebview);
		webtitletv = (TextView) findViewById(R.id.webtitletv);
		String a = "http://spvan.wsq.umeng.com";
		mywebview.getSettings().setJavaScriptEnabled(true);
		mywebview.getSettings().setSupportZoom(true);
		mywebview.getSettings().setBuiltInZoomControls(true);
		mywebview.loadUrl(a);
		intentjfrl.setOnClickListener(this);
		mywebview.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				webtitletv.setText(view.getTitle());
				super.onPageFinished(view, url);
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (mywebview.canGoBack()) {
				mywebview.goBack();
				return true;
			} else {
				finish();
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.intentjfrl:
			finish();
			break;
		}
	}
}
