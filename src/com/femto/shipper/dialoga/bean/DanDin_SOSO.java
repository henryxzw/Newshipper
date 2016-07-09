package com.femto.shipper.dialoga.bean;

import java.util.List;

import com.femto.shipper.bean.OrderListBean.OrderListZ;
import com.femto.shipper.bean.OrderListBean.OrderListZ.OrderlistX;

public class DanDin_SOSO
{

	public String status;
	public String count;
	public OrderListB orderinfo;
	public class OrderListB{
		public String order_no;
		public String addr_start;
		public String addr_end;
		public String payment_time;
		public List<OrderlistX> car_list;
		public class OrderlistX{
			public String plate_number;
			public String car_type;
			public String car_status;
		}
	}
	
	
}
