package com.demo.bean;

import java.io.Serializable;

public class AdminBean implements Serializable{
	private int adid;
	private String adname;
	private String adpass;
	public String getAdpass() {
		return adpass;
	}
	public void setAdpass(String adpass) {
		this.adpass = adpass;
	}
	public int getAdid() {
		return adid;
	}
	public void setAdid(int adid) {
		this.adid = adid;
	}
	public String getAdname() {
		return adname;
	}
	public void setAdname(String adname) {
		this.adname = adname;
	}
}
