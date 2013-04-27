package model.dto;

public class Post {
	private String image;
	private String text;
	private String uptime;
	private double latitude;
	private double longitude;
	
	public Post() {
		super();
	}
	
	public Post(String image, String text, String uptime, double latitude,
			double longitude) {
		super();
		this.image = image;
		this.text = text;
		this.uptime = uptime;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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
		builder.append("Message [image=");
		builder.append(image);
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