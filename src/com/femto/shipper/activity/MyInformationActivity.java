package com.femto.shipper.activity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.UpImageBean;
import com.femto.shipper.bean.UpdateTuXiangBean;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.femto.shipper.utils.URIUtils;
import com.femto.shipper.view.CircleImageView;
import com.femto.shipper.view.GetPictureFragment;
import com.femto.shipper.view.GetPictureFragment.OnGetPictureListener;
import com.femto.shipper.view.SelectPicPopupWindow;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

/**
 * @author mac 我的信息
 */
@SuppressLint("InlinedApi")
public class MyInformationActivity extends BaseActivity implements
		OnClickListener {

	boolean isUpdateIcon = false;
	private CircleImageView img_touxiang;
	private Uri imageUri = null;
	private String avatar, email;
	private SelectPicPopupWindow spw;
	private GetPictureFragment getPicture;
	private TextView my_info_name, my_info_phone, my_info_rale,
			my_info_youxiang;
	private RelativeLayout my_inform4, my_inform5;
	private String phonea = "", passworda = "";
	private SharedPreferences sharedPreferences, sharedPreferencesa,
			sharedpreferencese;
	private String name, phone, jingjiren, tokena;
	private UploadManager uploadManager;
	private Editor editora = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_MULTI_PROCESS);
		sharedPreferencesa = getSharedPreferences("user_xinxi",
				Activity.MODE_MULTI_PROCESS);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		// sharedpreferencese = getSharedPreferences("tokena",
		// Activity.MODE_PRIVATE);
		// tokena = sharedpreferencese.getString("tokena", "");
		// if (tokena == null || tokena.equals("") || tokena.equals("null")) {
		// tokena = ToolUtils.Token();
		// editora = sharedpreferencese.edit();
		// editora.putString("tokena", tokena);
		// editora.commit();
		// }
		info();
		avatar = sharedPreferencesa.getString("avatar", "").trim();
		name = sharedPreferencesa.getString("nc", "").trim();
		phone = sharedPreferencesa.getString("mobile", "").trim();
		jingjiren = sharedPreferencesa.getString("role_name", "").trim();
		email = sharedPreferencesa.getString("email", "").trim();
		if (!avatar.equals("")) {
			// ImageLoader.getInstance().displayImage(Net.PICURL + avatar,
			// img_touxiang, application.options);
			ImageLoader.getInstance().displayImage(avatar, img_touxiang,
					application.options);
		} else {
			img_touxiang.setImageResource(R.drawable.morentouxiang);
		}
		my_info_name.setText(name);
		my_info_phone.setText(phone);
		my_info_rale.setText(jingjiren);
		my_info_youxiang.setText(email);
	}

	public void info() {
		my_info_name = (TextView) findViewById(R.id.my_info_name);
		my_info_phone = (TextView) findViewById(R.id.my_info_phone);
		my_info_rale = (TextView) findViewById(R.id.my_info_rale);
		my_info_youxiang = (TextView) findViewById(R.id.my_info_youxiang);
		img_touxiang = (CircleImageView) findViewById(R.id.my_info_img_touxiang);
		my_inform4 = (RelativeLayout) findViewById(R.id.my_inform4);
		my_inform5 = (RelativeLayout) findViewById(R.id.my_inform5);
		findViewById(R.id.left).setOnClickListener(this);
		img_touxiang.setOnClickListener(this);
		my_inform4.setOnClickListener(this);
		my_inform5.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (isFastDoubleClick()) {
			return;
		}
		switch (v.getId()) {
		case R.id.left:
			Intent intent = new Intent();
			if (avatar.equals("")) {
				intent.putExtra("avatar", "1");
			} else {
				intent.putExtra("avatar", avatar);
			}

			setResult(Activity.RESULT_OK, intent);
			finish();
			break;
		case R.id.my_info_img_touxiang:// 头像
			isUpdateIcon = true;
			spw = new SelectPicPopupWindow(this, this);
			spw.showAtLocation(img_touxiang, Gravity.BOTTOM, 0, 0);
			break;
		case SelectPicPopupWindow.PICK_PHOTO_ID:// 相机拍照
			getPicture = GetPictureFragment.newInstance(
					isUpdateIcon == true ? GetPictureFragment.TYPE_REGIST
							: GetPictureFragment.TYPE_OTHER, true,
					mOnGetPictureListener);
			getPicture.show(getSupportFragmentManager(), "tag");
			break;
		case SelectPicPopupWindow.TAKE_PHOTO_ID:// 本地相册
			getPicture = GetPictureFragment.newInstance(
					isUpdateIcon == true ? GetPictureFragment.TYPE_REGIST
							: GetPictureFragment.TYPE_OTHER, false,
					mOnGetPictureListener);
			getPicture.show(getSupportFragmentManager(), "tag");
			break;
		case R.id.my_inform4:

			// 创建view从当前activity获取loginactivity
			LayoutInflater inflater = LayoutInflater.from(this);
			View v1 = inflater.inflate(R.layout.dialog_update_email, null);
			final EditText youxiang1 = (EditText) v1
					.findViewById(R.id.youxiang_update);
			Builder builder = new Builder(this);
			builder.setTitle("请输入邮箱号");
			builder.setView(v1);// 自己定义一个View，就可以放进去
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						public void onClick(final DialogInterface dialog,
								int which) {
							final String youxiang2 = youxiang1.getText()
									.toString();
							if (youxiang1.equals("")) {
								showToast("不能为空");
							} else {
								if (ToolUtils.isEmail(youxiang2)) {
									Map<String, String> map = new HashMap<String, String>();
									map.put("key", "edit_user_email");
									map.put("username", phonea);
									map.put("pwd", passworda);
									map.put("email", email);
									String jiaMi = ToolUtils.JiaMi(map);
									String url = Net.YONGHUXINXI + jiaMi;
									showProgressDialog("加载中...");
									application.doget(url,
											new RequestCallBack<String>() {

												@Override
												public void onFailure(
														HttpException arg0,
														String arg1) {
													showToast("网络异常");
													dismissProgressDialog();
												}

												@Override
												public void onSuccess(
														ResponseInfo<String> arg0) {
													dismissProgressDialog();
													UpdateTuXiangBean UpdateTuXiangBean = GsonUtils
															.json2Bean(
																	arg0.result,
																	UpdateTuXiangBean.class);
													if (UpdateTuXiangBean.status
															.equals("0")) {
														showToast("提交成功");
														email = youxiang2;
														my_info_youxiang
																.setText(youxiang2);
														dialog.dismiss();
													} else {
														showToast("提交失败");
													}
												}
											});
								} else {
									showToast("输入格式不正确");
								}
							}
						}
					});

			builder.setNegativeButton("取消",
					new android.content.DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});

			builder.create().show();
			break;
		case R.id.my_inform5:
			startActivity(My_SubmitPwm.class);
			break;
		}
	}

	@Override
	public void onStart() {
		super.onStart();

		if (imageUri != null) {
			try {
				LogUtils.i("onStart返回的url:---" + imageUri);
				Bitmap bitmap = MediaStore.Images.Media.getBitmap(
						this.getContentResolver(), imageUri);
				img_touxiang.setImageBitmap(bitmap);
				// UpDataToNet(bitmap);
				UpDataToNet();
			} catch (Exception e) {
				showToast("图片设置错误");
			}
		}
	}

	// private void UpDataToNet(final Bitmap bitmap) {
	// byte[] byteuria = ToolUtils.Bitmap2Bytes(ToolUtils
	// .compressImage(bitmap));
	// uploadManager = new UploadManager();
	// final String key = phonea + "_" + ToolUtils.Timea();
	// uploadManager.put(byteuria, key, tokena, new UpCompletionHandler() {
	//
	// @Override
	// public void complete(String arg0,
	// com.qiniu.android.http.ResponseInfo arg1, JSONObject arg2) {
	// if (arg1.statusCode == 200) {
	// avatar = Net.newpicturl + key;
	// getTuXiang(avatar);
	// } else if (arg1.statusCode == 401) {
	// tokena = ToolUtils.Token();
	// editora = sharedpreferencese.edit();
	// editora.putString("tokena", tokena);
	// editora.commit();
	// UpDataToNet(bitmap);
	// } else {
	// showToast(getResources().getString(R.string.scsba));
	// }
	// }
	// }, null);
	// }

	private void UpDataToNet() {
		File urlToFilePath = URIUtils.urlToFilePath(mContext, imageUri);
		RequestParams params = new RequestParams();
		params.addBodyParameter("action", "upload_face");
		params.addBodyParameter("face", urlToFilePath);
		showProgressDialog("上传中...");
		application.doPost(Net.UPTOUXIANG, params,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						LogUtils.i("getTuXiang" + arg0.result);
						UpImageBean statusBean = GsonUtils.json2Bean(
								arg0.result, UpImageBean.class);
						if (statusBean.status.equals("0")) {
							avatar = statusBean.path;
							getTuXiang(avatar);
						} else {
							showToast(statusBean.msg);
						}
						dismissProgressDialog();
					}

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						dismissProgressDialog();
						LogUtils.i(arg1);
					}
				});
	}

	private OnGetPictureListener mOnGetPictureListener = new OnGetPictureListener() {
		@Override
		public void onComplete(Uri result) {
			LogUtils.i(" onComplete");
			getPicture.dismiss();
			spw.dismiss();
			imageUri = result;

		}

		@Override
		public void onCancel() {
			getPicture.dismiss();
			spw.dismiss();
		}
	};

	// /上传图片
	public void getTuXiang(String avatar) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "edit_user_avatar");
		map.put("username", phonea);
		map.put("pwd", passworda);
		map.put("avatar", avatar);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.YONGHUXINXI + jiaMi;
		LogUtils.i("请求的url:" + url);
		showProgressDialog("加载中...");
//        uploadManager.put(data, key, token, completionHandler, options);
		application.doGet(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				LogUtils.i("" + arg1);
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				LogUtils.i(arg0.result);
				dismissProgressDialog();
				UpdateTuXiangBean UpdateTuXiangBean = GsonUtils.json2Bean(
						arg0.result, UpdateTuXiangBean.class);
				if (UpdateTuXiangBean.status.equals("0")) {
					showToast("上传成功");
				} else {
					showToast("上传失败,"+UpdateTuXiangBean.msg);
				}
			}
		});
	}

	// /修改邮箱
	public void getUdateYXa(final String email) {

	}

	public void onResume() {
		super.onResume();
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
	}

	public void onPause() {
		super.onPause();

	}
}
