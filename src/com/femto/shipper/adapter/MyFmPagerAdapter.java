package com.femto.shipper.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFmPagerAdapter<T> extends FragmentPagerAdapter {
	
	private List<T> list;
	public  MyFmPagerAdapter(FragmentManager fm ,List<T> list) {
		super(fm);
		this.list = list;
	}

	@Override
	public Fragment getItem(int position) {
		return (Fragment) list.get(position);
	}

	@Override
	public int getCount() {
		return list.size();
	}
}
