package com.femto.shipper.dialoga.bean;

import java.util.List;

public class XuQiuListBean {
	
	public String status;
	public String msg;
	public List<XuQiuBean> list;  
	public class XuQiuBean{
		public String amount;
		public String id;
		public String sort_id;
		public String title;
		public boolean  is_Select= false;//默认false 没选择状态
		
	}
}
