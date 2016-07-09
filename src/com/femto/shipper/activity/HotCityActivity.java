package com.femto.shipper.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.city.CityAdapter;
import com.femto.shipper.city.CityData;
import com.femto.shipper.city.CityItem;
import com.femto.shipper.city.ContactItemInterface;
import com.femto.shipper.city.ContactListViewImpl;

@SuppressLint("DefaultLocale")
public class HotCityActivity extends BaseActivity implements TextWatcher,
		OnClickListener {
	private Context context_ = this;
	private ContactListViewImpl listview;
	private EditText searchBox;
	private String searchString;
	private Object searchLock = new Object();
	boolean inSearchMode = false;
	private List<ContactItemInterface> contactList;
	private List<ContactItemInterface> filterList;
	private SearchListTask curSearchTask = null;
	private SharedPreferences mySharedPreferences;
	private Editor editor = null;

	@Override
	protected void onDestroy() {
		if (editor != null) {
			editor.clear();
		}
		if (contactList != null) {
			contactList.clear();
		}
		if (filterList != null) {
			filterList.clear();
		}
		super.onDestroy();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		HotCityActivity.this.setTheme(R.style.All);
		setContentView(R.layout.activity_hot_city);
		mySharedPreferences = getSharedPreferences("dinwei_xinxi",
				Activity.MODE_PRIVATE);
		editor = mySharedPreferences.edit();
		findViewById(R.id.left).setOnClickListener(this);
		filterList = new ArrayList<ContactItemInterface>();
		contactList = CityData.getSampleContactList();
		CityAdapter adapter = new CityAdapter(this, R.layout.city_item,
				contactList);
		listview = (ContactListViewImpl) this.findViewById(R.id.listview);
		listview.setFastScrollEnabled(false);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(
					@SuppressWarnings("rawtypes") AdapterView parent, View v,
					int position, long id) {
				List<ContactItemInterface> searchList = inSearchMode ? filterList
						: contactList;
				String fuwuCity = searchList.get(position).getDisplayInfo();
				Select_City(fuwuCity);
			}
		});
		searchBox = (EditText) findViewById(R.id.input_search_query);
		searchBox.addTextChangedListener(this);
		findViewById(R.id.text_beijing).setOnClickListener(this);
		findViewById(R.id.text_shanghai).setOnClickListener(this);
		findViewById(R.id.text_guangzhou).setOnClickListener(this);
		findViewById(R.id.text_shenzhen).setOnClickListener(this);
		findViewById(R.id.text_hangzhou).setOnClickListener(this);
		findViewById(R.id.text_chengdu).setOnClickListener(this);
		findViewById(R.id.text_huizhou).setOnClickListener(this);
		findViewById(R.id.text_fosan).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left:
			finish();
			break;
		case R.id.text_beijing:
			Select_City("广州");
			break;
		case R.id.text_shanghai:
			Select_City("东莞");
			break;
		case R.id.text_guangzhou:
			Select_City("深圳");
			break;
		case R.id.text_shenzhen:
			Select_City("中山");
			break;
		case R.id.text_hangzhou:
			Select_City("江门");
			break;
		case R.id.text_chengdu:
			Select_City("珠海");
			break;
		case R.id.text_huizhou:
			Select_City("惠州");
			break;
		case R.id.text_fosan:
			Select_City("佛山");
			break;
		}
	}

	private void Select_City(String fuwuCity) {
		editor.putString("city", fuwuCity);
		editor.commit();
		Intent intent = new Intent();
		intent.putExtra("carcls", "number");
		intent.putExtra("city", fuwuCity + "市");
		this.setResult(5, intent);
		finish();
	}

	@SuppressLint("DefaultLocale")
	@Override
	public void afterTextChanged(Editable s) {
		searchString = searchBox.getText().toString().trim().toUpperCase();

		if (curSearchTask != null
				&& curSearchTask.getStatus() != AsyncTask.Status.FINISHED) {
			try {
				curSearchTask.cancel(true);
			} catch (Exception e) {

			}

		}
		curSearchTask = new SearchListTask();
		curSearchTask.execute(searchString);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// do nothing
	}

	@SuppressLint("DefaultLocale")
	private class SearchListTask extends AsyncTask<String, Void, String> {

		@SuppressLint("DefaultLocale")
		@Override
		protected String doInBackground(String... params) {
			filterList.clear();

			String keyword = params[0];

			inSearchMode = (keyword.length() > 0);

			if (inSearchMode) {
				for (ContactItemInterface item : contactList) {
					CityItem contact = (CityItem) item;

					boolean isPinyin = contact.getFullName().toUpperCase()
							.indexOf(keyword) > -1;
					boolean isChinese = contact.getNickName().indexOf(keyword) > -1;

					if (isPinyin || isChinese) {
						filterList.add(item);
					}
				}
			}
			return null;
		}

		protected void onPostExecute(String result) {
			synchronized (searchLock) {

				if (inSearchMode) {
					listview.setVisibility(View.VISIBLE);
					CityAdapter adapter = new CityAdapter(context_,
							R.layout.city_item, filterList);
					adapter.setInSearchMode(true);
					// listview.setInSearchMode(true);
					listview.setAdapter(adapter);
				} else {
					listview.setVisibility(View.GONE);
					CityAdapter adapter = new CityAdapter(context_,
							R.layout.city_item, contactList);
					adapter.setInSearchMode(false);
					// listview.setInSearchMode(false);
					listview.setAdapter(adapter);
				}
			}
		}
	}
}