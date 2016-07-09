package com.femto.shipper.bean;

import java.util.List;

public class KaiPiaoJiLuBean
{
	
	public String status;
	public String msg;
	public List<KaiPiaoJiLuZ> list;
	public class KaiPiaoJiLuZ{
		public String amount;
		public String check;
		public String text;
		public String title;
		public String danweimingcheng;
	}
}
