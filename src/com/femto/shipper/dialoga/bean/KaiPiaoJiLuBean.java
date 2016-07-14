package com.femto.shipper.dialoga.bean;

import java.util.List;

public class KaiPiaoJiLuBean
{
	
	public String status;
	public String msg;
	public List<KaiPiaoJiLuZ> list;
	public class KaiPiaoJiLuZ{
		public String amount;
		public String status;
		public String text;
		public String title;
	}
}
