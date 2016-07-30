package com.femto.shipper.activity;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.femto.shipper.R;
import com.femto.shipper.base.BaseActivity;
import com.femto.shipper.utils.LogUtils;
import com.femto.shipper.utils.ToolUtils;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Order_op_ddxq extends BaseActivity implements OnClickListener {

	private TextView or_dikojuan;// /抵扣卷
	private TextView or_tele;// 备注
	private TextView or_sxh;// /上货下货
	private TextView or_sxhMoney;// 上下金额
	private TextView or_qphw;// 轻抛货物
	private TextView or_caeliang;// 车辆
	private TextView or_yangshi;// 箱式
	private TextView or_ewaiyaoqiu;// 额外要求
	private TextView or_eMoney;// 额外金额
	private TextView or_fa;// /已发
	private TextView or_yifa;// /已发送
	private TextView or_weizhi1, or_weizhi2, or_weizhi3, or_weizhi4,
			or_weizhi5, or_weizhi6, or_weizhi7;// /起点到终点
	private RelativeLayout or_xq_YX2, or_xq_YX3, or_xq_YX4, or_xq_YX5,
			or_xq_YX6, ll_SS;
	private TextView or_luchengjine;// /路程金额
	private TextView or_xiadanshijian;// /下单时间
	private TextView or_dindanhao;// /订单号
	private TextView or_jine;// /总金额
	private TextView or_gongli;// 公里
	private TextView or_timeZ;// /装货时间
	private TextView or_timeT;// /装货状态
	private TextView or_lxrqd;
	private TextView or_lxr1;
	private TextView or_lxr2;
	private TextView or_lxr3;
	private TextView or_lxr4;
	private TextView or_lxr5;
	private TextView or_lxrzd;
	private TextView text_SS;// //展示或隐藏
	private String phonea, passworda;
	private SharedPreferences sharedPreferences;
	private boolean isOpened1 = false; // 默认是关闭
	private int height;
	private LinearLayout or_xq_shengshuo, kuang, kuang1, dianji_IT,
			or_timeT_width, or_eMoney_width, or_caeliang_width, ddxqlla,
			ddxqllb, ddxqllc, ddxqlld, or_sxhMoney_width, or_dikojuan_width,
			youhuimongy;
	private TextView ZK_text;
	private ImageView ZK_image;
	private Intent it;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_property_xq);
		sharedPreferences = getSharedPreferences(
				getResources().getString(R.string.sqlname),
				Activity.MODE_PRIVATE);
		phonea = sharedPreferences.getString(
				getResources().getString(R.string.phone), "");
		passworda = sharedPreferences.getString(
				getResources().getString(R.string.pwd), "");
		it = getIntent();
		info();
		// // 测量高度
		or_xq_shengshuo.measure(0, 0);
		height = or_xq_shengshuo.getMeasuredHeight();
		// ///默认隐藏
		LayoutParams layoutParams1 = (LayoutParams) or_xq_shengshuo
				.getLayoutParams();
		layoutParams1.height = 0;
		or_xq_shengshuo.setLayoutParams(layoutParams1);

		or_jine.setText("¥"
				+ ToolUtils.fengbujiequ(it.getStringExtra("order_amount")));
		or_gongli.setText(it.getStringExtra("distance"));
		or_dindanhao.setText(it.getStringExtra("order_no"));
		String time = it.getStringExtra("payment_time").split("\\ ")[0]
				.split("\\-")[0]
				+ "/"
				+ it.getStringExtra("payment_time").split("\\ ")[0]
						.split("\\-")[1]
				+ "/"
				+ it.getStringExtra("payment_time").split("\\ ")[0]
						.split("\\-")[2]
				+ "  "
				+ it.getStringExtra("payment_time").split("\\ ")[1];
		or_xiadanshijian.setText(time);

		or_weizhi1.setText(it.getStringExtra("address_start").split("\\|")[0]);

		if (!it.getStringExtra("address_start_contact").equals("")) {
			or_lxrqd.setText(it.getStringExtra("address_start_contact").split(
					"\\|")[0]
					+ "  "
					+ it.getStringExtra("address_start_contact").split("\\|")[1]);
		}

		or_weizhi7.setText(it.getStringExtra("address_end").split("\\|")[0]);

		if (!it.getStringExtra("address_end_contact").equals("")) {
			or_lxrzd.setText(it.getStringExtra("address_end_contact").split(
					"\\|")[0]
					+ "  "
					+ it.getStringExtra("address_end_contact").split("\\|")[1]);
		}

		if (Float.valueOf(ToolUtils.fengbujiequ(it
				.getStringExtra("trip_amount"))) > 0) {
			or_luchengjine.setText("  ¥"
					+ ToolUtils.fengbujiequ(it.getStringExtra("trip_amount"))
					+ " ");
		} else {
			or_luchengjine.setVisibility(View.GONE);
		}

		or_fa.setText(it.getStringExtra("contacts_name") + "  "
				+ it.getStringExtra("contacts_mobile"));
		or_timeZ.setText(it.getStringExtra("use_time"));
		// /

		String ewyq = it.getStringExtra("extra_request");
		if (!ewyq.equals("")) {
			String a = "";
			for (int i = 0; i < ewyq.split("\\,").length; i++) {

				a = a + ewyq.split("\\,")[i] + " ";
				if (i == 2) {
					a = a + "\n";
				}

			}
			or_ewaiyaoqiu.setText(a);// /额外要求
		} else {
			or_ewaiyaoqiu.setText("");// /额外要求
			ddxqlla.setVisibility(View.GONE);
		}

		if (Float.valueOf(ToolUtils.fengbujiequ(it
				.getStringExtra("request_amount"))) > 0) {
			or_eMoney
					.setText("  ¥"
							+ ToolUtils.fengbujiequ(it
									.getStringExtra("request_amount")) + " ");// /额外费用
		} else {
			or_eMoney.setVisibility(View.GONE);
		}
		or_yangshi.setText(it.getStringExtra("cartype").split("\\,")[0] + "  "
				+ it.getStringExtra("cartype").split("\\,")[1] + "  "
				+ it.getStringExtra("cartype").split("\\,")[2]);
		or_qphw.setText(it.getStringExtra("goods_type") + "  "
				+ it.getStringExtra("pack"));
		String str = "";
		if (it.getStringExtra("is_up_goods").equals("1")) {
			str = str + "上货" + " ";
		}
		if (it.getStringExtra("is_down_goods").equals("1")) {
			str = str + "下货" + " ";
		}
		if (it.getStringExtra("is_supercargo").equals("1")) {
			str = str + "跟车" + "(" + it.getStringExtra("supercargo_number")
					+ "人)" + " ";
		}
		if (it.getStringExtra("is_up_floor").equals("1")) {
			str = str + "上楼" + "(" + it.getStringExtra("floor_number") + "层)"
					+ " ";
			if (it.getStringExtra("is_lift").equals("1")) {
				str = str + "有楼梯";
			}
		}
		or_sxh.setText(str);// //上下货
		if (str.equals("")) {
			ddxqllb.setVisibility(View.GONE);
		}
		LogUtils.i("--------通过----9--->>>>" + str);

		if (Float.valueOf(ToolUtils.fengbujiequ(it
				.getStringExtra("carry_amount"))) > 0) {
			or_sxhMoney.setText("  ¥"
					+ ToolUtils.fengbujiequ(it.getStringExtra("carry_amount"))
					+ " ");
		} else {
			or_sxhMoney.setVisibility(View.GONE);
		}
		or_yangshi.setText(it.getStringExtra("cartype"));// 样式
		or_caeliang.setText(it.getStringExtra("car_quantity") + "辆");// /几辆车
		or_tele.setText(it.getStringExtra("remark"));
		if (it.getStringExtra("remark").equals("")) {
			ddxqllc.setVisibility(View.GONE);
		}
		if (Float.valueOf(it.getStringExtra("coupon_amount")) > 0) {
			or_dikojuan.setText("  ¥"
					+ ToolUtils.fengbujiequ(it.getStringExtra("coupon_amount"))
					+ "   ");
		} else {
			or_dikojuan.setVisibility(View.GONE);
			ddxqlld.setVisibility(View.GONE);
		}

	}

	private void info() {
		ddxqlla = (LinearLayout) findViewById(R.id.ddxqlla);
		ddxqllb = (LinearLayout) findViewById(R.id.ddxqllb);
		ddxqllc = (LinearLayout) findViewById(R.id.ddxqllc);
		ddxqlld = (LinearLayout) findViewById(R.id.ddxqlld);
		or_dikojuan = (TextView) findViewById(R.id.or_dikojuan);// /抵扣卷
		or_tele = (TextView) findViewById(R.id.or_tele);// 备注
		or_sxh = (TextView) findViewById(R.id.or_sxh);// /上货下货
		or_sxhMoney = (TextView) findViewById(R.id.or_sxhMoney);// 上下金额
		or_qphw = (TextView) findViewById(R.id.or_qphw);// 轻抛货物
		or_caeliang = (TextView) findViewById(R.id.or_caeliang);// 车辆
		or_yangshi = (TextView) findViewById(R.id.or_yangshi);// 箱式
		or_ewaiyaoqiu = (TextView) findViewById(R.id.or_ewaiyaoqiu);// 额外要求
		or_eMoney = (TextView) findViewById(R.id.or_eMoney);// 额外金额
		or_fa = (TextView) findViewById(R.id.or_fa);// /已发
		or_luchengjine = (TextView) findViewById(R.id.or_luchengjine);//
		// /路程金额
		or_xiadanshijian = (TextView) findViewById(R.id.or_xiadanshijian);//
		// /下单时间
		or_dindanhao = (TextView) findViewById(R.id.or_dindanhao);// /订单号
		or_jine = (TextView) findViewById(R.id.or_jine);// /总金额
		or_gongli = (TextView) findViewById(R.id.or_gongli);// 公里
		or_timeZ = (TextView) findViewById(R.id.or_timeZ);// /装货时间
		or_timeT = (TextView) findViewById(R.id.or_timeT);// /装货状态
		or_xq_shengshuo = (LinearLayout) findViewById(R.id.or_xq_shengshuo);
		or_weizhi1 = (TextView) findViewById(R.id.or_weizhi1);// /位置（起点到终点）
		or_weizhi2 = (TextView) findViewById(R.id.or_weizhi2);
		or_weizhi3 = (TextView) findViewById(R.id.or_weizhi3);
		or_weizhi4 = (TextView) findViewById(R.id.or_weizhi4);
		or_weizhi5 = (TextView) findViewById(R.id.or_weizhi5);
		or_weizhi6 = (TextView) findViewById(R.id.or_weizhi6);
		or_weizhi7 = (TextView) findViewById(R.id.or_weizhi7);
		or_xq_YX2 = (RelativeLayout) findViewById(R.id.or_xq_YX2);
		or_xq_YX3 = (RelativeLayout) findViewById(R.id.or_xq_YX3);
		or_xq_YX4 = (RelativeLayout) findViewById(R.id.or_xq_YX4);
		or_xq_YX5 = (RelativeLayout) findViewById(R.id.or_xq_YX5);
		or_xq_YX6 = (RelativeLayout) findViewById(R.id.or_xq_YX6);
		or_lxrqd = (TextView) findViewById(R.id.or_lxrqd);
		or_lxr1 = (TextView) findViewById(R.id.or_lxr1);
		or_lxr2 = (TextView) findViewById(R.id.or_lxr2);
		or_lxr3 = (TextView) findViewById(R.id.or_lxr3);
		or_lxr4 = (TextView) findViewById(R.id.or_lxr4);
		or_lxr5 = (TextView) findViewById(R.id.or_lxr5);
		or_lxrzd = (TextView) findViewById(R.id.or_lxrzd);
		ZK_image = (ImageView) findViewById(R.id.ZK_image);
		ZK_text = (TextView) findViewById(R.id.ZK_text);
		findViewById(R.id.left).setOnClickListener(this);
		kuang = (LinearLayout) findViewById(R.id.kuang);
		kuang1 = (LinearLayout) findViewById(R.id.kuang1);
		dianji_IT = (LinearLayout) findViewById(R.id.dianji_IT);
		or_timeT_width = (LinearLayout) findViewById(R.id.or_timeT_width);
		or_eMoney_width = (LinearLayout) findViewById(R.id.or_eMoney_width);
		or_caeliang_width = (LinearLayout) findViewById(R.id.or_caeliang_width);
		or_sxhMoney_width = (LinearLayout) findViewById(R.id.or_sxhMoney_width);
		or_dikojuan_width = (LinearLayout) findViewById(R.id.or_dikojuan_width);
		youhuimongy = (LinearLayout) findViewById(R.id.youhuimongy);

		dianji_IT.setOnClickListener(this);
		// // /设置收藏和黑名单位置
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		LayoutParams rlawidth1 = (LayoutParams) kuang.getLayoutParams();
		LayoutParams rlawidth2 = (LayoutParams) kuang1.getLayoutParams();
		LayoutParams rlawidth4 = (LayoutParams) or_timeT_width
				.getLayoutParams();
		LayoutParams rlawidth5 = (LayoutParams) or_eMoney_width
				.getLayoutParams();
		LayoutParams rlawidth6 = (LayoutParams) or_caeliang_width
				.getLayoutParams();
		LayoutParams rlawidth7 = (LayoutParams) or_sxhMoney_width
				.getLayoutParams();
		LayoutParams rlawidth8 = (LayoutParams) or_dikojuan_width
				.getLayoutParams();
		rlawidth1.width = width * 3 / 5;
		rlawidth2.width = width * 2 / 5;
		rlawidth4.width = width * 2 / 5;
		rlawidth5.weight = width * 2 / 5;
		rlawidth6.weight = width * 2 / 5;
		rlawidth7.weight = width * 2 / 5;
		rlawidth8.weight = width * 2 / 5;
		kuang.setLayoutParams(rlawidth1);
		kuang1.setLayoutParams(rlawidth2);
		or_timeT_width.setLayoutParams(rlawidth4);
		or_eMoney_width.setLayoutParams(rlawidth5);
		or_caeliang_width.setLayoutParams(rlawidth6);
		or_sxhMoney_width.setLayoutParams(rlawidth7);
		or_dikojuan_width.setLayoutParams(rlawidth8);
		// //////////////////////////////////////////中点站///////1////////////////////////////////////////////////////
		if (it.getStringExtra("address_midway1").equals("")) {
			or_xq_YX2.setVisibility(View.GONE);
			or_xq_YX3.setVisibility(View.GONE);
			or_xq_YX4.setVisibility(View.GONE);
			or_xq_YX5.setVisibility(View.GONE);
			or_xq_YX6.setVisibility(View.GONE);
			// kuang.setVisibility(View.INVISIBLE);
			rlawidth1.height = 0;
			kuang.setLayoutParams(rlawidth1);
		} else {

			or_weizhi2.setText(it.getStringExtra("address_midway1")
					.split("\\|")[0]);

			if (!it.getStringExtra("address_midway1_contact").equals("")) {
				or_lxr1.setText(it.getStringExtra("address_midway1_contact")
						.split("\\|")[0]
						+ "  "
						+ it.getStringExtra("address_midway1_contact").split(
								"\\|")[1]);
			} else {
				or_lxr1.setText("                                ");
			}
			// ////////////////////////2//////////////////////////////////////
			if (it.getStringExtra("address_midway2").equals("")) {
				or_xq_YX3.setVisibility(View.GONE);
				or_xq_YX4.setVisibility(View.GONE);
				or_xq_YX5.setVisibility(View.GONE);
				or_xq_YX6.setVisibility(View.GONE);
			} else {
				or_weizhi3.setText(it.getStringExtra("address_midway2").split(
						"\\|")[0]);

				if (!it.getStringExtra("address_midway2_contact").equals("")) {
					or_lxr2.setText(it
							.getStringExtra("address_midway2_contact").split(
									"\\|")[0]
							+ "  "
							+ it.getStringExtra("address_midway2_contact")
									.split("\\|")[1]);
				} else {
					or_lxr2.setText("                                ");
				}
				// ////////////////////////3////////////////////////////////////////////
				if (it.getStringExtra("address_midway3").equals("")) {
					or_xq_YX4.setVisibility(View.GONE);
					or_xq_YX5.setVisibility(View.GONE);
					or_xq_YX6.setVisibility(View.GONE);
				} else {
					or_weizhi4.setText(it.getStringExtra("address_midway3")
							.split("\\|")[0]);

					if (!it.getStringExtra("address_midway3_contact")
							.equals("")) {
						or_lxr3.setText(it.getStringExtra(
								"address_midway3_contact").split("\\|")[0]
								+ "  "
								+ it.getStringExtra("address_midway3_contact")
										.split("\\|")[1]);
					} else {
						or_lxr3.setText("                                ");
					}
					// ///////////////////////4////////////////////////////////////////////////
					if (it.getStringExtra("address_midway4").equals("")) {
						or_xq_YX5.setVisibility(View.GONE);
						or_xq_YX6.setVisibility(View.GONE);
					} else {
						or_weizhi5.setText(it.getStringExtra("address_midway4")
								.split("\\|")[0]);
						if (!it.getStringExtra("address_midway4_contact")
								.equals("")) {
							or_lxr4.setText(it.getStringExtra(
									"address_midway4_contact").split("\\|")[0]
									+ "  "
									+ it.getStringExtra(
											"address_midway4_contact").split(
											"\\|")[1]);
						} else {
							or_lxr4.setText("                      ");
						}
						// //////////////////////5//////////////////////////////////////
						if (it.getStringExtra("address_midway5").equals("")) {
							or_xq_YX6.setVisibility(View.GONE);
						} else {
							or_weizhi6.setText(it.getStringExtra(
									"address_midway5").split("\\|")[0]);
							LogUtils.i("--------------->>>"
									+ it.getStringExtra("address_midway5")
											.split("\\|")[0]);
							if (!it.getStringExtra("address_midway5_contact")
									.equals("")) {
								or_lxr5.setText(it.getStringExtra(
										"address_midway5_contact").split("\\|")[0]
										+ "  "
										+ it.getStringExtra(
												"address_midway5_contact")
												.split("\\|")[1]);
							} else {
								or_lxr5.setText("                                ");
							}
						}
					}
				}
			}
		}

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (isFastDoubleClick()) {
			return;
		}
		switch (arg0.getId()) {
		case R.id.left:
			finish();
			break;
		case R.id.dianji_IT:
			toggle1(true);
			break;

		default:
			break;
		}
	}

	// 打开或是关闭
	private void toggle1(boolean animated) {

		if (isOpened1) {// 是打开的。关闭
			if (animated) {// 执行动画
				int start = height;
				int end = 0;
				doAnimation1(start, end);
			} else {
				LayoutParams layoutParams1 = (LayoutParams) or_xq_shengshuo
						.getLayoutParams();
				layoutParams1.height = 0;
				or_xq_shengshuo.setLayoutParams(layoutParams1);

			}
		} else {// 关闭状态，打开
			if (animated) {
				int start = 0;
				int end = height;
				doAnimation1(start, end);
			} else {
				LayoutParams layoutParams1 = (LayoutParams) or_xq_shengshuo
						.getLayoutParams();
				layoutParams1.height = height;
				or_xq_shengshuo.setLayoutParams(layoutParams1);
			}
		}
		// 改变箭头
		if (isOpened1) {

			ZK_image.setBackgroundResource(R.drawable.nnmoren);
			ZK_text.setText("展开");

		} else {
			ZK_image.setBackgroundResource(R.drawable.nn);
			ZK_text.setText("收起");

		}
		isOpened1 = !isOpened1;
	}

	/**
	 * 执行动画
	 * 
	 * @param start
	 * @param end
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void doAnimation1(int start, int end) {
		ValueAnimator valueAnimator = new ValueAnimator();
		valueAnimator.setDuration(500);
		// 设置值的变化
		valueAnimator.setIntValues(start, end);
		// 设置监听
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				int value = (Integer) valueAnimator.getAnimatedValue();// 获取当前变化的值
				// 修改属性
				LayoutParams layoutParams = (LayoutParams) or_xq_shengshuo
						.getLayoutParams();
				layoutParams.height = value;
				or_xq_shengshuo.setLayoutParams(layoutParams);
			};
		});
		// 启动动画
		valueAnimator.start();
	}

	public void onResume() {
		super.onResume();
	}

	public void onPause() {
		super.onPause();
	}
}
