package com.femto.shipper.yue;

import java.util.ArrayList;
import java.util.Calendar;

import com.femto.shipper.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 日期选择对话框
 * 
 * @author ywl
 * 
 */
public class ChangeBirthDialog extends Dialog implements
		android.view.View.OnClickListener {

	private Context context;
	private WheelView wvMonth;

	private View vChangeBirth;
	private View vChangeBirthChild;
	private TextView btnSure, year;
	private TextView btnCancel;
	private ArrayList<String> arry_months = new ArrayList<String>();
	private CalendarTextAdapter mMonthAdapter;
	private int month;
	private int intyear;
	private int currentMonth = 1;
	private int maxTextSize = 24;
	private int minTextSize = 14;
	private boolean issetdata = false;
	private String selectMonth, selectyear;

	private OnBirthListener onBirthListener;

	public ChangeBirthDialog(Context context) {
		super(context, R.style.ShareDialog);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_myinfo_changebirth);

		wvMonth = (WheelView) findViewById(R.id.wv_birth_month);
		vChangeBirth = findViewById(R.id.ly_myinfo_changebirth);
		vChangeBirthChild = findViewById(R.id.ly_myinfo_changebirth_child);
		btnSure = (TextView) findViewById(R.id.btn_myinfo_sure);
		btnCancel = (TextView) findViewById(R.id.btn_myinfo_cancel);
		year = (TextView) findViewById(R.id.year);
		vChangeBirth.setOnClickListener(this);
		vChangeBirthChild.setOnClickListener(this);
		btnSure.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		year.setText(intyear + "");
		if (!issetdata) {
			initData();
		}

		initMonths(month);
		mMonthAdapter = new CalendarTextAdapter(context, arry_months,
				setMonth(currentMonth), maxTextSize, minTextSize);
		wvMonth.setVisibleItems(5);
		wvMonth.setViewAdapter(mMonthAdapter);
		wvMonth.setCurrentItem(setMonth(currentMonth));

		wvMonth.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				// TODO Auto-generated method stub
				String currentText = (String) mMonthAdapter.getItemText(wheel
						.getCurrentItem());
				selectMonth = currentText;
				if (Integer.valueOf(currentText) - 5 > month) {
					int newyear = intyear - 1;
					selectyear = String.valueOf(newyear);
					year.setText(newyear + "");
				} else {
					year.setText(intyear + "");
					selectyear = String.valueOf(intyear);
				}
				setTextviewSize(currentText, mMonthAdapter);
				setMonth(Integer.parseInt(currentText));

			}
		});

		wvMonth.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				// TODO Auto-generated method stub
				String currentText = (String) mMonthAdapter.getItemText(wheel
						.getCurrentItem());
				setTextviewSize(currentText, mMonthAdapter);
			}
		});

	}

	public void initMonths(int months) {
		arry_months.clear();
		if (months - 5 < 0) {
			int a = month - 5;
			int b = 0 - a;
			int c = 5 - b;
			for (int i = 12 - b + 1; i <= 12; i++) {
				arry_months.add(i + "");
			}
			for (int i = 1; i <= c; i++) {
				arry_months.add(i + "");
			}

		} else {
			for (int i = months - 4; i <= months; i++) {
				arry_months.add(i + "");
			}
		}

	}

	private class CalendarTextAdapter extends AbstractWheelTextAdapter {
		ArrayList<String> list;

		protected CalendarTextAdapter(Context context, ArrayList<String> list,
				int currentItem, int maxsize, int minsize) {
			super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem,
					maxsize, minsize);
			this.list = list;
			setItemTextResource(R.id.tempValue);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);
			return view;
		}

		@Override
		public int getItemsCount() {
			return list.size();
		}

		@Override
		protected CharSequence getItemText(int index) {
			return list.get(index) + "";
		}
	}

	public void setBirthdayListener(OnBirthListener onBirthListener) {
		this.onBirthListener = onBirthListener;
	}

	@Override
	public void onClick(View v) {

		if (v == btnSure) {
			if (onBirthListener != null) {
				onBirthListener.onClick(selectyear, selectMonth);
			}
		} else if (v == btnSure) {

		} else if (v == vChangeBirthChild) {
			return;
		} else {
			dismiss();
		}
		dismiss();

	}

	public interface OnBirthListener {
		public void onClick(String year, String month);
	}

	/**
	 * 设置字体大小
	 * 
	 * @param curriteItemText
	 * @param adapter
	 */
	public void setTextviewSize(String curriteItemText,
			CalendarTextAdapter adapter) {
		ArrayList<View> arrayList = adapter.getTestViews();
		int size = arrayList.size();
		String currentText;
		for (int i = 0; i < size; i++) {
			TextView textvew = (TextView) arrayList.get(i);
			currentText = textvew.getText().toString();
			if (curriteItemText.equals(currentText)) {
				textvew.setTextSize(maxTextSize);
			} else {
				textvew.setTextSize(minTextSize);
			}
		}
	}

	public int getYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	public int getMonth() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH) + 1;
	}

	public int getDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DATE);
	}

	public void initData() {
		setDate(getYear(), getMonth());
		this.currentMonth = 1;
	}

	public void setDate(int year, int month) {
		selectMonth = month + "";
		issetdata = true;
		this.currentMonth = month;
		this.month = month;
		this.intyear = year;
		selectyear = String.valueOf(year);
	}

	public int setMonth(int month) {
		int monthIndex = 0;
		if (this.month - 5 < 1) {
			monthIndex = 4;
		} else {
			for (int i = 1; i < this.month; i++) {
				if (month == i) {
					return monthIndex;
				} else {
					monthIndex++;
				}
			}
		}
		return monthIndex;
	}
}