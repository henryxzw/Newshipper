package com.femto.shipper.bean;

import java.util.List;

public class KaipiaoBean
{
	public String status;
	public String msg;
	public List<KaipiaoBeanZ> list;

	public class KaipiaoBeanZ
	{
		public String id;
		public String address;
		public String contact_name;
		public String mobile;
		public String status;
		public String zipcode;
	}

}
