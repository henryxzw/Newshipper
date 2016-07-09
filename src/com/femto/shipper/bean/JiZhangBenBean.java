package com.femto.shipper.bean;

import java.util.List;

public class JiZhangBenBean
{
	public String status;
	public String amount;
	public List<JiZhangBenBeanZ> list;
	public class JiZhangBenBeanZ
	{
		public String date; // "date": "2015-12-30 12:00:00",
		public String title; // "title": "B123456789",
		public String momeny; // "momeny": "-99.0"
	}
}
