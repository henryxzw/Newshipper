package com.femto.shipper.dialoga.bean;

public class LocationBean
{
	public String address;
	public String jingdu;
	public String weidu;
	public int position;
	public LocationBean(int position,String address, String jingdu, String weidu)
	{
		super();
		this.position = position;
		this.address = address;
		this.jingdu = jingdu;
		this.weidu = weidu;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getJingdu()
	{
		return jingdu;
	}
	public void setJingdu(String jingdu)
	{
		this.jingdu = jingdu;
	}
	public String getWeidu()
	{
		return weidu;
	}
	public void setWeidu(String weidu)
	{
		this.weidu = weidu;
	}
	public int getPosition()
	{
		return position;
	}
	public void setPosition(int position)
	{
		this.position = position;
	}
	
	
}
