package com.femto.shipper.activity;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMConversation.EMConversationType;
import com.easemob.chatuidemo.activity.MainActivity;
import com.femto.shipper.R;
import com.femto.shipper.adapter.SysApplication;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.My_XinXiBean;
import com.femto.shipper.utils.Constants;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.femto.shipper.view.CircleImageView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * @author mac 我的空间
 */
@SuppressLint("InlinedApi")
public class MyInterspaceActivity extends BaseActivity implements
		OnClickListener {
	private TextView my_chedui_number, my_luxian_number, my_dikojuan_number,
			xiaoxi_count, my_name;
	private CircleImageView my_img_touxiang;
	private My_XinXiBean my_xinxibean;
	private SharedPreferences sharedPreferences, sharedPreferencesa;
	private String phonea, passworda, oldtimestamp, newtimestamp, nc, mobile,
			avatar, role_name, group_id, email, myclsl, mylxsl, mydkjsl;
	public static Activity myinterspaceactivity;
	private Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.AppBaseTheme);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_interspace);
		myinterspaceactivity = this;
		SysApplication.getInstance().addActivity(this);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_MULTI_PROCESS);
		sharedPreferencesa = getSharedPreferences("user_xinxi",
				Activity.MODE_MULTI_PROCESS);
		// editor = sharedPreferencesa.edit();
		my_img_touxiang = (CircleImageView) findViewById(R.id.my_img_touxiang);
		my_chedui_number = (TextView) findViewById(R.id.my_chedui_number);
		my_luxian_number = (TextView) findViewById(R.id.my_luxian_number);
		my_dikojuan_number = (TextView) findViewById(R.id.my_dikojuan_number);
		my_name = (TextView) findViewById(R.id.my_name);
		xiaoxi_count = (TextView) findViewById(R.id.xiaoxi_count);
		my_img_touxiang.setOnClickListener(this);
		findViewById(R.id.my_chedui).setOnClickListener(this);
		findViewById(R.id.my_luxian).setOnClickListener(this);
		findViewById(R.id.my_fapiao).setOnClickListener(this);
		findViewById(R.id.my_dikojuan).setOnClickListener(this);
		findViewById(R.id.my_jizhangbeng).setOnClickListener(this);
		findViewById(R.id.my_zhifu).setOnClickListener(this);
		findViewById(R.id.my_xiaoxi).setOnClickListener(this);
		findViewById(R.id.my_bangzhu).setOnClickListener(this);
		findViewById(R.id.my_setting).setOnClickListener(this);
		findViewById(R.id.left).setOnClickListener(this);
		// getDataForNet();
		// updateUnreadAddressLable();// /刷新消息
	}

	private void getDataForNet() {
		oldtimestamp = sharedPreferencesa.getString("timestamp", "").trim();
		if (oldtimestamp == null || oldtimestamp.equals("")) {
			oldtimestamp = "0";
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "user_info");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("timestamp", oldtimestamp);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.YONGHUXINXI + jiaMi;
		showProgressDialog("加载中...");
		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				LogUtils.i(arg0.result);
				dismissProgressDialog();
				my_xinxibean = GsonUtils.json2Bean(arg0.result,
						My_XinXiBean.class);
				if (my_xinxibean.status.equals("0")) {
					newtimestamp = my_xinxibean.timestamp;
					nc = my_xinxibean.nick_name;
					mobile = my_xinxibean.mobile;
					avatar = my_xinxibean.avatar;
					role_name = my_xinxibean.role_name;
					group_id = my_xinxibean.group_id;
					email = my_xinxibean.email;
					myclsl = my_xinxibean.carlist_count;
					mylxsl = my_xinxibean.myline_count;
					mydkjsl = my_xinxibean.coupon_count;
					if (!avatar.equals("")) {
						ImageLoader.getInstance().displayImage(
								Net.PICURL + avatar, my_img_touxiang,
								application.options);
						// ImageLoader.getInstance().displayImage(avatar,
						// my_img_touxiang, application.options);
					} else {
						avatar = "";
					}
					my_name.setText(nc);
					my_chedui_number.setText(myclsl + "个");
					my_luxian_number.setText(mylxsl + "条");
					my_dikojuan_number.setText(mydkjsl + "张");
					editor = sharedPreferencesa.edit();
					editor.putString("timestamp", newtimestamp);
					editor.putString("nc", nc);
					editor.putString("mobile", mobile);
					editor.putString("avatar", avatar);
					editor.putString("role_name", role_name);
					editor.putString("group_id", group_id);
					editor.putString("email", email);
					editor.putString("myclsl", myclsl);
					editor.putString("mylxsl", mylxsl);
					editor.putString("mydkjsl", mydkjsl);
					editor.commit();
				} else if (my_xinxibean.status.equals("200")) {
					avatar = sharedPreferencesa.getString("avatar", "").trim();
					nc = sharedPreferencesa.getString("nc", "").trim();
					mobile = sharedPreferencesa.getString("mobile", "").trim();
					role_name = sharedPreferencesa.getString("role_name", "")
							.trim();
					group_id = sharedPreferencesa.getString("group_id", "")
							.trim();
					email = sharedPreferencesa.getString("email", "").trim();
					myclsl = sharedPreferencesa.getString("myclsl", "").trim();
					mylxsl = sharedPreferencesa.getString("mylxsl", "").trim();
					mydkjsl = sharedPreferencesa.getString("mydkjsl", "")
							.trim();
					if (!avatar.equals("")) {
						// ImageLoader.getInstance().displayImage(avatar,
						// my_img_touxiang, application.options);
						ImageLoader.getInstance().displayImage(
								Net.PICURL + avatar, my_img_touxiang,
								application.options);
					}
					my_name.setText(nc);
					my_chedui_number.setText(myclsl + "个");
					my_luxian_number.setText(mylxsl + "条");
					my_dikojuan_number.setText(mydkjsl + "张");
				} else {
					showToast(my_xinxibean.status);
					showToast("数据有误！");
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				dismissProgressDialog();
				showToast("网络异常.....");
			}
		});

	}

	public void onResume() {
		super.onResume();
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		getDataForNet();
		updateUnreadAddressLable();
		// db = new My_DBSqlite(mContext);
		// cursor = db.select();
		// if (cursor.getCount() != 0) {
		// while (cursor.moveToNext()) {
		// carlist_count = cursor.getString(cursor
		// .getColumnIndex("carlist_count"));
		// myline_count = cursor.getString(cursor
		// .getColumnIndex("myline_count"));
		// coupon_count = cursor.getString(cursor
		// .getColumnIndex("coupon_count"));
		// avatar = cursor.getString(cursor.getColumnIndex("avatar"));
		// shijancuo = cursor
		// .getString(cursor.getColumnIndex("timestamp"));
		// nick_name = cursor
		// .getString(cursor.getColumnIndex("nick_name"));
		// }
		// }
	}

	// private void count() {
	// SharedPreferences shared = getSharedPreferences("count",
	// Activity.MODE_PRIVATE);
	// String cdcount = shared.getString("cdcount", "");
	// String lxcount = shared.getString("lxcount", "");
	// String dkjcount = shared.getString("dkjcount", "");
	// if (!cdcount.equals("")) {
	// my_chedui_number.setText(cdcount + "个");
	// }
	// if (!lxcount.equals("")) {
	// my_luxian_number.setText(lxcount + "条");
	// }
	// if (!dkjcount.equals("")) {
	// my_dikojuan_number.setText(dkjcount + "张");
	// }
	//
	// }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left:
			finish();
			break;
		case R.id.my_img_touxiang:
			Intent intent = new Intent(mContext, MyInformationActivity.class);
			startActivityForResult(intent, Constants.TOUXIANGURL);
			break;
		case R.id.my_chedui:// 我的车队
			Intent intent1 = new Intent(mContext, My_CheDuiActivity.class);
			startActivityForResult(intent1, 0001);
			break;
		case R.id.my_luxian:// 我的路线
			Intent intent2 = new Intent(mContext, My_LuXian.class);
			startActivityForResult(intent2, 0002);
			break;
		case R.id.my_xiaoxi:// 我的消息
			// //直接登录环信
			// if(MainActivity.dj==0){
			// MainActivity.dj=1;
			// }else{
			//
			// }
			startActivity(new Intent(mContext, MainActivity.class));
			break;
		case R.id.my_fapiao:// 发票
			startActivity(InvoiceActivity.class);
			break;
		case R.id.my_dikojuan:// 抵扣卷
			Intent intent3 = new Intent(mContext, AddCouponActivity.class);
			startActivityForResult(intent3, 0003);
			break;
		case R.id.my_jizhangbeng:// 记账本
			startActivity(JiZhangBenActivity.class);
		case R.id.my_zhifu:// 支付
			break;
		case R.id.my_bangzhu:// /帮助
			Intent it = new Intent(this, Web_INTENT.class);
			it.putExtra("type", "C");
			startActivity(it);
			break;
		case R.id.my_setting:// /设置
			startActivity(SettingActivity.class);
			break;
		}
	}

	// @Override
	// protected void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// super.onActivityResult(requestCode, resultCode, data);
	// if (resultCode == Activity.RESULT_OK) {
	// if (requestCode == Constants.TOUXIANGURL) {
	// String avatar1 = data.getStringExtra("avatar");
	// if (avatar1.equals("1")) {
	// my_img_touxiang.setImageResource(R.drawable.morentouxiang);
	// } else {
	// ImageLoader.getInstance().displayImage(
	// Net.PICURL + avatar1, my_img_touxiang,
	// application.options);
	// }
	// email = data.getStringExtra("email");
	// }
	// if (requestCode == 0001) {
	// String cdcount = data.getStringExtra("cdcount");
	// my_chedui_number.setText(cdcount + "个");
	// }
	// if (requestCode == 0002) {
	// String lxcount = data.getStringExtra("lxcount");
	// my_luxian_number.setText(lxcount + "条");
	// }
	// if (requestCode == 0003) {
	// String dkjcount = data.getStringExtra("dkjcount");
	// my_dikojuan_number.setText(dkjcount + "张");
	// }
	// }
	// }

	// ///////////////////////////////////////////////////////////////////////////

	/**
	 * 刷新申请与通知消息数
	 */
	public void updateUnreadAddressLable() {
		runOnUiThread(new Runnable() {
			public void run() {
				int count = getUnreadMsgCountTotal();
				if (count > 0) {
					xiaoxi_count.setText(String.valueOf(count));
				} else {
					xiaoxi_count.setVisibility(View.GONE);
				}

			}
		});

	}

	/**
	 * 获取未读申请与通知消息
	 * 
	 * @return
	 */
	// public int getUnreadAddressCountTotal() {
	// int unreadAddressCountTotal = 0;
	// if (((DemoHXSDKHelper) HXSDKHelper.getInstance()).getContactList().get(
	// Constant.NEW_FRIENDS_USERNAME) != null)
	// unreadAddressCountTotal = ((DemoHXSDKHelper) HXSDKHelper
	// .getInstance()).getContactList()
	// .get(Constant.NEW_FRIENDS_USERNAME).getUnreadMsgCount();
	// return unreadAddressCountTotal;
	// }

	/**
	 * 获取未读消息数
	 * 
	 * @return
	 */
	public int getUnreadMsgCountTotal() {
		int unreadMsgCountTotal = 0;
		int chatroomUnreadMsgCount = 0;
		unreadMsgCountTotal = EMChatManager.getInstance().getUnreadMsgsCount();
		for (EMConversation conversation : EMChatManager.getInstance()
				.getAllConversations().values()) {
			if (conversation.getType() == EMConversationType.ChatRoom)
				chatroomUnreadMsgCount = chatroomUnreadMsgCount
						+ conversation.getUnreadMsgCount();
		}
		return unreadMsgCountTotal - chatroomUnreadMsgCount;
	}

	public void onPause() {
		super.onPause();
	}
}
