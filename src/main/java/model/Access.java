package model;

import java.io.Serializable;

public class Access implements Serializable {
	private String userName; // ユーザー名
	private String intime; // 入室時間
	private String outtime;//退室時間

	public Access() {
	}
	

	public Access(String userName, String intime, String outtime) {
	    this.userName = userName;
	    this.intime = intime;
	    this.outtime = outtime;
	}
	
	public String getUserName() {
		return userName;
	}

	public String getInTime() {
		return intime;
	}
	
	public String getOutTime() {
		return outtime;
	}
	



}