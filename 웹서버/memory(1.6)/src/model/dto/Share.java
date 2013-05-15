package model.dto;

public class Share {
	
	int shareid;
	String userid;
	String syear;
	String smonth;
	String sday;
	
	public Share()
	{
	}
	
	public Share(int shareid, String userid, String syear, String smonth, String sday) {
		super();
		this.shareid = shareid;
		this.userid = userid;
		this.syear = syear;
		this.smonth = smonth;
		this.sday = sday;
	}

	public int getShareid() {
		return shareid;
	}

	public void setShareid(int shareid) {
		this.shareid = shareid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSyear() {
		return syear;
	}

	public void setSyear(String syear) {
		this.syear = syear;
	}

	public String getSmonth() {
		return smonth;
	}

	public void setSmonth(String smonth) {
		this.smonth = smonth;
	}

	public String getSday() {
		return sday;
	}

	public void setSday(String sday) {
		this.sday = sday;
	}
}
