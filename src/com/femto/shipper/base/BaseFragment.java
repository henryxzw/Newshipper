package com.femto.shipper.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.femto.shipper.application.DemoApplication;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.view.CustomProgressDialog;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BaseFragment extends Fragment
{

	public Context mContext;
	private CustomProgressDialog pd;
	public ImageLoader imageLoader;
	public DemoApplication application;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mContext = getActivity();
		application = DemoApplication.getInstance();
		imageLoader = ImageLoader.getInstance();
	}

	public <T> void startActivity(Class<T> class1)
	{
		startActivity(new Intent(mContext, class1));
	}

	public void showToast(String message)
	{
		Toast.makeText(mContext, message, 0).show();
	}

	/**
	 * 鏄剧ず鍔犺浇妗�
	 */
	public void showProgressDialog(String message)
	{
		if (pd == null)
		{
			pd = CustomProgressDialog.createDialog(getActivity());
			pd.setMessage(message);
		}
		pd.setCanceledOnTouchOutside(true);
		pd.show();
	}

	/**
	 * 鍙栨秷鍔犺浇妗�
	 */
	public void dismissProgressDialog()
	{
		if (pd != null)
		{
			pd.dismiss();
			pd = null;
		} else
		{
			return;
		}
	}

	// /闄愬埗璁块棶娆℃暟锛堢偣鍑诲悗,3绉掑唴璁块棶涓�娆★級
	private long lastClickTime;

	public boolean isFastDoubleClick()
	{
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 2000)
		{
			return true;
		}
		lastClickTime = time;
		return false;
	}

	public boolean areAllItemsEnabled()
	{
		return false;
	}

	public boolean isEnabled(int position)
	{
		return false;
	}
}
