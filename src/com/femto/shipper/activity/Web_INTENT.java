package com.femto.shipper.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.umeng.analytics.MobclickAgentJSInterface;

public class Web_INTENT extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Web_INTENT.this.setTheme(R.style.All);
		setContentView(R.layout.activity_web_intent);
		TextView text_name_web = (TextView) findViewById(R.id.text_name_web);
		WebView webview = (WebView) findViewById(R.id.web_intent);
		new MobclickAgentJSInterface(this, webview);
		if (getIntent().getStringExtra("type").toString().equals("A")) {
			webview.loadUrl("http://www.spvan.com/mycontent.aspx?page=user_terms"); // 指定要加载的网页
			text_name_web.setText("用户协议");
		} else if (getIntent().getStringExtra("type").toString().equals("B")) {
			webview.loadUrl("http://www.spvan.com/mycontent.aspx?page=about"); // 指定要加载的网页
			text_name_web.setText("关于");
		} else if (getIntent().getStringExtra("type").equals("C")) {
			webview.loadUrl("http://www.spvan.com/mycontent.aspx?page=help"); // 指定要加载的网页
			text_name_web.setText("帮助");
		}
		findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}
}
