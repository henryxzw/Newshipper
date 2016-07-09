package com.femto.shipper.activityab;

import java.util.ArrayList;
import com.femto.shipper.R;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Mydialogh implements OnClickListener
{
	private Context context;
	private Dialog dialog;
	private Button mdlhbtn, mdlhbtnqx;
	private WheelView mypaintabzzlxz, mypaintahwsxxz;
	private ArrayList<String> bzxzlist, sxxzlist;
	private String[] bz = new String[7];
	private String[] sx = new String[12];
	private Dialogcallbackh dialogcallbackh;
	private String bzzl = "null", hwsx = "null";
	private int maxsize = 24, minsize = 14;
	private Mypaintabzzlxzadapter mypaintabzzlxzadapter;
	private Mypaintahwsxxzadapter mypaintahwsxxzadapter;

	public Mydialogh(Context con, String kbbz, String zxbz, String mx, String jx, String tz, String sz, String qt, String nbcp, String ryp,
			String jc, String juju, String jydq, String jxsb, String dzcp, String lbj, String qph, String yshw, String hgcp)
	{
		this.context = con;
		bz[0] = kbbz;
		bz[1] = zxbz;
		bz[2] = mx;
		bz[3] = jx;
		bz[4] = tz;
		bz[5] = sz;
		bz[6] = qt;
		sx[0] = nbcp;
		sx[1] = ryp;
		sx[2] = jc;
		sx[3] = juju;
		sx[4] = jydq;
		sx[5] = jxsb;
		sx[6] = dzcp;
		sx[7] = lbj;
		sx[8] = qph;
		sx[9] = yshw;
		sx[10] = hgcp;
		sx[11] = qt;
		dialog = new Dialog(context, R.style.mydialoga);
		dialog.setContentView(R.layout.mydialogh);
		dialog.setCanceledOnTouchOutside(false);
		cshid();
		mypaintabzzlxz();
		mypaintahwsxxz();
		bzzl = bz[0];
		hwsx = sx[0];
	}

	@Override
	public void onClick(View arg0)
	{
		switch (arg0.getId())
		{
		case R.id.mdlhbtn:
			dialogcallbackh.dialogdoh(bzzl, hwsx);
			dialog.dismiss();
			break;
		case R.id.mdlhbtnqx:
			dialog.dismiss();
			break;
		}
	}

	private void cshid()
	{
		mdlhbtn = (Button) dialog.findViewById(R.id.mdlhbtn);
		mdlhbtnqx = (Button) dialog.findViewById(R.id.mdlhbtnqx);
		mypaintabzzlxz = (WheelView) dialog.findViewById(R.id.mypaintabzzlxz);
		mypaintahwsxxz = (WheelView) dialog.findViewById(R.id.mypaintahwsxxz);
		bzxzlist = new ArrayList<String>();
		sxxzlist = new ArrayList<String>();
		mdlhbtn.setOnClickListener(this);
		mdlhbtnqx.setOnClickListener(this);

		// mypaintabzzlxz.setData(bzxzlist);
		// for (int i = 0; i < sx.length; i++)
		// {
		// sxxzlist.add(sx[i]);
		// }
		// mypaintahwsxxz.setData(sxxzlist);
		// mypaintabzzlxz.setOnSelectListener(new onSelectListener()
		// {
		// @Override
		// public void onSelect(String text)
		// {
		// bzzl = text;
		// }
		// });
		// mypaintahwsxxz.setOnSelectListener(new onSelectListener()
		// {
		// @Override
		// public void onSelect(String text)
		// {
		// hwsx = text;
		// }
		// });
	}

	private void mypaintahwsxxz()
	{
		for (int i = 0; i < sx.length; i++)
		{
			sxxzlist.add(sx[i]);
		}
		mypaintahwsxxzadapter = new Mypaintahwsxxzadapter(context, sxxzlist, getsxitem(sx[0]), maxsize, minsize);
		mypaintahwsxxz.setVisibleItems(5);
		mypaintahwsxxz.setViewAdapter(mypaintahwsxxzadapter);
		mypaintahwsxxz.setCurrentItem(getsxitem(sx[0]));
		mypaintahwsxxz.addChangingListener(new OnWheelChangedListener()
		{

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue)
			{
				String text = (String) mypaintahwsxxzadapter.getItemText(wheel.getCurrentItem());
				hwsx = text;
				setTextviewaSize(text, mypaintahwsxxzadapter);
			}
		});
		mypaintahwsxxz.addScrollingListener(new OnWheelScrollListener()
		{
			@Override
			public void onScrollingFinished(WheelView wheel)
			{
				String currentText = (String) mypaintahwsxxzadapter.getItemText(wheel.getCurrentItem());
				setTextviewaSize(currentText, mypaintahwsxxzadapter);
			}

			@Override
			public void onScrollingStarted(WheelView wheel)
			{
			}
		});
	}

	private void mypaintabzzlxz()
	{
		for (int i = 0; i < bz.length; i++)
		{
			bzxzlist.add(bz[i]);
		}
		mypaintabzzlxzadapter = new Mypaintabzzlxzadapter(context, bzxzlist, getbzitem(bz[0]), maxsize, minsize);
		mypaintabzzlxz.setVisibleItems(5);
		mypaintabzzlxz.setViewAdapter(mypaintabzzlxzadapter);
		mypaintabzzlxz.setCurrentItem(getbzitem(bz[0]));
		mypaintabzzlxz.addChangingListener(new OnWheelChangedListener()
		{
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue)
			{
				String text = (String) mypaintabzzlxzadapter.getItemText(wheel.getCurrentItem());
				bzzl = text;
				setTextviewSize(text, mypaintabzzlxzadapter);
			}
		});
		mypaintabzzlxz.addScrollingListener(new OnWheelScrollListener()
		{
			@Override
			public void onScrollingFinished(WheelView wheel)
			{
				String currentText = (String) mypaintabzzlxzadapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, mypaintabzzlxzadapter);
			}

			@Override
			public void onScrollingStarted(WheelView wheel)
			{
			}
		});
	}

	public int getsxitem(String date)
	{
		int size = sxxzlist.size();
		int dateindex = 0;
		for (int i = 0; i < size; i++)
		{
			if (date.equals(sxxzlist.get(i)))
			{
				return dateindex;
			} else
			{
				dateindex++;
			}
		}
		return dateindex;
	}

	public void setTextviewaSize(String curriteItemText, Mypaintahwsxxzadapter adapter)
	{
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++)
		{
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText))
			{
				textvew.setTextSize(24);
			} else
			{
				textvew.setTextSize(14);
			}
		}
	}

	public void setTextviewSize(String curriteItemText, Mypaintabzzlxzadapter adapter)
	{
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++)
		{
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText))
			{
				textvew.setTextSize(24);
			} else
			{
				textvew.setTextSize(14);
			}
		}
	}

	public int getbzitem(String date)
	{
		int size = bzxzlist.size();
		int dateindex = 0;
		for (int i = 0; i < size; i++)
		{
			if (date.equals(bzxzlist.get(i)))
			{
				return dateindex;
			} else
			{
				dateindex++;
			}
		}
		return dateindex;
	}

	private class Mypaintahwsxxzadapter extends AbstractWheelTextAdapter
	{
		ArrayList<String> list;

		protected Mypaintahwsxxzadapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize)
		{
			super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent)
		{
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount()
		{
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index)
		{
			return list.get(index) + "";
		}
	}

	private class Mypaintabzzlxzadapter extends AbstractWheelTextAdapter
	{
		ArrayList<String> list;

		protected Mypaintabzzlxzadapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize)
		{
			super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent)
		{
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount()
		{
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index)
		{
			return list.get(index) + "";
		}
	}

	public interface Dialogcallbackh
	{
		public void dialogdoh(String a, String b);
	}

	public void setDialogCallbackh(Dialogcallbackh dialogcallbackh)
	{
		this.dialogcallbackh = dialogcallbackh;

	}

	public void show()
	{
		dialog.show();
	}

	public void dismiss()
	{
		dialog.dismiss();
	}
}
