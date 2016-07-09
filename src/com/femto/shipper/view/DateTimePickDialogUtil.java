package com.femto.shipper.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

import com.femto.shipper.R;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.ToolUtils;

/**
 * 日期时间选择控件 使用方法： private EditText inputDate;//需要设置的日期时间文本编辑框 private String
 * initDateTime="2012年9月3日 14:44",//初始日期时间值 在点击事件中使用：
 * inputDate.setOnClickListener(new OnClickListener() {
 * 
 * @Override public void onClick(View v) { DateTimePickDialogUtil
 *           dateTimePicKDialog=new
 *           DateTimePickDialogUtil(SinvestigateActivity.this,initDateTime);
 *           dateTimePicKDialog.dateTimePicKDialog(inputDate);
 * 
 *           } });
 * 
 * @author
 */
public class DateTimePickDialogUtil implements OnDateChangedListener, OnTimeChangedListener
{
	private DatePicker datePicker;
	private TimePicker timePicker;
	private AlertDialog.Builder ad;
	private String dateTime;
	private String initDateTime;
	private Activity activity;
	private int d_year;
	private int d_month;
	private int d_day;
	private int d_hour;
	private int d_min;

	/**
	 * 日期时间弹出选择框构造函数
	 * @param activity
	 *            ：调用的父activity
	 * @param initDateTime
	 *            初始日期时间值，作为弹出窗口的标题和日期时间初始值
	 */
	public DateTimePickDialogUtil(Activity activity, String initDateTime)
	{
		this.activity = activity;
		this.initDateTime = initDateTime;

	}

	public void init(DatePicker datePicker, TimePicker timePicker)
	{
		Calendar calendar = Calendar.getInstance();
		if (!(null == initDateTime || "".equals(initDateTime)))
		{
			calendar = this.getCalendarByInintData(initDateTime);
		} else
		{
			initDateTime = calendar.get(Calendar.YEAR) + "年" + calendar.get(Calendar.MONTH) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日 "
					+ calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
		}

		datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), this);
		timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
		timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
	}

	/**
	 * 弹出日期时间选择框方法
	 * 
	 * @param inputDate
	 *            :为需要设置的日期时间文本编辑框
	 * @return
	 */
	public void dateTimePicKDialog(final TextView inputDate)
	{
		LinearLayout dateTimeLayout = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.common_datetime, null);
		datePicker = (DatePicker) dateTimeLayout.findViewById(R.id.datepicker);
		timePicker = (TimePicker) dateTimeLayout.findViewById(R.id.timepicker);
		init(datePicker, timePicker);
		timePicker.setIs24HourView(true);
		timePicker.setOnTimeChangedListener(this);

		ad = new AlertDialog.Builder(activity);
		ad.setTitle(initDateTime);
		ad.setView(dateTimeLayout);
		ad.setPositiveButton("设置", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				Calendar calendar = Calendar.getInstance();
				d_year = calendar.get(Calendar.YEAR);
				d_month = calendar.get(Calendar.MONTH)+1;
				d_day = calendar.get(Calendar.DAY_OF_MONTH);
				d_hour = calendar.get(Calendar.HOUR_OF_DAY);
				d_min = calendar.get(Calendar.MINUTE);

				LogUtils.i("当前时间:" + d_year + "年" + d_month + "月" + d_day + "日" + " " + d_hour + ":" + d_min);
				boolean date1BeforeDate3 = ToolUtils.date1BeforeDate3(d_year + "年" + d_month + "月" + d_day + "日" + " " + d_hour + ":" + d_min,
						dateTime);
				if (!date1BeforeDate3)
				{
					Toast.makeText(activity, "选择的时间必须大于当前1小时", 0).show();
					return;
				} else
				{
					
					// 判断 不能大于5天的情况
					int month = datePicker.getMonth()+1;
					int dayOfMonth = datePicker.getDayOfMonth();
					Integer currentHour = timePicker.getCurrentHour();
					Integer currentMinute = timePicker.getCurrentMinute();
					LogUtils.i("控件的时间:"+month+";;"+dayOfMonth+";;"+currentHour+";;"+currentMinute);
					
					String dateAfter = ToolUtils.getDateAfter(new Date(), 5);
					boolean date1BeforeDate32 = ToolUtils.date1BeforeDate3(dateTime, dateAfter);
					if(!date1BeforeDate32){
						Toast.makeText(activity, "选择的时间不能大于5天之后", 0).show();
						return;
					}else{
						String dateHourAfter = ToolUtils.getDateHourAfter(new Date(), 1);//获取1小时之后的时间
						boolean date1BeforeDate33 = ToolUtils.date1BeforeDate3(dateTime, dateHourAfter);
						if(date1BeforeDate33){
							Toast.makeText(activity, "选择的时间必须大于当前1小时", 0).show();
							return;
						}else{
							inputDate.setText(dateTime);
							dialog.dismiss();
						}
					}
				}
				
				
				
			}
		});

		ad.setNegativeButton("取消", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int whichButton)
			{
				inputDate.setText("");
				dialog.dismiss();
			}
		});
		ad.show();
		onDateChanged(null, 0, 0, 0);
	}

	public void onTimeChanged(TimePicker view, int hourOfDay, int minute)
	{
		onDateChanged(null, 0, 0, 0);
	}

	public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth)
	{
		// 获得日历实例
		Calendar calendar = Calendar.getInstance();
		calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(),
				timePicker.getCurrentMinute());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		dateTime = sdf.format(calendar.getTime());
		ad.setTitle(dateTime);
	}

	/**
	 * 实现将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒,并赋值给calendar
	 * 
	 * @param initDateTime
	 *            初始日期时间值 字符串型
	 * @return Calendar
	 */
	private Calendar getCalendarByInintData(String initDateTime)
	{
		Calendar calendar = Calendar.getInstance();

		// 将初始日期时间2012年07月02日 16:45 拆分成年 月 日 时 分 秒
		String date = spliteString(initDateTime, "日", "index", "front"); // 日期
		String time = spliteString(initDateTime, "日", "index", "back"); // 时间

		String yearStr = spliteString(date, "年", "index", "front"); // 年份
		String monthAndDay = spliteString(date, "年", "index", "back"); // 月日

		String monthStr = spliteString(monthAndDay, "月", "index", "front"); // 月
		String dayStr = spliteString(monthAndDay, "月", "index", "back"); // 日

		String hourStr = spliteString(time, ":", "index", "front"); // 时
		String minuteStr = spliteString(time, ":", "index", "back"); // 分

		int currentYear = Integer.valueOf(yearStr.trim()).intValue();
		int currentMonth = Integer.valueOf(monthStr.trim()).intValue() - 1;
		int currentDay = Integer.valueOf(dayStr.trim()).intValue();
		int currentHour = Integer.valueOf(hourStr.trim()).intValue();
		int currentMinute = Integer.valueOf(minuteStr.trim()).intValue();

		calendar.set(currentYear, currentMonth, currentDay, currentHour, currentMinute);
		return calendar;
	}

	/**
	 * 截取子串
	 * 
	 * @param srcStr
	 *            源串
	 * @param pattern
	 *            匹配模式
	 * @param indexOrLast
	 * @param frontOrBack
	 * @return
	 */
	public static String spliteString(String srcStr, String pattern, String indexOrLast, String frontOrBack)
	{
		String result = "";
		int loc = -1;
		if (indexOrLast.equalsIgnoreCase("index"))
		{
			loc = srcStr.indexOf(pattern); // 取得字符串第一次出现的位置
		} else
		{
			loc = srcStr.lastIndexOf(pattern); // 最后一个匹配串的位置
		}
		if (frontOrBack.equalsIgnoreCase("front"))
		{
			if (loc != -1)
				result = srcStr.substring(0, loc); // 截取子串
		} else
		{
			if (loc != -1)
				result = srcStr.substring(loc + 1, srcStr.length()); // 截取子串
		}
		return result;
	}

}
