package com.femto.shipper.activitya;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.femto.shipper.R;
import com.femto.shipper.activitya.Mypainta.onSelectListener;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@SuppressLint("SimpleDateFormat")
public class Mydialogc implements OnClickListener
{
	private Mypainta minute_pva;
	private List<String> arraylist;
	private Context context;
	private Dialog dialog;
	@SuppressWarnings("unused")
	private int jianday, jiedajiaday, yongb, monthday, intyeara, intmonth, intday, intyear, sj, intmonthaa, week, intyearaa, jiaintday, jiede, yonga,
			fournextmonthdays, fourthismonthdays, intfourday, thisdays, thismonthdays, thismonths, weekyear, weekmonth, thisyears, nextmonthdays,
			monthzga, aintyeara, aintmonth, aintday;
	@SuppressWarnings("unused")
	private String yeara, montha, daya, weekday, mydate = "null", myhour = "null", myseconds = "null";
	private Date datea;
	private SimpleDateFormat sdfyear, sdfmonth, sdfday;
	private Button mdlcbtna, mdlcbtnb;

	public Mydialogc(Context con)
	{
		this.context = con;
		dialog = new Dialog(context, R.style.mydialoga);
		dialog.setContentView(R.layout.mydialogc);
		minute_pva = (Mypainta) dialog.findViewById(R.id.shijianpaint);
		mdlcbtna = (Button) dialog.findViewById(R.id.mdlcbtna);
		mdlcbtnb = (Button) dialog.findViewById(R.id.mdlcbtnb);
		mdlcbtna.setOnClickListener(this);
		mdlcbtnb.setOnClickListener(this);
		arraylist = new ArrayList<String>();
		datea = new Date(System.currentTimeMillis());
		sdfyear = new SimpleDateFormat("yyyy");
		yeara = sdfyear.format(datea);
		sdfmonth = new SimpleDateFormat("MM");
		montha = sdfmonth.format(datea);
		sdfday = new SimpleDateFormat("dd");
		daya = sdfday.format(datea);
		intyeara = Integer.valueOf(yeara);
		intmonth = Integer.valueOf(montha);
		intday = Integer.valueOf(daya);
		intyear = intyeara % 100;
		sj = intyeara / 100;
		// �жϵ�ǰʱ��������м���
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
		// 7���ĺ���
		int sdaysafter = intday + 6;
		// 4���ĺ���
		int fdaysafter = intday + 4;
		if (fdaysafter > monthzga)
		{
			fournextmonthdays = fdaysafter - monthzga;
			fourthismonthdays = 4 - fournextmonthdays;
			intfourday = fournextmonthdays;
		} else
		{
			intfourday = fdaysafter;
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
								weekday = "����";
							} else if (week == 1)
							{
								weekday = "��һ";
							} else if (week == 2)
							{
								weekday = "�ܶ�";
							} else if (week == 3)
							{
								weekday = "����";
							} else if (week == 4)
							{
								weekday = "����";
							} else if (week == 5)
							{
								weekday = "����";
							} else if (week == 6)
							{
								weekday = "����";
							}
							if (thisdays == intfourday)
							{
								arraylist.add("����");
							}
							arraylist.add(thismonths + "��" + thisdays + "��" + weekday);
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
								weekday = "����";
							} else if (week == 1)
							{
								weekday = "��һ";
							} else if (week == 2)
							{
								weekday = "�ܶ�";
							} else if (week == 3)
							{
								weekday = "����";
							} else if (week == 4)
							{
								weekday = "����";
							} else if (week == 5)
							{
								weekday = "����";
							} else if (week == 6)
							{
								weekday = "����";
							}
							if (thisdays == intfourday)
							{
								arraylist.add("����");
							}
							arraylist.add(thismonths + "��" + thisdays + "��" + weekday);
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
								weekday = "����";
							} else if (week == 1)
							{
								weekday = "��һ";
							} else if (week == 2)
							{
								weekday = "�ܶ�";
							} else if (week == 3)
							{
								weekday = "����";
							} else if (week == 4)
							{
								weekday = "����";
							} else if (week == 5)
							{
								weekday = "����";
							} else if (week == 6)
							{
								weekday = "����";
							}
							if (thisdays == intfourday)
							{
								arraylist.add("����");
							}
							arraylist.add(thismonths + "��" + thisdays + "��" + weekday);
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
								weekday = "����";
							} else if (week == 1)
							{
								weekday = "��һ";
							} else if (week == 2)
							{
								weekday = "�ܶ�";
							} else if (week == 3)
							{
								weekday = "����";
							} else if (week == 4)
							{
								weekday = "����";
							} else if (week == 5)
							{
								weekday = "����";
							} else if (week == 6)
							{
								weekday = "����";
							}
							if (thisdays == intfourday)
							{
								arraylist.add("����");
							}
							arraylist.add(thismonths + "��" + thisdays + "��" + weekday);
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
							weekday = "����";
						} else if (week == 1)
						{
							weekday = "��һ";
						} else if (week == 2)
						{
							weekday = "�ܶ�";
						} else if (week == 3)
						{
							weekday = "����";
						} else if (week == 4)
						{
							weekday = "����";
						} else if (week == 5)
						{
							weekday = "����";
						} else if (week == 6)
						{
							weekday = "����";
						}
						if (thisdays == intfourday)
						{
							arraylist.add("����");
						}
						arraylist.add(thismonths + "��" + thisdays + "��" + weekday);
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
							weekday = "����";
						} else if (week == 1)
						{
							weekday = "��һ";
						} else if (week == 2)
						{
							weekday = "�ܶ�";
						} else if (week == 3)
						{
							weekday = "����";
						} else if (week == 4)
						{
							weekday = "����";
						} else if (week == 5)
						{
							weekday = "����";
						} else if (week == 6)
						{
							weekday = "����";
						}
						if (thisdays == intfourday)
						{
							arraylist.add("����");
						}
						arraylist.add(thismonths + "��" + thisdays + "��" + weekday);
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
							weekday = "����";
						} else if (week == 1)
						{
							weekday = "��һ";
						} else if (week == 2)
						{
							weekday = "�ܶ�";
						} else if (week == 3)
						{
							weekday = "����";
						} else if (week == 4)
						{
							weekday = "����";
						} else if (week == 5)
						{
							weekday = "����";
						} else if (week == 6)
						{
							weekday = "����";
						}
						if (thisdays == intfourday)
						{
							arraylist.add("����");
						}
						arraylist.add(thismonths + "��" + thisdays + "��" + weekday);
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
							weekday = "����";
						} else if (week == 1)
						{
							weekday = "��һ";
						} else if (week == 2)
						{
							weekday = "�ܶ�";
						} else if (week == 3)
						{
							weekday = "����";
						} else if (week == 4)
						{
							weekday = "����";
						} else if (week == 5)
						{
							weekday = "����";
						} else if (week == 6)
						{
							weekday = "����";
						}
						if (thisdays == intfourday)
						{
							arraylist.add("����");
						}
						arraylist.add(thismonths + "��" + thisdays + "��" + weekday);
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
							weekday = "����";
						} else if (week == 1)
						{
							weekday = "��һ";
						} else if (week == 2)
						{
							weekday = "�ܶ�";
						} else if (week == 3)
						{
							weekday = "����";
						} else if (week == 4)
						{
							weekday = "����";
						} else if (week == 5)
						{
							weekday = "����";
						} else if (week == 6)
						{
							weekday = "����";
						}
						if (thisdays == intfourday)
						{
							arraylist.add("����");
						}
						arraylist.add(thismonths + "��" + thisdays + "��" + weekday);
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
							weekday = "����";
						} else if (week == 1)
						{
							weekday = "��һ";
						} else if (week == 2)
						{
							weekday = "�ܶ�";
						} else if (week == 3)
						{
							weekday = "����";
						} else if (week == 4)
						{
							weekday = "����";
						} else if (week == 5)
						{
							weekday = "����";
						} else if (week == 6)
						{
							weekday = "����";
						}
						if (thisdays == intfourday)
						{
							arraylist.add("����");
						}
						arraylist.add(thismonths + "��" + thisdays + "��" + weekday);
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
					weekday = "����";
				} else if (week == 1)
				{
					weekday = "��һ";
				} else if (week == 2)
				{
					weekday = "�ܶ�";
				} else if (week == 3)
				{
					weekday = "����";
				} else if (week == 4)
				{
					weekday = "����";
				} else if (week == 5)
				{
					weekday = "����";
				} else if (week == 6)
				{
					weekday = "����";
				}
				if (thisdays == intfourday)
				{
					arraylist.add("����");
				}
				arraylist.add(thismonths + "��" + thisdays + "��" + weekday);
			}
		}
		minute_pva.setData(arraylist);
		minute_pva.setOnSelectListener(new onSelectListener()
		{
			@Override
			public void onSelect(String text)
			{
				// if (text.equals("����"))
				// {
				// // dialog.dismiss();
				// // Mydialogb mydialogb = new Mydialogb(context);
				// // mydialogb.show();
				// }
			}
		});
	}

	public void show()
	{
		dialog.show();
	}

	public void dismiss()
	{
		dialog.dismiss();
	}

	@Override
	public void onClick(View arg0)
	{
		switch (arg0.getId())
		{
		case R.id.mdlcbtna:
			break;
		case R.id.mdlcbtnb:
			break;
		}

	}
}