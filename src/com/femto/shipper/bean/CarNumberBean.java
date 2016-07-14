package com.femto.shipper.bean;

import java.util.List;

public class CarNumberBean
{
	public String count;
	public String distance;
	public String status;
	public String msg;
	
	public List<CarNumberZ> list;
	
	public class CarNumberZ{
		public String lat;
		public String lng;
	}
}
