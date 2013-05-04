package model.dto;

public class Post {
	private int postid; //포스팅 아이디
	private int categoryId; //카테고리 아이디
	private String userid;	//유저 아이디(반드시 값 있음)
	private String title;	//포스팅 제목(반드시 값 있음)
	private String shopname;
	private String category;//음식점이냐, 쇼핑몰이냐 이런 거
	private String tel;		//전화번호
	private String image;	//이미지명
	private int imageNumber; // 이미지 번호명, 대표 이미지 선정을 위해 필요
	private String text;	//텍스트
	private String uptime;	//글 올린 시간
	private double latitude;	//위도
	private double longitude;	//경도
	
	public Post() {
		super();
	}

	public Post(int postid, int categoryId,String userid, String title, String shopname, String category,
			String tel, String image, int imageNumber, String text,
			String uptime, double latitude, double longitude) {
		super();
		this.postid = postid;
		this.categoryId = categoryId;
		this.userid = userid;
		this.title = title;
		this.shopname = shopname;
		this.category = category;
		this.tel = tel;
		this.image = image;
		this.imageNumber = imageNumber;
		this.text = text;
		this.uptime = uptime;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public int getPostid() {
		return postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
	}
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getImageNumber() {
		return imageNumber;
	}

	public void setImageNumber(int imageNumber) {
		this.imageNumber = imageNumber;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUptime() {
		return uptime;
	}

	public void setUptime(String uptime) {
		this.uptime = uptime;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Post [userid=");
		builder.append(userid);
		builder.append(", title=");
		builder.append(title);
		builder.append(", shopname=");
		builder.append(shopname);
		builder.append(", category=");
		builder.append(category);
		builder.append(", tel=");
		builder.append(tel);
		builder.append(", image=");
		builder.append(image);
		builder.append(", imageNumber=");
		builder.append(imageNumber);
		builder.append(", text=");
		builder.append(text);
		builder.append(", uptime=");
		builder.append(uptime);
		builder.append(", latitude=");
		builder.append(latitude);
		builder.append(", longitude=");
		builder.append(longitude);
		builder.append("]");
		return builder.toString();
	}

	
}