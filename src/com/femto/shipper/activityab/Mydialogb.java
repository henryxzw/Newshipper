package com.femto.shipper.activityab;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import com.femto.shipper.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

@SuppressLint("SimpleDateFormat")
public class Mydialogb extends Activity implements OnClickListener
{
	@SuppressWarnings("unused")
	private int weekmonth, weekyear, monthday, intyeara, intmonth, intday, intyear, sj, intmonthaa, week, intyearaa, jiaintday, jiede, yonga,
			intmyseconds, intmyhour, inthoura, intminutea, fourthismonthdays, intfourday, fournextmonthdays, thisyears, thismonths, thisdays,
			nextmonthdays, thismonthdays, aintyeara, maxsize = 24, minsize = 14, aintmonth, aintday, monthzga;
	// private Date datea;
	private SimpleDateFormat sdfyear, sdfmonth, sdfday, sdfhour, sdfminute;
	private String yeara, montha, daya, houra, minutea, weekday, onesj, stry, strxz, strr, strzy, strze, strzs, strzsi, strzw, strzl, strzr,
			myhour = "null", myseconds = "null";
	private Context context;
	private Dialog dialog;
	private Button mdlbtna, mdlbtnb;
	private Dialogcallbackb dialogcallbackb;
	private Mydateadapter dateadapter;
	private Mytimeadapter timeadapter;
	private Mysecondadapter secondadapter;
	@SuppressWarnings("unused")
	private Ondatelistener ondatelistener;
	private WheelView minute_pva, minute_pv, second_pv;
	private String[] datesql = new String[5];
	private ArrayList<String> arraylist, time, seconds;
	private TextView timedhtva;
	private LayoutParams minute_pvawhbb;

	public Mydialogb(Context con, String strxz, String stry, String strr, String strzy, String strze, String strzs, String strzsi, String strzw,
			String strzl, String strzr)
	{
		this.context = con;
		this.strxz = strxz;
		this.stry = stry;
		this.strr = strr;
		this.strzy = strzy;
		this.strze = strze;
		this.strzs = strzs;
		this.strzsi = strzsi;
		this.strzw = strzw;
		this.strzl = strzl;
		this.strzr = strzr;
		cshsj();
		initProvinces();
		onesj = datesql[0];
		dialog = new Dialog(context, R.style.mydialoga);
		dialog.setContentView(R.layout.mydialogb);
		dialog.setCanceledOnTouchOutside(false);
		cshid();

	}

	@Override
	public void onClick(View arg0)
	{
		switch (arg0.getId())
		{
		case R.id.mdlbtna:
			dismiss();
			break;
		case R.id.mdlbtnb:
			dialogcallbackb.dialogdob(onesj, myhour, myseconds);
			dismiss();
			break;
		}
	}

	private void cshid()
	{
		mdlbtnb = (Button) dialog.findViewById(R.id.mdlbtnb);
		mdlbtna = (Button) dialog.findViewById(R.id.mdlbtna);
		timedhtva = (TextView) dialog.findViewById(R.id.timedhtva);
		minute_pv = (WheelView) dialog.findViewById(R.id.minute_pv);
		second_pv = (WheelView) dialog.findViewById(R.id.second_pv);
		minute_pva = (WheelView) dialog.findViewById(R.id.minute_pva);
		minute_pvawhbb = new LayoutParams(minute_pva.getLayoutParams());
		// myhour = "12";
		// myseconds = "30";
		timedhtva.setTextSize(25f);
		minute_pvawhbb.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		minute_pva.setLayoutParams(minute_pvawhbb);
		time = new ArrayList<String>();
		seconds = new ArrayList<String>();
		mdlbtnb.setOnClickListener(this);
		mdlbtna.setOnClickListener(this);
		dateadapter = new Mydateadapter(context, arraylist, getdateitem(onesj), maxsize, minsize);
		minute_pva.setVisibleItems(5);
		minute_pva.setViewAdapter(dateadapter);
		minute_pva.setCurrentItem(getdateitem(onesj));

		// String[] timesqla = new String[24 - inthoura + 1];
		// for (int i = inthoura; i < 24; i++)
		// {
		// // timesqla[]=
		// }
		// int timelength = timesqla.length;
		// for (int i = 0; i < timelength; i++)
		// {
		// arraylist.add(timesqla[i]);
		// }

		for (int i = inthoura; i < 24; i++)
		{
			time.add(i < 10 ? "0" + i : "" + i);
		}
		if (inthoura < 10)
		{
			myhour = "0" + inthoura;
			timeadapter = new Mytimeadapter(context, time, gettimeitem("0" + inthoura), maxsize, minsize);
			minute_pv.setVisibleItems(5);
			minute_pv.setViewAdapter(timeadapter);
			minute_pv.setCurrentItem(gettimeitem("0" + inthoura));
		} else
		{
			myhour = "" + inthoura;
			timeadapter = new Mytimeadapter(context, time, gettimeitem("" + inthoura), maxsize, minsize);
			minute_pv.setVisibleItems(5);
			minute_pv.setViewAdapter(timeadapter);
			minute_pv.setCurrentItem(gettimeitem("" + inthoura));
		}
		for (int i = intminutea; i < 60; i++)
		{
			seconds.add(i < 10 ? "0" + i : "" + i);
		}
		if (intminutea < 10)
		{
			myseconds = "0" + intminutea;
			secondadapter = new Mysecondadapter(context, seconds, getseconditem("0" + intminutea), maxsize, minsize);
			second_pv.setVisibleItems(5);
			second_pv.setViewAdapter(secondadapter);
			second_pv.setCurrentItem(getseconditem("0" + intminutea));
		} else
		{
			myseconds = "" + intminutea;
			secondadapter = new Mysecondadapter(context, seconds, getseconditem("" + intminutea), maxsize, minsize);
			second_pv.setVisibleItems(5);
			second_pv.setViewAdapter(secondadapter);
			second_pv.setCurrentItem(getseconditem("" + intminutea));
		}
		second_pv.addChangingListener(new OnWheelChangedListener()
		{

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue)
			{

				String currentText = (String) secondadapter.getItemText(wheel.getCurrentItem());
				myseconds = currentText;
				setTextviewSizeb(currentText, secondadapter);

			}
		});
		second_pv.addScrollingListener(new OnWheelScrollListener()
		{
			@Override
			public void onScrollingStarted(WheelView wheel)
			{
			}

			@Override
			public void onScrollingFinished(WheelView wheel)
			{
				String currentText = (String) secondadapter.getItemText(wheel.getCurrentItem());
				setTextviewSizeb(currentText, secondadapter);
			}
		});

		minute_pv.addChangingListener(new OnWheelChangedListener()
		{

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue)
			{

				String currentText = (String) timeadapter.getItemText(wheel.getCurrentItem());
				myhour = currentText;
				String inthourab = "" + inthoura;
				if (inthoura < 10)
				{
					inthourab = "0" + inthoura;
				}
				if (onesj.equals(datesql[0]) && myhour.equals(inthourab))
				{
					seconds.clear();
					for (int i = intminutea; i < 60; i++)
					{
						seconds.add(i < 10 ? "0" + i : "" + i);
					}
					if (intminutea < 10)
					{
						myseconds = "0" + intminutea;
						secondadapter = new Mysecondadapter(context, seconds, getseconditem("0" + intminutea), maxsize, minsize);
						second_pv.setVisibleItems(5);
						second_pv.setViewAdapter(secondadapter);
						second_pv.setCurrentItem(getseconditem("0" + intminutea));
					} else
					{
						myseconds = "" + intminutea;
						secondadapter = new Mysecondadapter(context, seconds, getseconditem("" + intminutea), maxsize, minsize);
						second_pv.setVisibleItems(5);
						second_pv.setViewAdapter(secondadapter);
						second_pv.setCurrentItem(getseconditem("" + intminutea));
					}
				} else
				{
					seconds.clear();
					for (int i = 0; i < 60; i++)
					{
						seconds.add(i < 10 ? "0" + i : "" + i);
					}
					myseconds = "00";
					secondadapter = new Mysecondadapter(context, seconds, getseconditem("00"), maxsize, minsize);
					second_pv.setVisibleItems(5);
					second_pv.setViewAdapter(secondadapter);
					second_pv.setCurrentItem(getseconditem("00"));
				}
				setTextviewSizea(currentText, timeadapter);

			}
		});
		minute_pv.addScrollingListener(new OnWheelScrollListener()
		{
			@Override
			public void onScrollingStarted(WheelView wheel)
			{
			}

			@Override
			public void onScrollingFinished(WheelView wheel)
			{
				String currentText = (String) timeadapter.getItemText(wheel.getCurrentItem());
				setTextviewSizea(currentText, timeadapter);
			}
		});

		minute_pva.addChangingListener(new OnWheelChangedListener()
		{

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue)
			{

				String currentText = (String) dateadapter.getItemText(wheel.getCurrentItem());
				onesj = currentText;
				if (onesj.equals(datesql[0]))
				{
					time.clear();
					for (int i = inthoura; i < 24; i++)
					{
						time.add(i < 10 ? "0" + i : "" + i);
					}
					if (inthoura < 10)
					{
						myhour = "0" + inthoura;
						timeadapter = new Mytimeadapter(context, time, gettimeitem("0" + inthoura), maxsize, minsize);
						minute_pv.setVisibleItems(5);
						minute_pv.setViewAdapter(timeadapter);
						minute_pv.setCurrentItem(gettimeitem("0" + inthoura));
					} else
					{
						myhour = "" + inthoura;
						timeadapter = new Mytimeadapter(context, time, gettimeitem("" + inthoura), maxsize, minsize);
						minute_pv.setVisibleItems(5);
						minute_pv.setViewAdapter(timeadapter);
						minute_pv.setCurrentItem(gettimeitem("" + inthoura));
					}
					seconds.clear();
					for (int i = intminutea; i < 60; i++)
					{
						seconds.add(i < 10 ? "0" + i : "" + i);
					}
					if (intminutea < 10)
					{
						myseconds = "0" + intminutea;
						secondadapter = new Mysecondadapter(context, seconds, getseconditem("0" + intminutea), maxsize, minsize);
						second_pv.setVisibleItems(5);
						second_pv.setViewAdapter(secondadapter);
						second_pv.setCurrentItem(getseconditem("0" + intminutea));
					} else
					{
						myseconds = "" + intminutea;
						secondadapter = new Mysecondadapter(context, seconds, getseconditem("" + intminutea), maxsize, minsize);
						second_pv.setVisibleItems(5);
						second_pv.setViewAdapter(secondadapter);
						second_pv.setCurrentItem(getseconditem("" + intminutea));
					}
				} else
				{
					time.clear();
					for (int i = 0; i < 24; i++)
					{
						time.add(i < 10 ? "0" + i : "" + i);
					}
					myhour = "00";
					timeadapter = new Mytimeadapter(context, time, gettimeitem("00"), maxsize, minsize);
					minute_pv.setVisibleItems(5);
					minute_pv.setViewAdapter(timeadapter);
					minute_pv.setCurrentItem(gettimeitem("00"));
					seconds.clear();
					for (int i = 0; i < 60; i++)
					{
						seconds.add(i < 10 ? "0" + i : "" + i);
					}
					myseconds = "00";
					secondadapter = new Mysecondadapter(context, seconds, getseconditem("00"), maxsize, minsize);
					second_pv.setVisibleItems(5);
					second_pv.setViewAdapter(secondadapter);
					second_pv.setCurrentItem(getseconditem("00"));
				}
				setTextviewSize(currentText, dateadapter);

			}
		});
		minute_pva.addScrollingListener(new OnWheelScrollListener()
		{
			@Override
			public void onScrollingStarted(WheelView wheel)
			{
			}

			@Override
			public void onScrollingFinished(WheelView wheel)
			{
				String currentText = (String) dateadapter.getItemText(wheel.getCurrentItem());
				setTextviewSize(currentText, dateadapter);
			}
		});
	}

	private class Mysecondadapter extends AbstractWheelTextAdapter
	{
		ArrayList<String> list;

		protected Mysecondadapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize)
		{
			super(context, R.layout.item_birth_yeara, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempvaluea);
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

	private class Mytimeadapter extends AbstractWheelTextAdapter
	{
		ArrayList<String> list;

		protected Mytimeadapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize)
		{
			super(context, R.layout.item_birth_yeara, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempvaluea);
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

	private class Mydateadapter extends AbstractWheelTextAdapter
	{
		ArrayList<String> list;

		protected Mydateadapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize)
		{
			super(context, R.layout.item_birth_yeara, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempvaluea);
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

	public void setTextviewSize(String curriteItemText, Mydateadapter adapter)
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

	public void setTextviewSizea(String curriteItemText, Mytimeadapter adapter)
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

	public void setTextviewSizeb(String curriteItemText, Mysecondadapter adapter)
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

	public void setdate(String date)
	{
		if (date != null && date.length() > 0)
		{
			this.onesj = date;
		}
	}

	public int getseconditem(String date)
	{
		int size = seconds.size();
		int dateindex = 0;
		for (int i = 0; i < size; i++)
		{
			if (date.equals(seconds.get(i)))
			{
				return dateindex;
			} else
			{
				dateindex++;
			}
		}
		return dateindex;
	}

	public int gettimeitem(String date)
	{
		int size = time.size();
		int dateindex = 0;
		for (int i = 0; i < size; i++)
		{
			if (date.equals(time.get(i)))
			{
				return dateindex;
			} else
			{
				dateindex++;
			}
		}
		return dateindex;
	}

	public int getdateitem(String date)
	{
		int size = arraylist.size();
		int dateindex = 0;
		boolean nodate = true;
		for (int i = 0; i < size; i++)
		{
			if (date.equals(arraylist.get(i)))
			{
				nodate = false;
				return dateindex;
			} else
			{
				dateindex++;
			}
		}
		if (nodate)
		{
			onesj = strxz;
			return 22;
		}
		return dateindex;
	}

	public void setOndatelistener(Ondatelistener ondatelistener)
	{
		this.ondatelistener = ondatelistener;
	}

	public interface Ondatelistener
	{
		public void onClick(String province);
	}

	public interface Dialogcallbackb
	{
		public void dialogdob(String a, String b, String c);
	}

	public void setDialogCallbackb(Dialogcallbackb dialogcallbackb)
	{
		this.dialogcallbackb = dialogcallbackb;

	}

	public void show()
	{
		dialog.show();
	}

	public void hide()
	{
		dialog.hide();
	}

	public void dismiss()
	{
		dialog.dismiss();
	}

	public void initProvinces()
	{
		int length = datesql.length;
		for (int i = 0; i < length; i++)
		{
			arraylist.add(datesql[i]);
		}
	}

	private void cshsj()
	{
		arraylist = new ArrayList<String>();
		sdfhour = new SimpleDateFormat("HH");
		sdfyear = new SimpleDateFormat("yyyy");
		sdfmonth = new SimpleDateFormat("MM");
		sdfday = new SimpleDateFormat("dd");
		sdfminute = new SimpleDateFormat("mm");
		Calendar cal = Calendar.getInstance();
		// cal.set(Calendar.YEAR, 2017);
		// cal.set(Calendar.MONTH, 0);
		// cal.set(Calendar.DAY_OF_MONTH, 20);
		// cal.set(Calendar.HOUR_OF_DAY, 02);
		// cal.set(Calendar.MINUTE, 10);
		cal.setTime(new Date(cal.getTime().getTime() + 1000 * 60 * 60 * 2));
		daya = sdfday.format(cal.getTime());
		montha = sdfmonth.format(cal.getTime());
		yeara = sdfyear.format(cal.getTime());
		houra = sdfhour.format(cal.getTime());
		minutea = sdfminute.format(cal.getTime());
		inthoura = Integer.valueOf(houra);
		intminutea = Integer.valueOf(minutea);
		intyeara = Integer.valueOf(yeara);
		intmonth = Integer.valueOf(montha);
		intday = Integer.valueOf(daya);
		intyear = intyeara % 100;
		sj = intyeara / 100;
		int sdaysafter = intday + 4;
		if (intmonth > 7)
		{
			if (intmonth % 2 == 0)
			{
				monthzga = 31;
			} else
			{
				monthzga = 30;
			}
		} else
		{
			if (intmonth % 2 == 0)
			{
				if (intmonth == 2)
				{
					if (intyeara % 4 == 0)
					{
						monthzga = 29;
					} else
					{
						monthzga = 28;
					}
				} else
				{
					monthzga = 30;
				}
			} else
			{
				monthzga = 31;
			}
		}
		if (sdaysafter > monthzga)
		{
			if (intmonth > 7)
			{
				if (intmonth % 2 == 0)
				{
					if (intmonth == 12)
					{
						nextmonthdays = sdaysafter - monthzga;
						thismonthdays = 5 - nextmonthdays;
						for (int i = 0; i < thismonthdays; i++)
						{
							thisdays = intday + i;
							thismonths = intmonth;
							thisyears = intyear;
							if (thismonths == 1)
							{
								weekmonth = 13;
								weekyear = thisyears - 1;
							} else if (thismonths == 2)
							{
								weekmonth = 14;
								weekyear = thisyears - 1;
							} else if (thismonths > 2)
							{
								weekmonth = thismonths;
								weekyear = thisyears;
							}
							week = (weekyear + (weekyear / 4) + (sj / 4) - 2 * sj + (13 * (weekmonth + 1) / 5) + thisdays - 1) % 7;
							if (week < 0)
							{
								week = week + 7;
							}
							if (week == 0)
							{
								weekday = strzr;
							} else if (week == 1)
							{
								weekday = strzy;
							} else if (week == 2)
							{
								weekday = strze;
							} else if (week == 3)
							{
								weekday = strzs;
							} else if (week == 4)
							{
								weekday = strzsi;
							} else if (week == 5)
							{
								weekday = strzw;
							} else if (week == 6)
							{
								weekday = strzl;
							}
							datesql[i] = (thismonths + stry + thisdays + strr + weekday);
						}
						for (int i = 1; i < nextmonthdays + 1; i++)
						{
							thisdays = i;
							thismonths = 1;
							thisyears = intyear + 1;
							if (thismonths == 1)
							{
								weekmonth = 13;
								weekyear = thisyears - 1;
							} else if (thismonths == 2)
							{
								weekmonth = 14;
								weekyear = thisyears - 1;
							} else if (thismonths > 2)
							{
								weekmonth = thismonths;
								weekyear = thisyears;
							}
							week = (weekyear + (weekyear / 4) + (sj / 4) - 2 * sj + (13 * (weekmonth + 1) / 5) + thisdays - 1) % 7;
							if (week < 0)
							{
								week = week + 7;
							}
							if (week == 0)
							{
								weekday = strzr;
							} else if (week == 1)
							{
								weekday = strzy;
							} else if (week == 2)
							{
								weekday = strze;
							} else if (week == 3)
							{
								weekday = strzs;
							} else if (week == 4)
							{
								weekday = strzsi;
							} else if (week == 5)
							{
								weekday = strzw;
							} else if (week == 6)
							{
								weekday = strzl;
							}
							datesql[i + thismonthdays - 1] = (thismonths + stry + thisdays + strr + weekday);
						}
					} else
					{
						nextmonthdays = sdaysafter - monthzga;
						thismonthdays = 5 - nextmonthdays;
						for (int i = 0; i < thismonthdays; i++)
						{
							thisdays = intday + i;
							thismonths = intmonth;
							thisyears = intyear;
							if (thismonths == 1)
							{
								weekmonth = 13;
								weekyear = thisyears - 1;
							} else if (thismonths == 2)
							{
								weekmonth = 14;
								weekyear = thisyears - 1;
							} else if (thismonths > 2)
							{
								weekmonth = thismonths;
								weekyear = thisyears;
							}
							week = (weekyear + (weekyear / 4) + (sj / 4) - 2 * sj + (13 * (weekmonth + 1) / 5) + thisdays - 1) % 7;
							if (week < 0)
							{
								week = week + 7;
							}
							if (week == 0)
							{
								weekday = strzr;
							} else if (week == 1)
							{
								weekday = strzy;
							} else if (week == 2)
							{
								weekday = strze;
							} else if (week == 3)
							{
								weekday = strzs;
							} else if (week == 4)
							{
								weekday = strzsi;
							} else if (week == 5)
							{
								weekday = strzw;
							} else if (week == 6)
							{
								weekday = strzl;
							}
							// datesql[i + 1] = (thismonths + stry + thisdays +
							// strr + weekday);
							datesql[i] = (thismonths + stry + thisdays + strr + weekday);
						}
						for (int i = 1; i < nextmonthdays + 1; i++)
						{
							thisdays = i;
							thismonths = intmonth + 1;
							thisyears = intyear;
							if (thismonths == 1)
							{
								weekmonth = 13;
								weekyear = thisyears - 1;
							} else if (thismonths == 2)
							{
								weekmonth = 14;
								weekyear = thisyears - 1;
							} else if (thismonths > 2)
							{
								weekmonth = thismonths;
								weekyear = thisyears;
							}
							week = (weekyear + (weekyear / 4) + (sj / 4) - 2 * sj + (13 * (weekmonth + 1) / 5) + thisdays - 1) % 7;
							if (week < 0)
							{
								week = week + 7;
							}
							if (week == 0)
							{
								weekday = strzr;
							} else if (week == 1)
							{
								weekday = strzy;
							} else if (week == 2)
							{
								weekday = strze;
							} else if (week == 3)
							{
								weekday = strzs;
							} else if (week == 4)
							{
								weekday = strzsi;
							} else if (week == 5)
							{
								weekday = strzw;
							} else if (week == 6)
							{
								weekday = strzl;
							}
							// datesql[i + thismonthdays] = (thismonths + stry +
							// thisdays + strr + weekday);
							datesql[i + thismonthdays - 1] = (thismonths + stry + thisdays + strr + weekday);
						}
					}
				} else
				{
					nextmonthdays = sdaysafter - monthzga;
					thismonthdays = 5 - nextmonthdays;
					for (int i = 0; i < thismonthdays; i++)
					{
						thisdays = intday + i;
						thismonths = intmonth;
						thisyears = intyear;
						if (thismonths == 1)
						{
							weekmonth = 13;
							weekyear = thisyears - 1;
						} else if (thismonths == 2)
						{
							weekmonth = 14;
							weekyear = thisyears - 1;
						} else if (thismonths > 2)
						{
							weekmonth = thismonths;
							weekyear = thisyears;
						}
						week = (weekyear + (weekyear / 4) + (sj / 4) - 2 * sj + (13 * (weekmonth + 1) / 5) + thisdays - 1) % 7;
						if (week < 0)
						{
							week = week + 7;
						}
						if (week == 0)
						{
							weekday = strzr;
						} else if (week == 1)
						{
							weekday = strzy;
						} else if (week == 2)
						{
							weekday = strze;
						} else if (week == 3)
						{
							weekday = strzs;
						} else if (week == 4)
						{
							weekday = strzsi;
						} else if (week == 5)
						{
							weekday = strzw;
						} else if (week == 6)
						{
							weekday = strzl;
						}
						datesql[i] = (thismonths + stry + thisdays + strr + weekday);
					}
					for (int i = 1; i < nextmonthdays + 1; i++)
					{
						thisdays = i;
						thismonths = intmonth + 1;
						thisyears = intyear;
						if (thismonths == 1)
						{
							weekmonth = 13;
							weekyear = thisyears - 1;
						} else if (thismonths == 2)
						{
							weekmonth = 14;
							weekyear = thisyears - 1;
						} else if (thismonths > 2)
						{
							weekmonth = thismonths;
							weekyear = thisyears;
						}
						week = (weekyear + (weekyear / 4) + (sj / 4) - 2 * sj + (13 * (weekmonth + 1) / 5) + thisdays - 1) % 7;
						if (week < 0)
						{
							week = week + 7;
						}
						if (week == 0)
						{
							weekday = strzr;
						} else if (week == 1)
						{
							weekday = strzy;
						} else if (week == 2)
						{
							weekday = strze;
						} else if (week == 3)
						{
							weekday = strzs;
						} else if (week == 4)
						{
							weekday = strzsi;
						} else if (week == 5)
						{
							weekday = strzw;
						} else if (week == 6)
						{
							weekday = strzl;
						}
						// datesql[i + thismonthdays] = (thismonths + stry +
						// thisdays + strr + weekday);
						datesql[i + thismonthdays - 1] = (thismonths + stry + thisdays + strr + weekday);
					}
				}
			} else
			{
				if (intmonth % 2 == 0)
				{
					nextmonthdays = sdaysafter - monthzga;
					thismonthdays = 5 - nextmonthdays;
					for (int i = 0; i < thismonthdays; i++)
					{
						thisdays = intday + i;
						thismonths = intmonth;
						thisyears = intyear;
						if (thismonths == 1)
						{
							weekmonth = 13;
							weekyear = thisyears - 1;
						} else if (thismonths == 2)
						{
							weekmonth = 14;
							weekyear = thisyears - 1;
						} else if (thismonths > 2)
						{
							weekmonth = thismonths;
							weekyear = thisyears;
						}
						week = (weekyear + (weekyear / 4) + (sj / 4) - 2 * sj + (13 * (weekmonth + 1) / 5) + thisdays - 1) % 7;
						if (week < 0)
						{
							week = week + 7;
						}
						if (week == 0)
						{
							weekday = strzr;
						} else if (week == 1)
						{
							weekday = strzy;
						} else if (week == 2)
						{
							weekday = strze;
						} else if (week == 3)
						{
							weekday = strzs;
						} else if (week == 4)
						{
							weekday = strzsi;
						} else if (week == 5)
						{
							weekday = strzw;
						} else if (week == 6)
						{
							weekday = strzl;
						}
						// datesql[i + 1] = (thismonths + stry + thisdays + strr
						// + weekday);
						datesql[i] = (thismonths + stry + thisdays + strr + weekday);
					}
					for (int i = 1; i < nextmonthdays + 1; i++)
					{
						thisdays = i;
						thismonths = intmonth + 1;
						thisyears = intyear;
						if (thismonths == 1)
						{
							weekmonth = 13;
							weekyear = thisyears - 1;
						} else if (thismonths == 2)
						{
							weekmonth = 14;
							weekyear = thisyears - 1;
						} else if (thismonths > 2)
						{
							weekmonth = thismonths;
							weekyear = thisyears;
						}
						week = (weekyear + (weekyear / 4) + (sj / 4) - 2 * sj + (13 * (weekmonth + 1) / 5) + thisdays - 1) % 7;
						if (week < 0)
						{
							week = week + 7;
						}
						if (week == 0)
						{
							weekday = strzr;
						} else if (week == 1)
						{
							weekday = strzy;
						} else if (week == 2)
						{
							weekday = strze;
						} else if (week == 3)
						{
							weekday = strzs;
						} else if (week == 4)
						{
							weekday = strzsi;
						} else if (week == 5)
						{
							weekday = strzw;
						} else if (week == 6)
						{
							weekday = strzl;
						}
						// datesql[i + thismonthdays] = (thismonths + stry +
						// thisdays + strr + weekday);
						datesql[i + thismonthdays - 1] = (thismonths + stry + thisdays + strr + weekday);
					}
				} else
				{
					nextmonthdays = sdaysafter - monthzga;
					thismonthdays = 5 - nextmonthdays;
					for (int i = 0; i < thismonthdays; i++)
					{
						thisdays = intday + i;
						thismonths = intmonth;
						thisyears = intyear;
						if (thismonths == 1)
						{
							weekmonth = 13;
							weekyear = thisyears - 1;
						} else if (thismonths == 2)
						{
							weekmonth = 14;
							weekyear = thisyears - 1;
						} else if (thismonths > 2)
						{
							weekmonth = thismonths;
							weekyear = thisyears;
						}
						week = (weekyear + (weekyear / 4) + (sj / 4) - 2 * sj + (13 * (weekmonth + 1) / 5) + thisdays - 1) % 7;
						if (week < 0)
						{
							week = week + 7;
						}
						if (week == 0)
						{
							weekday = strzr;
						} else if (week == 1)
						{
							weekday = strzy;
						} else if (week == 2)
						{
							weekday = strze;
						} else if (week == 3)
						{
							weekday = strzs;
						} else if (week == 4)
						{
							weekday = strzsi;
						} else if (week == 5)
						{
							weekday = strzw;
						} else if (week == 6)
						{
							weekday = strzl;
						}
						// datesql[i + 1] = (thismonths + stry + thisdays + strr
						// + weekday);
						datesql[i] = (thismonths + stry + thisdays + strr + weekday);
					}
					for (int i = 1; i < nextmonthdays + 1; i++)
					{
						thisdays = i;
						thismonths = intmonth + 1;
						thisyears = intyear;
						if (thismonths == 1)
						{
							weekmonth = 13;
							weekyear = thisyears - 1;
						} else if (thismonths == 2)
						{
							weekmonth = 14;
							weekyear = thisyears - 1;
						} else if (thismonths > 2)
						{
							weekmonth = thismonths;
							weekyear = thisyears;
						}
						week = (weekyear + (weekyear / 4) + (sj / 4) - 2 * sj + (13 * (weekmonth + 1) / 5) + thisdays - 1) % 7;
						if (week < 0)
						{
							week = week + 7;
						}
						if (week == 0)
						{
							weekday = strzr;
						} else if (week == 1)
						{
							weekday = strzy;
						} else if (week == 2)
						{
							weekday = strze;
						} else if (week == 3)
						{
							weekday = strzs;
						} else if (week == 4)
						{
							weekday = strzsi;
						} else if (week == 5)
						{
							weekday = strzw;
						} else if (week == 6)
						{
							weekday = strzl;
						}
						// datesql[i + thismonthdays] = (thismonths + stry +
						// thisdays + strr + weekday);
						datesql[i + thismonthdays - 1] = (thismonths + stry + thisdays + strr + weekday);
					}
				}
			}
		} else
		{
			for (int i = intday; i < sdaysafter + 1; i++)
			{
				thisdays = i;
				thismonths = intmonth;
				thisyears = intyear;
				if (thismonths == 1)
				{
					weekmonth = 13;
					weekyear = thisyears - 1;
				} else if (thismonths == 2)
				{
					weekmonth = 14;
					weekyear = thisyears - 1;
				} else if (thismonths > 2)
				{
					weekmonth = thismonths;
					weekyear = thisyears;
				}
				week = (weekyear + (weekyear / 4) + (sj / 4) - 2 * sj + (13 * (weekmonth + 1) / 5) + thisdays - 1) % 7;
				if (week < 0)
				{
					week = week + 7;
				}
				if (week == 0)
				{
					weekday = strzr;
				} else if (week == 1)
				{
					weekday = strzy;
				} else if (week == 2)
				{
					weekday = strze;
				} else if (week == 3)
				{
					weekday = strzs;
				} else if (week == 4)
				{
					weekday = strzsi;
				} else if (week == 5)
				{
					weekday = strzw;
				} else if (week == 6)
				{
					weekday = strzl;
				}
				datesql[i - intday] = (thismonths + stry + thisdays + strr + weekday);
			}
		}
	}
}