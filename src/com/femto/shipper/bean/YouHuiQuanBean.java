package com.femto.shipper.bean;

import java.util.List;

public class YouHuiQuanBean
{
	public String status;
	public String msg;
	public List<YouHuiQuanZ> list;
	
	public class YouHuiQuanZ{
		public String Amount;
		public String ClosingTime;
		public String Description;
		public String DiscountValue;
		public String Name;
		public String ServerTime;
	}

}
