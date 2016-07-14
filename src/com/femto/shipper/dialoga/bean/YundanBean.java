package com.femto.shipper.dialoga.bean;

import java.util.List;

public class YundanBean
{
	
	public String status;
	public List<YundanBeanZ> list;
	public class YundanBeanZ{
		public String avatar;
		public String credit;
		public String yid;
		public String nick_name;
		public String plate_number;
		public String driver_id;
		public String mobile;
		public String ismycar;
		public String car_status;
	}
	public String msg;
}
