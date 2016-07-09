package com.femto.shipper.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.femto.shipper.R;
import com.femto.shipper.activitya.Mydialogk;
import com.femto.shipper.activitya.Mydialogk.Dialogcallbackk;
import com.femto.shipper.adapter.ListViewAdapter;
import com.femto.shipper.adapter.SlideCutListView;
import com.femto.shipper.adapter.SlideCutListView.RemoveDirection;
import com.femto.shipper.adapter.SlideCutListView.RemoveListener;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.bean.KaiPiao_ZengZhiBean;
import com.femto.shipper.bean.KaipiaoBean;
import com.femto.shipper.bean.UpImageBean;
import com.femto.shipper.bean.UpdateTuXiangBean;
import com.femto.shipper.bean.KaipiaoBean.KaipiaoBeanZ;
import com.femto.shipper.utils.GsonUtils;
import com.femto.shipper.utils.Net;
import com.femto.shipper.utils.ToolUtils;
import com.femto.shipper.utils.URIUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.umeng.analytics.MobclickAgent;

public class KaiPiao_ZengZhi extends BaseActivity implements OnClickListener,
		RemoveListener {
	private String phonea, tokena;
	private SharedPreferences sharedPreferences, sharedpreferencese;
	private KaipiaoBean Kaipiao;
	private MyAdapter adapter;
	private SlideCutListView NEWSlideCutListView;
	List<KaipiaoBeanZ> Kaipiaolist = new ArrayList<KaipiaoBeanZ>();
	private String name, phone, dizi = "null";
	private int temp = -1, b, tp = 0;
	private String imagepathA = "", imagepathB = "", imagepathC = "";
	private KaiPiao_ZengZhiBean KaiPiao_ZengZhiBean;
	boolean isUpdateIcon = false;
	private TextView kiapiao_ZT_moneyMax;
	private LinearLayout kiapiao_ZT_tijiao;// /鎻愪氦淇℃伅
	private Button kiapiao_ZT_new_dizi;
	private EditText kiapiao_ZT_jine, kiapiao_ZT_money, kiapiao_ZT_shibiema,
			kiapiao_ZT_adress, kiapiao_ZT_phone, kiapiao_ZT_yinhangWhere,
			kiapiao_ZT_yinhanghao;
	private GridView kaipioagv;
	private Gridkaipioagvadapter gridkaipioagvadapter;
	private Bitmap bitmap = null, bitmapa = null;
	// private Uri uria = null, urib = null, uric = null;
	private byte[] uria = null, urib = null, uric = null;
	private String kpje = "null", kkfped, dwmc = "null", nsrspm = "null",
			zcdz = "null", zcdh = "null", khyh = "null", yhzh = "null";
	private Editor editora = null;
	private UploadManager uploadManager;

	private void yz() {
		kpje = kiapiao_ZT_jine.getText().toString().trim();
		dwmc = kiapiao_ZT_money.getText().toString().trim();
		nsrspm = kiapiao_ZT_shibiema.getText().toString().trim();
		zcdz = kiapiao_ZT_adress.getText().toString().trim();
		zcdh = kiapiao_ZT_phone.getText().toString().trim();
		khyh = kiapiao_ZT_yinhangWhere.getText().toString().trim();
		yhzh = kiapiao_ZT_yinhanghao.getText().toString().trim();
		if (kpje.equals("") || kpje == null || kpje.equals("null")) {
			showToast("请输入开票金额");
		} else {
			try {
				if (dizi.equals("") || dizi == null || dizi.equals("null")) {
					showToast("请输入选择开票人联系方式！若没有请点击增加新地址");
				} else {
					int intkkfped = Integer.valueOf(ToolUtils
							.fengbujiequ(kkfped));
					int intkpje = Integer.valueOf(kpje);
					if (intkpje > intkkfped) {
						showToast("输入的开票金额过大！");
					} else {
						if (intkpje < 50) {
							showToast("开票金额不能少于50元！");
						} else {
							if (dwmc.equals("") || dwmc == null
									|| dwmc.equals("null")) {
								showToast("请输入单位名称！");
							} else {
								if (nsrspm.equals("") || nsrspm == null
										|| nsrspm.equals("null")) {
									showToast("请输入纳税人识别码！");
								} else {
									if (zcdz.equals("") || zcdz == null
											|| zcdz.equals("null")) {
										showToast("请输入注册地址！");
									} else {
										if (zcdh.equals("") || zcdh == null
												|| zcdh.equals("null")) {
											showToast("请输入注册电话！");
										} else {
											if (khyh.equals("") || khyh == null
													|| khyh.equals("null")) {
												showToast("请输入开户银行！");
											} else {
												if (yhzh.equals("")
														|| yhzh == null
														|| yhzh.equals("null")) {
													showToast("请输入银行账号！");
												} else {
													if (uric != null) {
														showProgressDialog("加载中..");
														sctpa();
													} else {
														showToast("图片至少3张！");
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				showToast("输入的开票金额不正确！");
			}
		}
	}

	private void sctp() {
		try {
			uria = ToolUtils.Bitmap2Bytes(ToolUtils
					.compressImage(Bimp.tempSelectBitmap.get(0).getBitmap()));
		} catch (Exception e) {
			uria = null;
		}
		try {
			urib = ToolUtils.Bitmap2Bytes(ToolUtils
					.compressImage(Bimp.tempSelectBitmap.get(1).getBitmap()));
		} catch (Exception e) {
			urib = null;
		}
		try {
			uric = ToolUtils.Bitmap2Bytes(ToolUtils
					.compressImage(Bimp.tempSelectBitmap.get(2).getBitmap()));
		} catch (Exception e) {
			uric = null;
		}
	}

	// private void sctp() {
	// try {
	// uria = Uri.parse(MediaStore.Images.Media.insertImage(
	// getContentResolver(), Bimp.tempSelectBitmap.get(0)
	// .getBitmap(), null, null));
	// } catch (Exception e) {
	// uria = null;
	// }
	// try {
	// urib = Uri.parse(MediaStore.Images.Media.insertImage(
	// getContentResolver(), Bimp.tempSelectBitmap.get(1)
	// .getBitmap(), null, null));
	// } catch (Exception e) {
	// urib = null;
	// }
	// try {
	// uric = Uri.parse(MediaStore.Images.Media.insertImage(
	// getContentResolver(), Bimp.tempSelectBitmap.get(2)
	// .getBitmap(), null, null));
	// } catch (Exception e) {
	// uric = null;
	// }
	// }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kaipioa_zengzhiactivity);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		sharedpreferencese = getSharedPreferences("tokena",
				Activity.MODE_PRIVATE);
		tokena = sharedpreferencese.getString("tokena", "");
		if (tokena == null || tokena.equals("") || tokena.equals("null")) {
			tokena = ToolUtils.Token();
			editora = sharedpreferencese.edit();
			editora.putString("tokena", tokena);
			editora.commit();
		}
		adapter = new MyAdapter(mContext);
		infv();

	}

	private void sctpa() {
		UpDataToNet(uria);
	}

	private void sctpb() {
		UpDataToNet(urib);
	}

	private void sctpc() {
		UpDataToNet(uric);
	}

	private void infv() {

		kiapiao_ZT_moneyMax = (TextView) findViewById(R.id.kiapiao_ZT_moneyMax);
		kiapiao_ZT_tijiao = (LinearLayout) findViewById(R.id.kiapiao_ZT_tijiao);
		kiapiao_ZT_money = (EditText) findViewById(R.id.kiapiao_ZT_money);
		kiapiao_ZT_shibiema = (EditText) findViewById(R.id.kiapiao_ZT_shibiema);
		kiapiao_ZT_adress = (EditText) findViewById(R.id.kiapiao_ZT_adress);
		kiapiao_ZT_phone = (EditText) findViewById(R.id.kiapiao_ZT_phone);
		kiapiao_ZT_yinhangWhere = (EditText) findViewById(R.id.kiapiao_ZT_yinhangWhere);
		kiapiao_ZT_yinhanghao = (EditText) findViewById(R.id.kiapiao_ZT_yinhanghao);
		kiapiao_ZT_jine = (EditText) findViewById(R.id.kiapiao_ZT_jine);
		NEWSlideCutListView = (SlideCutListView) findViewById(R.id.NEWSlideCutListView);
		kiapiao_ZT_new_dizi = (Button) findViewById(R.id.kiapiao_ZT_new_dizi);
		kaipioagv = (GridView) findViewById(R.id.kaipioagv);
		kaipioagv.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gridkaipioagvadapter = new Gridkaipioagvadapter(this);
		kaipioagv.setAdapter(gridkaipioagvadapter);
		kiapiao_ZT_tijiao.setOnClickListener(this);
		findViewById(R.id.left).setOnClickListener(this);
		kiapiao_ZT_new_dizi.setOnClickListener(this);
		NEWSlideCutListView.setRemoveListener(this);
		kkfped = getIntent().getStringExtra("max_money").trim();
		kiapiao_ZT_moneyMax.setText("¥" + kkfped);
		getinfo();
		getsele();
		// kaipiao_listView
		// .setOnItemLongClickListener(new OnItemLongClickListener() {
		//
		// @Override
		// public boolean onItemLongClick(AdapterView<?> arg0,
		// View arg1, int arg2, long arg3) {
		// showToast("");
		// return false;
		// }
		//
		// });
		kaipioagv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (arg2 == Bimp.tempSelectBitmap.size()) {
					Mydialogk mydialogk = new Mydialogk(KaiPiao_ZengZhi.this);
					mydialogk.setDialogCallbackk(mydialogkdissmiss);
					mydialogk.show();
				} else {
					Bimp.tempSelectBitmap.remove(arg2);
					kaipioagv.setAdapter(gridkaipioagvadapter);
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			if (b == 1) {
				if (Bimp.tempSelectBitmap.size() < 3) {
					// bitmap = (Bitmap) data.getExtras().getParcelable("data");
					bitmap = BitmapFactory.decodeFile(Environment
							.getExternalStorageDirectory()
							+ "/mycardworkuploadb.jpg");
					bitmapa = ToolUtils.Piczoom(bitmap, bitmap.getWidth() / 4,
							bitmap.getHeight() / 4);
					bitmap.recycle();
					ImageItem takePhoto = new ImageItem();
					takePhoto.setBitmap(bitmapa);
					Bimp.tempSelectBitmap.add(takePhoto);
					sctp();
				}
				b = 0;
			}
		}

	}

	Dialogcallbackk mydialogkdissmiss = new Dialogcallbackk() {
		@SuppressLint("InlinedApi")
		@Override
		public void dialogdok(int a) {
			if (a == 1) {
				String SDState = Environment.getExternalStorageState();
				if (SDState.equals(Environment.MEDIA_MOUNTED)) {
					Intent intenta = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					Uri photoUri = Uri.fromFile(new File(Environment
							.getExternalStorageDirectory(),
							"mycardworkuploadb.jpg"));
					intenta.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
							photoUri);
					startActivityForResult(intenta, 6);
					b = 1;
				} else {
					showToast("内存卡不存在");
				}
			} else if (a == 2) {
				Intent intentb = new Intent(KaiPiao_ZengZhi.this,
						AlbumActivity.class);
				startActivity(intentb);
				b = 2;
			}
		}
	};

	public class Gridkaipioagvadapter extends BaseAdapter {
		private LayoutInflater inflater;
		private int selectedPosition = -1;
		private boolean shape;

		public boolean isShape() {
			return shape;
		}

		public void setShape(boolean shape) {
			this.shape = shape;
		}

		public Gridkaipioagvadapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		public int getCount() {
			if (Bimp.tempSelectBitmap.size() == 3) {
				return 3;
			}
			return (Bimp.tempSelectBitmap.size() + 1);
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int arg0) {
			return 0;
		}

		public void setSelectedPosition(int position) {
			selectedPosition = position;
		}

		public int getSelectedPosition() {
			return selectedPosition;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = inflater
						.inflate(R.layout.ybgvitem, parent, false);
				holder = new ViewHolder();
				holder.image = (ImageView) convertView
						.findViewById(R.id.itemiv);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			if (position == Bimp.tempSelectBitmap.size()) {
				holder.image.setImageBitmap(BitmapFactory.decodeResource(
						getResources(), R.drawable.zxa));
				if (position == 3) {
					holder.image.setVisibility(View.GONE);
				}
			} else {
				holder.image.setImageBitmap(Bimp.tempSelectBitmap.get(position)
						.getBitmap());
			}

			return convertView;
		}

		public class ViewHolder {
			public ImageView image;
		}

		@SuppressLint("HandlerLeak")
		Handler handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 1:
					gridkaipioagvadapter.notifyDataSetChanged();
					break;
				}
				super.handleMessage(msg);
			}
		};
	}

	public void getinfo() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "invoice_addr_list");
		map.put("username", phonea);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.KAIPIAODONGZUO + jiaMi;
		showProgressDialog("加载中...");

		application.doget(url, new RequestCallBack<String>() {
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {

				dismissProgressDialog();
				Kaipiaolist.clear();
				Kaipiao = GsonUtils.json2Bean(arg0.result, KaipiaoBean.class);
				if (Kaipiao.status.equals("0")) {

					if (Kaipiao.list.size() != 0) {
						Kaipiaolist.addAll(Kaipiao.list);
						adapter.setList(Kaipiaolist);

					} else {
					}
					NEWSlideCutListView.setAdapter(adapter);
					setListViewHeightBasedOnChildren(NEWSlideCutListView);
				} else {
					// showToast(Kaipiao.status + Kaipiao.msg);
				}
			}

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast("网络异常");
				dismissProgressDialog();
			}
		});
	}

	public void setListViewHeightBasedOnChildren(SlideCutListView listView) {

		ListAdapter listAdapter = listView.getAdapter();

		if (listAdapter == null) {

			return;

		}

		int totalHeight = 0;

		for (int i = 0; i < listAdapter.getCount(); i++) {

			View listItem = listAdapter.getView(i, null, listView);

			listItem.measure(0, 0);

			totalHeight += listItem.getMeasuredHeight();

		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();

		params.height = totalHeight

		+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));

		listView.setLayoutParams(params);

	}

	private void tankuang(final String id) {

		Builder builder = new Builder(this);
		builder.setTitle("是否确认删除");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dele(id);
				dialog.dismiss();

			}
		});

		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		builder.create().show();
	}

	private void dele(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "del_invoice_addr");
		map.put("id", id);
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.TONGYONG + jiaMi;
		showProgressDialog("加载中...");
		application.doget(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast("网络异常");
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				dismissProgressDialog();
				UpdateTuXiangBean UpdateTuXiangBean = GsonUtils.json2Bean(
						arg0.result, UpdateTuXiangBean.class);
				if (UpdateTuXiangBean.status.equals("0")) {
					getinfo();
				}
			}
		});

	}

	class MyAdapter extends ListViewAdapter<KaipiaoBeanZ> {

		public MyAdapter(Context context) {
			super(context);
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup arg2) {
			final Holder holder;
			if (convertView == null) {
				holder = new Holder();
				convertView = View.inflate(mContext, R.layout.kaipiao_itme,
						null);
				holder.kaipiao_itme_moren = (RadioButton) convertView
						.findViewById(R.id.kaipiao_itme_moren);
				holder.kaipiao_itme_name = (TextView) convertView
						.findViewById(R.id.kaipiao_itme_name);
				holder.kaipiao_itme_phone = (TextView) convertView
						.findViewById(R.id.kaipiao_itme_phone);
				holder.kaipiao_itme_dizi = (TextView) convertView
						.findViewById(R.id.kaipiao_itme_dizi);
				holder.kaipiao_itme_next = (ImageView) convertView
						.findViewById(R.id.kaipiao_itme_next);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}

			final KaipiaoBeanZ orderz = myList.get(position);
			holder.kaipiao_itme_name.setText(orderz.contact_name);
			holder.kaipiao_itme_dizi.setText(orderz.address);
			holder.kaipiao_itme_phone.setText(orderz.mobile);
			if (orderz.status.equals("1")) {
				holder.kaipiao_itme_moren.setChecked(true);
				name = orderz.contact_name;
				phone = orderz.mobile;
				dizi = orderz.address;

			} else {
				holder.kaipiao_itme_moren.setChecked(false);
			}
			// 单选监听
			holder.kaipiao_itme_moren.setId(position);
			holder.kaipiao_itme_moren
					.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View arg0) {
							if (holder.kaipiao_itme_moren.isChecked()) {
								if (temp != -1) {
									RadioButton tempButton = (RadioButton) findViewById(temp);
									if (temp == position) {
										if (tempButton != null) {
											tempButton.setChecked(true);

										}
									} else {
										if (tempButton != null) {
											tempButton.setChecked(false);
										}
									}

								}
								name = orderz.contact_name;
								phone = orderz.mobile;
								dizi = orderz.address;
								temp = holder.kaipiao_itme_moren.getId();
							}

						}
					});

			holder.kaipiao_itme_next
					.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View arg0) {
							Intent it = new Intent(KaiPiao_ZengZhi.this,
									KaiPiao_Address.class);
							it.putExtra("type", "1");
							it.putExtra("address", orderz.address);
							it.putExtra("mobile", orderz.mobile);
							it.putExtra("contact_name", orderz.contact_name);
							it.putExtra("zipcode", orderz.zipcode);
							it.putExtra("id", orderz.id);
							startActivity(it);
						}
					});

			return convertView;
		}
	}

	class Holder {
		RadioButton kaipiao_itme_moren;
		TextView kaipiao_itme_name;
		TextView kaipiao_itme_phone;
		TextView kaipiao_itme_dizi;
		ImageView kaipiao_itme_next;

	}

	@Override
	public void removeItem(RemoveDirection direction, int position) {
		switch (direction) {
		case RIGHT:
			String id1 = Kaipiao.list.get(position).id;
			tankuang(id1);
			break;
		case LEFT:
			String id2 = Kaipiao.list.get(position).id;
			tankuang(id2);
			break;
		default:
			break;
		}
	}

	private void getsele() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "invoice_vat_info");
		map.put("username", phonea);//
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.TONGYONG + jiaMi;
		showProgressDialog("加载中...");
		application.doget(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast("网络异常");
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				dismissProgressDialog();
				KaiPiao_ZengZhiBean = GsonUtils.json2Bean(arg0.result,
						KaiPiao_ZengZhiBean.class);
				if (KaiPiao_ZengZhiBean.status.equals("0")) {
					kiapiao_ZT_money
							.setText(KaiPiao_ZengZhiBean.info.danweimingcheng);
					kiapiao_ZT_shibiema
							.setText(KaiPiao_ZengZhiBean.info.shibiema);
					kiapiao_ZT_adress
							.setText(KaiPiao_ZengZhiBean.info.zhucedizhi);
					kiapiao_ZT_phone
							.setText(KaiPiao_ZengZhiBean.info.zhucedianhua);
					kiapiao_ZT_yinhangWhere
							.setText(KaiPiao_ZengZhiBean.info.kaihuyinhang);
					kiapiao_ZT_yinhanghao
							.setText(KaiPiao_ZengZhiBean.info.yinhangzhanghao);
				} else {
					// showToast(Kaipiao.status + Kaipiao.msg);
				}
			}
		});
	}

	private void scall() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "add_invoice_vat");
		map.put("username", phonea);
		map.put("amount", kpje);// /发票金额
		map.put("address", dizi);// 联系地址
		map.put("mobile", phone);// 联系电话
		map.put("name", name);// 姓名
		map.put("content", "");// 内容
		map.put("title", "");// 抬头
		map.put("danweimingcheng", dwmc);// /单位名称
		map.put("imgurl", imagepathA + "|" + imagepathB + "|" + imagepathC);// 图片地址
		map.put("kaihuyinhang", khyh);// /开户银行
		map.put("shibiema", nsrspm);// /识别码
		map.put("yinhangzhanghao", yhzh);// /银行帐号
		map.put("zhucedianhua", zcdh);// /注册电话
		map.put("zhucedizhi", zcdz);// /注册地址
		String jiaMi = ToolUtils.JiaMi(map);
		String url = Net.TONGYONG + jiaMi;
		application.doget(url, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				showToast("网络异常");
				dismissProgressDialog();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				dismissProgressDialog();
				UpdateTuXiangBean UpdateTuXiangBean = GsonUtils.json2Bean(
						arg0.result, UpdateTuXiangBean.class);
				if (UpdateTuXiangBean.status.equals("0")) {
					showToast("提交成功");
					finish();
				} else {
					showToast("提交失败");
				}
			}
		});
	}

	@Override
	public void onClick(View arg0) {
		if (isFastDoubleClick()) {
			return;
		}
		switch (arg0.getId()) {
		case R.id.kiapiao_ZT_new_dizi:
			yz();
			break;
		case R.id.kiapiao_ZT_tijiao:// /鎻愪氦淇℃伅
			Intent it = new Intent(this, KaiPiao_Address.class);
			it.putExtra("type", "0");
			startActivity(it);
			break;
		case R.id.left://
			finish();
			break;
		default:
			break;
		}
	}

	private void UpDataToNet(final byte[] uri) {
		uploadManager = new UploadManager();
		final String key = phonea + "_" + ToolUtils.Timea();
		uploadManager.put(uri, key, tokena, new UpCompletionHandler() {
			@Override
			public void complete(String arg0,
					com.qiniu.android.http.ResponseInfo arg1, JSONObject arg2) {
				if (arg1.statusCode == 200) {
					tp++;
					if (tp == 1) {
						imagepathA = key;
						sctpb();
					} else if (tp == 2) {
						imagepathB = key;
						sctpc();
					} else if (tp == 3) {
						imagepathC = key;
						scall();
					}
				} else if (arg1.statusCode == 401) {
					tokena = ToolUtils.Token();
					editora = sharedpreferencese.edit();
					editora.putString("tokena", tokena);
					editora.commit();
					UpDataToNet(uri);
				} else {
					showToast(getResources().getString(R.string.scsba));
				}
			}
		}, null);
	}

	// private void UpDataToNet(Uri uri) {
	// File urlToFilePath = URIUtils.urlToFilePath(mContext, uri);
	// RequestParams params = new RequestParams();
	// params.addBodyParameter("action", "upload_goodsImg");
	// params.addBodyParameter("face", urlToFilePath);
	// application.doPost(Net.UPTOUXIANG, params,
	// new RequestCallBack<String>() {
	// @Override
	// public void onSuccess(ResponseInfo<String> arg0) {
	// UpImageBean statusBean = GsonUtils.json2Bean(
	// arg0.result, UpImageBean.class);
	// if (statusBean.status.equals("0")) {
	// tp++;
	// if (tp == 1) {
	// imagepathA = statusBean.path;
	// sctpb();
	// } else if (tp == 2) {
	// imagepathB = statusBean.path;
	// sctpc();
	// } else if (tp == 3) {
	// imagepathC = statusBean.path;
	// scall();
	// // type = false;
	// }
	// } else {
	// dismissProgressDialog();
	// showToast(tp + "图片上传失败");
	// showToast(statusBean.status + statusBean.msg);
	// }
	// }
	//
	// @Override
	// public void onFailure(HttpException arg0, String arg1) {
	// dismissProgressDialog();
	// showToast("网络异常");
	// }
	// });
	// }

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	public void onResume() {
		super.onResume();
		kaipioagv.setAdapter(gridkaipioagvadapter);
		getinfo();
		sctp();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}