package com.femto.shipper.dialoga.bean;

import java.util.List;

public class HuoWuTypeBean
{
	public String status;
	public String msg;
	public List<HuoWuTy> list;
	public class HuoWuTy{
		public String amount;
		public String id;
		public String sort_id;
		public String title;
	}
}
