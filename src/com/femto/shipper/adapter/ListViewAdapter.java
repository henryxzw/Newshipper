package com.femto.shipper.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class ListViewAdapter<T> extends BaseAdapter
{

	protected Context context;
	public List<T> myList;

	public ListViewAdapter(Context context)
	{
		this.context = context;
	}

	// 刷新数据
	public void setList(List<T> list)
	{
		this.myList = list;
		notifyDataSetChanged();
	}

	/**
	 * 为适配器添加一个泛型条目T
	 * 
	 * @param t
	 */
	public void addItem(T t)
	{
		if (myList == null)
		{
			myList = new ArrayList<T>();
		}
		myList.add(t);
		notifyDataSetChanged();
	}

	/**
	 * 为适配器添加一个List<T>的全部数据。并且重新唤醒适配器
	 * 
	 * @param list
	 */
	public void addList(List<T> list)
	{
		if (myList == null)
		{
			myList = new ArrayList<T>();
		}
		myList.addAll(list);// 此方法是将传进来的List<T>全部数据条目添加到myList<T>中
		notifyDataSetChanged();
	}

	public List<T> getList()
	{
		return myList;
	}

	/**
	 * 判断适配器数据是否为空
	 * 
	 * @return
	 */
	public Boolean isNull()
	{
		return myList == null || myList.size() == 0;
	}

	@Override
	public int getCount()
	{
		return null == myList ? 0 : myList.size();
	}

	@Override
	public Object getItem(int position)
	{
		return myList.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public abstract View getView(int position, View convertView, ViewGroup arg2);

}
