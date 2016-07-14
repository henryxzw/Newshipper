package com.femto.shipper.bean;

public class Order_gengduoBean
{
	public String status;
	public waybill_info_img waybill_info_img;

	public class waybill_info_img
	{
		public String addr_start;// 中山市明珠路与长和路交叉口附近|113.28665|22.627969",
		public String addr_start_img;
		public String addr_start_state;// : "1",
		public String addr_start_time;// ": "2016-01-30 11:12:13",

		public String address_midway1;// "广东省中山市体育馆|113.400996|22.507307",
		public String address_midway2;// 广东省中山市体育馆|113.400996|22.507307",
		public String address_midway3;
		public String address_midway4;
		public String address_midway5;

		public String midway1_img;
		public String midway1_state;
		public String midway1_time;// : "0001-01-01 00:00:00",
		public String midway2_img;
		public String midway2_state;
		public String midway2_time;// : "0001-01-01 00:00:00",
		public String midway3_img;
		public String midway3_state;
		public String midway3_time;// ": "0001-01-01 00:00:00",
		public String midway4_img;
		public String midway4_state;
		public String midway4_time;// ": "0001-01-01 00:00:00",
		public String midway5_img;
		public String midway5_state;
		public String midway5_time;// : "0001-01-01 00:00:00",

		public String address_end; // 广东省中山市体育馆|113.400996|22.507307",
		public String address_end_img;
		public String address_end_state;
		public String address_end_time;// : "2016-01-30 11:12:19",

		public String address_start_contact;// ": "张三|13690701225",
		public String address_midway1_contact;// ": "李四|13690701225",
		public String address_midway2_contact;// ": "王五|13690701225",
		public String address_midway3_contact;// ": "王五|13690701225",
		public String address_midway4_contact;// ": "王五|13690701225",
		public String address_midway5_contact;// ": "王五|13690701225",
		public String address_end_contact;// ": "冯六|13690701225",
		
		public String driver_comment;

	}

}
