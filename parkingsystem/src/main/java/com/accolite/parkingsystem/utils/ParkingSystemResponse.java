package com.accolite.parkingsystem.utils;

import java.util.Date;

public class ParkingSystemResponse {

	private String msg;
	private Date dateTime;

	public ParkingSystemResponse() {
	}

	public ParkingSystemResponse(String msg) {
		this.msg = msg;
		this.dateTime = new Date(System.currentTimeMillis());
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

}
