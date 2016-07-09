package com.femto.shipper.dialoga.bean;

import java.util.List;

public class CheXingBiaoBean 
{
	public List<chexing_title>  list;
	public String status;
	public String msg;
	
	public class chexing_title{
		public String amount;
		public String id;// 车类型id
		public String sort_id;
		public String title;
		public List<dun_title> sub_title;
	}
	
	public class dun_title{
		public String amount;
		public String id;// 载重id
		public String sort_id;
		public String title;
		public List<mi_title> sub_title;
	}
	
	public class mi_title{
		public String amount;
		public String id;// 长度 id
		public String sort_id;
		public String title;
	}
	
}
