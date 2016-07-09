package com.femto.shipper.bean;

import java.util.List;

public class OrderListBean {
	public String status;
	public String msg;
	public String timestamp;
	public String count;
	public String pagesize;
	public List<OrderListZ> list;

	public class OrderListZ {
		public String order_no;
		public String addr_start;
		public String addr_end;
		public String payment_time;
		public List<OrderlistX> car_list;

		public class OrderlistX {
			public String plate_number;
			public String car_type;
			public String car_status;
		}
	}

}
