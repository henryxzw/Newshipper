package com.femto.shipper.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseFragment;

public class NO_Select extends BaseFragment
{
	private TextView select_text;
	private String type;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View inflate = View.inflate(mContext, R.layout.order_dhcx, null);
		select_text = (TextView) inflate.findViewById(R.id.select_text);
		select_text.setText("没有搜索历史记录，点击搜索框快速查单");
		return inflate;

	}

}
