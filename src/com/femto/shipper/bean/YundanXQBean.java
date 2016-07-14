package com.femto.shipper.bean;

public class YundanXQBean {

	public String status;
	public Waybill_info waybill_info;
	public String timestamp;

	public class Waybill_info {
		public String avatar;
		public String credit;
		public String yid;
		public String nick_name;
		public String plate_number;
		public String mobile;
		public String cid;
		public String lng;
		public String lat;
		public String arrive_time;
		public String sign_time;
		public String start_time;
		public String ismycar;
		public String car_status;
		public String waybill_state;
		public String driver_id;
		public String addr_start;
		public String address_midway1;
		public String address_midway2;
		public String address_midway3;
		public String address_midway4;
		public String address_midway5;
		public String address_end;
	}

	public String msg;
}
