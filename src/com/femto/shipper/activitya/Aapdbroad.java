package com.femto.shipper.activitya;

import java.util.List;
import cn.jpush.android.api.JPushInterface;
import com.femto.shipper.activity.MainActivity;
import com.femto.shipper.activity.OrderPropertyActivity;
import com.femto.shipper.activity.Order_gengduo;
import com.femto.shipper.utils.GsonUtils;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

@SuppressLint("NewApi")
public class Aapdbroad extends BroadcastReceiver {
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Bundle bundle = arg1.getExtras();
		String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
		try {
			Aapdhelpera aapdhelpera = GsonUtils.json2Bean(extras,
					Aapdhelpera.class);
			ActivityManager am = (ActivityManager) arg0
					.getSystemService(Context.ACTIVITY_SERVICE);
			List<RunningTaskInfo> list = am.getRunningTasks(100);
			boolean isAppRunning = false;
			String MY_PKG_NAME = "com.femto.shipper";
			for (RunningTaskInfo info : list) {
				if (info.topActivity.getPackageName().equals(MY_PKG_NAME)
						|| info.baseActivity.getPackageName().equals(
								MY_PKG_NAME)) {
					isAppRunning = true;
					break;
				}
			}
			if (!JPushInterface.ACTION_NOTIFICATION_OPENED.equals(arg1
					.getAction())) {
				if (aapdhelpera.type.equals("driver_send_succ")) {
					String driver_id = aapdhelpera.driver_id;
					// String lat = aapdhelpera.lat;
					// String lon = aapdhelpera.lng;
					Intent intent = new Intent();
					intent.putExtra("driver_id", driver_id);
					intent.putExtra("send", "driver_send_succ");
					// intent.putExtra("lat", lat);
					// intent.putExtra("lon", lon);
					intent.setAction("com.femto.shipper.activitya.Aapdbroad");
					arg0.sendBroadcast(intent);
				} else if (aapdhelpera.type.equals("send_nocar")) {
					Intent intent = new Intent();
					intent.putExtra("send", "send_nocar");
					intent.setAction("com.femto.shipper.activitya.Aapdbroad");
					arg0.sendBroadcast(intent);
				} else if (aapdhelpera.type.equals("send_not_enough")) {
					Intent intent = new Intent();
					intent.putExtra("send", "send_not_enough");
					intent.setAction("com.femto.shipper.activitya.Aapdbroad");
					arg0.sendBroadcast(intent);
				} else if (aapdhelpera.type.equals("success")) {
					Intent intent = new Intent();
					intent.putExtra("send", "success");
					intent.setAction("com.femto.shipper.activitya.Aapdbroad");
					arg0.sendBroadcast(intent);
				} else if (aapdhelpera.type.equals("order_start")) {
					Intent intent = new Intent();
					intent.putExtra("send", "sx");
					intent.setAction("com.femto.shipper.activitya.Aapdbroad");
					arg0.sendBroadcast(intent);
				} else if (aapdhelpera.type.equals("order_arrive")) {
					Intent intent = new Intent();
					intent.putExtra("send", "sx");
					intent.setAction("com.femto.shipper.activitya.Aapdbroad");
					arg0.sendBroadcast(intent);
				} else if (aapdhelpera.type.equals("order_express")) {
					Intent intent = new Intent();
					intent.putExtra("send", "sx");
					intent.setAction("com.femto.shipper.activitya.Aapdbroad");
					arg0.sendBroadcast(intent);
				} else if (aapdhelpera.type.equals("order_sign")) {
					Intent intent = new Intent();
					intent.putExtra("send", "sx");
					intent.setAction("com.femto.shipper.activitya.Aapdbroad");
					arg0.sendBroadcast(intent);
				} else if (aapdhelpera.type.equals("user_online")) {
					Intent intent = new Intent();
					intent.putExtra("send", "useronlinea");
					intent.setAction("com.femto.shipper.activitya.Aapdbroad");
					arg0.sendBroadcast(intent);
				}
			} else {
				if (aapdhelpera.type.equals("order_start")) {
					if (isAppRunning) {
						Intent ia = new Intent(arg0,
								OrderPropertyActivity.class);
						ia.putExtra("order_no", aapdhelpera.order_no);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						arg0.startActivity(ia);
					} else {
						Intent ia = new Intent(arg0, MainActivity.class);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						Intent ib = new Intent(arg0,
								OrderPropertyActivity.class);
						ib.putExtra("order_no", aapdhelpera.order_no);
						Intent[] intents = { ia, ib };
						arg0.startActivities(intents);
					}
				} else if (aapdhelpera.type.equals("order_arrive")) {
					if (isAppRunning) {
						Intent ia = new Intent(arg0,
								OrderPropertyActivity.class);
						ia.putExtra("order_no", aapdhelpera.order_no);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						arg0.startActivity(ia);
					} else {
						Intent ia = new Intent(arg0, MainActivity.class);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						Intent ib = new Intent(arg0,
								OrderPropertyActivity.class);
						ib.putExtra("order_no", aapdhelpera.order_no);
						Intent[] intents = { ia, ib };
						arg0.startActivities(intents);
					}

				} else if (aapdhelpera.type.equals("order_arrive")) {
					if (isAppRunning) {
						Intent ia = new Intent(arg0, Order_gengduo.class);
						ia.putExtra("yid", aapdhelpera.yid);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						arg0.startActivity(ia);
					} else {
						Intent ia = new Intent(arg0, MainActivity.class);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						Intent ib = new Intent(arg0, Order_gengduo.class);
						ib.putExtra("yid", aapdhelpera.yid);
						Intent[] intents = { ia, ib };
						arg0.startActivities(intents);
					}

				} else if (aapdhelpera.type.equals("order_express")) {
					if (isAppRunning) {
						Intent ia = new Intent(arg0, Order_gengduo.class);
						ia.putExtra("yid", aapdhelpera.yid);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						arg0.startActivity(ia);
					} else {
						Intent ia = new Intent(arg0, MainActivity.class);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						Intent ib = new Intent(arg0, Order_gengduo.class);
						ib.putExtra("yid", aapdhelpera.yid);
						Intent[] intents = { ia, ib };
						arg0.startActivities(intents);
					}

				} else if (aapdhelpera.type.equals("mid1_finish")) {
					if (isAppRunning) {
						Intent ia = new Intent(arg0, Order_gengduo.class);
						ia.putExtra("yid", aapdhelpera.yid);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						arg0.startActivity(ia);
					} else {
						Intent ia = new Intent(arg0, MainActivity.class);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						Intent ib = new Intent(arg0, Order_gengduo.class);
						ib.putExtra("yid", aapdhelpera.yid);
						Intent[] intents = { ia, ib };
						arg0.startActivities(intents);
					}

				} else if (aapdhelpera.type.equals("mid2_finish")) {
					if (isAppRunning) {
						Intent ia = new Intent(arg0, Order_gengduo.class);
						ia.putExtra("yid", aapdhelpera.yid);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						arg0.startActivity(ia);
					} else {
						Intent ia = new Intent(arg0, MainActivity.class);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						Intent ib = new Intent(arg0, Order_gengduo.class);
						ib.putExtra("yid", aapdhelpera.yid);
						Intent[] intents = { ia, ib };
						arg0.startActivities(intents);
					}

				} else if (aapdhelpera.type.equals("mid3_finish")) {
					if (isAppRunning) {
						Intent ia = new Intent(arg0, Order_gengduo.class);
						ia.putExtra("yid", aapdhelpera.yid);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						arg0.startActivity(ia);
					} else {
						Intent ia = new Intent(arg0, MainActivity.class);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						Intent ib = new Intent(arg0, Order_gengduo.class);
						ib.putExtra("yid", aapdhelpera.yid);
						Intent[] intents = { ia, ib };
						arg0.startActivities(intents);
					}

				} else if (aapdhelpera.type.equals("mid4_finish")) {
					if (isAppRunning) {
						Intent ia = new Intent(arg0, Order_gengduo.class);
						ia.putExtra("yid", aapdhelpera.yid);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						arg0.startActivity(ia);
					} else {
						Intent ia = new Intent(arg0, MainActivity.class);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						Intent ib = new Intent(arg0, Order_gengduo.class);
						ib.putExtra("yid", aapdhelpera.yid);
						Intent[] intents = { ia, ib };
						arg0.startActivities(intents);
					}
				} else if (aapdhelpera.type.equals("mid5_finish")) {
					if (isAppRunning) {
						Intent ia = new Intent(arg0, Order_gengduo.class);
						ia.putExtra("yid", aapdhelpera.yid);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						arg0.startActivity(ia);
					} else {
						Intent ia = new Intent(arg0, MainActivity.class);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						Intent ib = new Intent(arg0, Order_gengduo.class);
						ib.putExtra("yid", aapdhelpera.yid);
						Intent[] intents = { ia, ib };
						arg0.startActivities(intents);
					}

				} else if (aapdhelpera.type.equals("order_sign")) {
					if (isAppRunning) {
						Intent ia = new Intent(arg0, Order_gengduo.class);
						ia.putExtra("yid", aapdhelpera.yid);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						arg0.startActivity(ia);
					} else {
						Intent ia = new Intent(arg0, MainActivity.class);
						ia.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
								| Intent.FLAG_ACTIVITY_CLEAR_TOP);
						Intent ib = new Intent(arg0, Order_gengduo.class);
						ib.putExtra("yid", aapdhelpera.yid);
						Intent[] intents = { ia, ib };
						arg0.startActivities(intents);
					}
				} else if (aapdhelpera.type.equals("user_online")) {
					Intent intent = new Intent();
					intent.putExtra("send", "useronlinea");
					intent.setAction("com.femto.shipper.activitya.Aapdbroad");
					arg0.sendBroadcast(intent);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}