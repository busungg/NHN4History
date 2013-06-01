package model.dto;

public class Share {
	
	int shareid;
	String userid;
	String simage;
	String syear;
	String smonth;
	String sday;
	
	public Share()
	{
	}
	
	public Share(String userid, String simage ,String syear, String smonth, String sday) {
		super();
		this.userid = userid;
		this.simage = simage;
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

	public String getSimage() {
		return simage;
	}

	public void setSimage(String simage) {
		this.simage = simage;
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
