package com.femto.shipper.broadcastreception;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;

import com.femto.shipper.activity.BaiDuActivity;
import com.femto.shipper.activity.TaskFailureActivity;
import com.femto.shipper.bean.TuiSongBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JPush";

	@Override
	public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        LogUtils.i("extras"+extras);
        try
		{
			TuiSongBean tuiSongBean = GsonUtils.json2Bean(extras, TuiSongBean.class);
			if(tuiSongBean.type.equals("OrderFail")){
				Intent i = new Intent(context, TaskFailureActivity.class);
				i.putExtra("type", tuiSongBean.type);
				i.putExtra("order_no", tuiSongBean.order_no);
				i.putExtra("remark", tuiSongBean.remark);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
				context.startActivity(i);
			}else if(tuiSongBean.type.equals("OrderConfrim")||
					tuiSongBean.type.equals("OrderStart")||
					tuiSongBean.type.equals("OrderArrive")||
					tuiSongBean.type.equals("OrderExpress")||
					tuiSongBean.type.equals("OrderSign")||
					tuiSongBean.type.equals("OrderComplete")){
				//司机确认接单
//				Intent  it=new Intent(context, PaiDanChengGong.class);
//				it.putExtra("type", tuiSongBean.type);
//				it.putExtra("order_no", tuiSongBean.order_no);
//				context.startActivity(it);
				
				/*
				 //下面代码是直接跳到“速补物流”页面
				 
				 Intent intent2 = new Intent(context, BaiDuActivity.class);
				intent2.putExtra("type", tuiSongBean.type);
				intent2.putExtra("order_no", tuiSongBean.order_no);
				intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
				context.startActivity(intent2);*/
			}
		} catch (Exception e)
		{
		}
        
        
        
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            LogUtils.i("接收Registration Id : " + regId);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
        	
        	LogUtils.i("接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
        
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            
        	LogUtils.i("[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            LogUtils.i("[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
        	
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            LogUtils.i(" 用户点击打开了通知");// 跳转页面操作
            
/*        	//打开自定义的Activity
        	Intent i = new Intent(context, TestActivity.class);
        	i.putExtras(bundle);
        	//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        	i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
        	context.startActivity(i);*/
        	
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            LogUtils.i("[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
        	
        } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
        	boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
        	 LogUtils.i("[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
        } else {
         	 LogUtils.i("[MyReceiver] Unhandled intent - " + intent.getAction());
        }
	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (bundle.getString(JPushInterface.EXTRA_EXTRA).isEmpty()) {
					Log.i(TAG, "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next().toString();
						sb.append("\nkey:" + key + ", value: [" +
								myKey + " - " +json.optString(myKey) + "]");
					}
				} catch (JSONException e) {
					Log.e(TAG, "Get message extra JSON error!");
				}

			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}
	
	
	
	
	
}
