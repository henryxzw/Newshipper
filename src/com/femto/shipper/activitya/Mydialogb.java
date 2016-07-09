package com.femto.shipper.activitya;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.femto.shipper.R;
import com.femto.shipper.activitya.Mypainta.onSelectListener;
import com.femto.shipper.activitya.WheelView.OnWheelChangedListener;
import com.femto.shipper.activitya.WheelView.OnWheelScrollListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

@SuppressLint("SimpleDateFormat")
public class Mydialogb extends Activity implements OnClickListener
{
	@SuppressWarnings("unused")
	private int weekmonth, weekyear, monthday, intyeara, intmonth, intday, intyear, sj, intmonthaa, week, intyearaa, jiaintday, jiede, yonga,
			intmyseconds, intmyhour, inthoura, intminutea, fourthismonthdays, intfourday, fournextmonthdays, thisyears, thismonths, thisdays,
			nextmonthdays, thismonthdays, aintyeara, maxsize = 24, minsize = 14, aintmonth, aintday, monthzga;
	private Date datea;
	private SimpleDateFormat sdfyear, sdfmonth, sdfday, sdfhour, sdfminute;
	private String yeara, montha, daya, houra, minutea, weekday, onesj, stry, strxz, strr, strzy, strze, strzs, strzsi, strzw, strzl, strzr,
			myhour = "null", myseconds = "null";
	private Context context;
	private Dialog dialog;
	private Button mdlbtna, mdlbtnb;
	private Dialogcallbackb dialogcallbackb;
	private Dateadapter dateadapter;
	@SuppressWarnings("unused")
	private Ondatelistener ondatelistener;
	private WheelView minute_pva;
	private Mypainta minute_pv, second_pv;
	private String[] datesql = new String[7];
	private ArrayList<String> arraylist = new ArrayList<String>();
	private TextView timedhtva;
	private List<String> time, seconds;
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
			intmyhour = Integer.valueOf(myhour);
			intmyseconds = Integer.valueOf(myseconds);
			if (onesj.equals(datesql[0]))
			{
				if (intmyhour > inthoura + 1)
				{
					dialogcallbackb.dialogdob(onesj, myhour, myseconds);
					dismiss();
				} else
				{
					Toast.makeText(context, strxz, Toast.LENGTH_SHORT).show();
				}
				// if (intminutea > intmyseconds)
				// {
				// intmyhour = intmyhour + 2;
				// if (intmyhour > inthoura)
				// {
				// dialogcallbackb.dialogdob(onesj, myhour, myseconds);
				// dismiss();
				// } else
				// {
				// Toast.makeText(context, "faile", Toast.LENGTH_SHORT).show();
				// }
				// } else
				// {
				// intmyhour = intmyhour + 1;
				// if (intmyhour > inthoura)
				// {
				// dialogcallbackb.dialogdob(onesj, myhour, myseconds);
				// dismiss();
				// } else
				// {
				//
				// }
				// }
			} else
			{
				dialogcallbackb.dialogdob(onesj, myhour, myseconds);
				dismiss();
			}
			break;
		}
	}

	private void cshid()
	{
		mdlbtnb = (Button) dialog.findViewById(R.id.mdlbtnb);
		mdlbtna = (Button) dialog.findViewById(R.id.mdlbtna);
		timedhtva = (TextView) dialog.findViewById(R.id.timedhtva);
		minute_pv = (Mypainta) dialog.findViewById(R.id.minute_pv);
		second_pv = (Mypainta) dialog.findViewById(R.id.second_pv);
		minute_pva = (WheelView) dialog.findViewById(R.id.minute_pva);
		// minute_pvawhbh = new LayoutParams(minute_pva.getLayoutParams());
		minute_pvawhbb = new LayoutParams(minute_pva.getLayoutParams());
		myhour = "12";
		myseconds = "30";
		timedhtva.setTextSize(25f);
		minute_pvawhbb.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		minute_pva.setLayoutParams(minute_pvawhbb);
		// minute_pv.setVisibility(View.GONE);
		// second_pv.setVisibility(View.GONE);
		// timedhtva.setTextSize(0f);
		// minute_pvawhbh.addRule(RelativeLayout.CENTER_HORIZONTAL);
		// minute_pva.setLayoutParams(minute_pvawhbh);
		time = new ArrayList<String>();
		seconds = new ArrayList<String>();
		mdlbtnb.setOnClickListener(this);
		mdlbtna.setOnClickListener(this);
		dateadapter = new Dateadapter(context, arraylist, getdateitem(onesj), maxsize, minsize);
		minute_pva.setVisibleItems(5);
		minute_pva.setViewAdapter(dateadapter);
		minute_pva.setCurrentItem(getdateitem(onesj));
		for (int i = 0; i < 24; i++)
		{
			time.add(i < 10 ? "0" + i : "" + i);
		}

		for (int i = 0; i < 60; i++)
		{
			seconds.add(i < 10 ? "0" + i : "" + i);
		}
		second_pv.setData(seconds);
		minute_pv.setData(time);
		minute_pva.addChangingListener(new OnWheelChangedListener()
		{

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue)
			{

				String currentText = (String) dateadapter.getItemText(wheel.getCurrentItem());
				// if (currentText.trim().equals(strxz))
				// {
				// minute_pv.setVisibility(View.GONE);
				// second_pv.setVisibility(View.GONE);
				// timedhtva.setTextSize(0f);
				// minute_pvawhbh.addRule(RelativeLayout.CENTER_HORIZONTAL);
				// minute_pva.setLayoutParams(minute_pvawhbh);
				// myhour = "null";
				// myseconds = "null";
				// } else
				// {
				// minute_pv.setVisibility(View.VISIBLE);
				// second_pv.setVisibility(View.VISIBLE);
				// myhour = "12";
				// myseconds = "30";
				// timedhtva.setTextSize(25f);
				// minute_pvawhbb.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				// minute_pva.setLayoutParams(minute_pvawhbb);
				// }
				onesj = currentText;
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
		minute_pv.setOnSelectListener(new onSelectListener()
		{

			@Override
			public void onSelect(String text)
			{
				myhour = text;
			}
		});
		second_pv.setOnSelectListener(new onSelectListener()
		{
			@Override
			public void onSelect(String text)
			{
				myseconds = text;
			}
		});
	}

	private class Dateadapter extends AbstractWheelTextAdapter
	{
		ArrayList<String> list;

		protected Dateadapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize)
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

	public void setTextviewSize(String curriteItemText, Dateadapter adapter)
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
		// datesql[0] = strxz;
		datea = new Date(System.currentTimeMillis());
		sdfyear = new SimpleDateFormat("yyyy");
		yeara = sdfyear.format(datea);
		sdfmonth = new SimpleDateFormat("MM");
		montha = sdfmonth.format(datea);
		sdfday = new SimpleDateFormat("dd");
		daya = sdfday.format(datea);
		sdfhour = new SimpleDateFormat("HH");
		houra = sdfhour.format(datea);
		inthoura = Integer.valueOf(houra);
		sdfminute = new SimpleDateFormat("mm");
		minutea = sdfminute.format(datea);
		intminutea = Integer.valueOf(minutea);
		intyeara = Integer.valueOf(yeara);
		intmonth = Integer.valueOf(montha);
		intday = Integer.valueOf(daya);
		intyear = intyeara % 100;
		sj = intyeara / 100;
		int sdaysafter = intday + 6;
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
						thismonthdays = 7 - nextmonthdays;
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
							// datesql[i + thismonthdays] = (thismonths + stry +
							// thisdays + strr + weekday);
							datesql[i + thismonthdays - 1] = (thismonths + stry + thisdays + strr + weekday);
						}
					} else
					{
						nextmonthdays = sdaysafter - monthzga;
						thismonthdays = 7 - nextmonthdays;
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
					thismonthdays = 7 - nextmonthdays;
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
			} else
			{
				if (intmonth % 2 == 0)
				{
					nextmonthdays = sdaysafter - monthzga;
					thismonthdays = 7 - nextmonthdays;
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
					thismonthdays = 7 - nextmonthdays;
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