package com.femto.shipper.dialoga.bean;

import java.util.List;

public class My_CheDuiBean
{
	public String status;
	public String msg;
	public List<CheDuiList> list;

	public class CheDuiList
	{
		public String driver_id;
		public String avatar;
		public String plate_number;
		public String phone;
		public String nick_name;
		public String star;
	}
}
