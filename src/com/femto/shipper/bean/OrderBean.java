package com.femto.shipper.bean;

import java.util.List;

public class OrderBean {

	public String status;
	public String msg;
	public OrderBeanZ order;

	public class OrderBeanZ {
		public String order_amount;
		public String distance;
		public String order_no; // 订单号
		public String payment_time; // 支付时间
		public String address_start; // 装货地址
		public String address_end; // 卸货地址
		public String address_midway1; // 中点站
		public String address_midway2;
		public String address_midway3;
		public String address_midway4;
		public String address_midway5;
		public String trip_amount; // 运费
		public String contacts_name; // 装货联系人
		public String contacts_mobile; // 装货联系人电话
		public String accept_name; // 卸货联系人
		public String accept_mobile; // 卸货联系人电话
		public String use_time; // 装货时间
		public String goods_type; // 货物属性
		public String extra_request; // 额外要求
		public String request_amount; // 要求费用
		public String cartype; // 车型
		public String car_quantity; // 车辆数
		public String is_up_goods; // 是否要上货（0否，1是），以下全都是
		public String is_down_goods; // 下货
		public String is_supercargo; // 跟车
		public String supercargo_number; // 跟车人数
		public String is_up_floor; // 上楼
		public String floor_number; // 楼层
		public String is_lift; // 是否有电梯
		public String carry_amount; // 搬运费用
		public String pack;
		public String address_start_contact;
		public String address_end_contact;
		public String address_midway1_contact;
		public String address_midway2_contact;
		public String address_midway3_contact;
		public String address_midway4_contact;
		public String address_midway5_contact;
		public String remark;
		public String coupon_amount;
		public List<car_list> car_list;

		public class car_list {
			public String driver_avatar;
			public String driver_name;
			public String star;
			public String plate_number;
			public String state;
			public String order_state;
			public String ismycar;
			public String yid;
			public String cid;
			public String start_time;
			public String sign_time;
			public String mobile;
			public String driver_id;

		};

	}
}
